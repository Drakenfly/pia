package com.pia.core.properties.primitiveObjectArrays;

import com.pia.core.properties.primitiveObjects.LongObjectDataType;
import com.pia.core.properties.primitives.LongDataType;

import java.lang.reflect.Field;

public class LongObjectArrayDataType extends PrimitiveObjectArrayDataType<LongObjectDataType> {
    protected LongObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public long[] getOriginalArray() {
        LongObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        long[] originalArray = new long[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }

}
