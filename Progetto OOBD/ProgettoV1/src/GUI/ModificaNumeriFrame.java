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

public class ModificaNumeriFrame extends JFrame {
	private JTextField textFieldPrefisso;
	private JTextField textFieldNome;
	private JTextField textFieldIdentificatore;
	private JTable tableNumeriMod;
	private Boolean IsPrincipale;
	private String tipoNumero;
	JFrame frame;
	private Controller controller;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificaNumeriFrame frame = new ModificaNumeriFrame();
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
	public ModificaNumeriFrame() {
		setResizable(false);
		setTitle("Modifica Numeri");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 455);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel labelInsertPrefissoNazionale = new JLabel("Inserisci Prefisso Nazionale");
		springLayout.putConstraint(SpringLayout.NORTH, labelInsertPrefissoNazionale, 59, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelInsertPrefissoNazionale, 106, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelInsertPrefissoNazionale, 277, SpringLayout.WEST, getContentPane());
		getContentPane().add(labelInsertPrefissoNazionale);
		
		textFieldPrefisso = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldPrefisso, 56, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldPrefisso, 287, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textFieldPrefisso, 489, SpringLayout.WEST, getContentPane());
		textFieldPrefisso.setEditable(true);
		textFieldPrefisso.setText("");
		getContentPane().add(textFieldPrefisso);
		textFieldPrefisso.setColumns(10);
		
		JLabel labelInsertNumero = new JLabel("Inserisci Numero");
		springLayout.putConstraint(SpringLayout.NORTH, labelInsertNumero, 85, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelInsertNumero, 106, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelInsertNumero, 277, SpringLayout.WEST, getContentPane());
		getContentPane().add(labelInsertNumero);
		
		textFieldNome = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldNome, 82, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldNome, 287, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textFieldNome, 489, SpringLayout.WEST, getContentPane());
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);

		
		JPanel panelTipoNumero = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelTipoNumero, 108, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelTipoNumero, 287, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelTipoNumero, 177, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelTipoNumero, 489, SpringLayout.WEST, getContentPane());
		
		//BOTTONI
		
		JCheckBox chckbxPrincipale = new JCheckBox("Principale");
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
		
		
		springLayout.putConstraint(SpringLayout.NORTH, chckbxPrincipale, 214, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, chckbxPrincipale, 287, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, chckbxPrincipale, 489, SpringLayout.WEST, getContentPane());
		getContentPane().add(chckbxPrincipale);
		
		
		JRadioButton mobileRadioButton = new JRadioButton("Numero Mobile");
		mobileRadioButton.setMnemonic(KeyEvent.VK_P);
		mobileRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				tipoNumero = "Mobile";
				System.out.println(tipoNumero);
			}
		});
		
		JRadioButton fissoRadioButton = new JRadioButton("Numero Fisso");
		fissoRadioButton.setMnemonic(KeyEvent.VK_P);
		fissoRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				tipoNumero = "Fisso";
				System.out.println(tipoNumero);
			}
		});
		

		
		ButtonGroup groupTipoNumero = new ButtonGroup();
		groupTipoNumero.add(mobileRadioButton);
		groupTipoNumero.add(fissoRadioButton);
		panelTipoNumero.add(fissoRadioButton);
		panelTipoNumero.add(mobileRadioButton);
		getContentPane().add(panelTipoNumero);
		
		JLabel labelInsertIdentificatore = new JLabel("Inserisci Identificatore");
		springLayout.putConstraint(SpringLayout.NORTH, labelInsertIdentificatore, 191, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelInsertIdentificatore, 106, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelInsertIdentificatore, 277, SpringLayout.WEST, getContentPane());
		getContentPane().add(labelInsertIdentificatore);
		
		textFieldIdentificatore = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldIdentificatore, 188, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldIdentificatore, 287, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textFieldIdentificatore, 489, SpringLayout.WEST, getContentPane());
		getContentPane().add(textFieldIdentificatore);
		textFieldIdentificatore.setColumns(10);
			
		JScrollPane scrollPaneNumeri = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPaneNumeri, 243, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPaneNumeri, 106, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPaneNumeri, 377, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPaneNumeri, 489, SpringLayout.WEST, getContentPane());
		getContentPane().add(scrollPaneNumeri);
		
		tableNumeriMod = new JTable();
		ArrayList<String> arrayListContatti = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		ArrayList<String> arrayListContattiPrincipale = new ArrayList<>(Arrays.asList("False", "True", "False","False"));
		ArrayList<String> arrayListContattiIdentificatore = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		
		DefaultTableModel modelNumeriMod =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
	    tableNumeriMod = new JTable(modelNumeriMod);
		ListSelectionModel listenerNumeriMod = tableNumeriMod.getSelectionModel();
		tableNumeriMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		modelNumeriMod.addColumn("Prefisso Nazionale"); 
		modelNumeriMod.addColumn("Numero");
		modelNumeriMod.addColumn("Principale");
		modelNumeriMod.addColumn("Identificatore");
		
		// Append a row 
		for(int i=0;i<arrayListContatti.size();i++) {
			modelNumeriMod.addRow(new Object[]{arrayListContatti.get(i), arrayListContattiCognome.get(i), arrayListContattiPrincipale.get(i), arrayListContattiIdentificatore.get(i)});
		}
		
		scrollPaneNumeri.setViewportView(tableNumeriMod);
		
		JButton buttonIndietro = new JButton("Indietro");
		buttonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JFrame chiamante del frame;
				frame.setVisible(false);
				//frame chiamante frameBorsa.setVisible(true);
				}
		});
/*		public void actionPerformed(ActionEvent e) {
			JFrame frameBorsa=new NuovaBorsa(controller,frame);
			frame.setVisible(false);
			frameBorsa.setVisible(true);
			}
	});*/
		springLayout.putConstraint(SpringLayout.NORTH, buttonIndietro, 6, SpringLayout.SOUTH, scrollPaneNumeri);
		springLayout.putConstraint(SpringLayout.EAST, buttonIndietro, -119, SpringLayout.EAST, getContentPane());
		getContentPane().add(buttonIndietro);
		
		JButton buttonConferma = new JButton("Conferma");
		springLayout.putConstraint(SpringLayout.NORTH, buttonConferma, 0, SpringLayout.NORTH, buttonIndietro);
		springLayout.putConstraint(SpringLayout.WEST, buttonConferma, 6, SpringLayout.EAST, buttonIndietro);
		getContentPane().add(buttonConferma);
		
	}
	//Metodi per action listeners
	
	
	public void actionPerformed(ActionEvent e) {
	       tipoNumero = e.getActionCommand();
	    }

	 
	 
}
