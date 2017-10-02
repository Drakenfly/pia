package com.pia.gui.controllers;

import com.pia.core.property.*;
import com.pia.gui.HeadingDataType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Collection;

public class TypeDependantCell extends TreeTableCell<DataType, DataType> {
    private ContextMenu menu;
    private final MainController controller;
    private TextField textField;

    public TypeDependantCell (MainController controller) {
        this.controller = controller;
    }

    public void initContextMenu (MainController controller) throws IllegalAccessException {
        menu = new ContextMenu();
        DataType item = getItem();
        if (item == null) {
            return;
        }

        if (item instanceof NullableType) {
            String text = ((NullableType) item).getValueIsNull() ? "Set not null" : "Set null";
            MenuItem nullItem = new MenuItem(text);
            nullItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle (ActionEvent event) {
                    NullableType data = (NullableType) getItem();
                    data.setValueIsNull(!data.getValueIsNull());
                    controller.refresh();
                }
            });
            menu.getItems().add(nullItem);
        }
        if (item instanceof CollectionType) {
            MenuItem addItem = new MenuItem("Add Element");
            addItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle (ActionEvent event) {
                    CollectionType data = (CollectionType) item;
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

        if (! (item instanceof HeadingDataType) && (controller.getParent(item) instanceof CollectionType)) {
            MenuItem removeItem = new MenuItem("Remove Element");
            removeItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle (ActionEvent event) {
                    CollectionType data = (CollectionType) controller.getParent(item);
                    data.remove(item);
                    controller.updateTableContent();
                    if (getItem() != null) {
                        controller.updateItem(data);
                    }
                }
            });
            menu.getItems().add(removeItem);
        }

        if (item instanceof ConstructableType) {
            ConstructableType constructable = (ConstructableType) getItem();
            if (constructable.getConstructors().size() > 0 && menu.getItems().size() > 0) {
                menu.getItems().add(new SeparatorMenuItem());
            }
            for (PiaConstructor constructor : constructable.getConstructors()) {
                MenuItem entry = new MenuItem(constructor.toString());
                entry.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle (ActionEvent event) {
                        ConstructableType data = (ConstructableType) getItem();
                        try {
                            data.setChosenConstructor(constructor);
                        } catch (IllegalAccessException e) {
                            //TODO Throw error
                            e.printStackTrace();
                        }
                        controller.updateTableContent();
                        if (getItem() != null) {
                            controller.updateItem(getItem());
                        }
                    }
                });
                menu.getItems().add(entry);
            }
        }
        setContextMenu(menu);
    }


    @Override
    protected void updateItem (DataType item, boolean empty) {
        super.updateItem(item, empty);

        try {
            initContextMenu(controller);
        } catch (IllegalAccessException e) {
            //TODO throw error
            e.printStackTrace();
        }

        if (item != null) {
            if (item instanceof ConstructableType) {
                try {
                    PiaConstructor constructor = ((ConstructableType) item).getChosenConstructor();
                    setText(constructor == null ? "Choose Constructor" : constructor.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    //TODO show error message
                }
            }
            else if (!(item instanceof HeadingDataType)) {
                setText(item.toString());
            }
            if (item instanceof NullableType && ((NullableType) item).getValueIsNull()) {
                setText("null");
            }
        }
        else {
            setText(null);
            setGraphic(null);
        }
    }

    @Override
    public void startEdit () {
        if (getItem() instanceof BaseType) {
            super.startEdit();
            if (textField == null) {
                createTextField();
            }
            textField.setText(getString());
            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.requestFocus();
        }
    }

    private String getString () {
        return getItem() == null ? "" : getItem().toString();
    }

    @Override
    public void cancelEdit () {
        super.cancelEdit();
        setText(getString());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    private void createTextField () {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    ((BaseType) getItem()).parseValue(textField.getText());
                    cancelEdit();
                }
                else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });
    }
}