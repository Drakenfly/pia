package com.pia.core.properties.basetypes.primitives;

import com.pia.core.properties.basetypes.IntegerType;

import java.lang.reflect.Field;

public class PrimitiveIntegerType extends IntegerType {

    public PrimitiveIntegerType (Field ownField) {
        super(ownField);
    }

    public PrimitiveIntegerType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public void writeValueBackToObject (Object object) throws IllegalAccessException {
        ownField.setInt(object, getValue());
    }
}
