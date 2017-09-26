package com.pia.core.properties.primitiveObjects;

import java.lang.reflect.Field;

public class ShortObjectDataType extends PrimitiveObjectDataType {
    private Short value;

    @Override
    public String toString () {
        return value + "";
    }


    protected ShortObjectDataType (Field ownField) {
        super(ownField);
    }

    public Short getValue () {
        return value;
    }

    public void setValue (Short value) {
        this.value = value;
    }
}
