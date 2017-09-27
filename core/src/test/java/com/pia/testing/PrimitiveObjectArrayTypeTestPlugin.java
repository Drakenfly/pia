package com.pia.testing;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;

/**
 * This test-plugin has parameters for all primitive object arrays.
 * The primitives are: boolean, byte, char, double, float, int, long and short
 */
public class PrimitiveObjectArrayTypeTestPlugin extends PiaPlugin {
    @Property(name = "Boolean[] Parameter")
    public Boolean[] aBooleanArray;

    @Property(name = "Byte[] Parameter")
    public Byte[] aByteArray;

    @Property(name = "Char[] Parameter")
    public Character[] aCharacterArray;

    @Property(name = "Double[] Parameter")
    public Double[] aDoubleArray;

    @Property(name = "Float[] Parameter")
    public Float[] aFloatArray;

    @Property(name = "Int[] Parameter")
    public Integer[] anIntegerArray;

    @Property(name = "Long[] Parameter")
    public Long[] aLongArray;

    @Property(name = "Short[] Parameter")
    public Short[] aShortArray;

    @Override
    public String getName () {
        return "Primitive Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
