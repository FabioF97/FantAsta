package gui;

import java.io.IOException;
import java.util.List;

import database.DBQuery;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ui.Championship;
import ui.Player;
import ui.Striker;
import ui.User;

public class ReleaseSellController {
	
	@FXML TableView<Player> tab;

	@FXML ChoiceBox<User> team;

	@FXML Label userLabel;
	@FXML Label budgetLabel;

	private DBQuery db;
	private Championship championship;
	
	private ObservableList<User> listUser;
	private ObservableList<Player> list;
	
	@FXML 
	public void initialize() {
		if (db != null) {
		list = FXCollections.observableArrayList(item -> new Observable[] {item.visibleProperty()});
		list.addListener((Change<? extends Player> c) -> {
			while(c.next()) {
				if(c.wasUpdated()) {
					team.getValue().release(list.get(c.getFrom()));
					//team.getValue().sell(list.get(c.getFrom())); bisogna pensare ad un modo per scegliere se fare i sell o il reslease
					list.remove(c.getFrom());
					tab.setItems(list);
					tab.refresh();
				}
			}
		});
		listUser = FXCollections.observableArrayList();
		listUser.addAll(championship.getCompetitors());
		team.getItems().addAll(listUser);
		team.setValue(listUser.get(0));
		TableColumn<Player,String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
		
		TableColumn<Player,String> teamColumn = new TableColumn<>("Team");
		teamColumn.setMinWidth(200);
		teamColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("team"));
		
		TableColumn<Player,Integer> valueColumn = new TableColumn<>("Value");
		valueColumn.setMinWidth(100);
		valueColumn.setCellValueFactory(new PropertyValueFactory<Player,Integer>("value"));
		
		TableColumn<Player,Integer> priceColumn = new TableColumn<>("Price");
		priceColumn.setMinWidth(100);
		priceColumn.setCellValueFactory(new PropertyValueFactory<Player,Integer>("price"));
		
		
		TableColumn<Player,Button> sellColumn = new TableColumn<>("Sell");
		sellColumn.setMinWidth(100);
		sellColumn.setCellValueFactory(new PropertyValueFactory<Player,Button>("sell"));
		
		TableColumn<Player,Button> releaseColumn = new TableColumn<Player,Button>("Release");
		releaseColumn.setMinWidth(100);
		releaseColumn.setCellValueFactory(new PropertyValueFactory<Player,Button>("release"));
		
		TableColumn<Player,Button> buyColumn = new TableColumn<Player, Button>("Buy");
		buyColumn.setMinWidth(200);
		buyColumn.setCellValueFactory(new PropertyValueFactory<Player,Button>("buy"));
		
		boolean bool = tab.getColumns().addAll(nameColumn,valueColumn,priceColumn,sellColumn,releaseColumn);
		System.out.println(bool);
		tab.setItems(list);
		}
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

	/**
	 * This method manages the ComboBox and changes team information, 
	 * current user and team displayed (fired by team)
	 * @param event
	 */
	@FXML
	public void handlerChoiceBox(ActionEvent event) {
		System.out.println("Invocato");
		userLabel.setText("Team: " + team.getValue().getUsername());
		budgetLabel.setText("Budget: " + team.getValue().getBudget());
		list.clear();
		List<Player> playerList = team.getValue().getClub().getTeam();
		list.addAll(playerList);
	}
	
	@FXML
	public void handlerNextController(ActionEvent event) throws IOException{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Transfer.fxml"));
		Parent parent = loader.load();
		TransferController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setChampionship(championship);
		ctrl.initialize();
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		window.setScene(scene2);
		window.show();
	}

}
