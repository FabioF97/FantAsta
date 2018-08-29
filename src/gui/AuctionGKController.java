package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import database.DBQuery;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ui.*;
import gui.MainController;

public class AuctionGKController {

	@FXML TableView<Player> tab;

	private MainController main;

	private DBQuery db;
	
	private Championship championship;

	ObservableList<Player> list;
	
	@FXML 
	public void initialize() {
		
		ObservableList<Player> list = FXCollections.observableArrayList(item -> new Observable[] {item.visibleProperty()});
		
		
		try {
			list = getPlayers();
		} catch (SQLException e) {
			System.out.println("Error with comunication with the database");
			e.printStackTrace();
		}
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
	
	public DBQuery getDb() {
		return db;
	}

	public void setDb(DBQuery db) {
		this.db = db;
	}

	public Championship getChampionship() {
		return championship;
	}

	public void setChampionship(Championship championship) {
		this.championship = championship;
	}

	public ObservableList<Player> getPlayers() throws SQLException{
		//ObservableList<Player> players = FXCollections.observableArrayList();
		
		//try {
			//DBQuery db = new DBQuery();
		//List<Player> list = db.getGk1("Spongebob"); 	// qua al posto di list_player va il nome della lista creata con lo stesso nome del campionato
		//ObservableList<Player> players = FXCollections.observableArrayList();
		//for(Player p : list) {
		//	players.add(p);
		//}
		//System.out.println(players.size());
		//Collections.sort(players);
		//return players;
		//}catch (SQLException e) {
		//	System.out.println("Error with comunication with the database");
		//}
		//return null;
		ObservableList<Player> ret = FXCollections.observableArrayList(item -> new Observable[] {item.visibleProperty()});
		List<Player> list = db.getGk1("Spongebob");
		for(Player p : list) {
			if(p.isVisible()) {
				ret.add(p);
			}
		}
		Collections.sort(ret);
		ret.addListener((Change<? extends Player> c) -> {
			while(c.next()) {
				if(c.wasUpdated()) {
					ret.remove(c.getFrom());
					tab.setItems(ret);
					tab.refresh();
				}
			}
		});
		return ret;
	}
	
	
	@FXML
	protected void handlerNextController(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("AuctionDef.fxml"));
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
