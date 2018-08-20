
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
	 * Constructor with 3 params
	 * @param username
	 * @param club
	 * @param budget
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

	
	@Override
	public String toString() {
		return "User [username=" + username + ", club=" + club + ", budget=" + budget + "]";
	}

}
