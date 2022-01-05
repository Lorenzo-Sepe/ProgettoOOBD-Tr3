package dao;

/**
 * @author LorenzoSepe
 *
 */


import java.sql.Connection;
import java.util.ArrayList;
import model.Account;
import model.Contatto;

public interface AccountDAO {

	public void addAccountDB(Account a, Contatto c);

	public ArrayList<Account> leggiAccountDB(Contatto c );

}