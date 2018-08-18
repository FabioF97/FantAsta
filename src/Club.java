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
	 * that will have to be part of the rose available to each user*/
	private final int players = 25;
	
	/*Name represents the name of the club, chosen by its owner */
	private String name;
	/*In this list all 25 players who make up the team are saved*/
	private List<Player> team;
	
	/**
	 * Constructor without params
	 */
	public Club() {
		super();
		team = new ArrayList<Player>();
	}
	
	/**
	 * Constructor with one param
	 * @param name
	 */
	public Club(String name) {
		super();
		team = new ArrayList<Player>();
	}
	
	/**
	 * Constructor with two params
	 * @param name
	 * @param team
	 */
	public Club(String name, List<Player> team) {
		super();
		this.name = name;
		this.team = team;
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
	 * Returns the number of players playing in each team
	 * @return players
	 */
	public int getPlayers() {
		return players;
	}
	
}
