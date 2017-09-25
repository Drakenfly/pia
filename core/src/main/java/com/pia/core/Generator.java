package com.pia.core;

import com.pia.plugin.PiaPlugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class Generator {
    PluginService pluginService = new PluginService();

    public void start() {
        try {
            File pluginsFolder = new File("plugins");
            List<File> jarFiles = Arrays.asList(pluginsFolder.listFiles(file -> file.getPath().toLowerCase().endsWith(".jar")));
            List<URL> jarUrls = new ArrayList<>();
            jarFiles.forEach(file -> {
                try {
                    jarUrls.add(file.toURI().toURL());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            });
            URLClassLoader urlCl = new URLClassLoader(jarUrls.toArray(new URL[0]));

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
