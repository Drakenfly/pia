package com.pia.plugins;

import com.pia.core.annotation.PluginMetadata;
import com.pia.core.annotation.Property;
import com.pia.core.plugin.Plugin;

@PluginMetadata(name = "Spring Plugin")
public class SpringPlugin extends Plugin {

    @Property(
            name = "Console output",
            description = "Sets the console output that will be printed"
    )
    String property1;

    @Property(
            name = "Temperature",
            description = "The desired temperature"
    )
    String property2;

    @Property(
            name = "Your desired middle name",
            description = "Have you ever wished for a (different) middle name? Set it here!"
    )
    String property3;

    public SpringPlugin() {

    }

    @Override
    public void start() {
        System.out.println("Spring plugin started");
    }
}
