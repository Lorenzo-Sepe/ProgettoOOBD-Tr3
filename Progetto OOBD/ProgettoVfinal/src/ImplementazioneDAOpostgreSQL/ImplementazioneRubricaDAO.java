package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			selectAllPS = connection.prepareStatement("SELECT * FROM Contatto WHERE \"Password_Cassaforte\" IS NULL ORDER BY nome");
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
	
	public void removeContattoDB (int id) throws SQLException {
		PreparedStatement removeContattoPS;
		PreparedStatement updateNumeriMobili;
		PreparedStatement updateNumeriFissi;
		updateNumeriMobili = connection.prepareStatement("UPDATE numeri_telefonici_mobili SET reindirizzamento = null WHERE contatto_associato = ?;");
		updateNumeriMobili.setInt(1, id);
		updateNumeriMobili.execute();
		updateNumeriFissi = connection.prepareStatement("UPDATE numeri_telefonici_fissi SET reindirizzamento = null WHERE contatto_associato = ?;");
		updateNumeriFissi.setInt(1, id);
		updateNumeriFissi.execute();
		removeContattoPS = connection.prepareStatement("DELETE FROM contatto WHERE contatto_id = ?");
		removeContattoPS.setInt(1, id);
		removeContattoPS.execute();
	}
	
	public void addGruppoDB (String nomeGruppo, ArrayList<Contatto> membriGruppo) throws SQLException {
		PreparedStatement addGruppoDB;
		addGruppoDB = connection.prepareStatement("INSERT INTO gruppi VALUES (?)");
		addGruppoDB.setString(1, nomeGruppo);
		addGruppoDB.execute();
		for (Contatto contatto : membriGruppo) {
			addGruppoDB = connection.prepareStatement("INSERT INTO appartenenza VALUES (?,?)");
			addGruppoDB.setString(1, nomeGruppo);
			addGruppoDB.setInt(2, contatto.getID());
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
	
	public void updateGruppoDB (String vecchioNome, String nuovoNome, ArrayList<Contatto> membriGruppo) throws SQLException {
		PreparedStatement updateGruppoPS;
		updateGruppoPS = connection.prepareStatement("DELETE FROM appartenenza WHERE gruppo_nome = ?");
		updateGruppoPS.setString(1, vecchioNome);
		updateGruppoPS.execute();
		updateGruppoPS = connection.prepareStatement("UPDATE gruppi SET \"nome\" = ? WHERE \"nome\" = ?");
		updateGruppoPS.setString(1, nuovoNome);
		updateGruppoPS.setString(2, vecchioNome);
		updateGruppoPS.execute();
		for (Contatto contatto : membriGruppo) {
			updateGruppoPS = connection.prepareStatement("INSERT INTO appartenenza VALUES (?,?)");
			updateGruppoPS.setString(1, nuovoNome);
			updateGruppoPS.setInt(2, contatto.getID());
			updateGruppoPS.execute();
		}
	}
	
	public void createCassaforteDB (String password) throws SQLException {
		PreparedStatement createCassafortePS;
		createCassafortePS = connection.prepareStatement("INSERT INTO cassaforte VALUES (?)");
		createCassafortePS.setString(1, password);
		createCassafortePS.execute();
	}
	
    public void deleteCassaforteDB () throws SQLException {
    	PreparedStatement deleteCassaforteDB;
    	deleteCassaforteDB = connection.prepareStatement("DELETE FROM cassaforte");
    	deleteCassaforteDB.execute();
    }
	
	public void setPasswordContattiDB (String password, ArrayList<Contatto> listaContatti) throws SQLException {
		PreparedStatement setPasswordContattiPS;
		for (Contatto contatto : listaContatti) {
			setPasswordContattiPS = connection.prepareStatement("UPDATE contatto SET \"Password_Cassaforte\" = ? WHERE contatto_id = ?");
			setPasswordContattiPS.setString(1, password);
			setPasswordContattiPS.setInt(2, contatto.getID());
			setPasswordContattiPS.execute();
		}
	}
	
	public boolean cassaforteExist () throws SQLException {
		PreparedStatement cassaforteExistsPS;
		int countPassword;
		cassaforteExistsPS = connection.prepareStatement("SELECT COUNT(\"password\") AS \"password\" FROM cassaforte");
		ResultSet rs = cassaforteExistsPS.executeQuery();
		rs.next();
		countPassword = rs.getInt("password");
		if (countPassword>0) {return true;}
		else {return false;}
	}
	
	public ArrayList<String> verificaMailDuplicatiDao() {
		
		PreparedStatement verificaDuplicatiContattoDaoPS;
		ArrayList<String> listaRisultato = new ArrayList<>();
		try {
			verificaDuplicatiContattoDaoPS = connection.prepareStatement("select mailD.Indirizzo_email from mail as mailD group by mailD.Indirizzo_email having Count(*)>1 ;");
			ResultSet rs = verificaDuplicatiContattoDaoPS.executeQuery();
			while (rs.next()) {
				listaRisultato.add(rs.getString("Indirizzo_email"));
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return listaRisultato;
	}
	
	//era arraylist string
	public ArrayList<String> verificaMailDuplicatiAccountDao(){
		PreparedStatement verifica;
		ArrayList<String> risultato = new ArrayList<>();
		
		try {
			verifica = connection.prepareStatement("SELECT mail FROM account GROUP BY mail HAVING COUNT(mail)>1 except SELECT mail FROM account where mail = 'Nessuna Mail';");
			ResultSet rs = verifica.executeQuery();
			while (rs.next()) {

				risultato.add(rs.getString("mail"));
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return risultato;
	}
	
	public ArrayList<Integer> verificaDuplicatiContattoDao(String mail) {
		PreparedStatement verificaDuplicatiContattoDaoPS;
		ArrayList<Integer> listaRisultato = new ArrayList<>();
		try {
			verificaDuplicatiContattoDaoPS = connection.prepareStatement("SELECT contatto_id from mail as mailES JOIN contatto on contatto_id=mailES.contatto_associato where mailES.indirizzo_email in  (?) ;");
			verificaDuplicatiContattoDaoPS.setString(1, mail);
			ResultSet rs = verificaDuplicatiContattoDaoPS.executeQuery();
			while (rs.next()) {
				listaRisultato.add(rs.getInt("contatto_id"));
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return listaRisultato;
	}
	
	public void transactionsBegin() {
		PreparedStatement inizioTransactions;
	try {
		inizioTransactions = connection.prepareStatement("BEGIN;");
		inizioTransactions.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	public void transactionsCommit() {
		PreparedStatement fineTransactions;
		try {
			fineTransactions = connection.prepareStatement("COMMIT;");
			fineTransactions.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void transactionsRollBack() {
		PreparedStatement fineTransactions;
		try {
			fineTransactions = connection.prepareStatement("ROLLBACK ;");
			fineTransactions.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}