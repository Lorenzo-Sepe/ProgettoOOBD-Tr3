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


    public String getPassword(){
    	return password;
    }
    
    public ArrayList<Contatto> getListaGruppo(){
    	return contatti;
    }
    
    public void aggiungiContatto (Contatto i) {
    	contatti.add(i);
	}
    
//    public void aggiungiListaContatti (ArrayList<Contatto> listaContatti) {
//    	this.contatti.addAll(listaContatti);
//    }
    
	
    public void setPassword(String pass) {
    	password=pass;
    	}


	public void setListaContatti(ArrayList<Contatto> contattiInCassaforte) {
		contatti = contattiInCassaforte;		
	}


	public Contatto getContatto(int contattoID) {
		for (Contatto contatto : contatti) {
			if (contatto.getID()==contattoID) {
				return contatto;
			}
		}
		return null;
	}

}
