package com.pia.plugin;

public interface PiaPlugin {
    PiaPlugin getInstance();
    String getName();
    void start();
}
