package DAO;

/**
 * @author LorenzoSepe
 *
 */


import java.sql.Connection;
import java.util.ArrayList;
import Model.Account;
import Model.Contatto;

public interface AccountDAO {

	public void addAccountDB(Account a, Contatto c);

	public ArrayList<Account> leggiAccountDB(Contatto c );
	
	 public ArrayList<Integer> verificaDuplicatiAccountDao();

}