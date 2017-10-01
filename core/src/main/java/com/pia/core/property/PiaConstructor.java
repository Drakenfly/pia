package com.pia.core.property;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PiaConstructor {
    private final Constructor constructor;

    private DataType[] argumentTypes;

    private PiaConstructor (Constructor constructor) throws IllegalAccessException {
        this.constructor = constructor;
    }

    public Object invoke(DataType[] arguments) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        checkArgs(arguments);
        Object[] constructorArgs = new Object[arguments.length];
        for (int i = 0; i < constructorArgs.length; i++) {
            constructorArgs[i] = arguments[i].getValue();
        }
        return constructor.newInstance(constructorArgs);
    }

    public boolean isEmptyConstructor() throws IllegalAccessException {
        return getArgumentTypes().length <= 0;
    }

    private void checkArgs (DataType[] arguments) throws IllegalAccessException {
        if (arguments == null) {
            throw new NullPointerException("The given arguments array must not be null, however, can be empty.");
        }
        if (arguments.length != getArgumentTypes().length) {
            throw new IllegalArgumentException("Wrong number of arguments (" + arguments.length + "). Expected " + getArgumentTypes().length + ".");
        }

        for (int i = 0; i < arguments.length; i++) {
            if (!getArgumentTypes()[i].ownClass.isAssignableFrom(arguments[i].ownClass)) {
                throw new IllegalArgumentException("Argument " + i + "'s type ("
                        + arguments[i].ownClass.getName() + ") does not match expected type "
                        + getArgumentTypes()[i].ownClass.getName());
            }
        }
    }

    public static List<PiaConstructor> getAllPiaConstructors (Class<?> c) throws IllegalAccessException {
        List<PiaConstructor> list = new LinkedList<>();
        for (Constructor constructor : c.getConstructors()) {
            list.add(new PiaConstructor(constructor));
        }
        return list;
    }

    public DataType[] getArgumentTypes () throws IllegalAccessException {
        if (argumentTypes == null) {
            argumentTypes = new DataType[constructor.getParameterCount()];
            for (int i = 0; i < argumentTypes.length; i++) {
                Class<?> argumentClass = constructor.getParameterTypes()[i];
                argumentTypes[i] = DataType.getDataType(argumentClass, argumentClass);
            }
        }
        return argumentTypes;
    }

    public Class<?> getDeclaringClass() {
        return constructor.getDeclaringClass();
    }

    @Override
    public String toString () {
        try {
            List<String> contentTypes = new LinkedList<>();
            for (DataType dataType : getArgumentTypes()) {
                contentTypes.add(dataType.getContentType());
            }
            String arrayString = Arrays.deepToString(contentTypes.toArray());
            return "new " + constructor.getDeclaringClass().getSimpleName() +
                    "(" + arrayString.substring(1, arrayString.length()-1) + ')';
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "Error in PiaConstructor";
    }
}
