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
    
    public void updateGruppoDB (String vecchioNome, String nuovoNome, ArrayList<Integer> membriGruppo) throws SQLException;
    
    public void createCassaforteDB (String password) throws SQLException;
    
    public void deleteCassaforteDB () throws SQLException;
    
    public void setPasswordContattiDB (String password, ArrayList<Integer> listaContattiID) throws SQLException;
    
    public void unsetPasswordContattiDB (String password) throws SQLException;
    
    public boolean cassaforteExist () throws SQLException;
    
    public ArrayList<Contatto> getContattiProtetti () throws SQLException;
    
}