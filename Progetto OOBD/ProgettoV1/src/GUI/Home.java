package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Contatto;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTable Rubrica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			Controller c = new Controller();
			
			public void run() {
				try {
					Home frame = new Home(c);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home(Controller c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGestrioneGruppi = new JMenu("Gestione Gruppi");
		menuBar.add(mnGestrioneGruppi);
		
		JMenuItem mntmCreaGruppo = new JMenuItem("Crea Gruppo");
		mnGestrioneGruppi.add(mntmCreaGruppo);
		
		JMenuItem mntmModificaGruppo = new JMenuItem("Visualizza Gruppi");
		mnGestrioneGruppi.add(mntmModificaGruppo);
		
		JMenuItem mntmEliminaGruppo = new JMenuItem("Elimina Gruppo");
		mnGestrioneGruppi.add(mntmEliminaGruppo);
		
		JMenu mnGestioneCassaforti = new JMenu("Gestione Cassaforti");
		menuBar.add(mnGestioneCassaforti);
		
		JMenuItem mntmCreaCassaforte = new JMenuItem("Crea Cassaforte");
		mnGestioneCassaforti.add(mntmCreaCassaforte);
		
		JMenuItem mntmVisualizzaCassaforte = new JMenuItem("Visualizza Cassaforti");
		mnGestioneCassaforti.add(mntmVisualizzaCassaforte);
		
		JMenuItem mntmEliminaCassaforte = new JMenuItem("Elimina Cassaforte");
		mnGestioneCassaforti.add(mntmEliminaCassaforte);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 0, 438, 33);
		contentPane.add(toolBar);
		
		JButton btnAggiungiContatto = new JButton("Aggiungi contatto");
		btnAggiungiContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/aggiungi.jpg")));
		toolBar.add(btnAggiungiContatto);
		
		JButton btnEliminaContatto = new JButton("Elimina Contatto");
		btnEliminaContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/elimina.jpg")));
		toolBar.add(btnEliminaContatto);
		
		JButton btnCercaContatto = new JButton("Cerca Contatto");
		btnCercaContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/ricerca.png")));
		toolBar.add(btnCercaContatto);
		
		JScrollPane scrollPaneRubrica = new JScrollPane();
		scrollPaneRubrica.setBounds(20, 44, 404, 184);
		contentPane.add(scrollPaneRubrica);
		
		//Rubrica = new JTable();
		DefaultTableModel modelloRubrica = new DefaultTableModel () {
			@Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
		}};
		
		Rubrica = new JTable(modelloRubrica);
		ListSelectionModel listenerContattoSelezionato=Rubrica.getSelectionModel();
		Rubrica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelloRubrica.addColumn("prefisso");
		modelloRubrica.addColumn("nome"); 
		modelloRubrica.addColumn("cognome"); 
		
		ArrayList<Contatto>  arrayListContatti = c.getListaContatti();
		
		for(int i=0;i<arrayListContatti.size();i++) {
			modelloRubrica.addRow(new Object[]{
					arrayListContatti.get(i).getPrefissoNome(),
					arrayListContatti.get(i).getNome(),
					arrayListContatti.get(i).getCognome()
			});
		}
		
		scrollPaneRubrica.setViewportView(Rubrica);
	}
}
