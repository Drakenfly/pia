package com.pia.core.plugin;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * A simple plugin finder that works on a provided list of plugins.
 */
public class ListBasedPluginFinder implements PluginFinder {

    private List<Class<? extends Plugin>> plugins;

    /**
     * Creates a new instance with a provided list of plugins.
     *
     * @param pluginList a list of plugins, must not be null
     */
    public ListBasedPluginFinder(@NotNull List<Class<? extends Plugin>> pluginList) {
        this.plugins = pluginList;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<Class<? extends Plugin>> findAvailablePlugins() {
        return this.plugins;
    }
}
