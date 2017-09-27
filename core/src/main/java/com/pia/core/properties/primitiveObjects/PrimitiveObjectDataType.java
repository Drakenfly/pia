package com.pia.core.properties.primitiveObjects;

import com.pia.core.properties.DataType;
import com.pia.core.properties.primitives.StringDataType;
import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Field;

/**
 * PrimitiveObjectDataType objects (as the name suggests)
 * are objects that wrap primitive datatypes' objects in
 * DataType instances to allow for further modification.
 * They cannot be handled by ComplexDataType instances,
 * since they override standard operations, such as
 * the '+' sign
 *
 * Primitive object types are:
 * Boolean, Byte, Character, Double, Float, Integer, Long and Short
 */
public abstract class PrimitiveObjectDataType extends DataType {
    protected PrimitiveObjectDataType (Field ownField) {
        super(ownField);
    }

    /**
     * Simmilar to getDataType in the DataType class, this method
     * decides which implementation of PrimitiveDataType should be
     * chosen, according to the field's type.
     * @param field {@inheritDoc}
     * @return {@inheritDoc}
     * @throws InvalidArgumentException
     */
    public static @NotNull
    PrimitiveObjectDataType getDataType(Field field) throws InvalidArgumentException {
        assert isPrimitiveObject(field.getType());
        switch (field.getType().getName()) {
            case "java.lang.Boolean" : return new BooleanObjectDataType(field);
            case "java.lang.Byte" : return new ByteObjectDataType(field);
            case "java.lang.Character" : return new CharacterObjectDataType(field);
            case "java.lang.Double" : return new DoubleObjectDataType(field);
            case "java.lang.Float" : return new FloatObjectDataType(field);
            case "java.lang.Integer" : return new IntegerObjectDataType(field);
            case "java.lang.Long" : return new LongObjectDataType(field);
            case "java.lang.Short" : return new ShortObjectDataType(field);
            default: String[] arg = new String[1]; arg[0] = "Field is not primitive"; throw new InvalidArgumentException(arg);
        }
    }
    
    public static @NotNull
    PrimitiveObjectDataType getDataType(Class fieldClass) throws InvalidArgumentException {
        assert isPrimitiveObject(fieldClass);
        switch (fieldClass.getName()) {
            case "java.lang.Boolean" : return new BooleanObjectDataType(null);
            case "java.lang.Byte" : return new ByteObjectDataType(null);
            case "java.lang.Character" : return new CharacterObjectDataType(null);
            case "java.lang.Double" : return new DoubleObjectDataType(null);
            case "java.lang.Float" : return new FloatObjectDataType(null);
            case "java.lang.Integer" : return new IntegerObjectDataType(null);
            case "java.lang.Long" : return new LongObjectDataType(null);
            case "java.lang.Short" : return new ShortObjectDataType(null);
            default: String[] arg = new String[1]; arg[0] = "Field is not primitive"; throw new InvalidArgumentException(arg);
        }
    }

    /**
     * Checks if a class is a primitive object or not
     * @param type the class to be checked
     * @return true if type is a primitive object, false if not
     */
    public static boolean isPrimitiveObject(Class type) {
        switch (type.getName()) {
            case "java.lang.Boolean" :
            case "java.lang.Byte" :
            case "java.lang.Character" :
            case "java.lang.Double" :
            case "java.lang.Float" :
            case "java.lang.Integer" :
            case "java.lang.Long" :
            case "java.lang.Short" : return true;
            default: return false;
        }
    }
}
