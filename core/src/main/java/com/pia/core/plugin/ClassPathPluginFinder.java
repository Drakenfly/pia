package com.pia.core.plugin;

import com.pia.core.internal.PluginHelper;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

/**
 * A plugin finder that fast scans using the system class loader or additional provided class loaders.
 *
 * To improve scan performance, a base package may be defined used by the finder.
 */
public class ClassPathPluginFinder extends PluginFinder {
    private Logger logger = LoggerFactory.getLogger(ClassPathPluginFinder.class);
    private final String basePackage;
    private List<ClassLoader> classLoaders = new LinkedList<>();

    public ClassPathPluginFinder() {
        this("");
    }

    public ClassPathPluginFinder(String basePackage) {
        this.basePackage = basePackage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Class<? extends Plugin>> getPluginsSynchronized() {
        logger.debug("Starting plugin scanning for base package '" + basePackage + "'");
        long currentTime = System.currentTimeMillis();

        FastClasspathScanner scanner = new FastClasspathScanner(basePackage);
        List<Class<? extends Plugin>> plugins = new LinkedList<>();

        this.classLoaders.forEach(scanner::addClassLoader);

        // TODO: check if plugin metadata is provided
        scanner
                .matchSubclassesOf(Plugin.class, plugin -> {
                    if (PluginHelper.isPlugin(plugin)) {
                        plugins.add(plugin);
                    }
                })
                .scan();

        long duration = System.currentTimeMillis() - currentTime;
        logger.debug("Classpath scanning finished. Found " + plugins.size() + " plugins. Scan took " + duration + "ms");

        return plugins;
    }

    /**
     * Adds a class loader to the classpath scanner
     *
     * @param classLoader the classLoader instance that should be added
     */
    public void addClassLoader(ClassLoader classLoader) {
        this.classLoaders.add(classLoader);
    }

    /**
     * @return the internal list of additional class loaders
     */
    public List<ClassLoader> getClassLoaders() {
        return this.classLoaders;
    }

    /**
     * Searches for jar files inside the passed directory and it's subdirectories, then
     * creates a class loader with all jar files included and add's it to the internal list
     *
     * @param directory A file that points to a directory. If the file is no directory, the search is omitted.
     */
    public void addJarFolder(File directory) {
        if (!directory.isDirectory()) {
            logger.info("Passed file is not a directory. Omitting search of jar files.");
        }

        logger.info("Searching for jar files to add in '" + directory.toString() + "'");

        File[] jarFiles = directory
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

        logger.info("Total amount: " + jarUrls.length + " jar files found in '" + directory.toString() + "'");

        this.addClassLoader(new URLClassLoader(jarUrls));
    }

}
