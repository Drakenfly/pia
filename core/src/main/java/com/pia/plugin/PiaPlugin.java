package com.pia.plugin;

import com.pia.plugin.annotations.Property;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class PiaPlugin {
    private List<PiaPluginProperty> properties;

    public PiaPlugin() {

    }

    public List<PiaPluginProperty> getProperties() {
        if (this.properties == null) {
            properties = new ArrayList<>();
            // Save "this" to variable - usage in inner class!
            PiaPlugin instance = this;

            for(Field field : instance.getClass().getDeclaredFields()){

                Property param = field.getAnnotation(Property.class);

                if (param != null) {
                    field.setAccessible(true);
                    PiaPluginProperty property = new AbstractPluginProperty(param.name(), param.description()) {
                        @Override
                        public Object getValue() {
                            try {
                                return field.get(instance);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        public void setValue(Object value) {
                            try {
                                field.set(instance, value);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    properties.add(property);
                }
            }
        }

        return properties;
    }

    public abstract String getName();
    public abstract void start();
}
