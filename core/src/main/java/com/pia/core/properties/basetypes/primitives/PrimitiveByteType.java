package com.pia.core.properties.basetypes.primitives;

import com.pia.core.properties.basetypes.ByteType;

import java.lang.reflect.Field;

public class PrimitiveByteType extends ByteType {

    public PrimitiveByteType (Field ownField) {
        super(ownField);
    }

    public PrimitiveByteType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public void writeValueBackToObject (Object object) throws IllegalAccessException {
        ownField.setByte(object, getValue());
    }

    @Override
    public void setValue (Byte value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot set null as value in a primitive datatype");
        }
        super.setValue(value);
    }
}
