package com.pia.gui;

import com.pia.core.Generator;

import com.pia.core.PluginService;
import com.pia.core.properties.DataType;
import com.pia.gui.controllers.MainController;
import com.pia.plugin.PiaPlugin;
import com.pia.plugin.PiaPluginProperty;
import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.File;
import java.lang.reflect.Field;

public class GuiApplication extends Application{
    private static PluginService pluginService;

    public static void main(String[] args) {
        Generator generator = new Generator(new File("plugins"));
        pluginService = generator.getPluginService();

        for (PiaPlugin plugin: pluginService.getPlugins()) {
            System.out.println("Spying plugin " + plugin.getName());
            for (Field f : plugin.getAnnotatedFields()) {
                ClassSpy.spy(f.getType());
                FieldSpy.spy(f);
                try {
                    DataType obj = DataType.getDataType(f);
                    System.out.println(obj);
                } catch (InvalidArgumentException e) {
                    e.printStackTrace();
                }
            }
        }

        generator.start();
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
