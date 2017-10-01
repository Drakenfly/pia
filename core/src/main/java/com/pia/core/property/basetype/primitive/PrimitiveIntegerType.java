package com.pia.core.property.basetype.primitive;

import com.pia.core.property.basetype.IntegerType;

import java.lang.reflect.Field;

public class PrimitiveIntegerType extends IntegerType {

    public PrimitiveIntegerType (Field ownField) {
        super(ownField);
    }

    public PrimitiveIntegerType (Class ownClass) {
        super(ownClass);
    }

    @Override
    protected void writeFieldToObject (Object object) throws IllegalAccessException {
        int value = getValue() == null ? 0 : getValue();
        ownField.setInt(object, value);
    }

    @Override
    public void setValue (Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot set null as value in a primitive datatype");
        }
        super.setValue(value);
    }
}
