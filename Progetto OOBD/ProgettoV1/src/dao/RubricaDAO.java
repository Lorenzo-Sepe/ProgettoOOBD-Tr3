package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Contatto;

public interface RubricaDAO {


    public ArrayList<Contatto> selectAllDB ();
    
    public int  addContattoDB (String prefisso, String nome,String cognome,String path) throws SQLException;
    
    public void addGruppoDB (String nomeGruppo, ArrayList<Integer> membriGruppo) throws SQLException;

}