/**
 * 
 */
package dao;

/**
 * @author LorenzoSepe
 *
 */


import java.sql.Connection;
import java.util.ArrayList;

import model.Indirizzi;
import model.Contatto;

public interface IndirizziDAO {

	public void addIndirizzoDB(Indirizzi i, Contatto c);

	public void addAbitaDB(Indirizzi i, Contatto c, String identificatore);
	
	public ArrayList<Indirizzi> leggiIndirizziDB(Contatto c);

}