package com.pia.testing;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;

/**
 * This test-plugin has parameters for all primitive's corresponding objects.
 * Note that java.lang.String is NOT included, since it's handled as a primitive.
 */
public class PrimitiveObjectTypeTestPlugin extends PiaPlugin {
    @Property(name = "Boolean Parameter")
    public Boolean aBoolean;

    @Property(name = "Byte Parameter")
    public Byte aByte;

    @Property(name = "Character Parameter")
    public Character aCharacter;

    @Property(name = "Double Parameter")
    public Double aDouble;

    @Property(name = "Float Parameter")
    public Float aFloat;

    @Property(name = "Integer Parameter")
    public Integer anInteger;

    @Property(name = "Long Parameter")
    public Long aLong;

    @Property(name = "Short Parameter")
    public Short aShort;

    @Override
    public String getName () {
        return "Primitive Type Test Plugin";
    }

    @Override
    public void start () {

    }
}
