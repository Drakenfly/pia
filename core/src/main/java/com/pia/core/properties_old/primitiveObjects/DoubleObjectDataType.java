package com.pia.core.properties_old.primitiveObjects;

import java.lang.reflect.Field;

public class DoubleObjectDataType extends PrimitiveObjectDataType {
    private Double value;

    @Override
    public String toString () {
        return value + "";
    }


    protected DoubleObjectDataType (Field ownField) {
        super(ownField);
    }

    public Double getValue () {
        return value;
    }

    public void setValue (Double value) {
        this.value = value;
    }
}
