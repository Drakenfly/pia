package com.pia.core;

import com.pia.core.plugin.PiaPlugin;
import com.pia.core.plugin.PiaPluginProperty;
import com.pia.core.annotations.Requires;
import com.pia.core.exceptions.RequiredObjectIsNoPiaPluginException;
import com.pia.core.exceptions.RequiredPluginNotAvailableException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PluginService {
    List<PiaPlugin> plugins = new ArrayList<>();

    void addPlugin (PiaPlugin plugin) {
        this.plugins.add(plugin);
    }

    void resolveRequirements () throws RequiredPluginNotAvailableException, RequiredObjectIsNoPiaPluginException {
        for (PiaPlugin plugin : this.plugins) {
            for (Field field : PiaPlugin.getFieldsUpTo(plugin.getClass(), Object.class)) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                Class requiredPluginClass = field.getType();

                for (Annotation a : annotations) {
                    if (a.annotationType() == Requires.class) {
                        if (!isPiaPlugin(field.getType())) {
                            throw new RequiredObjectIsNoPiaPluginException(plugin, requiredPluginClass);
                        }
                        try {
                            PiaPlugin requiredPlugin = findPluginForClass(field.getType());

                            if (requiredPlugin == null) {
                                throw new RequiredPluginNotAvailableException(plugin, requiredPluginClass);
                            }

                            boolean accessible = field.isAccessible();
                            field.setAccessible(true);
                            field.set(plugin, requiredPlugin);
                            field.setAccessible(accessible);

                        } catch (IllegalAccessException ex) {
                            // NOTE: this should not happen, as we set
                            // accessible to true before accessing the property
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private boolean isPiaPlugin(Class clazz) {
        if (clazz.equals(PiaPlugin.class)) {
            return true;
        } else if (clazz.equals(Object.class)) {
            return false;
        } else {
            Class superClass = clazz.getSuperclass();
            if (superClass == null) {
                return false;
            } else {
                return isPiaPlugin(superClass);
            }
        }
    }

    public List<PiaPlugin> getLoadedPlugins() {
        return this.plugins;
    }

    public List<PiaPluginProperty> getProperties (PiaPlugin plugin) {
        return plugin.getProperties();
    }

    void start () {
        for (PiaPlugin plugin : this.plugins) {
            plugin.start();
        }
    }

    private PiaPlugin findPluginForClass (Class type) {
        for (PiaPlugin plugin : this.plugins) {
            if (plugin.getClass() == type) {
                return plugin;
            }
        }

        return null;
    }
}
