package com.pia.core.exception;

import com.pia.core.plugin.PiaPlugin;

/**
 * Thrown, when the {@link com.pia.core.annotation.Requires} annotation was used
 * on non-{@link com.pia.core.plugin.PiaPlugin}-field.
 */
public class RequiredObjectIsNoPiaPluginException extends RuntimeException {
    private PiaPlugin sourcePlugin;
    private Class requiredClass;

    public RequiredObjectIsNoPiaPluginException(PiaPlugin sourcePlugin, Class requiredClass) {
        this.sourcePlugin = sourcePlugin;
        this.requiredClass = requiredClass;
    }
}
