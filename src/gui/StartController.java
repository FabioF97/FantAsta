package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import database.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ui.Championship;

public class StartController {

	@FXML private ChoiceBox<Championship> choice;
	
	@FXML private Button createChampionship;
	
	@FXML private Button loadChampionhsip;
	
	private DBQuery db;
	
	private List<Championship> list;
	
	@FXML
    public void initialize() {
		try {
			db = new DBQuery();
			list = db.getAllChampionship();
			ObservableList<Championship> loadList = FXCollections.observableArrayList();
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
}
