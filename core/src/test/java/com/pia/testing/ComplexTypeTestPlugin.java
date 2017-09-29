package com.pia.testing;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;
import com.pia.testing.beans.User;

public class ComplexTypeTestPlugin extends PiaPlugin{
    @Property(name = "User property")
    public User user;

    @Override
    public String getName () {
        return "Complex Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
