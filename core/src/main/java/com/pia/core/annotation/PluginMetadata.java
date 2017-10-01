package com.pia.core.annotation;

import java.lang.annotation.*;

/**
 * Specifies additional data a plugin must provide.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface PluginMetadata {
    public String name() default "";

    public String description() default "";

    public String version() default "";
}
