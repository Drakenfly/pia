package com.pia.core.properties;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Field;
import java.util.Map;

public class ComplexDataType<T> extends DataType{
    Map<Field, DataType> members;

    protected ComplexDataType (@Nullable Field ownField, Class contentClass) {
        super(ownField);
    }

    @Override
    public String toString () {
        return "Complex of Type " + ownField.getType().getName();
    }

    protected ComplexDataType (@NotNull Field ownField) {
        this(ownField, ownField.getType());
    }
}
