package com.pia.core.properties_old.primitives;

import java.lang.reflect.Field;

public class IntDataType extends PrimitiveDataType {
    private int value;

    @Override
    public String toString () {
        return value + "";
    }

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
