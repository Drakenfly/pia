package com.pia.testing;

import com.pia.core.annotation.Property;
import com.pia.core.plugin.Plugin;
import com.pia.testing.beans.Person;
import com.pia.testing.beans.User;

public class ComplexTypeTestPlugin extends Plugin {
    @Property(name = "User property")
    public User user;

    @Property(name = "Person property")
    public Person person;

    @Override
    public void start () {

    }
}
