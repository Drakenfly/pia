package com.pia.core.properties;
import java.lang.reflect.Field;

public class ComplexArrayDataType extends ArrayDataType <ComplexDataType> {
    protected ComplexArrayDataType (Field ownField, DataType contentClass) {
        super(ownField);
    }
}
