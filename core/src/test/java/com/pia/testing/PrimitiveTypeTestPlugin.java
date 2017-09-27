package com.pia.testing;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;

import java.util.List;

/**
 * This test-plugin has parameters for all primitives and java.lang.String.
 * The primitives are: boolean, byte, char, double, float, int, long and short
 */
public class PrimitiveTypeTestPlugin extends PiaPlugin{
    @Property(name = "boolean Parameter")
    public boolean aBoolean;

    @Property(name = "byte Parameter")
    public byte aByte;

    @Property(name = "char Parameter")
    public char aChar;

    @Property(name = "double Parameter")
    public double aDouble;

    @Property(name = "float Parameter")
    public float aFloat;

    @Property(name = "int Parameter")
    public int anInt;

    @Property(name = "long Parameter")
    public long aLong;

    @Property(name = "short Parameter")
    public short aShort;

    @Property(name = "String Parameter")
    public String aString;

    @Override
    public String getName () {
        return "Primitive Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
