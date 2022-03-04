package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Account;
import Model.Contatto;
import Model.Indirizzi;
import Model.NumeriTelefonici;

import java.util.ArrayList;

public interface ContattoDAO {

    public void addContattoDB (Contatto c);

    public void deleteContattoDB (Contatto c);

    public void updateContattoDB (Contatto c, String prefisso, String nome, String cognome, String pathFoto);

    public ArrayList<Contatto> readContattoDB (Contatto c);

    public int addNumeriDB (int idContatto, String prefisso, String numero, String tag, String tipo);

    public ArrayList<NumeriTelefonici> getListaNumeri (int id);

    public int addIndirizziDB (int idContatto, String via, String citta, String codicePostale, String nazione, String tag, boolean principale );

    public ArrayList<Indirizzi> getListaIndirizzi (int id);

    public int addAccountDB (int idContatto ,String nickname, String fornitore, String benvenuto, String email);

    public ArrayList<Account> getListaAccount (int id);

    public void addEmailDB (int idContatto, String email);
    
    public void updateEmailDB (String emailPrima, String emailDopo) throws SQLException;
    
    public void deleteEmailDB (String email) throws SQLException;

    public  ArrayList<String> getListaEmail (int id);

    public ArrayList<Integer> SearchAnagrafica(String prefisso, String nome, String cognome);

    public ArrayList<Integer> SearchAccount(String nickname, String fornitore);


    public void setDeputatoMobileSuFIsso(int contattoId, String prefisso2, String numero2, String prefisso1,
            String numero1);

    public void setDeputatoFissoSuMobile(int contattoId, String prefisso1, String numero1, String prefisso2,
            String numero2);

    public void setFoto(int id, String pathDestiCompleto);

    public ArrayList<Integer> SearchMail(String mail);

    public ArrayList<String> verificaMailDuplicatiDao() ;
    
    public ArrayList<Integer> verificaDuplicatiContattoDao(String mail);

    public int getIDNumeroFisso(String prefissoFisso,String numeroFisso,int idContatto) throws Exception ;

    public int getIDNumeroMobile(String prefissoMobile,String numeroMobile, int idContatto) throws Exception ;

	

}