package com.pia.core.properties.basetypes.primitives;

import com.pia.core.properties.basetypes.DoubleType;
import com.pia.core.properties.basetypes.IntegerType;

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
}
