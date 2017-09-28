package com.pia.core.properties_old.primitiveObjects;

import java.lang.reflect.Field;

public class CharacterObjectDataType extends PrimitiveObjectDataType {
    private Character value;

    @Override
    public String toString () {
        return value + "";
    }


    protected CharacterObjectDataType (Field ownField) {
        super(ownField);
    }

    public Character getValue () {
        return value;
    }

    public void setValue (Character value) {
        this.value = value;
    }
}
