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
import Model.Contatto;
import Model.EnumPrefissoNumero;
import Model.Gruppo;
import Model.Indirizzi;
import Model.NumeriTelefonici;
import Model.Rubrica;
import ImplementazioneDAOpostgreSQL.*;

public class Controller {
	Rubrica rubrica;
	// private boolean sync =false;

	// metodi

	public void dumpDati() throws SQLException {
		int id = 0;
		rubrica = new Rubrica("mio");
		rubrica.aggiungiContatto(new Contatto(id++, "sig", "ale", "tri", null));
		rubrica.aggiungiContatto(new Contatto(id++, "sig", "lor", "sep", null));
		rubrica.aggiungiContatto(new Contatto(id++, "sig", "rai", "mor", null));

		for (int i = 0; i < id; i++) {
			for (int j = 1; j < 3 + 1; j++) {
				String emailtmp = j + "EsimaMaiDilUser" + i + "@salcazzo.dio";
				String numero = "555-" + j + rubrica.getContatto(i).getCognome() + i + i + i;
				NumeriTelefonici numeroComp = new NumeriTelefonici("tag", EnumPrefissoNumero.Italia.getPrefisso(),
						numero, "fisso");

				rubrica.getContatto(i).aggiungiEmail(emailtmp);
				// System.out.println(numeroComp.stampaNumero());
				String via = "Via Sal Cazzo numero " + j;
				String tag = "casa Numero" + j;
				rubrica.getContatto(i).aggiungiIndirizzo(new Indirizzi(i, false, via, "Napoli", "" + i, "Italia", tag));

//				String nomeContatto = rubrica.getContatto(i).getNome();	
//				String indirizzoComp=rubrica.getContatto(i).getIndirizzo(j-1).stampaIndirizzo();
				// System.out.println("Indirizzo di User "+nomeContatto+": "+indirizzoComp);
				rubrica.getContatto(i).aggiungiNumero(numeroComp);

				// System.out.println("numero
				// "+rubrica.getContatto(i).getNumero(0).getNumero());
				String fornitore = "servizio " + j;
				String nickname = "Itz_" + rubrica.getContatto(i).getPrefissoNome()
						+ rubrica.getContatto(i).getCognome();
				String benvenuto = "frase di benvenuto di " + rubrica.getContatto(i).getNome();
				String email = emailtmp;
				Account account = new Account(fornitore, nickname, benvenuto, email);
				rubrica.getContatto(i).aggiungiAccount(account);
			}
		}
		// leggi dal database

		dumpListaContatti();

//				RubricaDAO rubricaDao= new ImplementazioneRubricaDAO();
//				ContattoDAO contattoDao = new ImplementazioneContattoDAO();
//                ArrayList<Contatto> contattiDB = rubricaDao.selectAllDB();
//                ArrayList<NumeriTelefonici> numeriDB;
//                ArrayList<Indirizzi> indirizziDB;
//                ArrayList<Account> accountDB;
//
//             
//               for (Contatto contatto : contattiDB) {
//                
//                	System.out.println("prova ripeti for "+contatto.StampaContatto());
//                	
////                   numeriDB = contattoDao.getListaNumeri(contatto.getID());
////                    indirizziDB = contattoDao.getListaIndirizzi(contatto.getID());
////                    accountDB = contattoDao.getListaAccount(contatto.getID());
////                    contatto.aggiungiNumero(numeriDB);
////                    contatto.aggiungiIndirizzo(indirizziDB);
////                    contatto.aggiungiAccount(accountDB);
//                    rubrica.aggiungiContatto(contatto);
//				
//              }

	}

