
import java.util.Collections;


public class TestApp {

	public static void main(String[] args) {

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
		
		System.out.println(c.sizeTeam());
		
		c.addPlayer(p, 12);
		c.addPlayer(p2, 12);
		c.addPlayer(p3, 12);
		System.out.println(c.sizeTeam());
		
		c.addPlayer(p4, 12);
		c.addPlayer(p5, 12);
		c.addPlayer(p6, 12);
		c.addPlayer(p7, 12);
		c.addPlayer(p8, 12);
		c.addPlayer(p9, 12);
		c.addPlayer(p10, 12);
		c.addPlayer(p11, 12);
		System.out.println(c.sizeTeam());
		
		c.addPlayer(p12, 12);
		c.addPlayer(p13, 12);
		c.addPlayer(p14, 12);
		c.addPlayer(p15, 12);
		c.addPlayer(p16, 12);
		c.addPlayer(p17, 12);
		c.addPlayer(p18, 12);
		c.addPlayer(p19, 12);
		System.out.println(c.sizeTeam());
		
		c.addPlayer(p20, 12);
		c.addPlayer(p21, 12);
		c.addPlayer(p22, 12);
		c.addPlayer(p23, 12);
		c.addPlayer(p24, 12);
		c.addPlayer(p25, 12);
		c.removePlayer(p20);
		c.removePlayer(p23);
		c.removePlayer(p20);
		Collections.sort(c.getTeam());
		System.out.println(c.sizeTeam());
		System.out.println(c.isFullTeam());
		System.out.println(c);
		
				

	}
}
