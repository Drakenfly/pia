package com.pia.core.property;

import com.pia.core.property.collectiontype.ArrayType;
import com.pia.core.property.collectiontype.GenericCollectionType;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The base datatype for all arrays, including
 * primitives, primitive objects and complex arrays
 *
 * @param <T>
 */
public abstract class CollectionType<T extends DataType> extends NullableTypeImpl {

    /**
     * The array's elements are stored in this list.
     * A list was chosen since adding and removing
     * elements on the fly is easier with that.
     */
    protected ArrayList<T> children = new ArrayList<>();

    protected final DataType childDataType;
    protected final Class<?> componentClass;
    protected final Type componentType;

    public CollectionType (Field ownField) throws IllegalAccessException {
        super(ownField);
        if (ownClass.isArray()) {
            componentType = ownClass.getComponentType();
            componentClass = ownClass.getComponentType();
        }
        else {
            Type generic = ownField.getGenericType();
            componentType = getContentClass(generic);
            if (componentType instanceof Class) {
                componentClass = (Class<?>) componentType;
            }
            else {
                componentClass = getClassFromParametrized((ParameterizedType) componentType);
            }
        }
        childDataType = DataType.getDataType(componentType, componentClass);
    }

    public CollectionType (Class<?> ownclass, Type ownType) throws IllegalAccessException {
        super(ownclass);
        if (ownclass.isArray()) {
            componentType = ownclass.getComponentType();
            componentClass = ownClass.getComponentType();
        }
        else {
            if (ownType instanceof ParameterizedType) {
                componentType = ((ParameterizedType) ownType).getActualTypeArguments()[0];
                if (componentType instanceof ParameterizedType) {
                    componentClass = (Class<?>) ((ParameterizedType)componentType).getRawType();
                } else {
                    componentClass = (Class<?>) componentType;
                }
            } else {
                if(ownclass.getComponentType() == null) {
                    //we have a generic collection with no specified type.
                    //TODO decide what to do here - for now "Object" is assumed
                    componentType = Object.class;
                    componentClass = Object.class;
                }
                else {
                    componentType = ownclass.getComponentType();
                    componentClass = ownClass.getComponentType();
                }
            }
        }
        childDataType = DataType.getDataType(componentType, componentClass);
    }

    /**
     * Adds an element to the collection, maintaining it's order.
     *
     * @param element the element to add
     */
    public void add (T element) {
        children.add(element);
    }

    public List<T> getChildren () {
        return children;
    }

    /**
     * Removes an element at the given index of the collection.
     *
     * @param index index of the element to be removed
     */
    public void remove (int index) {
        children.remove(index);
    }

    public void remove (DataType element) {
        children.remove(element);
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
            return new GenericCollectionType<D>(field);
        }
    }

    /**
     * {@inheritDoc}
     */
    public static @NotNull <D extends DataType>
    CollectionType getCollectionType (Class<?> fieldClass, Type fieldType) throws IllegalAccessException {
        assert isCollection(fieldClass);
        if (fieldClass.isArray()) {
            return new ArrayType<D>(fieldClass, fieldType);
        }
        else {
            return new GenericCollectionType<D>(fieldClass, fieldType);
        }
    }

    public DataType getChildDataType () throws IllegalAccessException {
        //It is important to use a new instance and not the stored variable
        return DataType.getDataType(componentType, componentClass);
    }

    @Override
    public String toString () {
        return Arrays.deepToString(children.toArray());
    }

    public static Type getContentClass (Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType generic = (ParameterizedType) type;
            return generic.getActualTypeArguments()[0];
        }
        else {
            //TODO TBD if exception should be thrown
            return Object.class;
        }
    }

    public static Class getClassFromParametrized (ParameterizedType generic) {
        return (Class<?>) generic.getRawType();
    }
}
