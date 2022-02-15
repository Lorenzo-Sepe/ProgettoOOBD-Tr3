package GUI;

import Model.*;
import Controller.Controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreaGruppoFrame extends JFrame {

	private JPanel contentPane;
	private JTable ContattiTable;
	private JTable GruppoTable;
	private JTextField textFieldGruppo;
	private static Controller controller;
	private static  JFrame frameChiamante;
	private ArrayList <String> membriGruppo = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreaGruppoFrame frame = new CreaGruppoFrame(controller, frameChiamante);
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
	public CreaGruppoFrame(Controller controller,  JFrame frameChiamante) {
		setTitle("Crea Gruppo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNomeGruppo = new JLabel("Nome Gruppo");
		labelNomeGruppo.setBounds(50, 20, 65, 25);
		contentPane.add(labelNomeGruppo);
		
		JScrollPane scrollPaneRubrica = new JScrollPane();
		scrollPaneRubrica.setBounds(15, 85, 390, 190);
		contentPane.add(scrollPaneRubrica);
		
		//Contatti = new JTable();
		DefaultTableModel modelloContatti =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		ContattiTable = new JTable(modelloContatti);
		ListSelectionModel listenerContattoSelezionato=ContattiTable.getSelectionModel();
		ContattiTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelloContatti.addColumn("Id");
		modelloContatti.addColumn("prefisso");
		modelloContatti.addColumn("nome"); 
		modelloContatti.addColumn("cognome"); 
		
		ArrayList <String>arrayListContattiID = new ArrayList<>(Arrays.asList("1","2","3","4") );
		ArrayList<String> arrayListContattiNome = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		ArrayList<String> arrayListContattiPrefisso = new ArrayList<>(Arrays.asList("Isabel","Cavaliere","Trincalex","Amante"));
		
		// Append a row 
		for(int i=0;i<arrayListContattiNome.size();i++) {
			modelloContatti.addRow(new Object[]{arrayListContattiID.get(i), arrayListContattiPrefisso.get(i), arrayListContattiNome.get(i), arrayListContattiCognome.get(i)});
			ContattiTable.removeColumn(ContattiTable.getColumnModel().getColumn(0));
		}
		scrollPaneRubrica.setViewportView(ContattiTable);
		
		JScrollPane scrollPaneGruppo = new JScrollPane();
		scrollPaneGruppo.setBounds(530, 85, 390, 190);
		contentPane.add(scrollPaneGruppo);
		
		//Gruppo = new JTable();
		DefaultTableModel modelloGruppo = new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
	    GruppoTable = new JTable(modelloGruppo);
		ListSelectionModel listenerGruppoSelezionato = GruppoTable.getSelectionModel();
		GruppoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelloGruppo.addColumn("prefisso");
		modelloGruppo.addColumn("nome"); 
		modelloGruppo.addColumn("cognome"); 
		

		scrollPaneGruppo.setViewportView(GruppoTable);
		
		JButton buttonAggiungi = new JButton("Aggiungi ->");
		buttonAggiungi.setBounds(410, 125, 115, 25);
		buttonAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				if (!listenerContattoSelezionato.isSelectionEmpty()) {
					row = ContattiTable.getSelectedRow();
					String prefisso = (String) ContattiTable.getValueAt(row, 0);
					String nome = (String) ContattiTable.getValueAt(row, 1);
					String cognome = (String) ContattiTable.getValueAt(row, 2);
					DefaultTableModel contattiModel = (DefaultTableModel) ContattiTable.getModel();
					contattiModel.removeRow(row);
					modelloGruppo.addRow(new Object[]{prefisso, nome, cognome});
					String id = (String) ContattiTable.getModel().getValueAt(row,0); 
					membriGruppo.add(id);
					}
			}
		});
		contentPane.add(buttonAggiungi);
		
		JButton buttonElimina = new JButton("<- Elimina");
		buttonElimina.setBounds(410, 155, 115, 25);
		buttonElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonElimina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				if (!listenerGruppoSelezionato.isSelectionEmpty()) {
					row = GruppoTable.getSelectedRow();
					String prefisso = (String) GruppoTable.getValueAt(row, 0);
					String nome = (String) GruppoTable.getValueAt(row, 1);
					String cognome = (String) GruppoTable.getValueAt(row, 2);
					DefaultTableModel gruppoModel = (DefaultTableModel) GruppoTable.getModel();
					gruppoModel.removeRow(row);
					String id = (String) GruppoTable.getModel().getValueAt(row,0); 
					membriGruppo.remove(id);
					modelloContatti.addRow(new Object[]{prefisso, nome, cognome});
				}
			}
		});
		contentPane.add(buttonElimina);
		
		JButton buttonSalva = new JButton("Salva");
		buttonSalva.setBounds(690, 325, 90, -45);
		contentPane.add(buttonSalva);
		
		JButton buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.setBounds(735, 305, 90, 23);
		contentPane.add(buttonAnnulla);
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.setBounds(835, 305, 90, 23);
		contentPane.add(btnSalva);
		
		textFieldGruppo = new JTextField();
		textFieldGruppo.setText("");
		textFieldGruppo.setBounds(175, 25, 230, 20);
		contentPane.add(textFieldGruppo);
		textFieldGruppo.setColumns(10);
	}
}