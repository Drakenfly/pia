package com.pia.core.properties.primitives;

import java.lang.reflect.Field;

public class CharDataType extends PrimitiveDataType{
    private char value;

    @Override
    public String toString () {
        return value + "";
    }


    protected CharDataType (Field ownField) {
        super(ownField);
    }

    public char getValue () {
        return value;
    }

    public void setValue (char value) {
        this.value = value;
    }
}
