package ProvaMain;

import java.util.ArrayList;

import Controller.Controller;

public class provaController {
	public static void main (String args[]) {
		Controller c = new Controller();
		c.dumpDati();
		ArrayList<String> risultato;
		risultato = c.ricercaDuplicati();
		for (String i : risultato) {
			System.out.println(i);
		}
		
		
	}
}
