package com.pia.core.property.basetype.primitive;

import com.pia.core.property.basetype.ShortType;

import java.lang.reflect.Field;

public class PrimitiveShortType extends ShortType {

    public PrimitiveShortType (Field ownField) {
        super(ownField);
    }

    public PrimitiveShortType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public void writeValueBackToObject (Object object) throws IllegalAccessException {
        ownField.setShort(object, getValue());
    }

    @Override
    public void setValue (Short value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot set null as value in a primitive datatype");
        }
        super.setValue(value);
    }
}
