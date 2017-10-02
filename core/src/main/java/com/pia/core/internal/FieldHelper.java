package com.pia.core.internal;

import com.pia.core.annotation.Property;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FieldHelper {
    /**
     * Returns a list of all fields that are annotated with {@link Property}
     * in the passed class or its superclasses up to {@link java.lang.Object}
     * @param clazz
     * @return
     */
    public static List<Field> getProperties(Class<?> clazz) {
        List<Field> annotatedFields = new LinkedList<>();

        for (Field field : getFieldsUpTo(clazz, Object.class)) {
            Property param = field.getAnnotation(Property.class);

            if (param != null) {
                annotatedFields.add(field);
            }
        }

        return annotatedFields;
    }

    /**
     * Gets all fields of a class and all the classes it derives from.
     *
     * @param startClass Get all fields starting from this class.
     * @param exclusiveParent Get fields up to this class. May be null.
     * @return all fields of a class and all the classes it derives from
     */
    public static List<Field> getFieldsUpTo (Class<?> startClass, Class<?> exclusiveParent) {

        List<Field> currentClassFields = new LinkedList<>();
        currentClassFields.addAll(Arrays.asList(startClass.getDeclaredFields()));
        Class<?> parentClass = startClass.getSuperclass();

        if (parentClass != null &&
                (exclusiveParent == null || !(parentClass.equals(exclusiveParent)))) {
            List<Field> parentClassFields =
                    (List<Field>) getFieldsUpTo(parentClass, exclusiveParent);
            currentClassFields.addAll(parentClassFields);
        }

        return currentClassFields;
    }
}
