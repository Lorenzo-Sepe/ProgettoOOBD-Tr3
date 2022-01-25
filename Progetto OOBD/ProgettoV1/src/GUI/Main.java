package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;



// TODO: Auto-generated Javadoc
/**
 * The Class GUIMain.
 */
public class Main {

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Home window = new Home(c);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
