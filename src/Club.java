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
	private final int players = 25;
	/*Represents the number of Goalkeepers of each team*/
	private final int numGoalkeepers = 3;
	/*Represents the number of Defenders of each team*/
	private final int numDefenders = 8;
	/*Represents the number of Midfielders of each team*/
	private final int numMidfielders = 8;
	/*Represents the number of Strikers of each team*/
	private final int numStrikers = 6;
	
	/*Name represents the name of the club, chosen by its owner */
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
	 * @param name The name of the team
	 */
	public Club(String name) {
		super();
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
	 * check if the maximum number of players in the team has been reached
	 * @return boolean
	 */
	public boolean isFullTeam() {
		if(team.size() == players) {
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
		if(goalkeepers == numGoalkeepers)
			return true;
		else 
			return false;
	}
	
	/**
	 * check if the maximum number of defenders in the team has been reached
	 * @return boolean
	 */
	public boolean isFullDef() {
		if(defenders == numDefenders)
			return true;
		else 
			return false;
	}
	
	/**
	 * check if the maximum number of midfielders in the team has been reached
	 * @return boolean
	 */
	public boolean isFullMid() {
		if(midfielders == numMidfielders)
			return true;
		else 
			return false;
	}
	
	/**
	 * check if the maximum number of strikers in the team has been reached
	 * @return boolean
	 */
	public boolean isFullStr() {
		if(strikers == numStrikers)
			return true;
		else 
			return false;
	}
	
}
