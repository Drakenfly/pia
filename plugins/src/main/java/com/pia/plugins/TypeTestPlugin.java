package com.pia.plugins;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;

import java.util.List;

public class TypeTestPlugin implements PiaPlugin{
    @Property(name = "String Parameter")
    public String stringParam;

    @Property(name = "Int Parameter")
    public int intParam;

    @Property(name = "String Parameter")
    public double doubleParam;

    @Property(name = "String Parameter")
    public List<String[]> listOfStringArraysParam;

    @Property(name = "String Parameter")
    public float[] floatArrayParam;

    @Override
    public String getName () {
        return "Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
