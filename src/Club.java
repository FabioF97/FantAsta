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
	private final int PLAYERS = 25;
	/*Represents the number of Goalkeepers of each team*/
	private final int NUMGOALKEEPERS = 3;
	/*Represents the number of Defenders of each team*/
	private final int NUMDEFENDERS = 8;
	/*Represents the number of Midfielders of each team*/
	private final int NUMMIDFIELDERS = 8;
	/*Represents the number of Strikers of each team*/
	private final int NUMSTRIKERS = 6;
	
	
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
	public void addPlayer(Player player, int price) {
		if(isFullTeam() == true) {
			System.out.println("This team is full!!");
			return;
		}
		if(player instanceof Goalkeeper) {
			if(isFullGk() == true) {
				System.out.println("Goalkeepers are enough");
				return;
			}
			else {
				//togliere il budget al giocatore che ha acquistato il giocatore
				incGk();
				team.add(player);
				System.out.println("Gk inserted! -->" + goalkeepers);
				return;
			}
		}
		if(player instanceof Defender) {
			if(isFullDef() == true) {
				System.out.println("Defenders are enough");
				return;
			}
			else {
				//togliere il budget al giocatore che ha acquistato il giocatore
				incDef();
				team.add(player);
				System.out.println("Def inserted!");
				return;
			}
		}
		if(player instanceof Midfielder) {
			if(isFullMid() == true) {
				System.out.println("Midfielders are enough");
				return;
			}
			else {
				//togliere il budget al giocatore che ha acquistato il giocatore
				incMid();
				team.add(player);
				System.out.println("Mid inserted!");
				return;
			}
		}
		if(player instanceof Striker) {
			if(isFullStr() == true) {
				System.out.println("Strikers are enough");
				return;
			}
			else {
				//togliere il budget al giocatore che ha acquistato il giocatore
				incStr();
				team.add(player);
				System.out.println("Str inserted!");
				return;
			}
		}
	}

	@Override
	public String toString() {
		return "Club [name=" + name + ", team=" + team + ", goalkeepers=" + goalkeepers + ", defenders=" + defenders
				+ ", midfielders=" + midfielders + ", strikers=" + strikers + "]";
	}
	
}
