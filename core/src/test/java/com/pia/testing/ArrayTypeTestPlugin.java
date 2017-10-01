package com.pia.testing;

import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.Property;
import com.pia.testing.beans.User;

public class ArrayTypeTestPlugin extends Plugin {
    @Property(name = "int array")
    public int[] intArray;

    @Property(name = "Integer object array")
    public Integer[] intObjectArray;

    @Property(name = "User array")
    public User[] userArray;

    @Property(name = "Integer object array array")
    public Integer[][] intObjectArrayArray;

    @Property(name = "User array array")
    public User[][] userArrayArray;

    @Property(name = "Integer object array array array array")
    public Integer[][][][] intObjectArrayArrayArrayArray;

    @Override
    public void start () {

    }
}
