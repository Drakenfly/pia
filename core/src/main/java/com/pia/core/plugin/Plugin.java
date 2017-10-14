package com.pia.core.plugin;

import com.pia.core.annotation.PluginMetadata;
import com.pia.core.internal.FieldHelper;
import com.pia.core.internal.PluginHelper;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * The base class for a new pia core plugin.
 *
 * Requires an {@link PluginMetadata}-annotation on your plugin class to provide additional information.
 */
public abstract class Plugin {
    private Set<Field> annotatedFields;

    public Plugin() { }

    public final Set<Field> getAnnotatedFields () {
        if (this.annotatedFields == null) {
            this.annotatedFields = FieldHelper.getProperties(this.getClass()).keySet();
        }

        return annotatedFields;
    }

    public final String getName() {
        return PluginHelper.getPluginName(this.getClass());
    }

    public final String getDescription() {
        return PluginHelper.getPluginDescription(this.getClass());
    }

    public abstract void start ();
}
