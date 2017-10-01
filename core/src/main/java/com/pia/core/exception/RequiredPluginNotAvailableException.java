package com.pia.core.exception;

import com.pia.core.plugin.Plugin;

/**
 * Thrown, when a plugin requires another plugin,
 * that cannot be found in the list of loaded plugins.
 */
public class RequiredPluginNotAvailableException extends RuntimeException {
    private Plugin sourcePlugin;
    private Class requiredPluginClass;

    public RequiredPluginNotAvailableException(Plugin sourcePlugin, Class requiredPluginClass) {
        this.sourcePlugin = sourcePlugin;
        this.requiredPluginClass = requiredPluginClass;
    }

    /**
     * @return The source plugin that requested another plugin.
     */
    public Plugin getSourcePlugin() {
        return sourcePlugin;
    }

    /**
     * @return The class object of the required plugin.
     */
    public Class getRequiredPluginClass() {
        return requiredPluginClass;
    }

    @Override
    public String toString() {
        return "Plugin '" + requiredPluginClass.getCanonicalName() + "' required by '" + sourcePlugin.getClass().getCanonicalName() + "' was not found in the list of loaded plugins.";
    }
}
