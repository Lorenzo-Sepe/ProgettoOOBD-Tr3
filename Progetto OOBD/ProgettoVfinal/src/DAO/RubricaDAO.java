package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Contatto;
import Model.Gruppo;

public interface RubricaDAO {


	/**
	 * esegue una query sulla tabella Contatto del database per estrarre tuti i contatti al suo interno
	 * @return ArrayList di Contatto preso dal ResulSet della query
	 */
    public ArrayList<Contatto> selectAllDB ();
    
    /**
     * esegue una query sulla tabella Gruppi del database per estrarre tuti i gruppi al suo interno
     * @return ArrayList di Gruppo preso dal ResulSet della query
     * @throws SQLException
     */
    public ArrayList<Gruppo> selectListaGruppiDB () throws SQLException;
    
    /**
     * esegue una query sulla tabella Contatti del database per aggiungere un contatto e restituirne l'id creato dal database
     * come serial
     * @param prefisso Stringa rappresentante il prefisso del contatto da aggiungere
     * @param nome Stringa rappresentante il nome del contatto da aggiungere
     * @param cognome Stringa rappresentante il cognome del contatto da aggiungere
     * @param path Stringa rappresentante il path della foto del contatto da aggiungere
     * @return identificativo univoco del contatto restituito dal ResulSet
     * @throws SQLException
     */
    public int  addContattoDB (String prefisso, String nome,String cognome,String path) throws SQLException;
    
    /**
     * esegue una query sulla tabella contatto del database per rimuovere un contatto dal suo interno
     * @param id identificatore univoco del contatto da eliminare
     * @throws SQLException
     */
    public void removeContattoDB (int id) throws SQLException;
    
    /**
     * esegue una query sulla tabella gruppi del database per aggiungere un gruppo al suo inerno
     * @param nomeGruppo Stringa rappresentante il nome del gruppo
     * @param membriGruppo ArrayList di Contatto rappresentante la lista dei contatti del gruppo da aggiungere
     * @throws SQLException
     */
    public void addGruppoDB (String nomeGruppo, ArrayList<Contatto> membriGruppo) throws SQLException;
    
    /**
     * esegue una query sulla tabella gruppi del database per rimuovere un gruppo dal suo interno
     * @param nomeGruppo Stringa rappresentante il nome del gruppo da eliminare
     * @throws SQLException
     */
    public void deleteGruppoDB (String nomeGruppo) throws SQLException;
    
    /**
     * esegue una query sulla tabella gruppi del database per modificare un gruppo al suo inerno
     * @param vecchioNome Stringa rappresentante il nome del gruppo prima della modifica, serve a torvare il gruppo all'inerno
     * della tabella, essendone la chiave primaria
     * @param nuovoNome Stringa rappresentante il nome del gruppo dopo la modifica
     * @param membriGruppo  ArrayList di Contatto rappresentante la lista dei contatti che il gruppo deve contenere dopo la modifica
     * @throws SQLException
     */
    public void updateGruppoDB (String vecchioNome, String nuovoNome, ArrayList<Contatto> membriGruppo) throws SQLException;
    
    /**
     * esegue una query sulla tabella cassaforte del database per crearla
     * @param password	Stringa rappresentante la password della cassaforte da creare
     * @throws SQLException
     */
    public void createCassaforteDB (String password) throws SQLException;
    
    /**
     * esegue una query sulla tabella contatti del database per impostare la password della cassaforte ai contatti che ne fanno parte
     * @param password Stringa rappresentante la password della cassaforte da impostare ai contatti
     * @param listaContatti ArrayList di contatti che rappresenta i contatti a cui impostare la password
     * @throws SQLException
     */
    public void setPasswordContattiDB (String password, ArrayList<Contatto> listaContatti) throws SQLException;
    
    /**
     * esegue una query sulla tabella cassaforte del database per verificare se e vuota
     * @return valore booleano che sara uguale a true se la cassaforte e presente, false altrimenti
     * @throws SQLException
     */
    public boolean cassaforteExist () throws SQLException;
    
    /**
     * esegue una query sulla tabella Rubrica del database per generare una lista di mail duplicate
     * @return ArrayList di Stringhe corrispondenti alle mail duplicate
     */
    public ArrayList<String> verificaMailDuplicatiDao() ;
    
    /**
     * esegue una query sulla tabella Rubrica del database per estrarre gli id di tutti i contatti che presentano la mail duplicata
     * @param mail Stringa rappresentante la mail duplicata
     * @return ArrayList di interi che rappresentano gli id dei contatti con la mail duplicata
     */
    public ArrayList<Integer> verificaDuplicatiContattoDao(String mail);
    
    /**
     * esegue una query sulla tabella Rubrica del database per iniziare una transazione
     */
    public void transactionsBegin();
    
    /**
     * esegue una query sulla tabella Rubrica del database per confermare una transazione
     */
    public void transactionsCommit();

    /**
     * esegue una query sulla tabella Rubrica del database per annullare una transazione
     */
    public void transactionsRollBack();
    
}