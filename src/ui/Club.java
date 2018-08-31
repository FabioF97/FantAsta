package ui;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a team. Each participant has a rose that must go to fill
 * in the course for a total of 25 players: 3 goalkeepers,
 * 8 defenders, 8 midfielders and 6 strikers.
 * @author Fabio Polito, Fabio Fontana
 *
 */

public class Club {
	
	/*This constant value represents the number of players 
	 * that will have to be part of the team available to each user*/
	public static final int PLAYERS = 25;
	/*Represents the number of Goalkeepers of each team*/
	public static final int NUMGOALKEEPERS = 3;
	/*Represents the number of Defenders of each team*/
	public static final int NUMDEFENDERS = 8;
	/*Represents the number of Midfielders of each team*/
	public static final int NUMMIDFIELDERS = 8;
	/*Represents the number of Strikers of each team*/
	public static final int NUMSTRIKERS = 6;
	
	
	

	private String name;
	/*In this list all 25 players who make up the team are saved*/
	private List<Player> team;
	/*Number of goalkeepers currently in the team*/
	private int goalkeepers;
	/*Number of defenders currently in the team*/
	private int defenders;
	/*Number of midfielders currently in the team*/
	private int midfielders;
	/*Number of strikers currently in the team*/
	private int strikers;
	
	/**
	 * Constructor with one param
	 * @param name 				Team's name
	 */
	public Club(String name) {
		super();
		this.name = name;
		team = new ArrayList<Player>();
		this.goalkeepers = 0;
		this.defenders = 0;
		this.midfielders = 0;
		this.strikers = 0;
	}

	/**
	 *  It returns the name of the team
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the name of the team
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the List(team)
	 * @return team
	 */
	public List<Player> getTeam() {
		return team;
	}

	/**
	 * Sets the List(team)
	 * @param team
	 */
	public void setTeam(List<Player> team) {
		this.team = team;
	}
	
	/**
	 * Gets the number of goalkeepers
	 * @return goalkeepers
	 */
	public int getGoalkeepers() {
		return goalkeepers;
	}

	/**
	 * Sets the number of goalkeepers
	 * @param goalkeepers
	 */
	public void setGoalkeepers(int goalkeepers) {
		this.goalkeepers = goalkeepers;
	}
	
	/**
	 * Gets the number of defenders
	 * @return defenders
	 */
	public int getDefenders() {
		return defenders;
	}

	/**
	 * Sets the number of defenders
	 * @param defenders
	 */
	public void setDefenders(int defenders) {
		this.defenders = defenders;
	}

	/**
	 * Gets the number of midfielders
	 * @return midfielders
	 */
	public int getMidfielders() {
		return midfielders;
	}

	/**
	 * Sets the number of midfielders
	 * @param midfielders
	 */
	public void setMidfielders(int midfielders) {
		this.midfielders = midfielders;
	}

	/**
	 * Gets the number of strikers
	 * @return strikers
	 */
	public int getStrikers() {
		return strikers;
	}

	/**
	 * Sets the number of strikers
	 * @param strikers
	 */
	public void setStrikers(int strikers) {
		this.strikers = strikers;
	}
	
	
	/**
	 * Increase the number of goalkeeper by one
	 */
	public void incGk() {
		++goalkeepers;
	}
	
	/**
	 * Decrease the number of goalkeeper by one
	 */
	public void decGk() {
		--goalkeepers;
	}
	
	/**
	 * Increase the number of defenders by one
	 */
	public void incDef() {
		++defenders;
	}
	
	/**
	 * Decrease the number of defenders by one
	 */
	public void decDef() {
		--defenders;
	}
	
	/**
	 * Increase the number of midfielders by one
	 */
	public void incMid() {
		++midfielders;
	}
	
	/**
	 * Decrease the number of midfielders by one
	 */
	public void decMid() {
		--midfielders;
	}
	
	/**
	 * Increase the number of strikers by one
	 */
	public void incStr() {
		++strikers;
	}
	
