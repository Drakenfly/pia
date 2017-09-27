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

/**
 * The base datatype for all arrays, including
 * primitives, primitive objects and complex arrays
 * @param <T>
 */
public abstract class ArrayDataType<T extends DataType> extends DataType {

    /**
     * The array's elements are stored in this list.
     * A list was chosen since adding and removing
     * elements on the fly is easier with that.
     */
    private ArrayList<T> children = new ArrayList<>();

    protected ArrayDataType (Field ownField) {
        super(ownField);
    }

    /**
     * Adds an element to the collection, maintaining it's order.
     * @param element the element to add
     */
    public void add(T element) {
        children.add(element);
    }

    /**
     * Removes an element at the given index of the collection.
     * @param index index of the element to be removed
     */
    public void remove(int index) {
        children.remove(index);
    }

    public int size() {
        return children.size();
    }

    /**
     * Get the information stored in the datatype as an array.
     * @return A shallow copy of the private collection field
     */
    public T[] getArray() {
        if (children.size() > 0) {
            T[] toR = (T[]) java.lang.reflect.Array.newInstance(children.get(0)
                    .getClass(), children.size());
            return children.toArray(toR);
        } else {
            return null;
        }
    }

    /**
     * Checks if the given is an array.
     * @param type class to be checked
     * @return true if type is an array
     */
    public static boolean isArray (Class type) {
        return type.isArray();
    }

    /**
     * Simmilar to getDataType in the DataType class, this method
     * decides which implementation of ArrayDataType should be
     * chosen, according to whether or not the array's contents
     * have a primitive, primitive object or complex type
     *
     * @param field {@inheritDoc}
     * @return {@inheritDoc}
     * @throws InvalidArgumentException
     */
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

    /**
     * {@inheritDoc}
     */
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

    @Override
    public String toString () {
        return Arrays.deepToString(children.toArray());
    }
}
