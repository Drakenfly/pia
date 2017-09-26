package com.pia.core.properties.primitiveObjects;

import java.lang.reflect.Field;

public class FloatObjectDataType extends PrimitiveObjectDataType {
    private Float value;

    @Override
    public String toString () {
        return value + "";
    }


    protected FloatObjectDataType (Field ownField) {
        super(ownField);
    }

    public Float getValue () {
        return value;
    }

    public void setValue (Float value) {
        this.value = value;
    }
}
