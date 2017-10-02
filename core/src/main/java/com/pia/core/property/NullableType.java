package com.pia.core.property;

public interface NullableType {
    boolean getValueIsNull ();

    void setValueIsNull (boolean valueIsNull);

    boolean isRequired ();
}
