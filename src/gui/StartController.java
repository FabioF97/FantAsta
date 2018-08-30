package gui;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import database.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ui.Championship;
import database.DBQuery;

public class StartController {

	@FXML private ChoiceBox<Championship> choice;
	
	@FXML private Button createChampionship;
	
	@FXML private Button loadChampionhsip;
	
	private DBQuery db;
	
	private List<Championship> list;
	
	private ObservableList<Championship> loadList;
	
	@FXML
    public void initialize() {
		try {
			db = new DBQuery();
			list = db.getAllChampionship();
			loadList = FXCollections.observableArrayList();
			for(Championship c: list) {
				loadList.add(c);
			}
			choice.getItems().addAll(loadList);
			choice.setValue(list.get(0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	
	@FXML
	protected void handlerCreateChampionshipController(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("createChampionship.fxml"));
		Parent parent = loader.load();
		CreateChampionshipController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setList(list);
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		window.setScene(scene2);
		window.show();
	}
	
	@FXML
	protected void handlerLoadChampionshipController(ActionEvent event) throws IOException {
		Championship championship = choice.getValue();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowChampionship.fxml"));
		Parent parent = loader.load();
		ShowChampionshipController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setChampionship(championship);
		ctrl.initialize();
		
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		window.setScene(scene2);
		window.show();
	}
	
	@FXML
	public void handlerDeleteChampionshipButton(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete champioship");
		alert.setHeaderText("Are you sure you want to delet " + choice.getValue().getName() + "?");
		alert.setContentText("Make your choice!");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				db.deleteChampionship(choice.getValue().getName());
				loadList.remove(choice.getValue());
				choice.getItems().clear();
				choice.getItems().addAll(loadList);
				choice.setValue(list.get(0));
			}catch (SQLException e) {
				Alert alertError = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Unable to delete the " + choice.getValue().getName());
				alert.setContentText("Ooops, there was an error!");

				alert.showAndWait();
			}
			
		}
	}
}
