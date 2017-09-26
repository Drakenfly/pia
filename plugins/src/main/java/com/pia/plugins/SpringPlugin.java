package com.pia.plugins;

import com.pia.plugin.annotations.Property;
import com.pia.plugin.PiaPlugin;

public class SpringPlugin extends PiaPlugin {

    @Property(
            name = "Console output",
            description = "Sets the console output that will be printed"
    )
    String property1;

    @Override
    public String getName() {
        return "Spring plugin";
    }

    @Override
    public void start() {
        System.out.println("Spring plugin started");
    }
}
