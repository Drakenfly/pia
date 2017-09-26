package com.pia.plugins;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;

import java.util.Arrays;
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

    @Property(name = "String[] Parameter")
    public String[] stringArrayParam;

    @Property(name = "Object[] Parameter")
    public Object[] objectArrayParam;

    @Override
    public String getName () {
        return "Type Test Plugin";
    }

    @Property(name = "float[][] Parameter")
    public float[][] floatArrayArrayParam;

    @Override
    public void start () {

    }
}
