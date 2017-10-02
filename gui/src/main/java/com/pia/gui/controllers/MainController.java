package com.pia.gui.controllers;

import com.pia.core.PluginService;
import com.pia.core.plugin.Plugin;
import com.pia.core.property.CollectionType;
import com.pia.core.property.ConstructableType;
import com.pia.core.property.DataType;
import com.pia.core.property.PiaConstructor;
import com.pia.gui.HeadingDataType;
import com.pia.gui.popup.PopupManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    TreeView<Plugin> treeView;

    @FXML
    TreeTableView<DataType> attributeTable;
    @FXML
    TreeTableColumn<DataType, String> attributeNameColumn;
    @FXML
    TreeTableColumn<DataType, DataType> attributeValueColumn;
    @FXML
    TreeTableColumn<DataType, String> attributeDescriptionColumn;

    @FXML
    TextArea pluginDescription;

    private PluginService pluginService;
    private Map<Plugin, List<DataType>> pluginDataTypeMap;
    private Plugin selectedPlugin;
    private AttributeTableCellManager cellManager;
    private Map<DataType, TreeItem<DataType>> dataTypeTreeItemMap;


    @Override
    public void initialize (URL location, ResourceBundle resources) {
        cellManager = new AttributeTableCellManager(this, attributeNameColumn, attributeValueColumn, attributeDescriptionColumn);
        setUpTreeView();
        setUpTableView();
        //setUpListView();
    }

    private void setUpTableView () {
        DataType rootDataType = null;
        try {
            rootDataType = DataType.getDataType(String.class, String.class);
        } catch (IllegalAccessException e) {
            PopupManager.quickStacktraceDisplay(e);
        }
        TreeItem<DataType> root = new TreeItem<>(rootDataType);
        attributeTable.setRoot(root);
        attributeTable.setShowRoot(false);

        cellManager.setUpCellValueFactories();
        cellManager.setUpCellFactories();

        attributeTable.setEditable(true);
        attributeNameColumn.setEditable(false);
        attributeValueColumn.setEditable(true);
        attributeDescriptionColumn.setEditable(false);

        attributeNameColumn.prefWidthProperty().bind(attributeTable.widthProperty().divide(3));
        attributeValueColumn.prefWidthProperty().bind(attributeTable.widthProperty().divide(2.5));
        attributeDescriptionColumn.prefWidthProperty().bind(attributeTable.widthProperty().divide(5));

        attributeNameColumn.setSortable(false);
        attributeValueColumn.setSortable(false);
        attributeDescriptionColumn.setSortable(false);
    }

    public void updateTableContent () {
        logger.info("updating table content");
        if (selectedPlugin == null) {
            return;
        }
        attributeTable.getRoot().getChildren().clear();
        for (DataType dataType : pluginDataTypeMap.get(selectedPlugin)) {
            TreeItem<DataType> item = getTreeItemFromDataType(dataType);
            attributeTable.getRoot().getChildren().add(item);
        }

        refresh();
    }

    public void refresh () {
        attributeTable.getColumns().get(0).setVisible(false);
        attributeTable.getColumns().get(0).setVisible(true);
    }

    public void updateItem (DataType item) {
        if (item == null) {
            return;
        }
        TreeItem<DataType> node = dataTypeTreeItemMap.get(item);
        node.getChildren().clear();
        TreeItem<DataType> parent = node.getParent();
        int index = parent.getChildren().indexOf(node);
        parent.getChildren().remove(node);
        TreeItem<DataType> newNode = getTreeItemFromDataType(node.getValue());
        parent.getChildren().add(index, newNode);
        newNode.setExpanded(true);
        parent.setExpanded(true);
    }

    public void setPluginService (PluginService pluginService) {
        this.pluginService = pluginService;
        this.pluginDataTypeMap = new LinkedHashMap<>();
        this.dataTypeTreeItemMap = new LinkedHashMap<>();
        for (Plugin plugin : pluginService.getLoadedPlugins()) {
            List<DataType> dataTypes = new LinkedList<>();
            for (Field field : plugin.getAnnotatedFields()) {
                try {
                    dataTypes.add(DataType.getDataType(field));
                } catch (IllegalAccessException e) {
                    PopupManager.quickStacktraceDisplay(e);
                }
            }
            this.pluginDataTypeMap.put(plugin, dataTypes);
        }
    }

    private void setUpTreeView () {
        treeView.setCellFactory(c -> new CheckBoxTreeCell<Plugin>() {
            @Override
            public void updateItem (Plugin item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                }
            }
        });

        treeView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<TreeItem<Plugin>>() {

                    @Override
                    public void changed (
                            ObservableValue<? extends TreeItem<Plugin>> observable,
                            TreeItem<Plugin> old_val, TreeItem<Plugin> new_val) {
                        selectedPlugin = new_val.getValue();
                        selectPlugin();
                    }

                });

        CheckBoxTreeItem<Plugin> rootItem = new CheckBoxTreeItem<>();
        rootItem.setExpanded(true);
        //rootItem.setValue(new TestPlugin("Root"));
        treeView.setShowRoot(false);

        treeView.setRoot(rootItem);
    }

    public void populateTreeWithPlugins () {
        for (Plugin plugin : pluginService.getLoadedPlugins()) {
            CheckBoxTreeItem<Plugin> item = new CheckBoxTreeItem<>();
            item.setValue(plugin);
            treeView.getRoot().getChildren().add(item);
        }
    }

    private void selectPlugin () {
        pluginDescription.setText("The description for plugin \"" + selectedPlugin.getName() + "\" goes here. " +
                "Dependencies and other information will be displayed here as well in a list.");
        updateTableContent();
    }

    private TreeItem<DataType> getTreeItemFromDataType (DataType dataType) {
        TreeItem<DataType> root = new TreeItem<>(dataType);

        TreeItem<DataType> constructorParamsRoot = root;
        TreeItem<DataType> publicAttributesRoot = root;
        TreeItem<DataType> childrenRoot = root;

        List<TreeItem<DataType>> constructorParams = new LinkedList<>();
        List<TreeItem<DataType>> publicAttributes = new LinkedList<>();
        List<TreeItem<DataType>> children = new LinkedList<>();

        if (dataType instanceof CollectionType) {
            for (DataType child : ((CollectionType<?>) dataType).getChildren()) {
                children.add(getTreeItemFromDataType(child));
            }
        }

        if (dataType instanceof ConstructableType) {
            PiaConstructor constructor = null;
            List<DataType> arguments;

            try {
                constructor = ((ConstructableType) dataType).getChosenConstructor();
            } catch (IllegalAccessException e) {
                PopupManager.quickStacktraceDisplay(e);
            }

            arguments = ((ConstructableType) dataType).getChosenArgumens();
            if (constructor != null && arguments != null) {
                for (DataType child : arguments) {
                    constructorParams.add(getTreeItemFromDataType(child));
                }
            }
        }

        if (((constructorParams.size() > 0 ? 1 : 0) + (publicAttributes.size() > 0 ? 1 : 0) + (children.size() > 0 ? 1 : 0)) > 1) {
            //add headings before individal list entries
            constructorParamsRoot = new TreeItem<>(new HeadingDataType("Constructor parameters"));
            publicAttributesRoot = new TreeItem<>(new HeadingDataType("Public variables"));
            childrenRoot = new TreeItem<>(new HeadingDataType("Child elements"));
            if (constructorParams.size() > 0) root.getChildren().add(constructorParamsRoot);
            if (publicAttributes.size() > 0) root.getChildren().add(publicAttributesRoot);
            if (children.size() > 0) root.getChildren().add(childrenRoot);
        }

        constructorParamsRoot.getChildren().setAll(constructorParams);
        publicAttributesRoot.getChildren().addAll(publicAttributes);
        childrenRoot.getChildren().addAll(children);

        dataTypeTreeItemMap.put(dataType, root);
        return root;
    }

    public DataType getParent (DataType dataType) {
        TreeItem<DataType> parent = dataTypeTreeItemMap.get(dataType).getParent();
        if (parent.getValue() instanceof HeadingDataType) {
            parent = parent.getParent();
        }
        return parent.getValue();
    }

    @FXML
    public void writeBack() {
        logger.info("Trying to write fields back to plugin");
        if (selectedPlugin != null) {
            for (TreeItem<DataType> node : attributeTable.getRoot().getChildren()) {
                try {
                    node.getValue().writeValueBackToObject(selectedPlugin);
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    PopupManager.quickStacktraceDisplay(e);
                }
            }
        }
    }

    // Keep until root surely doesn't need a value
    class TestPlugin extends Plugin {
        String name;

        TestPlugin () {
            name = "Test PluginMetadata";
        }

        TestPlugin (String name) {
            this.name = name;
        }

        @Override
        public void start () {
            System.out.println("Test plugin started");
        }
    }
}