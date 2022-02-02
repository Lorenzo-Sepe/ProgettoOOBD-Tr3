package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Rubrica {
	private String nome;
	private ArrayList<Contatto> contatti = null;
	
	public Rubrica (String nome) {
		this.nome = nome;
	}
	
	public void aggiungiContatto (Contatto c) {
		contatti.add(c);
	}
	
	public boolean eliminaContatto (Contatto c) {
		boolean riuscita;
		riuscita = contatti.remove(c);
		return riuscita;
	}
	public void modificaContatto (Contatto c, int op, String modifica) {
		switch (op)
		{
		case 1:
			c.setPrefissoNome(modifica);
		break;
		case 2:
			c.setNome(modifica);
		break;
		case 3:
			c.setCognome(modifica);
		break;
		case 4:
			c.setPathFoto(modifica);
		break;
		case 5:
			c.AggiungiEmail(modifica);
		}
	}
	public ArrayList<Contatto> getListaContatti(){
		return contatti;
		
	}
	/**
	 * 
	 * @param id
	 * @return contatto associato al id inserito
	 */
	public Contatto getContatto(int id) {
		for (Contatto contatto : contatti) {
			if(contatto.getID()==id)
				return contatto;
		}
		return null;
	}
}
