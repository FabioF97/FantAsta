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
import ui.Championship;
import ui.Player;
import ui.User;
/**
 * This class is the AuctionDef controller and is responsible 
 * for initializing and managing it
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class AuctionDefController {
	
	/**
	 * Table containing all the available goalkeepers
	 * */
	@FXML TableView<Player> tab;
	/**
	 * Table containing all the players of a chosen team
	 * */
	@FXML private TableView<Player> tabClub;
	
	/**
	 * Used to choose the user to view the team
	 * */
	@FXML private ChoiceBox<User> clubBox;
	
	/**
	 * Shows the username
	 * */
	@FXML private Label clubUserLabel;
	/**
	 * Show the budget of the chosen user
	 * */
	@FXML private Label clubBudgetLabel;
	
	/**
	 * Link to the Database
	 * */
	private DBQuery db;
	/**
	 * The championship to which users are participating
	 * */
	private Championship championship;
	
	/** Used to check if the current auction is a repair auction */
	private boolean sceneSelector;
	
	/**
	 * Used to fill clubBox
	 * */
	private ObservableList<User> clubs;
	/**
	 * Used to fill tabClub
	 * */
	private ObservableList<Player> clubList;
	
	/**
	 * This method initializes the scene by creating 
	 * the table columns that are filled by the ObservableLists 
	 * 
	 */
	@FXML 
	public void initialize() {
		if(db != null) {
			ObservableList<Player> list = FXCollections.observableArrayList(item -> new Observable[] {item.visibleProperty()});
			
			clubs = FXCollections.observableArrayList();
			for (User u : championship.getCompetitors()) {
				clubs.add(u);
			}
			
			System.out.println("Mi ha passato: "+ championship);
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
		
		clubList = FXCollections.observableArrayList();
		
		TableColumn<Player,String> positionClubColumn = new TableColumn<>("Position");
		positionClubColumn.setMinWidth(50);
		positionClubColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
		
		TableColumn<Player,String> nameClubColumn = new TableColumn<>("Name");
		nameClubColumn.setMinWidth(200);
		nameClubColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Player,String> teamClubColumn = new TableColumn<>("Team");
		teamClubColumn.setMinWidth(200);
		teamClubColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
		
		TableColumn<Player,Integer> valueClubColumn = new TableColumn<>("Value");
		valueClubColumn.setMinWidth(100);
		valueClubColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		TableColumn<Player,Integer> priceClubColumn = new TableColumn<>("Price");
		priceClubColumn.setMinWidth(200);
		priceClubColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		
		clubBox.getItems().addAll(clubs);
		clubBox.setValue(clubs.get(0));
		tabClub.getColumns().addAll(positionClubColumn,nameClubColumn,teamClubColumn,valueClubColumn,priceClubColumn);
		tabClub.setItems(clubList);
		}
    }
	
	
	
	public boolean isSceneSelector() {
		return sceneSelector;
	}



	public void setSceneSelector(boolean sceneSelector) {
		this.sceneSelector = sceneSelector;
	}



	/**
	 * Returns the link with the database
	 * @return db
	 */
	public DBQuery getDb() {
		return db;
	}

	/**
	 * Sets the connection to the database
	 * @param db
	 */
	public void setDb(DBQuery db) {
		this.db = db;
	}

	/**
	 * Returns the current championship
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
	 * Return the list of purchasable players sensitive 
	 * to the change of the attribute of the players vsbl
	 * @return list
	 * @throws SQLException
	 */
	public ObservableList<Player> getPlayers() throws SQLException{
		ObservableList<Player> ret = FXCollections.observableArrayList(item -> new Observable[] {item.visibleProperty()});
		List<Player> list;
		if(sceneSelector == false) {
			list = db.getDef1(championship.getName());
		}
		else {
			list = db.getDef2(championship.getName());
		}
		for(Player p : list) {
			boolean bool = p.visibleProperty().getValue();
			if(bool == true) {
				p.fillChoiceBox(clubs);
				ret.add(p);
			}
		}
		Collections.sort(ret);
		ret.addListener((Change<? extends Player> c) -> {
			while(c.next()) {
				if(c.wasUpdated()) {
					if(ret.get(c.getFrom()).destination().buyPlayer(ret.get(c.getFrom()), ret.get(c.getFrom()).buyPrice()) == true) {
						ret.remove(c.getFrom());
						tab.setItems(ret);
						tab.refresh();
						refreshClubBox();
					}
					else {
						ret.get(c.getFrom()).visibleProperty().set(true);
						System.out.println(ret.get(c.getFrom()).visibleProperty());
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText(null);
						alert.setContentText("Defenders are enough or the budget is insufficient!");
						alert.showAndWait();
					}
				}
			}
		});
		return ret;
	}
	
	/**
	 * Change the scene and pass the necessary 
	 * parameters to the next scene if the requirements are met
	 * (fired by next button)
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handlerNextController(ActionEvent event) throws IOException {
		for(User u: clubs) {
			if(u.getClub().isFullDef() == false) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText(u.getClub().getName() +" is missing defenders!");
				alert.showAndWait();
				return;
			}
		}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AuctionMid.fxml"));
		Parent parent = loader.load();
		AuctionMidController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setChampionship(championship);
		ctrl.setSceneSelector(sceneSelector);
		ctrl.initialize();
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene2.getStylesheets().add(getClass().getResource("Start.css").toExternalForm());
		
		window.setScene(scene2);
		window.show();
	}
	
	/**
	 * Updates the team shown when the choice is changed to ChoicheBox (fired by clubBox)
	 * @param event
	 */
	@FXML
	public void handlerClubBox(ActionEvent event) {
		clubList.clear();
		List<Player> playerList = clubBox.getValue().getClub().getTeam();
		clubList.addAll(playerList);
		Collections.sort(clubList);
		tabClub.setItems(clubList);
		tabClub.refresh();
		clubUserLabel.setText("User: " + clubBox.getValue().getUsername());
		clubBudgetLabel.setText("Budget: " + clubBox.getValue().getBudget());
	}
	
	/**
	 * Updates the team shown by entering the newly purchased players
	 */
	@FXML
	public void refreshClubBox() {
		clubList.clear();
		List<Player> playerList = clubBox.getValue().getClub().getTeam();
		clubList.addAll(playerList);
		Collections.sort(clubList);
		tabClub.setItems(clubList);
		tabClub.refresh();
		clubUserLabel.setText("User: " + clubBox.getValue().getUsername());
		clubBudgetLabel.setText("Budget: " + clubBox.getValue().getBudget());
	}
}
