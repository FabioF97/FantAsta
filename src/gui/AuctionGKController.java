package gui;

import java.io.IOException;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ui.*;

public class AuctionGKController {

@FXML TableView<Player> tab;
	
	@FXML 
	public void initialize() {
		ObservableList<Player> list = getPlayers();
		TableColumn<Player,String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Player,String> teamColumn = new TableColumn<>("Team");
		teamColumn.setMinWidth(200);
		teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
		
		TableColumn<Player,Integer> valueColumn = new TableColumn<>("Value");
		valueColumn.setMinWidth(100);
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		TableColumn<Player,TextField> textFieldColumn = new TableColumn<>("Price");
		textFieldColumn.setMinWidth(200);
		textFieldColumn.setCellValueFactory(new PropertyValueFactory<>("priceTab"));
		
		TableColumn<Player,ChoiceBox<String>> choiceBoxColumn = new TableColumn<>("To");
		choiceBoxColumn.setMinWidth(200);
		choiceBoxColumn.setCellValueFactory(new PropertyValueFactory<>("choice"));
		
		TableColumn<Player,Button> buyColumn = new TableColumn<>("Buy");
		buyColumn.setMinWidth(200);
		buyColumn.setCellValueFactory(new PropertyValueFactory<>("buy"));
		
		tab.getColumns().addAll(nameColumn,teamColumn,valueColumn,textFieldColumn, choiceBoxColumn, buyColumn);
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
		Parent parent = FXMLLoader.load(getClass().getResource("AuctionDef.fxml"));
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		window.setScene(scene2);
		window.show();
	}
}
