package com.pia.plugins;

import com.pia.core.plugin.PiaPlugin;
import com.pia.core.annotations.Property;

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

    @Property(name = "float[][] Parameter")
    public float[][] floatArrayArrayParam;

    @Override
    public String getName () {
        return "Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
