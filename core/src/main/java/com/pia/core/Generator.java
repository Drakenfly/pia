package com.pia.core;

import com.pia.core.plugin.Plugin;
import com.pia.core.plugin.PluginFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class Generator {
    private List<PluginFinder> pluginFinders = new ArrayList<>();

    private Logger logger = LoggerFactory.getLogger(Generator.class);

    public Generator () {
    }

    public void addPluginFinder(PluginFinder finder) {
        this.pluginFinders.add(finder);
    }

    public Collection<Class<? extends Plugin>> getPlugins() {
        Set<Class<? extends Plugin>> plugins = new HashSet<>();
        for (PluginFinder finder: this.pluginFinders) {
            plugins.addAll(finder.findAvailablePlugins());
        }
        return plugins;
    }

    public PluginContext getPluginService (Collection<Class<? extends Plugin>> plugins) {
        PluginContext pluginContext = new PluginContext();

        for (Class<? extends Plugin> pluginClass: plugins) {
            Constructor defaultConstructor = null;
            for (Constructor c: pluginClass.getConstructors()) {
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
}
