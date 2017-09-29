package com.pia.core.properties;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PiaConstructor {
    private final Constructor constructor;
    private final DataType[] argumentTypes;

    private PiaConstructor (Constructor constructor) throws IllegalAccessException {
        this.constructor = constructor;
        argumentTypes = new DataType[constructor.getParameterCount()];
        for (int i = 0; i < argumentTypes.length; i++) {
            Class<?> argumentClass = constructor.getParameterTypes()[i];
            argumentTypes[i] = DataType.getDataType(argumentClass, argumentClass);
        }
    }

    public Object invoke(DataType[] arguments) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        checkArgs(arguments);
        Object[] constructorArgs = new Object[arguments.length];
        for (int i = 0; i < constructorArgs.length; i++) {
            constructorArgs[i] = arguments[0].getValue();
        }
        return constructor.newInstance(constructorArgs);
    }

    public boolean isEmptyConstructor() {
        return argumentTypes.length <= 0;
    }

    private void checkArgs (DataType[] arguments) {
        if (arguments == null) {
            throw new NullPointerException("The given arguments array must not be null, however, can be empty.");
        }
        if (arguments.length != argumentTypes.length) {
            throw new IllegalArgumentException("Wrong number of arguments (" + arguments.length + "). Expected " + argumentTypes.length + ".");
        }

        for (int i = 0; i < arguments.length; i++) {
            if (!argumentTypes[i].ownClass.isAssignableFrom(arguments[i].ownClass)) {
                throw new IllegalArgumentException("Argument " + i + "'s type ("
                        + arguments[i].ownClass.getName() + ") does not match expected type "
                        + argumentTypes[i].ownClass.getName());
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

    @Override
    public String toString () {
        return "PiaConstructor arguments = " + Arrays.deepToString(Arrays.stream(argumentTypes).map(DataType::getContentType).toArray());
    }
}
