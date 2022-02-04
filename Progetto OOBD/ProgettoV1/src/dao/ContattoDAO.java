package DAO;

import java.sql.Connection;
import Model.Contatto;
import java.util.ArrayList;

public interface ContattoDAO {
	
	public void addContattoDB (Contatto c);
	
	public void deleteContattoDB (Contatto c);
	
	public void updateContattoDB (Contatto c, String prefisso, String nome, String cognome, String pathFoto);
	
	public ArrayList<Contatto> readContattoDB (Contatto c);
	
	public ArrayList<Contatto> selectAllDB ();
}