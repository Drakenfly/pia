package com.pia.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * May be used to automatically inject other plugin instances.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Requires {
    /**
     * @return the version matching string
     */
    public String versionString() default "";

    /**
     * @return true if the required plugin is optional and may be skipped (set to null), else false
     */
    public boolean optional() default false;
}
