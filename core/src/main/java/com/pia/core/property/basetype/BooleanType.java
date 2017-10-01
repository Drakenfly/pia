package com.pia.core.property.basetype;

import com.pia.core.property.BaseType;

import java.lang.reflect.Field;

public class BooleanType extends BaseType<Boolean> {
    private Boolean value;

    public BooleanType (Field ownField) {
        super(ownField);
    }

    @Override
    protected void setDefaultValue () {
        value = false;
    }

    public BooleanType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public String getContentType () {
        return "Boolean";
    }

    @Override
    public String toString () {
        return value == null ? "null" : value.toString();
    }

    @Override
    public Boolean getValue () {
        return value;
    }

    @Override
    public void setValue (Boolean value) {
        this.value = value;
    }

    @Override
    public void parseValue (String value) {
        this.value = Boolean.parseBoolean(value);
        //TODO maybe implement other parsing methods
    }
}
