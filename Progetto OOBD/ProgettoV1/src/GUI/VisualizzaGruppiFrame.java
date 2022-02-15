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
import ImplementazioneDAOpostegreSQL.*;
import Model.Gruppo;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JTable;

public class VisualizzaGruppiFrame extends JFrame {

	private JPanel contentPane;
	static JFrame frameChiamante;
	static Controller controller;
	private JList listGruppi;
	private JScrollPane scrollPaneGruppi;
	private JButton buttonCreaGruppo;
	private JTable tableGruppi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaGruppiFrame frame = new  VisualizzaGruppiFrame (controller, frameChiamante);
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
	public VisualizzaGruppiFrame(Controller controller, JFrame frameChiamante) {
		
		ArrayList<String> ListaNomiGruppo = new ArrayList <>(Arrays.asList("Alfa", "Beta", "Gamma","Delta")); 
		

		
		
		setResizable(false);
		setTitle("Visualizza gruppi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		scrollPaneGruppi = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneGruppi, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneGruppi, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneGruppi, -5, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneGruppi, -150, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPaneGruppi);
		
		JButton buttonModificaGruppo = new JButton("Modifica Gruppo");
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonModificaGruppo, 5, SpringLayout.EAST, scrollPaneGruppi);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonModificaGruppo, -10, SpringLayout.EAST, contentPane);
		contentPane.add(buttonModificaGruppo);
		
		JButton buttonEliminaGruppo = new JButton("Elimina Gruppo");
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonEliminaGruppo, 5, SpringLayout.SOUTH, buttonModificaGruppo);
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonEliminaGruppo, 5, SpringLayout.EAST, scrollPaneGruppi);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonEliminaGruppo, -10, SpringLayout.EAST, contentPane);
		contentPane.add(buttonEliminaGruppo);
		
		buttonCreaGruppo = new JButton("Crea Gruppo");
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonModificaGruppo, 5, SpringLayout.SOUTH, buttonCreaGruppo);
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonCreaGruppo, 5, SpringLayout.EAST, scrollPaneGruppi);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonCreaGruppo, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonCreaGruppo, 0, SpringLayout.NORTH, scrollPaneGruppi);
		
		 DefaultTableModel modelGruppi =  new DefaultTableModel() {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		           //all cells false
		           return false;
		        }};
			
		        modelGruppi.addColumn("Id");
		        modelGruppi.addColumn("Nome");
			
			
			// Inserimento nella tabella dei numeri del contatto
			
			for(int i=0;i< ListaNomiGruppo.size();i++) {
				modelGruppi.addRow(new Object[]{
						i,
						 ListaNomiGruppo.get(i)
				});	
				
			}
		
		tableGruppi = new JTable(modelGruppi);
		tableGruppi.removeColumn(tableGruppi.getColumnModel().getColumn(0));
		scrollPaneGruppi.setViewportView(tableGruppi);
		contentPane.add(buttonCreaGruppo);
	}

}
