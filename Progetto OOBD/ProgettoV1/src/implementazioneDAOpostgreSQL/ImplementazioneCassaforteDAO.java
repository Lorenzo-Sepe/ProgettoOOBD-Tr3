package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.CassaforteDAO;
import Database.Connessione;

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
}
