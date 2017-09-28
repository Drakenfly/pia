package com.pia.core.properties_old.primitives;

import java.lang.reflect.Field;

public class FloatDataType extends PrimitiveDataType {
    private float value;

    @Override
    public String toString () {
        return value + "";
    }


    protected FloatDataType (Field ownField) {
        super(ownField);
    }

    public float getValue () {
        return value;
    }

    public void setValue (float value) {
        this.value = value;
    }
}
