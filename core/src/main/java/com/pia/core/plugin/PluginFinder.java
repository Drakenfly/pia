package com.pia.core.plugin;

import java.util.List;

public interface PluginFinder {
    List<Class<? extends Plugin>> findAvailablePlugins();
}
