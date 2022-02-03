package Controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ProvaMAin.MainGetpath;
import model.Contatto;
import model.Rubrica;

public class Controller {
	Rubrica rubrica;
	private boolean ync =false;
	
	//metodi 
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
			          pathDestinazione = GetFuturePathDestinazione()+ "FotoDiContattoID"+id; // concatenazione directory di salvataggio pi� l' ID del contatto
			          ImageIO.write(img, estenzione, new File(pathDestinazione));
			          //Modifico il Path Destinazione mettendo estenzione 
			          pathDestinazione=pathDestinazione+estenzione;
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
		String nomeFileContatto=null;
		int pxw=100;
		int pxh=100;
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
	
}
