package com.pia.core;

import com.pia.core.internal.FieldHelper;
import com.pia.core.plugin.Plugin;
import com.pia.core.plugin.PluginFinder;
import com.pia.core.property.DataType;
import com.pia.core.property.dto.PluginWrapperDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Generator {
    private List<PluginFinder> pluginFinders = new ArrayList<>();

    private Logger logger = LoggerFactory.getLogger(Generator.class);

    public Generator () {
    }

    public void addPluginFinder (PluginFinder finder) {
        this.pluginFinders.add(finder);
    }

    public Collection<Class<? extends Plugin>> getPlugins() {
        Set<Class<? extends Plugin>> plugins = new HashSet<>();
        for (PluginFinder finder: this.pluginFinders) {
            plugins.addAll(finder.findAvailablePlugins());
        }
        return plugins;
    }

    public PluginContext getPluginContext(Collection<Class<? extends Plugin>> plugins) {
        PluginContext pluginContext = new PluginContext();

        for (Class<? extends Plugin> pluginClass : plugins) {
            Constructor defaultConstructor = null;
            for (Constructor c : pluginClass.getConstructors()) {
                if (c.getParameterCount() == 0) {
                    defaultConstructor = c;
                    break;
                }
            }

            if (defaultConstructor == null) {
                logger.error("Could not find default constructor for " + pluginClass.getSimpleName());
            }

            try {
                pluginContext.addPlugin((Plugin) defaultConstructor.newInstance());
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        pluginContext.resolveRequirements();
        return pluginContext;
    }

    /**
     * Gets a plugin's wrapper object, containing all
     * necessary DataType fields and a plugin's class.
     * This information is necessary to instantiate
     * a plugin completely later on.
     *
     * @param plugin The plugin's class to be wrapped
     * @return a wrapper object containing all
     * information necessary to instantiate a plugin
     * later.
     */
    public static PluginWrapper getPluginWrapper (Class<? extends Plugin> plugin) throws IllegalAccessException {
        List<DataType> dataTypes = new LinkedList<>();
        for (Field field : FieldHelper.getProperties(plugin).keySet()) {
            dataTypes.add(DataType.getDataType(field));
        }
        return new PluginWrapper(plugin, dataTypes);
    }

    /**
     * Gets a plugin's wrapper DTO, containing all
     * necessary information that is needed to
     * instantiate a plugin completely later on.
     *
     * @param plugin The plugin's class to be wrapped
     * @return a wrapper DTO object containing all
     * information necessary to instantiate a plugin
     * later.
     */
    public static PluginWrapperDTO getPluginWrapperDTO (Class<? extends Plugin> plugin) {
        return null;
    }
}
