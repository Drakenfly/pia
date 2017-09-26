package com.pia.core;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.PiaPluginProperty;
import com.pia.plugin.AbstractPluginProperty;
import com.pia.plugin.annotations.Property;
import com.pia.plugin.annotations.Requires;

import javax.swing.text.html.ListView;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PluginService {
    List<PiaPlugin> plugins = new ArrayList<>();

    void addPlugin(PiaPlugin plugin) {
        this.plugins.add(plugin);
    }

    void resolveRequirements() {
        for (PiaPlugin plugin: this.plugins) {

            for(Field field : plugin.getClass().getDeclaredFields()){
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation a: annotations) {
                    if (a.annotationType() == Requires.class) {
                        try {
                            boolean accessible = field.isAccessible();
                            field.setAccessible(true);
                            field.set(plugin, findPluginForClass(field.getType()));
                            field.setAccessible(accessible);
                        } catch (Exception ex) {

                        }
                    }
                }
            }
        }
    }

    public List<PiaPlugin> getPlugins() {
        return this.plugins;
    }

    public List<Field> getAnnotatedPluginFields(PiaPlugin plugin) {
        List<Field> fields = new LinkedList<>();
        for (Field field : plugin.getClass().getFields()) {
            Property param = field.getAnnotation(Property.class);

            if (param != null) {
                fields.add(field);
            }
        }
        return fields;
    }

    public List<PiaPluginProperty> getProperties(PiaPlugin plugin) {
        List<PiaPluginProperty> properties = new ArrayList<>();

        for(Field field : plugin.getClass().getDeclaredFields()){

            Property param = field.getAnnotation(Property.class);

            if (param != null) {
                field.setAccessible(true);
                PiaPluginProperty property = new AbstractPluginProperty(param.name(), param.description()) {
                    @Override
                    public Object getValue() {
                        try {
                            return field.get(plugin);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    public void setValue(Object value) {
                        try {
                            field.set(plugin, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                };

                properties.add(property);
            }
        }

        return properties;
    }

    void start() {
        for (PiaPlugin plugin: this.plugins) {
            plugin.start();
        }
    }

    private PiaPlugin findPluginForClass(Class type) {
        for (PiaPlugin plugin: this.plugins) {
            if (plugin.getClass() == type)
                return plugin;
        }

        return null;
    }
}
