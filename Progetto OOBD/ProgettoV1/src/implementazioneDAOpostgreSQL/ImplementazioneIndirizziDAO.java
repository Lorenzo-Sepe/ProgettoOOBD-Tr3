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
		PreparedStatement addIndirizzoPS;
		try {
			addIndirizzoPS = connection.prepareStatement(
					//da aggiunstare una volta finito il DB
					"INSERT INTO INDIRIZZI (via , città, codice_postale, nazione) VALUES (?, ?, ?, ?)");
					addIndirizzoPS.setString(1, i.getVia());
					addIndirizzoPS.setString(2, i.getCittà());
					addIndirizzoPS.setInt(3, i.getCodicePostale());
					addIndirizzoPS.setString(4, i.getNazione());
					ResultSet rs = addIndirizzoPS.executeQuery();
					//- Release delle risorse
					rs.close();
					addIndirizzoPS.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addAbitaDB(Indirizzi i, Contatto c, String identificatore) {
		PreparedStatement addAbitaPS;
		try {
			addAbitaPS = connection.prepareStatement("INSER INTO ABITA(indirizzo_ID, Contatto_ID, abitazione_principale, identificatore) (? , ? , ?, ?)");
			addAbitaPS.setInt(1, i.getID());
			addAbitaPS.setInt(2, c.getID());
			addAbitaPS.setString(3, "FALSE");
			addAbitaPS.setString(4, identificatore);
			ResultSet rs = addAbitaPS.executeQuery();
			//- Release delle risorse
			rs.close();
			addAbitaPS.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Indirizzi> leggiIndirizziDB(Contatto c) {
		PreparedStatement leggiIndirizziPS;
		ArrayList<Indirizzi> listaIndirizzi = new ArrayList<>();		
		try {
			leggiIndirizziPS = connection.prepareStatement(
					//da cambare una volta finito il database
					"SELECT * FROM \"Indirizzi\".\"Abita\" WHERE \"Contatto_ID\"='"+c.getID()+"'");
					
		ResultSet rs = leggiIndirizziPS.executeQuery();
		while (rs.next()) {
			Indirizzi i = new Indirizzi (rs.getInt("Indirizzo_ID"), rs.getBoolean("Abitazione_Principale"),  rs.getString("Via"), rs.getString("Città"), rs.getInt("Codice_Postale"), rs.getString("Nazione"), rs.getString("Identificatore") );
			listaIndirizzi.add(i);
			connection.close();
		}
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaIndirizzi;		
	}

}
