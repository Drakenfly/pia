package com.pia.core.properties_old.primitiveObjects;

import java.lang.reflect.Field;

public class BooleanObjectDataType extends PrimitiveObjectDataType {
    private Boolean value;

    @Override
    public String toString () {
        return value + "";
    }


    protected BooleanObjectDataType (Field ownField) {
        super(ownField);
    }

    public Boolean getValue () {
        return value;
    }

    public void setValue (Boolean value) {
        this.value = value;
    }
}
