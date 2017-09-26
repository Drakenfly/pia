package com.pia.core.properties;

import java.lang.reflect.Field;
import java.util.Map;

public class ComplexDataType<T> extends DataType{
    Map<Field, DataType> members;

    protected ComplexDataType (Field ownField) {
        super(ownField);
    }
}
