package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TestApp extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
		Scene scene1 = new Scene(root);
		scene1.getStylesheets().add(getClass().getResource("Start.css").toExternalForm());
		primaryStage.getIcons().add(new Image("file:images/icon1.png"));
		primaryStage.setTitle("FantAsta");
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
