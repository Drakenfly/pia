package com.pia.core.property;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;

public class ComplexType extends NullableTypeImpl implements ConstructableType {
    protected final Type ownType;

    private final List<PiaConstructor> ownConstructors;
    private PiaConstructor chosenConstructor;
    private List<DataType> chosenArguments;
    private boolean chosenConstructorLoaded = false;

    protected ComplexType (Field ownField) throws IllegalAccessException {
        super(ownField);
        ownType = ownField.getType();
        ownConstructors = PiaConstructor.getAllPiaConstructors(ownClass);

        /* initialize as null value to prevent infinite recursion later on */
        setValueIsNull(true);
    }

    protected ComplexType (Type ownType, Class<?> ownClass) throws IllegalAccessException {
        super(ownClass);
        this.ownType = ownType;
        ownConstructors = PiaConstructor.getAllPiaConstructors(ownClass);

        /* initialize as null value to prevent infinite recursion later on */
        setValueIsNull(true);
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
                    // TODO infinite recursion is a problem otherwise
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
    protected void readFieldFromObject (Object object) throws IllegalAccessException {
        /* TODO read fields if complex type actually manages the fields variables */
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
            if (false && !chosenConstructorLoaded) {
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
