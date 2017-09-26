package com.pia.core.properties;

import com.pia.core.properties.primitiveArrays.PrimitiveArrayDataType;
import com.pia.core.properties.primitiveObjectArrays.PrimitiveObjectArrayDataType;
import com.pia.core.properties.primitiveObjects.PrimitiveObjectDataType;
import com.pia.core.properties.primitives.*;
import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ArrayDataType<T extends DataType> extends DataType {

    @Override
    public String toString () {
        return Arrays.deepToString(children.toArray());
    }

    private ArrayList<T> children = new ArrayList<>();

    protected ArrayDataType (Field ownField) {
        super(ownField);
    }

    public void add(T element) {
        children.add(element);
    }

    public void remove(int index) {
        children.remove(index);
    }

    public T[] getArray() {
        if (children.size() > 0) {
            T[] toR = (T[]) java.lang.reflect.Array.newInstance(children.get(0)
                    .getClass(), children.size());
            return children.toArray(toR);
        } else {
            return null;
        }
    }

    public static boolean isArray (Class type) {
        return type.isArray();
    }

    public static @NotNull
    ArrayDataType getDataType(Field field) throws InvalidArgumentException {
        assert isArray(field.getType());
        if (PrimitiveDataType.isPrimitive(field.getType().getComponentType())) {
            return PrimitiveArrayDataType.getDataType(field);
        }
        else if (PrimitiveObjectDataType.isPrimitiveObject(field.getType().getComponentType())) {
            return PrimitiveObjectArrayDataType.getDataType(field);
        }
        else {
            return new ComplexArrayDataType(field, DataType.getDataType(field.getType().getComponentType()));
        }
    }

    public static @NotNull
    ArrayDataType getDataType(Class fieldClass) throws InvalidArgumentException {
        assert isArray(fieldClass);
        if (PrimitiveDataType.isPrimitive(fieldClass.getComponentType())) {
            return PrimitiveArrayDataType.getDataType(fieldClass);
        }
        else if (PrimitiveObjectDataType.isPrimitiveObject(fieldClass.getComponentType())) {
            return PrimitiveObjectArrayDataType.getDataType(fieldClass);
        }
        else {
            return new ComplexArrayDataType(null, DataType.getDataType(fieldClass.getComponentType()));
        }
    }
}
