/**
 * 
 */
package DAO;

/**
 * @author LorenzoSepe
 *
 */

import java.sql.SQLException;


public interface IndirizziDAO {

	public void updateIndirizzoDB(int idContatto,int idIndirizzo ,String via, String città, String codicePostale, String nazione, String tag, boolean principale );
}