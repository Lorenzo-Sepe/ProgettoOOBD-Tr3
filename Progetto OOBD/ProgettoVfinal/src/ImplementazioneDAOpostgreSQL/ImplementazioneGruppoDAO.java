package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.GruppoDAO;
import Database.Connessione;
import Model.Contatto;

public class ImplementazioneGruppoDAO implements GruppoDAO {
	
private Connection connection;
	
	public ImplementazioneGruppoDAO() {
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

	public ArrayList<Contatto> selectListaContattiGruppoDB (String nomeGruppo) throws SQLException {
		PreparedStatement selectListaContattiGruppoPS;
		ArrayList<Contatto> listaIdContatti = new ArrayList<>();
		selectListaContattiGruppoPS = connection.prepareStatement("SELECT contatto.* FROM appartenenza, contatto WHERE contatto.contatto_id = appartenenza.contatto_id AND gruppo_nome = ?;");
		selectListaContattiGruppoPS.setString(1, nomeGruppo);
		ResultSet rs = selectListaContattiGruppoPS.executeQuery();
		while (rs.next()) {
			listaIdContatti.add(new Contatto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		return listaIdContatti;
	}
	
	public boolean checkContattoInGruppo (int idContatto) {
		PreparedStatement checkContattoInGruppoPS = null;
		try {
			checkContattoInGruppoPS = connection.prepareStatement("SELECT * FROM appartenenza WHERE contatto_id = ?");
			checkContattoInGruppoPS.setInt(1, idContatto);
			ResultSet rs = checkContattoInGruppoPS.executeQuery();
			return rs.next();
		}
		catch (SQLException e) {
			//System.out.println("Siamo nel catch "+checkContattoInGruppoPS.toString());
			e.printStackTrace();
			return false;
		}
	}
}
