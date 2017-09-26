package com.pia.core.properties;

import com.pia.core.properties.primitiveObjects.PrimitiveObjectDataType;
import com.pia.core.properties.primitives.PrimitiveDataType;
import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public abstract class DataType {
    public String printTypeAndVal() {
        return getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1) + " = " + toString();
    }
    public abstract String toString();
    protected final Field ownField;

    protected DataType (Field ownField) {
        this.ownField = ownField;
    }

    public static @NotNull
    DataType getDataType (Field field) throws InvalidArgumentException {
        if (PrimitiveDataType.isPrimitive(field.getType())) {
            return PrimitiveDataType.getDataType(field);
        }
        else if (PrimitiveObjectDataType.isPrimitiveObject(field.getType())) {
            return PrimitiveObjectDataType.getDataType(field);
        }
        else if (ArrayDataType.isArray(field.getType())) {
            return ArrayDataType.getDataType(field);
        }
        else if (CollectionDataType.isCollection(field.getType())) {
            return new CollectionDataType<>(field, DataType.getDataType(CollectionDataType.getContentClass(field)));
        }
        else {
            return new ComplexDataType<>(field);
        }
    }
    
    public static @NotNull
    DataType getDataType (Class fieldClass) throws InvalidArgumentException {
        if (PrimitiveDataType.isPrimitive(fieldClass)) {
            return PrimitiveDataType.getDataType(fieldClass);
        }
        else if (PrimitiveObjectDataType.isPrimitiveObject(fieldClass)) {
            return PrimitiveObjectDataType.getDataType(fieldClass);
        }
        else if (ArrayDataType.isArray(fieldClass)) {
            return ArrayDataType.getDataType(fieldClass);
        }
        else if (CollectionDataType.isCollection(fieldClass)) {
            return new CollectionDataType<>(null, DataType.getDataType(fieldClass));
        }
        else {
            return new ComplexDataType<>(null, fieldClass);
        }
    }
}
