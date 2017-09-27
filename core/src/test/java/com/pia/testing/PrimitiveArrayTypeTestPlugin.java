package com.pia.testing;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;

/**
 * This test-plugin has parameters for all primitive arrays and java.lang.String[].
 * The primitives are: boolean, byte, char, double, float, int, long and short
 */
public class PrimitiveArrayTypeTestPlugin extends PiaPlugin {
    @Property(name = "boolean[] Parameter")
    public boolean[] aBooleanArray;

    @Property(name = "byte[] Parameter")
    public byte[] aByteArray;

    @Property(name = "char[] Parameter")
    public char[] aCharArray;

    @Property(name = "double[] Parameter")
    public double[] aDoubleArray;

    @Property(name = "float[] Parameter")
    public float[] aFloatArray;

    @Property(name = "int[] Parameter")
    public int[] anIntArray;

    @Property(name = "long[] Parameter")
    public long[] aLongArray;

    @Property(name = "short[] Parameter")
    public short[] aShortArray;

    @Property(name = "String[] Parameter")
    public String[] aStringArray;

    @Override
    public String getName () {
        return "Primitive Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
