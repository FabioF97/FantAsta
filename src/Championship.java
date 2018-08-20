import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	private SimpleDateFormat date;
	/** List of users participating in the championship */
	private List<User> Competitors;
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
		this.Competitors = new ArrayList<User>();
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

	public SimpleDateFormat getDate() {
		return date;
	}

	public void setDate(SimpleDateFormat date) {
		this.date = date;
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
		Competitors.add(u);
		incNComp();
	}
	
	/**
	 * Removes a user to the championship.
	 * @param u
	 */
	public void removeCompetitor (User u) {
		Competitors.remove(u);
		decNComp();
	}
	
}
