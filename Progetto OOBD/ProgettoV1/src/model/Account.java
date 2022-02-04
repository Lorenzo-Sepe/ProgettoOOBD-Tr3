package Model;

/**
 * 
 */
public class Account {

	    private String fornitore;
	    private String nickname;
	    private String benvenuto;
	    private String mail;
	    
    

	/**
     * Default constructor
     */
    public  Account(String f, String n, String b, String m) {
    	fornitore = f;
    	nickname = n;
    	benvenuto = b;
    	mail = m;
    }

    //Setter
    
    public void setFornitore(String fornitore) {
    	this.fornitore = fornitore;
    }
    
    public void setNickname(String nickname) {
    	this.nickname = nickname;
    }
    
    public void setBenvenuto(String benvenuto) {
    	this.benvenuto = benvenuto;
    }
    
    public void setMail(String mail) {
    	this.mail = mail;
    }
    
    //Getter
    
    public String getFornitore() {
		return fornitore;
	}
    
    public String getNickname() {
		return nickname;
	}
    public String getBenvenuto() {
		return benvenuto;
	}
    public String getMail() {
		return mail;
	}
    
    /**
     * 
     */
   

    public void gestioneDuplicati() {
        // TODO implement here
    }

}
