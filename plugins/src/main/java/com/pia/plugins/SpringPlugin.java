package com.pia.plugins;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.Requires;

public class SpringPlugin implements PiaPlugin {

    @Requires
    TestPlugin testPlugin;

    @Override
    public PiaPlugin getInstance() {
        return new SpringPlugin();
    }

    @Override
    public String getName() {
        return "Spring plugin";
    }

    @Override
    public void start() {
        System.out.println("Spring plugin started");
        System.out.println("Test plugin is not null? " + (testPlugin != null));
        System.out.println("Calling testPlugin.getName(): " + testPlugin.getName());
    }
}
