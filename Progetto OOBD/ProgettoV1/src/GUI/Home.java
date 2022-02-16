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
import Model.*;
import GUI.*;

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
import javax.swing.SpringLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTable RubricaTable;
	Rubrica rubrica;
	Controller c;
	JFrame frame;


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
		frame=this;
		c.dumpDati();
		setTitle("Rubrica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		
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
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JToolBar toolBar = new JToolBar();
		sl_contentPane.putConstraint(SpringLayout.NORTH, toolBar, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, toolBar, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, toolBar, 35, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, toolBar, 450, SpringLayout.WEST, contentPane);
		contentPane.add(toolBar);
		
		JButton btnAggiungiContatto = new JButton("Aggiungi contatto");
		btnAggiungiContatto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame aggiungiContatto = new AggiungiContatto (c,frame);
				frame.setVisible(false);
				aggiungiContatto.setVisible(true);
			}
		});
		btnAggiungiContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/aggiungi.jpg")));
		toolBar.add(btnAggiungiContatto);
		
		JButton btnEliminaContatto = new JButton("Elimina Contatto");
		btnEliminaContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/elimina.jpg")));
		toolBar.add(btnEliminaContatto);
		
		JButton btnCercaContatto = new JButton("Cerca Contatto");
		btnCercaContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/ricerca.png")));
		toolBar.add(btnCercaContatto);
		
		JScrollPane scrollPaneRubrica = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneRubrica, 45, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneRubrica, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneRubrica, -15, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneRubrica, -15, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPaneRubrica);
		
		//Rubrica = new JTable();
		DefaultTableModel modelloRubrica = new DefaultTableModel () {
			@Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
		}};
		
		RubricaTable = new JTable(modelloRubrica);
		RubricaTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		            if(e.getClickCount()==2) {
		                System.out.println(RubricaTable.getSelectedRow());
		            	int id = (int) RubricaTable.getModel().getValueAt(RubricaTable.getSelectedRow(),0);
		            	VisualizzaContattoFrame newFrame = new VisualizzaContattoFrame(c, frame, id);
		            }
		    }
		});
		ListSelectionModel listenerContattoSelezionato=RubricaTable.getSelectionModel();
		RubricaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelloRubrica.addColumn("Id");
		modelloRubrica.addColumn("prefisso");
		modelloRubrica.addColumn("nome"); 
		modelloRubrica.addColumn("cognome"); 
		
		ArrayList<Contatto>  arrayListContatti = c.getListaContatti();
		
		for(int i=0;i<arrayListContatti.size();i++) {
			modelloRubrica.addRow(new Object[]{
					arrayListContatti.get(i).getID(),
					arrayListContatti.get(i).getPrefissoNome(),
					arrayListContatti.get(i).getNome(),
					arrayListContatti.get(i).getCognome()
			});
		}
		RubricaTable.removeColumn(RubricaTable.getColumnModel().getColumn(0));
		scrollPaneRubrica.setViewportView(RubricaTable);
	}
}