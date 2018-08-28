package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.Player;
import ui.Striker;

public class ShowChampionshipController {

@FXML TableView<Player> tab;
@FXML ChoiceBox team;
	
	@FXML 
	public void initialize() {
		
		ObservableList<String> teams = FXCollections.observableArrayList("Team 1","Team 2","Team 3");
		team.setItems(teams);
		team.setValue(teams.get(0));
		ObservableList<Player> list = getPlayers();
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
	
	public ObservableList<Player> getPlayers(){
		ObservableList<Player> players = FXCollections.observableArrayList();
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		players.add(new Striker("Simeone", "Fiorentina",25, 10));
		return players;
	}
}
