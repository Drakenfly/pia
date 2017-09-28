package com.pia.core.properties_old.primitiveObjectArrays;

import com.pia.core.properties_old.primitiveObjects.ShortObjectDataType;

import java.lang.reflect.Field;

public class ShortObjectArrayDataType extends PrimitiveObjectArrayDataType<ShortObjectDataType> {
    protected ShortObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public short[] getOriginalArray () {
        ShortObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        short[] originalArray = new short[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
