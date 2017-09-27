package com.pia.testing;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;
import com.pia.testing.beans.User;

import java.net.Socket;

public class ComplexTypeTestPlugin extends PiaPlugin {
    @Property(name = "User Parameter")
    User user;

    @Property(name = "another User Parameter")
    User user2;

    /* Thread also has an empty constructor */
    @Property(name = "Thread Parameter")
    Thread thread;

    /* Socket also has an empty constructor */
    @Property(name = "Socket Parameter")
    Socket socket;

    @Override
    public String getName () {
        return "Complex Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
