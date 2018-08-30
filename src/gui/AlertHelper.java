package gui;

import javafx.scene.control.Alert;

import javafx.stage.Window;

/**
 * This helper class has only one method that helps to show pop up
 * @author Fabio Fontana
 *
 */
public class AlertHelper {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
	
}
