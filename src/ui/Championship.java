package ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represent  a championship.
 * A maximum of 12 people can partecipate in a championship.
 * Each championship has an initial budget enstablished at the time of creation.
 * @author Fabio Polito, Fabio Fontana
 *
 */

public class Championship {
	
	private String name;
	/** Date of creation of the championship  */
	String date; 
	/** List of users participating in the championship */
	private List<User> competitors;
	/** Number of users participating in the championship  */
	private int nComp;
	private int budget;
	

	/**
	 * Constructor
	 * @param name						Name of the Championship
	 * @param budget					Initial championship budget
	 */
	public Championship(String name, int budget) {
		super();
		this.name = name;
		this.budget = budget;
		this.nComp = 0;
		this.competitors = new ArrayList<User>();
		this.date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	}
	
	/**
	 * Constructor with 3 params
	 * @param name						Name of the Championship
	 * @date 							Date of creation
	 * @param budget					Initial championship budget
	 */
	public Championship(String name, String date, int budget) {
		super();
		this.name = name;
		this.budget = budget;
		this.nComp = 0;
		this.competitors = new ArrayList<User>();
		this.date = date;
	}

	/**
	 * Gets championship's name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets championship's name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
		
	/**
	 * Gets the number of Competitors
	 * @return nComp
	 */
	public int getnComp() {
		return nComp;
	}

	/**
	 * Sets the number of competitor.
	 * @param nComp
	 */
	public void setnComp(int nComp) {
		this.nComp = nComp;
	}
	
	/**
	 * Gets championship budget.
	 * @return budget
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * Sets championship budget.
	 * @param budget
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	/**
	 * Gets the date of creation of the championship.
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date of creation of the championship.
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Gets the list of users
	 * @return competitors
	 */
	public List<User> getCompetitors() {
		return competitors;
	}

	/**
	 * Sets competitors
	 * @param competitors
	 */
	public void setCompetitors(List<User> competitors) {
		this.competitors = competitors;
	}

	/**
	 * Increase by one the number of Competitors.
	 */
	public void incNComp() {
		++nComp;
	}
	
	/**
	 * Decrease by one the number of Competitors.
	 */
	public void decNComp() {
		--nComp;
	}
	
	/**
	 * Adds a user to the championship.
	 * @param u
	 */
	public void addCompetitor (User u) {
		competitors.add(u);
		incNComp();
	}
	
	/**
	 * Removes a user to the championship.
	 * @param u
	 */
	public void removeCompetitor (User u) {
		competitors.remove(u);
		decNComp();
	}
	
	/**
	 * Check if all the team are full.
	 * @return boolean
	 */
	public boolean checkClub() {
		boolean ret = true;
		for (User u : competitors) {
			if (!(u.getClub().isFullTeam()))
			{
				if(u.getClub().getTeam().size() < Club.PLAYERS) {
					System.out.println("there are too few players in " + u.getClub().getName() + "!");
				}
				else {
					System.out.println("there are too many players in " + u.getClub().getName() + "!");
				}
				ret = false;
			}
		}
		return ret;
	}

	/**
	 * Show the username of the competitors in the championship.
	 */
	public void showCompetitor() {
		for (User u : competitors) {
			System.out.println(u.getUsername());
		}
	}
	/**
	 * Returns the date of creation of the championship.
	 * @return String
	 */
	public String ShowDate() {
		return "Championship [date= " + date + " ]";
	}
	
	@Override
	public String toString() {
		return (name + " date: " + date);
	}

}
