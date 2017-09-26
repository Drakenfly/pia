package com.pia.core.properties.primitives;

import java.lang.reflect.Field;

public class DoubleDataType extends PrimitiveDataType{
    private double value;

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
