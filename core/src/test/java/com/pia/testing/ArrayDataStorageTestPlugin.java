package com.pia.testing;

import com.pia.core.plugin.PiaPlugin;
import com.pia.core.annotations.Property;

public class ArrayDataStorageTestPlugin extends PiaPlugin{
    @Property(name = "Boolean object Array type")
    public Boolean[] aBooleanObjectArray;

    @Property(name = "String Array type")
    public String[] aStringArray;

    @Property(name = "int Array Array type")
    public int[][] aIntArrayArray;

    @Override
    public String getName () {
        return "Plugin with Base Types";
    }

    @Override
    public void start () {

    }
}
