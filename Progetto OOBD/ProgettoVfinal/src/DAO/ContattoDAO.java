package DAO;

import java.sql.SQLException;

import Model.Account;
import Model.Contatto;
import Model.Indirizzi;
import Model.NumeriTelefonici;

import java.util.ArrayList;

public interface ContattoDAO {

	/**
	 * esegue una query sulla tabella contatto del database per estrarne uno
	 * @param id identificatore del contatto da estrarre
	 * @return oggetto della classe Contatto corrispondente al contatto estratto
	 */
    public Contatto readContattoDB (int id);

    /**
     * esegue una query sulla tabella numeri_telefonici_fissi o numeri_telefonici_mobili a seconda del tipo del numero
     * del database per inserire un numero e restituirne l'id generato dal database come serial
     * @param idContatto identificatore del contatto da associare al numero da aggiungere
     * @param prefisso Stringa rappresentante il prefisso nazionale del numero da aggiungere
     * @param numero Stringa rappresentante il numero telefonico da aggiungere
     * @param tag Stringa rappresentante il tag del numero telefoncioo da aggiungere
     * @param tipo Stringa rappresentante il tipo del numero da aggiungere
     * @return
     */
    public int addNumeriDB (int idContatto, String prefisso, String numero, String tag, String tipo);
    
    /**
     * esegue una query sulla tabella numeri_telefonici_fissi o numeri_telefonici_mobili a seconda del tipo del numero
     * del database per rimuovere un numero
     * @param idNumero identificatrore del numero da eliminare
     * @param tipo tipo del numero da eliminale, serve per stabilire su quale tabella svolgere la query
     * @throws SQLException
     */
    public void removeNumeriDB (int idNumero, String tipo) throws SQLException;

    /**
     * esegue una query sulla tabella numeri_telefonici_fissi e numeri_telefonici_mobili del database per restituire
     * la lista dei numeri associati ad un contatto
     * @param id identificatore del contatto associato
     * @return ArrayList di NumeriTelefonici ricavati dal ResulSet della query
     */
    public ArrayList<NumeriTelefonici> getListaNumeri (int id);

    /**
     * esegue una query sulle tabelle indirizzi e abita del database per aggiungere un indirizzo e restituirne l'id creato dal database
     * come serial
     * @param idContatto id del contatto a cui appartiene l'indirizzo da aggiungere
     * @param via Stringa rappresentante la via dell'indirizzo da aggiungere
     * @param citta Stringa rappresentante la citta dell'indirizzo da aggiungere
     * @param codicePostale Stringa rappresentante il codice postale dell'indirizzo da aggiungere
     * @param nazione Stringa rappresentante la nazione dell'indirizzo da aggiungere
     * @param tag Stringa rappresentante il tag dell'indirizzo da aggiungere
     * @param principale valore booleano che vale true se l'indirizzo da aggiungere e il principale del contatto, false altrimenti
     * @return identificatore dell'indirizzo dato dal ResulSet della query
     */
    public int addIndirizziDB (int idContatto, String via, String citta, String codicePostale, String nazione, String tag, boolean principale );
    
    /**
     * esegue una query sulle tabelle indirizzi e abita del database per rimuovere un indirizzo
     * @param indirizzoID identificatore dell'indirizzo da eliminare
     * @throws SQLException
     */
    public void deleteIndirizzoDB(int indirizzoID) throws SQLException;

    /**
     * esegue una query sulle tabelle indirizzi e abita del database per restituire la lista degli indirizzi appartenenti ad un contatto
     * @param id identificatore del contatto a cui appartengono gli indirizzi
     * @return ArrayList di Indirizzi ottenuto dal ResultSet della query
     */
    public ArrayList<Indirizzi> getListaIndirizzi (int id);

    /**
     * esegue una query sulla tabella account del database per aggiungere un account e restituirne l'id creato dal database
     * come serial
     * @param idContatto identificatore del contatto associato all'account
     * @param nickname Stringa rappresentante il nickname dell'account
     * @param fornitore Stringa rappresentante il forintore dell'account
     * @param benvenuto Stringa rappresentante la frase di benvenuto dell'account
     * @param email Stringa rappresentante la mail utilizzata dall'account
     * @return identificatore dell'account restituito dal ResultSet
     */
    public int addAccountDB (int idContatto ,String nickname, String fornitore, String benvenuto, String email);
    
    /**
     * esegue una query sulla tabella account del database per rimuovere un account
     * @param idOld identificatore dell'account da eliminare
     */
    public void deleteAccountDB (int idOld);

    /**
     * esegue una query sulla tabella account del database per restituire una lista degli account di un contatto
     * @param id identificatore del contatto a cui appartengono gli account
     * @return ArrayList di Account restituita dal ResultSet della qurey
     */
    public ArrayList<Account> getListaAccount (int id);

    /**
     * esegue una query sulla tabella mail del database per aggiungere una mail
     * @param idContatto id del contatto da associare alla mail
     * @param email Stringa rappresentante la mail
     */
    public void addEmailDB (int idContatto, String email);
    
    /**
     * esegue una query sulla tabella mail del database per modificare una mail
     * @param idContatto identificatore del contatto associato
     * @param oldEmail Stringa rappresentante la mail prima della modifica,  serve a torvare la mail all'inerno
     * della tabella, essendone la chiave primaria insieme al contatto associato
     * @param newEmail Stringa rappresentante la mail dopo la modifica
     * @throws SQLException
     */
    public void updateMail(int idContatto, String oldEmail, String newEmail) throws SQLException;
    
