package com.pia.core.properties;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Collection DataTypes handle the representation of all
 * implementations of Collections.
 * The collection can have undefined type variables, but
 * should have one whenever possible to be able to insert
 * actual elements.
 *
 * @param <E> the type of elements in this collection
 */
public class CollectionDataType<E extends DataType> extends DataType {
    /**
     * The collection's elements are stored in this list.
     */
    private List<E> children = new LinkedList<>();

    /**
     * @param ownField
     * @param contentDataType TODO: TBD if this field is needed
     */
    public CollectionDataType (Field ownField, DataType contentDataType) {
        super(ownField);
    }

    /**
     * Adds an element to the collection, maintaining it's order.
     *
     * @param element the element to add
     */
    public void add (E element) {
        if (!element.getClass().equals(getContentClass())) {
            throw new RuntimeException("The element of type " + element.getClass().getName() + " is not of type " + getContentClass().getName());
        }
        children.add(element);
    }

    /**
     * Removes an element at the given index of the collection.
     *
     * @param index index of the element to be removed
     */
    public void remove (int index) {
        children.remove(index);
    }

    public int size () {
        return children.size();
    }

    /**
     * Get the collection stored in the datatype
     *
     * @return A shallow copy of the private collection field
     */
    public Collection<E> getCollection () {
        return new LinkedList<>(children);
    }

    /**
     * Checks if a class implements the Collection interface
     *
     * @param c The class to be checked
     * @return true if c implements Collection, false otherwise
     */
    public static boolean isCollection (Class c) {
        return Collection.class.isAssignableFrom(c);
    }

    /**
     * Reads the type of object to be stored in the collection
     *
     * @return the type of object that should be stored in this collection
     */
    public Class getContentClass () {
        return getContentClass(ownField);
    }

    /**
     * Reads the type of object to be stored in the given field
     *
     * @return the type of object that should be stored in the given field's collection
     */
    public static Class getContentClass (Field field) {
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            ParameterizedType generic = (ParameterizedType) type;
            Class<?> genericClass = (Class<?>) generic.getActualTypeArguments()[0];
            return genericClass;
        }
        else {
            //TODO TBD if exception should be thrown
            return Object.class;
        }
    }

    @Override
    public String toString () {
        return Arrays.deepToString(getCollection().toArray());
    }
}
