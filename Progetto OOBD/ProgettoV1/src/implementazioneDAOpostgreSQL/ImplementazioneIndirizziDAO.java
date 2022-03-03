/**
 * 
 */
package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DAO.IndirizziDAO;
import Database.Connessione;
import Model.Contatto;
import Model.Indirizzi;



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

	@Override
	public void updateIndirizzoDB(int indirizzoID, String via, String città, String codicePostale, String nazione, String tag) throws SQLException {
		PreparedStatement updateIndirizzoPS;
		updateIndirizzoPS = connection.prepareStatement("UPDATE Indirizzi SET via = ?, città = ?, codicePostale = ?, nazione = ? WHERE indirizzi_id = ?");
		updateIndirizzoPS.setString(1, via);
		updateIndirizzoPS.setString(2, città);
		updateIndirizzoPS.setString(3, codicePostale);
		updateIndirizzoPS.setString(4, nazione);
		updateIndirizzoPS.setInt(5, indirizzoID);
		updateIndirizzoPS.execute();
		updateIndirizzoPS = connection.prepareStatement("UPDATE abita, SET identificatore = ? WHERE indirizzo_associato = ?");
		updateIndirizzoPS.setString(1, tag);
		updateIndirizzoPS.setInt(2, indirizzoID);
		updateIndirizzoPS.execute();	
	}

	@Override
	public void deleteIndirizzoDB(int indirizzoID) throws SQLException {
		PreparedStatement deleteIndirizzoPS;
		deleteIndirizzoPS = connection.prepareStatement("DELETE FROM indirizzi WHERE indirizzi_id = ?");
		deleteIndirizzoPS.setInt(1, indirizzoID);
		deleteIndirizzoPS.execute();
	}
}
