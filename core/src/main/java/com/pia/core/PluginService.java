package com.pia.core;

import com.pia.core.internal.FieldHelper;
import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.Requires;
import com.pia.core.exception.RequiredObjectIsNoPiaPluginException;
import com.pia.core.exception.RequiredPluginNotAvailableException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PluginService {
    List<Plugin> plugins = new ArrayList<>();

    void addPlugin (Plugin plugin) {
        this.plugins.add(plugin);
    }

    void resolveRequirements () throws RequiredPluginNotAvailableException, RequiredObjectIsNoPiaPluginException {
        for (Plugin plugin : this.plugins) {
            for (Field field : FieldHelper.getFieldsUpTo(plugin.getClass(), Object.class)) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                Class requiredPluginClass = field.getType();

                for (Annotation a : annotations) {
                    if (a.annotationType() == Requires.class) {
                        if (!isPiaPlugin(field.getType())) {
                            throw new RequiredObjectIsNoPiaPluginException(plugin, requiredPluginClass);
                        }
                        try {
                            Plugin requiredPlugin = findPluginForClass(field.getType());

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
        if (clazz.equals(Plugin.class)) {
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

    public List<Plugin> getLoadedPlugins() {
        return this.plugins;
    }

    void start () {
        for (Plugin plugin : this.plugins) {
            plugin.start();
        }
    }

    private Plugin findPluginForClass (Class type) {
        for (Plugin plugin : this.plugins) {
            if (plugin.getClass() == type) {
                return plugin;
            }
        }

        return null;
    }
}
