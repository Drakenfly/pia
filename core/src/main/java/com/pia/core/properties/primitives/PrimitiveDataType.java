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
        switch (field.getType().toString()) {
            case "boolean" : return new BooleanDataType(field);
            case "byte" : return new ByteDataType(field);
            case "char" : return new CharDataType(field);
            case "double" : return new DoubleDataType(field);
            case "float" : return new FloatDataType(field);
            case "int" : return new IntDataType(field);
            case "long" : return new LongDataType(field);
            case "short" : return new ShortDataType(field);
            case "class java.lang.String" : return new StringDataType(field);
            default: String[] arg = new String[1]; arg[0] = "Field is not primitive"; throw new InvalidArgumentException(arg);
        }
    }
    
    public static boolean isPrimitive(Class type) {
        System.out.println(type.toString());
        System.out.println(type + "");
        switch (type.toString()) {
            case "boolean" :
            case "byte" : 
            case "char" : 
            case "double" :
            case "float" :
            case "int" :
            case "long" :
            case "short" :
            case "class java.lang.String" : return true;
            default: return false;
        }
    }
}
