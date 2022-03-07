package Model;

import java.util.ArrayList;

public class Rubrica {
	private ArrayList<Contatto> contatti= new ArrayList<>();
	private ArrayList<Gruppo> gruppi = new ArrayList<>();
	
	private Cassaforte cassaforte =null;	

	/**
	 * aggiunge un contatto alla rubrica
	 * @param c oggetto della classe Contatto che corrisponde al contatto da aggiungere alla rubrica
	 */
	public void aggiungiContatto (Contatto c) {
		contatti.add(c);
		
	}
	
	/**
	 * elimina un contatto dalla rubrica
	 * @param c oggetto della classe contatto che corrisponde al contatto da eliminare dalla rubrica
	 * @return	valore booleano che rappresenta la riuscita o meno dell'eliminazione del contatto dalla rubrica
	 */
	public boolean eliminaContatto (Contatto c) {
		boolean riuscita;
		riuscita = contatti.remove(c);
		return riuscita;
	}
	
	
	/**
	 * restituisce la lista dei contatti della rubrica
	 * @return ArrayList di Contatto corrispondente alla lista dei contatti della rubrica
	 */
	public ArrayList<Contatto> getListaContatti(){
		return contatti;		
	}
	/**
	 * restituisce il contatto con l'id inserito
	 * @param id identificatore del contatto da restituire
	 * @return contatto associato al id inserito
	 */
	public Contatto getContatto(int id) {
		for (Contatto contatto : contatti) {
			if(contatto.getID()==id)
				return contatto;
		}
		return null;
	}
	
	/**
	 * crea un gruppo e lo aggiunge alla lista dei gruppi della rubrica
	 * @param nomeGruppo Stringa che corrisponde al nome del gruppo da aggiungere
	 */
	public void creaGruppo(String nomeGruppo) {
		Gruppo gruppo = new Gruppo(nomeGruppo);
		gruppi.add(gruppo);
	}
	
	/**
	 * elimina un gruppo dalla lista
	 * @param nomeGruppo Stringa che corrisponde al nome del gruppo da eliminare
	 */
	public void eliminaGruppo(String nomeGruppo) {
		for (Gruppo gruppo : gruppi) {
			if (nomeGruppo.compareTo(gruppo.getNomeGruppo())==0) {
				gruppi.remove(gruppo);
				break;
			}
		}
	}
	
	/**
	 * restituisce la lista dei gruppi della rubrica
	 * @return ArrayList di Gruppo che corrisponde alla lista dei gruppi della rubrica
	 */
	public ArrayList<Gruppo> getListaGruppi (){
		return gruppi;
	}
	
	/**
	 * restituisce un gruppo della lista
	 * @param nomeGruppo nome del gruppo da restituire
	 * @return oggetto della classe Gruppo
	 */
	public Gruppo getGruppo (String nomeGruppo) {
		for (Gruppo gruppo : gruppi) {
			if (nomeGruppo.compareTo(gruppo.getNomeGruppo())==0) {
				return gruppo;
			}
		}
		return null;
	}
	
	/**
	 * crea la cassaforte della rubrica
	 * @param password Stringa che corrisponde alla password della cassaforte
	 */
	public void creaCassaforte(String password) {
		if(cassaforte==null)
		cassaforte = new Cassaforte( password);
	}

	/**
	 * restituisce la cassaforte della rubrica
	 * @return oggetto della classe Cassaforte corrispondente alla cassaforte della rubrica
	 */
	public Cassaforte  getCassaforte() {
		return cassaforte;
	}	
}
