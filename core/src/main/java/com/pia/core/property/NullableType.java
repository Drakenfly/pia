package com.pia.core.property;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class NullableType extends DataType {
    private boolean valueIsNull;

    public NullableType (Field ownField) {
        super(ownField);
    }

    public NullableType (Class<?> ownClass) {
        super(ownClass);
    }

    @Override
    protected void writeFieldToObject (Object object) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        boolean originalAccessibility = ownField.isAccessible();
        ownField.setAccessible(true);
        ownField.set(object, getValueIsNull() ? null : getValue());
        ownField.setAccessible(originalAccessibility);
    }

    public boolean getValueIsNull () {
        return valueIsNull;
    }

    public void setValueIsNull (boolean valueIsNull) {
        this.valueIsNull = valueIsNull;
    }
}
