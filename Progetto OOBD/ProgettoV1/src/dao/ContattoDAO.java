package DAO;

import java.sql.Connection;

import Model.Account;
import Model.Contatto;
import Model.Indirizzi;
import Model.NumeriTelefonici;

import java.util.ArrayList;

public interface ContattoDAO {
	
	public void addContattoDB (Contatto c);
	
	public void deleteContattoDB (Contatto c);
	
	public void updateContattoDB (Contatto c, String prefisso, String nome, String cognome, String pathFoto);
	
	public ArrayList<Contatto> readContattoDB (Contatto c);

	public int addNumeriDB (int idContatto, String prefisso, String numero, String tag, String tipo);
	
	public ArrayList<NumeriTelefonici> getListaNumeri (int id);
	
	public int addIndirizziDB (int idContatto, String via, String città, String codicePostale, String nazione, String tag, boolean principale );
	
	public ArrayList<Indirizzi> getListaIndirizzi (int id);
	
	public int addAccountDB (int idContatto ,String nickname, String fornitore, String benvenuto, String email);
	
	public ArrayList<Account> getListaAccount (int id);
	
	public void addEmailDB (int idContatto, String email);
	
	public  ArrayList<String> getListaEmail (int id);

	public ArrayList<Integer> SearchAnagrafica(String prefisso, String nome, String cognome) ;
	
	public  ArrayList<Integer> SearchMail(String mail);
	
	public  ArrayList<Integer> SearchAccount(String nickname, String fornitore);

	public void setFoto(int id, String pathDestiCompleto);

	public void setDeputatoFissoSuMobile(int contattoId, String prefisso1, String numero1, String prefisso2,
			String numero2);

	public void setDeputatoMobileSuFIsso(int contattoId, String prefisso1, String numero1, String prefisso2,
			String numero2);
	
}