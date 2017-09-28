package com.pia.core.properties.basetypes.primitives;

import com.pia.core.properties.basetypes.LongType;

import java.lang.reflect.Field;

public class PrimitiveLongType extends LongType {

    public PrimitiveLongType (Field ownField) {
        super(ownField);
    }

    public PrimitiveLongType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public void writeValueBackToObject (Object object) throws IllegalAccessException {
        ownField.setLong(object, getValue());
    }
}
