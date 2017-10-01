package com.pia.gui;

import com.pia.core.property.DataType;

import java.lang.reflect.InvocationTargetException;

public class HeadingDataType extends DataType{
    private final String heading;

    public HeadingDataType (String heading) {
        super(HeadingDataType.class);
        this.heading = heading;
    }

    @Override
    public String getContentType () throws IllegalAccessException {
        return "Heading " + heading;
    }

    @Override
    protected void readFieldFromObject (Object object) throws IllegalAccessException {

    }

    @Override
    public Object getValue () throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return heading;
    }

    @Override
    public String toString () {
        return heading;
    }

    @Override
    protected void writeFieldToObject (Object object) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    }
}
