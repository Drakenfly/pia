package com.pia.plugins;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;

import java.util.List;

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
