package com.pia.core.plugin;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.matchprocessor.SubclassMatchProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class ClassPathPluginFinder implements PluginFinder {
    private Logger logger = LoggerFactory.getLogger(ClassPathPluginFinder.class);
    private final String basePackage;

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

        scanner
                .matchSubclassesOf(Plugin.class, plugins::add)
                .scan();

        long duration = System.currentTimeMillis() - currentTime;
        logger.debug("Classpath scanning finished. Found " + plugins.size() + " plugins. Scan took " + duration + "ms");

        return plugins;
    }
}
