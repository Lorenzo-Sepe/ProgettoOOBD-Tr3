package GUI;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Contatto;
/**
 * 
 * @author AlessandroTrincone
 *
 */
@SuppressWarnings("serial")
public class CreaCassaforteFrame extends JFrame {

	private JPanel contentPane;
	private JTable ContattiTable;
	private JTable CassaforteTable;
	private JButton buttonAnnulla;
	private JButton btnSalva;
	private JButton buttonAggiungi;
	private JButton buttonElimina;
	
	private static Controller c;
	private static  JFrame frameChiamante;
	private static JFrame frame;
	
	private ArrayList<Contatto> listaContatti = new ArrayList<>();
	private ArrayList<Contatto> contattiInCassaforte = new ArrayList<>();
	
	
	public CreaCassaforteFrame(Controller controller, JFrame chiamante, String password, DefaultTableModel modelloContatti) {
		frame = this;
		frameChiamante = chiamante;
		c = controller;
		setResizable(false);
		setTitle("Crea Cassaforte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 950, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneRubrica = new JScrollPane();
		scrollPaneRubrica.setBounds(15, 43, 390, 232);
		contentPane.add(scrollPaneRubrica);
		
		//Contatti = new JTable();
//		DefaultTableModel modelloContatti =  new DefaultTableModel() {
//	        @Override
//	        public boolean isCellEditable(int row, int column) {
//	           //all cells false
//	           return false;
//	        }};
		
		ContattiTable = new JTable(modelloContatti);
		ListSelectionModel listenerContattoSelezionato=ContattiTable.getSelectionModel();
		ContattiTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
//		modelloContatti.addColumn("Id");
//		modelloContatti.addColumn("prefisso");
//		modelloContatti.addColumn("nome"); 
//		modelloContatti.addColumn("cognome"); 
//		
		listaContatti = c.getListaContatti();
//		
//		// Append a row 
//		
//		for (Contatto contatto : listaContatti) {
//			modelloContatti.addRow(new Object[] {
//					contatto.getID(),
//					contatto.getPrefissoNome(),
//					contatto.getNome(),
//					contatto.getCognome()
//			});
//		}
		
		ContattiTable.removeColumn(ContattiTable.getColumnModel().getColumn(0));
		scrollPaneRubrica.setViewportView(ContattiTable);
		
		JScrollPane scrollPaneCassaforte = new JScrollPane();
		scrollPaneCassaforte.setBounds(530, 43, 390, 232);
		contentPane.add(scrollPaneCassaforte);
		
		//Gruppo = new JTable();
		DefaultTableModel modelloCassaforte =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
	    CassaforteTable = new JTable(modelloCassaforte);
		ListSelectionModel listenerGruppoSelezionato=CassaforteTable.getSelectionModel();
		CassaforteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelloCassaforte.addColumn("Id");
		modelloCassaforte.addColumn("prefisso");
		modelloCassaforte.addColumn("nome"); 
		modelloCassaforte.addColumn("cognome"); 
		
		CassaforteTable.removeColumn(CassaforteTable.getColumnModel().getColumn(0));
		scrollPaneCassaforte.setViewportView(CassaforteTable);
		
		buttonAggiungi = new JButton("Aggiungi ->");
		buttonAggiungi.setBounds(410, 125, 115, 25);
		buttonAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				if (!listenerContattoSelezionato.isSelectionEmpty()) {
					row = ContattiTable.getSelectedRow();
					//System.out.println(modelloContatti.getValueAt(row, 0));
					int id = Integer.parseInt(modelloContatti.getValueAt(row, 0).toString());
					String prefisso = (String) modelloContatti.getValueAt(row, 1);
					String nome = (String) modelloContatti.getValueAt(row, 2);
					String cognome = (String) modelloContatti.getValueAt(row, 3);
					contattiInCassaforte.add(listaContatti.get(row));
					listaContatti.remove(row);
					modelloContatti.removeRow(row);
					modelloCassaforte.addRow(new Object[]{id, prefisso, nome, cognome});
					//TODO MembriCassaforte.add();
					}
			}
		});
		contentPane.add(buttonAggiungi);
		
		buttonElimina = new JButton("<- Elimina");
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
					row = CassaforteTable.getSelectedRow();
					int id = (int) modelloCassaforte.getValueAt(row, 0);
					String prefisso = (String) modelloCassaforte.getValueAt(row, 1);
					String nome = (String) modelloCassaforte.getValueAt(row, 2);
					String cognome = (String) modelloCassaforte.getValueAt(row, 3);
					listaContatti.add(contattiInCassaforte.get(row));
					contattiInCassaforte.remove(row);
					modelloCassaforte.removeRow(row);
					int posizioneContatto = 0; //TODO implementare c.posizioneContatto(modelRubrica,nome,cognome)
					modelloContatti.insertRow(posizioneContatto, new Object[]{id,prefisso, nome, cognome});
					//TODO MembriCassaforte.remove(object);
				}
			}
		});
		contentPane.add(buttonElimina);
		
		buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		buttonAnnulla.setBounds(735, 305, 90, 23);
		contentPane.add(buttonAnnulla);
		
		btnSalva = new JButton("Salva");
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					c.creaCassaforte(password);
					c.aggiungiListaContattiCassaforte(password, contattiInCassaforte);
					
					JOptionPane.showMessageDialog(null, "Cassaforte creata con successo");
					frameChiamante.setVisible(true);
					frame.setVisible(false);
					frame.dispose();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();	//TODO toglierlo
				}
			}
		});
		btnSalva.setBounds(835, 305, 90, 23);
		contentPane.add(btnSalva);
	}
}