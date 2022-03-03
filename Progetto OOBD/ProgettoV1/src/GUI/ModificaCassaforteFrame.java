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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 
 * @author AlessandroTrincone
 *
 */
public class ModificaCassaforteFrame extends JFrame {

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
	private ArrayList<Contatto> RAIlistaContatti = new ArrayList<>();
	private ArrayList<Contatto> RAIlistaContattiCassaforte = new ArrayList<>();
	
	
	public  ModificaCassaforteFrame (Controller controller, JFrame chiamante, String password) {
		frame = this;
		frameChiamante = chiamante;
		c = controller;
		c.transactionBegin();
		setResizable(false);
		setTitle("Modifica Cassaforte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneRubrica = new JScrollPane();
		scrollPaneRubrica.setBounds(15, 43, 390, 232);
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
		
//		listaContatti = c.getListaContatti();
//		ArrayList<Integer> listaContattiNonInCassaforte = new ArrayList<>();
//		ArrayList<Contatto> contattiInCassaforte = c.rubrica.getCassaforte().getListaGruppo();
//		ArrayList<Integer> contattiInCassaforteId = new ArrayList<>();
//		for (Contatto contatto : listaContatti) {
//			listaContattiNonInCassaforte.add(contatto.getID());
//		}
//		for (Contatto contatto : contattiInCassaforte) {
//			contattiInCassaforteId.add(contatto.getID());
//		}
//		listaContattiNonInCassaforte.removeAll(contattiInCassaforteId);
//		// Append a row 
//		
//		
//		for (int id : listaContattiNonInCassaforte) {
//			modelloContatti.addRow(new Object[] {
//					id,
//					c.getInfoContattoPrefisso(id),
//					c.getInfoContattoNome(id),
//					c.getInfoContattoCognome(id)
//			});
//		}
		
		RAIlistaContattiCassaforte = c.getContattiCassaforte();
		RAIlistaContatti = c.getListaContatti();
		
		for (Contatto raiContatto : RAIlistaContatti) {
			modelloContatti.addRow(new Object[] {
					raiContatto.getID(),
					raiContatto.getPrefissoNome(),
					raiContatto.getNome(),
					raiContatto.getCognome()

			});
		}
		
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
		
		for (Contatto contatto : RAIlistaContattiCassaforte) {
			modelloCassaforte.addRow(new Object[] {
					contatto.getID(),
					contatto.getPrefissoNome(),
					contatto.getNome(),
					contatto.getCognome()
			});
		}
		
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
					System.out.println(modelloContatti.getValueAt(row, 0));
					int id = Integer.parseInt(modelloContatti.getValueAt(row, 0).toString());
					String prefisso = (String) modelloContatti.getValueAt(row, 1);
					String nome = (String) modelloContatti.getValueAt(row, 2);
					String cognome = (String) modelloContatti.getValueAt(row, 3);
					modelloContatti.removeRow(row);
					RAIlistaContattiCassaforte.add(RAIlistaContatti.get(row));
					RAIlistaContatti.remove(row);
					modelloCassaforte.addRow(new Object[]{id, prefisso, nome, cognome});
					try {
						c.aggiungiContattoInCassaforteDB(id);
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();	//TODO toglierlo
					}
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
					modelloCassaforte.removeRow(row);
					RAIlistaContatti.add(RAIlistaContattiCassaforte.get(row));
					RAIlistaContattiCassaforte.remove(row);
					modelloContatti.addRow(new Object[]{prefisso, nome, cognome});
					try {
						c.eliminaContattoDaCassaforteDB(id);
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();	//TODO toglierlo
					}
				}
			}
		});
		contentPane.add(buttonElimina);
		
		buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.transactionRollBack();
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
//				ArrayList<Integer> listaContattiId = new ArrayList<>();
//				for (int i = 0; i < modelloCassaforte.getRowCount(); i++) {
//					String prefisso;
//					String nome;
//					String cognome;
//					if (modelloCassaforte.getValueAt(i, 0)!= null) {
//						prefisso = modelloCassaforte.getValueAt(i, 0).toString();
//					}
//					else {
//						prefisso = "";
//					}
//					if (modelloCassaforte.getValueAt(i, 1)!= null) {
//						nome = modelloCassaforte.getValueAt(i, 1).toString();
//					}
//					else {
//						nome = "";
//					}
//					if (modelloCassaforte.getValueAt(i, 2)!= null) {
//						cognome = modelloCassaforte.getValueAt(i, 2).toString();
//					}
//					else {
//						cognome = "";
//					}
//					Contatto contatto = new Contatto((int)modelloCassaforte.getValueAt(i, 0), prefisso, nome, cognome, "null");
//					listaContattiId.add((int)modelloCassaforte.getValueAt(i, 0));
//					contattiInCassaforte.add(contatto);
//				}
				
				c.setListaContattiCassaforte(RAIlistaContattiCassaforte);
				c.transactionCommit();
				frameChiamante.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnSalva.setBounds(835, 305, 90, 23);
		contentPane.add(btnSalva);
	}
}