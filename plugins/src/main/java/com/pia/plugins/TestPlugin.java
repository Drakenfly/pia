package com.pia.plugins;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.Requires;

public class TestPlugin implements PiaPlugin {

    @Requires
    SpringPlugin springPlugin;

    @Override
    public PiaPlugin getInstance() {
        return new TestPlugin();
    }

    @Override
    public String getName() {
        return "Test Plugin";
    }

    @Override
    public void start() {
        System.out.println("Test plugin started");
        System.out.println("Spring plugin is not null? " + (springPlugin != null));
        System.out.println("Calling springPlugin.getName(): " + springPlugin.getName());
    }
}
