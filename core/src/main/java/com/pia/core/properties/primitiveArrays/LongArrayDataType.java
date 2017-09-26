package com.pia.core.properties.primitiveArrays;

import com.pia.core.properties.primitives.LongDataType;

import java.lang.reflect.Field;

public class LongArrayDataType extends PrimitiveArrayDataType<LongDataType> {
    protected LongArrayDataType (Field ownField) {
        super(ownField);
    }

    public long[] getOriginalArray() {
        LongDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        long[] originalArray = new long[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }

}
