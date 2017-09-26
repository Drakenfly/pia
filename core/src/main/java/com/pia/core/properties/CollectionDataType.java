package com.pia.core.properties;

import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CollectionDataType<T extends DataType> extends DataType {
    private ArrayList<T> children = new ArrayList<>();

    public CollectionDataType (Field ownField, DataType contentDataType) {
        super(ownField);
    }

    public void add(T element) {
        if (!element.getClass().equals(getContentClass())) {
            throw new RuntimeException("The element of type " + element.getClass().getName() + " is not of type " + getContentClass().getName());
        }
        children.add(element);
    }

    public void remove(int index) {
        children.remove(index);
    }

    /**
     * Get the collection stored in the datatype
     * @return A shallow copy of the private collection field
     */
    public Collection<T> getCollection() {
        return new ArrayList<>(children);
    }

    public static boolean isCollection(Class type) {
        return Collection.class.isAssignableFrom(type);
    }

    public Class getContentClass() {
        ParameterizedType generic = (ParameterizedType) ownField.getGenericType();
        Class<?> genericClass = (Class<?>) generic.getActualTypeArguments()[0];
        return genericClass;
    }

    public static Class getContentClass(Field field) {
        ParameterizedType generic = (ParameterizedType) field.getGenericType();
        Class<?> genericClass = (Class<?>) generic.getActualTypeArguments()[0];
        return genericClass;
    }

    @Override
    public String toString () {
        return Arrays.deepToString(getCollection().toArray());
    }
}