    /**
     * esegue una query sulla tabella mail del database per eliminare una mail
     * @param idContatto id del contatto associato alla mail da eliminare
     * @param email stringa rappresentante la mail del contatto
     * @throws SQLException
     */
    public void deleteMail(int idContatto, String email) throws SQLException;

    /**
     * esegue una query sulla tabella mail del database per restituire la lista di mail associate al contatto
     * @param id identificatore del contatto associato alle mail da restituire
     * @return ArrayList di Stringhe data dal ResulSet della query
     */
    public  ArrayList<String> getListaEmail (int id);

    /**
     * esegue una query sulla tabella contatto del database per restituire una lista contatti in base ai parametri dati
     * @param prefisso Stringa rappresentante il prefisso da ricercare
     * @param nome Stringa rappresentante il nome da ricercare
     * @param cognome Stringa rappresentante il cognome da ricercare
     * @return ArrayList di Integer dati dal ResulSet rappresentaniti gli id dei contatti trovati
     */
    public ArrayList<Integer> SearchAnagrafica(String prefisso, String nome, String cognome);

    /**
     * esegue una query sulla tabella contatto del database per restituire una lista contatti in base ai parametri dati
     * @param nickname Stringa rappresentante il nickname da ricercare
     * @param fornitore Stringa rappresentante il fornitore da ricercare
     * @return ArrayList di Integer dati dal ResulSet rappresentaniti gli id dei contatti trovati
     */
    public ArrayList<Integer> SearchAccount(String nickname, String fornitore);


    /**
     * esegue una query sulla tabella numeri_telefonici_mobili del database per impostare il deputato del numero
     * @param contattoId contatto associato al numero e al deputato da impostare
     * @param prefisso2 Stringa rappresentante il prefisso del numero associato
     * @param numero2 Stringa rappresentante il numero associato
     * @param prefisso1 Stringa rappresentante il prefisso del numero da associare
     * @param numero1 Stringa rappresentante il numero da associare
     */
    public void setDeputatoMobileSuFIsso(int contattoId, String prefisso2, String numero2, String prefisso1,
            String numero1);

    /**
     * esegue una query sulla tabella numeri_telefonici_fissi del database per impostare il deputato del numero
     * @param contattoId contatto associato al numero e al deputato da impostare
     * @param prefisso1 Stringa rappresentante il prefisso del numero associato
     * @param numero1 Stringa rappresentante il numero associato
     * @param prefisso2 Stringa rappresentante il prefisso del numero da associare
     * @param numero2 Stringa rappresentante il numero da associare
     */
    public void setDeputatoFissoSuMobile(int contattoId, String prefisso1, String numero1, String prefisso2,
            String numero2);

    /**
     * esegue una query sulla tabella numeri_telefonici_mobili del database per impostarne la foto
     * @param id identificatore del contatto al quale impostare la foto
     * @param pathDestiCompleto Stringa rappresentante il path completo della foto da settare
     */
    public void setFoto(int id, String pathDestiCompleto);

    /**
     * esegue una query sulla tabella contatto del database per restituire una lista contatti in base ai parametri dati
     * @param mail Stringa rappresentare la mail da cercare
     * @return ArrayList di Integer dati dal ResulSet rappresentaniti gli id dei contatti trovati
     */
    public ArrayList<Integer> SearchMail(String mail);
    
    /**
     * esegue una query sulla tabella numeri_telefonici_fissi del database per restituire l'id del numero
     * @param prefissoFisso Stringa rappresentante il prefisso del numero
     * @param numeroFisso Stringa rappresentante il numero del numero
     * @param idContatto identificatore del contatto associato al numero
     * @return identificatore del numero dato dal ResultSet
     * @throws Exception
     */
    public int getIDNumeroFisso(String prefissoFisso,String numeroFisso,int idContatto) throws Exception ;

    /**
     * esegue una query sulla tabella numeri_telefonici_mobili del database per restituire l'id del numero
     * @param prefissoFisso Stringa rappresentante il prefisso del numero
     * @param numeroFisso Stringa rappresentante il numero del numero
     * @param idContatto identificatore del contatto associato al numero
     * @return identificatore del numero dato dal ResultSet
     * @throws Exception
     */
    public int getIDNumeroMobile(String prefissoMobile,String numeroMobile, int idContatto) throws Exception ;

    /**
     * esegue una query sulla tabella contatto del database per restituire una lista contatti in base ai parametri dati
     * @param prefissoNumero Stringa rappresentante il prefisso del numero associato al contatto da ricercare
     * @param numero Stringa rappresentante il numero associato al contatto da ricercare
     * @param tipoNumero Stringa rappresentante il tipo del numero associato al contatto da ricercare
     * @return ArrayList di Integer dati dal ResulSet rappresentaniti gli id dei contatti trovati
     */
    public ArrayList<Integer> SearchNumeri(String prefissoNumero, String numero, String tipoNumero);

    /**
     * esegue una query sulla tabella contatto del database per modificare un contatto al suo interno
     * @param idContatto id del contatto da modificare
     * @param prefisso Stringa rappresentante il prefisso da impostare al contatto
     * @param nome Stringa rappresentante il nome da impostare al contatto
     * @param cognome Stringa rappresentante il cognome da impostare al contatto
     * @param pathFoto Stringa rappresentante il path della foto da impostare al contatto
     */
    public void updateContattoDB (int idContatto, String prefisso, String nome, String cognome, String pathFoto);

}