package com.pia.core.properties_old.primitiveArrays;

import com.pia.core.properties_old.primitives.CharDataType;

import java.lang.reflect.Field;

public class CharArrayDataType extends PrimitiveArrayDataType<CharDataType> {
    protected CharArrayDataType (Field ownField) {
        super(ownField);
    }

    public char[] getOriginalArray () {
        CharDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        char[] originalArray = new char[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
