package implementazioneDAOpostgreSQL;

import DAO.ContattoDAO;
import Model.Contatto;
import DAO.Connessione;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImplementazioneContattoDAO implements ContattoDAO {
	
	private Connection connection;
	
	public ImplementazioneContattoDAO () {
		try {
			connection = Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addContattoDB (Contatto c) {
		PreparedStatement addContattoPS;
		try {
			addContattoPS = connection.prepareStatement("INSERT INTO Contatto (prefisso_nome,nome,cognome,path_foto) VALUES (?,?,?,?)");
			addContattoPS.setString(1, c.getPrefissoNome());
			addContattoPS.setString(2, c.getNome());
			addContattoPS.setString(3, c.getCognome());
			addContattoPS.setString(4, c.getPathFoto());
			addContattoPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteContattoDB (Contatto c) {
		PreparedStatement deleteContattoPS;
		try {
			deleteContattoPS = connection.prepareStatement("DELETE FROM Contatto WHERE contatto_ID = ?");
			deleteContattoPS.setInt(1, c.getID());
			deleteContattoPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateContattoDB (Contatto c, String prefisso, String nome, String cognome, String pathFoto) {
		PreparedStatement updateContattoPS;
		try {
			updateContattoPS = connection.prepareStatement("UPDATE Contatto SET prefisso_nome = ?, nome = ?, cognome = ?, path_foto = ? WHERE contatto_id = ?");
			updateContattoPS.setString(1, prefisso);
			updateContattoPS.setString(2, nome);
			updateContattoPS.setString(3, cognome);
			updateContattoPS.setString(4, pathFoto);
			updateContattoPS.setInt(5, c.getID());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Contatto> readContattoDB (Contatto c) {
		PreparedStatement readContattoPS;
		ArrayList<Contatto> listaContatti = new ArrayList<>();
		try {
			readContattoPS = connection.prepareStatement("SELECT * FROM Contatto WHERE contatto_id = ?");
			readContattoPS.setInt(1, c.getID());
			ResultSet rs = readContattoPS.executeQuery();
			while (rs.next()) {
				Contatto i = new Contatto (rs.getString("prefisso_nome"), rs.getString("nome"), rs.getString("cognome"),rs.getString("path_foto"));
				listaContatti.add(i);
				connection.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaContatti;
	}
	
	public ArrayList<Contatto> selectAllDB () {
		PreparedStatement selectAllPS;
		ArrayList<Contatto> listaContatti = new ArrayList<>();
		try {
			selectAllPS = connection.prepareStatement("SELECT * FROM Contatto ORDER BY contatto_id");
			ResultSet rs = selectAllPS.executeQuery();
			while (rs.next()) {
				Contatto i = new Contatto (rs.getString("prefisso_nome"), rs.getString("nome"), rs.getString("cognome"),rs.getString("path_foto"));
				listaContatti.add(i);
				connection.close();
			}
	}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaContatti;
	}
}
