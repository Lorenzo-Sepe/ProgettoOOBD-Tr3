package Controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import DAO.*;
import Model.Account;
import Model.Contatto;
import Model.EnumPrefissoNumero;
import Model.Indirizzi;
import Model.NumeriTelefonici;
import Model.Rubrica;
import ImplementazioneDAOpostgreSQL.*;

public class Controller {
	Rubrica rubrica;
	//private boolean sync =false;
	
	//metodi 
	
	
	public void  dumpDati() {
		int id=0;
		rubrica=new Rubrica("mio");
		rubrica.aggiungiContatto(new Contatto(id++,"sig","ale","tri",null));
		rubrica.aggiungiContatto(new Contatto(id++,"sig","lor","sep",null));
		rubrica.aggiungiContatto(new Contatto(id++,"sig","rai","mor",null));
		
		for(int i=0;i<id;i++) {
			for (int j=1;j<  3+1;j++) {
				String emailtmp= j+"EsimaMaiDilUser"+i+"@salcazzo.dio";
				String numero="555-"+j+rubrica.getContatto(i).getCognome()+i+i+i;
				NumeriTelefonici numeroComp=new NumeriTelefonici("tag",EnumPrefissoNumero.Italia.getPrefisso(),numero, "fisso");
			
				rubrica.getContatto(i).aggiungiEmail(emailtmp);
				//System.out.println(numeroComp.stampaNumero());
				String via = "Via Sal Cazzo numero "+j;
				String tag= "casa Numero"+j;
				rubrica.getContatto(i).aggiungiIndirizzo(new Indirizzi(i, false, via,"Napoli", i, "Italia", tag));
				
//				String nomeContatto = rubrica.getContatto(i).getNome();	
//				String indirizzoComp=rubrica.getContatto(i).getIndirizzo(j-1).stampaIndirizzo();
				//System.out.println("Indirizzo di User "+nomeContatto+": "+indirizzoComp);
				rubrica.getContatto(i).aggiungiNumero(numeroComp);
				
				//System.out.println("numero "+rubrica.getContatto(i).getNumero(0).getNumero());
				String fornitore ="servizio "+j;
				String nickname = "Itz_"+rubrica.getContatto(i).getPrefissoNome()+rubrica.getContatto(i).getCognome();
				String benvenuto="frase di benvenuto di "+rubrica.getContatto(i).getNome();
				String email=emailtmp;
				Account account =new Account(fornitore, nickname, benvenuto, email);
				rubrica.getContatto(i).aggiungiAccount(account);
			}
		}
				//leggi dal database
				
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
		
		
	
	

	
	public void dumpListaContatti() {
		RubricaDAO rubricaDao= new ImplementazioneRubricaDAO();
        ArrayList<Contatto> contattiDB = rubricaDao.selectAllDB();
		for (Contatto contatto : contattiDB) {
            
        	System.out.println("prova ripeti for "+contatto.StampaContatto());

            rubrica.aggiungiContatto(contatto);
		
      }
	}
	
	/**
	 * 
	 * @return ArrayList di Contatti della rubrica
	 */
	public ArrayList<Contatto> getListaContatti() {
		//TODO RubricaImplementazionePostgresDao
				/*if(sync ==false) then //TODO vedere come fare il sync in un modo meno barbaro 
				 * Lettura al db
				 *RubricaDao borsaDao = new RubricaImplementazionePostgresDao();
				 *RubricaDao.GetListaContatto()
				 */
		return rubrica.getListaContatti();
	}
	
public Contatto getContatto(int id) {
	return rubrica.getContatto(id);
	
}

/**
 * Funzione che aggiunge un membro nella arraylist delle rubrica 
 * @param prefisso
 * @param nome
 * @param cognome
 * @param path
 * @return 
 */
public int aggiungiContatto(String prefisso, String nome, String cognome,String path ) {
		RubricaDAO rubricadao=new ImplementazioneRubricaDAO();
		int id =rubricadao.addContattoDB(prefisso, nome, cognome, path);
		setFotoContatto(new File(path), id);
		
		rubrica.aggiungiContatto(new Contatto(id, prefisso, nome, cognome, " "));
		return id;
}

@Deprecated
public void aggiungiContatto(Contatto contatto) {
	rubrica.aggiungiContatto(contatto);
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
	public void setFotoContatto(File file, int id) {

	String pathSorgente= file.getAbsolutePath();
		 String estenzione = pathSorgente.substring(pathSorgente.lastIndexOf(".")+1);
		String pathDestiCompleto= GetRelativePath()+"User"+id+"."+estenzione;
		copiaFoto(pathSorgente, pathDestiCompleto);
		  System.out.println("Stringa con pathSorgente : \n"+pathSorgente);

		//TODO ContattoImplementazionePostgresDao
		/*Aggiungi al db
		 *ContattoDao contattoDao = new ContattoImplementazionePostgresDao();
		 *contattoDao.SetPath(contatto)
		 *
		 */



            System.out.println("Stringa con pathCompleto che viene restituito dal metodo CaricaESettaFoto: \n"+pathDestiCompleto);
            try {
    			rubrica.getContatto(id).aggiungiFoto("User"+id+"."+estenzione);
    		} catch (Exception e) {
    			e.getStackTrace();
    			System.out.println("Errore hai provato a settare una foto a un contatto non esistente");

    		}
            System.out.println("contatto "+id+" "+rubrica.getContatto(id).getNome()+" path: "+rubrica.getContatto(id).getPathFoto());

	}
	
	public  void copiaFoto(String pathSorgente, String pathDestiCompleto) {
		BufferedImage bufferFoto = null;
		String estenzione = pathSorgente.substring(pathSorgente.lastIndexOf(".")+1);
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
	 * @param Path path foto, 
	 * @param  Contatto IDcontatto
	 */
	@Deprecated
	public void setFotoContatto(String path, int id) {
		String pathDestiCompleto=null;
		
		int pxw=150;
		int pxh=150;
		//TODO ContattoImplementazionePostgresDao
		/*Aggiungi al db
		 *ContattoDao contattoDao = new ContattoImplementazionePostgresDao();
		 *contattoDao.SetPath(contatto)
		 *
		 */
		
		
 	
            try {
				pathDestiCompleto=CaricaESettaFoto(path, id,pxw, pxh );
			} catch (IOException e) {
				// TODO Auto-generated catch block
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






public ArrayList<Contatto> searchMail(String mail) {
	if (mail == null) {mail = "true";};
	ArrayList <Contatto> ListRisultati = new ArrayList <>();
	ArrayList<Integer> ListID = ImplementazioneContattoDAO.SearchMail(mail);
	// TODO Auto-generated method stub
	return ListRisultati;
}



//public ArrayList<Contatto> SearchAnagrafica(String prefisso, String nome, String cognome) {
//	// TODO Auto-generated method stub
//	if (prefisso == null) {prefisso="true";}; 
//	if (nome == null) {nome="true";}; 
//	if (cognome == null) {cognome="true";};
//	ArrayList<Integer> ListID = ImplementazioneContattoDAO.SearchAnagrafica(prefisso,nome,cognome);
//	ArrayList <Contatto> ListRisultati = new ArrayList <>();
////	ArrayList <Contatto> List = new ArrayList <>();
////	List = getListaContatti();
//	
//	for (Integer id : ListID) {
//		ListRisultati.add(Rubrica.getContatto(id));
//	}
//	return ListRisultati;
//}

}