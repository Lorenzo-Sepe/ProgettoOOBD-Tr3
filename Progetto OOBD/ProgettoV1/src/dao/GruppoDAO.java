package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Contatto;

public interface GruppoDAO {

	public ArrayList<Integer> selectListaIdContattiGruppoDB (String nomeGruppo) throws SQLException;
}
