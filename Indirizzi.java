package Model;




public class Indirizzi {

	   private int id;
	   private boolean principale;
	   private String via;
	   private String citta;
	   private String codicePostale;
	   private String nazione;
	   private String tag;
	   
//Costruttore
	   
	   public Indirizzi (int id, boolean principale, String via, String citta, String codicePostale, String nazione, String identificatore)
	   {
		this.id = id;    
		this.principale = principale;   
		this.via = via;
		this.citta=citta;
		this.codicePostale=codicePostale; 
		this.nazione=nazione;	
		this.tag = identificatore;
	   }
	   

//Getter	   
	   
	   /**
	    * restituisce l'identificatore univoco dell'indirizzo
	    * @return identificatore dell'indirizzo
	    */
	   public int getID() {
		   return id;
	   }
	   
	   /**
	    * restituisce la via
	    * @return Stringa corrispondente alla via
	    */
	   public String getVia() {
			return via;
		}
	   
	   /**
	    * restituisce la citta
	    * @return Stringa corrispondente alla citta
	    */
	   public String getCitta() {
			return citta;
		}
	   
	   /**
	    * restituisce la nazione
	    * @return Stringa corrispondente alla nazione
	    */
	   public String getNazione() {
			return nazione;
		}
	   
	   /**
	    * restituisce il codice postale
	    * @return Stringa corrispondente al codice postale
	    */
	   public String getCodicePostale() {
		   return codicePostale;
	   }
	   
	   /**
	    * restituisce il tag del numero
	    * @return Stringa corrispondente al tag
	    */
	   public String getTag() {
		   return tag;
	   }
	   
	   /**
	    * 
	    * @return valore booleano che è uguale a true se l'indirizzo è principale, false altrimenti
	    */
	   public Boolean getPrincipale() {
		   return principale;
	   }
	   
    /**
     * 
     */

	public String stampaIndirizzo() {
		return  tag+" "+ via+" "+citta+" "+codicePostale;
	}


	/**
	 * imposta l'indirizzo principale
	 * @param b valore booleano che imposta il valore
	 */
	public void setPrincipale(boolean b) {
		principale = b;		
	}
	
	/**
     * 

     * @param principale String
     * @param via String
     * @param citta String
     * @param codicePostale String 
     * @param nazione
     */
    public void setAll(boolean principale, String via, String citta, String codicePostale,
            String nazione,String tag) {

        this.principale = principale;
        this.via = via;
        this.citta=citta;
        this.codicePostale=codicePostale; 
        this.nazione=nazione;
        this.tag = tag;

    }

}
