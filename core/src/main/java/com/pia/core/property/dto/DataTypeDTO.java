package com.pia.core.property.dto;

import java.util.List;

public class DataTypeDTO<T> {
    private DataTypeType type;
    private String fieldName;

    /* fields for BaseType implementations */
    private T value;

    /* fields for NullableType implementations */
    private boolean isNull;
    private boolean required;

    /* fields for ConstructableType implementations */
    private List<ClassDTO<? extends T>> subtypes;
    private ClassDTO<? extends T> chosenImplementation;

    /* fields for CollectionType implementations */
    private List<DataTypeDTO> children;

    /* fields for ComplexType implementations */


    public DataTypeType getType () {
        return type;
    }

    public void setType (DataTypeType type) {
        this.type = type;
    }

    public String getFieldName () {
        return fieldName;
    }

    public void setFieldName (String fieldName) {
        this.fieldName = fieldName;
    }

    public T getValue () {
        return value;
    }

    public void setValue (T value) {
        this.value = value;
    }

    public boolean isNull () {
        return isNull;
    }

    public void setNull (boolean aNull) {
        isNull = aNull;
    }

    public boolean isRequired () {
        return required;
    }

    public void setRequired (boolean required) {
        this.required = required;
    }

    public List<ClassDTO<? extends T> > getSubtypes () {
        return subtypes;
    }

    public void setSubtypes (List<ClassDTO<? extends T> > subtypes) {
        this.subtypes = subtypes;
    }

    public ClassDTO getChosenImplementation () {
        return chosenImplementation;
    }

    public void setChosenImplementation (ClassDTO chosenImplementation) {
        this.chosenImplementation = chosenImplementation;
    }

    public List<DataTypeDTO> getChildren () {
        return children;
    }

    public void setChildren (List<DataTypeDTO> children) {
        this.children = children;
    }
}
