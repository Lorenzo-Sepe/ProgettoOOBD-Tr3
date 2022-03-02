package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GUI.*;
import Model.*;
import Controller.Controller;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SpringLayout;

public class GestioneDuplicatiFrame extends JFrame {

	private JPanel contentPane;
	private JTable tableContatti;
	private JButton btnDelete;
	private JButton btnEdit;
	private JScrollPane scrollPane;
	Controller controller;
	JFrame frameChiamante;
	JFrame frame;
	ArrayList<Contatto> listaRisultati = new ArrayList<>();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GestioneDuplicatiFrame frame = new GestioneDuplicatiFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GestioneDuplicatiFrame(Controller controller, JFrame frameChiamante, ArrayList<Contatto> listaRisultati) {
		frame=this;
		
		setTitle("Gestione Duplicati");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 50, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -30, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane);
		
		
		DefaultTableModel modelloRubrica = new DefaultTableModel () {
			@Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
		}};
		
		tableContatti = new JTable(modelloRubrica);
		tableContatti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		            if(e.getClickCount()==2) {
		                //System.out.println(tableContatti.getSelectedRow());
		            	int id = (int) tableContatti.getModel().getValueAt(tableContatti.getSelectedRow(),0);
		            	VisualizzaContattoFrame newFrame = new VisualizzaContattoFrame(controller, frame, id);
		            }
		    }
		});
		ListSelectionModel listenerContattoSelezionato=tableContatti.getSelectionModel();
		tableContatti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelloRubrica.addColumn("Id");
		modelloRubrica.addColumn("prefisso");
		modelloRubrica.addColumn("nome"); 
		modelloRubrica.addColumn("cognome"); 
		
		for(int i=0;i<listaRisultati.size();i++) {
			modelloRubrica.addRow(new Object[]{
					listaRisultati.get(i).getID(),
					listaRisultati.get(i).getPrefissoNome(),
					listaRisultati.get(i).getNome(),
					listaRisultati.get(i).getCognome()
			});
		}
		tableContatti.removeColumn(tableContatti.getColumnModel().getColumn(0));
		scrollPane.setViewportView(tableContatti);
		
		JToolBar toolBar = new JToolBar();
		sl_contentPane.putConstraint(SpringLayout.NORTH, toolBar, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, toolBar, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, toolBar, 50, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, toolBar, 0, SpringLayout.EAST, contentPane);
		contentPane.add(toolBar);
		
		btnDelete = new JButton("Elimina Contatto");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO
			}
		});
		btnDelete.setIcon(new ImageIcon(GestioneDuplicatiFrame.class.getResource("/immagini/elimina.jpg")));
		toolBar.add(btnDelete);
		
		btnEdit = new JButton("Modifica Contatto");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println(RubricaTable.getSelectedRow());
            	int id = (int) tableContatti.getModel().getValueAt(tableContatti.getSelectedRow(),0);
            	ModificaContattoFrame newFrame = new ModificaContattoFrame(controller, frame, id);
			}
		});
		btnEdit.setIcon(new ImageIcon(GestioneDuplicatiFrame.class.getResource("/immagini/edit.png")));
		toolBar.add(btnEdit);
		
		JButton btnBack = new JButton("Indietro");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnBack, -25, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnBack, 535, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnBack, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnBack, 0, SpringLayout.EAST, contentPane);
		contentPane.add(btnBack);
	}
}
