package gui;

import java.util.List;

import database.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.Championship;
import ui.Player;
import ui.User;

public class TransferController {
	
	@FXML private TableView<Player> tab;
	@FXML private ChoiceBox<User> clubBox;
	@FXML private ChoiceBox<User> creditBox;
	@FXML private TextField creditTextfield; 
	@FXML private Button creditButton;
	@FXML private Label userLabel;
	@FXML private Label budgetLabel;
	
	private DBQuery db;
	private Championship championship;
	
	private ObservableList<User> users;
	private ObservableList<Player> list;
	
	
	@FXML
	public void initialize() {
		if(db != null) {
			list = FXCollections.observableArrayList();
			users = FXCollections.observableArrayList();
			users.addAll(championship.getCompetitors());
			clubBox.getItems().addAll(users);
			clubBox.setValue(users.get(0));
			User user = clubBox.getValue();
			userLabel.setText("User: "+ user.getUsername());
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

}
