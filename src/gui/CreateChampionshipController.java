package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateChampionshipController {
	
	@FXML private Label label;
	@FXML private TextField textfield;
	
	public CreateChampionshipController() {
		System.out.println("Costruttore invocato");
	}
	
	@FXML 
	public void initialize() {
		System.out.println("inizialize invocato");
    }
	
	@FXML
	protected void handlerAddController(ActionEvent event) {
		label.setText("Team added: "+ textfield.getText());
	}
	
	@FXML
	protected void handlerNextController(ActionEvent event) throws IOException {
		label.setText("Team added: "+ textfield.getText());
		Parent parent = FXMLLoader.load(getClass().getResource("AuctionGK.fxml"));
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		window.setScene(scene2);
		window.show();
	}

}
