package com.pia.core.internal;

import com.pia.core.annotation.PluginMetadata;
import com.pia.core.plugin.Plugin;

/**
 * Helper class to query common plugin related information.
 */
public class PluginHelper {
    /**
     * Checks if the passed class extends {@link Plugin} and is annotated with {@link PluginMetadata}.
     *
     * @param questionableClass the class that should be checked
     * @return true if all described conditions are met, false otherwise
     */
    public static boolean isPlugin(Class questionableClass) {
        return (
                Plugin.class.isAssignableFrom(questionableClass) &&
                questionableClass.getAnnotation(PluginMetadata.class) != null
        );
    }
}
