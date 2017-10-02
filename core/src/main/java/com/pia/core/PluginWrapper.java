package com.pia.core;

import com.pia.core.plugin.Plugin;
import com.pia.core.property.DataType;

import java.util.List;

public class PluginWrapper {
    private Class<? extends Plugin> pluginClass;
    private List<DataType> fieldValues;

    public PluginWrapper (Class<? extends Plugin> pluginClass, List<DataType> fieldValues) {
        this.pluginClass = pluginClass;
        this.fieldValues = fieldValues;
    }

    public Class<? extends Plugin> getPluginClass () {
        return pluginClass;
    }

    public void setPluginClass (Class<? extends Plugin> pluginClass) {
        this.pluginClass = pluginClass;
    }

    public List<DataType> getFieldValues () {
        return fieldValues;
    }

    public void setFieldValues (List<DataType> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
