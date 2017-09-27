package com.pia.core.properties.primitiveObjectArrays;

import com.pia.core.properties.primitiveObjects.IntegerObjectDataType;

import java.lang.reflect.Field;

public class IntegerObjectArrayDataType extends PrimitiveObjectArrayDataType<IntegerObjectDataType> {
    protected IntegerObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public int[] getOriginalArray () {
        IntegerObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        int[] originalArray = new int[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
