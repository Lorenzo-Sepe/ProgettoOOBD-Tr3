package Model;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 */
 public class Cassaforte{

	private String password;
	private ArrayList<Contatto> contatti =null;
    /**
     * Default constructor
     */
    public Cassaforte(String pass) {
    	password=pass;

    }


    public String getPassword(){
    	return password;
    }
    public ArrayList<Contatto> getListaGruppo(){
    	return contatti;
    }
    public void aggiungiContatto (Contatto i) {
    	contatti.add(i);
	}
	public void aggiungiContatto(ArrayList<Contatto>arraylist){
		if(contatti.isEmpty()) {
			contatti=arraylist;
		}else {
			contatti.addAll(arraylist);
		}
	}
    //questo script è per fare dei test
    public void setPassword(String pass) {
//    	byte [] salt= "E*.^%j=+Z6-Ed9c,".getBytes();
//    	KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt,1,16*8);
//    	SecretKeyFactory f =SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//    	password=f.generateSecret(spec).getEncoded().toString();
    	password=pass;
    	}

    public  String CifraturaPassword(String pass) {
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

//    public ArrayList<Contatto> getListaGruppo(String pass){
//		if(pass.compareTo(password)==0) {
//			System.out.println("yess ti do il gruppo");
//			return contatti;
//		}else {
//			System.out.println("Noooo password errata, niente gruppo >-<");
//			return contatti;
//		}
//	}
    
    public byte[] getEncryptedPassword(String password,byte[] salt,int iterations,int derivedKeyLength) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		KeySpec spec = new PBEKeySpec(password.toCharArray(),salt,iterations,derivedKeyLength * 8);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		return f.generateSecret(spec).getEncoded();
	}
}
