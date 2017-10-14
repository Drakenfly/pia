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

    /**
     * Returns the statically available plugin name, based on the {@link PluginMetadata}.
     *
     * @param pluginClass the questionable plugin class
     * @return Returns the plugin name if available and not empty, otherwise returns the simple class name.
     */
    public static String getPluginName(Class<?> pluginClass) {
        PluginMetadata metadata = getPluginMetadata(pluginClass);
        if (metadata != null && !metadata.name().equals("")) {
            return metadata.name();
        }
        return pluginClass.getSimpleName();
    }

    /**
     * Returns the statically available description, based on the {@link PluginMetadata}.
     *
     * @param pluginClass the questionable plugin class
     * @return Returns the plugin description if available and not empty, otherwise null.
     */
    public static String getPluginDescription(Class<?> pluginClass) {
        PluginMetadata metadata = getPluginMetadata(pluginClass);
        if (metadata != null && !metadata.description().equals("")) {
            return metadata.description();
        }
        return null;
    }

    private static PluginMetadata getPluginMetadata(Class<?> pluginClass) {
        return pluginClass.getAnnotation(PluginMetadata.class);
    }
}
