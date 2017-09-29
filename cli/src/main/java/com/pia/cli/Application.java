package com.pia.cli;

import com.pia.core.Generator;
import com.pia.core.PluginService;
import com.pia.plugin.PiaPlugin;
import com.pia.plugin.PiaPluginProperty;

import java.io.File;

public class Application {
    public static void main(String[] args) {
        Generator generator = new Generator(new File("plugins"));
        PluginService pluginService = generator.getPluginService();

        for (PiaPlugin plugin: pluginService.getLoadedPlugins()) {
            System.out.println("Showing available parameters for " + plugin.getName());

            for (PiaPluginProperty property: pluginService.getProperties(plugin)) {
                System.out.println(property.getName() + " [" + property.getDescription() + "]");
                System.out.println("Property value before assignment: " + property.getValue());
                property.setValue("Test");
                System.out.println("Property value after assignment: " + property.getValue());
            }
        }

        generator.start();
    }
}
