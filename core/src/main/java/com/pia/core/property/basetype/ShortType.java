package com.pia.core.property.basetype;

import com.pia.core.property.BaseType;

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
        return value == null ? "null" : value.toString();
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
