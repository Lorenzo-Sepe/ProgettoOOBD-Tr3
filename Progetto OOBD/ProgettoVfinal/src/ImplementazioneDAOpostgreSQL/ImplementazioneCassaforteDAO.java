package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.CassaforteDAO;
import Database.Connessione;
import Model.Contatto;

public class ImplementazioneCassaforteDAO implements CassaforteDAO {

private Connection connection;
	
	public ImplementazioneCassaforteDAO() {
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
	
	public String getPasswordDB () throws SQLException {
		PreparedStatement getPasswordPS;
		String password;
		getPasswordPS = connection.prepareStatement("SELECT password FROM cassaforte");
		ResultSet rs = getPasswordPS.executeQuery();
		rs.next();
		password = rs.getString("password");
		return password;
	}
	
	public void addContattoCassaforteDB(int id, String password) throws SQLException {
		PreparedStatement addContattoCassafortePS;
		addContattoCassafortePS = connection.prepareStatement("UPDATE contatto SET \"Password_Cassaforte\" = ? WHERE contatto_id = ?");
		addContattoCassafortePS.setString(1, password);
		addContattoCassafortePS.setInt(2, id);
		addContattoCassafortePS.execute();
	}
	
	public void removeContattoCassaforteDB(int id) throws SQLException {
		PreparedStatement removeContattoCassafortePS;
		removeContattoCassafortePS = connection.prepareStatement("UPDATE contatto SET \"Password_Cassaforte\" = null WHERE contatto_id = ?");
		removeContattoCassafortePS.setInt(1, id);
		removeContattoCassafortePS.execute();
	}
	
	public void changePasswordDB (String nuovaPassword, String vecchiaPassword) throws SQLException {
		PreparedStatement changePasswordPS;
		changePasswordPS = connection.prepareStatement("UPDATE cassaforte SET \"password\" = ? WHERE \"password\" = ?");
		changePasswordPS.setString(1, nuovaPassword);
		changePasswordPS.setString(2, vecchiaPassword);
		changePasswordPS.execute();
	}
	
	public ArrayList<Contatto> getContattiProtetti () throws SQLException {
		PreparedStatement getContattiProtettiPS;
		ArrayList<Contatto> contattiProtetti = new ArrayList<>();
		getContattiProtettiPS = connection.prepareStatement("SELECT * FROM contatto WHERE \"Password_Cassaforte\" IS NOT NULL");
		ResultSet rs = getContattiProtettiPS.executeQuery();
		while (rs.next()) {
			Contatto contatto = new Contatto(rs.getInt("contatto_id"),rs.getString("prefisso_nome"), rs.getString("nome"), rs.getString("cognome"),rs.getString("path_foto"));
			contattiProtetti.add(contatto);
		}
		return contattiProtetti;
	}
	
}
