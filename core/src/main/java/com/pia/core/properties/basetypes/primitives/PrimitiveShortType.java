package com.pia.core.properties.basetypes.primitives;

import com.pia.core.properties.basetypes.IntegerType;
import com.pia.core.properties.basetypes.ShortType;

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
}
