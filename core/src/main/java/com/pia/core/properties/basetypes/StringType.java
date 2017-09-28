package com.pia.core.properties.basetypes;

import com.pia.core.properties.BaseType;

import java.lang.reflect.Field;

public class StringType extends BaseType<String> {
    private String value;

    public StringType (Field ownField) {
        super(ownField);
    }

    @Override
    protected void setDefaultValue () {
        value = null;
    }

    public StringType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public String getContentType () {
        return "String";
    }

    @Override
    public String toString () {
        return value;
    }

    @Override
    public String getValue () {
        return value;
    }

    @Override
    public void setValue (String value) {
        this.value = value;
    }

    @Override
    public void parseValue (String value) {
        setValue(value);
    }
}
