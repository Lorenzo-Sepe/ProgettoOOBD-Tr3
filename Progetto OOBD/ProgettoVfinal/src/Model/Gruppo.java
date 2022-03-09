package Model;

import java.util.ArrayList;

/**
 * 
 */
public class Gruppo {

    protected String nomeGruppo;
    protected ArrayList<Contatto> membriGruppo =null;
    
    
    public Gruppo(String nome) {
    	nomeGruppo=nome;
    	membriGruppo=new ArrayList<Contatto>();
    	
    }

    /**
     * restituisce la lista dei contatti che fanno parte del gruppo
     * @return ArrayList di Contatto che rappresenta la lista dei contatti
     */
	public ArrayList<Contatto> getMembriGruppo(){
		return membriGruppo;    	
    }

	
	/**
	 * restituisce il nome del gruppo
	 * @return Stringa che rappresenta il nome del gruppo
	 */
	public String getNomeGruppo() {
		return nomeGruppo;
	}
    
	/**
	 * aggiunge un contatto nel gruppo
	 * @param contatto oggetto della classe Contatto che corrisponde al contatto da aggiungere
	 */
	public void aggiungiContatto(Contatto contatto) {
        membriGruppo.add(contatto);
    }
    
	/**
	 * elimina un contatto dal gruppo
	 * @param contatto  oggetto della classe Contatto che corrisponde al contatto da eliminare
	 * @return valore booleano che sarà uguale a true se 
	 */
    public Boolean eliminaContatto(Contatto contatto) {
    	return membriGruppo.remove(contatto);
    	
    }
    
    public void setMembriGruppo (ArrayList<Contatto> membriGruppo) {
    	this.membriGruppo = membriGruppo;
    }
    
}
