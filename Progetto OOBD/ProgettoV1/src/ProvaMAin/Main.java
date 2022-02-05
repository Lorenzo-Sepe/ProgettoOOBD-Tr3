package ProvaMAin;

import model.Contatto;

public class Main {
	public static void main(String[] args) {
		String path ="C:\\Users\\Utente\\Pictures\\Camera\\AProva.jpg";
		Contatto c =new Contatto(1,"", "Raimondo", "Morosini");
		c.aggiungiFoto(path);
		System.out.println("Dio CAN");
	}
}
