package Controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.plaf.multi.MultiPopupMenuUI;

import DAO.ContattoDAO;
import DAO.RubricaDAO;
import ImplementazioneDAOpostgreSQL.ImplementazioneContattoDAO;
import ImplementazioneDAOpostgreSQL.ImplementazioneRubricaDAO;
import Model.Account;
import Model.Contatto;
import Model.Indirizzi;
import Model.NumeriTelefonici;
import Model.Rubrica;

public class Controller {
	Rubrica rubrica;
	@SuppressWarnings("unused")
	private boolean ync =false;
	
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
				NumeriTelefonici numeroComp=new NumeriTelefonici("tag","+39",numero, "fisso");
			
				rubrica.getContatto(i).aggiungiEmail(emailtmp);
				//System.out.println(numeroComp.stampaNumero());
				String via = "Via Sal Cazzo numero "+j;
				String tag= "casa Numero"+j;
				rubrica.getContatto(i).aggiungiIndirizzo(new Indirizzi(i, false, via,"Napoli", i, "Italia", tag));
				
				String nomeContatto = rubrica.getContatto(i).getNome();	
				String indirizzoComp=rubrica.getContatto(i).getIndirizzo(j-1).stampaIndirizzo();
				//System.out.println("Indirizzo di User "+nomeContatto+": "+indirizzoComp);
				rubrica.getContatto(i).aggiungiNumero(numeroComp);
				
				//System.out.println("numero "+rubrica.getContatto(i).getNumero(0).getNumero());
				String fornitore ="servizio "+j;
				String nickname = "Itz_"+rubrica.getContatto(i).getPrefissoNome()+rubrica.getContatto(i).getCognome();
				String benvenuto="frase di benvenuto di "+rubrica.getContatto(i).getNome();
				String email=emailtmp;
				Account account =new Account(fornitore, nickname, benvenuto, email);
				rubrica.getContatto(i).aggiungiAccount(account);
				
				//Leggi dal Database
				RubricaDAO rubricaDao= new ImplementazioneRubricaDAO();
				ContattoDAO contattoDao = new ImplementazioneContattoDAO();
                ArrayList<Contatto> contattiDB = rubricaDao.selectAllDB();
                ArrayList<NumeriTelefonici> numeriDB;
                ArrayList<Indirizzi> indirizziDB;
                ArrayList<Account> accountDB;
                for (Contatto contatto : contattiDB) {
                    numeriDB = contattoDao.getListaNumeri(contatto.getID());
                    indirizziDB = contattoDao.getListaIndirizzi(contatto.getID());
                    accountDB = contattoDao.getListaAccount(contatto.getID());
                    contatto.aggiungiNumero(numeriDB);
                    contatto.aggiungiIndirizzo(indirizziDB);
                    contatto.aggiungiAccount(accountDB);
                    rubrica.aggiungiContatto(contatto);
				
                }
			}
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
public void aggiungiContatto(Contatto contatto) {
		//TODO ContattoImplementazionePostgresDao
		/*Aggiungi al db
		 *ContattoDao contattoDao = new ContattoImplementazionePostgresDao();
		 *contattoDao.aggiungiContatto(contatto)
		 */
		rubrica.aggiungiContatto(contatto);
	}
	/**
	 * 
	 * @param id 
	 * @return Il percorso completo di dove salvare la foto profilo
	 */
	private String GetFuturePathDestinazione() {
		String pathDestIniziale = null;
		try {
			pathDestIniziale =Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String pathSenzaBin = pathDestIniziale.substring(0,pathDestIniziale.length()-4);
         String pathDestiCompSenzaNomefile=pathSenzaBin+"src/Immagini/";	
         
         return pathDestiCompSenzaNomefile;
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
	
	private String CaricaESettaFoto(String pathSorgente, int id, int w, int h)
			 throws IOException 
			 {
				String pathDestinazione=null;
			      // leggi file
			      File f = new File(pathSorgente);
			      
			      try {
			    	  BufferedImage inputImage = ImageIO.read(f);
			    	// creates the output image
			          BufferedImage img = new BufferedImage(w, h, inputImage.getType());
			     
			          // balance the input image to the output image
			          Graphics2D g = img.createGraphics();
			          g.drawImage(inputImage, 0, 0, w, h, null);
			          g.dispose();
			     
			          // extract the extension of the output file
			          String name = pathSorgente.substring(pathSorgente.lastIndexOf("\\")+1);
			          
			          // writes to the output file
			          String estenzione = name.substring(name.lastIndexOf(".")+1);
			          pathDestinazione = GetFuturePathDestinazione()+ "User"+id+"."+estenzione; // concatenazione directory di salvataggio pi� l' ID del contatto
			          System.out.println("pathDestinazione: "+pathDestinazione+"\n estensione: "+estenzione);
			          ImageIO.write(img, estenzione, new File(pathDestinazione));
			          //Modifico il Path Destinazione mettendo estenzione 
			      
				} catch (Exception e) {
					e.printStackTrace();
				}
			      
			     // BufferedImage inputImage = ImageIO.read(f);
			 return pathDestinazione;

			 }
	
	/**
	 * Setta foto di un contatto
	 *
	 * @param Path path foto, 
	 * @param  Contatto IDcontatto
	 */
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
            
            
            try {
    			rubrica.getContatto(id).aggiungiFoto(pathDestiCompleto);
    		} catch (Exception e) {
    			e.getStackTrace();
    			System.out.println("Errore hai provato a settare una foto a un contatto non esistente");
    		
    		}
		
		
	}
	
	public String getPathContatto(int id) {
		String path=rubrica.getContatto(id).getPathFoto();
	
		if(path==" ") {
			
			return GetFuturePathDestinazione()+"NoImage.jpg";
		}else {
			return path;
		}
	}
	
	
	
	
	
}
