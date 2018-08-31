package gui;

import database.DBQuery;
import javafx.fxml.FXML;
import ui.Championship;

public class TransferController {
	
	private DBQuery db;
	private Championship championship;
	
	@FXML
	public void initialize() {
		if (db != null) {
			
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
