/**
 * 
 */
package implementazioneDAOpostgreSQL;

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

import dao.Connessione;
import dao.IndirizziDAO;
import model.Contatto;
import model.Indirizzi;


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

	@Override
	public void addIndirizzoDB(Indirizzi i, Contatto c) {
		// TODO Auto-generated method stub

	}

	@Override
	public Indirizzi leggiIndirizziDB(Contatto c) {
		PreparedStatement leggiIndirizziPS;
		ListaIndirizzi l=new ListaIndirizzi();
		try {
			leggiIndirizziPS = connection.prepareStatement(
					//da cambare una volta finito il database
					"SELECT * FROM \"Indirizzi\".\"Abita\" WHERE \"Contatto_ID\"='"+c.getID()+"'");
					
		ResultSet rs = leggiIndirizziPS.executeQuery();
		while (rs.next()) {
			Indirizzi i = new Indirizzi (rs.getBoolean("Abitazione_Principale"),  rs.getString("Via"), rs.getString("Città"), rs.getInt("Codice_Postale"), rs.getString("Nazione"), rs.getString("Identificatore") );
			l.addSocieta(i);
			connection.close();
		}
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;		
	}

}
