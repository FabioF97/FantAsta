package ui;
/**
 * The class describes each user of each championship.
 * Everyone has a name, a team and a budget
 * @author Fabio Polito, Fabio Fontana
 *
 */
public class User {
	
	private String username;
	private Club club;
	private int budget;
	
	
	
	/**
	 * Constructor with 1 param
	 * @param username				username
	 */
	// Non inizializzo gli atributi club e budget è un problema?
	public User(String username) {
		super();
		this.username = username;
	}

	/**
	 * Constructor with 3 params
	 * @param username				username
	 * @param club					user's club
	 * @param budget				user's budget
	 */
	public User(String username, Club club, int budget) {
		super();
		this.username = username;
		this.club = club;
		this.budget = budget;
	}

	/**
	 * Gets the username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the club
	 * @return budget
	 */
	public Club getClub() {
		return club;
	}

	/**
	 * Sets the club
	 * @param club
	 */
	public void setClub(Club club) {
		this.club = club;
	}

	/**
	 * Gets Budget
	 * @return budget
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * Sets Budget
	 * @param budget
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	/**
	 * This method decreases the user's budget
	 * @param withdraw
	 */
	public void withdraw(int withdraw) {
		this.budget = this.budget - withdraw;
	}
	
	/**
	 * This method increases the user's budget
	 * @param deposit
	 */
	public void deposit(int deposit) {
		this.budget = this.budget + deposit;
	}
	
	/**
	 * An user can buy a player, his price will be subtracted from the budget
	 * @param player
	 * @param price
	 * @return boolean
	 */
	public boolean buyPlayer(Player player, int price) {
		if(getBudget() < price)
			return false;
		if(club.addPlayer(player) == true) {
			withdraw(price);
			player.setPrice(price);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * If the player is sold abroad the user has the right to be returned 
	 * the invested amount to buy it
	 * @param player
	 * @return boolean
	 */
	public boolean sell(Player player) {
		if(club.removePlayer(player) == true) {
			deposit(player.getPrice());
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * When the user releases one of his players, the player's value is returned
	 * @param player
	 * @return boolean
	 */
	public boolean release(Player player) {
		if(club.removePlayer(player) == true) {
			deposit(player.getValue());
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Method used for the transfer at no cost during the exchange from one user to another
	 * @param player
	 * @param user
	 * @return boolean
	 */
	public boolean transfer(Player player, User user) {
		if(club.removePlayer(player) == true) {
			user.getClub().getTeam().add(player);
			user.getClub().inc(player);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return club.getName();
	}

}
