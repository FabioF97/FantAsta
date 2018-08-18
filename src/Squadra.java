
public class Squadra {
	
	private String nomeSquadra;
	private List<Giocatore> rosa;
	
	public Squadra() {
		super();
		this.nomeSquadra = new ArrayList<Giocatore>();
	}
	
	public Squadra(String nomeSquadra, List<Giocatore> rosa) {
		super();
		this.nomeSquadra = nomeSquadra;
		this.rosa = rosa;
	}
	
	
	
}
