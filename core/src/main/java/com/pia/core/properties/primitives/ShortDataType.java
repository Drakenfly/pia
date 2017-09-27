package com.pia.core.properties.primitives;

import java.lang.reflect.Field;

public class ShortDataType extends PrimitiveDataType {
    private short value;

    @Override
    public String toString () {
        return value + "";
    }


    protected ShortDataType (Field ownField) {
        super(ownField);
    }

    public short getValue () {
        return value;
    }

    public void setValue (short value) {
        this.value = value;
    }
}
