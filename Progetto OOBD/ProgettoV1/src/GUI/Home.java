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
import javax.swing.JOptionPane;
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
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTable RubricaTable;
	Rubrica rubrica;
	Controller c;
	JFrame frame;
	private JButton btnModificaContatto;
	private JMenuItem mntmContattiDuplicati;
	private JMenuItem mntmAccountDuplicati;


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
		try {
			c.dumpListaContatti();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
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
		
		JMenu mnGestioneDuplicati = new JMenu("Gestione Duplicati");
		menuBar.add(mnGestioneDuplicati);
		
		mntmContattiDuplicati = new JMenuItem("Cerca Contatti Duplicati");
		mntmContattiDuplicati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ArrayList<String>listaRisultati = c.verificaMailDuplicate();				
					JFrame GestioneDuplicatiFrame = new gestioneDuplicatiContattiFrame(c,frame,listaRisultati);
					frame.setVisible(false);
					GestioneDuplicatiFrame.setVisible(true);
			}
		});
		
		mnGestioneDuplicati.add(mntmContattiDuplicati);
		
		mntmAccountDuplicati = new JMenuItem("Cerca Account Duplicati");
		mntmAccountDuplicati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
//				ArrayList<Contatto>listaRisultati = c.verificaDuplicatiAccount();
//				JFrame GestioneDuplicatiFrame = new GestioneDuplicatiFrame(c,frame,listaRisultati);
//				frame.setVisible(false);
//				GestioneDuplicatiFrame.setVisible(true);
			}
		});
		mnGestioneDuplicati.add(mntmAccountDuplicati);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		//Rubrica = new JTable();
				DefaultTableModel modelloRubrica = new DefaultTableModel () {
					@Override
			        public boolean isCellEditable(int row, int column) {
			           //all cells false
			           return false;
				}};
		
		JToolBar toolBar = new JToolBar();
		sl_contentPane.putConstraint(SpringLayout.NORTH, toolBar, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, toolBar, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, toolBar, 35, SpringLayout.NORTH, contentPane);
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
		
		btnModificaContatto = new JButton("Modifica Contatto");
		btnModificaContatto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println(RubricaTable.getSelectedRow());
            	int id = (int) RubricaTable.getModel().getValueAt(RubricaTable.getSelectedRow(),0);
            	ModificaContattoFrame newFrame = new ModificaContattoFrame(c, frame, id);
			}
		});
		btnModificaContatto.setIcon(new ImageIcon(Home.class.getResource("/immagini/edit.png")));
		toolBar.add(btnModificaContatto);
		
		JButton btnEliminaContatto = new JButton("Elimina Contatto");
		btnEliminaContatto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = RubricaTable.getSelectedRow();
				if (row>0) {
					if (JOptionPane.showConfirmDialog(null, "Sicuro di voler cancellare il contatto?", "WARNING", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						int id = (int) modelloRubrica.getValueAt(row, 0);
						try {
							c.eliminaContattoDB(id);
							modelloRubrica.removeRow(row);
							JOptionPane.showMessageDialog(null, "Contatto eliminato con successo");
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace(); 
						}
					}
					
				}
			}
		});
		btnEliminaContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/elimina.jpg")));
		toolBar.add(btnEliminaContatto);
		
		JButton btnCercaContatto = new JButton("Cerca Contatto");
		btnCercaContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/ricerca.png")));
		toolBar.add(btnCercaContatto);
		
		JScrollPane scrollPaneRubrica = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.EAST, toolBar, 0, SpringLayout.EAST, scrollPaneRubrica);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneRubrica, 45, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneRubrica, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneRubrica, -15, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneRubrica, -15, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPaneRubrica);
		
		
		
		RubricaTable = new JTable(modelloRubrica);
		RubricaTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		            if(e.getClickCount()==2) {
		               // System.out.println(RubricaTable.getSelectedRow());
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