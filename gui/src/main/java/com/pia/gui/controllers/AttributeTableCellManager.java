package com.pia.gui.controllers;

import com.pia.core.properties.BaseType;
import com.pia.core.properties.ConstructableType;
import com.pia.core.properties.DataType;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class AttributeTableCellManager {
    private final TreeTableColumn<DataType, String> nameCol;
    private final TreeTableColumn<DataType, DataType> valueCol;
    private final TreeTableColumn<DataType, String> descriptionCol;
    private final MainController controller;

    public AttributeTableCellManager (MainController controller, TreeTableColumn<DataType, String> nameCol, TreeTableColumn<DataType, DataType> valueCol, TreeTableColumn<DataType, String> descriptionCol) {
        this.controller = controller;
        this.nameCol = nameCol;
        this.valueCol = valueCol;
        this.descriptionCol = descriptionCol;
    }

    public void setUpCellValueFactories() {

        nameCol.setCellValueFactory(nameCellValueFactory());
        valueCol.setCellValueFactory(valueCellValueFactory());
        descriptionCol.setCellValueFactory(descriptionCellValueFactory());
    }

    public void setUpCellFactories() {
        valueCol.setCellFactory(new Callback<TreeTableColumn<DataType, DataType>, TreeTableCell<DataType, DataType>>() {
            @Override
            public TreeTableCell<DataType, DataType> call (TreeTableColumn<DataType, DataType> param) {
                return TypeDependantCell.get(controller);
            }
        });
    }

    private Callback<TreeTableColumn.CellDataFeatures<DataType, String>, ObservableValue<String>> nameCellValueFactory () {
        //TODO actual implementation
        return descriptionCellValueFactory();
    }

    private Callback<TreeTableColumn.CellDataFeatures<DataType, DataType>, ObservableValue<DataType>> valueCellValueFactory () {
        return param -> {
            DataType dataType = param.getValue().getValue();
            if (dataType instanceof ConstructableType) {

            }
            return new ReadOnlyObjectWrapper<DataType>(dataType);
        };
    }

    private Callback<TreeTableColumn.CellDataFeatures<DataType, String>, ObservableValue<String>> descriptionCellValueFactory () {
        return param -> {
            try {
                return new SimpleStringProperty(param.getValue().getValue().getContentType());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        };
    }


}
