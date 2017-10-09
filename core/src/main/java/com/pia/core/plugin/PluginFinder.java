package com.pia.core.plugin;

import com.pia.core.internal.PluginHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * A plugin finder implements logic to find plugins on a given strategy.
 */
public abstract class PluginFinder {
    private Logger logger = LoggerFactory.getLogger(PluginFinder.class);

    /**
     * Performs a non-cached search of plugins based on the implemented strategy.
     *
     * @return a list of classes found by the finder.
     *         The classes are of type {@link com.pia.core.plugin.Plugin} and
     *         are annotated with {@link com.pia.core.annotation.PluginMetadata}.
     *         If they do not, they are simply stripped out of the list afterwards.
     */
    abstract protected List<Class<? extends Plugin>> getPluginsSynchronized();

    /**
     * Performs a non-cached search of plugins based on the implemented strategy.
     *
     * @return a list of classes found by the finder.
     *         The classes are of type {@link com.pia.core.plugin.Plugin} and
     *         are annotated with {@link com.pia.core.annotation.PluginMetadata}.
     *         If they do not, they are simply stripped out of the list afterwards.
     */
    public List<Class<? extends Plugin>> findAvailablePlugins() {
        List<Class<? extends Plugin>> plugins = this.getPluginsSynchronized();
        return this.stripIncompletePlugins(plugins);
    }

    /**
     * Iterates through the passes list and removes elements,
     * that do not fulfill all requirements for a valid plugin.
     *
     * The passed list is modified in place.
     *
     * @param pluginClasses The list of classes that should be checked and invalid classes should be removed from
     */
    protected List<Class<? extends Plugin>> stripIncompletePlugins(List<Class<? extends Plugin>> pluginClasses) {
        Iterator<Class<? extends Plugin>> pluginIterator = pluginClasses.iterator();

        while (pluginIterator.hasNext()) {
            Class<? extends Plugin> pluginClass = pluginIterator.next();
            if (!PluginHelper.isPlugin(pluginClass)) {
                pluginIterator.remove();
                logger.debug("Stripped class '" + pluginClass.getSimpleName() + "' from plugins, because it does not fulfill the requirements for a plugin (extends Plugin, is annotated with @PluginMetadata)");
            }
        }

        return pluginClasses;
    }
}
