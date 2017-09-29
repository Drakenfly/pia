package com.pia.plugins;

import com.pia.core.plugin.PiaPlugin;
import com.pia.core.annotations.Property;

public class PrimitiveObjectTypeTestPlugin extends PiaPlugin{
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

    @Override
    public String getName () {
        return "Primitive Object Type Test Plugin";
    }

    @Property(name = "float[][] Parameter")
    public float[][] floatArrayArrayParam;

    @Override
    public void start () {

    }
}
