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

}
