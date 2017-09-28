package com.pia.testing;

import com.pia.core.properties.basetypes.CharacterType;
import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;

public class SimpleDataStorageTestPlugin extends PiaPlugin{
    @Property(name = "Boolean object type")
    public Boolean aBooleanObject;

    @Property(name = "Int object type")
    public Integer aIntObject;

    @Property(name = "String type")
    public String aString;

    @Property(name = "Character type")
    public Character aCharObject;

    @Property(name = "boolean type")
    public boolean aBoolean;

    @Property(name = "char type")
    public char aChar;

    @Property(name = "double type")
    public double aDouble;

    @Property(name = "int type")
    public int aInt;

    @Override
    public String getName () {
        return "Plugin with Base Types";
    }

    @Override
    public void start () {

    }
}
