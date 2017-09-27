package com.pia.core.properties.primitiveArrays;

import com.pia.core.properties.primitives.ShortDataType;

import java.lang.reflect.Field;

public class ShortArrayDataType extends PrimitiveArrayDataType<ShortDataType> {
    protected ShortArrayDataType (Field ownField) {
        super(ownField);
    }

    public short[] getOriginalArray () {
        ShortDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        short[] originalArray = new short[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
