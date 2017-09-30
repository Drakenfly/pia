package com.pia.gui.controllers;

import com.pia.core.PluginService;
import com.pia.core.properties.BaseType;
import com.pia.core.properties.CollectionType;
import com.pia.core.properties.DataType;
import com.pia.core.plugin.PiaPlugin;
import com.pia.core.properties.NullableType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    TreeView<PiaPlugin> treeView;

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
    private Map<PiaPlugin, List<DataType>> pluginDataTypeMap;
    private PiaPlugin selectedPlugin;
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
            e.printStackTrace();
            //TODO show error
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

    public void updateTableContent() {
        System.out.println("updating table content");
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

    public void refresh() {
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
        for (PiaPlugin plugin : pluginService.getLoadedPlugins()) {
            List<DataType> dataTypes = new LinkedList<>();
            for (Field field : plugin.getAnnotatedFields()) {
                try {
                    dataTypes.add(DataType.getDataType(field));
                } catch (IllegalAccessException e) {
                    // TODO Display error
                    e.printStackTrace();
                }
            }
            this.pluginDataTypeMap.put(plugin, dataTypes);
        }
    }

    private void setUpTreeView () {
        treeView.setCellFactory(c -> new CheckBoxTreeCell<PiaPlugin>() {
            @Override
            public void updateItem (PiaPlugin item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                }
            }
        });

        treeView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<TreeItem<PiaPlugin>>() {

                    @Override
                    public void changed (
                            ObservableValue<? extends TreeItem<PiaPlugin>> observable,
                            TreeItem<PiaPlugin> old_val, TreeItem<PiaPlugin> new_val) {
                        selectedPlugin = new_val.getValue();
                        selectPlugin();
                    }

                });

        CheckBoxTreeItem<PiaPlugin> rootItem = new CheckBoxTreeItem<>();
        rootItem.setExpanded(true);
        rootItem.setValue(new TestPlugin("Root"));
        treeView.setShowRoot(false);

        treeView.setRoot(rootItem);
    }

    public void populateTreeWithPlugins () {
        for (PiaPlugin plugin : pluginService.getLoadedPlugins()) {
            CheckBoxTreeItem<PiaPlugin> item = new CheckBoxTreeItem<>();
            item.setValue(plugin);
            treeView.getRoot().getChildren().add(item);
        }
    }

    private void selectPlugin () {
        pluginDescription.setText("The description for plugin \"" + selectedPlugin.getName() + "\" goes here. " +
                "Dependencies and other information will be displayed here as well in a list.");
        updateTableContent();
    }

    private TreeItem<DataType> getTreeItemFromDataType(DataType dataType) {
        TreeItem<DataType> root = new TreeItem<>(dataType);
        if (dataType instanceof CollectionType) {
            List<TreeItem<DataType>> children = new LinkedList<>();
            for (DataType child : ((CollectionType<?>) dataType).getChildren()) {
                children.add(getTreeItemFromDataType(child));
            }
            root.getChildren().setAll(children);
        }
        dataTypeTreeItemMap.put(dataType, root);
        return root;
    }

    class TestPlugin extends PiaPlugin {
        String name;

        TestPlugin () {
            name = "Test Plugin";
        }

        TestPlugin (String name) {
            this.name = name;
        }

        @Override
        public String getName () {
            return name;
        }

        @Override
        public void start () {
            System.out.println("Test plugin started");
        }
    }
}