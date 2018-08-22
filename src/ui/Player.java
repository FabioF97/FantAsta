package ui;
/**
 * This class describes the characteristics of a player.
 * The class is abstract to avoid instancing players without a role.
 * @author Fabio Polito, Fabio Fontana
 *
 */
public abstract class Player implements Comparable<Player>{
	
	/** Minimum selling price of a player */
	private final int MINPRICE = 0;
	
	
	private String name;
	private String team;
	private String position; 
	private int value;
	private int price;
	private int id;
	private boolean visible;
	
	
	


	/**
	 * Constructor with all params
	 * @param name					Player's name	
	 * @param team					Player's team
	 * @param position				Player's position
	 * @param value					Player's value
	 * @param id					Player's id
	 * @param visible				If it's true, the player will be visible in the list
	 */
	public Player(String name, String team, String position, int value, int id) {
		super();
		this.name = name;
		this.team = team;
		this.position = position;
		this.value = value;
		this.id = id;
		this.visible = true;
		this.price = MINPRICE;
	}
	
	/**
	 * 
	 * @param name					Player's name
	 * @param team					Player's team
	 * @param position				Player's position
	 * @param value					Player's value
	 * @param id					Player's id
	 * @param price					Price paid by the user who owns this player
	 * @param visible				If it's true, the player will be visible in the list
	 */
	public Player(String name, String team, String position, int value, int id, int price, boolean visible) {
		super();
		this.name = name;
		this.team = team;
		this.position = position;
		this.value = value;
		this.id = id;
		this.price = price;
		this.visible = visible;
	}
	
	/**
	 * Gets player's price
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Sets player's price
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Gets player's id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets player's id
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
	 * Sets player's team
	 * @param team
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	
	/**
	 * Gets player's position
	 * @return position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Sets player's position
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Gets player's value
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Sets player's value
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	
	/**
	 * Sets visible boolean
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}
	

	/**
	 * Gets visible boolean
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	/**
	 * Method used to sort the team:
	 * The players are sorted by looking first at the position
	 * and then the player's name
	 */
	@Override
	public int compareTo(Player player) {
		if(this.position == "Gk") {
			if(!player.getPosition().equals(this.position)) {
				return -1;
			}
			else {
				return this.name.compareTo(player.getName());
			}
		}
		if(this.position == "Def") {
			if(player.getPosition() == "Gk") {
				return 1;
			}
			if(this.position.compareTo(player.getPosition()) == 0) {
				return this.name.compareTo(player.getName());
			}
			else {
				return -1;
			}
		}
		if(this.position == "Mid") {
			if(player.getPosition() == "Gk" || player.getPosition() == "Def") {
				return 1;
			}
			if(this.position.compareTo(player.getPosition()) == 0) {
				return this.name.compareTo(player.getName());
			}
			else {
				return -1;
			}
		}
		if(this.position == "Str") {
			if(!player.getPosition().equals("Str")) {
				return 1;
			}
			if(this.position.compareTo(player.getPosition()) == 0) {
				return this.name.compareTo(player.getName());
			}
		}
		System.out.println("Non è riuscito a fare nulla");
		return 2;
	}
	
	
	/**
	 * Shows the attributes of the class
	 */
	@Override
	public String toString() {
		return "[" + name + ", " + position + ", " + visible +"]";
	}
	

}
