package com.pia.core.properties;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class ComplexType extends DataType implements ConstructableType {
    protected final Type ownType;
    private final List<PiaConstructor> ownConstructors;
    private PiaConstructor chosenConstructor;
    private List<DataType> chosenArguments;


    protected ComplexType (Field ownField) throws IllegalAccessException {
        super(ownField);
        ownType = ownField.getType();
        ownConstructors = PiaConstructor.getAllPiaConstructors(ownClass);
        findDefaultConstructor();
    }

    protected ComplexType (Type ownType, Class<?> ownClass) throws IllegalAccessException {
        super(ownClass);
        this.ownType = ownType;
        ownConstructors = PiaConstructor.getAllPiaConstructors(ownClass);
        findDefaultConstructor();
    }

    @Override
    public String getContentType () {
        return "ComplexType(" + ownClass.getSimpleName() + ")";
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

    @Override
    public void setChosenConstructor (PiaConstructor constructor) {
        chosenConstructor = constructor;
    }

    @Override
    public PiaConstructor getChosenConstructor () {
        return chosenConstructor;
    }

    @Override
    public void setChosenArgumens (List<DataType> arguments) {
        chosenArguments = arguments;
    }

    @Override
    public List<DataType> getChosenArgumens () {
        return chosenArguments;
    }

    private void findDefaultConstructor() {
        for (PiaConstructor constructor : ownConstructors) {
            if (constructor.isEmptyConstructor()) {
                chosenConstructor = constructor;
                chosenArguments = new LinkedList<>();
                break;
            }
        }
    }
}
