package Model;

import java.util.ArrayList;

public class Contatto {
	private int contatto_ID;
	private String prefissoNome;
	private String nome;
	private String cognome;
	private String pathFoto;
	private ArrayList<String> ListaEmail = new ArrayList<String>();
	private ArrayList<Indirizzi> listaIndirizzi = new ArrayList<Indirizzi>();
	private ArrayList<NumeriTelefonici> listaNumeri = new ArrayList<NumeriTelefonici>();
	private ArrayList<Account> listaAccount = new ArrayList<Account>();
	

	public Contatto (int contattoID ,String prefisso, String nome, String cognome,String path) {
		if (prefisso == null) {
			this.prefissoNome = null;
		}
		else {
			this.prefissoNome = prefisso;
		}
		
		this.cognome = cognome;
		this.contatto_ID=contattoID;
		this.nome = nome;
		
		if(path==null) {
			this.pathFoto= " ";
		}else {
			this.pathFoto= path;
		}
		
	}

	
	/**
	 * imposta il codice identificativo del contatto
	 * @param id valore che verrà assegnato all'identificatore univoco del contatto
	 */
	public void setID (int id) {
		this.contatto_ID = id;
	}
	
	/**
	 * restituisce l'identificativo del contatto
	 * @return il valore identificativo univoco del contatto
	 */
	public int getID () {
		return contatto_ID;
	}
	
	/**
	 * imposta il prefisso del nome del contatto
	 * @param pref stringa che verrà assegnata al prefisso del nome del contatto
	 */
	public void setPrefissoNome (String pref) {
		this.prefissoNome = pref;
	}
	
	/**
	 * restituisce il prefisso del nome del contatto
	 * @return stringa che corrisponde al prefisso del contatto
	 */
	public String getPrefissoNome () {
		return prefissoNome;
	}
	
	/**
	 * imposta il nome del contatto
	 * @param stringa che verrà assegnato al nome del contatto
	 */
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	/**
	 * restituisce il nome del contatto
	 * @return stringa che corrisponde al nome del contatto
	 */
	public String getNome () {
		return nome;
	}
	
