package Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.GiocatoreDAO;
import DAO.ListinoDAO;
import DAO.SocietaDAO;
import Database.ConnessioneDatabase;
import ImplementazioniPostgresDAO.GiocatoreImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.GiocatoreImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.ListinoImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.SocietaImplementazionePostgresDAO;
import Model.Acquisto;
import Model.Borsa;
import Model.Giocatore;
import Model.Listino;
import Model.Societa;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
public class Controller {
	
	/** The b. */
	Borsa b;
	
	/** The g. */
	Giocatore g;

	public Controller() {
	}
	
	
	/**
	 * Nuova borsa.
	 *
	 * @param citta the citta
	 * @throws SQLException 
	 */
	public void nuovaBorsa(String citta){
		b=new Borsa(citta);
		ListinoDAO l=new ListinoImplementazionePostgresDAO();
		// assegno al listino in memoria il risultato che ottengo dal db
		b.setListino(l.leggiListinoDB(b));
	}

	/**
	 * Gets the citta borsa.
	 *
	 * @return the citta borsa
	 */
	public String getCittaBorsa() {
		if (b==null)
			return "";
		else
			return b.getCitta();
	}

	/**
	 * Nuova societa.
	 *
	 * @param nomeSocieta the nome societa
	 * @param prezzoAzione the prezzo azione
	 * @return the string
	 * @throws SQLException 
	 */
	public String nuovaSocieta(String nomeSocieta, String prezzoAzione){
		// TODO Verificare che la societa non esista gi�
		float p;
		String errore="OK";
		try {
			p=Float.parseFloat(prezzoAzione);
			// Crea in memoria una nuova societa
			Societa s= new Societa(nomeSocieta,p);
			b.getListino().addSocieta(s);
			//Aggiungi al db
			ListinoDAO l=new ListinoImplementazionePostgresDAO();
			l.addSocietaDB(s,b);
			
		}catch (NumberFormatException e) {
			errore=new String("Il prezzo non � un numero valido");
		}
		
		return errore;
	}




	/**
	 * Gets the listino societa prezzo.
	 *
	 * @return the listino societa prezzo
	 */
	public ArrayList getListinoSocietaPrezzo() {
		ArrayList a = new ArrayList();
		if (b.getListino().getSocieta()!=null)
			for (Societa s:b.getListino().getSocieta()) {
				a.add(s.getNome());
				a.add(s.getPrezzoAzione());
			}
		else 
			a=null;
		return a;
	}

	/**
	 * Cerca societa.
	 *
	 * @param nomeSocieta the nome societa
	 * @return true, if successful
	 */
	public boolean cercaSocieta(String nomeSocieta) {
		boolean trovato=false;
		for (Societa s:b.getListino().getSocieta())
			if (s.getNome().contentEquals(nomeSocieta)) {
				trovato=true;
			}
		return trovato;
	}

	/**
	 * Sets the prezzo azione.
	 *
	 * @param societa the societa
	 * @param nuovoPrezzo the nuovo prezzo
	 */
	public void setPrezzoAzione(String societa, float nuovoPrezzo) {
		for (Societa s:b.getListino().getSocieta())
			if (s.getNome().contentEquals(societa)) {
				s.setPrezzo(nuovoPrezzo);
				//scrivo sul db
				SocietaDAO sDAO = new SocietaImplementazionePostgresDAO();
				sDAO.updatePrezzo(s,nuovoPrezzo);
			}		
	}

	/**
	 * Nuovo giocatore.
	 *
	 * @param nomeGiocatore the nome giocatore
	 */
	public void nuovoGiocatore(String nomeGiocatore) {
		// TODO Controllare anche se il giocatore esiste gi�
		//assumo 1000 come dotazione iniziale
		g= new Giocatore(nomeGiocatore,1000.0f);
		
	}

	/**
	 * Acquista.
	 *
	 * @param nomeSocieta the nome societa
	 * @param quantita the quantita
	 * @return true, if successful
	 */
	public boolean acquista(String nomeSocieta, int quantita) {
		boolean ok=true;
		// TODO Verificare che ci sia disponibilita
		for (Societa s:b.getListino().getSocieta())
			if (s.getNome().contentEquals(nomeSocieta)) {
				//verifica
				if (ok) {
					//System.out.println("Capitale attuale : "+ g.getCapitale());
					//Aggiungo l'acquisto in memoria
					g.acquista(quantita, LocalDate.now(), s.getPrezzoAzione(), s);
					//Inserisco nel DB
					GiocatoreDAO gDAO=new GiocatoreImplementazionePostgresDAO();
					gDAO.acquistaDB(g,quantita, LocalDate.now(), s.getPrezzoAzione(), s);

					//aggiorno il capitale (calcolo con i dati in memoria)
					g.calcolaCapitale();
					
					//System.out.println("Capitale dopo l'acquisto : "+ g.getCapitale());
					//if(g.getLiquidita()<0) 
					//    throw new RuntimeException("Errore: non avevi soldi per questo acquisto");
					return true;
				}
			}
		return false;
	}

	/**
	 * Gets the nome giocatore.
	 *
	 * @return the nome giocatore
	 */
	public String getNomeGiocatore() {
		return g.getNome();
	}

	/**
	 * Gets the bilancio.
	 *
	 * @return the bilancio
	 */
	public String getBilancio() {
		g.calcolaCapitale();
		return ((Float)g.getCapitale()).toString();
	}

	/**
	 * Gets the lista acquisti.
	 *
	 * @return the lista acquisti
	 */
	public ArrayList<Acquisto> getListaAcquisti() {
		return g.getListaAcquisti();
	}


}
