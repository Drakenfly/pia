package com.pia.core.plugin;

import java.util.Arrays;
import java.util.List;

public class ListBasedPluginFinder implements PluginFinder {

    private List<Class<? extends Plugin>> plugins;

    public ListBasedPluginFinder(List<Class<? extends Plugin>> pluginList) {
        this.plugins = pluginList;
    }

    @Override
    public List<Class<? extends Plugin>> findAvailablePlugins() {
        return this.plugins;
    }
}
