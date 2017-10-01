package com.pia.core.properties;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ComplexType extends NullableType implements ConstructableType {
    protected final Type ownType;

    private final List<PiaConstructor> ownConstructors;
    private PiaConstructor chosenConstructor;
    private List<DataType> chosenArguments;
    private boolean chosenConstructorLoaded = false;

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
    public String getContentType () throws IllegalAccessException {
        if (!chosenConstructorLoaded) {
            findDefaultConstructor();
        }
        StringBuilder args = new StringBuilder("Object ... args");
        if (chosenConstructor != null) {
            args = new StringBuilder();
            Iterator<DataType> iterator = Arrays.asList(chosenConstructor.getArgumentTypes()).iterator();
            while (iterator.hasNext()) {
                DataType arg = iterator.next();
                if (arg instanceof ComplexType || arg instanceof CollectionType) {
                    // infinite recursion is a problem otherwise
                    args.append(arg.toString());
                }
                else {
                    args.append(arg.getContentType());
                }
                if (iterator.hasNext()) {
                    args.append(", ");
                }
            }
        }
        return ownClass.getSimpleName() + "(" + args.toString() + ")";
    }

    @Override
    public Object getValue () throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (!chosenConstructorLoaded) {
            findDefaultConstructor();
        }
        if (isInterfaceOrAbstract()) {
            throw new IllegalStateException(getContentType() + " is an interface with no known implementation.");
        }
        else if (chosenConstructor == null || chosenArguments == null) {
            throw new IllegalStateException("You must choose a constructor and arguments first, before instantiating an instance");
        }
        DataType[] args = new DataType[chosenArguments.size()];
        args = chosenArguments.toArray(args);
        return chosenConstructor.invoke(args);
    }

    @Override
    public String toString () {
        try {
            if (!chosenConstructorLoaded) {
                findDefaultConstructor();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ownClass.getSimpleName() + (chosenArguments == null ? "" : (Arrays.deepToString(chosenArguments.toArray())));
    }

    @Override
    public List<PiaConstructor> getConstructors () {
        try {
            if (!chosenConstructorLoaded) {
                findDefaultConstructor();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ownConstructors;
    }

    @Override
    public void setChosenConstructor (PiaConstructor constructor) throws IllegalAccessException {
        chosenConstructor = constructor;
        if (chosenConstructor.isEmptyConstructor()) {
            chosenArguments = new LinkedList<>();
        }
        else {
            chosenArguments = new LinkedList<>(Arrays.asList(chosenConstructor.getArgumentTypes()));
        }
    }

    @Override
    public PiaConstructor getChosenConstructor () throws IllegalAccessException {
        if (!chosenConstructorLoaded) {
            findDefaultConstructor();
        }
        return chosenConstructor;
    }

    @Override
    public List<DataType> getChosenArgumens () {
        return chosenArguments;
    }

    private void findDefaultConstructor () throws IllegalAccessException {
        chosenConstructorLoaded = true;
        if (ownConstructors.size() == 1) {
            setChosenConstructor(ownConstructors.get(0));
        }
        else {
            for (PiaConstructor constructor : ownConstructors) {
                if (constructor.isEmptyConstructor()) {
                    setChosenConstructor(constructor);
                    break;
                }
            }
        }
    }
}
