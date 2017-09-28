package com.pia.core.properties_old.primitiveArrays;

import com.pia.core.properties_old.primitives.DoubleDataType;

import java.lang.reflect.Field;

public class DoubleArrayDataType extends PrimitiveArrayDataType<DoubleDataType> {
    protected DoubleArrayDataType (Field ownField) {
        super(ownField);
    }

    public double[] getOriginalArray () {
        DoubleDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        double[] originalArray = new double[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
