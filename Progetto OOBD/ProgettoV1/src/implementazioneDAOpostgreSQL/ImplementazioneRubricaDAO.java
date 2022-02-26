package ImplementazioneDAOpostgreSQL;

import java.sql.*;
import java.util.ArrayList;

import DAO.RubricaDAO;
import Database.Connessione;
import Model.Contatto;
import Model.Gruppo;

public class ImplementazioneRubricaDAO implements RubricaDAO {

	private Connection connection;
	
	public ImplementazioneRubricaDAO() {
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
 
	public ArrayList<Contatto> selectAllDB () {
		PreparedStatement selectAllPS;
		ArrayList<Contatto> listaContatti = new ArrayList<>();
		try {
			selectAllPS = connection.prepareStatement("SELECT * FROM Contatto ORDER BY contatto_id");
			ResultSet rs = selectAllPS.executeQuery();
			while (rs.next()) {
				if(rs.getInt("contatto_id")!=1) {
				Contatto i = new Contatto (rs.getInt("contatto_id"),rs.getString("prefisso_nome"), rs.getString("nome"), rs.getString("cognome"),rs.getString("path_foto"));
				listaContatti.add(i);
				
			}
			}
			
	}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaContatti;
	}
	
    public ArrayList<Gruppo> selectListaGruppiDB () throws SQLException {
    	PreparedStatement selectListaGruppiDB;
    	ArrayList<Gruppo> listaGruppi = new ArrayList<>();
    	selectListaGruppiDB = connection.prepareStatement("SELECT * FROM gruppi ORDER BY nome");
    	ResultSet rs = selectListaGruppiDB.executeQuery();
    	while (rs.next()) {
    		Gruppo gruppo = new Gruppo(rs.getString("nome"));
    		listaGruppi.add(gruppo);
    	}
    	return listaGruppi;
    }
	
	@Override
	public int addContattoDB(String prefisso, String nome, String cognome, String path) throws SQLException {
		 PreparedStatement addContattoPS;
         int id=0;
            addContattoPS = connection.prepareStatement("INSERT INTO Contatto (prefisso_nome,nome,cognome, \"path_foto\") VALUES (?,?,?,?) returning Contatto_id as ID");
            addContattoPS.setString(1, prefisso);
            addContattoPS.setString(2, nome);
            addContattoPS.setString(3, cognome);
            addContattoPS.setString(4, path);
            ResultSet rs= addContattoPS.executeQuery();
             rs.next();
            id= rs.getInt("ID");
       
        return id;

	}
	
	public void addGruppoDB (String nomeGruppo, ArrayList<Integer> membriGruppo) throws SQLException {
		PreparedStatement addGruppoDB;
		addGruppoDB = connection.prepareStatement("INSERT INTO gruppi VALUES (?)");
		addGruppoDB.setString(1, nomeGruppo);
		addGruppoDB.execute();
		for (int membro : membriGruppo) {
			addGruppoDB = connection.prepareStatement("INSERT INTO appartenenza VALUES (?,?)");
			addGruppoDB.setString(1, nomeGruppo);
			addGruppoDB.setInt(2, membro);
			addGruppoDB.execute();
		}
	}
	
	public void deleteGruppoDB (String nomeGruppo) throws SQLException {
		PreparedStatement delteteGruppoPS;
		delteteGruppoPS = connection.prepareStatement("DELETE FROM appartenenza WHERE gruppo_nome = ?");
		delteteGruppoPS.setString(1, nomeGruppo);
		delteteGruppoPS.execute();
		delteteGruppoPS = connection.prepareStatement("DELETE FROM gruppi WHERE nome = ?");
		delteteGruppoPS.setString(1, nomeGruppo);
		delteteGruppoPS.execute();
	}

}