package com.pia.core.properties_old.primitives;

import java.lang.reflect.Field;

public class DoubleDataType extends PrimitiveDataType {
    private double value;

    @Override
    public String toString () {
        return value + "";
    }


    protected DoubleDataType (Field ownField) {
        super(ownField);
    }

    public double getValue () {
        return value;
    }

    public void setValue (double value) {
        this.value = value;
    }
}
