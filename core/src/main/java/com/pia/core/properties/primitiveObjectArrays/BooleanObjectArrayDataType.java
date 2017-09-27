package com.pia.core.properties.primitiveObjectArrays;

import com.pia.core.properties.primitiveObjects.BooleanObjectDataType;

import java.lang.reflect.Field;

public class BooleanObjectArrayDataType extends PrimitiveObjectArrayDataType<BooleanObjectDataType> {
    protected BooleanObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public Boolean[] getOriginalArray () {
        BooleanObjectDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        Boolean[] originalArray = new Boolean[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
