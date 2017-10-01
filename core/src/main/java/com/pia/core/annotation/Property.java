package com.pia.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to declare properties that should be editable by the user interface.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
    /**
     * @return a more readable display name that may be used by the UI
     */
    String name ();

    /**
     * @return a description to clarify the usage of the property
     */
    String description () default "";

    /**
     * @return true if the annotated property may be set to null if set to, false if it must not be null
     */
    boolean allowNull () default true;
}