	public void dumpListaContatti() throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		ArrayList<Contatto> contattiDB = rubricaDao.selectAllDB();
		for (Contatto contatto : contattiDB) {

			System.out.println("prova ripeti for " + contatto.StampaContatto());

			rubrica.aggiungiContatto(contatto);

		}
	}

	public Contatto dumpContatto(int contattoID) {
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		Contatto contatto = new Contatto(contattoID, rubrica.getContatto(contattoID).getPrefissoNome(),
				rubrica.getContatto(contattoID).getNome(), rubrica.getContatto(contattoID).getCognome(),
				rubrica.getContatto(contattoID).getPathFoto());
		contatto.aggiungiNumero(contattoDao.getListaNumeri(contattoID));

		contatto.aggiungiAccount(contattoDao.getListaAccount(contattoID));
		contatto.aggiungiIndirizzo(contattoDao.getListaIndirizzi(contattoID));
		contatto.aggiungiEmail(contattoDao.getListaEmail(contattoID));
		return contatto;
	}
	
	public void dumpListaGruppi() throws SQLException {
		rubrica = new Rubrica("gruppi");
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		ArrayList<Gruppo> listaGruppi = rubricaDao.selectListaGruppiDB();
		for (Gruppo gruppo : listaGruppi) {
			System.out.println("prova ripeti for "+gruppo.getNomeGruppo());			
			rubrica.creaGruppo(gruppo.getNomeGruppo());
		}
	}

	/**
	 * 
	 * @return ArrayList di Contatti della rubrica
	 */
	public ArrayList<Contatto> getListaContatti() {
		// TODO RubricaImplementazionePostgresDao
		/*
		 * Lettura al db RubricaDao borsaDao = new RubricaImplementazionePostgresDao();
		 * RubricaDao.GetListaContatto()
		 */
		return rubrica.getListaContatti();
	}
	
	public ArrayList<Gruppo> getListaGruppi () {
		return rubrica.getListaGruppi();
	}

	public Contatto getContatto(int id) {
		return rubrica.getContatto(id);

	}

	public boolean isPrefissoNumero(String value) {
		EnumPrefissoNumero[] arrayEnumPrefissi = EnumPrefissoNumero.class.getEnumConstants();
		for (EnumPrefissoNumero enumPrefissoNumero : arrayEnumPrefissi) {
			if (enumPrefissoNumero.getPrefisso().equals(value))
				return true;
		}
		return false;
	}

	
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

		// Verifica unicità numero
		if (numeri.contains(prefisso + numero))
			throw new Exception("Il numero inserito è già presente nel contatto");

	}

	public boolean checkFormIndirizzo(String codicePostale) throws Exception {
		if (codicePostale.matches("[0-9]+") == false) {
			throw new Exception("il codice postale contiene dei caratteri");
		}
		return true;
	}

	public void checkFormMail(String mail, ArrayList<String> arrayListMail) throws Exception {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
		// Controllo se la e-mail è formattata bene
		if (!matcher.find())
			throw new Exception("Email non valida");

		// Verifica unicità mail
		if (arrayListMail.contains(mail))
			throw new Exception("la mail inserita è già presente nel contatto");

	}

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
	
	public void checkGruppo (String nomeGruppo, ArrayList<Integer> membriGuppo) throws Exception {
		if (nomeGruppo.equals("")) {
			throw new Exception("Gruppo senza nome");
		}
		else if (membriGuppo.isEmpty()) {
			throw new Exception("Non ci sono elementi nel gruppo");
		}
		else {
			dumpListaGruppi();
			for (Gruppo gruppo : rubrica.getListaGruppi()) {
				if (nomeGruppo.equals(gruppo.getNomeGruppo())) {
					throw new Exception("Esiste già un gruppo con questo nome");
				}
			}
		}
	}

	public void setDeputato (int contattoId, String prefisso1, String numero1, String tipo1, String prefisso2, String numero2, String tipo2) throws Exception {
        if (tipo1.equals(tipo2)) {
            throw new Exception("Il tipo del numero è uguale");
        }
        else {
            ContattoDAO contattoDAO = new ImplementazioneContattoDAO();
            if (tipo1.equals("Fisso")) {
                contattoDAO.setDeputatoFissoSuMobile(contattoId, prefisso1, numero1, prefisso2, numero2);
            }
            else {
                contattoDAO.setDeputatoMobileSuFIsso(contattoId, prefisso1, numero1, prefisso2, numero2);
            }
        }
    }
	
	/**
	 * Funzione che aggiunge un membro nella arraylist delle rubrica
	 * 
	 * @param prefisso
	 * @param nome
	 * @param cognome
	 * @param path
	 * @return
	 */
	public int aggiungiContatto (String prefisso, String nome, String cognome, String path) throws SQLException {
		RubricaDAO rubricadao = new ImplementazioneRubricaDAO();
		int id = 0;

		id = rubricadao.addContattoDB(prefisso, nome, cognome, path);

		setFotoContatto(new File(path), id);

		rubrica.aggiungiContatto(new Contatto(id, prefisso, nome, cognome, " "));
		return id;
	}

	public void aggiungiNumero(int id,String tag,String prefisso,String numero, String tipo) {
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		contattoDao.addNumeriDB(id, prefisso, numero, tag, tipo);
	}
	public void aggiungiIndirizzo(int id,String tag,String via,String  citta ,String  codicePostale, String nazione, boolean principale) {
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		contattoDao.addIndirizziDB(id, via, citta, codicePostale, nazione, tag, principale);
	}
	public void aggiungiMail(int id,String mail) {
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		contattoDao.addEmailDB(id, mail);
	}
	public void aggiungiAccount(int id,String tag,String nickname,String fornitore,String benvenuto, String email) {
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		contattoDao.addAccountDB(id, nickname, fornitore, benvenuto, email);
	}
	
	public void aggiungiGruppo (String nomeGruppo, ArrayList<Integer> membriGruppo) throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		rubricaDao.addGruppoDB(nomeGruppo, membriGruppo);
		
		rubrica.creaGruppo(nomeGruppo);
	}
	
	public void eliminaGruppo (String nomeGruppo) throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		rubricaDao.deleteGruppoDB(nomeGruppo);
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
				contattoDao.addIndirizziDB(id, indirizzo.getVia(), indirizzo.getCittà(), indirizzo.getCodicePostale(),
						indirizzo.getNazione(), indirizzo.getIdentificatore(),
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
	 * 
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
	 * 
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
																				// salvataggio pi� l' ID del contatto
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
	public void setFotoContatto(File file, int id) {

		String pathSorgente = file.getAbsolutePath();
		String estenzione = pathSorgente.substring(pathSorgente.lastIndexOf(".") + 1);
		String pathDestiCompleto = GetRelativePath() + "User" + id + "." + estenzione;
		if (esisteUnaFotoContatto(id))
			eliminaFotoContatto(id);
		copiaFoto(pathSorgente, pathDestiCompleto);
		System.out.println("Stringa con pathSorgente : \n" + pathSorgente);

		// TODO ContattoImplementazionePostgresDao
		/*
		 * Aggiungi al db ContattoDao contattoDao = new
		 * ContattoImplementazionePostgresDao(); contattoDao.SetPath(contatto)
		 *
		 */

		System.out.println(
				"Stringa con pathCompleto che viene restituito dal metodo CaricaESettaFoto: \n" + pathDestiCompleto);
		try {
			rubrica.getContatto(id).aggiungiFoto("User" + id + "." + estenzione);
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Errore hai provato a settare una foto a un contatto non esistente");

		}
		System.out.println("contatto " + id + " " + rubrica.getContatto(id).getNome() + " path: "
				+ rubrica.getContatto(id).getPathFoto());

	}

	private void eliminaFotoContatto(int id) {

		File fileFotoContatto = new File(getPathContatto(id));
		if (fileFotoContatto.exists() && !fileFotoContatto.isDirectory())
			fileFotoContatto.delete();

	}

	private boolean esisteUnaFotoContatto(int id) {
		String pathContatto = getPathContatto(id);
		if (pathContatto.substring(pathContatto.lastIndexOf("/")).startsWith("User" + id))
			return true;
		return false;

	}

	public void copiaFoto(String pathSorgente, String pathDestiCompleto) {
		BufferedImage bufferFoto = null;
		String estenzione = pathSorgente.substring(pathSorgente.lastIndexOf(".") + 1);
		try {
			bufferFoto = ImageIO.read(new File(pathSorgente));
			File outputfile = new File(pathDestiCompleto);
			ImageIO.write(bufferFoto, estenzione, outputfile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
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
			rubrica.getContatto(id).aggiungiFoto(pathDestiCompleto);
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Errore hai provato a settare una foto a un contatto non esistente");

		}
		System.out.println("contatto " + id + " " + rubrica.getContatto(id).getNome() + " path "
				+ rubrica.getContatto(id).getPathFoto());

	}

	public String getPathContatto(int id) {
		String path;
		try {
			path = rubrica.getContatto(id).getPathFoto();
		} catch (Exception e) {
			path = "NoImageFound.png";
		}

		if (path == " ") {
			return GetRelativePath() + "NoImage.jpg";
		} else {
			if (!new File(GetRelativePath() + path).exists()) {
				return GetRelativePath() + "NoImageFound.png";
			} else {
				return GetRelativePath() + path;

			}

		}
	}

	// metodi di Rubrica e Cassaforte

	public void CreaCassaforte(String pass) throws Exception {
		if (rubrica.getCassaforte() == null) {
			rubrica.creaCassaforte(cifraPassword(pass));
		} else {
			throw new Exception("Errore non puoi creare un ulteriore cassaforte");
		}
	}

	public void cambiaPasswordCassaforte(String oldPass, String newPass) throws Exception {
		if (ceckPassword(oldPass)) {
			rubrica.getCassaforte().setPassword(newPass);
		} else {
			throw new Exception("Errore la passoword inserita non è uguale alla precedente password");
		}
	}

	private boolean ceckPassword(String oldPass) throws Exception {
		if (rubrica.getCassaforte().getPassword().compareTo(oldPass) == 0) {
			return true;
		} else {
			return false;
		}
	}

	private String cifraPassword(String pass) {
		// TODO Creare metodo funzionate ;
		return pass;
	}

	public ArrayList<Contatto> getContattiCassaforte(String pass) throws Exception {
		if (ceckPassword(pass)) {
			return rubrica.getCassaforte().getListaGruppo();
		} else {
			throw new Exception("La password inserita non è corretta, ");
		}
	}

	public void aggiungiContattoCassaforte(String pass, int id) {

		rubrica.getCassaforte().aggiungiContatto(rubrica.getContatto(id));
		getListaContatti().remove(rubrica.getContatto(id));
	}

	public ArrayList<Contatto> SearchAnagrafica(String prefisso, String nome, String cognome) {
	    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	    if (prefisso == null) {prefisso="true";}; 
	    if (nome == null) {nome="true";}; 
	    if (cognome == null) {cognome="true";};
	    ArrayList<Integer> ListID =  contattoDao.SearchAnagrafica(prefisso,nome,cognome);
	    ArrayList <Contatto> ListRisultati = new ArrayList <>();
	    // TODO algoritmo di aggiunta dei numeri
	    //    ArrayList <Contatto> List = new ArrayList <>();
	    //    List = getListaContatti();
	    //
	    //    for (Integer id : ListID) {
	    //        ListRisultati.add(Rubrica.getContatto(id));
	    //    }
	    return ListRisultati;
	}


	public ArrayList<Contatto> searchAccount(String nickname, String fornitore) {
	    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	    if (nickname == null) {nickname="true";}; 
	    if (fornitore == null) {fornitore="true";}; 
	    ArrayList<Integer> ListID =  contattoDao.SearchAccount(nickname, fornitore);
	    ArrayList <Contatto> ListRisultati = new ArrayList <>();

	    // TODO Auto-generated method stub
	    return ListRisultati;
	}

}