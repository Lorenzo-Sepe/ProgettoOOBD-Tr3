package DAO;

import java.sql.SQLException;

public interface CassaforteDAO {
	
	public String getPasswordDB () throws SQLException;
	
	public void addContattoCassaforteDB(int id, String password) throws SQLException;
	
	public void removeContattoCassaforteDB(int id) throws SQLException;
	
	public void changePasswordDB (String nuovaPassword, String vecchiaPassword) throws SQLException;

}
