package Controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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
	 * Setta foto di un contatto
	 *
	 * @param Path path foto, 
	 * @param  Contatto IDcontatto
	 */
	public void setFotoContatto(String path, int id) {
		
		int pxw=100;
		int pxh=100;
		//TODO ContattoImplementazionePostgresDao
		/*Aggiungi al db
		 *ContattoDao contattoDao = new ContattoImplementazionePostgresDao();
		 *contattoDao.SetPath(contatto)
		 *
		 */
		
		try {
			//rubrica.getContatto(id).aggiungiFoto(path);
			String pathdest=new String("C:\\Users\\Utente\\Documents\\Esame OO BD\\OO\\Progetto\\srcFoto\\FotoUserDiPath.png");
			CaricaESettaFoto(path, pathdest, pxw, pxh);
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Errore hai provato a settare una foto a un contatto non esistente");
		
		}
		
	}
	/**
	 * 
	 * @param pathSorgente
	 * @param pathDest
	 * @param w
	 * @param h
	 * @throws IOException
	 */
	private static void CaricaESettaFoto(String pathSorgente, String pathDest, int w, int h)
			 throws IOException 
			 {
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
			          String name = pathDest.substring(pathDest.lastIndexOf(".") + 1);
			     
			          // writes to the output file
			          ImageIO.write(img, name, new File(pathDest));
			     
				} catch (Exception e) {
					e.printStackTrace();
				}
			      
			     // BufferedImage inputImage = ImageIO.read(f);
			 
			      }

	
}
