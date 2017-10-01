package com.pia.core.properties.basetypes.primitives;

import com.pia.core.properties.basetypes.DoubleType;

import java.lang.reflect.Field;

public class PrimitiveDoubleType extends DoubleType {

    public PrimitiveDoubleType (Field ownField) {
        super(ownField);
    }

    public PrimitiveDoubleType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public void writeValueBackToObject (Object object) throws IllegalAccessException {
        ownField.setDouble(object, getValue());
    }

    @Override
    public void setValue (Double value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot set null as value in a primitive datatype");
        }
        super.setValue(value);
    }
}
