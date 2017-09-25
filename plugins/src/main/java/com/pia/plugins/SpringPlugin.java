package com.pia.plugins;

import com.pia.plugin.PiaPlugin;

public class SpringPlugin implements PiaPlugin {

    @Override
    public String getName() {
        return "Spring plugin";
    }

    @Override
    public void start() {
        System.out.println("Spring plugin started");
    }
}
