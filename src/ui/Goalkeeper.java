package ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents goalkeepers
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class Goalkeeper extends Player {

	/**
	 * Constructor with 4 params
	 * @param name					Player's name	
	 * @param team					Player's team
	 * @param value					Player's value
	 * @param id					Player's id
	 */
	public Goalkeeper(String name, String team, int value, int id) {
		super(name, team, "Gk", value, id);
		
	}

	/**
	 * Constructor with 4 params
	 * @param name					Player's name	
	 * @param team					Player's team
	 * @param value					Player's value
	 * @param id					Player's id
	 * @param price					Price paid by the user who owns this player
	 * @param visible				If it's true, the player will be visible in the list
	 */
	public Goalkeeper(String name, String team, int value, int id, int price, boolean visible) {
		super(name, team, "Gk", value, id, price, visible);
		
	}
	
	
	/**
	 * Returns the list filled with all goalkeepers for the first auction.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public List<Player> getGk1(String championship) throws SQLException {
		List<Player> lGk = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from " + championship.replace(" ", "_") + " where position='Gk'");
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
	public List<Player> getGk2(String championship) throws SQLException {
		List<Player> lGk = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from " + championship.replace(" ", "_") + " where position='Gk'");
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
	public List<Player> getDef1(String championship) throws SQLException {
		List<Player> lDef = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from " + championship.replace(" ", "_") + " where position='Def'");
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
	public List<Player> getDef2(String championship) throws SQLException {
		List<Player> lDef = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from " + championship.replace(" ", "_") + " where position='Def'");
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
	public List<Player> getMid1(String championship) throws SQLException {
		List<Player> lMid = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from " + championship.replace(" ", "_") + " where position='Mid'");
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
	public List<Player> getMid2(String championship) throws SQLException {
		List<Player> lMid = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from " + championship.replace(" ", "_") + " where position='Mid'");
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
	public List<Player> getStr1(String championship) throws SQLException {
		List<Player> lStr = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from " + championship.replace(" ", "_") + " where position='Str'");
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
	public List<Player> getStr2(String championship) throws SQLException {
		List<Player> lStr = new ArrayList<Player>();
		ResultSet rs = db.executeQuery("select * "	+ "from " + championship.replace(" ", "_") + " where position='Str'");
		while(rs.next()) {
			lStr.add(new Striker(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible"))));
		}
		return lStr;
	}
	
	
}
