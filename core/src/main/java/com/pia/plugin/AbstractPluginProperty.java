package com.pia.plugin;

public abstract class AbstractPluginProperty<T> implements  PiaPluginProperty<T> {

    private String name;
    private String description;

    public AbstractPluginProperty (String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public abstract T getValue();

    public abstract void setValue(T value);
}