	/**
	 * imposta il cognome del contatto
	 * @param cognome stringa che verrà assegnato al cognome del contatto
	 */
	public void setCognome (String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * restituisce il cognome del contatto
	 * @return stringa che corrisponde al cognome del contatto
	 */
	public String getCognome () {
		return cognome;
	}
	
	/**
	 * imposta il path della foto che verrà assegnata al contatto
	 * @param PF stringa che verrà assegnata al path della foto contatto
	 */
	public void setPathFoto (String PF) {
		this.pathFoto = PF;
	}
	
	/**
	 * restituisce il path della foto del contatto
	 * @return stringa che corrisponde al path della foto contatto
	 */
	public String getPathFoto () { 
		return pathFoto;
	}
	
	/**
	 * aggiunge un indirizzo alla lista di indirizzi del contatto
	 * @param i oggetto della classe Indirizzi che verrà aggiunto all'ArrayList di oggetti Indirizzi
	 */
	public void aggiungiIndirizzo (Indirizzi i) {
		listaIndirizzi.add(i);
	}
	
	/**
	 * elimina un indirizzo alla lista di indirizzi del contatto
	 * @param i oggetto della classe indirizzo che corrisponde all'elemento nella ArrayList di Indirizzi 
	 * @return valore booleano che rappresenta la riuscita o meno dell'eliminazione dell'indirizzo dalla lista
	 */
	public boolean EliminaIndirizzo (Indirizzi i) {
		boolean riuscita;
		riuscita = listaIndirizzi.remove(i);
		return riuscita;
	}
	
	/**
	 * aggiunge una lista di numeri al contatto
	 * @param listaNumeri2 ArrayList di oggetti NumeriTelefonici che verrà assegnato alla lista di numeri del contatto
	 */
	public void setNumero(ArrayList<NumeriTelefonici> listaNumeri2) {
        listaNumeri= listaNumeri2;

    }

	/**
	 * aggiunge una lista di account al contatto
	 * @param listaAccount2 ArrayList di oggetti Account che verrà assegnato alla lista di account del contatto
	 */
    public void setAccount(ArrayList<Account> listaAccount2) {
        listaAccount=listaAccount2;

    }

    /**
     * aggiunge una lista di indirizzi al contatto
     * @param listaIndirizzi2 ArrayList di oggetti Indirizzi che verrà assegnato alla lista di indirizzi del contatto
     */
    public void setIndirizzo(ArrayList<Indirizzi> listaIndirizzi2) {
        listaIndirizzi=listaIndirizzi2;
    }

    /**
     * aggiunge una lista di mail al contatto
     * @param listaEmail2 ArrayList di stringhe che verrà assegnato alla lista di mail del contatto
     */
    public void setEmail(ArrayList<String> listaEmail2) {
            ListaEmail=listaEmail2;
    }
	
	/**
	 * aggiunge una mail alla lista di mail del contatto
	 * @param modifica stringa che corrisponde alla mail da aggiungere alla lista
	 */
	public void aggiungiEmail(String modifica) {
		ListaEmail.add(modifica);
	}
	
	/**
	 * aggiunge un numero telefonico alla lista di numeri
	 * @param numero oggetto della classe NumeriTelefonici che corrisponde al numero da aggiungere alla lista di numeri
	 */
	public void aggiungiNumero(NumeriTelefonici numero){
		listaNumeri.add(numero);
	}
	
	/**
	 * elimina un numero dalla lista dei numeri
	 * @param numero oggetto della classe NumeriTelefonici che corrisponde al numero da eliminare
	 */
	public void eliminaNumero (NumeriTelefonici numero) {
		listaNumeri.remove(numero);
	}
	
	/**
	 * elimina un account dalla lista di account
	 * @param a oggetto della classe Account che corrisponde all'account che verrà eliminato dalla lista
	 */
	public void eliminaAccount (Account a) {
		listaAccount.remove(a);
	}
	
	/**
	 * restituisce i campi del contatto
	 * @return stringa composta dalla somma di prefisso, nome e cognome del contatto
	 */
	public String StampaContatto() { 		
		return new String(this.contatto_ID+" "+this.prefissoNome+" "+this.nome+" "+this.cognome) ;
	}

	/**
	 * restituisce un numero dalla lista
	 * @param i indice nella lista del numero da restituire
	 * @return oggetto della classe NumeriTelefonici nella posizione i dell'ArrayList di NumeriTelefonici presente in contatto
	 */
	public NumeriTelefonici getNumero(int i) {
		return listaNumeri.get(i);
	}
	
	/**
	 * restituisce un numero dalla lista
	 * @param pref Stringa che corrisponde al prefisso del numero da restituire
	 * @param num Stringa che corrisponde al numero da restituire
	 * @return oggetto della classe NumeriTelefonici nella posizione i dell'ArrayList di NumeriTelefonici presente in contatto
	 */
	public NumeriTelefonici getNumero(String pref, String num) {
		int ris=0;
		
		for (int j = 0; j < listaNumeri.size(); j++) {
			if (listaNumeri.get(j).getPrefisso().compareTo(pref)==0 && listaNumeri.get(j).getNumero().compareTo(num)==0) {
			ris = j;}
		} 
		
		return listaNumeri.get(ris);
	}

	/**
	 * restituisce un indirizzo dalla lista
	 * @param j indice nella lista dell'indirizzo da restituire
	 * @return oggetto della classe Indirizzi nella posizione j dell'ArrayList di Indirizzi presente in contatto
	 */
	public Indirizzi getIndirizzo(int j) {
		return  listaIndirizzi.get(j);
		
	}
	
	/**
	 * restituisce un account dalla lista
	 * @param i indice nella lista dell'account da restituire
	 * @return oggetto della classe Account nella posizione i dell'ArrayList di Account presente in contatto
	 */
	public Account getAccount(int i) {
		return listaAccount.get(i);
	}

	/**
	 * restituisce la lista dei numeri del contatto
	 * @return ArrayList di NumeriTelefonici corrispondente alla lista dei numeri del contatto
	 */
	public ArrayList<NumeriTelefonici> getListaNumeri() {
		return listaNumeri;
	
	}

	/**
	 * restituisce la lista degli indirizzi del contatto
	 * @return ArrayList di Indirizzi corrispondente alla lista degli indirizzi del contatto
	 */
	public ArrayList<Indirizzi> getListaIndirizzi() {
		return listaIndirizzi;
	}

	/**
	 * restituisce la lista degli account del contatto
	 * @return ArrayList di Account corrispondente alla lista degli account del contatto
	 */
	public ArrayList<Account> getListaAccount() {
		return listaAccount;
	}
	
	/**
	 * restituisce la lista delle mail del contatto
	 * @return ArrayList di stringhe corrispondente alla lista dele mail del contatto
	 */
	public ArrayList<String> getListaEmail() {
		return ListaEmail;
	}	
}	
