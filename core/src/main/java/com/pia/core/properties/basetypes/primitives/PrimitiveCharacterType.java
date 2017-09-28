package com.pia.core.properties.basetypes.primitives;

import com.pia.core.properties.basetypes.CharacterType;

import java.lang.reflect.Field;

public class PrimitiveCharacterType extends CharacterType {

    public PrimitiveCharacterType (Field ownField) {
        super(ownField);
    }

    public PrimitiveCharacterType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public void writeValueBackToObject (Object object) throws IllegalAccessException {
        ownField.setChar(object, getValue());
    }
}
