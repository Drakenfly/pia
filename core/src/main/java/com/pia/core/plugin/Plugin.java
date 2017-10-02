package com.pia.core.plugin;

import com.pia.core.annotation.PluginMetadata;
import com.pia.core.internal.FieldHelper;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * The base class for a new pia core plugin.
 *
 * Requires an {@link PluginMetadata}-annotation on your plugin class to provide additional information.
 * If {@link PluginMetadata} is not present, {@link Plugin} will provide default information.
 */
@PluginMetadata(
        name = "",
        description = "This plugin has no description yet."
)
public abstract class Plugin {
    private Set<Field> annotatedFields;
    private final PluginMetadata pluginMetadata;
    private final String fallbackName;

    public Plugin() {
        this.pluginMetadata = this.getClass().getAnnotation(PluginMetadata.class);
        this.fallbackName = this.getClass().getSimpleName();
    }

    public final Set<Field> getAnnotatedFields () {
        if (this.annotatedFields == null) {
            this.annotatedFields = FieldHelper.getProperties(this.getClass()).keySet();
        }

        return annotatedFields;
    }

    public final String getName() {
        return pluginMetadata.name().equals("") ? this.fallbackName : pluginMetadata.name();
    }

    public final String getDescription() {
        return pluginMetadata.description();
    }

    public abstract void start ();
}
