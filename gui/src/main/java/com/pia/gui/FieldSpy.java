package com.pia.gui;

import java.lang.reflect.Field;
import java.util.List;

public class FieldSpy<T> {
    public static void spy(Field f) {
        if (f.getType().isArray()) {
            System.out.printf("Field is an array of type %s%n", f.getType().getComponentType());
        }
        System.out.format("Type: %s%n", f.getType());
        System.out.format("GenericType: %s%n", f.getGenericType());
    }

    public static void spy(Iterable<Field> fs) {
        for (Field f : fs)
            spy(f);
    }

}