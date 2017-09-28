package com.pia.core.properties_old.primitiveArrays;

import com.pia.core.properties_old.ArrayDataType;
import com.pia.core.properties_old.primitives.PrimitiveDataType;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

/**
 * This class (as the name suggests) is a wrapper for primitive arrays.
 * To learn more about Arrays @see com.pia.core.ArrayDataType
 * To learn more about primitives @see com.pia.core.primitives.PrimitiveDataType
 *
 * @param <T>
 */
public abstract class PrimitiveArrayDataType<T extends PrimitiveDataType> extends ArrayDataType<T> {

    protected PrimitiveArrayDataType (Field ownField) {
        super(ownField);
    }

    /**
     * {@inheritDoc}
     */
    public static @NotNull
    PrimitiveArrayDataType getDataType (Field field) {
        assert isArray(field.getType());
        assert PrimitiveDataType.isPrimitive(field.getType().getComponentType());

        switch (field.getType().getComponentType().getName()) {
            case "boolean":
                return new BooleanArrayDataType(field);
            case "byte":
                return new ByteArrayDataType(field);
            case "char":
                return new CharArrayDataType(field);
            case "double":
                return new DoubleArrayDataType(field);
            case "float":
                return new FloatArrayDataType(field);
            case "int":
                return new IntArrayDataType(field);
            case "long":
                return new LongArrayDataType(field);
            case "short":
                return new ShortArrayDataType(field);
            case "java.lang.String":
                return new StringArrayDataType(field);
            default:
                throw new IllegalArgumentException("Field is not primitive " + field.getType().getComponentType().toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    public static @NotNull
    PrimitiveArrayDataType getDataType (Class fieldClass) {
        assert isArray(fieldClass);
        assert PrimitiveDataType.isPrimitive(fieldClass.getComponentType());

        switch (fieldClass.getComponentType().getName()) {
            case "boolean":
                return new BooleanArrayDataType(null);
            case "byte":
                return new ByteArrayDataType(null);
            case "char":
                return new CharArrayDataType(null);
            case "double":
                return new DoubleArrayDataType(null);
            case "float":
                return new FloatArrayDataType(null);
            case "int":
                return new IntArrayDataType(null);
            case "long":
                return new LongArrayDataType(null);
            case "short":
                return new ShortArrayDataType(null);
            case "java.lang.String":
                return new StringArrayDataType(null);
            default:
                throw new IllegalArgumentException("Field is not primitive " + fieldClass.getComponentType().toString());
        }
    }
}
