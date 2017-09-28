package com.pia.core.properties.basetypes;

import com.pia.core.properties.BaseType;

import java.lang.reflect.Field;

public class IntegerType extends BaseType<Integer> {
    private Integer value;

    public IntegerType (Field ownField) {
        super(ownField);
    }

    @Override
    protected void setDefaultValue () {
        value = 0;
    }

    public IntegerType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public String getContentType () {
        return "Integer";
    }

    @Override
    public String toString () {
        return value == null ? "null" : value.toString();
    }

    @Override
    public Integer getValue () {
        return value;
    }

    @Override
    public void setValue (Integer value) {
        this.value = value;
    }

    @Override
    public void parseValue (String value) {
        this.value = Integer.parseInt(value);
    }
}
