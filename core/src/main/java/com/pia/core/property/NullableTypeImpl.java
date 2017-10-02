package com.pia.core.property;

import com.pia.core.annotation.Property;
import com.pia.core.internal.FieldHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class NullableTypeImpl extends DataType implements NullableType {
    private boolean valueIsNull;
    private final boolean required;

    public NullableTypeImpl (Field ownField) {
        super(ownField);
        Property property = FieldHelper.getProperty(ownField);
        if (property != null && property.required()) {
            required = property.required();
        }
        else {
            required = false;
        }
    }

    public NullableTypeImpl (Class<?> ownClass) {
        super(ownClass);
        required = false;
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
        if (isRequired() && valueIsNull) {
            throw new IllegalArgumentException("Cannot set a field to null, which is required.");
        }
        this.valueIsNull = valueIsNull;
    }

    public boolean isRequired () {
        return required;
    }
}
