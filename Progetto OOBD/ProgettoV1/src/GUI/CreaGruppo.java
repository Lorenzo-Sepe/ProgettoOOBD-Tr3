package GUI;

import Model.*;
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

public class CreaGruppo extends JFrame {

	private JPanel contentPane;
	private JTextField nomeGruppo;
	private JTable Contatti;
	private JTable Gruppo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreaGruppo frame = new CreaGruppo();
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
	public CreaGruppo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Nome Gruppo");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 11, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 37, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, 35, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, 103, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		nomeGruppo = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, nomeGruppo, 13, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, nomeGruppo, 166, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, nomeGruppo, 393, SpringLayout.WEST, contentPane);
		contentPane.add(nomeGruppo);
		nomeGruppo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 46, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 187, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, 155, SpringLayout.WEST, contentPane);
		contentPane.add(scrollPane);
		
		//Contatti = new JTable();
		DefaultTableModel modelloContatti =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		Contatti = new JTable(modelloContatti);
		ListSelectionModel listenerContattoSelezionato=Contatti.getSelectionModel();
		Contatti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelloContatti.addColumn("prefisso");
		modelloContatti.addColumn("nome"); 
		modelloContatti.addColumn("cognome"); 
		
		ArrayList<String> arrayListContattiNome = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		ArrayList<String> arrayListContattiPrefisso = new ArrayList<>(Arrays.asList("Isabel","Cavaliere","Trincalex","Amante"));
		
		// Append a row 
		for(int i=0;i<arrayListContattiNome.size();i++) {
			modelloContatti.addRow(new Object[]{arrayListContattiPrefisso.get(i), arrayListContattiNome.get(i), arrayListContattiCognome.get(i)});
			
		}
		scrollPane.setViewportView(Contatti);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane_1, 46, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane_1, 265, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane_1, 187, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane_1, 410, SpringLayout.WEST, contentPane);
		contentPane.add(scrollPane_1);
		
		Gruppo = new JTable();
		scrollPane_1.setViewportView(Gruppo);
		
		JButton Aggiungi = new JButton("Aggiungi");
		sl_contentPane.putConstraint(SpringLayout.NORTH, Aggiungi, 70, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, Aggiungi, 166, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, Aggiungi, 255, SpringLayout.WEST, contentPane);
		contentPane.add(Aggiungi);
		
		JButton Elimina = new JButton("Elimina");
		sl_contentPane.putConstraint(SpringLayout.NORTH, Elimina, 104, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, Elimina, 166, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, Elimina, 255, SpringLayout.WEST, contentPane);
		contentPane.add(Elimina);
		
		JButton Salva = new JButton("Salva");
		sl_contentPane.putConstraint(SpringLayout.NORTH, Salva, 219, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, Salva, 321, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, Salva, 410, SpringLayout.WEST, contentPane);
		contentPane.add(Salva);
		
		JButton Annulla = new JButton("Annulla");
		sl_contentPane.putConstraint(SpringLayout.NORTH, Annulla, 219, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, Annulla, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, Annulla, 99, SpringLayout.WEST, contentPane);
		contentPane.add(Annulla);
	}
}
