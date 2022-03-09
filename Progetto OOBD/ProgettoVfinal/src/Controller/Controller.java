package Controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import DAO.*;
import Model.Account;
import Model.Cassaforte;
import Model.Contatto;
import Model.EnumPrefissoNumero;
import Model.Gruppo;
import Model.Indirizzi;
import Model.NumeriTelefonici;
import Model.Rubrica;
import ImplementazioneDAOpostgreSQL.*;

public class Controller{
	private static Rubrica rubrica;
	// private boolean sync =false;

	// metodi

	/**
	 * scarica dal database i dati salvati
	 * @throws SQLException
	 */
	public void dumpDati() throws SQLException {
		rubrica = new Rubrica();
		dumpListaContatti();
		if (cassaforteExist()) {
			dumpCassaforte();
		}
	}

	/**
	 * <p>
	 * Funziona che inizializza usando i dati presenti sul database.
	 * </p>
	 * <p>
	 * Utilizza  altre funzioni Per recuperare i dati: <br>
	 * 		{@link #dumpListaContatti() } <br>
	 * 		{@link #dumpListaCassaforte() } <br>
	 *
	 *  </p>
	 * @throws SQLException
	 */
	public void dumpCassaforte () throws SQLException {
		rubrica.creaCassaforte(dumpPasswordCassaforte());
		dumpListaCassaforte();
	}

