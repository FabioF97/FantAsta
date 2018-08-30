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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import ui.*;

public class AuctionGKController {

	@FXML private TableView<Player> tab;
	@FXML private TableView<Player> tabClub;
	
	@FXML private ChoiceBox<User> clubBox;

	private DBQuery db;
	
	private Championship championship;
	

	private ObservableList<User> clubs;
	private ObservableList<Player> clubList;
	
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
		ObservableList<Player> ret = FXCollections.observableArrayList(item -> new Observable[] {item.visibleProperty()});
		List<Player> list = db.getGk1(championship.getName());
		for(Player p : list) {
			if(p.isVisible()) {
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
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText(null);
						alert.setContentText("Goalkeepers are enough!");
						alert.showAndWait();
					}
				}
			}
		});
		return ret;
	}
	
	// aggiunger i controlli che si può andare avanti solo se tutti i team hanno il numero correttto di giocatori
	
	@FXML
	protected void handlerNextController(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AuctionDef.fxml"));
		Parent parent = loader.load();
		AuctionDefController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setChampionship(championship);
		ctrl.initialize();
		
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		window.setScene(scene2);
		window.show();
	}
	
	@FXML
	public void handlerClubBox(ActionEvent event) {
		clubList.clear();
		List<Player> playerList = clubBox.getValue().getClub().getTeam();
		clubList.addAll(playerList);
	}
	
	@FXML
	public void refreshClubBox() {
		clubList.clear();
		List<Player> playerList = clubBox.getValue().getClub().getTeam();
		clubList.addAll(playerList);
	}


}
