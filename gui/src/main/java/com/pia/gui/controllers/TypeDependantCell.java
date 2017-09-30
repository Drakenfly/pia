package com.pia.gui.controllers;

import com.pia.core.properties.BaseType;
import com.pia.core.properties.CollectionType;
import com.pia.core.properties.DataType;
import com.pia.core.properties.NullableType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class TypeDependantCell extends TreeTableCell<DataType, DataType> {
    private ContextMenu menu;
    private final MainController controller;

    private TypeDependantCell (MainController controller) {
        this.controller = controller;
    }

    public static TypeDependantCell get(MainController controller) {
        TypeDependantCell cell =  new TypeDependantCell(controller);
        cell.initContextMenu(controller);
        cell.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle (ContextMenuEvent event) {
                cell.initContextMenu(controller);
                cell.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                    @Override
                    public void handle (ContextMenuEvent event) {
                        cell.initContextMenu(controller);
                    }
                });
            }
        });
        return cell;
    }

    public void initContextMenu (MainController controller) {
        menu = new ContextMenu();
        DataType item = getItem();
        if (item instanceof NullableType) {
            MenuItem nullItem = new MenuItem("Set Null");
            nullItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle (ActionEvent event) {
                    NullableType data = (NullableType) getItem();
                    data.setValueIsNull(!data.getValueIsNull());
                }
            });
            menu.getItems().add(nullItem);
        }
        if (item instanceof CollectionType) {
            MenuItem addItem = new MenuItem("Add Element");
            addItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle (ActionEvent event) {
                    CollectionType data = (CollectionType) getItem();
                    try {
                        data.add(data.getChildDataType());
                        controller.updateTableContent();
                        if (getItem() != null) {
                            controller.updateItem(getItem());
                        }

                    } catch (IllegalAccessException e) {
                        //TODO show error
                        e.printStackTrace();
                    }
                }
            });
            menu.getItems().add(addItem);
        }
        setContextMenu(menu);
    }

    private TextField textField;

    @Override
    protected void updateItem (DataType item, boolean empty) {
        super.updateItem(item, empty);

        initContextMenu(controller);

        if (item != null) {
            setText(item.toString());
        }
        else {
            setText(null);
            setGraphic(null);
        }
    }

    @Override
    public void startEdit () {
        super.startEdit();
        if (getItem() instanceof BaseType) {
            if (textField == null) {
                createTextField();
            }
            textField.setText(getString());
            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.requestFocus();
        }
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getString());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    ((BaseType) getItem()).parseValue(textField.getText());
                    cancelEdit();
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });
    }

}