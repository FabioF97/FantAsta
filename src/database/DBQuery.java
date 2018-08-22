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
			lGk.add(new Goalkeeper(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), rs.getInt("visible")));
		}
		return lGk;
	}
	
	/**
	 * Returns the list filled with all goalkeepers for the first auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getDef1() throws SQLException {
		List<Player> lGk = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Gk'");
		while(rs.next()) {
			lGk.add(new Defender(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id")));
		}
		return lGk;
	}
	
	/**
	 * Returns the list filled with all goalkeepers for the second auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getDef2() throws SQLException {
		List<Player> lGk = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Gk'");
		while(rs.next()) {
			lGk.add(new Defender(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), rs.getInt("visible")));
		}
		return lGk;
	}
	
	/**
	 * Returns the list filled with all goalkeepers for the first auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getMid1() throws SQLException {
		List<Player> lGk = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Gk'");
		while(rs.next()) {
			lGk.add(new Midfielder(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id")));
		}
		return lGk;
	}
	
	/**
	 * Returns the list filled with all goalkeepers for the second auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getMid2() throws SQLException {
		List<Player> lGk = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Gk'");
		while(rs.next()) {
			lGk.add(new Midfielder(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), rs.getInt("visible")));
		}
		return lGk;
	}
	
	/**
	 * Returns the list filled with all goalkeepers for the first auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getStr1() throws SQLException {
		List<Player> lGk = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Gk'");
		while(rs.next()) {
			lGk.add(new Striker(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id")));
		}
		return lGk;
	}
	
	/**
	 * Returns the list filled with all goalkeepers for the second auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getStr2() throws SQLException {
		List<Player> lGk = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where position='Gk'");
		while(rs.next()) {
			lGk.add(new Striker(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), rs.getInt("visible")));
		}
		return lGk;
	}
	
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
