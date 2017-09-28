package com.pia.core.properties_old.primitiveObjects;

import java.lang.reflect.Field;

public class ByteObjectDataType extends PrimitiveObjectDataType {
    private Byte value;

    @Override
    public String toString () {
        return value + "";
    }


    protected ByteObjectDataType (Field ownField) {
        super(ownField);
    }

    public Byte getValue () {
        return value;
    }

    public void setValue (Byte value) {
        this.value = value;
    }
}
