/**
 * 
 */
package DAO;

/**
 * @author LorenzoSepe
 *
 */


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Indirizzi;
import Model.Contatto;

public interface IndirizziDAO {

	public void updateIndirizzoDB (int indirizzoID, String via, String città, String codicePostale, String nazione, String tag) throws SQLException;
	
	public void deleteIndirizzoDB (int indirizzoID) throws SQLException;

}