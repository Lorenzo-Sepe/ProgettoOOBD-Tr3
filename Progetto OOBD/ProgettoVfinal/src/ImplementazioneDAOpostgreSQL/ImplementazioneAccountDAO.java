/**
 * 
 */
package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AccountDAO;
import Database.Connessione;
import Model.Account;

/**
 * @author LorenzoSepe
 *
 */
public class ImplementazioneAccountDAO implements AccountDAO {
	
private Connection connection;
	
	public ImplementazioneAccountDAO() {
		try {
			connection = Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateAccountDB(int idMod, String fornitore, String nickname, String mail, String fraseDiBenvenuto) {
        PreparedStatement modificaAccountPS;
        try {
            modificaAccountPS=connection.prepareStatement("UPDATE account SET fornitore = ?, nickname = ?, mail = ?, frase_di_benvenuto = ? WHERE account_id = ? ;");
            modificaAccountPS.setString(1, fornitore);
            modificaAccountPS.setString(2, nickname);
            modificaAccountPS.setString(3, mail);
            modificaAccountPS.setString(4, fraseDiBenvenuto);
            modificaAccountPS.setInt(5,idMod);
            modificaAccountPS.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
//	PreparedStatement verificaDuplicatiContattoDaoPS;
//	ArrayList<Integer> listaRisultato = new ArrayList<>();
//	try {
//		verificaDuplicatiContattoDaoPS = connection.prepareStatement("SELECT contatto_id from mail as mailES JOIN contatto on contatto_id=mailES.contatto_associato where mailES.indirizzo_email in  (?) ;");
//		verificaDuplicatiContattoDaoPS.setString(1, mail);
//		ResultSet rs = verificaDuplicatiContattoDaoPS.executeQuery();
//		while (rs.next()) {
//			listaRisultato.add(rs.getInt("contatto_id"));
//			}
//	}catch (SQLException e) {
//		e.printStackTrace();
//	}
//	return listaRisultato;

	public ArrayList<Account> verificaDuplicatiAccountDao(String mail) {
        PreparedStatement verificaDuplicatiAccountDaoPS;
        ArrayList<Account> listaRisultato = new ArrayList<>();
        try {
            verificaDuplicatiAccountDaoPS = connection.prepareStatement("SELECT * FROM account JOIN contatto on \"contatto_id\" = \"contatto_associato\" WHERE mail = ?;");
            verificaDuplicatiAccountDaoPS.setString(1, mail);
            ResultSet rs = verificaDuplicatiAccountDaoPS.executeQuery();
            while (rs.next()) {
                listaRisultato.add(new Account(rs.getInt("account_id"),rs.getInt("contatto_associato"),rs.getString("fornitore"), rs.getString("nickname"), rs.getString("frase_di_benvenuto"), rs.getString("mail")));
                }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRisultato;
    }
	
	

}
