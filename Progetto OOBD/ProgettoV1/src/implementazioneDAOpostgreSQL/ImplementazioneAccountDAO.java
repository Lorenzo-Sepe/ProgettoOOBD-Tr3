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

	@Override
	public void addAccountDB(Account a, Contatto c) {
		// TODO Auto-generated method stub
		PreparedStatement addAccountPS;
		try {
			addAccountPS = connection.prepareStatement("INSERT INTO ACCOUNT (Contatto_Associato, Fornitore, Nickname, mail, Frase_Di_Benvenuto) (?,?,?,?,?);");
			addAccountPS.setInt(1, c.getID());
			addAccountPS.setString(2 , a.getFornitore());
			addAccountPS.setString(3, a.getNickname());
			addAccountPS.setString(4, a.getMail());
			addAccountPS.setString(5, a.getBenvenuto());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
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

}

