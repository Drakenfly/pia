package com.pia.core.properties_old.primitiveArrays;

import com.pia.core.properties_old.primitives.BooleanDataType;

import java.lang.reflect.Field;

public class BooleanArrayDataType extends PrimitiveArrayDataType<BooleanDataType> {
    protected BooleanArrayDataType (Field ownField) {
        super(ownField);
    }

    public boolean[] getOriginalArray () {
        BooleanDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        boolean[] originalArray = new boolean[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