	/**
	 * <p>
	 * salva nel {@link Controller#rubrica} un ArrayList di {@link Model.Contatto} recuperati dal database  <b> tramite apposite DAO</b>
	 * </p>
	 * <p>
	 * funzione Utilizzata in:  {@link #dumpDati()} <br>
	 *  @see {@link RubricaDAO}
	 * </p>
	 */
	public void dumpListaContatti() throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		ArrayList<Contatto> contattiDB = rubricaDao.selectAllDB();
		for (Contatto contatto : contattiDB) {

			//System.out.println("prova ripeti for " + contatto.StampaContatto());

			rubrica.aggiungiContatto(contatto);

		}
	}

	/**
	 *  <p>
	 * Funzione per recuperare tutte le informazioni di un contatto<b> tramite apposite  DAO per comunicare con il Database</b>
	 * </p>
	 * @param contattoID
	 * <p>
	 * funzione Utilizzata in:  {@link #dumpDati()} <br>
	 *  @see {@link DAO}
	 * </p>
	 *
	 */

    public void dumpContatto(int contattoID) {
        ContattoDAO contattoDao = new ImplementazioneContattoDAO();
//        Contatto contatto = new Contatto(contattoID, rubrica.getContatto(contattoID).getPrefissoNome(),
//                rubrica.getContatto(contattoID).getNome(), rubrica.getContatto(contattoID).getCognome(),
//                rubrica.getContatto(contattoID).getPathFoto());
//

        Contatto contatto = rubrica.getContatto(contattoID);
        if( contatto.getPathFoto()!=null&&contatto.getPathFoto().compareTo("")!=0) {
            contatto.setPathFoto("User"+contattoID+"."+getEstenzioneImmagine(contatto.getPathFoto()));
        }else {
            contatto.setPathFoto("");
        }
        contatto.setNumero(contattoDao.getListaNumeri(contattoID));

        contatto.setAccount(contattoDao.getListaAccount(contattoID));
        contatto.setIndirizzo(contattoDao.getListaIndirizzi(contattoID));
        for(Indirizzi indirizzo : contatto.getListaIndirizzi()) {
            System.out.println("Stampa Indirizzo DEl contatto:-"+indirizzo.stampaIndirizzo());
        }
        contatto.setEmail(contattoDao.getListaEmail(contattoID));



    }

		/**
		 * <p>
			 * Funzione per recuperare La lista dei gruppi<b> tramite apposite  DAO per comunicare con il Database</b>
			 * </p>
			 * <p>
			 *  @see {@link RubricaDAO} <br>
			 * </p>
		 * @throws SQLException
		 */
	public void dumpListaGruppi() throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		ArrayList<Gruppo> listaGruppi = rubricaDao.selectListaGruppiDB();
		for (Gruppo gruppo : listaGruppi) {
			System.out.println("prova ripeti for "+gruppo.getNomeGruppo());
			rubrica.creaGruppo(gruppo.getNomeGruppo());
		}
	}
	/**
	 * <p>
	 * Funzione per recuperare La lista dei contatti salvati  nel sottosistema di sicurezza<b> tramite apposite  DAO per comunicare con il Database</b>
	 * </p>
	 * <p>
	 *  @see {@link RubricaDAO} <br>
	 * </p>
	 * @throws SQLException
	 */
	public void dumpListaCassaforte() throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		ArrayList <Contatto> listaContattiProtetti = new ArrayList<>();
		listaContattiProtetti = rubricaDao.getContattiProtetti();
		rubrica.getCassaforte().setListaContatti(listaContattiProtetti);
	}

	/**
	 * getter della lista contatti
	 * @return ArrayList di Contatti della rubrica
	 */
	public ArrayList<Contatto> getListaContatti() {
		return rubrica.getListaContatti();
	}

	/**
	 * getter della gruppi
	 * @return ArrayList dei gruppi presenti
	 */
	public ArrayList<Gruppo> getListaGruppi () {
		return rubrica.getListaGruppi();
	}

	/**
	* Tramite un medoto presente in {@link Model.Rubrica} si scorre la ArrayList  e restituisce un oggetto  {@link Model.Contatto}
	* @param id int codice che identifica univocamente il contatto
	 * @return singolo contatto appartenente al model
	 */
	public Contatto getContatto(int id) {
		return rubrica.getContatto(id);

	}

	/**
 * <p>
 * dato un valore String vede se fa parte dell'enumeration  del prefissi <br> <b>attenzione è Case Sensitive </b>
 * </p>
 * @param value valore che verrà confrontato con i valori assunti dall'enumeration  {@link Model.EnumPrefissoNumero}
 * @return ritorna true se il valore è uguale a uno dei prefissi dell'enumeration <br> altrimenti restituisce false
 */
	public boolean isPrefissoNumero(String value) {
		EnumPrefissoNumero[] arrayEnumPrefissi = EnumPrefissoNumero.class.getEnumConstants();
		for (EnumPrefissoNumero enumPrefissoNumero : arrayEnumPrefissi) {
			if (enumPrefissoNumero.getPrefisso().equals(value))
				return true;
		}
		return false;
	}

	/**
	* <p>
	 * Verifica se il numero modificato inserto è valido <br>
	 * Riprende la funzione {@link #checkFormNumero(String, String, ArrayList)} <br>
	 * Differisce nella verifica della unicità del numero <br> Prima di fare il controllo elimina la prima occorrenza trovatasi nell'ArrayList
	* </p>
	* @param prefisso
	 * @param numero
	 * @param numeri ArrayList {@literal<String>} (formattata in questo modo Prefisso+numero)
	 * @throws Exception in caso di anomali lancia un messaggio di errore
	 * @see {@link #checkFormNumero(String, String, ArrayList) }
	 */
	public void checkFormNumeroModifica(String prefisso, String numero, ArrayList<String> numeriParametro) throws Exception {
        // TODO Da decidere come dare
        // verifica se i dati inseriti sono formattati bene
        ArrayList<String> numeri = new ArrayList<String>(numeriParametro);
        if (numero.matches("[0-9]+") == false)
            if (numero.isEmpty()) {
                throw new Exception("Non hai inserito un numero ");
            } else
                throw new Exception("Il numero:" + numero + "contiene dei caratteri ");
        else if (!isPrefissoNumero(prefisso))
            throw new Exception("il prefisso inserito non compare tra i prefissi selezionabili");

        // Verifica unicita numero
        numeri.remove(prefisso + numero);
        if (numeri.contains(prefisso + numero))
            throw new Exception("Il numero inserito è gia presente nel contatto");

    }

	/**
	 * verifica se il numero inserito è valido <br>
	 *   Controlla se il parametro prefisso sia uno dei possibili fissi dall'enumeration  {@link Model.EnumPrefissoNumero} <br>
	 *  Controlla se il numero è formato solo da numeri <br>
	 *  Controlla se il numero non sia presente tra ArrayList <br>
	 * @param prefisso String
	 * @param numero String
	 * @param numeri ArrayList {@literal<String>} (formattata in questo modo Prefisso+numero)
	 * @throws Exception in caso di anomali lancia un messaggio di errore
	 */
	public void checkFormNumero(String prefisso, String numero, ArrayList<String> numeri) throws Exception {
		// TODO Da decidere come dare
		// verifica se i dati inseriti sono formattati bene

		if (numero.matches("[0-9]+") == false)
			if (numero.isEmpty()) {
				throw new Exception("Non hai inserito un numero ");
			} else
				throw new Exception("Il numero:" + numero + "contiene dei caratteri ");
		else if (!isPrefissoNumero(prefisso))
			throw new Exception("il prefisso inserito non compare tra i prefissi selezionabili");

		// Verifica unicita numero
		if (numeri.contains(prefisso + numero))
			throw new Exception("Il numero inserito è gia presente nel contatto");

	}

	/**
	 * verifica se l'indirizzo inserito è valido
	 * <br>Controlla se il parametro contiene solo numeri
	 * @param codicePostale
	 * @return restituisce true se non ci sono anomalie nella formattazione del codice postale
	 * @throws Exception in caso di anomali lancia un messaggio di errore
	 */
	public boolean checkFormIndirizzo(String codicePostale) throws Exception {
		if (codicePostale.matches("[0-9]+") == false) {
			throw new Exception("il codice postale contiene dei caratteri");
		}
		return true;
	}

	/**
	 * verifica se la mail inserita è valida
	 * <br>controlla se il parametro mail sia ben formattato <br>
	 * controlla se il parametro mail  non appare nella ArrayList <br>
	 * @param mail String
	 * @param arrayListMail  ArrayList {@literal<String>}
	 * @throws Exception in caso di anomali lancia un messaggio di errore
	 */
	public void checkFormMail(String mail, ArrayList<String> arrayListMail) throws Exception {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
		// Controllo se la e-mail è formattata bene
		if (!matcher.find())
			throw new Exception("Email non valida");

		// Verifica unicita mail
		if (arrayListMail.contains(mail))
			throw new Exception("la mail inserita è gia presente nel contatto");

	}

	/**
	 * verifica se si sono inseriti due numeri con tipo diverso
	 * <br>dato un ArrayList di stringe vede se ci siano almeno un occorenza di valore <b>fisso </b> e una di valore  <b>mobile</b>
	 * @param arraylisyTipi ArrayList {@literal<String>}
	 * @throws Exception in caso di anomali lancia un messaggio di errore
	 */
	public void checkAlmenoDueNumeriConTipoDiverso(ArrayList<String> arraylisyTipi) throws Exception {
		boolean existTipoFisso = false;
		boolean existTipoMobile = false;

		for (String tipo : arraylisyTipi) {
			System.out.println("arrayTipi : "+tipo);
			if (tipo.compareToIgnoreCase("fisso") ==0)
				existTipoFisso = true;
			else if (tipo.compareToIgnoreCase("mobile") ==0)
				existTipoMobile = true;
		}
		System.out.println("existTipoFisso : "+existTipoFisso +"\nexistTipoMobile: "+existTipoMobile);

		if (existTipoFisso == false || existTipoMobile == false)
			throw new Exception("Non ci sono almeno due numeri con tip diverso ");
	}

	/**
	 * verifica che il nuovo nome non corrisponde ad un nome gia inserito e che il gruppo non sia vuoto
	 * <br>Controlla che il nomeGruppo non sia vuoto o che sai già stato usato  <br>
	 * Controlla che ci sia almeno un membro nel gruppo <br>
	 * @param nomeGruppo String
	 * @param membriGuppo ArrayList {@literal<Integer>} lista Id dei membri
	 * @throws Exception  in caso di anomali lancia un messaggio di errore
	 */
	public void checkGruppo (String nuovoNomeGruppo, String vecchioNomeGruppo ,ArrayList<Contatto> membriGuppo) throws Exception {
		while (nuovoNomeGruppo != null && nuovoNomeGruppo.startsWith(" ")) {
			nuovoNomeGruppo = nuovoNomeGruppo.substring(1);
		}
		if (nuovoNomeGruppo.compareTo("")==0) {
			throw new Exception("Gruppo senza nome");
		}
		else if (membriGuppo.isEmpty()) {
			throw new Exception("Non ci sono elementi nel gruppo");
		}
		else  if (nuovoNomeGruppo.compareTo(vecchioNomeGruppo)!=0) {
			for (Gruppo gruppo : rubrica.getListaGruppi()) {
				if (nuovoNomeGruppo.compareTo(gruppo.getNomeGruppo())==0) {
					throw new Exception("Esiste gia un gruppo con questo nome");
				}
			}
		}
	}

	/**
	 * verifica che il nuovo gruppo abbia un nome diverso da quelli gia esistenti e che non sia vuoto
	 * <br>Controlla che il nomeGruppo non sia vuoto o che sai già stato usato  <br>
	 * Controlla che ci sia almeno un membro nel gruppo <br>
	 * @param nomeGruppo String
	 * @param membriGuppo ArrayList {@literal<Integer>} lista Id dei membri
	 * @throws Exception  in caso di anomali lancia un messaggio di errore
	 */
	public void checkGruppo (String nomeGruppo,ArrayList<Contatto> membriGuppo) throws Exception {
		while (nomeGruppo != null && nomeGruppo.startsWith(" ")) {
			nomeGruppo = nomeGruppo.substring(1);
		}
		if (nomeGruppo.compareTo("")==0) {
			throw new Exception("Gruppo senza nome");
		}
		else if (membriGuppo.isEmpty()) {
			throw new Exception("Non ci sono elementi nel gruppo");
		}
		else {
			for (Gruppo gruppo : rubrica.getListaGruppi()) {
				if (nomeGruppo.compareTo(gruppo.getNomeGruppo())==0) {
					throw new Exception("Esiste gia un gruppo con questo nome");
				}
			}
		}
	}

	/**
	 * associa al numero 1 il numero 2 come reindirizzamento <br>
	 * Dati due numeri  devono già essere presenti nel Database <br>
	 *
	 * @param contattoId <b>int</b> identifica univocamente un contatto
	 * @param prefisso1  <b>String</b> prefisso del numero principale
	 * @param numero1  <b>String</b> Numero principale
	 * @param tipo1 <b>String</b> tipo del Numero principale
	 * @param prefisso2 <b>String</b> prefisso del numero secondario
	 * @param numero2	<b>String</b> numero secondario
	 * @param tipo2	<b>String</b> tipo del numero secondario
	 * @throws Exception se  i due tipi sono uguali lancia un exception <br> lancia un exception se non andassse a buon fine il salvataggio nel database;
	 * @throws Exception se il numeri non sono già presenti nel database
	 * @see {@link #aggiungiNumero(int, String, String, String, String)} metodo per salvare un numero nel database
	 */
	public void setDeputato (int contattoId, String prefisso1, String numero1, String tipo1, String prefisso2, String numero2, String tipo2) throws Exception {
        if (tipo1.compareToIgnoreCase(tipo2)==0) {
            throw new Exception("Il tipo del numero è uguale");
        }
        else {
            ContattoDAO contattoDAO = new ImplementazioneContattoDAO();
            if (tipo1.compareToIgnoreCase("Fisso")==0) {
                contattoDAO.setDeputatoFissoSuMobile(contattoId, prefisso2, numero2, prefisso1, numero1);
                //rubrica.getContatto(contattoId).getNumero(prefisso1, numero1).setDeputato(new NumeriTelefonici(prefisso2, numero1, tipo2, numero2));
            }
            else {
                contattoDAO.setDeputatoMobileSuFIsso(contattoId, prefisso1, numero1, prefisso2, numero2);
            }
        }
    }



	/**
     * Metodo che crea un contatto da aggiungere nella arraylist delle rubrica <br>
     * salva anche nel database <br>
     *
     * @param prefisso <b>String</b>
     * @param nome <b>String</b>
     * @param cognome <b>String</b>
     * @param path <b>String</b>
     * @return dopo la creazione del contatto restituisce un identificativo  univoco che è associato alcontatto
     */
    public int aggiungiContatto(String prefisso, String nome, String cognome, String path) throws SQLException {
        RubricaDAO rubricadao = new ImplementazioneRubricaDAO();
        int id = 0;

        if(getEstenzioneImmagine(path)==null) {
             id = rubricadao.addContattoDB(prefisso, nome, cognome, "");
        }else {
            id = rubricadao.addContattoDB(prefisso, nome, cognome, path);
        }


        if(path!=null && path.compareTo(GetRelativePath()+"NoImage.jpg")!=0)
            path = setFotoContatto(new File(path), id);

        rubrica.aggiungiContatto(new Contatto(id, prefisso, nome, cognome, path));
        return id;
    }

    /**
		* elimina dal database il contatto di id dato
		* @param id int codice che identifica univocamente il contatto
		* @throws SQLException
		*/
	public void eliminaContattoDB (int id) throws SQLException {
		RubricaDAO rubricDao = new ImplementazioneRubricaDAO();
		rubricDao.removeContattoDB(id);
	}

	/**
	 * Salva nel database un nuovo numero del contatto associato
	 * @param id int codice che identifica univocamente il contatto
	 * @param tag String
	 * @param prefisso String
	 * @param numero String
	 * @param tipo String
	 * @throws Exception per
	 */
	public void aggiungiNumero(int id,String tag,String prefisso,String numero, String tipo) {
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		contattoDao.addNumeriDB(id, prefisso, numero, tag, tipo);
	}

	/**
	 * Salva nel database un nuovo indirizzo  del contatto associato <br>
	 * <b>Nota: </b> qualora il parametro principale  è true diventera l'unico indirizzo ad esserlo
	 * @param id int codice che identifica univocamente il contatto
	 * @param tag String
	 * @param via String
	 * @param citta String
	 * @param codicePostale String
	 * @param nazione String
	 * @param principale  boolean true per indicare che è l'indirizzo principale <br>False per il contrario
	 * @return dopo la creazione del contatto restituisce un identificativo  univoco che è associato al indirizzo
	 *
	 */
	 public int aggiungiIndirizzo(int id,String tag,String via,String  citta ,String  codicePostale, String nazione, boolean principale) {
 		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
 		return contattoDao.addIndirizziDB(id, via, citta, codicePostale, nazione, tag, principale);
 	}

	/**
	 *  Salva nel database un nuovo indirizzo email  del contatto associato
	 * @param id int codice che identifica univocamente il contatto
	 * @param mail String
	 * @see {@link ContattoDAO}
	 */
	public void aggiungiMail(int id,String mail) {
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		contattoDao.addEmailDB(id, mail);
	}

	/**
	 * elimina dal database la mail data del contatto di id dato
	 * @param id int codice che identifica univocamente il contatto
	 * @param mailSelected
	 * @throws SQLException
	 */
	public void eliminaMail(int id, String mailSelected) throws SQLException {
	    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	    contattoDao.deleteMail(id,mailSelected);
	}

	/**
	 * modifica nel database la mail con il parametro NewEmail nel contatto di id dato
	 * @param id
	 * @param oldEmail
	 * @param newEmail
	 * @throws SQLException
	 */
	public void modificaMail(int id, String oldEmail, String newEmail) throws SQLException {
	    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	    contattoDao.updateMail(id, oldEmail, newEmail);

	}

	/**
	 * aggiunge al contatto di id dato un nuovo Account
	 * @param id int codice che identifica univocamente il contatto
	 * @param nickname String
	 * @param fornitore String
	 * @param benvenuto String
	 * @param email String
	 * @return
	 */
	public int aggiungiAccount(int id,String nickname,String fornitore,String benvenuto, String email) {
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		return contattoDao.addAccountDB(id, nickname, fornitore, benvenuto, email);
	}

	/**
	 * aggiunge nel database un nuovo gruppo con i contatti al suo interno
	 * @param nomeGruppo String
	 * @param membriGruppo String
	 * @throws SQLException
	 */
	public void aggiungiGruppo (String nomeGruppo, ArrayList<Contatto> membriGruppo) throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		rubricaDao.addGruppoDB(nomeGruppo, membriGruppo);

		rubrica.creaGruppo(nomeGruppo);
	}

	/**
	 * verifica che un contatto di id dato sia in un gruppo
	 * @param idContatto int codice che identifica univocamente il contatto
	 * @return true se appartiene, false altrimenti
	 */
	public boolean checkContattoInGruppo (int idContatto) {
		GruppoDAO gruppoDao = new ImplementazioneGruppoDAO();
		return gruppoDao.checkContattoInGruppo(idContatto);
	}

	/**
	 * Elimina il gruppo dal database
	 * @param nomeGruppo String
	 * @throws SQLException
	 */
	public void eliminaGruppo (String nomeGruppo) throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		rubricaDao.deleteGruppoDB(nomeGruppo);
		rubrica.eliminaGruppo(nomeGruppo);
	}

	/**
	 * modifica il nome ed elementi del gruppo selezionato del database
	 * @param vecchioNome vecchio nome del gruppo
	 * @param nuovoNome nuovo nome del gruppo
	 * @param membriGruppo arraylist dei membri
	 * @throws SQLException
	 */
	public void modificaGruppo (String vecchioNome, String nuovoNome, ArrayList<Contatto> membriGruppo) throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		rubricaDao.updateGruppoDB(vecchioNome, nuovoNome, membriGruppo);
	}

	/**
	 * metodo che va a prendere i membri dal gruppo
	 * @param nomeGruppo
	 * @return ArrayList dei membri del gruppo
	 */
	public ArrayList<Contatto> getListaMembriGruppo (String nomeGruppo) {
		ArrayList<Contatto> listaContattiInGruppo = new ArrayList<>();
		return rubrica.getGruppo(nomeGruppo).getMembriGruppo();
	}

	/**
	 * metodo che va a scaricare dal database i membri del gruppo
	 * @param nomeGruppo
	 * @throws SQLException
	 */
	public void dumpListaMembriGruppo (String nomeGruppo) throws SQLException {
		GruppoDAO gruppoDao = new ImplementazioneGruppoDAO();
		ArrayList<Contatto> listaMembriGruppo = gruppoDao.selectListaContattiGruppoDB(nomeGruppo);
		rubrica.getGruppo(nomeGruppo).setMembriGruppo(listaMembriGruppo);
	}

	/**
	 * metodo che aggiorna la lista contatti
	 * @param nomeGruppo
	 * @return Arraylist dei contatti meno i membri del gruppo selezionato
	 */
	public ArrayList<Contatto> getListaContattiMenoGruppo (String nomeGruppo) {
		ArrayList<Contatto> listaContattiMenoGruppo = new ArrayList<Contatto> (getListaContatti()); //TODO Speriamo bene
		ArrayList<Contatto> membriGruppo = new ArrayList<Contatto> (getListaMembriGruppo(nomeGruppo)); //TODO Speriamo bene
		boolean eliminato;

		for (int i1=0; i1<listaContattiMenoGruppo.size();i1++) {
			eliminato = false;
			for (int i2=0; !eliminato && i2<membriGruppo.size(); i2++) {
				if (listaContattiMenoGruppo.get(i1).getID()==membriGruppo.get(i2).getID()) {
					System.out.println("ListaContattiMenoGruppo "+listaContattiMenoGruppo.get(i1).StampaContatto());
					listaContattiMenoGruppo.remove(i1);
					membriGruppo.remove(i2);
					eliminato = true;
					i1--;
				}
			}
		}
		return listaContattiMenoGruppo;
	}

	/**
	 * metodo che crea la cassaforte nel database
	 * @param password
	 * @throws SQLException
	 */
	public void creaCassaforte (String password) throws SQLException {
		RubricaDAO rubricaDAO = new ImplementazioneRubricaDAO();
		rubrica.creaCassaforte(password);
		rubricaDAO.createCassaforteDB(password);
	}

	/**
	 * metodo che aggiunge alla cassaforte la lista dei contatti inseriti
	 * @param password
	 * @param listaContatti
	 * @throws SQLException
	 */
	public void aggiungiListaContattiCassaforte (String password ,ArrayList<Contatto> listaContatti) throws SQLException {
		getCassaforte().setListaContatti(listaContatti);
		RubricaDAO rubricaDAO = new ImplementazioneRubricaDAO();
		rubricaDAO.setPasswordContattiDB(password, listaContatti);
	}

	/**
	 * metodo usato per prendere la cassaforte
	 * @return oggetto Cassaforte
	 */
	public Cassaforte getCassaforte () {
		return rubrica.getCassaforte();
	}

	/**
	 *  scarica dal database la password della cassaforte
	 * @return la password della cassaforte
	 * @throws SQLException
	 */
	public String dumpPasswordCassaforte () throws SQLException {
		String password;
		CassaforteDAO cassaforteDao = new ImplementazioneCassaforteDAO();
		password = cassaforteDao.getPasswordDB();
		return password;
	}

	/**
	 * aggiunge un contatto alla cassaforte
	 * @param id
	 * @throws SQLException
	 */
	public void aggiungiContattoInCassaforteDB (int id) throws SQLException {
		CassaforteDAO cassaforteDao = new ImplementazioneCassaforteDAO();
		String password = rubrica.getCassaforte().getPassword();
		cassaforteDao.addContattoCassaforteDB(id, password);
	}

	/**
	 * elimina un contatto dalla cassaforte
	 * @param id
	 * @throws SQLException
	 */
	public void eliminaContattoDaCassaforteDB (int id) throws SQLException {
		CassaforteDAO cassaforteDao = new ImplementazioneCassaforteDAO();
		cassaforteDao.removeContattoCassaforteDB(id);
	}

	/**
	 * cambia nel database la cassaforte
	 * @param nuovaPassword
	 * @throws SQLException
	 */
	public void cambiaPasswordCassaforteDB (String nuovaPassword) throws SQLException {
		CassaforteDAO cassaforteDao = new ImplementazioneCassaforteDAO();
		String vecchiaPassword = rubrica.getCassaforte().getPassword();
		cassaforteDao.changePasswordDB(nuovaPassword, vecchiaPassword);
	}

	/**
	 * metodo per cominciare la transaction nel database
	 */
	public void transactionBegin () {
		RubricaDAO rubrDao = new ImplementazioneRubricaDAO();
		rubrDao.transactionsBegin();
	}

	/**
	 * metodo per confermare la transazione del database
	 */
	public void transactionCommit () {
		RubricaDAO rubrDao = new ImplementazioneRubricaDAO();
		rubrDao.transactionsCommit();
	}

	/**
	 * metodo per annullare la transazione nel database
	 */
	public void transactionRollBack () {
		RubricaDAO rubrDao = new ImplementazioneRubricaDAO();
		rubrDao.transactionsRollBack();
	}

	/**
	 * dato un contatto salva in locale soltanto le anagrafiche il database
	 * inserisce tutto
	 *
	 * @param contatto
	 * @throws SQLException
	 */
	public void aggiungiContatto(Contatto contatto) throws SQLException {
		int id = 0;
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		id = aggiungiContatto(contatto.getPrefissoNome(), contatto.getNome(), contatto.getCognome(),
				contatto.getPathFoto());
		if (!contatto.getListaNumeri().isEmpty()) {
			for (NumeriTelefonici numero : contatto.getListaNumeri()) {
				contattoDao.addNumeriDB(id, numero.getPrefisso(), numero.getNumero(), numero.getTag(),
						numero.getTipoNumero());
			}
		}
		if (!contatto.getListaIndirizzi().isEmpty()) {
			for (Indirizzi indirizzo : contatto.getListaIndirizzi()) {
				contattoDao.addIndirizziDB(id, indirizzo.getVia(), indirizzo.getCitta(), indirizzo.getCodicePostale(),
						indirizzo.getNazione(), indirizzo.getTag(),
						indirizzo.getPrincipale().booleanValue());
			}
		}
		if (!contatto.getListaEmail().isEmpty()) {
			for (String mail : contatto.getListaEmail()) {
				contattoDao.addEmailDB(id, mail);
			}
		}
		if (!contatto.getListaAccount().isEmpty()) {
			for (Account account : contatto.getListaAccount()) {
				contattoDao.addAccountDB(id, account.getNickname(), account.getFornitore(), account.getBenvenuto(),
						account.getMail());
			}
		}
	}

	/**
	 * metodo per ottenere il path relativo di una foto
	 * @param id
	 * @return Il percorso completo di dove salvare la foto profilo
	 */
	public String GetRelativePath() {
		String pathDestIniziale = null;
		try {
			pathDestIniziale = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		String pathSenzaBin = pathDestIniziale.substring(0, pathDestIniziale.length() - 4);
		String pathDestiCompSenzaNomefile = pathSenzaBin + "src/Immagini/";

		return pathDestiCompSenzaNomefile;
	}

	/**
	 * metodo che modifica la dimensione delle immagini inserite
	 * @param width
	 * @param height
	 * @param file
	 * @return BufferedImage ridimensionata del file immagine
	 */
	public BufferedImage getImageModificata(int width, int height, File file) {
        BufferedImage img = null;
        BufferedImage inputImage = null;
        try {
            inputImage = ImageIO.read(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // throw new Exception(e.getMessage());
        }
        // creates the output image
        img = new BufferedImage(width, height, inputImage.getType());

        // balance the input image to the output image
        Graphics2D g = img.createGraphics();
        g.drawImage(inputImage, 0, 0, width, height, null);
        g.dispose();

        return img;

    }

	/**
	 * Copia e fa un resize
	 *
	 * @param pathSorgente path della foto da salvare
	 * @param id           ID del contatto
	 * @param w
	 * @param h
	 * @throws IOException
	 * @return Ritorna il Path della foto Creata
	 */
	@Deprecated
	private String CaricaESettaFoto(String pathSorgente, int id, int w, int h) throws IOException {
		String pathDestinazione = null;
		// leggi file
		File f = new File(pathSorgente);
		BufferedImage img = getImageModificata(w, h, f);
		String name = pathSorgente.substring(pathSorgente.lastIndexOf("\\") + 1);

		// writes to the output file
		String estenzione = name.substring(name.lastIndexOf(".") + 1);
		pathDestinazione = GetRelativePath() + "User" + id + "." + estenzione; // concatenazione directory di
																				// salvataggio più l' ID del contatto
		System.out.println("pathSorgente: " + pathSorgente + "\n estensione: " + estenzione);
		System.out.println("pathDestinazione: " + pathDestinazione + "\n estensione: " + estenzione);
		ImageIO.write(img, estenzione, new File(pathDestinazione));
		// Modifico il Path Destinazione mettendo estenzione

		// BufferedImage inputImage = ImageIO.read(f);
		return pathDestinazione;

	}

	/**
	 * Setta foto di un contatto
	 *
	 * @param File     file con la foto,
	 * @param Contatto IDcontatto
	 */
	public String  setFotoContatto(File file, int id) {

        String pathSorgente = file.getAbsolutePath();
        String estenzione = pathSorgente.substring(pathSorgente.lastIndexOf(".") + 1);
        String pathDestiCompleto=null;
        estenzione = getEstenzioneImmagine(pathSorgente);
        if(estenzione!=null) {
             pathDestiCompleto = GetRelativePath() + "User" + id  +"."+ estenzione;
             if (esisteUnaFotoContatto(id))
                 eliminaFotoContatto(id);

                 copiaFoto(pathSorgente, pathDestiCompleto);
        }else return "";

        System.out.println("Sono COntroller.setFotoContatto: Stringa con pathSorgente : \n" + pathSorgente);



        System.out.println(
                "Sono COntroller.setFotoContatto:  Stringa con pathCompleto che viene restituito dal metodo CaricaESettaFoto: \n" + pathDestiCompleto);
//        try {
//            rubrica.getContatto(id).aggiungiFoto("User" + id + "." + estenzione);
//        } catch (Exception e) {
//            e.getStackTrace();
//            System.out.println("Errore hai provato a settare una foto a un contatto non esistente");
//
////        }
//        System.out.println("contatto " + id + " " + rubrica.getContatto(id).getNome() + " path: "
//                + rubrica.getContatto(id).getPathFoto());

        return "User" + id + "." + estenzione;
    }

	/**
	 * elimina nel database la foto dal contatto
	 * @param id
	 */
	private void eliminaFotoContatto(int id) {

		File fileFotoContatto = new File(getPathContatto(id));
		if (fileFotoContatto.exists() && !fileFotoContatto.isDirectory())
			fileFotoContatto.delete();

	}

	/**
	 * metodo che verifica se il contatto ha la foto
	 * @param id
	 * @return vero se il contatto ha la foto selezionata, falso altrimenti
	 */
	private boolean esisteUnaFotoContatto(int id) {
		String pathContatto = getPathContatto(id);
		if (pathContatto.substring(pathContatto.lastIndexOf("/")).startsWith("User" + id))
			return true;
		return false;

	}

	/**
	 * metodo copia la foto dal sorgente
	 * @param pathSorgente
	 * @param pathDestiCompleto
	 */
	public void copiaFoto(String pathSorgente, String pathDestiCompleto) {
		BufferedImage bufferFoto = null;
		String estenzione = pathSorgente.substring(pathSorgente.lastIndexOf(".") + 1);
		try {
			bufferFoto = ImageIO.read(new File(pathSorgente));
			File outputfile = new File(pathDestiCompleto);
			ImageIO.write(bufferFoto, estenzione, outputfile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Setta foto di un contatto
	 *
	 * @param Path     path foto,
	 * @param Contatto IDcontatto
	 */
	@Deprecated
	public void setFotoContatto(String path, int id) {
		String pathDestiCompleto = null;

		int pxw = 150;
		int pxh = 150;
		// TODO ContattoImplementazionePostgresDao
		/*
		 * Aggiungi al db ContattoDao contattoDao = new
		 * ContattoImplementazionePostgresDao(); contattoDao.SetPath(contatto)
		 *
		 */

		try {
			pathDestiCompleto = CaricaESettaFoto(path, id, pxw, pxh);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(
				"Stringa con pathCompleto che viene restituito dal metodo CaricaESettaFoto: \n" + pathDestiCompleto);
		try {
			rubrica.getContatto(id).setPathFoto(pathDestiCompleto);
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Errore hai provato a settare una foto a un contatto non esistente");

		}
		System.out.println("contatto " + id + " " + rubrica.getContatto(id).getNome() + " path "
				+ rubrica.getContatto(id).getPathFoto());

	}

	/**
	 * getter metodo che restituiesce il path della contatto indicato <br>
	 * @param id del contatto dato
	 * @return path della foto associata al contatto
	 * @see {@link #getGetRelativePath}
	 */
	public String getPathContatto(int id) {
        String path;
        try {
            path = getContatto(id).getPathFoto();
        } catch (Exception e) {
            path = "NoImage.jpg";
        }
        System.out.println("sono in controller.GetPAthContatto poco prima degli if:-"+ path);
        if (path == "") {
            return GetRelativePath() + "NoImage.jpg";
        } else {
            if (!new File(GetRelativePath() + path).exists()) {
                return GetRelativePath() + "NoImageFound.png";
            } else {
                System.out.println("sono in controller.GetPAthContatto:-"+GetRelativePath() + path);
                return GetRelativePath() + path;

            }

        }
    }

	// metodi di Rubrica e Cassaforte
	
	/**
	 * metodo per la creazione della cassaforte
	 * @param pass
	 * @throws Exception se già esiste una cassaforte 
	 */
	public void CreaCassaforte(String pass) throws Exception {
		if (rubrica.getCassaforte() == null) {
			rubrica.creaCassaforte(cifraPassword(pass));
		} else {
			throw new Exception("Errore non puoi creare un ulteriore cassaforte");
		}
	}

	/**
	 * metodo che verifica che la String oldPass sia corretta e dopo la modifica con la stringa newPAss
	 * @param oldPass String vecchia passoword
	 * @param newPass String Nuova password
	 * @throws Exception se la passoword inserita non è uguale alla precedente password
	 */
	public void cambiaPasswordCassaforte(String oldPass, String newPass) throws Exception {
		if (ceckPassword(oldPass)) {
			rubrica.getCassaforte().setPassword(newPass);
		} else {
			throw new Exception("Errore la passoword inserita non è uguale alla precedente password");
		}
	}

	/**
	 * Metodo per verificare l'esistenza della cassaforte
	 * @return true se la cassaforte esiste, false altrimenti
	 * @throws SQLException
	 */
	public boolean cassaforteExist () throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		return rubricaDao.cassaforteExist();
	}

	/**
	 *  confronta se la password inserita sia uguale a quella associata alla cassaforte
	 * @param oldPass String
	 * @return true se le due stringhe corrispondono, false altrimenti
	 * @throws Exception
	 */
	private boolean ceckPassword(String oldPass) throws Exception {
		if (rubrica.getCassaforte().getPassword().compareTo(oldPass) == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo per cifrare la passowrd
	 * @param pass String
	 * @return 
	 */
	private String cifraPassword(String pass) {
		// TODO Creare metodo funzionate ;
		return pass;
	}

	/**
	 * 
	 * @param pass
	 * @return un Arraylist di {@link Model.Contatto} appartenenti alla cassaforte
	 * @throws Exception
	 */
	public ArrayList<Contatto> getContattiCassaforte(String pass) throws Exception {
		if (ceckPassword(pass)) {
			return rubrica.getCassaforte().getListaGruppo();
		} else {
			throw new Exception("La password inserita non è corretta, ");
		}
	}

	public ArrayList<Contatto> getContattiCassaforte () {
		return rubrica.getCassaforte().getListaGruppo();
	}

	public void aggiungiContattoCassaforte(String pass, int id) {

		rubrica.getCassaforte().aggiungiContatto(rubrica.getContatto(id));
		getListaContatti().remove(rubrica.getContatto(id));
	}

	/**
	 *cerca i contatti con email simili alla stringa data 
	 * @param mail
	 * @return lista di contatti risultanti dalla ricerca
	 */
	public ArrayList<Contatto> searchMail(String mail) {
	    mail = "%"+mail+"%";
	    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	    ArrayList <Contatto> ListRisultati = new ArrayList <>();
	    ArrayList<Integer> ListID = contattoDao.SearchMail(mail);
	    for (Integer id : ListID) {
	        ListRisultati.add(rubrica.getContatto(id));
	    }
	    return ListRisultati;
	}


	/**
	 *cerca i contatti con anagrafie simili alle stringhe date 
	 * @param prefisso String
	 * @param nome String
	 * @param cognome String
	 * @return arraylist dei contatti risultati
	 */
	public ArrayList<Contatto> SearchAnagrafica(String prefisso, String nome, String cognome) {
	    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	    prefisso="%"+prefisso+"%";
	       nome="%"+nome+"%";
	        cognome="%"+cognome+"%";
	    ArrayList<Integer> ListID =  contattoDao.SearchAnagrafica(prefisso,nome,cognome);
	    ArrayList <Contatto> ListRisultati = new ArrayList <>();
	    // TODO algoritmo di aggiunta dei numeri
	    for (int i = 0; i < ListID.size(); i++) {
	         ListRisultati.add(rubrica.getContatto(ListID.get(i)));
	    }

	    return ListRisultati;
	}

	/**
	 *cerca i contatti con parametri di account simili alle stringhe date 
	 * @param nickname String
	 * @param fornitore String
	 * @return lista dei risultati della search
	 */
	public ArrayList<Contatto> searchAccount(String nickname, String fornitore) {
	    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	    nickname="%"+nickname+"%";
	    fornitore="%"+fornitore+"%";
	    ArrayList<Integer> ListID =  contattoDao.SearchAccount(nickname, fornitore);
	    //TODO Controllare la correttezza
	    ArrayList <Contatto> ListRisultati = new ArrayList <>();
	    for (int i = 0; i < ListID.size(); i++) {
	        ListRisultati.add(rubrica.getContatto(ListID.get(i)));
	    }
	    return ListRisultati;
	}

	/**
	 *cerca i contatti con Numeri simili alle stringa  data 
	 * @param prefissoNumero
	 * @param numero
	 * @param tipoNumero
	 * @return array list risultati della ricerca
	 */
	public ArrayList<Contatto> searchNumeri(String prefissoNumero, String numero, String tipoNumero) {
	    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	    prefissoNumero="%"+prefissoNumero+"%";
	    numero="%"+numero+"%";
	    ArrayList<Integer> ListID =  contattoDao.SearchNumeri(prefissoNumero, numero, tipoNumero);
	    //TODO Controllare la correttezza
	    ArrayList <Contatto> ListRisultati = new ArrayList <>();
	    for (int i = 0; i < ListID.size(); i++) {
	        ListRisultati.add(rubrica.getContatto(ListID.get(i)));
	    }
	    return ListRisultati;
	}

	/**
	 * 
	 * @param mail String
	 * @return I contatti che hanno email uguale al parametro mail dato 
	 */
	public ArrayList<Contatto> verificaDuplicatiContatto(String mail) {
	    ArrayList<Contatto> listaRisultato = new ArrayList();
	    ArrayList<Integer> listaID = new ArrayList();
	    RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
	    listaID = rubricaDao.verificaDuplicatiContattoDao(mail);
	        for (int j = 0; j < listaID.size(); j++) {
	            listaRisultato.add(rubrica.getContatto(listaID.get(j) ) );
	    }
	    return listaRisultato;
	}

	/**
	 * 
	 * @return ArrayList di stringhe contenente tutte le email che sono presenti in più contatti
	 */
	public ArrayList<String> verificaMailDuplicate() {
	    ArrayList<String> listaMail = new ArrayList<String>();
	    RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
	    listaMail = rubricaDao.verificaMailDuplicatiDao();
	    return listaMail;
	}

/**
 * 
 * @return ArrayList {@literal<Contatto>} con tutti i contatit che hanno email uguali fra i loro possibili {@link Model.Account}
 */
	public ArrayList<Contatto> verificaDuplicatiAccount() {
	    ArrayList<Contatto> listaRisultato = new ArrayList();
	    ArrayList<Integer> listaID = new ArrayList();
	    AccountDAO accountDao = new ImplementazioneAccountDAO();
	    listaID=accountDao.verificaDuplicatiAccountDao();
	        for (int j = 0; j < listaID.size(); j++) {
	            listaRisultato.add(rubrica.getContatto(listaID.get(j) ) );
	    }
	    return listaRisultato;
	}

	/**
	 *Getter del prefisso del contatto associato al parametro dato 
	 * @param id int codice che identifica univocamente il contatto
	 * @return prefisso del contatto
	 */
	public String getInfoContattoPrefisso (int id){
		Contatto contattoChiamato = rubrica.getContatto(id);
		String Prefisso = contattoChiamato.getPrefissoNome();
		return Prefisso;
	}

	/**
	 *Getter del nome del contatto associato al parametro dato 
	 * @param id int codice che identifica univocamente il contatto
	 * @return nome del contatto
	 */
public String getInfoContattoNome (int id){
	Contatto contattoChiamato = rubrica.getContatto(id);
	String nome = contattoChiamato.getNome();
	return nome;
}

/**
 *Getter del cognome del contatto associato al parametro dato 
	 * @param id int codice che identifica univocamente il contatto
 * @return cognome del contatto
 */
public String getInfoContattoCognome (int id){
	Contatto contattoChiamato = rubrica.getContatto(id);
	String cognome = contattoChiamato.getCognome();
	return cognome;
}



/**
 *Getter di quanti numeri ha il contatto associato al parametro dato 
	 * @param id int codice che identifica univocamente il contatto
 * @return quantita di numeri associati al contatto
 */
public Integer getInfoContattoNumeroQuantita (int id) {
    Integer ris = rubrica.getContatto(id).getListaNumeri().size();
    return ris;
}


/**
 * Modifica {@link Model.NumeriTelefonici} del contatto associato
 * @param contattoID int codice che identifica univocamente il contatto
 * @param tag String
 * @param prefisso String
 * @param numero String
 * @param tipoNEW String
 * @param tipoOLD String
 * @param PrefissoOLD String
 * @param numeroOLD String
 * @throws Exception
 */ 
public void modificaNumero(int contattoID,String tag,String prefisso,String numero,String tipoNEW,String tipoOLD,String PrefissoOLD,String numeroOLD) throws Exception {
    ContattoDAO contattoDao= new ImplementazioneContattoDAO();
    int idNumero=0;
    NumeriTelefoniciDAO numeriDao=new ImplementazioneNumeriTelefoniciDAO();
    if(tipoOLD.compareToIgnoreCase("fisso")==0) {
        idNumero=contattoDao.getIDNumeroFisso(PrefissoOLD, numeroOLD, contattoID);
    }else {
        idNumero=contattoDao.getIDNumeroMobile(PrefissoOLD, numeroOLD, contattoID);
    }

    numeriDao.updateNumeroDB(contattoID, idNumero, tag, prefisso, numero, tipoNEW, tipoOLD);

}

/**
 * Getter di quanti numeri ha il contatto associato al parametro dato 
	 * @param id int codice che identifica univocamente il contatto
 * @return
 */
public Integer getNumeroQuantita (int id) {
	Integer ris = rubrica.getContatto(id).getListaNumeri().size();
	return ris;
}

/**
 *Getter del tag del numero del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return tag del numero di telefono
 */
public String getInfoContattoTagNumero(int i, int id) {
	String ris = rubrica.getContatto(id).getNumero(i).getTag();
	return ris;
}

/**
 *Getter del prefisso del numero del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return prefisso nazionale del numero
 */
public String getInfoContattoPrefissoNumero(int i ,int id) {
	String ris = rubrica.getContatto(id).getNumero(i).getPrefisso();
	return ris;
}

/**
 *Getter del numero del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
*  @return numero di telefono
 */
public String getInfoContattoNumeroNumero(int i, int id) {
	String ris = rubrica.getContatto(id).getNumero(i).getNumero();
	return ris;
}

/**
 *Getter del tipo del numero del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return tipo del numero
 */
public String getInfoContattoNumeroTipo(int i, int id) {
	String ris = rubrica.getContatto(id).getNumero(i).getTipoNumero();
	return ris;
}

/**
 *Getter di quanti {@link Model.Indirizzi} ha il contatto associato al parametro dato 
* @param id int codice che identifica univocamente il contatto
 * @return numero degli indirizzi associati al Contatto
 */
public Integer getInfoContattoIndirizzoQuantita (int id) {
	Integer ris = rubrica.getContatto(id).getListaIndirizzi().size();
	return ris;
}

/**
 * Getter del {@link Model.Account} del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return
 */
public int  getInfoContattoAccountId(int i, int id) {
    return getContatto(id).getListaAccount().get(i).getID();
}

public int getInfoContattoIndirizzoId(int i, int id) {
    int ris= rubrica.getContatto(id).getIndirizzo(i).getID();
    return ris;
}

/**
 *Getter della via dell'indirizzo del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return via dell'indirizzo
 */
public String getInfoContattoIndirizzoVia(int i, int id) {
	String ris = rubrica.getContatto(id).getIndirizzo(i).getVia();
	return ris;
}

/**
 *Getter della citta' dell'indirizzo del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return citta dell'indirizzo
 */
public String getInfoContattoIndirizzoCitta(int i, int id) {
	String ris = rubrica.getContatto(id).getIndirizzo(i).getCitta();
	return ris;
}

/**
 *Getter del codice postale dell'indirizzo del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return codice postale dell'indrizzo
 */
public String getInfoContattoIndirizzoCodicePostale(int i, int id) {
	String ris = rubrica.getContatto(id).getIndirizzo(i).getCodicePostale();
	return ris;
}

/**
 *Getter della nazione dell'indirizzo del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return nazione dell'indirizzo
 */
public String getInfoContattoIndirizzoNazione(int i, int id) {
	String ris = rubrica.getContatto(id).getIndirizzo(i).getNazione();
	return ris;
}

/**
 *Getter di quanti {@link Model.Account} ha il contatto associato al parametro dato 
	 * @param id int codice che identifica univocamente il contatto
 * @return numero di account associati al contatto
 */
public Integer getInfoContattoAccountQuantita(int id) {
	Integer ris = rubrica.getContatto(id).getListaAccount().size();
	return ris;
}

/**
 *Getter del  fornitore  del {@link Model.Account} del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return fornitore dell'account
 */
public String getInfoContattoAccountFornitore(int i, int id) {
	String ris  = rubrica.getContatto(id).getAccount(i).getFornitore();
	return ris;
}

/**
 *Getter del nickname del {@link Model.Account} del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return nickname associata all'account
 */
public String getInfoContattoAccountNickname(int i, int id) {
	String ris = rubrica.getContatto(id).getAccount(i).getNickname();
	return ris;
}

/**
 *Getter dell'email associa al {@link Model.Account} del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return mail associata all'account
 */
public String getInfoContattoAccountMail(int i, int id) {
	String ris  = rubrica.getContatto(id).getAccount(i).getMail();
	return ris;
}

/**
 *Getter della frase di benvenuto del {@link Model.Account} del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return frase di benvenuto dell'Account
 */
public String getInfoContattoAccountBenvenuto(int i, int id) {
	String ris = rubrica.getContatto(id).getAccount(i).getBenvenuto();
	return ris;
}

/**
 * Getter di tutte emal associate al contatto al parametro dato
* @param id int codice che identifica univocamente il contatto
 * @return ArrayList@{@literal<String>} 
 */
public ArrayList<String> getInfoContattoMailList(int id) {
    return rubrica.getContatto(id).getListaEmail();
}

/**
 * Setta la lista dei contatti che sono nella cassaforte
 * @param contattiInCassaforte ArrayList{@literal<Contatto>}
 * @see
 * {@link Model.Contatto} <br>
 *{@link Model.Cassaforte}
 */
public void setListaContattiCassaforte(ArrayList<Contatto> contattiInCassaforte) {
	rubrica.getCassaforte().setListaContatti(contattiInCassaforte);
}

/**
 * elimina 
 * @param contattoId int codice che identifica univocamente il contatto
 * @param idOld int codice che identifica univocamente account 
 * @param index usato per eliminare Account dalla Arraylist{@literal<Account>} del contatto
 */
public void eliminaAccount(int contattoId,int idOld,int index) {
    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
    contattoDao.deleteAccountDB(idOld);
    getContatto(contattoId).getListaAccount().remove(index);


}


/**
 * 
 * @param idContatto int codice che identifica univocamente il contatto
 * @param index usato per modficare  Account dalla Arraylist{@literal<Account>} del contatto
 * @param idMod int codice che identifica univocamente account
 * @param fornitore String
 * @param nickname String
 * @param mail String
 * @param fraseDiBenvenuto String
 * @throws SQLException
 */
public void modificaAccount(int idContatto,int index,int idMod, String fornitore, String nickname, String mail, String fraseDiBenvenuto) throws SQLException {
    AccountDAO accountDao = new ImplementazioneAccountDAO();

    accountDao.updateAccountDB(idMod,fornitore,nickname,mail,fraseDiBenvenuto);
    getContatto(idContatto).getListaAccount().remove(index);
    getContatto(idContatto).getListaAccount().add(index, new Account(idMod, fornitore, nickname, fraseDiBenvenuto, mail));

}

/**
 * Modifica le anagrafie del contatto 
 * @param contattoID int codice che identifica univocamente il contatto
 * @param contattoPrefisso String
 * @param contattoNome String
 * @param contattoCognome String
 * @param contattoPath String
 */
public void modificaContattoAnagrafiche(int contattoID, String contattoPrefisso, String contattoNome,String contattoCognome,String contattoPath) {
    Contatto contatto =getContatto(contattoID);
    ContattoDAO contattoDao = new ImplementazioneContattoDAO();

        contattoPath= setFotoContatto(new File (contattoPath), contattoID);
        System.out.println("SOno controller.modifcaContattoAnagrafiche:- contattoPath:-"+contattoPath);
    contattoDao.updateContattoDB(contattoID, contattoPrefisso, contattoNome, contattoCognome, contattoPath);
    contatto.setPathFoto(contattoPath);
    contatto.setPrefissoNome(contattoPrefisso);
    contatto.setNome(contattoNome);
    contatto.setCognome(contattoCognome);

}

/**
 *
 * @param contattoID int codice che identifica univocamente il contatto
 * @param prefisso String
 * @param numero String
 * @param tipo String
 * @throws Exception
 */
public void eliminaNumero(int contattoID,String prefisso, String numero,String tipo) throws Exception {
 	ContattoDAO contattoDao= new ImplementazioneContattoDAO();
 	int idNumero=0;
 	NumeriTelefoniciDAO numeroDao=new ImplementazioneNumeriTelefoniciDAO();
 	if(tipo.compareToIgnoreCase("fisso")==0) {
 		idNumero=contattoDao.getIDNumeroFisso(prefisso, numero, contattoID);
 	}else {
 		idNumero=contattoDao.getIDNumeroMobile(prefisso, numero, contattoID);
 	}

 	contattoDao.removeNumeriDB(idNumero, tipo);


 }

/**
 * metodo che elimina un indirizzo di un dato contatto
 * @param idContatto int codice che identifica univocamente il contatto
 * @param idIndirizzo  int codice che identifica univocamente indirizzo
 * @param indexIndirizzo usato per eliminare indirizzo dalla Arraylist{@literal<indirizzo>} del contatto
 * @throws SQLException
 */
public void deleteIndirizzo(int idContatto,int idIndirizzo,int indexIndirizzo) throws SQLException {
    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
    contattoDao.deleteIndirizzoDB(idIndirizzo);
    rubrica.getContatto(idContatto).getListaIndirizzi().remove(indexIndirizzo);
}

/**
 * metodo che modifica un indirizzo
 * @param idContatto int codice che identifica univocamente accoun
 * @param idIndirizzo int codice che identifica univocamente indirizzo
 * @param tag String
 * @param via String
 * @param citta String
 * @param codicePostale String
 * @param nazione String
 * @param principale String
 * @param indexIndirizzo usato per modificare indirizzo dalla Arraylist{@literal<indirizzo>} del contatto

 */
public void modificaIndirizzo(int idContatto,int idIndirizzo,String tag,String via,String  citta ,String  codicePostale, String nazione, boolean principale,int indexIndirizzo) {
 	IndirizziDAO indirizzoDao = new ImplementazioneIndirizziDAO();

 	indirizzoDao.updateIndirizzoDB(idContatto, idIndirizzo, via, citta, codicePostale, nazione, tag, principale);

 	if(principale && indexIndirizzo!=0) {
 		if(indexIndirizzo==0) {
 			rubrica.getContatto(idContatto).getListaIndirizzi().remove(indexIndirizzo);
 			rubrica.getContatto(idContatto).getListaIndirizzi().add(0, new Indirizzi(idIndirizzo, principale, via, citta, codicePostale, nazione, nazione));
 	}else {
 		rubrica.getContatto(idContatto).getListaIndirizzi().remove(indexIndirizzo);
 		rubrica.getContatto(idContatto).getListaIndirizzi().add(indexIndirizzo, new Indirizzi(idIndirizzo, principale, via, citta, codicePostale, nazione, nazione));

 	}
 		for(Indirizzi indrizzo : getContatto(idContatto).getListaIndirizzi()) {
 			System.out.println("Sono dentro Controller.modificaIndirizzo:-"+indrizzo.stampaIndirizzo());
 		}

 }
}

/**
 * Getter del tag dell'indirizzo del contatto associato ai parametri dati
 * @param i index dell'elemento che vuoi prendere dall' ArrayList
* @param id int codice che identifica univocamente il contatto
 * @return
 */
public String getInfoContattoIndirizzoTag(int i, int id) {
 	String ris = rubrica.getContatto(id).getIndirizzo(i).getTag();

 	return ris;
 }

 /**
  * metodo che modifica nell'indirizzo di un contatto
  * @param id int codice che identifica univocamente il contatto
  * @param tag String
  * @param via String
  * @param citta String
  * @param codicePostale String
  * @param nazione String
  * @param principale String
  * @return id dell'indirizzo modificato
  */
 public int aggiungiIndirizzoModifica(int id,String tag,String via,String  citta ,String  codicePostale, String nazione, boolean principale) {
 	ContattoDAO contattoDao = new ImplementazioneContattoDAO();
 	int idIndirizzo = contattoDao.addIndirizziDB(id, via, citta, codicePostale, nazione, tag, principale);
 	if(principale) {
 		getContatto(id).getListaIndirizzi().add(0,new Indirizzi(idIndirizzo, principale, via, citta, codicePostale, nazione, tag));

 	}else {
 		getContatto(id).getListaIndirizzi().add(new Indirizzi(idIndirizzo, principale, via, citta, codicePostale, nazione, tag));
 	}

 	return idIndirizzo;
 }

 /**
  * metodo per estrarre dal path in ingresso l'estenzione dell'immagine
  * @param path
  * @return estenzione dell'immagine se trovata altrimente null
  */
 private String getEstenzioneImmagine(String path) {
	 String JPEG = "jpeg";
     String JPG = "jpg";
     String GIF = "gif";
     String TIFF = "tiff";
     String TIF = "tif";
     String PNG = "png";
     if(path!=null&&(path.endsWith(JPEG) || path.endsWith(JPG) || path.endsWith(GIF) || path.endsWith(TIFF) || path.endsWith(TIF) || path.endsWith(PNG))) {
    	 return path.substring(path.lastIndexOf(".")+1);
     }
     else {
    	 return null;
     }
    }






/**
 * metodo che cerca il deputato di un numero dato dal database
 * @param contattoID int codice che identifica univocamente il contatto
 * @param prefN String
 * @param numero String
 * @param tipoNumero String
 * @return oggetto NumeriTefonici del deputato
 */
public NumeriTelefonici getDeputatoDiNumero (int contattoID, String prefN, String numero,String tipoNumero) {
    NumeriTelefoniciDAO numeroDao= new ImplementazioneNumeriTelefoniciDAO();
    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
    int idNumero=0;
    try {
    if(tipoNumero.compareToIgnoreCase("fisso")==0)
        idNumero = contattoDao.getIDNumeroFisso(prefN, numero, contattoID);
    else
        idNumero = contattoDao.getIDNumeroMobile(prefN, numero, contattoID);

    return numeroDao.readDeputatoDiNumeroDB(idNumero, tipoNumero);

    }catch (Exception e) {
        return null;
    }
    }

}
