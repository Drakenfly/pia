package com.pia.core.properties_old.primitiveObjects;

import java.lang.reflect.Field;

public class IntegerObjectDataType extends PrimitiveObjectDataType {
    private Integer value;

    @Override
    public String toString () {
        return value + "";
    }


    protected IntegerObjectDataType (Field ownField) {
        super(ownField);
    }

    public Integer getValue () {
        return value;
    }

    public void setValue (Integer value) {
        this.value = value;
    }
}
