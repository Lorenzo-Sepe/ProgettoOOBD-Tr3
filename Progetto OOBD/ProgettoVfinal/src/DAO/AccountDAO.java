package DAO;

/**
 * @author LorenzoSepe
 *
 */

import java.util.ArrayList;

import Model.Account;


public interface AccountDAO {

	/**
	 * esegue una query sulla tabella account del database per estrarre gli id di tutti gli account che presentano la mail duplicata
	 * @return ArrayList di Account
	 */
	public ArrayList<Account> verificaDuplicatiAccountDao(String mail);
	 
	/**
	 * esegue una query sulla tabella account del database per modificare un account
	 * @param idMod identificatore dell'account
	 * @param fornitore Stringa rappresentante il fornitore da impostare nell'account
	 * @param nickname Stringa rappresentante il nickname da impostare nell'account
	 * @param mail Stringa rappresentante la mail da impostare nell'account
	 * @param fraseDiBenvenuto Stringa rappresentante la frase di benvenuto da impostare nell'account
	 */
	public void updateAccountDB(int idMod, String fornitore, String nickname, String mail, String fraseDiBenvenuto);
	
	
}