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
import Model.Contatto;

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
            modificaAccountPS=connection.prepareStatement("UPDATE ON account SET fornitore = ?, nickname = ?, mail = ?, frase_di_benvenuto = ? WHERE account_id = ? ;");
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

	public ArrayList<Account> leggiAccountDB(Contatto c) {
			PreparedStatement leggiAccountPS;
			ArrayList<Account> listaAccount = new ArrayList<>();		
			try {
				leggiAccountPS = connection.prepareStatement(
						//da cambare una volta finito il database
						"SELECT * FROM \"Account\".\"Contatto\" WHERE \"Contatto_Associato\"='"+c.getID()+"';");
						
			ResultSet rs = leggiAccountPS.executeQuery();
			while (rs.next()) {
				Account i = new Account (rs.getString("Fornitore"), rs.getString("Nickname"),rs.getString("Frase_di_Benvenuto"),  rs.getString("Mail") );
				listaAccount.add(i);
			}
			rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return listaAccount;		
		}
	
	public ArrayList<Integer> verificaDuplicatiAccountDao() {
        PreparedStatement verificaDuplicatiAccountDaoPS;
        ArrayList<Integer> listaRisultato = new ArrayList<>();
        try {
            verificaDuplicatiAccountDaoPS = connection.prepareStatement("select * from account where mail in (select mail from account group by mail having Count(*)>1);");
            ResultSet rs = verificaDuplicatiAccountDaoPS.executeQuery();
            while (rs.next()) {
                listaRisultato.add(rs.getInt("contatto_associato"));
                }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRisultato;
    }

}
