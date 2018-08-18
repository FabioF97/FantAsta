
public class TestApp {

	public static void main(String[] args) {

		Goalkeeper p2 = new Goalkeeper("Ciccio Valente", "Correggio", 23, 7788);
		System.out.println(p2);
		Defender p3 = new Defender("Ciccio Valente", "Correggio", 23, 7788);
		System.out.println(p3);
		Midfielder p4 = new Midfielder("Ciccio Valente", "Correggio", 23, 7788);
		System.out.println(p4);
		Striker p5 = new Striker("Ciccio Valente", "Correggio", 23, 7788);
		System.out.println(p5);
		Club c = new Club("Porelli");
		c.addPlayer(p2, 12);
		c.addPlayer(p2, 12);
		c.addPlayer(p2, 12);
		
		c.addPlayer(p3, 12);
		c.addPlayer(p3, 12);
		c.addPlayer(p3, 12);
		c.addPlayer(p3, 12);
		c.addPlayer(p3, 12);
		c.addPlayer(p3, 12);
		
		c.addPlayer(p4, 12);
		c.addPlayer(p4, 12);
		c.addPlayer(p4, 12);
		c.addPlayer(p4, 12);
		c.addPlayer(p4, 12);
		c.addPlayer(p4, 12);
		c.addPlayer(p4, 12);
		c.addPlayer(p4, 12);
		c.addPlayer(p4, 12);
		
		c.addPlayer(p5, 12);
		c.addPlayer(p5, 12);
		c.addPlayer(p5, 12);
		c.addPlayer(p5, 12);
		c.addPlayer(p5, 12);
		c.addPlayer(p5, 12);
		c.addPlayer(p5, 12);

	}
}
