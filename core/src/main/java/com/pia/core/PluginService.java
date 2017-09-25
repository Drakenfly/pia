package com.pia.core;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.Requires;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PluginService {
    List<PiaPlugin> plugins = new ArrayList<>();

    public void addPlugin(PiaPlugin plugin) {
        this.plugins.add(plugin);
    }

    public void resolveRequirements() {
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

    public void start() {
        this.plugins.forEach(plugin -> plugin.start());
    }

    private PiaPlugin findPluginForClass(Class type) {
        for (PiaPlugin plugin: this.plugins) {
            if (plugin.getClass() == type)
                return plugin;
        }

        return null;
    }
}
