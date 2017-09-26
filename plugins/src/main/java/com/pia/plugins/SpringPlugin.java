package com.pia.plugins;

import com.pia.plugin.annotations.Property;
import com.pia.plugin.PiaPlugin;

public class SpringPlugin implements PiaPlugin {

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

    @Override
    public String getName() {
        return "Spring plugin";
    }

    @Override
    public void start() {
        System.out.println("Spring plugin started");
    }
}
