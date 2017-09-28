package com.pia.core.properties_old.primitiveObjectArrays;

import com.pia.core.properties_old.ArrayDataType;
import com.pia.core.properties_old.primitiveObjects.PrimitiveObjectDataType;
import com.pia.core.properties_old.primitives.PrimitiveDataType;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

/**
 * Primitive object arrays are - as the name suggests - wrappers for arrays of primitive objects.
 * To learn more about Arrays @see com.pia.core.ArrayDataType
 * To learn more about primitive objects @see com.pia.core.primitiveObjects.PrimitiveObjectDataType
 *
 * @param <T>
 */
public abstract class PrimitiveObjectArrayDataType<T extends PrimitiveObjectDataType> extends ArrayDataType<T> {

    protected PrimitiveObjectArrayDataType (Field ownField) {
        super(ownField);
    }

    /**
     * {@inheritDoc}
     */
    public static @NotNull
    PrimitiveObjectArrayDataType getDataType (Field field) {
        assert isArray(field.getType());
        //assert PrimitiveDataType.isPrimitive(field.getType().getComponentType());

        switch (field.getType().getComponentType().getName()) {
            case "java.lang.Boolean":
                return new BooleanObjectArrayDataType(field);
            case "java.lang.Byte":
                return new ByteObjectArrayDataType(field);
            case "java.lang.Character":
                return new CharacterObjectArrayDataType(field);
            case "java.lang.Double":
                return new DoubleObjectArrayDataType(field);
            case "java.lang.Float":
                return new FloatObjectArrayDataType(field);
            case "java.lang.Integer":
                return new IntegerObjectArrayDataType(field);
            case "java.lang.Long":
                return new LongObjectArrayDataType(field);
            case "java.lang.Short":
                return new ShortObjectArrayDataType(field);
            default:
                throw new IllegalArgumentException("Field is not primitive");
        }
    }

    /**
     * {@inheritDoc}
     */
    public static @NotNull
    PrimitiveObjectArrayDataType getDataType (Class fieldClass) {
        assert isArray(fieldClass);
        assert PrimitiveDataType.isPrimitive(fieldClass.getComponentType());

        switch (fieldClass.getComponentType().getName()) {
            case "java.lang.Boolean":
                return new BooleanObjectArrayDataType(null);
            case "java.lang.Byte":
                return new ByteObjectArrayDataType(null);
            case "java.lang.Character":
                return new CharacterObjectArrayDataType(null);
            case "java.lang.Double":
                return new DoubleObjectArrayDataType(null);
            case "java.lang.Float":
                return new FloatObjectArrayDataType(null);
            case "java.lang.Integer":
                return new IntegerObjectArrayDataType(null);
            case "java.lang.Long":
                return new LongObjectArrayDataType(null);
            case "java.lang.Short":
                return new ShortObjectArrayDataType(null);
            default:
                throw new IllegalArgumentException("Field is not primitive " + fieldClass.getComponentType().toString());
        }
    }
}
