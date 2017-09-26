package com.pia.core.properties.primitives;

import java.lang.reflect.Field;

public class IntDataType extends PrimitiveDataType{
    private int value;

    protected IntDataType (Field ownField) {
        super(ownField);
    }

    public int getValue () {
        return value;
    }

    public void setValue (int value) {
        this.value = value;
    }
}
