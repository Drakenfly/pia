package com.pia.core.properties.primitives;

import com.pia.core.properties.DataType;
import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public abstract class PrimitiveDataType extends DataType {
    protected PrimitiveDataType (Field ownField) {
        super(ownField);
    }

    public static @NotNull PrimitiveDataType getDataType(Field field) throws InvalidArgumentException {
        assert isPrimitive(field.getType());
        switch (field.getType().getName()) {
            case "boolean" : return new BooleanDataType(field);
            case "byte" : return new ByteDataType(field);
            case "char" : return new CharDataType(field);
            case "double" : return new DoubleDataType(field);
            case "float" : return new FloatDataType(field);
            case "int" : return new IntDataType(field);
            case "long" : return new LongDataType(field);
            case "short" : return new ShortDataType(field);
            case "java.lang.String" : return new StringDataType(field);
            default: String[] arg = new String[1]; arg[0] = "Field is not primitive"; throw new InvalidArgumentException(arg);
        }
    }
    
    public static @NotNull PrimitiveDataType getDataType(Class fieldClass) throws InvalidArgumentException {
        assert isPrimitive(fieldClass);
        switch (fieldClass.getName()) {
            case "boolean" : return new BooleanDataType(null);
            case "byte" : return new ByteDataType(null);
            case "char" : return new CharDataType(null);
            case "double" : return new DoubleDataType(null);
            case "float" : return new FloatDataType(null);
            case "int" : return new IntDataType(null);
            case "long" : return new LongDataType(null);
            case "short" : return new ShortDataType(null);
            case "java.lang.String" : return new StringDataType(null);
            default: String[] arg = new String[1]; arg[0] = "Field is not primitive"; throw new InvalidArgumentException(arg);
        }
    }

    public static boolean isPrimitive(Class type) {
        switch (type.getName()) {
            case "boolean" :
            case "byte" : 
            case "char" : 
            case "double" :
            case "float" :
            case "int" :
            case "long" :
            case "short" :
            case "java.lang.String" : return true;
            default: return false;
        }
    }
}
