package com.pia.core;

import com.pia.core.plugin.PiaPlugin;
import com.pia.core.annotations.Requires;
import com.pia.core.exceptions.RequiredObjectIsNoPiaPluginException;
import com.pia.core.exceptions.RequiredPluginNotAvailableException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PiaPluginServiceTests {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

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

    private static class RequireSpecificPlugin extends PiaPlugin {

        @Requires
        RequiredPlugin plugin;

        @Override
        public String getName() {
            return null;
        }

        @Override
        public void start() {

        }
    }

    private static class RequiredPlugin extends PiaPlugin {

        @Override
        public String getName() {
            return null;
        }

        @Override
        public void start() {

        }
    }

    private static class PluginWithWrongRequiresType extends PiaPlugin {
        @Requires
        private String thisIsNotAPiaPluginObject;

        @Override
        public String getName() {
            return "Wrong type";
        }

        @Override
        public void start() {

        }
    }
}
