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

public class PluginContextTests {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void addPlugin () {
        PluginContext pluginContext = new PluginContext();
        pluginContext.addPlugin(new SimplePiaPlugin());
        assertEquals("PluginMetadata service should have 1 plugin", 1, pluginContext.getLoadedPlugins().size());
    }

    @Test()
    public void wrongUsageOfRequiresShouldThrowException() {
        PluginContext pluginContext = new PluginContext();
        pluginContext.addPlugin(new PluginWithWrongRequiresType());

        expectedException.expect(RequiredObjectIsNoPiaPluginException.class);
        pluginContext.resolveRequirements();
    }

    @Test()
    public void requiredPluginCannotBeFound() {
        PluginContext pluginContext = new PluginContext();
        pluginContext.addPlugin(new RequireSpecificPlugin());

        expectedException.expect(RequiredPluginNotAvailableException.class);
        pluginContext.resolveRequirements();
    }

    @Test()
    public void requiredPluginCanBeFoundWorks() {
        PluginContext pluginContext = new PluginContext();
        pluginContext.addPlugin(new RequireSpecificPlugin());
        pluginContext.addPlugin(new RequiredPlugin());
        pluginContext.resolveRequirements();
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
