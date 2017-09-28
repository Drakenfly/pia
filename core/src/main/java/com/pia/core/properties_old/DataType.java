package com.pia.core.properties_old;

import com.pia.core.properties_old.primitiveObjects.PrimitiveObjectDataType;
import com.pia.core.properties_old.primitives.PrimitiveDataType;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

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
@Deprecated
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
     * Standard constructor storing the own field as a reference.
     *
     * @param ownField Stores a reference to the corresponding
     *                 field that the DataType originated from.
     */
    protected DataType (Field ownField) {
        this.ownField = ownField;
    }

    /**
     * By parsing the field's class and type information the
     * correct DataType implementation is chosen and instantiated.
     * This method should be called almost every time when
     * a DataType object is needed externally.
     */
    public static @NotNull
    DataType getDataType (Field field) {
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

    /**
     * This method returns the same DataType implementation as
     * getCollectionType(Field) for the field's class.
     * It should almost never be called from outside, but rather
     * is used for complex datatypes that have nested types
     * within, but no corresponding class' field.
     *
     * @param fieldClass
     * @return
     * @throws IllegalArgumentException
     */
    public static @NotNull
    DataType getDataType (Class fieldClass) {
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

    /**
     * Results in a string with information about the
     * class and the content, provided by the subclass'
     * implementation of toString(). Mainly used for
     * development purposes, since a real frontend should
     * be used to display data.
     *
     * @return A string consisting of class name and toString value
     */
    public String printTypeAndVal () {
        return getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1) + " = " + toString();
    }

    public abstract String toString ();
}
