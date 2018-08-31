package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
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
import javafx.stage.Window;
import ui.Championship;
import ui.Player;
import ui.User;

public class TransferController {
	
	@FXML private TableView<Player> tab1;
	@FXML private ChoiceBox<User> clubBox;
	@FXML private ChoiceBox<User> creditBox;
	@FXML private TextField creditTextfield; 
	@FXML private Button goButton;
	@FXML private Label userLabel;
	@FXML private Label budgetLabel;
	
	private DBQuery db;
	private Championship championship;
	
	private ObservableList<User> users;
	private ObservableList<Player> list;
	
	
	@FXML
	public void initialize() {
		if(db != null) {
			users = FXCollections.observableArrayList();
			users.addAll(championship.getCompetitors());
			list = FXCollections.observableArrayList(item -> new Observable[] {item.visibleProperty()});
			list.addListener((Change<? extends Player> c) -> {
				while(c.next()) {
					if(c.wasUpdated()) {
						System.out.println("Vedi?");
						list.get(c.getFrom()).visibleProperty().set(false);
						clubBox.getValue().transfer(list.get(c.getFrom()),list.get(c.getFrom()).getChoice().getValue());
						refreshClubBox(list.get(c.getFrom()).getChoice().getValue().getClub().getTeam());
						list.remove(c.getFrom());
						tab1.setItems(list);
						refreshClubBox2();
					}
				}
			});
			for (User u : championship.getCompetitors()) {
				for (Player p : u.getClub().getTeam()) {
					p.fillChoiceBox(users);
				}
			}
			
			creditBox.getItems().addAll(users);
			creditBox.setValue(users.get(0));
			clubBox.getItems().addAll(users);
			clubBox.setValue(users.get(0));
			User user = clubBox.getValue();
			userLabel.setText("User: "+ user.getUsername());
			budgetLabel.setText("Budget: " + user.getBudget());
			TableColumn<Player,String> positionColumn = new TableColumn<>("Position");
			positionColumn.setMinWidth(50);
			positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
				
			TableColumn<Player,String> nameColumn = new TableColumn<>("Name");
			nameColumn.setMinWidth(100);
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
				
			TableColumn<Player,String> teamColumn = new TableColumn<>("Team");
			teamColumn.setMinWidth(100);
			teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
				
			TableColumn<Player,Integer> valueColumn = new TableColumn<>("Value");
			valueColumn.setMinWidth(50);
			valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
				
			TableColumn<Player,Integer> priceColumn = new TableColumn<>("Price");
			priceColumn.setMinWidth(50);
			priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
			
			TableColumn<Player,ChoiceBox<User>> choiceColumn = new TableColumn<>("To");
			choiceColumn.setMinWidth(200);
			choiceColumn.setCellValueFactory(new PropertyValueFactory<>("choice"));
			
			TableColumn<Player,Button> sendColumn = new TableColumn<>("Send");
			sendColumn.setMinWidth(200);
			sendColumn.setCellValueFactory(new PropertyValueFactory<>("send"));
				
			tab1.getColumns().addAll(positionColumn, nameColumn,teamColumn,valueColumn,priceColumn,choiceColumn,sendColumn);
			tab1.setItems(list);
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
	
	@FXML
	public void handlerClubBox(ActionEvent event) {
		userLabel.setText("User: " + clubBox.getValue().getUsername());
		budgetLabel.setText("Budget: " + clubBox.getValue().getBudget());
		list.clear();
		List<Player> playerList = clubBox.getValue().getClub().getTeam();
		list.addAll(playerList);
	}
	
	@FXML
	public void handlerGoButton(ActionEvent event) {
		Window owner = goButton.getScene().getWindow();
		int n;
		if (creditTextfield.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter the amount of credits to be sent!");
			return;
		}
		try{
			n = Integer.parseInt(creditTextfield.getText());
		} catch (NumberFormatException e) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Credit insertion not allowed.");
			return;
		}
		if(clubBox.getValue().getBudget() < n) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", clubBox.getValue().getUsername() + " has insufficient credit!");
			return;
		}
		clubBox.getValue().withdraw(n);
		creditBox.getValue().deposit(n);
		creditTextfield.clear();
		budgetLabel.setText("Budget: " + clubBox.getValue().getBudget());
	}
	
	public void refreshClubBox(List<Player> team) {
		list.clear();
		Collections.sort(team);
		list.addAll(team);
	}
	
	public void refreshClubBox2() {
		list.clear();
		List<Player> playerList = clubBox.getValue().getClub().getTeam();
		list.addAll(playerList);
		Collections.sort(list);
	}
	
	@FXML
	public void handlerNextController(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReleaseSell.fxml"));
		Parent parent = loader.load();
		ReleaseSellController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setChampionship(championship);
		ctrl.setSceneSelector(true);
		ctrl.initialize();
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
		window.setScene(scene2);
		window.show();
	}

}
