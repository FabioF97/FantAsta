package ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import data.DBQuery;
import domain.Championship;
import domain.Player;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * This class is the ShowChampionship controller and is responsible 
 * for initializing and managing it.
 * In this scene the championship, the team of each user and his information
 * iare displayed and there is the possibility to start a repair auction.
 * 
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class ShowChampionshipController {

	/**
	 * This table shows the team selected in the ChoiceBox
	 */
	@FXML TableView<Player> tab;
	/**
	 * It allows you to choose the team to display
	 */
	@FXML ChoiceBox<User> team;
	/**
	 * Shows the username
	 */
	@FXML Label teamLabel;
	/**
	 * Shows the budget of the selected user
	 */
	@FXML Label budgetLabel;
	
	/**
	 * Label showing the championship's name
	 */
	@FXML Label championshipLabel;

	/**
	 * Link to the database
	 */
	private DBQuery db;
	/**
	 * Choosen championship
	 */
	private Championship championship;
	/**
	 * This list contains the team currently displayed
	 */
	private ObservableList<Player> list;
	
	/**
	 * This method initializes the scene by creating 
	 * the table columns that are filled by the ObservableLists 
	 */
	@FXML 
	public void initialize() {
		if(db != null) {
		list = FXCollections.observableArrayList();
		ObservableList<User> users = FXCollections.observableArrayList();
		List<User> list1 = championship.getCompetitors();
		users.addAll(list1);
		team.getItems().addAll(users);
		team.setValue(users.get(0));
		User user = team.getValue();
		teamLabel.setText("User: "+ user.getUsername());
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
		Collections.sort(list);
		tab.setItems(list);
		championshipLabel.setText(championship.getName());
		}
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
	 * This button gives access to the repair auction
	 * @param event
	 */
	@FXML
	public void handlerAuctionButton(ActionEvent event) throws IOException{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReleaseSell.fxml"));
		Parent parent = loader.load();
		ReleaseSellController ctrl = loader.getController();
		ctrl.setDb(db);
		ctrl.setChampionship(championship);
		ctrl.initialize();
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene2.getStylesheets().add(getClass().getResource("Start.css").toExternalForm());
		
		window.setScene(scene2);
		window.show();
	}
	
	/**
	 * This button gives access to the homepage
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void handlerBackButton(ActionEvent event) throws IOException{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
		Parent parent = loader.load();
		StartController ctrl = loader.getController();
		Scene scene2 = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene2.getStylesheets().add(getClass().getResource("Start.css").toExternalForm());
		
		window.setScene(scene2);
		window.show();
	}
	
	/**
	 * This button save the data in a PDF
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void handlerPDFButton(ActionEvent event) throws IOException{

			String path = new String();
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			DirectoryChooser directoryChooser = new DirectoryChooser();
			File selectedDirectory = directoryChooser.showDialog(window);
	            if (selectedDirectory != null) {
	                path=selectedDirectory.getAbsolutePath();
	            }
			System.out.println(path + "\\" + team.getValue().getUsername());
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(path + "\\" + team.getValue().getUsername() + ".pdf"));
				document.open();
				document.add(new Paragraph("Championship: " + championship.getName()));
				document.add(new Paragraph("User: " + team.getValue().getUsername()));
				document.add(new Paragraph("Club: " + team.getValue().getClub().getName()));
				document.add(new Paragraph("Budget: " + team.getValue().getBudget()));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				
				PdfPTable table = new PdfPTable(5);
				table.setWidthPercentage(90f);
				
				PdfPCell c1 = new PdfPCell(new Phrase("Position"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c1);
		        PdfPCell c2 = new PdfPCell(new Phrase("Name"));
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c2);
		        PdfPCell c3 = new PdfPCell(new Phrase("Team"));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c3);
		        PdfPCell c4 = new PdfPCell(new Phrase("Value"));
				c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c4);
		        PdfPCell c5 = new PdfPCell(new Phrase("Price"));
				c5.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c5);
		             
		        for (Player p : team.getValue().getClub().getTeam()) {
		        	table.addCell(p.getPosition());
		        	table.addCell(p.getName());
		        	table.addCell(p.getTeam());
		        	table.addCell(Integer.toString(p.getValue()));
		        	table.addCell(Integer.toString(p.getPrice()));
		        }
		        
		        document.add(table);
				
				document.close();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * This method manages the ComboBox and changes team information, 
	 * current user and team displayed (fired by team)
	 * @param event
	 */
	@FXML
	public void handlerChoiceBox(ActionEvent event) {
		System.out.println("Invocato");
		teamLabel.setText("User: " + team.getValue().getUsername());
		budgetLabel.setText("Budget: " + team.getValue().getBudget());
		list.clear();
		List<Player> playerList = team.getValue().getClub().getTeam();
		Collections.sort(playerList);
		list.addAll(playerList);
	}

}