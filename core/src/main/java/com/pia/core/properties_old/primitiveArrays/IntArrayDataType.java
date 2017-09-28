package com.pia.core.properties_old.primitiveArrays;

import com.pia.core.properties_old.primitives.IntDataType;

import java.lang.reflect.Field;

public class IntArrayDataType extends PrimitiveArrayDataType<IntDataType> {
    protected IntArrayDataType (Field ownField) {
        super(ownField);
    }

    public int[] getOriginalArray () {
        IntDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        int[] originalArray = new int[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
