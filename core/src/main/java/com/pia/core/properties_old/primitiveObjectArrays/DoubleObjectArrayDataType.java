package com.pia.core.properties_old.primitiveObjectArrays;

import com.pia.core.properties_old.primitiveObjects.DoubleObjectDataType;

import java.lang.reflect.Field;

public class DoubleObjectArrayDataType extends PrimitiveObjectArrayDataType<DoubleObjectDataType> {
    protected DoubleObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public double[] getOriginalArray () {
        DoubleObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        double[] originalArray = new double[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
