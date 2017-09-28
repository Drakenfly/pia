package com.pia.core.properties.basetypes;

import com.pia.core.properties.BaseType;

import java.lang.reflect.Field;

public class FloatType extends BaseType<Float> {
    private Float value;

    public FloatType (Field ownField) {
        super(ownField);
    }

    @Override
    protected void setDefaultValue () {
        value = 0f;
    }

    public FloatType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public String getContentType () {
        return "Float";
    }

    @Override
    public String toString () {
        return value.toString();
    }

    @Override
    public Float getValue () {
        return value;
    }

    @Override
    public void setValue (Float value) {
        this.value = value;
    }

    @Override
    public void parseValue (String value) {
        try {
        this.value = Float.parseFloat(value);
    }
    catch (NumberFormatException ex) {
        value = value.replace(",", ".");
        this.value = Float.parseFloat(value);
    }
    }
}
