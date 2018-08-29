package gui;

import java.io.IOException;
import java.sql.SQLException;

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
import javafx.stage.Stage;
import javafx.stage.Window;
import gui.AlertHelper;
import ui.Championship;
import ui.Club;
import ui.User;

public class CreateChampionshipController {
	
	@FXML private Label labelChampionship;
	@FXML private Label labelUser;
	@FXML private TextField textfieldName;
	@FXML private TextField textfieldBudget;
	@FXML private TextField textfieldUsername;
	@FXML private TextField textfieldClub;
	@FXML private Button buttonChampionship;
	@FXML private Button buttonUser;
	@FXML private Button buttonNext;
	
	protected Championship championship;
	private MainController main;
	
	private DBQuery db;
	
	
	public DBQuery getDb() {
		return db;
	}

	public void setDb(DBQuery db) {
		this.db = db;
	}
	
	@FXML
	public void handlerButtonChampionship() {
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
		championship = new Championship(textfieldName.getText(), Integer.parseInt(textfieldBudget.getText()));
		System.out.println(championship);
		labelChampionship.setText("Championship created: "+ textfieldName.getText());
		textfieldName.editableProperty().setValue(false);
		textfieldBudget.editableProperty().setValue(false);
	}
	
	//textfieldName.getText().isEmpty()
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
	
	@FXML
	protected void handlerNextController(ActionEvent event) throws IOException {
		if(championship == null || championship.getCompetitors().size() < 2) {
			Window owner = buttonUser.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please create the championship and add at least two users!");
			return;
		}
		System.out.println(championship);
		//main.setChampionship(championship);
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
		
		window.setScene(scene2);
		window.show();
	}
/*
	public void init(MainController mainController) {
		main = mainController;
		
	}*/

}
