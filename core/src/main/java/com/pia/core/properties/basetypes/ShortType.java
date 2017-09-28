package com.pia.core.properties.basetypes;

import com.pia.core.properties.BaseType;

import java.lang.reflect.Field;

public class ShortType extends BaseType<Short> {
    private Short value;

    public ShortType (Field ownField) {
        super(ownField);
    }

    @Override
    protected void setDefaultValue () {
        value = 0;
    }

    public ShortType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public String getContentType () {
        return "Short";
    }

    @Override
    public String toString () {
        return value.toString();
    }

    @Override
    public Short getValue () {
        return value;
    }

    @Override
    public void setValue (Short value) {
        this.value = value;
    }

    @Override
    public void parseValue (String value) {
        this.value = Short.parseShort(value);
    }
}
