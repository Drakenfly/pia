package com.pia.core.property.basetype.primitive;

import com.pia.core.property.basetype.BooleanType;

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

    @Override
    public void setValue (Boolean value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot set null as value in a primitive datatype");
        }
        super.setValue(value);
    }
}
