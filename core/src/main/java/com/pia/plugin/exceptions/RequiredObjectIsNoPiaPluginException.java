package com.pia.plugin.exceptions;

import com.pia.plugin.PiaPlugin;

/**
 * Thrown, when the {@link com.pia.plugin.annotations.Requires} annotation was used
 * on non-{@link com.pia.plugin.PiaPlugin}-field.
 */
public class RequiredObjectIsNoPiaPluginException extends RuntimeException {
    private PiaPlugin sourcePlugin;
    private Class requiredClass;

    public RequiredObjectIsNoPiaPluginException(PiaPlugin sourcePlugin, Class requiredClass) {
        this.sourcePlugin = sourcePlugin;
        this.requiredClass = requiredClass;
    }
}
