package com.pia.core.properties;

import com.pia.core.properties_old.ArrayDataType;
import com.pia.core.properties_old.CollectionDataType;
import com.pia.core.properties_old.ComplexDataType;
import com.pia.core.properties_old.primitiveObjects.PrimitiveObjectDataType;
import com.pia.core.properties_old.primitives.PrimitiveDataType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * A DataType object is used to handle dataflow
 * from and to a class' field.
 * It is important to use appropriate implementations
 * for any kind of field, since variable access to
 * unknown objects at runtime must be safe and should
 * guarantee some consistency. Also using this DataType
 * structure helps decorating different fields with
 * display techniques, depending on the chosen frontend.
 */
public abstract class DataType {
    /**
     * This field stores a reference to the corresponding
     * field that the DataType originated from.
     * This variable also always stores additional
     * information, for example about the generic type
     * of a field or it's annotations.
     */
    protected final Field ownField;

    /**
     * Mainly used when ownField is null.
     * Is needed when the data does not sit on a field,
     * but in a collection.
     */
    protected final Class ownClass;

    /**
     * Standard constructor storing the own field as a reference.
     *
     * @param ownField Stores a reference to the corresponding
     *                 field that the DataType originated from.
     */
    public DataType (Field ownField) {
        this.ownField = ownField;
        this.ownClass = ownField.getType();
    }

    public DataType (Class ownClass) {
        this.ownField = null;
        this.ownClass = ownClass;
    }

    public abstract String getContentType ();

    public void writeValueBackToObject (Object object) throws IllegalAccessException {
        ownField.set(object, getValue());
    }

    public abstract Object getValue();

    /**
     * By parsing the field's class and type information the
     * correct DataType implementation is chosen and instantiated.
     * This method should be called almost every time when
     * a DataType object is needed externally.
     */
    public static @NotNull
    DataType getDataType (Field field) throws IllegalAccessException {
        /*
         * Basetypes are primitives, their primitive object wrappers and Strings
         */
        if (BaseType.isBaseType(field.getType())) {
            return BaseType.getBaseType(field);
        }
        /*
         * Collections are arrays, primitive arrays and generic collections
         */
        else if (CollectionType.isCollection(field.getType())) {
            return CollectionType.getCollectionType(field);
        }
        else if (false) {
            //TODO for Map<K,V>
            throw new NotImplementedException();
        }
        else {
            return new ComplexType(field);
        }
    }

    /**
     * This method returns the same DataType implementation as
     * getCollectionType(Field) for the field's class.
     * It should almost never be called from outside, but rather
     * is used for complex datatypes that have nested types
     * within, but no corresponding class' field.
     *
     * @param fieldType
     * @return
     * @throws IllegalArgumentException
     */
    public static @NotNull
    DataType getDataType (Class fieldType) throws IllegalAccessException {
        /*
         * Basetypes are primitives, their primitive object wrappers and Strings
         */
        if (BaseType.isBaseType(fieldType)) {
            return BaseType.getBaseType(fieldType);
        }
        /*
         * Collections are arrays, primitive arrays and generic collections
         */
        else if (CollectionType.isCollection(fieldType)) {
            return CollectionType.getCollectionType(fieldType);
        }
        else if (false) {
            //TODO for Map<K,V>
            throw new NotImplementedException();
        }
        else {
            return new ComplexType(fieldType);
        }
    }

    public abstract String toString ();
}
