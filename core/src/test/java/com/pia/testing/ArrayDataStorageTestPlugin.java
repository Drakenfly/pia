package com.pia.testing;

import com.pia.core.annotation.PluginMetadata;
import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.Property;

@PluginMetadata
public class ArrayDataStorageTestPlugin extends Plugin {
    @Property(name = "Boolean object Array type")
    public Boolean[] aBooleanObjectArray;

    @Property(name = "String Array type")
    public String[] aStringArray;

    @Property(name = "int Array Array type")
    public int[][] aIntArrayArray;

    @Override
    public void start () {

    }
}
