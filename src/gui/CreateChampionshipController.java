package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import database.DBQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import gui.AlertHelper;
import ui.Championship;
import ui.Club;
import ui.User;

/**
 * Controller of the page for creation of a championship.
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class CreateChampionshipController {
	
	/** Label used to show the championship just created */
	@FXML private Label labelChampionship;
	/** Label used to show the user just added to the championship */
	@FXML private Label labelUser;
	/** TextField used to get the championship's name */
	@FXML private TextField textfieldName;
	/** TextField used to get the championship's budget */
	@FXML private TextField textfieldBudget;
	/** TextField used to get the user's name */
	@FXML private TextField textfieldUsername;
	/** TextField used to get the name of the user's club */
	@FXML private TextField textfieldClub;
	/** Button used to create the championship */
	@FXML private Button buttonChampionship;
	/** Button used to add a user */
	@FXML private Button buttonUser;
	/** Button used to start the auction */
	@FXML private Button buttonNext;
	
	/** Used to save the championship just created */
	protected Championship championship;
	
	/** List containing already existing championships */
	private List<Championship> list;
	
	/** Link to the database */
	private DBQuery db;
	
	/**
	 * Gets the link to the database.
	 * @return db
	 */
	public DBQuery getDb() {
		return db;
	}

	/**
	 * Sets the link to the database.
	 * @param db
	 */
	public void setDb(DBQuery db) {
		this.db = db;
	}
	
	/**
	 * Gets the List already existing championships. 
	 * @return list 
	 */
	public List<Championship> getList() {
		return list;
	}
	
	/**
	 * Sets the List already existing championships. 
	 * @param list
	 */
	public void setList(List<Championship> list) {
		this.list = list;
	}

	/**
	 * Fired by the Button buttonChampionship.
	 * Creates the championship using the parameters taken from the TextFields texfieldName and textfieldBudget.
	 * @param event
	 */
	@FXML
	public void handlerButtonChampionship(ActionEvent event) {
		Window owner = buttonChampionship.getScene().getWindow();
		if (textfieldName.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter the championship's name.");
			return;
		}
		if (textfieldBudget.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter the championship's budget.");
			return;
		}
		try{
			Integer.parseInt(textfieldBudget.getText());
		} catch (NumberFormatException e) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Budget insertion not allowed.");
			return;
		}
		if(Character.isDigit(textfieldName.getText().charAt(0))) {
			System.out.println("The championship name can not start with a digit");
			return;
		}
		championship = new Championship(textfieldName.getText(), Integer.parseInt(textfieldBudget.getText()));
		for(Championship c: list) {
			if(c.getName().equals(championship.getName())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("There is another championship with the name " + c.getName());
				alert.showAndWait();
				return;
			}
		}
		labelChampionship.setText("Championship created: "+ textfieldName.getText());
		textfieldName.editableProperty().setValue(false);
		textfieldBudget.editableProperty().setValue(false);
		buttonChampionship.setDisable(true);
		
	}
	
	/**
	 * Fired by the Button buttonUser.
	 * Adds the user to the championship using the parameters taken from the TextFields texfieldUserame and textfieldClub.
	 * @param event
	 */
	@FXML
	protected void handlerButtonUser(ActionEvent event) {
		Window owner = buttonUser.getScene().getWindow();
		if (championship == null) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please create the championship.");
			return;
		}
		if (textfieldUsername.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter the user's name.");
			return;
		}
		if (textfieldClub.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter the club's name.");
			return;
		}
		Club club = new Club(textfieldClub.getText());
		User user = new User(textfieldUsername.getText(), club, Integer.parseInt(textfieldBudget.getText()));
		championship.addCompetitor(user);
		
		System.out.println(user);
		labelUser.setText("User added: "+ textfieldUsername.getText());
		textfieldUsername.clear();
		textfieldClub.clear();
	}
	
	/**
	 * Fired by the Button buttonNext.
	 * Initializes the auction start page.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handlerNextController(ActionEvent event) throws IOException {
		if(championship == null || championship.getCompetitors().size() < 2) {
			Window owner = buttonUser.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please create the championship and add at least two users!");
			return;
		}
		System.out.println(championship);
		try {
			db.championshipInsert(championship.getName(), championship.getDate(), championship.getnComp(), championship.getBudget());
		}catch (SQLException e) {
			Stage owner = (Stage) ((Node) event.getSource()).getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Problem with the comunication with the database.");
			return;
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AuctionGK.fxml"));
		Parent parent = loader.load();
		AuctionGKController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setChampionship(championship);
		ctrl.initialize();
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene2.getStylesheets().add(getClass().getResource("Start.css").toExternalForm());
		
		window.setScene(scene2);
		window.show();
	}

}
