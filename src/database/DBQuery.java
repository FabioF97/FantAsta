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
	public Player role (String name, String team, String position, int value, int id, int price, boolean visible) throws SQLException {
		if (position.equals("Gk")) {
			return new Goalkeeper(name, team, value, id, price, visible);
		}
		if (position.equals("Def")) {
			return new Defender(name, team, value, id, price, visible);
		}
		if (position.equals("Mid")) {
			return new Midfielder(name, team, value, id, price, visible);
		}
		else {
			return new Striker(name, team, value, id, price, visible);
		}
	}
	
	/**
	 * Creates a new list of player for a new championship
	 * @param championship
	 * @throws SQLException
	 */
	public void createList(String championship) throws SQLException {
		db.executeUpdate("CREATE TABLE " + championship + " (" +
				"id INTEGER, " +
				"position TEXT, " +
				"name	TEXT, " +
				"team	TEXT, " +
				"value INTEGER, " +
				"price INTEGER, " +
				"visible INTEGER, " +
				"club TEXT DEFAULT null, " +
				"PRIMARY KEY(id) " +
				")");
		db.executeUpdate("INSERT INTO " + championship + 
						 " SELECT * FROM list_player");
	}
	
	/**
	 * Returns the Club named club.
	 * @return List<Player>
	 * @throws SQLException
	 */
	public Club getClub(String club, String championship) throws SQLException {
		Club ret = new Club(club);
		ResultSet rs = db.executeQuery("select * "	+ "from " + championship + " where Club='" + club + "'");
		while(rs.next()) {
			ret.addPlayer(role(rs.getString("name"), rs.getString("team"), rs.getString("position"), rs.getInt("value"), rs.getInt("id"), rs.getInt("price"), toBoolean(rs.getInt("visible"))));
		}
		Collections.sort(ret.getTeam());
		if (!(ret.isFullTeam())){
			System.out.println("Il club " + club + " non è completo! NUMGOALKEEPERS=" + ret.getGoalkeepers() + " NUMDEFENDERS=" + ret.getDefenders() + " NUMMIDFIELDERS=" + ret.getMidfielders() + " NUMSTRIKERS=" + ret.getStrikers());
		} else {
			if(ret.getGoalkeepers()== Club.NUMGOALKEEPERS && ret.getDefenders() == Club.NUMDEFENDERS && ret.getMidfielders() == Club.NUMMIDFIELDERS && ret.getStrikers() == Club.NUMSTRIKERS) {
				ret.setGoalkeepers(Club.NUMGOALKEEPERS);
				ret.setDefenders(Club.NUMDEFENDERS);
				ret.setMidfielders(Club.NUMMIDFIELDERS);
				ret.setStrikers(Club.NUMSTRIKERS);
			}
			else {
				System.out.println("C'è un problema di riempimento in qualche ruolo per il team " + club);
			}
		}
		return ret;
	}

	/**
	 * Returns the User named user.
	 * @param user
	 * @return User
	 * @throws SQLException
	 */
	public User getUser(String user, String championship) throws SQLException {
		ResultSet rs = db.executeQuery("select * from user where username='" + user + "'");
		int budget = rs.getInt("budget");
		Club club = getClub(rs.getString("club"), championship);
		return new User(user, club, budget);
	}

	/**
	 * Returns the Championship named championship.
	 * @param championship
	 * @return Championship
	 * @throws SQLException
	 */
	public Championship getChampionship(String championship) throws SQLException {
		ResultSet rs = db.executeQuery("select * from championship where name='" + championship + "'");
		Championship ret = new Championship(championship, rs.getString("date"), rs.getInt("budget"));
		ResultSet rsu = db.executeQuery("select * from user where championship='" + championship + "'");
		while(rsu.next()) {
			ret.addCompetitor(getUser(rsu.getString("name"), championship));;
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
	
	/**
	 * Saves the attributes of a player on the database
	 * @throws SQLException
	 */
	public void playerUpdate(int id, int price, boolean visible, String club, String championship) throws SQLException {
		int n;
		if(visible == true)
			n = 1;
		else
			n = 0;
		db.executeUpdate(
				"UPDATE " + championship + " SET price="+ price + ", " + 
						"club='" + club + "', " +
						"visible=" + n +
				"WHERE id=" + id);
	}
	
	/**
	 * Saves the attributes of a user on the database
	 * @throws SQLException
	 */
	public void userUpdate(String username, int budget) throws SQLException {
		db.executeUpdate(
				"UPDATE user SET budget="+ budget + 
				"WHERE username='" + username + "'");
	}
	
	/**
	 * Inserts a user in the database
	 * @throws SQLException
	 */
	public void userInsert(String username, String club, String championship, int budget) throws SQLException {	
		db.executeUpdate("INSERT INTO user (username,club,championship,budget)" +
						  "VALUES(" + username + ", '" + club + "', '" +
						  championship +"'," + budget + ")");
	}
	
	/**
	 * Inserts a championship in the database
	 * @throws SQLException
	 */
	public void championshipInsert(String name, String date, int ncomp, int budget) throws SQLException {	
		db.executeUpdate("INSERT INTO user (name,date,ncomp,budget)" +
						  "VALUES(" + name + ", '" + date + "', '" +
						  ncomp +"'," + budget + ")");
	}
	
	
	public void deleteChampionship (String championship) throws SQLException {
		db.executeUpdate("DROP table " + championship);
		db.executeUpdate("DELETE from user where championship='" + championship + "'");
		db.executeUpdate("DELETE from championship where name='" + championship +"'");	
	}
	
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
