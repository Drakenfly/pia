package com.pia.core;

import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.Requires;
import com.pia.core.exception.RequiredObjectIsNoPiaPluginException;
import com.pia.core.exception.RequiredPluginNotAvailableException;
import com.pia.testing.SimplePiaPlugin;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class PluginServiceTests {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void addPlugin () {
        PluginService pluginService = new PluginService();
        pluginService.addPlugin(new SimplePiaPlugin());
        assertEquals("PluginMetadata service should have 1 plugin", 1, pluginService.getLoadedPlugins().size());
    }

    @Test()
    public void wrongUsageOfRequiresShouldThrowException() {
        PluginService pluginService = new PluginService();
        pluginService.addPlugin(new PluginWithWrongRequiresType());

        expectedException.expect(RequiredObjectIsNoPiaPluginException.class);
        pluginService.resolveRequirements();
    }

    @Test()
    public void requiredPluginCannotBeFound() {
        PluginService pluginService = new PluginService();
        pluginService.addPlugin(new RequireSpecificPlugin());

        expectedException.expect(RequiredPluginNotAvailableException.class);
        pluginService.resolveRequirements();
    }

    @Test()
    public void requiredPluginCanBeFoundWorks() {
        PluginService pluginService = new PluginService();
        pluginService.addPlugin(new RequireSpecificPlugin());
        pluginService.addPlugin(new RequiredPlugin());
        pluginService.resolveRequirements();
    }

    /* Helper plugin classes */

    private static class RequireSpecificPlugin extends Plugin {

        @Requires
        RequiredPlugin plugin;

        @Override
        public void start() {

        }
    }

    private static class RequiredPlugin extends Plugin {

        @Override
        public void start() {

        }
    }

    private static class PluginWithWrongRequiresType extends Plugin {
        @Requires
        private String thisIsNotAPiaPluginObject;

        @Override
        public void start() {

        }
    }
}
