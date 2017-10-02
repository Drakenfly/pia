package com.pia.gui;

import com.pia.core.Generator;
import com.pia.core.PluginService;
import com.pia.core.plugin.ListBasedPluginFinder;
import com.pia.core.plugin.Plugin;
import com.pia.gui.controllers.MainController;
import com.pia.plugins.PrimitiveObjectTypeTestPlugin;
import com.pia.plugins.SpringPlugin;
import com.pia.plugins.TestPlugin;
import com.pia.plugins.TypeTestPlugin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class GuiApplication extends Application {
    private static PluginService pluginService;

    public static void main (String[] args) {
        List<Class<? extends Plugin>> plugins = new LinkedList<>();

        plugins.add(SpringPlugin.class);
        plugins.add(TestPlugin.class);
        plugins.add(TypeTestPlugin.class);
        plugins.add(PrimitiveObjectTypeTestPlugin.class);

        Generator generator = new Generator();
        generator.addPluginFinder(new ListBasedPluginFinder(plugins));
        pluginService = generator.getPluginService(generator.getPlugins());

        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/main.fxml"));

        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.setPluginService(pluginService);
        controller.populateTreeWithPlugins();

        primaryStage.setTitle("Pia - Poject Initialization Assistant");
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();
    }
}
