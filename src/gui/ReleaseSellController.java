package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ui.Championship;
import ui.Club;
import ui.Player;
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
	private List<Player> toUpdate;
	private boolean sceneSelector = false;
	
	@FXML 
	public void initialize() {
		if (db != null) {
			toUpdate = new ArrayList<Player>();
			list = FXCollections.observableArrayList(item -> new Observable[] {item.visibleProperty()});
			list.addListener((Change<? extends Player> c) -> {
			while(c.next()) {
				if(c.wasUpdated()) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Sell or release?");
					alert.setHeaderText(null);
					alert.setContentText("Do you want to sell or release the chosen player?");

					ButtonType buttonSell = new ButtonType("Sell");
					ButtonType buttonRelease = new ButtonType("Release");
					ButtonType buttonCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

					alert.getButtonTypes().setAll(buttonRelease, buttonSell, buttonCancel);

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == buttonSell){
						team.getValue().sell(list.get(c.getFrom()));
						toUpdate.add(list.get(c.getFrom()));
						list.remove(c.getFrom());
						tab.setItems(list);
						tab.refresh();
						budgetLabel.setText("Budget: " + team.getValue().getBudget());
					} else if (result.get() == buttonRelease) {
						team.getValue().release(list.get(c.getFrom()));
						toUpdate.add(list.get(c.getFrom()));
						list.remove(c.getFrom());
						tab.setItems(list);
						tab.refresh();
						budgetLabel.setText("Budget: " + team.getValue().getBudget());
						
					}
					else {
						list.get(c.getFrom()).visibleProperty().set(false);
					}
				}
			}
		});
		listUser = FXCollections.observableArrayList();
		listUser.addAll(championship.getCompetitors());
		team.getItems().addAll(listUser);
		team.setValue(listUser.get(0));
		
		TableColumn<Player,String> positionColumn = new TableColumn<>("Position");
		positionColumn.setMinWidth(20);
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
		
		TableColumn<Player,Integer> priceColumn = new TableColumn<>("Price");
		priceColumn.setMinWidth(100);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		TableColumn<Player,Button> sellColumn = new TableColumn<>("Sell");
		sellColumn.setMinWidth(100);
		sellColumn.setCellValueFactory(new PropertyValueFactory<>("sell"));
		
		tab.getColumns().addAll(positionColumn, nameColumn,valueColumn,priceColumn,sellColumn);
		tab.setItems(list);
		}
    }

	public boolean isSceneSelector() {
		return sceneSelector;
	}

	public void setSceneSelector(boolean sceneSelector) {
		this.sceneSelector = sceneSelector;
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
		userLabel.setText("User: " + team.getValue().getUsername());
		budgetLabel.setText("Budget: " + team.getValue().getBudget());
		list.clear();
		List<Player> playerList = team.getValue().getClub().getTeam();
		list.addAll(playerList);
	}
	
	@FXML
	public void handlerNextController(ActionEvent event) throws IOException{
		if(sceneSelector == false) {
			list.clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Transfer.fxml"));
			Parent parent = loader.load();
			TransferController ctrl = loader.getController();
			ctrl.setDb(db);
			ctrl.setChampionship(championship);
			ctrl.initialize();
			System.out.println("Nella lista dei giocatori da aggiornare ci sono: " + toUpdate.size() + " giocatori");
			for(Player p: toUpdate) {
				try {
					db.playerUpdate(p.getId(), p.getPrice(), p.visibleProperty().get(), null, championship.getName());
				} catch (SQLException e) {
					System.out.println("Comunication error with the database");
					e.printStackTrace();
				}
			}
			Scene scene2 = new Scene(parent);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			window.setScene(scene2);
			window.show();
		}
		else {
			List<User> userList = championship.getCompetitors();
			for(User u: userList) {
				if(u.getClub().getGoalkeepers() > Club.NUMGOALKEEPERS) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!");
					alert.setHeaderText(null);
					alert.setContentText("Ooops, " + u.getClub().getName() + " has too many goalkeepers!");
					alert.showAndWait();
					return;
				}
				if(u.getClub().getDefenders() > Club.NUMDEFENDERS) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!");
					alert.setHeaderText(null);
					alert.setContentText("Ooops, " + u.getClub().getName() + " has too many defenders!");
					alert.showAndWait();
					return;
				}
				if(u.getClub().getMidfielders() > Club.NUMMIDFIELDERS) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!");
					alert.setHeaderText(null);
					alert.setContentText("Ooops, " + u.getClub().getName() + " has too many midfielders!");
					alert.showAndWait();
					return;
				}
				if(u.getClub().getStrikers() > Club.NUMSTRIKERS) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!");
					alert.setHeaderText(null);
					alert.setContentText("Ooops, " + u.getClub().getName() + " has too many strikers!");
					alert.showAndWait();
					return;
				}
			}
			for(Player p: toUpdate) {
				try {
					db.playerUpdate(p.getId(), p.getPrice(), p.visibleProperty().get(), null, championship.getName());
				} catch (SQLException e) {
					System.out.println("Comunication error with the database");
					e.printStackTrace();
				}
			}
			for(User u: userList) {
				List<Player> club = u.getClub().getTeam();
				for(Player p: club) {
					try {
						db.playerUpdate(p.getId(), p.getPrice(), p.visibleProperty().get(), u.getClub().getName(), championship.getName());
					} catch (SQLException e) {
						System.out.println("Comunication error with the database");
						e.printStackTrace();
					}
				}
			}
			list.clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AuctionGK.fxml"));
			Parent parent = loader.load();
			AuctionGKController ctrl = loader.getController();
			ctrl.setDb(db);
			ctrl.setChampionship(championship);
			ctrl.setSceneSelector(sceneSelector);
			ctrl.initialize();
			System.out.println("Nella lista dei giocatori da aggiornare ci sono: " + toUpdate.size() + " giocatori");
			for(Player p: toUpdate) {
				try {
					db.playerUpdate(p.getId(), p.getPrice(), p.visibleProperty().get(), null, championship.getName());
				} catch (SQLException e) {
					System.out.println("Comunication error with the database");
					e.printStackTrace();
				}
			}
			Scene scene2 = new Scene(parent);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			window.setScene(scene2);
			window.show();
		}
	}

}
