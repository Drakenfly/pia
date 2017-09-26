package com.pia.core.properties.primitiveArrays;

import com.pia.core.properties.ArrayDataType;
import com.pia.core.properties.primitives.*;
import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Field;

public abstract class PrimitiveArrayDataType<T extends PrimitiveDataType> extends ArrayDataType<T> {

    protected PrimitiveArrayDataType (Field ownField) {
        super(ownField);
    }

    public static @NotNull
    PrimitiveArrayDataType getDataType(Field field) throws InvalidArgumentException {
        assert isArray(field.getType());
        assert PrimitiveDataType.isPrimitive(field.getType().getComponentType());
        
        switch (field.getType().getComponentType().toString()) {
            case "boolean" : return new BooleanArrayDataType(field);
            case "byte" : return new ByteArrayDataType(field);
            case "char" : return new CharArrayDataType(field);
            case "double" : return new DoubleArrayDataType(field);
            case "float" : return new FloatArrayDataType(field);
            case "int" : return new IntArrayDataType(field);
            case "long" : return new LongArrayDataType(field);
            case "short" : return new ShortArrayDataType(field);
            case "class java.lang.String" : return new StringArrayDataType(field);
            default: {
                String[] arg = new String[1];
                arg[0] = "Field is not primitive " + field.getType().getComponentType().toString();
                throw new InvalidArgumentException(arg);
            }
        }
    }
}
