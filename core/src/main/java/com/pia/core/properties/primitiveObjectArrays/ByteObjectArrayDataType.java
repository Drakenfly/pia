package com.pia.core.properties.primitiveObjectArrays;

import com.pia.core.properties.primitiveObjects.ByteObjectDataType;

import java.lang.reflect.Field;

public class ByteObjectArrayDataType extends PrimitiveObjectArrayDataType<ByteObjectDataType> {
    protected ByteObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public byte[] getOriginalArray () {
        ByteObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        byte[] originalArray = new byte[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
