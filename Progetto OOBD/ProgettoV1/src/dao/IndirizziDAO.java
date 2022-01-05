/**
 * 
 */
package dao;

/**
 * @author LorenzoSepe
 *
 */


import java.sql.Connection;

import model.Indirizzi;
import model.Contatto;

public interface IndirizziDAO {

	public void addIndirizzoDB(Indirizzi i, Contatto c);

	public Indirizzi leggiIndirizziDB(Contatto c );

}