package com.pia.plugins;

import com.pia.core.plugin.PiaPlugin;
import com.pia.core.annotation.Property;
import com.pia.core.annotation.Requires;

public class TestPlugin extends PiaPlugin {

    @Property(name = "Test parameter", description = "This is a fancy feature")
    String test;

    @Requires
    SpringPlugin springPlugin;

    @Override
    public String getName() {
        return "Test Plugin";
    }

    @Override
    public void start() {
        System.out.println("Test plugin started");
        System.out.println("Spring plugin is not null? " + (springPlugin != null));
        System.out.println("Calling springPlugin.getName(): " + springPlugin.getName());
        System.out.println("Printing test value: " + this.test);
    }
}
