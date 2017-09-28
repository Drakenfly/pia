package com.pia.core.properties_old.primitiveArrays;

import com.pia.core.properties_old.primitives.StringDataType;

import java.lang.reflect.Field;

public class StringArrayDataType extends PrimitiveArrayDataType<StringDataType> {
    protected StringArrayDataType (Field ownField) {
        super(ownField);
    }

    public String[] getOriginalArray () {
        StringDataType[] dataTypeArray = getArray();
        assert dataTypeArray != null;
        String[] originalArray = new String[dataTypeArray.length];
        for (int i = 0; i < dataTypeArray.length; i++) {
            originalArray[i] = dataTypeArray[i].getValue();
        }
        return originalArray;
    }
}
