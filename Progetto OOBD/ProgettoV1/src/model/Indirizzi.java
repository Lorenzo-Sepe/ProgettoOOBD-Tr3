package model;


import java.util.*;

/**
 * 
 */
public class Indirizzi {

	  private boolean principale;
	   private String via;
	   private String città;
	   private int codicePostale;
	   private String nazione;
	   private String identificatore;
	   
//Costruttore
	   
	   public Indirizzi (boolean p, String v, String c, int cp, String n, String i)
	   {
		principale = p;   
		via = v;
		città=c;
		codicePostale=cp;
		nazione=n;	
		identificatore = i;
	   }
	   
//Setter	   

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
