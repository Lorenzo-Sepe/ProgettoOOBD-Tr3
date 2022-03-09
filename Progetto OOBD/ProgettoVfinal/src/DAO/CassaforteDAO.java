package DAO;


import java.sql.SQLException;
import java.util.ArrayList;

import Model.Contatto;

public interface CassaforteDAO {

	/**
	 * esegue una query sulla tabella cassaforte del database per restituire la password associata
 	 * @return password della cassafrte dato dal ResulSet della query
	 * @throws SQLException
	 */
    public String getPasswordDB () throws SQLException;

    /**
     * esegue una query sulla tabella cassaforte del database per aggiungere un contatto 
     * @param id int identificatore del contatto associato
     * @param password String password 
     * @throws SQLException
     */
    public void addContattoCassaforteDB(int id, String password) throws SQLException;
	
    /**
	 * esegue una query sulla tabella cassaforte del database per eliminare  un contatto 
     * @param id int identificatore del contatto associato
	 * @throws SQLException
	 */
    public void removeContattoCassaforteDB(int id) throws SQLException;
    
	/**
	 * 
	 * esegue una query sulla tabella cassaforte del database per cambiare la passoword
	 * @param nuovaPassword  String che sostituira in caso di esito positivo la password associata alla cassaforte
	 * @param vecchiaPassword String passoword precedente da confrontare con quella che si trova nel database
	 * @throws SQLException
	 */
    public void changePasswordDB (String nuovaPassword, String vecchiaPassword) throws SQLException;
	
    /**
	 * 
	 * @return un ArrayList{@literal<Contatti>} dato dal ResulSet della query
	 * @throws SQLException
	 * @see {@link Model.Contatto}
	 */
    public ArrayList<Contatto> getContattiProtetti () throws SQLException;

}