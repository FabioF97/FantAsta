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

/**
 * This class manages the repair auction transfer phase 
 * in which each user can send 
 * money and players to any other team
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class TransferController {
	
	/**
	 * Shows the player of the current team
	 */
	@FXML private TableView<Player> tab1;
	/**
	 * Allow to choose the user to view the team
	 */
	@FXML private ChoiceBox<User> clubBox;
	/**
	 * Allow to choose the user to send credits to
	 */
	@FXML private ChoiceBox<User> creditBox;
	/**
	 * Allow to enter the amount of credits to be sent
	 */
	@FXML private TextField creditTextfield; 
	/**
	 * Used to send credits
	 */
	@FXML private Button goButton;
	/**
	 * Shows the current username
	 */
	@FXML private Label userLabel;
	/**
	 * Shows the budget of the current user
	 */
	@FXML private Label budgetLabel;
	
	/**
	 * Link to the database
	 */
	private DBQuery db;
	/**
	 * Current championship
	 */
	private Championship championship;
	
	/**
	 * Used to fill clubBox and creditBox
	 */
	private ObservableList<User> users;
	/**
	 * Used to fill tab1
	 */
	private ObservableList<Player> list;
	
	
	/**
	 * This method initializes the scene by creating 
	 * the table columns that are filled by the ObservableLists 
	 */
	@FXML
	public void initialize() {
		if(db != null) {
			users = FXCollections.observableArrayList();
			users.addAll(championship.getCompetitors());
			list = FXCollections.observableArrayList(item -> new Observable[] {item.visibleProperty()});
			list.addListener((Change<? extends Player> c) -> {
				while(c.next()) {
					if(c.wasUpdated()) {
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

	/**
	 * Gets the link to the database
	 * @return db
	 */
	public DBQuery getDb() {
		return db;
	}
	
	/**
	 * Sets the link to the database
	 * @param db
	 */
	public void setDb(DBQuery db) {
		this.db = db;
	}
	
	/**
	 * Gets the current championship
	 * @return championship
	 */
	public Championship getChampionship() {
		return championship;
	}
	
	/**
	 * Sets the current championship
	 * @param championship
	 */
	public void setChampionship(Championship championship) {
		this.championship = championship;
	}
	
	/**
	 * This method manages the update of the displayed team
	 * @param event
	 */
	@FXML
	public void handlerClubBox(ActionEvent event) {
		userLabel.setText("User: " + clubBox.getValue().getUsername());
		budgetLabel.setText("Budget: " + clubBox.getValue().getBudget());
		list.clear();
		List<Player> playerList = clubBox.getValue().getClub().getTeam();
		list.addAll(playerList);
	}
	
	/**
	 * Manages the sending of credits between users
	 * @param event
	 */
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
	
	/**
	 * Update the team to be displayed
	 * @param team
	 */
	public void refreshClubBox(List<Player> team) {
		list.clear();
		Collections.sort(team);
		list.addAll(team);
	}
	
	/**
	 * Update the team to be displayed
	 */
	public void refreshClubBox2() {
		list.clear();
		List<Player> playerList = clubBox.getValue().getClub().getTeam();
		list.addAll(playerList);
		Collections.sort(list);
	}
	
	/**
	 * Manages the necessary operations before changing scene
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void handlerNextController(ActionEvent event) throws IOException{
		list.clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReleaseSell.fxml"));
		Parent parent = loader.load();
		ReleaseSellController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setChampionship(championship);
		ctrl.setSceneSelector(true);
		ctrl.initialize();
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene2.getStylesheets().add(getClass().getResource("Start.css").toExternalForm());
			
		window.setScene(scene2);
		window.show();
	}

}
