package com.pia.cli;

import com.pia.core.Generator;
import com.pia.core.PluginService;
import com.pia.core.plugin.Plugin;

import java.io.File;

public class Application {
    public static void main(String[] args) {
        Generator generator = new Generator(new File("plugins"));
        PluginService pluginService = generator.getPluginService();

        for (Plugin plugin: pluginService.getLoadedPlugins()) {
            System.out.println("Showing available parameters for " + plugin.getName());
        }

        generator.start();
    }
}
