package com.pia.core;

import com.pia.plugin.PiaPlugin;
import com.sun.istack.internal.Nullable;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

public class Generator {
    PluginService pluginService = new PluginService();

    public Generator() {
        this.loadPlugins(null);
    }

    public Generator(String directory) {
        this.loadPlugins(this.findExternalPlugins(directory));
    }

    private URLClassLoader findExternalPlugins(String directory) {
        File pluginsFolder = new File(directory);
        File[] jarFiles = pluginsFolder
                .listFiles(file -> file.getPath().toLowerCase().endsWith(".jar"));
        URL[] jarUrls = { };
        if (jarFiles != null) {
            jarUrls = new URL[jarFiles.length];

            try {
                for (int i = 0; i < jarFiles.length; i++) {
                    jarUrls[i] = jarFiles[i].toURI().toURL();
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }

        return new URLClassLoader(jarUrls);
    }

    private void loadPlugins(@Nullable URLClassLoader classLoader) {
        ServiceLoader<PiaPlugin> serviceLoader;

        if (classLoader != null) {
            serviceLoader = ServiceLoader.load(PiaPlugin.class, classLoader);
        } else {
            serviceLoader = ServiceLoader.load(PiaPlugin.class);
        }

        for (PiaPlugin plugin : serviceLoader) {
            pluginService.addPlugin(plugin);
        }

        pluginService.resolveRequirements();
    }

    public PluginService getPluginService() {
        return pluginService;
    }

    public void start() {
        pluginService.start();
    }
}
