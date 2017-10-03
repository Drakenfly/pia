package com.pia.core.property.dto;

import java.util.List;

public class DataTypeDTO {
    private DataTypeType type;
    private String fieldName;
    private String canonicalClassName;

    /* fields for BaseType implementations */
    private Object value;

    /* fields for NullableType implementations */
    private boolean isNull;
    private boolean required;

    /* fields for ConstructableType implementations */
    private List<ConstructorDTO> constructors;
    private ConstructorDTO chosenConstructor;

    /* fields for CollectionType implementations */
    private List<DataTypeDTO> children;

    /* fields for ComplexType implementations */
    private List<DataTypeDTO> publicFields;

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

    public String getCanonicalClassName () {
        return canonicalClassName;
    }

    public void setCanonicalClassName (String canonicalClassName) {
        this.canonicalClassName = canonicalClassName;
    }

    public Object getValue () {
        return value;
    }

    public void setValue (Object value) {
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

    public List<ConstructorDTO> getConstructors () {
        return constructors;
    }

    public void setConstructors (List<ConstructorDTO> constructors) {
        this.constructors = constructors;
    }

    public ConstructorDTO getChosenConstructor () {
        return chosenConstructor;
    }

    public void setChosenConstructor (ConstructorDTO chosenConstructor) {
        this.chosenConstructor = chosenConstructor;
    }

    public List<DataTypeDTO> getChildren () {
        return children;
    }

    public void setChildren (List<DataTypeDTO> children) {
        this.children = children;
    }

    public List<DataTypeDTO> getPublicFields () {
        return publicFields;
    }

    public void setPublicFields (List<DataTypeDTO> publicFields) {
        this.publicFields = publicFields;
    }
}
