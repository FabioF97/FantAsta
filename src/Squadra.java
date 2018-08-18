import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe rappresenta Le squadre. A ogni partecipante all'asta 
 * spetta una rosa che deve andare a riempire nel corso della medesima
 * per un totale di 25 giocatori: 3 portieri, 8 difensori, 8 centrocampisti
 * e 6 attaccanti.
 * @author Fabio Fontana
 *
 */

public class Squadra {
	
	//Costante che rappresenta il numero obbligatorio di giocatori 
	//all'interno della rosa alla fine dell'asta
	private final int numGiocatori = 25;
	
	private String nomeSquadra;
	private List<Giocatore> rosa;
	
	public Squadra() {
		super();
		rosa = new ArrayList<Giocatore>();
	}
	
	public Squadra(String nomeSquadra) {
		super();
		rosa = new ArrayList<Giocatore>();
	}
	
	public Squadra(String nomeSquadra, List<Giocatore> rosa) {
		super();
		this.nomeSquadra = nomeSquadra;
		this.rosa = rosa;
	}

	public String getNomeSquadra() {
		return nomeSquadra;
	}

	public void setNomeSquadra(String nomeSquadra) {
		this.nomeSquadra = nomeSquadra;
	}

	public List<Giocatore> getRosa() {
		return rosa;
	}

	public void setRosa(List<Giocatore> rosa) {
		this.rosa = rosa;
	}
	
}
