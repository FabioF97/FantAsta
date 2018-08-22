package database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ui.Player;

public class DBQuery {
	
	
	public DBManager db;
	
	/**
	 * Constructor.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public DBQuery() throws SQLException {
		super();
		try {
			db = new DBManager();
		}	catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List getGk() throws SQLException{
		ResultSet rs = db.executeQuery("SELECT * FROM book LIMIT 100");
		while(rs.next()) {
			
		}
	}
	

}
