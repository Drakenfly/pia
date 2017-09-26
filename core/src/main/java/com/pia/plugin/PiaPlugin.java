package com.pia.plugin;

import com.pia.plugin.annotations.Property;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class PiaPlugin {
    private List<PiaPluginProperty> properties;
    private List<Field> annotatedFields;

    public PiaPlugin() {

    }

    public List<PiaPluginProperty> getProperties() {
        if (this.properties == null) {
            properties = new ArrayList<>();
            // Save "this" to variable - usage in inner class!
            PiaPlugin instance = this;

            for(Field field : instance.getClass().getDeclaredFields()){

                Property param = field.getAnnotation(Property.class);

                if (param != null) {
                    field.setAccessible(true);
                    PiaPluginProperty property = new AbstractPluginProperty(param.name(), param.description()) {
                        @Override
                        public Object getValue() {
                            try {
                                return field.get(instance);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        public void setValue(Object value) {
                            try {
                                field.set(instance, value);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    properties.add(property);
                }
            }
        }

        return properties;
    }
    public List<Field> getAnnotatedFields() {
        if (this.annotatedFields == null) {
            // Save "this" to variable - usage in inner class!
            PiaPlugin instance = this;
            annotatedFields = new LinkedList<>();

            for(Field field : getFieldsUpTo(instance.getClass(), Object.class)){

                Property param = field.getAnnotation(Property.class);

                if (param != null) {
                    annotatedFields.add(field);
                }
            }
        }

        return annotatedFields;
    }

    private static List<Field> getFieldsUpTo(Class<?> startClass, @Nullable Class<?> exclusiveParent) {

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

    public abstract String getName();
    public abstract void start();
}
