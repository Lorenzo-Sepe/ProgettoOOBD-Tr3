/**
 * 
 */
package DAO;

/**
 * @author LorenzoSepe
 *
 */


public interface IndirizziDAO {

	/**
	 * esegue una query sulle tabelle indirizzi e abita del database per modificare un indirizzo
	 * @param idContatto identificatore del contatto a cui appartiene l'indirizzo da modificare
	 * @param idIndirizzo identificatore dell'indirizzo da modificare
	 * @param via Stringa rappresentante la via da impostare nell'indirizzo
	 * @param citta Stringa rappresentante la citta da impostare nell'indirizzo
	 * @param codicePostale Stringa rappresentante il codice postale da impostare nell'indirizzo
	 * @param nazione Stringa rappresentante la nazione da impostare nell'indirizzo
	 * @param tag Stringa rappresentante il tag da impostare nell'indirizzo
	 * @param principale valore booleano che vale true se l'indirizzo da aggiungere e il principale del contatto, false altrimenti
	 */
	public void updateIndirizzoDB(int idContatto,int idIndirizzo ,String via, String citta, String codicePostale, String nazione, String tag, boolean principale );
}