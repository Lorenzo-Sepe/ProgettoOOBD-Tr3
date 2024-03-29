package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Contatto;

@SuppressWarnings("serial")
public class CreaGruppoFrame extends JFrame {

	private JPanel contentPane;
	private JTable ContattiTable;
	private JTable GruppoTable;
	private JTextField textFieldGruppo;
	
	private static Controller c;
	private static  JFrame frameChiamante;
	private static JFrame frame;
	
	private ArrayList <Contatto> membriGruppo = new ArrayList<>();
	private ArrayList<Contatto> listaContattiArrayList; 


	/**
	 * Create the frame.
	 */
	public CreaGruppoFrame(Controller controller,  JFrame chiamante) {
		c =controller;
		frameChiamante = chiamante;
		frame = this;
		listaContattiArrayList = new ArrayList<>(c.getListaContatti());
		setTitle("Crea Gruppo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 955, 385);
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
	
//		if (listaContattiArrayList.isEmpty()) {
//			System.out.println("La lista dei contatti per la crea gruppo è vuota");
//		}
		
		for (Contatto contatto : listaContattiArrayList) {
			modelloContatti.addRow(new Object[] {
				contatto.getID(),
				contatto.getPrefissoNome(),
				contatto.getNome(),
				contatto.getCognome()
			});
		}
		ContattiTable.removeColumn(ContattiTable.getColumnModel().getColumn(0));
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
		
		modelloGruppo.addColumn("Id");
		modelloGruppo.addColumn("prefisso");
		modelloGruppo.addColumn("nome"); 
		modelloGruppo.addColumn("cognome"); 
		
		GruppoTable.removeColumn(GruppoTable.getColumnModel().getColumn(0));
		scrollPaneGruppo.setViewportView(GruppoTable);
		
		JButton buttonAggiungi = new JButton("Aggiungi ->");
		buttonAggiungi.setBounds(410, 125, 115, 25);
		buttonAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				if (!listenerContattoSelezionato.isSelectionEmpty()) {
					row = ContattiTable.getSelectedRow();
					int idContatto = Integer.parseInt(modelloContatti.getValueAt(row, 0).toString());
					Object prefisso = modelloContatti.getValueAt(row, 1);
					Object nome = modelloContatti.getValueAt(row, 2);
					Object cognome = modelloContatti.getValueAt(row, 3); 
					membriGruppo.add(listaContattiArrayList.get(row));
					listaContattiArrayList.remove(row);
					modelloContatti.removeRow(row);
					modelloGruppo.addRow(new Object[]{idContatto,prefisso, nome, cognome});
					}
			}
		});
		contentPane.add(buttonAggiungi);
		
		JButton buttonElimina = new JButton("<- Elimina");
		buttonElimina.setBounds(410, 155, 115, 25);
		buttonElimina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				if (!listenerGruppoSelezionato.isSelectionEmpty()) {
					row = GruppoTable.getSelectedRow();
					int idContatto = Integer.parseInt(modelloGruppo.getValueAt(row, 0).toString());
					Object prefisso = modelloGruppo.getValueAt(row, 1);
					Object nome = modelloGruppo.getValueAt(row, 2);
					Object cognome = modelloGruppo.getValueAt(row, 3);
					listaContattiArrayList.add(membriGruppo.get(row));
					membriGruppo.remove(row);
					modelloGruppo.removeRow(row);
					modelloContatti.addRow(new Object[]{idContatto, prefisso, nome, cognome});
				}
			}
		});
		contentPane.add(buttonElimina);
		
//		JButton buttonSalva = new JButton("Salva");
//		buttonSalva.setBounds(690, 325, 90, -45);
//		contentPane.add(buttonSalva);
		
		JButton buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameChiamante.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		buttonAnnulla.setBounds(735, 305, 90, 23);
		contentPane.add(buttonAnnulla);
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					c.checkGruppo(textFieldGruppo.getText(), membriGruppo);
					c.aggiungiGruppo(textFieldGruppo.getText(), membriGruppo);
					JOptionPane.showMessageDialog(null, "Gruppo creato con successo");
				
					frameChiamante.setVisible(true);
					frame.setVisible(false);
					frame.dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalva.setBounds(835, 305, 90, 23);
		contentPane.add(btnSalva);
		
		textFieldGruppo = new JTextField();
		textFieldGruppo.setText("");
		textFieldGruppo.setBounds(175, 25, 230, 20);
		contentPane.add(textFieldGruppo);
		textFieldGruppo.setColumns(10);
	}
}