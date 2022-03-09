package Model;




public class Indirizzi {

	   private int id;
	   private boolean principale;
	   private String via;
	   private String città;
	   private String codicePostale;
	   private String nazione;
	   private String tag;
	   
//Costruttore
	   
	   public Indirizzi (int id, boolean principale, String via, String città, String codicePostale, String nazione, String identificatore)
	   {
		this.id = id;    
		this.principale = principale;   
		this.via = via;
		this.città=città;
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
	    * restituisce la città
	    * @return Stringa corrispondente alla città
	    */
	   public String getCittà() {
			return città;
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
		return  tag+" "+ via+" "+città+" "+codicePostale;
	}

}
