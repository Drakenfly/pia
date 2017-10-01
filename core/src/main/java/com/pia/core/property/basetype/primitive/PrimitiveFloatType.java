package com.pia.core.property.basetype.primitive;

import com.pia.core.property.basetype.FloatType;

import java.lang.reflect.Field;

public class PrimitiveFloatType extends FloatType {

    public PrimitiveFloatType (Field ownField) {
        super(ownField);
    }

    public PrimitiveFloatType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public void writeValueBackToObject (Object object) throws IllegalAccessException {
        ownField.setFloat(object, getValue());
    }

    @Override
    public void setValue (Float value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot set null as value in a primitive datatype");
        }
        super.setValue(value);
    }
}
