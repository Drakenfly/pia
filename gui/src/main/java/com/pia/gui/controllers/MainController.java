package com.pia.gui.controllers;

import com.pia.core.PluginService;
import com.pia.plugin.PiaPlugin;
import com.pia.plugin.PiaPluginProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Markus on 25.09.2017.
 */
public class MainController implements Initializable {
    @FXML
    TreeView<PiaPlugin> treeView;

    @FXML
    TextArea pluginDescription;

    @FXML
    ListView<PiaPluginProperty> pluginAttributeList;

    private PluginService pluginService;
    private ObservableList<PiaPluginProperty> pluginAttributeObservables;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        setUpTreeView();
        setUpListView();
    }

    public void setPluginService (PluginService pluginService) {
        this.pluginService = pluginService;
    }

    private void setUpTreeView() {
        treeView.setCellFactory(c -> new CheckBoxTreeCell<PiaPlugin>() {
            @Override
            public void updateItem (PiaPlugin item, boolean empty){
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                }
            }
        });

        treeView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<TreeItem<PiaPlugin>>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends TreeItem<PiaPlugin>> observable,
                            TreeItem<PiaPlugin> old_val, TreeItem<PiaPlugin> new_val) {
                        TreeItem<PiaPlugin> selectedItem = new_val;
                        selectPlugin(new_val.getValue());
                    }

                });

        CheckBoxTreeItem<PiaPlugin> rootItem = new CheckBoxTreeItem<>();
        rootItem.setExpanded(true);
        rootItem.setValue(new TestPlugin("Root"));

        treeView.setRoot(rootItem);
    }

    private void setUpListView() {
        pluginAttributeList.setCellFactory(c -> new ListCell<PiaPluginProperty>() {
            @Override
            public void updateItem (PiaPluginProperty item, boolean empty){
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName() + " = " + item.getValue());
                }
            }
        });

        pluginAttributeObservables = FXCollections.observableArrayList();
        pluginAttributeList.setItems(pluginAttributeObservables);
    }

    public void populateTreeWithPlugins() {
        for (PiaPlugin plugin: pluginService.getPlugins()) {
            CheckBoxTreeItem<PiaPlugin> item = new CheckBoxTreeItem<> ();
            item.setValue(plugin);
            treeView.getRoot().getChildren().add(item);
        }
    }

    private void selectPlugin(PiaPlugin plugin) {
        pluginDescription.setText("The description for plugin \"" + plugin.getName() + "\" goes here. " +
                "Dependencies and other information will be displayed here as well in a list.");
        pluginAttributeObservables.setAll(pluginService.getProperties(plugin));
    }
}

class TestPlugin implements PiaPlugin {
    String name;

    TestPlugin() {
        name = "Test Plugin";
    }

    TestPlugin(String name) {
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