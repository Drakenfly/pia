package com.pia.gui.popup;

import javafx.scene.control.Alert;

public class PopupManager {
    public static void showAlert(String title, String header, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        displayAlert(alert, title, header, text);

    }
    public static void showError(String title, String header, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        displayAlert(alert, title, header, text);
    }
    private static void displayAlert(Alert alert, String title, String header, String text) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
    }
}
