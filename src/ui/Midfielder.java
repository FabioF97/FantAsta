package ui;

/**
 * This class represents midfielders
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class Midfielder extends Player {

	/**
	 * Constructor with 4 params
	 * @param name					Player's name	
	 * @param team					Player's team
	 * @param value					Player's value
	 * @param id					Player's id
	 */
	public Midfielder(String name, String team, int value, int id) {
		super(name, team, "Mid", value, id);
		
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
	public Midfielder(String name, String team, int value, int id, int price, boolean visible) {
		super(name, team, "Mid", value, id, price, visible);
		
	}

}
