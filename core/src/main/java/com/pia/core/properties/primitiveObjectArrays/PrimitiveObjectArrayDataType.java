package com.pia.core.properties.primitiveObjectArrays;

import com.pia.core.properties.ArrayDataType;
import com.pia.core.properties.primitiveObjects.PrimitiveObjectDataType;
import com.pia.core.properties.primitives.PrimitiveDataType;
import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Field;

public abstract class PrimitiveObjectArrayDataType<T extends PrimitiveObjectDataType> extends ArrayDataType<T> {

    protected PrimitiveObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    public static @NotNull
    PrimitiveObjectArrayDataType getDataType(Field field) throws InvalidArgumentException {
        assert isArray(field.getType());
        assert PrimitiveDataType.isPrimitive(field.getType().getComponentType());
        
        switch (field.getType().getComponentType().getName()) {
            case "java.lang.Boolean" : return new BooleanObjectArrayDataType(field);
            case "java.lang.Byte" : return new ByteObjectArrayDataType(field);
            case "java.lang.Character" : return new CharacterObjectArrayDataType(field);
            case "java.lang.Double" : return new DoubleObjectArrayDataType(field);
            case "java.lang.Float" : return new FloatObjectArrayDataType(field);
            case "java.lang.Integer" : return new IntegerObjectArrayDataType(field);
            case "java.lang.Long" : return new LongObjectArrayDataType(field);
            case "java.lang.Short" : return new ShortObjectArrayDataType(field);
            default: String[] arg = new String[1]; arg[0] = "Field is not primitive"; throw new InvalidArgumentException(arg);
        }
    }

    public static @NotNull
    PrimitiveObjectArrayDataType getDataType(Class fieldClass) throws InvalidArgumentException {
        assert isArray(fieldClass);
        assert PrimitiveDataType.isPrimitive(fieldClass.getComponentType());

        switch (fieldClass.getComponentType().getName()) {
            case "java.lang.Boolean" : return new BooleanObjectArrayDataType(null);
            case "java.lang.Byte" : return new ByteObjectArrayDataType(null);
            case "java.lang.Character" : return new CharacterObjectArrayDataType(null);
            case "java.lang.Double" : return new DoubleObjectArrayDataType(null);
            case "java.lang.Float" : return new FloatObjectArrayDataType(null);
            case "java.lang.Integer" : return new IntegerObjectArrayDataType(null);
            case "java.lang.Long" : return new LongObjectArrayDataType(null);
            case "java.lang.Short" : return new ShortObjectArrayDataType(null);
            default: {
                String[] arg = new String[1];
                arg[0] = "Field is not primitive " + fieldClass.getComponentType().toString();
                throw new InvalidArgumentException(arg);
            }
        }
    }
}
