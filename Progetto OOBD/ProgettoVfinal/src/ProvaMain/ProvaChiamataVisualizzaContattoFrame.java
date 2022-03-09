package ProvaMain;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.Controller;
import GUI.AggiungiIndririzzoModificaPanel;
import GUI.Home;
import GUI.VecchiaHome;
import GUI.VisualizzaContattoFrame;
import Model.Contatto;
import Model.Indirizzi;

public class ProvaChiamataVisualizzaContattoFrame {
	public static void main(String[] args) {
		Controller c = new Controller();
		try {
			c.dumpDati();
			JFrame home = new VecchiaHome(c);
			home.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
