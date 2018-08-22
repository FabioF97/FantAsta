package database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ui.*;

public class DBQuery {
	
	
	protected DBManager db;
	
	/**
	 * Constructor.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public DBQuery() throws SQLException {
		super();
		try {
			db = new DBManager();
			db.executeQuery("select * from list_player");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Int -> Boolean converter
	 * @param n
	 * @return boolean
	 */
	public boolean toBoolean(int n) {
		if(n != 0)
			return true;
		else return false;
	}
	
	/**
	 * closes the connection
	 * @throws SQLException
	 */
	public void close() throws SQLException{
		db.close();
	}
	
	/**
	 * Returns the list filled with all goalkeepers for the first auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getGk1() throws SQLException {
		List<Player> lGk = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Gk'");
		while(rs.next()) {
			lGk.add(new Goalkeeper(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id")));
		}
		return lGk;
	}
	
	/**
	 * Returns the list filled with all goalkeepers for the second auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getGk2() throws SQLException {
		List<Player> lGk = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Gk'");
		while(rs.next()) {
			lGk.add(new Goalkeeper(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible"))));
		}
		return lGk;
	}
	
	/**
	 * Returns the list filled with all defenders for the first auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getDef1() throws SQLException {
		List<Player> lDef = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Def'");
		while(rs.next()) {
			lDef.add(new Defender(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id")));
		}
		return lDef;
	}
	
	/**
	 * Returns the list filled with all defenders for the second auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getDef2() throws SQLException {
		List<Player> lDef = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Def'");
		while(rs.next()) {
			lDef.add(new Defender(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible"))));
		}
		return lDef;
	}
	
	/**
	 * Returns the list filled with all midfielders for the first auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getMid1() throws SQLException {
		List<Player> lMid = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Mid'");
		while(rs.next()) {
			lMid.add(new Midfielder(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id")));
		}
		return lMid;
	}
	
	/**
	 * Returns the list filled with all midfielders for the second auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getMid2() throws SQLException {
		List<Player> lMid = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Mid'");
		while(rs.next()) {
			lMid.add(new Midfielder(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible"))));
		}
		return lMid;
	}
	
	/**
	 * Returns the list filled with all strikers for the first auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getStr1() throws SQLException {
		List<Player> lStr = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Str'");
		while(rs.next()) {
			lStr.add(new Striker(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id")));
		}
		return lStr;
	}
	
	/**
	 * Returns the list filled with all strikers for the second auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getStr2() throws SQLException {
		List<Player> lStr = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Str'");
		while(rs.next()) {
			lStr.add(new Striker(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible"))));
		}
		return lStr;
	}
	
	/**
	 * Returns the list filled with all strikers for the second auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	/*
	public Club getClub(String club) throws SQLException {
		Club ret;
		List<Player> lPly = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where Club=" + club);
		while(rs.next()) {
			lPly.add(new Striker(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible"))));
		}
	}
	*/
	
	/**
	 * Returns the list filled with all strikers for the second auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	/*
	 * BISOGNA CAMBIARE IL COSTRUTTORE PRIMA DI ANDARE AVANTI CON QUESTO METODO
	public List<Championship> getChampionship() throws SQLException {
		List<Championship> lChmp = new ArrayList<Championship>();
		ResultSet rs = db.executeQuery("Select * from championship");
		while(rs.next()) {
			lChmp.add(rs.getString("name"),rs.get);
		}
		return lChmp;
	}*/
	
	/**
	 * Returns the list filled with all the users of a championship
	 * @return List<User>
	 * @throws SQLException
	 */
	/*
	 * Bisogna mettere dentro il metodo il riempimento delle rose da database
	public List<User> getUser() throws SQLException {
		List<User> lUsr = new ArrayList<User>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Str'");
		while(rs.next()) {
			lUsr.add();
		}
		return lUsr;
	}
	*/
	
	/**
	 * Prints a row
	 * @param rs
	 * @throws SQLException
	 */
	public void printRow(ResultSet rs) throws SQLException {
		System.out.println(
				"id=" + rs.getInt("id") + 
				", position=" + rs.getString("position") + 
				", name=" + rs.getString("name") +
				", team=" + rs.getString("team") +
				", value=" + rs.getInt("value") +
				", price=" + rs.getInt("price") +
				", visible=" + rs.getInt("visible") +
				", club=" + rs.getString("club"));
	}
	

}
