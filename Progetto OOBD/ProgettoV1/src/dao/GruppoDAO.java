package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Contatto;

public interface GruppoDAO {

	public ArrayList<Contatto> selectListaContattiGruppoDB (String nomeGruppo) throws SQLException;
	
	public boolean checkContattoInGruppo (int idContatto);
}
