package com.pia.core.properties;

import com.pia.core.properties.primitives.PrimitiveDataType;
import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Field;

public abstract class DataType {
    protected final Field ownField;

    protected DataType (Field ownField) {
        this.ownField = ownField;
    }

    public static @NotNull
    DataType getDataType (Field field) throws InvalidArgumentException {
        if (PrimitiveDataType.isPrimitive(field.getType())) {
            return PrimitiveDataType.getDataType(field);
        }
        else if (ArrayDataType.isArray(field.getType())) {
            return ArrayDataType.getDataType(field);
        }
        //TODO add one for lists or collections in general
        else {
            return new ComplexDataType<>(field);
        }
    }
}
