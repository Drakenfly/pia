package com.pia.plugins;

import com.pia.core.annotation.PluginMetadata;
import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.Property;

@PluginMetadata(name = "Primitive Objects")
public class PrimitiveObjectTypeTestPlugin extends Plugin {
    @Property(name = "Integer Parameter")
    public Integer integerParam;

    @Property(name = "Double Parameter")
    public Double doubleParam;

    @Property(name = "Boolean Parameter")
    public Boolean boolParam;

    @Property(name = "Byte Parameter")
    public Byte byteParam;

    @Property(name = "Character Parameter")
    public Character characterParam;

    @Property(name = "float[][] Parameter")
    public float[][] floatArrayArrayParam;

    @Override
    public void start () {

    }
}
