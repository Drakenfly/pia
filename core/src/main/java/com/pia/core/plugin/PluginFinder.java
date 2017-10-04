package com.pia.core.plugin;

import java.util.List;

// TODO: create abstract class that calls an internal findAvailablePlugins() and checks if the plugins are of type Plugin and have PluginMetadata attached

/**
 * A plugin finder implements logic to find plugins on a given strategy.
 */
public interface PluginFinder {
    /**
     * Performs a non-cached search of plugins based on the implemented strategy
     *
     * @return a list of classes found by the finder.
     *         The classes are of type {@link com.pia.core.plugin.Plugin} and
     *         are annotated with {@link com.pia.core.annotation.PluginMetadata}
     */
    List<Class<? extends Plugin>> findAvailablePlugins();
}
