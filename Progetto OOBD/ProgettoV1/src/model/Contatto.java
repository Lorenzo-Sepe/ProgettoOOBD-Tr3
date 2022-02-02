package Model;

import java.util.ArrayList;

public class Contatto {
	private int contatto_ID;
	private String prefissoNome;
	private String nome;
	private String cognome;
	private String pathFoto;
	private ArrayList<String> ListaEmail = null;
	private ArrayList<Indirizzi> ListaIndirizzi = null;
	private ArrayList<NumeriTelefonici> ListaNumeri = null;
	private ArrayList<account> ListaAccount = null;
	
	public Contatto (String prefisso, String nome, String cognome, String pathFoto) {
		if (prefisso == null) {
			this.prefissoNome = null;
		}
		else {
			this.prefissoNome = new String (prefisso);
		}
		this.nome = new String (nome);
		this.cognome = new String (cognome);
		if (pathFoto == null) {
			this.pathFoto = null;
		}
		else {
			this.pathFoto = new String (pathFoto);
		}
	}
	
	public void setID (int id) {
		this.contatto_ID = id;
	}
	
	public int getID () {
		return contatto_ID;
	}
	
	public void setPrefissoNome (String pref) {
		this.prefissoNome = pref;
	}
	
	public String getPrefissoNome () {
		return prefissoNome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getNome () {
		return nome;
	}
	
	public void setCognome (String cognome) {
		this.cognome = cognome;
	}
	
	public String getCognome () {
		return cognome;
	}
	
	public void setPathFoto (String PF) {
		this.pathFoto = PF;
	}
	
	public String getPathFoto () {
		return pathFoto;
	}
	
	public void AggiungiIndirizzo (Indirizzi i) {
		ListaIndirizzi.add(i);
	}
	
	public boolean EliminaIndirizzo (Indirizzi i) {
		boolean riuscita;
		riuscita = ListaIndirizzi.remove(i);
		return riuscita;
	}
	
	public void AggiungiEmail (String e) {
		ListaEmail.add(e);
	}
	
	public boolean EliminaEmail (String e) {
		boolean riuscita;
		riuscita = ListaEmail.remove(e);
		return riuscita;
	}
	
	public void AggiungiNumero (NumeriTelefonici n) {
		ListaNumeri.add(n);
	}
	
	public boolean EliminaNumero (NumeriTelefonici n) {
		boolean riuscita;
		riuscita = ListaNumeri.remove(n);
		return riuscita;
	}
	
	public void AggiungiAccount (account a) {
		ListaAccount.add(a);
	}
	
	public boolean EliminaAccount (account a) {
		boolean riuscita;
		riuscita = ListaAccount.remove(a);
		return riuscita;
	}
	public String StampaContatto() {
		
		return new String(this.contatto_ID+" "+this.prefissoNome+" "+this.nome+" "+this.cognome) ;
	}
	public void aggiungiFoto(String path) {
		pathFoto= new String("Foto/fotoDiUser"+contatto_ID);
		copiaFoto(path);
		
	}
	
	
	private void copiaFoto(String path) {
		try {
		    // retrieve image
			File inputfile = new File(path);
			System.out.println("Prova dio can");
			
			if(inputfile.canRead()) {
				System.out.println("siiii");
			}
			else {
				System.out.println("Noooooo");
			}
			try {

				BufferedImage bufferFoto = ImageIO.read(new File(path));
			    File outputfile = new File("C:\\Users\\Utente\\Documents\\Esame OO BD\\OO\\Progetto\\src\\Foto\\fotoDiUser1.jpg");
			   // outputfile.createNewFile();
			    ImageIO.write(bufferFoto, "jpg", outputfile);
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
	
}

