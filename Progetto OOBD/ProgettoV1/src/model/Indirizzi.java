package model;


import java.util.*;

/**
 * 
 */
public class Indirizzi {

		private int id;
	   private boolean principale;
	   private String via;
	   private String città;
	   private int codicePostale;
	   private String nazione;
	   private String identificatore;
	   
//Costruttore
	   
	   public Indirizzi (int d, boolean p, String v, String c, int cp, String n, String i)
	   {
		id = d;    
		principale = p;   
		via = v;
		città=c;
		codicePostale=cp;
		nazione=n;	
		identificatore = i;
	   }
	   
//Setter	   

	   public void setID(int id) {
		   this.id = id;
	   }
	   
	public void setVia(String via) {
			this.via = via;
		}
 
	   public void setCittà(String città) {
			this.città = città;
		}

	   public void setCodicePostale(int codicePostale) {
			this.codicePostale = codicePostale;
		}
	   
	   public void setNazione(String nazione) {
			this.nazione = nazione;
		}
	   
	   public void setIdentificatore(String identificatore) {
		   this.identificatore = identificatore;
	   }
//Getter	   
	   
	   public int getID() {
		   return id;
	   }
	   
	   public String getVia() {
			return via;
		}
	   
	   public String getCittà() {
			return città;
		}
	   
	   public String getNazione() {
			return nazione;
		}
	   
	   public Integer getCodicePostale() {
		   return codicePostale;
	   }
	   
	   public String getIdentificatore() {
		   return identificatore;
	   }
	   
	   public Boolean getPrincipale() {
		   return principale;
	   }
	   
    /**
     * 
     */
    public void setPrincipale() {
        // TODO implement here
    }

}
