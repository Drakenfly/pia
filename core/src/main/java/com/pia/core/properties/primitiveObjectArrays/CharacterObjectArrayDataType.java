package com.pia.core.properties.primitiveObjectArrays;

import com.pia.core.properties.primitiveObjects.CharacterObjectDataType;

import java.lang.reflect.Field;

public class CharacterObjectArrayDataType extends PrimitiveObjectArrayDataType<CharacterObjectDataType> {
    protected CharacterObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public char[] getOriginalArray () {
        CharacterObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        char[] originalArray = new char[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
