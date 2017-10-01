package com.pia.testing;

import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.Property;

public class BaseTypeTestPlugin extends Plugin {
    /* All primitive Objects */
    @Property(name = "Boolean object type")
    public Boolean aBooleanObject;
    
    @Property(name = "Byte object type")
    public Byte aByteObject;

    @Property(name = "Char object type")
    public Character aCharObject;

    @Property(name = "Double object type")
    public Double aDoubleObject;

    @Property(name = "Float object type")
    public Float aFloatObject;

    @Property(name = "Int object type")
    public Integer aIntObject;

    @Property(name = "Long object type")
    public Long aLongObject;

    @Property(name = "Short object type")
    public Short aShortObject;
    
    @Property(name = "String type")
    public String aString;

    /* All primitive Objects */
    @Property(name = "boolean type")
    public boolean aBoolean;

    @Property(name = "byte type")
    public byte aByte;

    @Property(name = "char type")
    public char aChar;

    @Property(name = "double type")
    public double aDouble;

    @Property(name = "float type")
    public float aFloat;

    @Property(name = "int type")
    public int aInt;

    @Property(name = "long type")
    public long aLong;

    @Property(name = "short type")
    public short aShort;

    @Override
    public void start () {

    }
}
