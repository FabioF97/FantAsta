package ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import database.DBManager;


public class TestApp {

	public static void main(String[] args) throws SQLException {

		/*
		Goalkeeper p = new Goalkeeper("Lafont", "Fiorentina", 23, 7788);
		Goalkeeper p2 = new Goalkeeper("Dragowski", "Fiorentina", 23, 7788);
		Goalkeeper p3 = new Goalkeeper("Sportiello", "Fiorentina", 23, 7788);
		System.out.println(p2);
		Defender p4 = new Defender("Milenkovic", "Fiorentina", 23, 7788);
		Defender p5 = new Defender("Pezzella", "Fiorentina", 23, 7788);
		Defender p6 = new Defender("Vitor Hugo", "Fiorentina", 23, 7788);
		Defender p7 = new Defender("Ceccherini", "Fiorentina", 23, 7788);
		Defender p8 = new Defender("Biraghi", "Fiorentina", 23, 7788);
		Defender p9 = new Defender("Olivera", "Fiorentina", 23, 7788);
		Defender p10 = new Defender("Hancko", "Fiorentina", 23, 7788);
		Defender p11 = new Defender("Laurini", "Fiorentina", 23, 7788);
		System.out.println(p3);
		Midfielder p12 = new Midfielder("Veretout", "Fiorentina", 23, 7788);
		Midfielder p13 = new Midfielder("Benassi", "Fiorentina", 23, 7788);
		Midfielder p14 = new Midfielder("Gerson", "Fiorentina", 23, 7788);
		Midfielder p15 = new Midfielder("Edimilson", "Fiorentina", 23, 7788);
		Midfielder p16 = new Midfielder("Dabo", "Fiorentina", 23, 7788);
		Midfielder p17 = new Midfielder("Cristòforo", "Fiorentina", 23, 7788);
		Midfielder p18 = new Midfielder("Eysseric", "Fiorentina", 23, 7788);
		Midfielder p19 = new Midfielder("Norgaard", "Fiorentina", 23, 7788);
		System.out.println(p4);
		Striker p20 = new Striker("Pjaca", "Fiorentina", 23, 7788);
		Striker p21 = new Striker("Zekhnini", "Fiorentina", 23, 7788);
		Striker p22 = new Striker("Mirallas", "Fiorentina", 23, 7788);
		Striker p23 = new Striker("Chiesa", "Fiorentina", 23, 7788);
		Striker p24 = new Striker("Simeone", "Fiorentina", 23, 7788);
		Striker p25 = new Striker("Thereau", "Fiorentina", 23, 7788);
		System.out.println(p5);
		Club c = new Club("Porelli");
		Championship ch = new Championship("Parrocchia", 50000000);
		User u = new User("Fenomeno", c, ch.getBudget());
		ch.addCompetitor(u);
		Club c2 = new Club("Scapoli");
		User u2 = new User("Muscio",c2,ch.getBudget());
		ch.addCompetitor(u2);
		
		System.out.println(c.sizeTeam());
		System.out.println(ch.getCompetitors().size());
		
		Striker p26 = new Striker("Ronaldo","Juventus",23,54);
		
		u.buyPlayer(p, 10);
		u.buyPlayer(p2, 10);
		u.buyPlayer(p3, 10);
		System.out.println(c.sizeTeam());
		
		u.buyPlayer(p4, 10);
		u.buyPlayer(p5, 10);
		u.buyPlayer(p6, 10);
		u.buyPlayer(p7, 10);
		u.buyPlayer(p8, 10);
		u.buyPlayer(p9, 10);
		u.buyPlayer(p10, 10);
		u.buyPlayer(p11, 10);
		System.out.println(c.sizeTeam());
		
		u.buyPlayer(p12, 10);
		u.buyPlayer(p13, 10);
		u.buyPlayer(p14, 10);
		u.buyPlayer(p15, 10);
		u.buyPlayer(p16, 10);
		u.buyPlayer(p17, 10);
		u.buyPlayer(p18, 10);
		u.buyPlayer(p19, 10);
		System.out.println(c.sizeTeam());
		ch.checkClub();
		
		u.buyPlayer(p20, 10);
		u.buyPlayer(p21, 10);
		u.buyPlayer(p22, 10);
		u.buyPlayer(p23, 10);
		u.buyPlayer(p24, 10);
		u.buyPlayer(p25, 10);
		
		
		u2.buyPlayer(p26, 432);
		System.out.println(u2.transfer(p26, u));
		System.out.println(c2.sizeTeam());
		System.out.println(c2);
		Collections.sort(u.getClub().getTeam());
		System.out.println(c);
		
		System.out.println("PRIMAAAA");
		
		u.sell(p22);
		System.out.println(p22);
		
		Collections.sort(c.getTeam());
		Collections.sort(c2.getTeam());
		System.out.println(c.sizeTeam());
		System.out.println(c.isFullTeam());
		System.out.println(c);
		System.out.println(c2);
		ch.checkClub();		
		*/
		
		List<Player> listone = new ArrayList<Player>();
		
		try {
			DBManager db = new DBManager();
			ResultSet rs = db.executeQuery("SELECT * FROM list_player");
			while(rs.next()) {
				listone.add(readPlayer(rs.getString("name"),rs.getString("team"),rs.getString("position"),rs.getInt("value"),rs.getInt("id")));
				System.out.println(readPlayer(rs.getString("name"),rs.getString("team"),rs.getString("position"),rs.getInt("value"),rs.getInt("id")));
			}
		} catch(SQLException e) {
			System.out.println("E' finito qua");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(listone.size());

	}
	
	public static Player readPlayer(String name, String team, String position, int value, int id) {
		Player p;
		if(position.equals("Gk")) {
			p = new Goalkeeper(name,team,value,id);
			return p;
		}
		if(position.equals("Def")) {
			p = new Defender(name,team,value,id);
			return p;
		}
		if(position.equals("Mid")) {
			p = new Midfielder(name,team,value,id);
			return p;
		}
		if(position.equals("Str")) {
			p = new Striker(name,team,value,id);
			return p;
		}
			System.out.println("Error in readPlayer");
			return null;
	}
	
	public  static void printRow(ResultSet rs) throws SQLException {
		System.out.println(
				"id=" + rs.getInt("id") + 
				", name=" + rs.getString("name") + 
				", position=" + rs.getString("position") +
				", team=" + rs.getString("team") +
				", value=" + rs.getInt("value") +
				", price=" + rs.getInt("price") +
				", visible=" + rs.getInt("visible"));
	}
}
