package com.pia.core.properties_old.primitiveObjects;

import java.lang.reflect.Field;

public class LongObjectDataType extends PrimitiveObjectDataType {
    private Long value;

    @Override
    public String toString () {
        return value + "";
    }


    protected LongObjectDataType (Field ownField) {
        super(ownField);
    }

    public Long getValue () {
        return value;
    }

    public void setValue (Long value) {
        this.value = value;
    }
}
