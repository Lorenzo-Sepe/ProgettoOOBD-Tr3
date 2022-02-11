package Model;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Model.NumeriTelefonici;

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
	private ArrayList<Gruppo> listaGruppo= new ArrayList<Gruppo>();
	private Cassaforte visibilita = null;
	

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

public void provaDao() {
	
}
	
	public static void main(String[] args) {
		String path="C:\\Users\\Utente\\Pictures\\Camera";
		
		
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
	
	public void aggiungiIndirizzo (Indirizzi i) {
		listaIndirizzi.add(i);
	}
	public void aggiungiIndirizzo(ArrayList<Indirizzi>arraylist){
		if(listaIndirizzi.isEmpty()) {
			listaIndirizzi=arraylist;
		}else {
			listaIndirizzi.addAll(arraylist);
		}
	}
	
	public boolean EliminaIndirizzo (Indirizzi i) {
		boolean riuscita;
		riuscita = listaIndirizzi.remove(i);
		return riuscita;
	}

	public void aggiungiFoto(String path) {
		pathFoto= new String(path);
		
	}
	
	
	

	public void aggiungiEmail(String modifica) {
		// TODO Auto-generated method stub
		ListaEmail.add(modifica);
	}
	public void aggiungiEmail(ArrayList<String>arraylist){
		if(ListaEmail.isEmpty()) {
			ListaEmail=arraylist;
		}else {
			ListaEmail.addAll(arraylist);
		}
	}
public void aggiungiNumero(NumeriTelefonici numero){
	listaNumeri.add(numero);
}
public void aggiungiNumero(ArrayList<NumeriTelefonici>numeri){
	if(listaNumeri.isEmpty()) {
		listaNumeri=numeri;
	}else {
		listaNumeri.addAll(numeri);
	}
}
public void aggiungiAccount(Account a) {
	listaAccount.add(a);
}
public void aggiungiAccount(ArrayList<Account>arraylist){
	if(listaAccount.isEmpty()) {
		listaAccount=arraylist;
	}else {
		listaAccount.addAll(arraylist);
	}
}
	public String StampaContatto() { 
		
		return new String(this.contatto_ID+" "+this.prefissoNome+" "+this.nome+" "+this.cognome) ;
	}


	public NumeriTelefonici getNumero(int i) {
		return listaNumeri.get(i);
	}


	public Indirizzi getIndirizzo(int j) {
		return  listaIndirizzi.get(j);
		
	}
	public Account getAccount(int i) {
		return listaAccount.get(i);
	}

	public ArrayList<NumeriTelefonici> getListaNumeri() {
		return listaNumeri;
	
	}


	public ArrayList<Indirizzi> getListaIndirizzi() {
		return listaIndirizzi;
	}

	public ArrayList<Account> getListaAccount() {
		return listaAccount;
	}

	public ArrayList<Gruppo> getListaGruppo() {
		return listaGruppo;
	}

	public void aggiungiGruppo(Gruppo gruppo) {
		this.listaGruppo.add(gruppo) ;
	}
	public void aggiungiGruppo(ArrayList<Gruppo>arraylist){
		if(listaGruppo.isEmpty()) {
			listaGruppo=arraylist;
		}else {
			listaGruppo.addAll(arraylist);
		}
	}

	public Cassaforte getVisibilita() {
		return visibilita;
	}

	public void setCassaforte(Cassaforte visibilita) {
		this.visibilita = visibilita;
	}
	
}	
