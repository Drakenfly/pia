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

    /**
     * Returns the field's property annotation if present, else null.
     * 
     * @param field the field object that should be queried.
     * @return the found annotation instance
     */
    public static Property getProperty(Field field) {
        return getAnnotationForField(field, Property.class);
    }

    /**
     * Returns the field's annotation for the specified type if such an annotation is present, else null.
     *
     * @param field the field object that should be queried
     * @param annotationClass the Class object corresponding to the annotation type
     * @param <A> the type of the annotation to query for and return if present
     * @return the field's annotation for the specified annotation type if present on this element, else null
     */
    private static <A extends Annotation> A getAnnotationForField(Field field, Class<A> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    /**
     * Returns all fields in the class where a defined annotation is present.
     *
     * @param clazz the class to start searching for annotated elements
     * @param exclusiveParent the exclusive parent that stops the field search in super classes
     * @param annotationClass the Class object corresponding to the annotation type
     * @param <A> the type of the annotation to query for and return as entry with the annotated field
     * @return the field's annotation for the specified annotation type if present on this element, else null
     */
    private static <A extends Annotation> Map<Field, A> getAnnotatedFields(Class<?> clazz, Class<?> exclusiveParent, Class<A> annotationClass) {
        Map<Field, A> annotatedFields = new HashMap<>();

        for (Field field: getFieldsUpTo(clazz, exclusiveParent)) {
            A value = field.getAnnotation(annotationClass);

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
