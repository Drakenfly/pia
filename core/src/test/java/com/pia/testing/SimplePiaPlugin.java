package com.pia.testing;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;

public class SimplePiaPlugin extends PiaPlugin {

    public static final String EXAMPLE_PROPERTY_NAME = "example property";
    public static final String EXAMPLE_PROPERTY_DESCRIPTION = "example property";

    @Property(name = EXAMPLE_PROPERTY_NAME, description = EXAMPLE_PROPERTY_DESCRIPTION, targetClass = String.class)
    public String exampleProperty;

    private String name;

    public SimplePiaPlugin() {

    }

    public SimplePiaPlugin(String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void start() {

    }
}
