package com.pia.core.property.basetype.primitive;

import com.pia.core.property.basetype.ByteType;

import java.lang.reflect.Field;

public class PrimitiveByteType extends ByteType {

    public PrimitiveByteType (Field ownField) {
        super(ownField);
    }

    public PrimitiveByteType (Class ownClass) {
        super(ownClass);
    }

    @Override
    protected void writeFieldToObject (Object object) throws IllegalAccessException {
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
