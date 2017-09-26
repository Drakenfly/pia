package com.pia.core;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.PiaPluginProperty;
import com.pia.plugin.annotations.Property;
import com.pia.plugin.annotations.Requires;

import javax.swing.text.html.ListView;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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
                            // NOTE: this should not happen, as we set
                            // accessible to true before accessing the property
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public List<PiaPlugin> getPlugins() {
        return this.plugins;
    }

    public List<PiaPluginProperty> getProperties(PiaPlugin plugin) {
        return plugin.getProperties();
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
