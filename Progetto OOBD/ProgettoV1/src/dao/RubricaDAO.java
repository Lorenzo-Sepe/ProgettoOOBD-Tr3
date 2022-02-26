package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Contatto;
import Model.Gruppo;

public interface RubricaDAO {


    public ArrayList<Contatto> selectAllDB ();
    
    public ArrayList<Gruppo> selectListaGruppiDB () throws SQLException;
    
    public int  addContattoDB (String prefisso, String nome,String cognome,String path) throws SQLException;
    
    public void addGruppoDB (String nomeGruppo, ArrayList<Integer> membriGruppo) throws SQLException;
    
    public void deleteGruppoDB (String nomeGruppo) throws SQLException;

}