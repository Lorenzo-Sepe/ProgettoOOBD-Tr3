package DAO;

import java.sql.SQLException;

import Model.Account;
import Model.Contatto;
import Model.Indirizzi;
import Model.NumeriTelefonici;

import java.util.ArrayList;

public interface ContattoDAO {

    public Contatto readContattoDB (int id);

    public int addNumeriDB (int idContatto, String prefisso, String numero, String tag, String tipo);
    
    public void removeNumeriDB (int idNumero, String tipo) throws SQLException;

    public ArrayList<NumeriTelefonici> getListaNumeri (int id);

    public int addIndirizziDB (int idContatto, String via, String citta, String codicePostale, String nazione, String tag, boolean principale );
    
    public void deleteIndirizzoDB(int indirizzoID) throws SQLException;

    public ArrayList<Indirizzi> getListaIndirizzi (int id);

    public int addAccountDB (int idContatto ,String nickname, String fornitore, String benvenuto, String email);
    
    public void deleteAccountDB (int idOld);

    public ArrayList<Account> getListaAccount (int id);

    public void addEmailDB (int idContatto, String email);
    
    public void updateMail(int idContatto, String oldEmail, String newEmail) throws SQLException;
    
    public void deleteMail(int idContatto, String email) throws SQLException;

    public  ArrayList<String> getListaEmail (int id);

    public ArrayList<Integer> SearchAnagrafica(String prefisso, String nome, String cognome);

    public ArrayList<Integer> SearchAccount(String nickname, String fornitore);


    public void setDeputatoMobileSuFIsso(int contattoId, String prefisso2, String numero2, String prefisso1,
            String numero1);

    public void setDeputatoFissoSuMobile(int contattoId, String prefisso1, String numero1, String prefisso2,
            String numero2);

    public void setFoto(int id, String pathDestiCompleto);

    public ArrayList<Integer> SearchMail(String mail);
    
    public int getIDNumeroFisso(String prefissoFisso,String numeroFisso,int idContatto) throws Exception ;

    public int getIDNumeroMobile(String prefissoMobile,String numeroMobile, int idContatto) throws Exception ;

    public ArrayList<Integer> SearchNumeri(String prefissoNumero, String numero, String tipoNumero);

    public void updateContattoDB (int idContatto, String prefisso, String nome, String cognome, String pathFoto);

}