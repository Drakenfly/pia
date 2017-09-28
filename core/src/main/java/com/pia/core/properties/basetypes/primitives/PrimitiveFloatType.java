package com.pia.core.properties.basetypes.primitives;

import com.pia.core.properties.basetypes.FloatType;
import com.pia.core.properties.basetypes.IntegerType;

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
}
