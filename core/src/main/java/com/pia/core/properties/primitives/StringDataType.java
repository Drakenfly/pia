package com.pia.core.properties.primitives;

import java.lang.reflect.Field;

public class StringDataType extends PrimitiveDataType{
    private String value;

    protected StringDataType (Field ownField) {
        super(ownField);
    }

    public String getValue () {
        return value;
    }

    public void setValue (String value) {
        this.value = value;
    }
}
