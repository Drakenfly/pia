package com.pia.core.properties;


import com.pia.core.properties.basetypes.*;
import com.pia.core.properties.basetypes.primitives.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public abstract class BaseType<T> extends DataType {

    public BaseType (Field ownField) {
        super(ownField);
        setDefaultValue();
    }

    protected abstract void setDefaultValue ();

    public BaseType (Class ownClass) {
        super(ownClass);
        setDefaultValue();
    }

    @Override
    public abstract T getValue ();

    public abstract void setValue (T value);

    public abstract void parseValue (String value);

    static boolean isBaseType (Class<?> c) {
        return isPrimitive(c) || isPrimitiveObject(c);
    }

    static BaseType getBaseType (Field f) throws IllegalAccessException {
        assert isBaseType(f.getType());

        if (isPrimitive(f.getType())) {
            switch (f.getType().getName()) {
                case "boolean":
                    return new PrimitiveBooleanType(f);
                case "byte":
                    return new PrimitiveByteType(f);
                case "char":
                    return new PrimitiveCharacterType(f);
                case "double":
                    return new PrimitiveDoubleType(f);
                case "float":
                    return new PrimitiveFloatType(f);
                case "int":
                    return new PrimitiveIntegerType(f);
                case "long":
                    return new PrimitiveLongType(f);
                case "short":
                    return new PrimitiveShortType(f);
                default:
                    throw new IllegalAccessException("Class of field f (" + f.getClass().getName()
                            + ") is a primitive, but could not be assigned properly.");
            }
        }
        else if (isPrimitiveObject(f.getType())) {
            switch (f.getType().getName()) {
                case "java.lang.Boolean":
                    return new BooleanType(f);
                case "java.lang.Byte":
                    return new ByteType(f);
                case "java.lang.Character":
                    return new CharacterType(f);
                case "java.lang.Double":
                    return new DoubleType(f);
                case "java.lang.Float":
                    return new FloatType(f);
                case "java.lang.Integer":
                    return new IntegerType(f);
                case "java.lang.Long":
                    return new LongType(f);
                case "java.lang.Short":
                    return new ShortType(f);
                case "java.lang.String":
                    return new StringType(f);
                default:
                    throw new IllegalAccessException("Class of field f (" + f.getClass().getName()
                            + ") is a primitive object, but could not be assigned properly.");
            }
        }

        throw new IllegalArgumentException("Class of field of type " + f.getType().getName() + " could not be assigned a BaseType instance.");
    }

    static BaseType getBaseType (Class<?> fieldClass) throws IllegalAccessException {
        assert isBaseType(fieldClass);

        if (isPrimitive(fieldClass)) {
            switch (fieldClass.getName()) {
                case "boolean":
                    return new PrimitiveBooleanType(fieldClass);
                case "byte":
                    return new PrimitiveByteType(fieldClass);
                case "char":
                    return new PrimitiveCharacterType(fieldClass);
                case "double":
                    return new PrimitiveDoubleType(fieldClass);
                case "float":
                    return new PrimitiveFloatType(fieldClass);
                case "int":
                    return new PrimitiveIntegerType(fieldClass);
                case "long":
                    return new PrimitiveLongType(fieldClass);
                case "short":
                    return new PrimitiveShortType(fieldClass);
                default:
                    throw new IllegalAccessException("Class of field f (" + fieldClass.getName()
                            + ") is a primitive, but could not be assigned properly.");
            }
        }
        else if (isPrimitiveObject(fieldClass)) {
            switch (fieldClass.getName()) {
                case "java.lang.Boolean":
                    return new BooleanType(fieldClass);
                case "java.lang.Byte":
                    return new ByteType(fieldClass);
                case "java.lang.Character":
                    return new CharacterType(fieldClass);
                case "java.lang.Double":
                    return new DoubleType(fieldClass);
                case "java.lang.Float":
                    return new FloatType(fieldClass);
                case "java.lang.Integer":
                    return new IntegerType(fieldClass);
                case "java.lang.Long":
                    return new LongType(fieldClass);
                case "java.lang.Short":
                    return new ShortType(fieldClass);
                case "java.lang.String":
                    return new StringType(fieldClass);
                default:
                    throw new IllegalAccessException("Class of field f (" + fieldClass.getName()
                            + ") is a primitive object, but could not be assigned properly.");
            }
        }

        throw new IllegalArgumentException("Class of field of type " + fieldClass.getName() + " could not be assigned a BaseType instance.");
    }

    /**
     * Checks if a class is a primitive object or not
     *
     * @param type the class to be checked
     * @return true if type is a primitive object, false if not
     */
    private static boolean isPrimitiveObject (Type type) {
        switch (type.getTypeName()) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Character":
            case "java.lang.Double":
            case "java.lang.Float":
            case "java.lang.Integer":
            case "java.lang.Long":
            case "java.lang.Short":
            case "java.lang.String":
                return true;
            default:
                return false;
        }
    }

    private static boolean isPrimitive (Class<?> type) {
        return type.isPrimitive();
    }
}
