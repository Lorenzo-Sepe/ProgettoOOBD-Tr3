package model;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * 
 */
 public class Cassaforte extends Gruppo {
	
	private String password;

    /**
     * Default constructor
     */
    public Cassaforte(Gruppo gruppo, String pass) {
    	super(gruppo.getNomeGruppo());
    	password=CifraturaPassword(pass);
    	if(!gruppo.getListaGruppo().isEmpty()) {
    		membriGruppo =new ArrayList<Contatto>(gruppo.getListaGruppo());
    	}else {
    		membriGruppo =new ArrayList<Contatto>();
    	}
    }
    

    public String getPassword(){
    	return password;
    }
    //questo script è per fare dei test
    public void setPassword(String pass) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	byte [] salt= "E*.^%j=+Z6-Ed9c,".getBytes();
    	KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt,1,16*8);
    	SecretKeyFactory f =SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    	password=f.generateSecret(spec).getEncoded().toString();
    	}
    
    private String CifraturaPassword(String pass) {
    	// TODO gestire veramente la cifrattura
    	byte [] salt= "E*.^%j=+Z6-Ed9c,".getBytes();
    	KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt,1,16*8);
    	
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			return f.generateSecret(spec).getEncoded().toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	  return pass;
    }
    
    public ArrayList<Contatto> getListaGruppo(String pass){
		if(CifraturaPassword(pass).compareTo(password)==0) {
			System.out.println("yess ti do il gruppo");
			return membriGruppo;
		}else {
			System.out.println("Noooo password errata, niente gruppo >-<");
			return membriGruppo;
		}
	}
    public static byte[] getEncryptedPassword(String password,byte[] salt,int iterations,int derivedKeyLength) throws NoSuchAlgorithmException, InvalidKeySpecException 
	{
		KeySpec spec = new PBEKeySpec(password.toCharArray(),salt,iterations,derivedKeyLength * 8);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		return f.generateSecret(spec).getEncoded();
	}
}
