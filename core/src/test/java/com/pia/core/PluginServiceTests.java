package com.pia.core;

import com.pia.core.plugin.PiaPluginProperty;
import com.pia.testing.SimplePiaPlugin;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PluginServiceTests {
    @Test
    public void addPlugin () {
        PluginService pluginService = new PluginService();
        pluginService.addPlugin(new SimplePiaPlugin());
        assertEquals("Plugin service should have 1 plugin", 1, pluginService.getLoadedPlugins().size());
    }

    @Test
    public void propertiesRetrievedViaPluginServiceShouldReflectPluginProperties () {
        PluginService pluginService = new PluginService();
        SimplePiaPlugin plugin = new SimplePiaPlugin();
        pluginService.addPlugin(plugin);
        List<PiaPluginProperty> properties = pluginService.getProperties(plugin);
        // Pre-conditions
        assertEquals("SimplePiaPlugin should have 1 property", properties.size(), 1);
        PiaPluginProperty property = properties.get(0);
        assertEquals("Make sure we are operating on the correct property", property.getName(), SimplePiaPlugin.EXAMPLE_PROPERTY_NAME);

        // Actual test
        String sampleValue1 = "Test 1";
        String sampleValue2 = "Test 2";

        // Setting directly in the plugin
        plugin.exampleProperty = sampleValue1;
        assertEquals(plugin.exampleProperty, sampleValue1);
        // Should reflect the example property too
        assertEquals(property.getValue(), sampleValue1);

        // Setting via retrieved proeprty
        property.setValue(sampleValue2);
        // Should reflect the changed property too
        assertEquals(plugin.exampleProperty, sampleValue2);
        assertEquals(property.getValue(), sampleValue2);
    }
}
