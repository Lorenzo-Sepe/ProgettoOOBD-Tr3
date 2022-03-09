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

public class Controller {
	private static Rubrica rubrica;
	// private boolean sync =false;

	// metodi

	public void dumpDati() throws SQLException {
		rubrica = new Rubrica();
		dumpListaContatti();
		if (cassaforteExist()) {
			dumpCassaforte();
		}
	}
	
	public void dumpCassaforte () throws SQLException {
		rubrica.creaCassaforte(dumpPasswordCassaforte());
		dumpListaCassaforte();
	}

	public void dumpListaContatti() throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		ArrayList<Contatto> contattiDB = rubricaDao.selectAllDB();
		for (Contatto contatto : contattiDB) {

			System.out.println("prova ripeti for " + contatto.StampaContatto());

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
	
	public void dumpListaGruppi() throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		ArrayList<Gruppo> listaGruppi = rubricaDao.selectListaGruppiDB();
		for (Gruppo gruppo : listaGruppi) {
			System.out.println("prova ripeti for "+gruppo.getNomeGruppo());			
			rubrica.creaGruppo(gruppo.getNomeGruppo());
		}
	}
	
	public void dumpListaCassaforte() throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		ArrayList <Contatto> listaContattiProtetti = new ArrayList<>();
		listaContattiProtetti = rubricaDao.getContattiProtetti();
		rubrica.getCassaforte().setListaContatti(listaContattiProtetti);
	}

	/**
	 * 
	 * @return ArrayList di Contatti della rubrica
	 */
	public ArrayList<Contatto> getListaContatti() {		
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

        // Verifica unicità numero
        numeri.remove(prefisso + numero);
        if (numeri.contains(prefisso + numero))
            throw new Exception("Il numero inserito è già presente nel contatto");

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
					throw new Exception("Esiste già un gruppo con questo nome");
				}
			}
		}
	}
	
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
					throw new Exception("Esiste già un gruppo con questo nome");
				}
			}
		}
	}

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
	
	public NumeriTelefonici getDeputatoDiNumero (int id, String pref, String num) {
	 	NumeriTelefonici deputato = rubrica.getContatto(id).getNumero(pref, num).getDeputato();
	 	  return deputato;	 
	   }
	
	/**
     * Metodo che crea  contatto da aggiungere nella arraylist delle rubrica <br>
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
	
	public void eliminaContattoDB (int id) throws SQLException {
		RubricaDAO rubricDao = new ImplementazioneRubricaDAO();
		rubricDao.removeContattoDB(id);
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
	
	public void eliminaMail(int id, String mailSelected) throws SQLException {
	    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	    contattoDao.deleteMail(id,mailSelected);
	}
	
	public void modificaMail(int id, String oldEmail, String newEmail) throws SQLException {
	    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	    contattoDao.updateMail(id, oldEmail, newEmail);

	}
	
	public int aggiungiAccount(int id,String nickname,String fornitore,String benvenuto, String email) {
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		return contattoDao.addAccountDB(id, nickname, fornitore, benvenuto, email);
	}
	
	public void aggiungiGruppo (String nomeGruppo, ArrayList<Contatto> membriGruppo) throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		rubricaDao.addGruppoDB(nomeGruppo, membriGruppo);
		
		rubrica.creaGruppo(nomeGruppo);
	}
	
	public boolean checkContattoInGruppo (int idContatto) {
		GruppoDAO gruppoDao = new ImplementazioneGruppoDAO();
		return gruppoDao.checkContattoInGruppo(idContatto);
	}
	
	public void eliminaGruppo (String nomeGruppo) throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		rubricaDao.deleteGruppoDB(nomeGruppo);
		rubrica.eliminaGruppo(nomeGruppo);
	}
	
	public void modificaGruppo (String vecchioNome, String nuovoNome, ArrayList<Contatto> membriGruppo) throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		rubricaDao.updateGruppoDB(vecchioNome, nuovoNome, membriGruppo);
	}
	
	public ArrayList<Contatto> getListaMembriGruppo (String nomeGruppo) {
		ArrayList<Contatto> listaContattiInGruppo = new ArrayList<>();
		return rubrica.getGruppo(nomeGruppo).getMembriGruppo();
	}
	
	public void dumpListaMembriGruppo (String nomeGruppo) throws SQLException {
		GruppoDAO gruppoDao = new ImplementazioneGruppoDAO();
		ArrayList<Contatto> listaMembriGruppo = gruppoDao.selectListaContattiGruppoDB(nomeGruppo);
		rubrica.getGruppo(nomeGruppo).setMembriGruppo(listaMembriGruppo);
	}
	
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
	
	
	public void creaCassaforte (String password) throws SQLException {
		RubricaDAO rubricaDAO = new ImplementazioneRubricaDAO();
		rubrica.creaCassaforte(password);
		rubricaDAO.createCassaforteDB(password);
	}
	
	public void aggiungiListaContattiCassaforte (String password ,ArrayList<Contatto> listaContatti) throws SQLException {
		getCassaforte().setListaContatti(listaContatti);
		RubricaDAO rubricaDAO = new ImplementazioneRubricaDAO();
		rubricaDAO.setPasswordContattiDB(password, listaContatti);
	}
	
	public Cassaforte getCassaforte () {
		return rubrica.getCassaforte();
	}
	
	public String dumpPasswordCassaforte () throws SQLException {
		String password;
		CassaforteDAO cassaforteDao = new ImplementazioneCassaforteDAO();
		password = cassaforteDao.getPasswordDB();
		return password;
	}
	
	public void aggiungiContattoInCassaforteDB (int id) throws SQLException {
		CassaforteDAO cassaforteDao = new ImplementazioneCassaforteDAO();
		String password = rubrica.getCassaforte().getPassword();
		cassaforteDao.addContattoCassaforteDB(id, password);
	}
	
	public void eliminaContattoDaCassaforteDB (int id) throws SQLException {
		CassaforteDAO cassaforteDao = new ImplementazioneCassaforteDAO();
		cassaforteDao.removeContattoCassaforteDB(id);
	}
	
	public void cambiaPasswordCassaforteDB (String nuovaPassword) throws SQLException {
		CassaforteDAO cassaforteDao = new ImplementazioneCassaforteDAO();
		String vecchiaPassword = rubrica.getCassaforte().getPassword();
		cassaforteDao.changePasswordDB(nuovaPassword, vecchiaPassword);
	}
	
	public void transactionBegin () {
		RubricaDAO rubrDao = new ImplementazioneRubricaDAO();
		rubrDao.transactionsBegin();
	}
	
	public void transactionCommit () {
		RubricaDAO rubrDao = new ImplementazioneRubricaDAO();
		rubrDao.transactionsCommit();
	}
	
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
				contattoDao.addIndirizziDB(id, indirizzo.getVia(), indirizzo.getCittà(), indirizzo.getCodicePostale(),
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
			rubrica.getContatto(id).setPathFoto(pathDestiCompleto);
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
	
	public boolean cassaforteExist () throws SQLException {
		RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
		return rubricaDao.cassaforteExist();
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
	
	public ArrayList<Contatto> getContattiCassaforte () {
		return rubrica.getCassaforte().getListaGruppo();
	}

	public void aggiungiContattoCassaforte(String pass, int id) {

		rubrica.getCassaforte().aggiungiContatto(rubrica.getContatto(id));
		getListaContatti().remove(rubrica.getContatto(id));
	}

	/**
	 * 
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
	 * 
	 * @param prefisso
	 * @param nome
	 * @param cognome
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
	 * 
	 * @param nickname
	 * @param fornitore
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
	 * 
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

	public ArrayList<String> verificaMailDuplicate() {
	    ArrayList<String> listaMail = new ArrayList<String>();
	    RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
	    listaMail = rubricaDao.verificaMailDuplicatiDao();
	    return listaMail;
	}


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
	 * 
	 * @param id
	 * @return prefisso del contatto
	 */
	public String getInfoContattoPrefisso (int id){
		Contatto contattoChiamato = rubrica.getContatto(id);
		String Prefisso = contattoChiamato.getPrefissoNome();
		return Prefisso;
	}
	
	/**
	 * 
	 * @param id
	 * @return nome del contatto
	 */
