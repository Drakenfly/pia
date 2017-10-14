package com.pia.plugins;

import com.pia.core.annotation.PluginMetadata;
import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.Property;
import com.pia.core.annotation.Requires;
import com.pia.plugins2.OutOfScopePlugin;

@PluginMetadata(name = "TestPlugin")
public class TestPlugin extends Plugin {

    @Property(name = "Test parameter", description = "This is a fancy feature")
    String test;

    @Requires
    SpringPlugin springPlugin;

    @Requires(optional = true)
    OutOfScopePlugin plugin;

    @Override
    public void start() {
        System.out.println("Test plugin started");
        System.out.println("Spring plugin is not null? " + (springPlugin != null));
        System.out.println("Calling springPlugin.getName(): " + springPlugin.getName());
        System.out.println("Printing test value: " + this.test);
    }
}
