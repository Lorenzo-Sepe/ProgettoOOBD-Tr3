package Controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
	//private boolean sync =false;
	
	//metodi 
	
	public void dumpDati() throws SQLException {
        rubrica = new Rubrica("mio");
        dumpListaContatti();
        if (cassaforteExist()) {
            dumpCassaforte();
        }
    }
    
    public void dumpCassaforte () throws SQLException {
        rubrica.creaCassaforte(dumpPasswordCassaforte());
        dumpListaCassaforte();
    }

    public String dumpPasswordCassaforte () throws SQLException {
        String password;
        CassaforteDAO cassaforteDao = new ImplementazioneCassaforteDAO();
        password = cassaforteDao.getPasswordDB();
        return password;
    }
    
    public void dumpListaContatti() throws SQLException {
        RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
        ArrayList<Contatto> contattiDB = rubricaDao.selectAllDB();
        for (Contatto contatto : contattiDB) {

            System.out.println("prova ripeti for " + contatto.StampaContatto());

            rubrica.aggiungiContatto(contatto);

        }
    }

    public void dumpContatto(int contattoID) {
        ContattoDAO contattoDao = new ImplementazioneContattoDAO();
//        Contatto contatto = new Contatto(contattoID, rubrica.getContatto(contattoID).getPrefissoNome(),
//                rubrica.getContatto(contattoID).getNome(), rubrica.getContatto(contattoID).getCognome(),
//                rubrica.getContatto(contattoID).getPathFoto());
//
        Contatto contatto = rubrica.getContatto(contattoID);
        contatto.aggiungiNumero(contattoDao.getListaNumeri(contattoID));
        contatto.aggiungiAccount(contattoDao.getListaAccount(contattoID));
        contatto.aggiungiIndirizzo(contattoDao.getListaIndirizzi(contattoID));
        contatto.aggiungiEmail(contattoDao.getListaEmail(contattoID));
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
		
    
	public ArrayList<Integer> getListaIdContatti() {
		ArrayList<Integer> listaId = rubrica.getListaIdContatti();
		return listaId;
	}
	
	/**
	 * 
	 * @return ArrayList di Contatti della rubrica
	 */
	public ArrayList<Contatto> getListaContatti() {
		return rubrica.getListaContatti();
	}

/**
 * 	
 * @param id
 * @return contatti dal model
 */
	@Deprecated	
public Contatto getContatto(int id) {
	return rubrica.getContatto(id);
}

	/**
	 * 
	 * @param id
	 * @return prefisso del contatto
	 */
	public String getInfoContattoPrefisso (int id){
		Contatto contattoChiamato = rubrica.getContatto(id);
	 	String Prefisso = contattoChiamato.getPrefissoNome();
		if(Prefisso==null) return "";
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
	if(nome==null) return "";
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
	if(cognome==null) return "";
	return cognome;
}	

/**
 * 
 * @param id
 * @return quantità di numeri associati al contatto
 */
public Integer getNumeroQuantità (int id) {
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
	if(ris==null) return "";
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
	if(ris==null) return "";
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
	if(ris==null) return "";
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
	if(ris==null) return "";
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

/**
 * 
 * @param i
 * @param id
 * @return via dell'indirizzo 
 */
public String getInfoContattoIndirizzoVia(int i, int id) {
	String ris = rubrica.getContatto(id).getIndirizzo(i).getVia();
	if(ris==null) return "";
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
	if(ris==null) return "";
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
	if(ris==null) return "";
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
	if(ris==null) return "";
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
	if(ris==null) return "";
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
	if(ris==null) return "";
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
	if(ris==null) return "";
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
	if(ris==null) return "";
	return ris;
}

/**
 * 
 * @param id
 * @return lunghezza della lista di mail
 */
public Integer getInfoContattoMailQuantità(int id) {
	Integer ris = rubrica.getContatto(id).getListaEmail().size();
	return ris;
}

/**
 * 
 * @param id
 * @return arraylist di mail associate al contatto
 */
public ArrayList getInfoContattoMailList(int id) {
	return rubrica.getContatto(id).getListaEmail();
}
/**
 * 
 * @param value
 * @return verifica che il valore in ingresso sia un prefisso numero valido
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
	 * 
	 * @param prefisso
	 * @param numero
	 * @param numeri
	 * @throws Exception
	 */
	public void checkFormNumero(String prefisso, String numero, ArrayList<String> numeri) throws Exception {

        if (numero.matches("[0-9]+") == false)
            if (numero.isEmpty()) {
                throw new Exception("Non hai inserito un numero ");
            } else
                throw new Exception("Il numero: " + numero + "contiene dei caratteri ");
        else if (!isPrefissoNumero(prefisso))
            throw new Exception("il prefisso inserito non compare tra i prefissi selezionabili");

        // Verifica unicità numero
        if (numeri.contains(prefisso + numero))
            throw new Exception("Il numero inserito è già presente nel contatto");

    }

	/**
	 * 
	 * @param codicePostale
	 * @return verifica che il codice postale inserito sia solo numerico
	 * @throws Exception
	 */
	public boolean checkFormIndirizzo(String codicePostale) throws Exception {
		if (codicePostale.matches("[0-9]+") == false) {
			throw new Exception("il codice postale contiene dei caratteri");
		}
		return true;
	}

	/**
	 * 
	 * @param mail
	 * @param arrayListMail
	 * @throws Exception
	 */
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
	
	
/**
 * Funzione che aggiunge un membro nella arraylist delle rubrica 
 * @param prefisso
 * @param nome
 * @param cognome
 * @param path
 * @return ritorna l'id del contatto che si è appena inserito
 */
public int aggiungiContatto(String prefisso, String nome, String cognome, String path) throws SQLException {
    RubricaDAO rubricadao = new ImplementazioneRubricaDAO();
    int id = 0;

    id = rubricadao.addContattoDB(prefisso, nome, cognome, path);

    if(path.compareTo(GetRelativePath()+"NoImage.jpg")!=0)
    path=setFotoContatto(new File(path), id);


    rubrica.aggiungiContatto(new Contatto(id, prefisso, nome, cognome, path));
    return id;
}

/**
 *	dato un contatto salva in locale soltanto le anagrafiche il database inserisce tutto
 * @param contatto
 * @throws SQLException 
 */
public void aggiungiContatto(Contatto contatto) throws SQLException {
	int id=0;
	ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	id =aggiungiContatto(contatto.getPrefissoNome(), contatto.getNome(), contatto.getCognome(), contatto.getPathFoto());
	if(!contatto.getListaNumeri().isEmpty()) {
		for (NumeriTelefonici numero : contatto.getListaNumeri()) {
		contattoDao.addNumeriDB (id, numero.getPrefisso(), numero.getNumero(), numero.getTag(), numero.getTipoNumero());
		}
	}
	if(!contatto.getListaIndirizzi().isEmpty()) {
		for (Indirizzi indirizzo : contatto.getListaIndirizzi()) {
			contattoDao.addIndirizziDB(id, indirizzo.getVia(), indirizzo.getCittà(), indirizzo.getCodicePostale(), indirizzo.getNazione(), indirizzo.getIdentificatore(), indirizzo.getPrincipale().booleanValue());
		}
	}
	if(!contatto.getListaEmail().isEmpty()) {
		for (String mail: contatto.getListaEmail()) {
			contattoDao.addEmailDB(id, mail);
		}
	}
	if(!contatto.getListaAccount().isEmpty()) {
		for (Account account: contatto.getListaAccount()) {
			contattoDao.addAccountDB(id, account.getNickname(), account.getFornitore(), account.getBenvenuto(), account.getMail());
		}
	}
	
	
}
	/**
	 * 
	 * @param id 
	 * @return Il percorso completo di dove salvare la foto profilo
	 */
	public  String GetRelativePath() {
		String pathDestIniziale = null;
		try {
			pathDestIniziale = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		String pathSenzaBin = pathDestIniziale.substring(0,pathDestIniziale.length()-4);
         String pathDestiCompSenzaNomefile=pathSenzaBin+"src/Immagini/";	
         
         return pathDestiCompSenzaNomefile;
	}
	
	/**
	 * 
	 * @param width 
	 * @param height
	 * @param file
	 * @return  BufferedImage ridimensionata del file immagine 
	 */
	public BufferedImage  getImageModificata(int width, int height, File file) {
		BufferedImage img=null;
		BufferedImage inputImage = null;
		try {
			inputImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	// creates the output image
          img = new BufferedImage(width, height, inputImage.getType());
     
          // balance the input image to the output image
          Graphics2D g = img.createGraphics();
          g.drawImage(inputImage, 0, 0, width, height, null);
          g.dispose();
          
          return img ;
		
	}
	
	
	/**
	 * Copia e fa un resize 
	 * @param pathSorgente  path della foto da salvare 
	 * @param id ID del contatto 
	 * @param w 
	 * @param h
	 * @throws IOException
	 * @return Ritorna il Path della foto Creata
	 */
	@Deprecated
	private String CaricaESettaFoto(String pathSorgente, int id, int w, int h)
			 throws IOException 
			 {
				String pathDestinazione=null;
			      // leggi file
			      File f = new File(pathSorgente);
			      		BufferedImage img = getImageModificata(w, h, f);
			          String name = pathSorgente.substring(pathSorgente.lastIndexOf("\\")+1);
			          
			          // writes to the output file
			          String estenzione = name.substring(name.lastIndexOf(".")+1);
			          pathDestinazione = GetRelativePath()+ "User"+id+"."+estenzione; // concatenazione directory di salvataggio pi� l' ID del contatto
			          System.out.println("pathSorgente: "+pathSorgente+"\n estensione: "+estenzione);
			          System.out.println("pathDestinazione: "+pathDestinazione+"\n estensione: "+estenzione);
			          ImageIO.write(img, estenzione, new File(pathDestinazione));
			          //Modifico il Path Destinazione mettendo estenzione 
			      			      
			     // BufferedImage inputImage = ImageIO.read(f);
			 return pathDestinazione;

			 }
	/**
	 * Setta foto di un contatto
	 *
	 * @param File file con la foto,
	 * @param  Contatto IDcontatto
	 */
	public String setFotoContatto(File file, int id) {

	String pathSorgente= file.getAbsolutePath();
		 String estenzione = pathSorgente.substring(pathSorgente.lastIndexOf(".")+1);
		String pathDestiCompleto= GetRelativePath()+"User"+id+"."+estenzione;
		copiaFoto(pathSorgente, pathDestiCompleto);
		  System.out.println("Stringa con pathSorgente : \n"+pathSorgente);
		  
		  ContattoDAO contattoDao = new ImplementazioneContattoDAO();
			 contattoDao.setFoto(id, pathDestiCompleto);


            System.out.println("Stringa con pathCompleto che viene restituito dal metodo CaricaESettaFoto: \n"+pathDestiCompleto);
            try {
    			rubrica.getContatto(id).aggiungiFoto("User"+id+"."+estenzione);
    		} catch (Exception e) {
    			e.getStackTrace();
    			System.out.println("Errore hai provato a settare una foto a un contatto non esistente");

    		}
            System.out.println("contatto "+id+" "+rubrica.getContatto(id).getNome()+" path: "+rubrica.getContatto(id).getPathFoto());
            return "User"+id+estenzione;
	}
	
	public  void copiaFoto(String pathSorgente, String pathDestiCompleto) {
		BufferedImage bufferFoto = null;
		String estenzione = pathSorgente.substring(pathSorgente.lastIndexOf(".")+1);
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
	 * @param Path path foto, 
	 * @param  Contatto IDcontatto
	 */
	@Deprecated
	public void setFotoContatto(String path, int id) {
		String pathDestiCompleto=null;
		
		int pxw=150;
		int pxh=150;
            try {
				pathDestiCompleto=CaricaESettaFoto(path, id,pxw, pxh );
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            System.out.println("Stringa con pathCompleto che viene restituito dal metodo CaricaESettaFoto: \n"+pathDestiCompleto);
            try {
    			rubrica.getContatto(id).aggiungiFoto(pathDestiCompleto);
    		} catch (Exception e) {
    			e.getStackTrace();
    			System.out.println("Errore hai provato a settare una foto a un contatto non esistente");
    		
    		}
            System.out.println("contatto "+id+" "+rubrica.getContatto(id).getNome()+" path "+rubrica.getContatto(id).getPathFoto());
            ContattoDAO contattoDao = new ImplementazioneContattoDAO();
			contattoDao.setFoto(id, pathDestiCompleto);

	}
	
	public String getPathContatto(int id) {
		String path=rubrica.getContatto(id).getPathFoto();

		if(path==" ") {
			return GetRelativePath()+"NoImage.jpg";
		}else {
			if(!new File(GetRelativePath()+path).exists()) {
				return GetRelativePath()+"NoImageFound.png" ;
			}else {
				return GetRelativePath()+path;
				
			}
			
			
		}
	}
	
	//metodi di Rubrica e Cassaforte
	
	public void CreaCassaforte(String pass) throws Exception {
		if(rubrica.getCassaforte()==null){
			rubrica.creaCassaforte(cifraPassword(pass));
		}else {
			throw new Exception("Errore non puoi creare un ulteriore cassaforte");
		}		
	}
	public void cambiaPasswordCassaforte(String oldPass, String newPass) throws Exception {
		if(ceckPassword(oldPass) ){
			rubrica.getCassaforte().setPassword(newPass);
		}else {
			throw new Exception("Errore la passoword inserita non è uguale alla precedente password");
		}		
	}
	private boolean ceckPassword(String oldPass) throws Exception {
		if( rubrica.getCassaforte().getPassword().compareTo(oldPass) ==0) {
			return true;
		}else {
			return false;
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
	
	private String cifraPassword(String pass) {
		//TODO Creare metodo funzionate ;
		return pass;
	}

public ArrayList<Contatto> getContattiCassaforte(String pass) throws Exception{
	if(ceckPassword(pass)) {
	 return	rubrica.getCassaforte().getListaGruppo();
	}else {
		throw new Exception("La password inserita non è corretta, ");
		}
}

public void aggiungiContattoCassaforte(String pass,int id) {
	
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

/**
 * 
 * @param id
 * @param tag
 * @param prefisso
 * @param numero
 * @param tipo
 */
public void aggiungiNumero(int id,String tag,String prefisso,String numero, String tipo) {
    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
    contattoDao.addNumeriDB(id, prefisso, numero, tag, tipo);
}

/**
 * 
 * @param id
 * @param tag
 * @param via
 * @param citta
 * @param codicePostale
 * @param nazione
 * @param principale
 */
public void aggiungiIndirizzo(int id,String tag,String via,String  citta ,String  codicePostale, String nazione, boolean principale) {
    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
    contattoDao.addIndirizziDB(id, via, citta, codicePostale, nazione, tag, principale);
}

/**
 * 
 * @param id
 * @param mail
 */
public void aggiungiMail(int id,String mail) {
    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
    contattoDao.addEmailDB(id, mail);
}

/**
 * 
 * @param id
 * @param nickname
 * @param fornitore
 * @param benvenuto
 * @param email
 */
public void aggiungiAccount(int id,String nickname,String fornitore,String benvenuto, String email) {
    ContattoDAO contattoDao = new ImplementazioneContattoDAO();
    contattoDao.addAccountDB(id, nickname, fornitore, benvenuto, email);
}

/**
 * 
 * @param arraylisyTipi
 * @throws Exception
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


public boolean cassaforteExist () throws SQLException {
    RubricaDAO rubricaDao = new ImplementazioneRubricaDAO();
    return rubricaDao.cassaforteExist();
}


public ArrayList<Contatto> verificaDuplicatiContatto(String mail) {
	ArrayList<Contatto> listaRisultato = new ArrayList();
	ArrayList<Integer> listaID = new ArrayList();
	ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	listaID=contattoDao.verificaDuplicatiContattoDao(mail);
		for (int j = 0; j < listaID.size(); j++) {
			listaRisultato.add(rubrica.getContatto(listaID.get(j) ) );	
	}
	return listaRisultato;
}

public ArrayList<String> verificaMailDuplicate() {
	ArrayList<String> listaMail = new ArrayList<String>();
	ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	listaMail = contattoDao.verificaMailDuplicatiDao();
	return listaMail;
}


public ArrayList<Contatto> verificaDuplicatiAccount() {
	ArrayList<Contatto> listaRisultato = new ArrayList();
	ArrayList<Integer> listaID = new ArrayList();
	ContattoDAO contattoDao = new ImplementazioneContattoDAO();
	listaID=contattoDao.verificaDuplicatiAccountDao();
		for (int j = 0; j < listaID.size(); j++) {
			listaRisultato.add(rubrica.getContatto(listaID.get(j) ) );	
	}
	return listaRisultato;
}


public void eliminaContattoDB (int id) throws SQLException {
    RubricaDAO rubricDao = new ImplementazioneRubricaDAO();
    rubricDao.removeContattoDB(id);
}

public void modificaNumero(int contattoID,String tag,String prefisso,String numero,String tipoNEW,String tipoOLD) throws Exception {
    ContattoDAO contattoDao= new ImplementazioneContattoDAO();
    int idNumero=0;
    NumeriTelefoniciDAO numeriDao=new ImplementazioneNumeriTelefoniciDAO();
    if(tipoOLD.compareToIgnoreCase("fisso")==0) {
        idNumero=contattoDao.getIDNumeroFisso(prefisso, numero, contattoID);
    }else {
        idNumero=contattoDao.getIDNumeroMobile(prefisso, numero, contattoID);
    }

    numeriDao.updateNumeroDB(contattoID, idNumero, tag, prefisso, numero, tipoNEW, tipoOLD);

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

    //checkEliminaNumero(idNumero,tipo); 

    numeroDao.deleteNumeriDB(idNumero, tipo);


}

public void deleteIndirizzo(int idContatto,int idIndirizzo,int indexIndirizzo) {
    IndirizziDAO indirizzoDao = new ImplementazioneIndirizziDAO();
            indirizzoDao.deleteIndirizzoDB(idIndirizzo);
            rubrica.getContatto(idContatto).getListaIndirizzi().remove(indexIndirizzo);
} 







	
}
