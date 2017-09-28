package com.pia.core.properties;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class ComplexType extends DataType {
    protected final Type componentType;

    protected ComplexType (Field ownField) {
        super(ownField);
        componentType = ownField.getType();
    }

    protected ComplexType (Type ownType) {
        super(ownType.getClass());
        componentType = ownType;
    }

    @Override
    public String getContentType () {
        return "ComplexType";
    }

    @Override
    public Object getValue () {
        return null;
    }

    @Override
    public String toString () {
        return null;
    }
}
