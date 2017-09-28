package com.pia.core.properties;

import com.pia.core.properties.collectiontypes.ArrayType;
import com.pia.core.properties.collectiontypes.GenericCollectiontype;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * The base datatype for all arrays, including
 * primitives, primitive objects and complex arrays
 *
 * @param <T>
 */
public abstract class CollectionType<T extends DataType> extends DataType {

    /**
     * The array's elements are stored in this list.
     * A list was chosen since adding and removing
     * elements on the fly is easier with that.
     */
    protected ArrayList<T> children = new ArrayList<>();

    protected final DataType childDataType;
    protected final Class componentType;

    public CollectionType (Field ownField) throws IllegalAccessException {
        super(ownField);
        if (ownClass.isArray()) {
            componentType = ownClass.getComponentType();
        } else {
            componentType = getContentClass(ownField.getGenericType());
        }
        childDataType = DataType.getDataType(componentType);
    }

    public CollectionType (Class ownType) throws IllegalAccessException {
        super(ownType);
        if (ownType.isArray()) {
            componentType = ownType.getComponentType();
        } else {
            componentType = getContentClass(ownType);
        }
        childDataType = DataType.getDataType(componentType);
    }

    /**
     * Adds an element to the collection, maintaining it's order.
     *
     * @param element the element to add
     */
    public void add (T element) {
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
     * Checks if the given is an array.
     *
     * @param c class to be checked
     * @return true if type is an array
     */
    public static boolean isCollection (Class c) {
        boolean isArray = c.isArray();
        boolean isCollection = Collection.class.isAssignableFrom(c);
        return isArray || isCollection;
    }

    /**
     * Simmilar to getCollectionType in the DataType class, this method
     * decides which implementation of ArrayDataType should be
     * chosen, according to whether or not the array's contents
     * have a primitive, primitive object or complex type
     *
     * @param field {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IllegalArgumentException
     */
    public static @NotNull <D extends DataType>
    CollectionType getCollectionType (Field field) throws IllegalAccessException {
        assert isCollection(field.getType());
        if (field.getType().isArray()) {
            return new ArrayType<D>(field);
        }
        else {
            return new GenericCollectiontype<D>(field);
        }
    }

    /**
     * {@inheritDoc}
     */
    public static @NotNull <D extends DataType>
    CollectionType getCollectionType (Class fieldType) throws IllegalAccessException {
        assert isCollection(fieldType);
        if (fieldType.isArray()) {
            return new ArrayType<D>(fieldType);
        }
        else {
            return new GenericCollectiontype<D>(fieldType);
        }
    }

    @Override
    public String toString () {
        return Arrays.deepToString(children.toArray());
    }

    public static Class getContentClass (Type type) {
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
}
