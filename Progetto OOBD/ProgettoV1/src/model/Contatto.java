package Model;

import java.util.ArrayList;

public class Contatto {
	private int contatto_ID;
	private String prefissoNome;
	private String nome;
	private String cognome;
	private String pathFoto;
	private ArrayList<String> ListaEmail = null;
	private ArrayList<Indirizzi> ListaIndirizzi = null;
	private ArrayList<NumeriTelefonici> ListaNumeri = null;
	private ArrayList<account> ListaAccount = null;
	
	public Contatto (String prefisso, String nome, String cognome, String pathFoto) {
		if (prefisso == null) {
			this.prefissoNome = null;
		}
		else {
			this.prefissoNome = new String (prefisso);
		}
		this.nome = new String (nome);
		this.cognome = new String (cognome);
		if (pathFoto == null) {
			this.pathFoto = null;
		}
		else {
			this.pathFoto = new String (pathFoto);
		}
	}
	
	public void setID (int id) {
		this.contatto_ID = id;
	}
	
	public int getID () {
		return contatto_ID;
	}
	
	public void setPrefissoNome (String pref) {
		this.prefissoNome = pref;
	}
	
	public String getPrefissoNome () {
		return prefissoNome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getNome () {
		return nome;
	}
	
	public void setCognome (String cognome) {
		this.cognome = cognome;
	}
	
	public String getCognome () {
		return cognome;
	}
	
	public void setPathFoto (String PF) {
		this.pathFoto = PF;
	}
	
	public String getPathFoto () {
		return pathFoto;
	}
	
	public void AggiungiIndirizzo (Indirizzi i) {
		ListaIndirizzi.add(i);
	}
	
	public boolean EliminaIndirizzo (Indirizzi i) {
		boolean riuscita;
		riuscita = ListaIndirizzi.remove(i);
		return riuscita;
	}
	
	public void AggiungiEmail (String e) {
		ListaEmail.add(e);
	}
	
	public boolean EliminaEmail (String e) {
		boolean riuscita;
		riuscita = ListaEmail.remove(e);
		return riuscita;
	}
}

