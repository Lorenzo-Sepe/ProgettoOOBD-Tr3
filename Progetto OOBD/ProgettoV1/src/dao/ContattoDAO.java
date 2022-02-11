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
	
	public ArrayList<Contatto> selectAllDB ();
	
	public ArrayList<NumeriTelefonici> getListaNumeri (int id);
	
	public ArrayList<Indirizzi> getListaIndirizzi (int id);
	
	public ArrayList<Account> getListaAccount (int id);
}