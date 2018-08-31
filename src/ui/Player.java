package ui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

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
	private BooleanProperty vsbl;
	//In this textfield the admin will write player's price
	private TextField priceTab;
	//Button used to buy a player
	private Button buy;
	//Button used to buy a player
	private Button sell;
	//Button used to buy a player
	//private Button release;
	//Button used to buy a player
	private Button send;
	//Used to display the team of destination
	private ChoiceBox<User> choice; 
	
	
	


	/**
	 * Constructor with all params
	 * @param name					Player's name	
	 * @param team					Player's team
	 * @param position				Player's position
	 * @param value					Player's value
	 * @param id					Player's id
	 */
	public Player(String name, String team, String position, int value, int id) {
		super();
		this.name = name;
		this.team = team;
		this.position = position;
		this.value = value;
		this.id = id;
		this.vsbl = new SimpleBooleanProperty();
		vsbl.set(true);
		this.price = MINPRICE;
		priceTab = new TextField();
		buy = new Button("Buy");
		buy.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (checkBuy()) {
					System.out.println("Player: " + name + " goes to -> " + ((String) choice.getValue().getClub().getName()));
				vsbl.set(false);
				}				
			}
		});
		sell = new Button("Remove player");
		sell.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Player: " + name + " Sold");
				vsbl.set(true);
			}
		});
		send = new Button("Send");
		send.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (checkSend()) {
					System.out.println("Player: " + name + " sent to " + ((String) choice.getValue().getClub().getName()));
					vsbl.set(true);
				}	
			}
		});
		choice = new ChoiceBox<User>();
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
		this.vsbl = new SimpleBooleanProperty();
		this.vsbl.set(visible);
		priceTab = new TextField();
		buy = new Button("Buy");
		buy.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (checkBuy()) {
					System.out.println("Player: " + name + " goes to -> " + ((String) choice.getValue().getClub().getName()));
				vsbl.set(false);
				}				
			}
		});
		sell = new Button("Remove player");
		sell.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Player: " + name + " Sold");
				System.out.println(vsbl.get());
				vsbl.set(true);
			}
		});
		send = new Button("Send");
		send.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (checkSend()) {
					System.out.println("Player: " + name + " sent to " + ((String) choice.getValue().getClub().getName()));
					vsbl.set(true);
				}		
			}
		});
		choice = new ChoiceBox<User>();
	}
	
	public BooleanProperty visibleProperty() {
		return vsbl;
	}

	public Button getSell() {
		return sell;
	}

	public void setSell(Button sell) {
		this.sell = sell;
	}

	public Button getSend() {
		return send;
	}

	public void setSend(Button send) {
		this.send = send;
	}

	public TextField getPriceTab() {
		return priceTab;
	}

	public void setPriceTab(TextField priceTab) {
		this.priceTab = priceTab;
	}

	public Button getBuy() {
		return buy;
	}

	public void setBuy(Button buy) {
		this.buy = buy;
	}

	public ChoiceBox<User> getChoice() {
		return choice;
	}

	public void setChoice(ChoiceBox<User> choice) {
		this.choice = choice;
	}
	
	public User destination() {
		return choice.getValue();
	}
	
	public int buyPrice() {
		return Integer.parseInt(priceTab.getText());
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
	 * Sets the choicebox parameters.
	 * @param list
	 */
	public void fillChoiceBox(ObservableList<User> list) {
		choice.getItems().addAll(list);
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
	
	public boolean checkBuy()
	{
		if (choice.getValue() == null) {
			return false;
		}
		try {
			Integer.parseInt(priceTab.getText());
		}catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public boolean checkSend() {
		if (choice.getValue() == null) {
			return false;
		}
		return true;
		
	}
	
	/**
	 * Shows the attributes of the class
	 */
	@Override
	public String toString() {
		return "[" + name + ", " + position + "]";
	}
	

}
