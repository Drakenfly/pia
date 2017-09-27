package com.pia.core.properties;

import java.lang.reflect.Field;

/**
 * Complex array types are every array of a non
 * primitive type.
 * Exceptions are the corresponding objects for
 * primitives, such as java.lang.Integer,
 * as well as java.lang.String arrays.
 */
public class ComplexArrayDataType extends ArrayDataType<ComplexDataType> {
    protected ComplexArrayDataType (Field ownField, DataType contentClass) {
        super(ownField);
    }
}
