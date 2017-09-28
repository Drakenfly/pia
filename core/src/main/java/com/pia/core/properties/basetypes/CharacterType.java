package com.pia.core.properties.basetypes;

import com.pia.core.properties.BaseType;

import java.lang.reflect.Field;

public class CharacterType extends BaseType<Character> {
    private Character value;

    public CharacterType (Field ownField) {
        super(ownField);
    }

    @Override
    protected void setDefaultValue () {
        value = '\u0000';
    }

    public CharacterType (Class ownClass) {
        super(ownClass);
    }

    @Override
    public String getContentType () {
        return "Character";
    }

    @Override
    public String toString () {
        return value.toString();
    }

    @Override
    public Character getValue () {
        return value;
    }

    @Override
    public void setValue (Character value) {
        this.value = value;
    }

    @Override
    public void parseValue (String value) {
        if (value.length() > 0)
            this.value = value.charAt(0);
        else
            this.value = 0;
    }
}