public String getInfoContattoNome (int id){
	Contatto contattoChiamato = rubrica.getContatto(id);
	String nome = contattoChiamato.getNome();
	return nome;
}	

/**
 * 
 * @param id
 * @return cognome del contatto
 */
public String getInfoContattoCognome (int id){
	Contatto contattoChiamato = rubrica.getContatto(id);
	String cognome = contattoChiamato.getCognome();
	return cognome;
}	



/**
 * 
 * @param id
 * @return quantità di numeri associati al contatto
 */
public Integer getInfoContattoNumeroQuantita (int id) {
    Integer ris = rubrica.getContatto(id).getListaNumeri().size();
    return ris;
}
/**
 * 
 * @param id
 * @return quantità di numeri associati al contatto
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

public Integer getNumeroQuantita (int id) {
	Integer ris = rubrica.getContatto(id).getListaNumeri().size();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return tag del numero di telefono
 */
public String getInfoContattoTagNumero(int i, int id) {
	String ris = rubrica.getContatto(id).getNumero(i).getTag();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return prefisso nazionale del numero 
 */
public String getInfoContattoPrefissoNumero(int i ,int id) {
	String ris = rubrica.getContatto(id).getNumero(i).getPrefisso();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return numero di telefono
 */
public String getInfoContattoNumeroNumero(int i, int id) {
	String ris = rubrica.getContatto(id).getNumero(i).getNumero();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return tipo del numero
 */
public String getInfoContattoNumeroTipo(int i, int id) {
	String ris = rubrica.getContatto(id).getNumero(i).getTipoNumero();
	return ris;
}

/**
 * 
 * @param id
 * @return numero degli indirizzi associati al Contatto
 */
public Integer getInfoContattoIndirizzoQuantità (int id) {
	Integer ris = rubrica.getContatto(id).getListaIndirizzi().size();
	return ris;
}

public int  getInfoContattoAccountId(int i, int id) {
    return getContatto(id).getListaAccount().get(i).getID();
}

public int getInfoContattoIndirizzoId(int i, int id) {
    int ris= rubrica.getContatto(id).getIndirizzo(i).getID();
    return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return via dell'indirizzo 
 */
public String getInfoContattoIndirizzoVia(int i, int id) {
	String ris = rubrica.getContatto(id).getIndirizzo(i).getVia();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return città dell'indirizzo
 */
public String getInfoContattoIndirizzoCittà(int i, int id) {
	String ris = rubrica.getContatto(id).getIndirizzo(i).getCittà();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return codice postale dell'indrizzo
 */
public String getInfoContattoIndirizzoCodicePostale(int i, int id) {
	String ris = rubrica.getContatto(id).getIndirizzo(i).getCodicePostale();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return nazione dell'indirizzo
 */
public String getInfoContattoIndirizzoNazione(int i, int id) {
	String ris = rubrica.getContatto(id).getIndirizzo(i).getNazione();
	return ris;
}

/**
 * 
 * @param id
 * @return numero di account associati al contatto
 */
public Integer getInfoContattoAccountQuantità(int id) {
	Integer ris = rubrica.getContatto(id).getListaAccount().size();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return fornitore dell'account
 */
public String getInfoContattoAccountFornitore(int i, int id) {
	String ris  = rubrica.getContatto(id).getAccount(i).getFornitore();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return nickname associata all'account
 */
public String getInfoContattoAccountNickname(int i, int id) {
	String ris = rubrica.getContatto(id).getAccount(i).getNickname();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return mail associata all'account
 */
public String getInfoContattoAccountMail(int i, int id) {
	String ris  = rubrica.getContatto(id).getAccount(i).getMail();
	return ris;
}

/**
 * 
 * @param i
 * @param id
 * @return frase di benvenuto dell'Account
 */
public String getInfoContattoAccountBenvenuto(int i, int id) {
	String ris = rubrica.getContatto(id).getAccount(i).getBenvenuto();
	return ris;
}

public ArrayList<String> getInfoContattoMailList(int id) {
    return rubrica.getContatto(id).getListaEmail();
}

public void setListaContattiCassaforte(ArrayList<Contatto> contattiInCassaforte) {
	rubrica.getCassaforte().setListaContatti(contattiInCassaforte);
}

public void eliminaAccount(int contattoId,int idOld,int index) {
    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
    contattoDao.deleteAccountDB(idOld);
    getContatto(contattoId).getListaAccount().remove(index);


}

public ArrayList<Account> eliminaAccountLista(int idOld, ArrayList <Account> lista) {
    int indexOld=0;
    for (int i = 0; i < lista.size(); i++) {
        if(lista.get(i).getID()==idOld) {
            indexOld = i;
        }
    } 
    lista.remove(indexOld);

    return lista;
}

public ArrayList<Account> modificaAccountLista(int idMod, ArrayList<Account> listaAccount, String fornitore, String nickname, String mail, String benvenuto) {
    int indexMod=0;
    for (int i = 0; i < listaAccount.size(); i++) {
        if(listaAccount.get(i).getID()==idMod) {
            indexMod = i;
        }
    } 
    Account AccountMod = new Account (listaAccount.get(indexMod).getID(),fornitore,nickname,benvenuto,mail); 
    listaAccount.set(indexMod, AccountMod);
    return listaAccount;
}

public void modificaAccount(int idContatto,int index,int idMod, String fornitore, String nickname, String mail, String fraseDiBenvenuto) throws SQLException {
    AccountDAO accountDao = new ImplementazioneAccountDAO();

    accountDao.updateAccountDB(idMod,fornitore,nickname,mail,fraseDiBenvenuto);
    getContatto(idContatto).getListaAccount().remove(index);
    getContatto(idContatto).getListaAccount().add(index, new Account(idMod, fornitore, nickname, fraseDiBenvenuto, mail));

}

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

public void deleteIndirizzo(int idContatto,int idIndirizzo,int indexIndirizzo) throws SQLException {
    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
    contattoDao.deleteIndirizzoDB(idIndirizzo);
    rubrica.getContatto(idContatto).getListaIndirizzi().remove(indexIndirizzo);
}

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

public String getInfoContattoIndirizzoTag(int i, int id) {
 	String ris = rubrica.getContatto(id).getIndirizzo(i).getTag();
 //	System.out.println("Tag:-"+ris);
 	return ris;
 }
 
 
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


 //Sepe

public ArrayList<String> riempiListaMail(int id) {
    ArrayList<String> ris = rubrica.getContatto(id).getListaEmail();
    return ris;
}

public ArrayList<Account> aggiungiAccountLista(int newId, String fornitore, String nick, String mail, String benvenuto, ArrayList<Account> listaAccount) {
    Account newAccount =new Account(newId, fornitore,nick, benvenuto,mail);
    listaAccount.add(newAccount);
    return listaAccount;
}

}