package com.pia.plugins;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;
import com.pia.plugin.annotations.Requires;

public class TestPlugin implements PiaPlugin {

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
