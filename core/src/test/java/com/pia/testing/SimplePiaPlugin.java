package com.pia.testing;

import com.pia.core.plugin.PiaPlugin;
import com.pia.core.annotations.Property;

public class SimplePiaPlugin extends PiaPlugin {

    public static final String EXAMPLE_PROPERTY_NAME = "example property";
    public static final String EXAMPLE_PROPERTY_DESCRIPTION = "example property";

    @Property(name = EXAMPLE_PROPERTY_NAME, description = EXAMPLE_PROPERTY_DESCRIPTION)
    public String exampleProperty;

    private String name;

    public SimplePiaPlugin () {

    }

    public SimplePiaPlugin (String name) {
        this.name = name;
    }

    @Override
    public String getName () {
        return this.name;
    }

    @Override
    public void start () {

    }
}
