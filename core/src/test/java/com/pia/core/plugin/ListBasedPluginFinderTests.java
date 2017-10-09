package com.pia.core.plugin;

import com.pia.core.internal.PluginHelper;
import com.pia.core.plugin.testPlugins.ValidPlugin1;
import com.pia.core.plugin.testPlugins.ValidPlugin2;
import com.pia.core.plugin.testPlugins.InvalidPluginNoMetadata;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListBasedPluginFinderTests {

    @Test
    public void shouldStripInvalidPlugins() {
        List<Class<? extends Plugin>> plugins = new ArrayList<>();
        plugins.add(ValidPlugin1.class);
        plugins.add(ValidPlugin2.class);
        plugins.add(InvalidPluginNoMetadata.class);

        // CHECK pre-conditions
        assert(PluginHelper.isPlugin(ValidPlugin1.class));
        assert(PluginHelper.isPlugin(ValidPlugin2.class));
        assert(!PluginHelper.isPlugin(InvalidPluginNoMetadata.class));

        ListBasedPluginFinder finder = new ListBasedPluginFinder(plugins);

        assertEquals(finder.findAvailablePlugins().size(), 2);
        assertEquals(finder.findAvailablePlugins().get(0), ValidPlugin1.class);
        assertEquals(finder.findAvailablePlugins().get(1), ValidPlugin2.class);
    }
}
