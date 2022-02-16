package Model;



import java.util.*;


/**
 *
 */
 public class Cassaforte{

	private String password;
	private ArrayList<Contatto> contatti =null;
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
	public void aggiungiContatto(ArrayList<Contatto>arraylist){
		if(contatti.isEmpty()) {
			contatti=arraylist;
		}else {
			contatti.addAll(arraylist);
		}
	}
    public void setPassword(String pass) {
    	password=pass;
    	}

}
