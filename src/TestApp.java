
public class TestApp {

	public static void main(String[] args) {
		
		Player p1 = new Player("Ciccio Valente", "Correggio", 23, 7788);
		System.out.println(p1);
		Goalkeeper p2 = new Goalkeeper("Lezzo schifoso","Bagnolo",23,1234);
		Goalkeeper p3 = new Goalkeeper("Bastardo cane","Modena",12,1233);
		Goalkeeper p4 = new Goalkeeper("Giallo Nero","Parma",64,11345);
		Goalkeeper p5 = new Goalkeeper("Sponge Bob","Bikini Bottom",342,1233544);
		Club Grenoble = new Club("Grenoble");
		Grenoble.addPlayer(p2, 1);
		Grenoble.addPlayer(p3, 8);
		Grenoble.addPlayer(p4, 5);
		Grenoble.addPlayer(p5, 0);
		
	}
}
