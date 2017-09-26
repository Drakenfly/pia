package com.pia.plugins;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;

import java.util.List;

public class TypeTestPlugin extends PiaPlugin{
    @Property(name = "String Parameter")
    public String stringParam;

    @Property(name = "int Parameter")
    public int intParam;

    @Property(name = "double Parameter")
    public double doubleParam;

    @Property(name = "List<String[]> Parameter")
    public List<String[]> listOfStringArraysParam;

    @Property(name = "float[] Parameter")
    public float[] floatArrayParam;

    @Override
    public String getName () {
        return "Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
