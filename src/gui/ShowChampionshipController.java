package gui;

import java.util.List;

import database.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import ui.Championship;
import ui.Player;
import ui.Striker;
import ui.User;

public class ShowChampionshipController {

	@FXML TableView<Player> tab;
	@FXML ChoiceBox<User> team;
	@FXML Label teamLabel;
	@FXML Label budgetLabel;

	private DBQuery db;
	private Championship championship;
	private User user;
	private ObservableList<Player> list;
	
	@FXML 
	public void initialize() {
		if(db != null) {
		list = FXCollections.observableArrayList();
		ObservableList<User> users = FXCollections.observableArrayList();
		List<User> list1 = championship.getCompetitors();
		users.addAll(list1);
		team.getItems().addAll(users);
		team.setValue(users.get(0));
		user = team.getValue();
		teamLabel.setText("Team: "+ user.getClub().getName());
		budgetLabel.setText("Budget: " + user.getBudget());
		TableColumn<Player,String> positionColumn = new TableColumn<>("Position");
		positionColumn.setMinWidth(50);
		positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
		
		TableColumn<Player,String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Player,String> teamColumn = new TableColumn<>("Team");
		teamColumn.setMinWidth(200);
		teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
		
		TableColumn<Player,Integer> valueColumn = new TableColumn<>("Value");
		valueColumn.setMinWidth(100);
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		TableColumn<Player,Integer> textFieldColumn = new TableColumn<>("Price");
		textFieldColumn.setMinWidth(200);
		textFieldColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		tab.getColumns().addAll(positionColumn, nameColumn,teamColumn,valueColumn,textFieldColumn);
		tab.setItems(list);
		}
    }
	
	public Championship getChampionship() {
		return championship;
	}

	public void setChampionship(Championship championship) {
		this.championship = championship;
	}

	public DBQuery getDb() {
		return db;
	}

	public void setDb(DBQuery db) {
		this.db = db;
	}

	
	@FXML
	public void handlerAuctionButton(ActionEvent event) {
		System.out.println("Questo bottone dovrà fare qualcosa");
	}
	
	@FXML
	public void handlerChoiceBox(ActionEvent event) {
		System.out.println("Invocato");
		teamLabel.setText("Team: " + team.getValue().getClub().getName());
		budgetLabel.setText("Budget: " + team.getValue().getBudget());
		list.clear();
		List<Player> playerList = team.getValue().getClub().getTeam();
		list.addAll(playerList);
	}

}
