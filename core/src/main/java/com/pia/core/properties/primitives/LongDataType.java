package com.pia.core.properties.primitives;

import java.lang.reflect.Field;

public class LongDataType extends PrimitiveDataType{
    private long value;

    protected LongDataType (Field ownField) {
        super(ownField);
    }

    public long getValue () {
        return value;
    }

    public void setValue (long value) {
        this.value = value;
    }
}
