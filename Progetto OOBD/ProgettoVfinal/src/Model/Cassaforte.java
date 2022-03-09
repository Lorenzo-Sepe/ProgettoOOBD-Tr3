package Model;



import java.util.*;


/**
 *
 */
 public class Cassaforte{

	private String password;
	private ArrayList<Contatto> contatti = new ArrayList<>();
    /**
     * Default constructor
     */
    public Cassaforte(String pass) {
    	password=pass;

    }


    /**
     * restituisce la password della cassaforte
     * @return Stringa che rappresenta la password
     */
    public String getPassword(){
    	return password;
    }
    
    /**
     * restituisce la lista dei contatti della cassaforte
     * @return ArrayList di Contatto che rappresenta la lista di contatti
     */
    public ArrayList<Contatto> getListaGruppo(){
    	return contatti;
    }
    /**
     * aggiunge un contatto in cassaforte
     * @param i
     */
    public void aggiungiContatto (Contatto i) {
    	contatti.add(i);
	}
    
	/**
	 * imposta la password della cassaforte
	 * @param pass Stringa che rappresenta la password da impostare
	 */
    public void setPassword(String pass) {
    	password=pass;
    	}


    /**
     * imposta la lista di contatti della cassaforte
     * @param contattiInCassaforte ArrayList di contatti che rappresenta la lista da impostare
     */
	public void setListaContatti(ArrayList<Contatto> contattiInCassaforte) {
		contatti = contattiInCassaforte;		
	}
	
	/**
	 * restituisce un contatto dalla cassaforte
	 * @param id identificatore del contatto da restituire
	 * @return oggetto della classe Contatto rappresentante il contatto da restituire
	 */
	public Contatto getContatto (int id) {
		for (Contatto contatto : contatti) {
			if (contatto.getID()==id) {
				return contatto;
			}
		}
		return null;
	}

}
