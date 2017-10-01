package com.pia.core.exception;

import com.pia.core.plugin.Plugin;

/**
 * Thrown, when the {@link com.pia.core.annotation.Requires} annotation was used
 * on non-{@link Plugin}-field.
 */
public class RequiredObjectIsNoPiaPluginException extends RuntimeException {
    private Plugin sourcePlugin;
    private Class requiredClass;

    public RequiredObjectIsNoPiaPluginException(Plugin sourcePlugin, Class requiredClass) {
        this.sourcePlugin = sourcePlugin;
        this.requiredClass = requiredClass;
    }
}
