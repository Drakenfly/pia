package com.pia.plugins2;

import com.pia.core.annotation.PluginMetadata;
import com.pia.core.plugin.Plugin;

@PluginMetadata(
        name = "Test-out-of-package",
        description = "Should not be loaded because of different package"
)
public class OutOfScopePlugin extends Plugin {

    @Override
    public void start() {

    }
}
