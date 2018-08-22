package database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
	 * Instantiate the player according to the role.
	 * @param rs
	 * @return Player
	 * @throws SQLException
	 */
	public Player role (ResultSet rs) throws SQLException {
		if (rs.getString("position").equals("Gk")) {
			return new Goalkeeper(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible")));
		}
		if (rs.getString("position").equals("Def")) {
			return new Defender(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible")));
		}
		if (rs.getString("position").equals("Mid")) {
			return new Midfielder(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible")));
		}
		else {
			return new Striker(rs.getString("name"), rs.getString("team"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible")));
		}
		
	}
	
	/**
	 * Returns the Club named club.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public Club getClub(String club) throws SQLException {
		Club ret = new Club(club);
		ResultSet rs = db.executeQuery("select * "	+ "from list_player" + " where Club='" + club + "'");
		while(rs.next()) {
			ret.addPlayer(role(rs));
		}
		Collections.sort(ret.getTeam());
		if (!(ret.isFullTeam())){
			System.out.println("Il club " + club + " non è completo! NUMGOALKEEPERS=" + ret.getGoalkeepers() + " NUMDEFENDERS=" + ret.getDefenders() + " NUMMIDFIELDERS=" + ret.getMidfielders() + " NUMSTRIKERS=" + ret.getStrikers());
		} else {
			ret.setGoalkeepers(Club.NUMGOALKEEPERS);
			ret.setDefenders(Club.NUMDEFENDERS);
			ret.setMidfielders(Club.NUMMIDFIELDERS);
			ret.setStrikers(Club.NUMSTRIKERS);
		}
		return ret;
	}

	/**
	 * Returns the User named user.
	 * @param user
	 * @return User
	 * @throws SQLException
	 */
	public User getUser(String user) throws SQLException {
		User ret = new User(user);
		ResultSet rs = db.executeQuery("select * from user where username='" + user + "'");
		ret.setBudget(rs.getInt("budget"));
		ret.setClub(getClub(rs.getString("club")));
		return ret;
	}

	/**
	 * Returns the Championship named championship.
	 * @param championship
	 * @return Championship
	 * @throws SQLException
	 */
	public Championship getChampionship(String championship) throws SQLException {
		Championship ret = new Championship(championship);
		ResultSet rs = db.executeQuery("select * from championship where name='" + championship + "'");
		ret.setDate(rs.getString("date"));
		ret.setBudget(rs.getInt("budget"));
		ResultSet rsu = db.executeQuery("select * from user where championship='" + championship + "'");
		while(rs.next()) {
			ret.addCompetitor(getUser(rsu.getString("name")));;
		}
		if (rs.getInt("ncomp") != ret.getnComp()) {
			System.out.println("Not all competitors have been found in the championship " + championship);
		}				
		return ret;
	}

	/**
	 * Return all the championships saved on the database.
	 * @return List<Championship>
	 * @throws SQLException
	 */
	public List<Championship> getAllChampionship() throws SQLException {
		List<Championship> lChmp = new ArrayList<Championship>();
		ResultSet rs = db.executeQuery("Select * from championship");
		while(rs.next()) {
			lChmp.add(getChampionship(rs.getString("name")));
		}
		return lChmp;
	}
	
	
	//Questa non dovrebbe servire, ma l'hai scritta quindi la lascio qua che magari mi sono bruciato qualcosa.
	//Se ti serve la lista basta chiamare il metodo getCompetitors() in Championship
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
