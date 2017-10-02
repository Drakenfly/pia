package com.pia.core.internal;

import com.pia.core.annotation.Property;
import com.pia.core.annotation.Requires;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class FieldHelper {
    /**
     * Returns a list of all fields that are annotated with {@link Property}
     * in the passed class or its superclasses up to {@link Object}.
     * @param clazz The start class
     * @return
     */
    public static Map<Field, Property> getProperties(Class<?> clazz) {
        return getAnnotatedFields(clazz, Object.class, Property.class);
    }

    /**
     * Returns a list of all fields that are annotated with {@link Requires}
     * in the passed class or its superclasses up to {@link Object}.
     * @param clazz The start class
     */
    public static Map<Field, Requires> getRequiredPlugins(Class<?> clazz) {
        return getAnnotatedFields(clazz, Object.class, Requires.class);
    }

    private static <T extends Annotation> Map<Field, T> getAnnotatedFields(Class<?> clazz, Class<?> exclusiveParent, Class<T> annotationClass) {
        Map<Field, T> annotatedFields = new HashMap<>();

        for (Field field: getFieldsUpTo(clazz, exclusiveParent)) {
            T value = field.getAnnotation(annotationClass);

            if (value != null) {
                annotatedFields.put(field, value);
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
