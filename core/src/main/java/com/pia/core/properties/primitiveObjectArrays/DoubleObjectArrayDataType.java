package com.pia.core.properties.primitiveObjectArrays;

import com.pia.core.properties.primitiveObjects.DoubleObjectDataType;
import com.pia.core.properties.primitives.DoubleDataType;

import java.lang.reflect.Field;

public class DoubleObjectArrayDataType extends PrimitiveObjectArrayDataType<DoubleObjectDataType> {
    protected DoubleObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public double[] getOriginalArray() {
        DoubleObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        double[] originalArray = new double[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
