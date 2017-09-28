package com.pia.core.properties_old.primitives;

import java.lang.reflect.Field;

public class StringDataType extends PrimitiveDataType {
    private String value;

    @Override
    public String toString () {
        return value + "";
    }


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
