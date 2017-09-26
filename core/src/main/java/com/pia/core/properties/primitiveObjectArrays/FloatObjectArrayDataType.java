package com.pia.core.properties.primitiveObjectArrays;

import com.pia.core.properties.primitiveObjects.FloatObjectDataType;
import com.pia.core.properties.primitives.FloatDataType;

import java.lang.reflect.Field;

public class FloatObjectArrayDataType extends PrimitiveObjectArrayDataType<FloatObjectDataType> {
    protected FloatObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public float[] getOriginalArray() {
        FloatObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        float[] originalArray = new float[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
