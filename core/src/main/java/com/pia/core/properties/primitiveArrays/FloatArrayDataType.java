package com.pia.core.properties.primitiveArrays;

import com.pia.core.properties.primitives.FloatDataType;

import java.lang.reflect.Field;

public class FloatArrayDataType extends PrimitiveArrayDataType<FloatDataType> {
    protected FloatArrayDataType (Field ownField) {
        super(ownField);
    }

    public float[] getOriginalArray () {
        FloatDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        float[] originalArray = new float[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
