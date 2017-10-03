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

    public void addPluginFolder(File folder) {
        logger.info("Searching for jar files to add in '" + folder.toString() + "'");

        File pluginsFolder = folder;
        File[] jarFiles = pluginsFolder
                .listFiles(file -> file.getPath().toLowerCase().endsWith(".jar"));
        URL[] jarUrls = {};
        if (jarFiles != null) {
            jarUrls = new URL[jarFiles.length];

            try {
                for (int i = 0; i < jarFiles.length; i++) {
                    URL url = jarFiles[i].toURI().toURL();
                    jarUrls[i] = url;

                    logger.debug("Found '" + url.toString() + "'");
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        logger.debug("Found a total amount " + jarUrls.length + " jar files in '" + folder.toString() + "'");

        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> urlClass = URLClassLoader.class;
        Method method;
        try {
            method = urlClass.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            logger.error("ClassLoader.getSystemClassLoaer().addUrl(URL url) does not exist.");
            return;
        }

        for (URL url : jarUrls) {
            try {
                method.invoke(urlClassLoader, url);
                logger.debug("Successfully added jar '" + url.toString() + "' to classpath");
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error("Could not add '" + url.toString() + "' to classpath");
            }
        }
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
