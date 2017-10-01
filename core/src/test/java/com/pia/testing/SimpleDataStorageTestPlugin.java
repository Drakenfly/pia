package com.pia.testing;

import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.Property;

public class SimpleDataStorageTestPlugin extends Plugin {
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
    public void start () {

    }
}
