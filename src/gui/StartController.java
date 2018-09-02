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

/**
 * Controller of the home page.
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class StartController {

	/** ChoiceBox used to show the championships */
	@FXML private ChoiceBox<Championship> choice;
	
	/** Button used to create a new championship */
	@FXML private Button createChampionship;
	
	/** Button used to load an already existing championship */
	@FXML private Button loadChampionhsip;
	
	/** Link to the database */
	private DBQuery db;
	
	/** List containing all the already existing championships */ 
	private List<Championship> list;
	
	/** ObservableList loaded in the choiceBox 	containing the championships */
	private ObservableList<Championship> loadList;
	
	/**
	 * Initializes the home page.
	 * Instantiates the DBQuery and populates the list with all the championships.
	 */
	@FXML
    public void initialize() {
		try {
			db = new DBQuery();
			list = db.getAllChampionship();
			loadList = FXCollections.observableArrayList();
			for(Championship c: list) {
				if (c.getCompetitors().size() == 0 || !(c.checkClub())) {
					db.deleteChampionship(c.getName());
				}else {
					loadList.add(c);
				}														
			}
			if(list.size() > 0) {
				choice.getItems().addAll(loadList);
				choice.setValue(list.get(0));
			}
		} catch (SQLException e) {
			System.out.println("No championships found");
		}
    }
	
	/**
	 * Fired by the button createChampionship.
	 * Initializes the page to create the championship.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handlerCreateChampionshipController(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("createChampionship.fxml"));
		Parent parent = loader.load();
		CreateChampionshipController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setList(list);
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene2.getStylesheets().add(getClass().getResource("Start.css").toExternalForm());
		
		window.setScene(scene2);
		window.show();
	}
	
	/**
	 * Fired by the button loadChampionship.
	 * Initializes the page showing the selected championship in the ChoiceBox choice.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handlerLoadChampionshipController(ActionEvent event) throws IOException {
		Championship championship = choice.getValue();
		if(championship == null)
			return;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowChampionship.fxml"));
		Parent parent = loader.load();
		ShowChampionshipController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setChampionship(championship);
		ctrl.initialize();
		
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene2.getStylesheets().add(getClass().getResource("Start.css").toExternalForm());
		
		window.setScene(scene2);
		window.show();
	}
	
	/**
	 * Fired by the button deleteChampionshipButton.
	 * Delete the championship selected in the ChoiceBox choice.
	 * @param event
	 */
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
