package com.pia.core.properties;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

public class ComplexType extends DataType implements ConstructableType {
    protected final Type ownType;
    private final List<PiaConstructor> ownConstructors;


    protected ComplexType (Field ownField) throws IllegalAccessException {
        super(ownField);
        ownType = ownField.getType();
        ownConstructors = PiaConstructor.getAllPiaConstructors(ownClass);
    }

    protected ComplexType (Type ownType, Class<?> ownClass) throws IllegalAccessException {
        super(ownClass);
        this.ownType = ownType;
        ownConstructors = PiaConstructor.getAllPiaConstructors(ownClass);
    }

    @Override
    public String getContentType () {
        return "ComplexType(" + ownClass.getName() + ")";
    }

    @Override
    public Object getValue () {
        return null;
    }

    @Override
    public String toString () {
        return null;
    }

    @Override
    public List<PiaConstructor> getConstructors () {
        return ownConstructors;
    }
}
