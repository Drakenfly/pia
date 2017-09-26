package com.pia.core.properties.primitives;

import java.lang.reflect.Field;

public class ByteDataType extends PrimitiveDataType{
    private byte value;

    protected ByteDataType (Field ownField) {
        super(ownField);
    }

    public byte getValue () {
        return value;
    }

    public void setValue (byte value) {
        this.value = value;
    }
}
