package ProvaMAin;

import javax.swing.JFrame;

import Controller.Controller;
import GUI.VisualizzaContattoFrame;
import model.Contatto;

public class ProvaChiamataVisualizzaContattoFrame {
	public static void main(String[] args) {
		JFrame Frame=new JFrame();
		Controller c= new Controller();
		int id=0;
		c.dumpDati();
		JFrame frameVisualizzaContatto= new VisualizzaContattoFrame(c, Frame, id);
		frameVisualizzaContatto.setVisible(true);
		
	}
}
