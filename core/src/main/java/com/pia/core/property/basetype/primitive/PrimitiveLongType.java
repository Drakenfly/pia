package com.pia.core.property.basetype.primitive;

import com.pia.core.property.basetype.LongType;

import java.lang.reflect.Field;

public class PrimitiveLongType extends LongType {

    public PrimitiveLongType (Field ownField) {
        super(ownField);
    }

    public PrimitiveLongType (Class ownClass) {
        super(ownClass);
    }

    @Override
    protected void writeFieldToObject (Object object) throws IllegalAccessException {
        ownField.setLong(object, getValue());
    }

    @Override
    public void setValue (Long value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot set null as value in a primitive datatype");
        }
        super.setValue(value);
    }
}
