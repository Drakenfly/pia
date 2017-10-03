package com.pia.core.plugin;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.matchprocessor.SubclassMatchProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

public class ClassPathPluginFinder implements PluginFinder {
    private Logger logger = LoggerFactory.getLogger(ClassPathPluginFinder.class);
    private final String basePackage;
    private List<ClassLoader> classLoaders = new LinkedList<>();

    @FunctionalInterface
    interface SubclassMatchProcessor<T> {
        public void processMatch(Class<? extends T> matchingClass);
    }

    public ClassPathPluginFinder(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public List<Class<? extends Plugin>> findAvailablePlugins() {
        logger.debug("Starting plugin scanning for base package '" + basePackage + "'");
        long currentTime = System.currentTimeMillis();

        FastClasspathScanner scanner = new FastClasspathScanner(basePackage);
        List<Class<? extends Plugin>> plugins = new LinkedList<>();

        this.classLoaders.forEach(classLoader -> scanner.addClassLoader(classLoader));

        scanner
                .matchSubclassesOf(Plugin.class, plugins::add)
                .scan();

        long duration = System.currentTimeMillis() - currentTime;
        logger.debug("Classpath scanning finished. Found " + plugins.size() + " plugins. Scan took " + duration + "ms");

        return plugins;
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
        logger.debug("Total amount: " + jarUrls.length + " jar files (in '" + folder.toString() + "')");

        this.classLoaders.add(new URLClassLoader(jarUrls));
    }


}
