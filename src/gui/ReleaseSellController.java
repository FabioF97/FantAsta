package gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.Player;
import ui.Striker;

public class ReleaseSellController {
	
@FXML TableView<Player> tab;

@FXML ChoiceBox<String> team;
	
	@FXML 
	public void initialize() {
		
		//La successiva linea è temporanea, bisognerà fare il vero set degli items
		ObservableList<String> teams = FXCollections.observableArrayList("Team 1","Team 2","Team 3");
		team.setItems(teams);
		ObservableList<Player> list = getPlayers();
		team.setValue(teams.get(0));
		TableColumn<Player,String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Player,String> teamColumn = new TableColumn<>("Team");
		teamColumn.setMinWidth(200);
		teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
		
		TableColumn<Player,Integer> valueColumn = new TableColumn<>("Value");
		valueColumn.setMinWidth(100);
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		TableColumn<Player,Integer> priceColumn = new TableColumn<>("Price");
		priceColumn.setMinWidth(100);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		
		TableColumn<Player,Button> sellColumn = new TableColumn<>("Sell");
		sellColumn.setMinWidth(100);
		sellColumn.setCellValueFactory(new PropertyValueFactory<>("sell"));
		
		TableColumn<Player,Button> releaseColumn = new TableColumn<>("Release");
		releaseColumn.setMinWidth(100);
		releaseColumn.setCellValueFactory(new PropertyValueFactory<>("release"));
		
		tab.getColumns().addAll(nameColumn,valueColumn,priceColumn,sellColumn,releaseColumn);
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
	
	@FXML
	protected void handlerNextController(ActionEvent event) throws IOException {
		System.out.println("Bisogna fare la pagina successiva");
	}

}
