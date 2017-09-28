package com.pia.core.properties_old.primitives;

import java.lang.reflect.Field;

public class BooleanDataType extends PrimitiveDataType {
    private boolean value;

    @Override
    public String toString () {
        return value + "";
    }


    protected BooleanDataType (Field ownField) {
        super(ownField);
    }

    public boolean getValue () {
        return value;
    }

    public void setValue (boolean value) {
        this.value = value;
    }
}
