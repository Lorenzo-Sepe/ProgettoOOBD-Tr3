package Model;

import java.util.ArrayList;

public class Rubrica {
	private String nome;
	private ArrayList<Contatto> contatti= new ArrayList<>();
	
	private Cassaforte cassaforte =null;
	
	public Rubrica (String nome) {
		this.setNome(nome);
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void aggiungiContatto ( Contatto c) {
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
			c.aggiungiEmail(modifica);
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
	public void creaGruppo() {
		//TODO
	}
	public void eliminaGruppo() {
		//TODO
	}
	
	public void creaCassaforte(String password) {
		if(cassaforte==null)
		cassaforte = new Cassaforte( password);
	}
	
	public void aggiungiContattoCassaforte(int id) {
		
		cassaforte.aggiungiContatto(getContatto(id));
		contatti.remove(getContatto(id));
	}

	public Cassaforte  getCassaforte() {
		return cassaforte;
	}

	
}
