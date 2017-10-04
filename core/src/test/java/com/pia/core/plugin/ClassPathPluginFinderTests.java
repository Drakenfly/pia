package com.pia.core.plugin;

import org.junit.Test;

import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClassPathPluginFinderTests {

    private final String testPluginsClassPath = "com.pia.core.plugin.testPlugins";
    private final int numberOfValidPlugins = 2;

    @Test
    public void hasNoAdditionalClassLoadersOnInstantiation() {
        ClassPathPluginFinder finder = new ClassPathPluginFinder();
        assertEquals(finder.getClassLoaders().size(), 0);
    }

    @Test
    public void readJarsFromDirectory() throws IOException {
        Path tempDirectory = Files.createTempDirectory("testJarSearch");
        int numberOfJarFiles = (int)(Math.random() * 10);

        for (int x = 0; x < numberOfJarFiles; x++) {
            Files.createTempFile(tempDirectory, "test_" + x, ".jar");
        }

        ClassPathPluginFinder finder = new ClassPathPluginFinder();

        // PRE-Condition: no additional class loaders provided.
        // Assures that .get(0) returns the correct classloader afterwards
        // and no other plugins may be found
        assertEquals(finder.getClassLoaders().size(), 0);

        finder.addJarFolder(tempDirectory.toFile());
        List<ClassLoader> classLoaders = finder.getClassLoaders();

        assertEquals(classLoaders.size(), 1);
        assertEquals(classLoaders.get(0) instanceof URLClassLoader, true);
        assertEquals(((URLClassLoader)classLoaders.get(0)).getURLs().length, numberOfJarFiles);
    }

    /**
     * Without any additional class loaders, the finder should
     * just find the valid plugins that are inside the given package
     */
    @Test
    public void returnsTheCorrectNumberOfPlugins() {
        ClassPathPluginFinder finder = new ClassPathPluginFinder(testPluginsClassPath);
        assertEquals(finder.findAvailablePlugins().size(), numberOfValidPlugins);
    }

    /**
     * Without any additional class loaders, the finder should
     * just find the valid plugins that are inside the given package when called on the abstract method
     */
    @Test
    public void returnsTheCorrectNumberOfPluginsWhenCalledInternally() {
        ClassPathPluginFinder finder = new ClassPathPluginFinder(testPluginsClassPath);
        assertEquals(finder.getPluginsSynchronized().size(), numberOfValidPlugins);
    }
}
