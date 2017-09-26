package com.pia.gui;

import java.lang.reflect.Field;
import java.util.List;

public class FieldSpy<T> {
    public boolean[][] b = {{ false, false }, { true, true } };
    public String name  = "Alice";
    public List<Integer> list;
    public T val;

    public static void spy(Field f) {
            System.out.format("Type: %s%n", f.getType());
            System.out.format("GenericType: %s%n", f.getGenericType());
    }

    public static void spy(Iterable<Field> fs) {
        for (Field f : fs)
            spy(f);
    }

}