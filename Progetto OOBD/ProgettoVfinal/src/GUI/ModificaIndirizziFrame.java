package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
/**
 * 
 * @author LorenzoSepe
 *
 */

public class ModificaIndirizziFrame extends JFrame {
	private JTextField textFieldVia;
	private JTextField textFieldCittà;
	private JTextField textFieldCodicePostale;
	private JTextField textFieldNazione;
	private JTable tableIndirizziMod;
	private Boolean IsPrincipale;
	static JFrame frameChiamante;
	static JFrame frame;
	static Controller controller;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificaIndirizziFrame frame = new ModificaIndirizziFrame(controller, frameChiamante);
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
	public ModificaIndirizziFrame(Controller controller, JFrame frameChiamante) {
		frame= this;
		setResizable(false);
		setTitle("Modifica Indirizzi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 455);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel labelInsertVia = new JLabel("Inserisci Via e Civico");
		springLayout.putConstraint(SpringLayout.NORTH, labelInsertVia, 35, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelInsertVia, 105, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelInsertVia, 280, SpringLayout.WEST, getContentPane());
		getContentPane().add(labelInsertVia);
		
		textFieldVia = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldVia, 30, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldVia, 290, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textFieldVia, 490, SpringLayout.WEST, getContentPane());
		textFieldVia.setEditable(true);
		textFieldVia.setText("");
		getContentPane().add(textFieldVia);
		textFieldVia.setColumns(10);
		
		JLabel labelInsertCittà = new JLabel("Inserisci Città");
		springLayout.putConstraint(SpringLayout.NORTH, labelInsertCittà, 60, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelInsertCittà, 105, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelInsertCittà, 280, SpringLayout.WEST, getContentPane());
		getContentPane().add(labelInsertCittà);
		
		textFieldCittà = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldCittà, 55, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldCittà, 290, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textFieldCittà, 490, SpringLayout.WEST, getContentPane());
		getContentPane().add(textFieldCittà);
		textFieldCittà.setColumns(10);
		
		//BOTTONI
		
		JCheckBox chckbxPrincipale = new JCheckBox("Principale");
		
		
		springLayout.putConstraint(SpringLayout.NORTH, chckbxPrincipale, 105, SpringLayout.NORTH, getContentPane());
		chckbxPrincipale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxPrincipale.isSelected()) {
					IsPrincipale = true;
					System.out.println(IsPrincipale);
				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
		            IsPrincipale = false;
		            System.out.println(IsPrincipale);
		        }
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, chckbxPrincipale, 290, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, chckbxPrincipale, 490, SpringLayout.WEST, getContentPane());
		getContentPane().add(chckbxPrincipale);
		
		
				
		JLabel labelInsertCodicePostale = new JLabel("Inserisci Codice Postale");
		springLayout.putConstraint(SpringLayout.NORTH, labelInsertCodicePostale, 85, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelInsertCodicePostale, 105, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelInsertCodicePostale, 280, SpringLayout.WEST, getContentPane());
		getContentPane().add(labelInsertCodicePostale);
		
		 textFieldCodicePostale = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH,  textFieldCodicePostale, 80, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldCodicePostale, 290, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST,  textFieldCodicePostale, 490, SpringLayout.WEST, getContentPane());
		getContentPane().add( textFieldCodicePostale);
		 textFieldCodicePostale.setColumns(10);
			
		JScrollPane scrollPaneIndirizzi = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPaneIndirizzi, 140, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPaneIndirizzi, 105, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPaneIndirizzi, 355, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPaneIndirizzi, 490, SpringLayout.WEST, getContentPane());
		getContentPane().add(scrollPaneIndirizzi);
		
		tableIndirizziMod = new JTable();
		ArrayList<String> arrayListContatti = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		ArrayList<String> arrayListContattiPrincipale = new ArrayList<>(Arrays.asList("False", "True", "False","False"));
		ArrayList<String> arrayListContattiIdentificatore = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		
		DefaultTableModel modelIndirizziMod =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
	    tableIndirizziMod = new JTable(modelIndirizziMod);
		ListSelectionModel listenerNumeriMod = tableIndirizziMod.getSelectionModel();
		tableIndirizziMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		modelIndirizziMod.addColumn("Via"); 
		modelIndirizziMod.addColumn("Città");
		modelIndirizziMod.addColumn("Codice Postale");
		modelIndirizziMod.addColumn("Nazione");
		
		// Append a row 
		for(int i=0;i<arrayListContatti.size();i++) {
			modelIndirizziMod.addRow(new Object[]{arrayListContatti.get(i), arrayListContattiCognome.get(i), arrayListContattiPrincipale.get(i), arrayListContattiIdentificatore.get(i)});
		}
		
		scrollPaneIndirizzi.setViewportView(tableIndirizziMod);
		
		JButton buttonIndietro = new JButton("Indietro");
		springLayout.putConstraint(SpringLayout.NORTH, buttonIndietro, 31, SpringLayout.SOUTH, scrollPaneIndirizzi);
		buttonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frameChiamante.setVisible(true);
				}
		});
		springLayout.putConstraint(SpringLayout.EAST, buttonIndietro, -119, SpringLayout.EAST, getContentPane());
		getContentPane().add(buttonIndietro);
		
		JButton buttonConferma = new JButton("Conferma");
		springLayout.putConstraint(SpringLayout.NORTH, buttonConferma, 0, SpringLayout.NORTH, buttonIndietro);
		springLayout.putConstraint(SpringLayout.WEST, buttonConferma, 6, SpringLayout.EAST, buttonIndietro);
		getContentPane().add(buttonConferma);
		
	}
	
	 
}
