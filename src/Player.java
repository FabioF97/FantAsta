/**
 * This class describes the characteristics of a player.
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class Player {
	
	/** Minimum selling price of a player */
	private final int MinPrice = 1;
	
	
	/** Player's name*/
	private String name;
	/** Player's team*/
	private String team;
	/** Player's value*/
	private int value;
	/** price at which the player is bought */
	private int price;
	/** Player's id*/
	private int id;
	
	
	/**
	 * Constructor with all params
	 * @param name					Player's name	
	 * @param team					Player's team
	 * @param value					Player's value
	 * @param id					Player's id
	 */
	public Player(String name, String team, int value, int id) {
		super();
		this.name = name;
		this.team = team;
		this.value = value;
		this.id = id;
		this.price = MinPrice;
	}
	
	/**
	 * Get player's price
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Set player's price
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Get player's id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set player's id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets player's name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets player's name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
			
	/**
	 * Gets player's team
	 * @return team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * Set player's team
	 * @param team
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	
	/**
	 * Get player's value
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Set player's value
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	
	
	

}
