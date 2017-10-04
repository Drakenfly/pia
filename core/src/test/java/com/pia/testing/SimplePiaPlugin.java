package com.pia.testing;

import com.pia.core.annotation.PluginMetadata;
import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.Property;

@PluginMetadata
public class SimplePiaPlugin extends Plugin {

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
    public void start () {

    }
}
