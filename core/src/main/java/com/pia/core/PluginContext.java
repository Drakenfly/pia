package com.pia.core;

import com.pia.core.annotation.Requires;
import com.pia.core.exception.RequiredObjectIsNoPiaPluginException;
import com.pia.core.exception.RequiredPluginNotAvailableException;
import com.pia.core.internal.FieldHelper;
import com.pia.core.internal.PluginHelper;
import com.pia.core.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PluginContext {
    private Logger logger = LoggerFactory.getLogger(PluginContext.class);
    private List<Plugin> plugins = new ArrayList<>();

    public PluginContext() { }

    void addPlugin (Plugin plugin) {
        this.plugins.add(plugin);
    }

    void resolveRequirements () throws RequiredPluginNotAvailableException, RequiredObjectIsNoPiaPluginException {
        for (Plugin plugin : this.plugins) {
            Class pluginClass = plugin.getClass();
            String pluginName = PluginHelper.getPluginName(pluginClass);
            Map<Field, Requires> requirements = FieldHelper.getRequiredPlugins(plugin.getClass());

            for (Map.Entry<Field, Requires> requirement: requirements.entrySet()) {
                Field field = requirement.getKey();
                Requires requires = requirement.getValue();

                Class requiredPluginClass = field.getType();
                String requiredPluginName = PluginHelper.getPluginName(requiredPluginClass);

                if (!isPiaPlugin(requiredPluginClass)) {
                    throw new RequiredObjectIsNoPiaPluginException(plugin, requiredPluginClass);
                }
                try {
                    Plugin requiredPlugin = findPluginForClass(requiredPluginClass);

                    if (requiredPlugin == null) {
                        if (requires.optional()) {
                            logger.debug("Did not wire optional '" + requiredPluginName + "' for '" + pluginName + "', because there is no available instance.");
                        } else {
                            throw new RequiredPluginNotAvailableException(plugin, requiredPluginClass);
                        }
                    } else {
                        logger.debug("Wiring target plugin '" + requiredPluginName + "' for " + pluginName);

                        boolean accessible = field.isAccessible();
                        field.setAccessible(true);
                        field.set(plugin, requiredPlugin);
                        field.setAccessible(accessible);
                    }

                } catch (IllegalAccessException ex) {
                    // NOTE: this should not happen, as we set
                    // accessible to true before accessing the property
                    ex.printStackTrace();
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

    private Plugin findPluginForClass (Class type) {
        for (Plugin plugin : this.plugins) {
            if (plugin.getClass() == type) {
                return plugin;
            }
        }

        return null;
    }
}
