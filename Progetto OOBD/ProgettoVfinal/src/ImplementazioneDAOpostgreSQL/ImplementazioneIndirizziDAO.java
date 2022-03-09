/**
 * 
 */
package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.IndirizziDAO;
import Database.Connessione;




/**
 * @author LorenzoSepe
 *
 */
public class ImplementazioneIndirizziDAO implements IndirizziDAO {
	
	private Connection connection;
	
	public ImplementazioneIndirizziDAO() {
		try {
			connection = Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void  chiudiConnessione() {
		 try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	public void updateIndirizzoDB(int idContatto,int idIndirizzo ,String via, String citta, String codicePostale, String nazione, String tag, boolean principale ) {
        PreparedStatement addIndirizziDB;
        PreparedStatement addAbitaDB;
        
        try {
            addIndirizziDB = connection.prepareStatement("UPDATE public.indirizzi\r\n"
                    + "    SET  via=? ,  \"città\" =? , codice_postale = ? , nazione = ? \r\n"
                    + "    WHERE indirizzi_id = ? ;   ");
            addIndirizziDB.setString(1, via);
            addIndirizziDB.setString(2, citta);
            addIndirizziDB.setString(3, codicePostale);
            addIndirizziDB.setString(4, nazione);
            addIndirizziDB.setInt(5, idIndirizzo);
            System.out.println("query Update:-"+addIndirizziDB.toString());
            addIndirizziDB.execute();

            
//            addAbitaDB = connection.prepareStatement("UPDATE abita"
//                    + "    SET  abitazione_principale = ?, identificatore= ?"
//                    + "    WHERE  indirizzi_id = ? AND contatto_associato = ?;");
//            
        
//            addAbitaDB.setBoolean(1, principale);
//            addAbitaDB.setString(2, tag);
//            addIndirizziDB.setInt(3, idIndirizzo);
//            addIndirizziDB.setInt(4, idContatto);
        
            addAbitaDB = connection.prepareStatement("UPDATE abita"
                    + "    SET  abitazione_principale = ?, identificatore= ?"
                    + "    WHERE  indirizzo_associato = "+idIndirizzo+" AND contatto_associato = "+idContatto+" ;");
            addAbitaDB.setBoolean(1, principale);
            addAbitaDB.setString(2, tag);
            
            System.out.println("Sono nel ImplementazioneIdirizzo update indirizzo con idIdndi:-"+idIndirizzo+" contatto ID:-"+idContatto+"\nquery:-"+addAbitaDB.toString());
            addAbitaDB.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
     }

}
