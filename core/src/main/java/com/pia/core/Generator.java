package com.pia.core;

import com.pia.plugin.PiaPlugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.ServiceLoader;

public class Generator {
    PluginService pluginService = new PluginService();

    public void start() {
        try {
            File pluginsFolder = new File("plugins");
            File[] jarFiles = pluginsFolder.listFiles(file -> file.getPath().toLowerCase().endsWith(".jar"));
            URL[] jarUrls = new URL[jarFiles.length];
            System.out.println("Number of found jars in /plugins: " + jarFiles.length);
            for (int i = 0; i < jarFiles.length; i++)
                jarUrls[i] = jarFiles[i].toURI().toURL();
            URLClassLoader urlCl = new URLClassLoader(jarUrls);

            System.out.println("Printing plugin-list:");
            ServiceLoader<PiaPlugin> serviceLoader = ServiceLoader.load(PiaPlugin.class, urlCl);
            for (PiaPlugin aServiceLoader : serviceLoader) {
                pluginService.addPlugin(aServiceLoader);
                System.out.println(aServiceLoader.getName());
            }
            System.out.println("Finished printing plugin-list");
        } catch (Exception ex) {

        }

        pluginService.resolveRequirements();
        pluginService.start();
    }
}
