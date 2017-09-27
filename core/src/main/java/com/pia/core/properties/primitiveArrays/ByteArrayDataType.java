package com.pia.core.properties.primitiveArrays;

import com.pia.core.properties.primitives.ByteDataType;

import java.lang.reflect.Field;

public class ByteArrayDataType extends PrimitiveArrayDataType<ByteDataType> {
    protected ByteArrayDataType (Field ownField) {
        super(ownField);
    }

    public byte[] getOriginalArray () {
        ByteDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        byte[] originalArray = new byte[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
