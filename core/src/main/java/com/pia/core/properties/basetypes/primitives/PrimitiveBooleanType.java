package com.pia.core.properties.basetypes.primitives;

import com.pia.core.properties.basetypes.BooleanType;
import com.pia.core.properties.basetypes.IntegerType;

import java.lang.reflect.Field;

public class PrimitiveBooleanType extends BooleanType {

    public PrimitiveBooleanType (Field ownField) {
        super(ownField);
    }

    public PrimitiveBooleanType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public void writeValueBackToObject (Object object) throws IllegalAccessException {
        ownField.setBoolean(object, getValue());
    }
}
