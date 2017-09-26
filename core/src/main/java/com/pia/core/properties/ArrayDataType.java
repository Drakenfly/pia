package com.pia.core.properties;

import com.pia.core.properties.primitiveArrays.PrimitiveArrayDataType;
import com.pia.core.properties.primitives.*;
import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class ArrayDataType<T extends DataType> extends DataType {
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
        //TODO else if is Object of primitive, such as 'Integer' or 'Boolean'
        else {
            return new ComplexArrayDataType(field);
        }
    }
}