	/**
	 * Decrease the number of strikers by one
	 */
	public void decStr() {
		--strikers;
	}
	
	
	/**
	 * Decrease the number of players based on position
	 * @param player
	 */
	public void dec(Player player) {
		if(player instanceof Goalkeeper) {
			decGk();
			return;
		}
		if(player instanceof Defender) {
			decDef();
			return;
		}
		if(player instanceof Midfielder) {
			decMid();
			return;
		}
		if(player instanceof Striker) {
			decStr();
			return;
		}
		System.out.println("Player's position unknown in dec");
	}
	
	/**
	 * Increase the number of players based on position
	 * @param player
	 */
	public void inc(Player player) {
		if(player instanceof Goalkeeper) {
			incGk();
			return;
		}
		if(player instanceof Defender) {
			incDef();
			return;
		}
		if(player instanceof Midfielder) {
			incMid();
			return;
		}
		if(player instanceof Striker) {
			incStr();
			return;
		}
		System.out.println("Player's position unknown in inc");
	}
	
	
	/**
	 * Return the team's size
	 * @return int
	 */
	public int sizeTeam() {
		return team.size();
	}
	/**
	 * check if the maximum number of players in the team has been reached
	 * @return boolean
	 */
	public boolean isFullTeam() {
		if(team.size() == PLAYERS) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * check if the maximum number of goalkeepers in the team has been reached
	 * @return boolean
	 */
	public boolean isFullGk() {
		if(goalkeepers == NUMGOALKEEPERS)
			return true;
		else 
			return false;
	}
	
	/**
	 * check if the maximum number of defenders in the team has been reached
	 * @return boolean
	 */
	public boolean isFullDef() {
		if(defenders == NUMDEFENDERS)
			return true;
		else 
			return false;
	}
	
	/**
	 * check if the maximum number of midfielders in the team has been reached
	 * @return boolean
	 */
	public boolean isFullMid() {
		if(midfielders == NUMMIDFIELDERS)
			return true;
		else 
			return false;
	}
	
	/**
	 * check if the maximum number of strikers in the team has been reached
	 * @return boolean
	 */
	public boolean isFullStr() {
		if(strikers == NUMSTRIKERS)
			return true;
		else 
			return false;
	}
	
	/**
	 * With this method you can add a player to the team
	 * @param player
	 * @param price
	 */
	public boolean addPlayer(Player player) {
		if(isFullTeam() == true) {
			System.out.println("This team is full!!");
			return false;
		}
		if(player instanceof Goalkeeper) {
			if(isFullGk() == true) {
				System.out.println("Goalkeepers are enough");
				return false;
			}
			else {
				incGk();
				team.add(player);
				return true;
			}
		}
		if(player instanceof Defender) {
			if(isFullDef() == true) {
				System.out.println("Defenders are enough");
				return false;
			}
			else {
				incDef();
				team.add(player);
				return true;
			}
		}
		if(player instanceof Midfielder) {
			if(isFullMid() == true) {
				System.out.println("Midfielders are enough");
				return false;
			}
			else {
				incMid();
				team.add(player);
				return true;
			}
		}
		if(player instanceof Striker) {
			if(isFullStr() == true) {
				System.out.println("Strikers are enough");
				return false;
			}
			else {
				incStr();
				team.add(player);
				return true;
			}
		}
		System.out.println("Player's position is unknown...");
		return false;
	}
	
	/**
	 * Remove a player from the team.
	 * @param p player to remove
	 */
	public boolean removePlayer (Player p) {
		if (team.contains(p)) {
				team.remove(p);
				if(p instanceof Goalkeeper) {
					decGk();
					return true;
				}
				if(p instanceof Defender) {
					decDef();
					return true;
				}
				if(p instanceof Midfielder) {
					decMid();
					return true;
				}
				if(p instanceof Striker) {
					decStr();
					return true;
				}
		} else {
			
			System.out.println(p.getName() + " doesn't belong to the club " + name);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Club [name=" + name + ", team=" + team + ", goalkeepers=" + goalkeepers + ", defenders=" + defenders
				+ ", midfielders=" + midfielders + ", strikers=" + strikers + "]";
	}
	
}
