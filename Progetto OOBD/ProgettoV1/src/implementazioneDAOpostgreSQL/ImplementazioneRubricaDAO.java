package implementazioneDAOpostgreSQL;

import java.sql.*;
import java.util.ArrayList;

import DAO.RubricaDAO;
import Database.Connessione;
import Model.Contatto;

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
	
	@Override
	public int addContattoDB(String prefisso, String nome, String cognome, String path) {
		 PreparedStatement addContattoPS;
         int id=0;
        try {
            addContattoPS = connection.prepareStatement("INSERT INTO Contatto (prefisso_nome,nome,cognome,path_foto,visibilitÃ ) VALUES (?,?,?,?,?) returning Contatto_id as ID");
            addContattoPS.setString(1, prefisso);
            addContattoPS.setString(2, nome);
            addContattoPS.setString(3, cognome);
            addContattoPS.setString(4, path);
            addContattoPS.setBoolean(5, true);
            ResultSet rs= addContattoPS.executeQuery();
             rs.next();
            id= rs.getInt("ID");
            

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

	}

}