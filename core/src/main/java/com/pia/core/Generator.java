package com.pia.core;

import com.pia.core.plugin.Plugin;
import com.pia.core.plugin.PluginFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

public class Generator {
    private PluginService pluginService = new PluginService();
    private List<PluginFinder> pluginFinders = new ArrayList<>();

    private Logger logger = LoggerFactory.getLogger(Generator.class);

    public Generator () {
    }

    public void addPluginFinder(PluginFinder finder) {
        this.pluginFinders.add(finder);
    }

    public List<Class<? extends Plugin>> getPlugins() {
        List<Class<? extends Plugin>> plugins = new LinkedList<>();
        for (PluginFinder finder: this.pluginFinders) {
            plugins.addAll(finder.findAvailablePlugins());
        }
        return plugins;
    }

    public PluginService getPluginService (List<Class<? extends Plugin>> plugins) {
        PluginService pluginService = new PluginService();

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
                pluginService.addPlugin((Plugin) defaultConstructor.newInstance());
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        pluginService.resolveRequirements();
        return pluginService;
    }
}
