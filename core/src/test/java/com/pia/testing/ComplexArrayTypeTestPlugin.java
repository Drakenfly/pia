package com.pia.testing;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;
import com.pia.testing.beans.User;

import java.net.Socket;

public class ComplexArrayTypeTestPlugin extends PiaPlugin {
    @Property(name = "User Parameter")
    User[] user;

    /* Thread also has an empty constructor */
    @Property(name = "Thread Parameter")
    Thread[] thread;

    /* Socket also has an empty constructor */
    @Property(name = "Socket Parameter")
    Socket[] socket;

    @Override
    public String getName () {
        return "Complex Array Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
