package com.pia.testing;

import com.pia.core.annotation.Property;
import com.pia.core.plugin.PiaPlugin;
import com.pia.testing.beans.Person;
import com.pia.testing.beans.User;

public class ComplexTypeTestPlugin extends PiaPlugin {
    @Property(name = "User property")
    public User user;

    @Property(name = "Person property")
    public Person person;

    @Override
    public String getName () {
        return "Complex Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
