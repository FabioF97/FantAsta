package ui;

/**
 * This class represents defenders
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class Defender extends Player{

	/**
	 * Constructor with 4 params
	 * @param name					Player's name	
	 * @param team					Player's team
	 * @param value					Player's value
	 * @param id					Player's id
	 */
	public Defender(String name, String team, int value, int id) {
		super(name, team, "Def", value, id);
		
	}
	

}
