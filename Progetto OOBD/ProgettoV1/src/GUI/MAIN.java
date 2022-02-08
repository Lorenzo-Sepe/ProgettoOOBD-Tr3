package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class GUIMain.
 */
public class MAIN {

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller c=new Controller();
					Home window = new Home(c);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
