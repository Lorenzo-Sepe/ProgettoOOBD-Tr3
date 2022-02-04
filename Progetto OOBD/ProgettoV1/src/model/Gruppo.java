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


	public ArrayList<Contatto> getListaGruppo(){
		return membriGruppo;    	
    }
	public void setNomeGruppo(String nome) {
		nomeGruppo=nome;
		
	}
	public String getNomeGruppo() {
		return nomeGruppo;
	}
    
	public void aggiungiContatto(Contatto contatto) {
        membriGruppo.add(contatto);
    }
    
    public Boolean eliminaContatto(Contatto contatto) {
    	return membriGruppo.remove(contatto);
    	
    }
    
}
