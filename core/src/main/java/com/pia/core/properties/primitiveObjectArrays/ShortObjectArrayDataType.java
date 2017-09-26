package com.pia.core.properties.primitiveObjectArrays;

import com.pia.core.properties.primitiveObjects.ShortObjectDataType;
import com.pia.core.properties.primitives.ShortDataType;

import java.lang.reflect.Field;

public class ShortObjectArrayDataType extends PrimitiveObjectArrayDataType<ShortObjectDataType> {
    protected ShortObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public short[] getOriginalArray() {
        ShortObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        short[] originalArray = new short[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
