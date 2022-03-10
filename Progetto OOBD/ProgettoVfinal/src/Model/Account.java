package Model;

/**
 * 
 */
public class Account {

		private int idAssociato;
		private int id;
	 	private String fornitore;
	    private String nickname;
	    private String benvenuto;
	    private String mail;
	    
    

	/**
     * Default constructor
     */
    public  Account(String fornitore, String nickname, String benvenuto, String mail) {
    	this.fornitore = fornitore;
    	this.nickname = nickname; 
    	this.benvenuto = benvenuto;
    	this.mail = mail;
    }
    
    public  Account(int id,String fornitore, String nickname, String benvenuto, String mail) {
    	this.id = id;
    	this.fornitore = fornitore;
    	this.nickname = nickname; 
    	this.benvenuto = benvenuto;
    	this.mail = mail;
    }
    
    public  Account(int id,int idAssociato,String fornitore, String nickname, String benvenuto, String mail) {
    	this.id = id;
    	this.idAssociato=idAssociato;
    	this.fornitore = fornitore;
    	this.nickname = nickname; 
    	this.benvenuto = benvenuto;
    	this.mail = mail;
    }
    
    //Getter
    
    /**
     * restituisce il fornitore dell'account
     * @return Stringa corrispondente al fornitore
     */
    public String getFornitore() {
		return fornitore;
	}
    
    /**
     * restituisce il nickname dell'account
     * @return Stringa corrispondente al nickname
     */
    public String getNickname() {
		return nickname;
	}
    
    /**
     * restituisce la frase di benvenuto dell'account
     * @return Stringa corrispondente alla frase di benvenuto
     */
    public String getBenvenuto() {
		return benvenuto;
	}
    
    /**
     * resituisce la mail associata all'account
     * @return Stringa corrispondente alla mail
     */
    public String getMail() {
		return mail;
	}
    
    /**
     * restituisce l'identificatore univoco dell'account
     * @return identificatore dell'account
     */
    public int getID() {
    	return id;
    }
    
    /**
     * getter che restituisce l'identificatore del accoount
     * @return id del contatto associato a questo account
     */
    public int getAssociato() {
    	return idAssociato;
    }
}