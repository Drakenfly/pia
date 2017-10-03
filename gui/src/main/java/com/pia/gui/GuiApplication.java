package com.pia.gui;

import com.pia.core.Generator;
import com.pia.core.PluginContext;
import com.pia.core.plugin.ClassPathPluginFinder;
import com.pia.gui.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class GuiApplication extends Application {
    private static PluginContext pluginContext;

    public static void main (String[] args) {
        Generator generator = new Generator();
        generator.addPluginFinder(new ClassPathPluginFinder("com.pia.plugins"));
        generator.addPluginFolder(new File("plugins"));
        pluginContext = generator.getPluginService(generator.getPlugins());

        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/main.fxml"));

        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.setPluginContext(pluginContext);
        controller.populateTreeWithPlugins();

        primaryStage.setTitle("Pia - Poject Initialization Assistant");
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();
    }
}
