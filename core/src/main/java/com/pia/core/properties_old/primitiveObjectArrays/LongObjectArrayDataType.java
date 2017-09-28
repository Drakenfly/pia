package com.pia.core.properties_old.primitiveObjectArrays;

import com.pia.core.properties_old.primitiveObjects.LongObjectDataType;

import java.lang.reflect.Field;

public class LongObjectArrayDataType extends PrimitiveObjectArrayDataType<LongObjectDataType> {
    protected LongObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public long[] getOriginalArray () {
        LongObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        long[] originalArray = new long[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }

}
