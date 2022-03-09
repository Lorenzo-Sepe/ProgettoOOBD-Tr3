package DAO;

/**
 * @author LorenzoSepe
 *
 */

import java.util.ArrayList;

public interface AccountDAO {

	public ArrayList<Integer> verificaDuplicatiAccountDao();
	 
	public void updateAccountDB(int idMod, String fornitore, String nickname, String mail, String fraseDiBenvenuto);

}