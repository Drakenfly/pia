package com.pia.core.properties_old.primitives;


import com.pia.core.properties_old.DataType;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

/**
 * PrimitiveDataType objects (as the name suggests)
 * are objects that wrap primitive datatypes (+ String) in
 * DataType instances to allow for further modification.
 * Usually these are the types that every object should
 * boil down to, representing the final hurdle before
 * actual data.
 * <p>
 * Primitive types are:
 * boolean, byte, char, double, float, int, long and short
 * (+ String)
 */
public abstract class PrimitiveDataType extends DataType {

    public PrimitiveDataType (Field ownField) {
        super(ownField);
    }

    /**
     * Simmilar to getCollectionType in the DataType class, this method
     * decides which implementation of PrimitiveDataType should be
     * chosen, according to the field's primitive type.
     *
     * @param field {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IllegalArgumentException
     */
    public static @NotNull PrimitiveDataType getDataType (Field field) {
        assert isPrimitive(field.getType());
        switch (field.getType().getName()) {
            case "boolean":
                return new BooleanDataType(field);
            case "byte":
                return new ByteDataType(field);
            case "char":
                return new CharDataType(field);
            case "double":
                return new DoubleDataType(field);
            case "float":
                return new FloatDataType(field);
            case "int":
                return new IntDataType(field);
            case "long":
                return new LongDataType(field);
            case "short":
                return new ShortDataType(field);
            case "java.lang.String":
                return new StringDataType(field);
            default:
                throw new IllegalArgumentException("Field is not primitive");
        }
    }

    /**
     * {@inheritDoc}
     */
    public static @NotNull PrimitiveDataType getDataType (Class fieldClass) {
        assert isPrimitive(fieldClass);
        switch (fieldClass.getName()) {
            case "boolean":
                return new BooleanDataType(null);
            case "byte":
                return new ByteDataType(null);
            case "char":
                return new CharDataType(null);
            case "double":
                return new DoubleDataType(null);
            case "float":
                return new FloatDataType(null);
            case "int":
                return new IntDataType(null);
            case "long":
                return new LongDataType(null);
            case "short":
                return new ShortDataType(null);
            case "java.lang.String":
                return new StringDataType(null);
            default:
                throw new IllegalArgumentException("Field is not primitive");
        }
    }

    /**
     * Checks if a class is a primitive (or a String) or not
     *
     * @param type the class to be checked
     * @return true if type is primitive, false if not
     */
    public static boolean isPrimitive (Class type) {
        switch (type.getName()) {
            case "boolean":
            case "byte":
            case "char":
            case "double":
            case "float":
            case "int":
            case "long":
            case "short":
            case "java.lang.String":
                return true;
            default:
                return false;
        }
    }
}
