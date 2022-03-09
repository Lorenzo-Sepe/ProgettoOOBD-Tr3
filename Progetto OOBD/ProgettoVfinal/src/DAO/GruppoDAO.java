package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Contatto;

public interface GruppoDAO {

	/**
	 * esegue una query sulla tabella Gruppo del database <br> 
	 * restituisce tutti i contatti associati del gruppo con il nome uguale a parametro dato
	 * @param nomeGruppo String del nome 
	 * @return ArrayList{@literal<Contatto>} dato dal ResulSet della query
	 * @throws SQLException
	 * @see {@link Model.Contatto}
	 */
    public ArrayList<Contatto> selectListaContattiGruppoDB (String nomeGruppo) throws SQLException;

    /**
     * esegue una query sulla tabella Gruppo del database per verificare se un contatto fa parte di un gruppo
     * @param idContatto int codice che identifica univocamente il contatto
     * @return boolean dato dal ResulSet della query
     */
    public boolean checkContattoInGruppo (int idContatto);
}