package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Contatto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class VisualizzaListaContattiGruppoFrame extends JDialog {

	private JPanel contentPane;
	private JTable tableContatti;
	
	private static Controller c;
	private static  JFrame frameChiamante;
	private static JDialog frame;
	private static String nomeGruppo;

	
	
	
	public VisualizzaListaContattiGruppoFrame(Controller controller, JFrame chiamante, String nGruppo) {
		c = controller;
		frameChiamante = chiamante;
		frame = this;
		nomeGruppo = nGruppo;
		try {
			c.dumpListaMembriGruppo(nomeGruppo);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();	//TODO toglierlo
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle(nomeGruppo);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeGruppo = new JLabel(nomeGruppo);
		lblNomeGruppo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeGruppo.setBounds(53, 11, 336, 14);
		contentPane.add(lblNomeGruppo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 45, 372, 172);
		contentPane.add(scrollPane);
		
//		table = new JTable();
		DefaultTableModel modelloContatti = new DefaultTableModel() {
			@Override
			public boolean isCellEditable (int row, int column) {
				return false;
			}
		};
		
		tableContatti = new JTable(modelloContatti);
		tableContatti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					int row = tableContatti.getSelectedRow();
					int id = (int)modelloContatti.getValueAt(row, 0);
					JFrame visualizzaContatto = new VisualizzaContattoFrame(controller, frameChiamante,id);
					visualizzaContatto.setVisible(true);
					frame.setVisible(false);
					frameChiamante.setVisible(false);
					frame.dispose();
				}
			}
		});
		ListSelectionModel listenerContattoSelezionato = tableContatti.getSelectionModel();
		tableContatti.setSelectionMode(listenerContattoSelezionato.SINGLE_SELECTION);
		
		modelloContatti.addColumn("Id");
		modelloContatti.addColumn("Prefisso");
		modelloContatti.addColumn("Nome");
		modelloContatti.addColumn("Cognome");
		
		ArrayList<Contatto> listaContatti = new ArrayList<>();
		listaContatti = new ArrayList<>(c.getListaMembriGruppo(nomeGruppo));
		
		for (Contatto contatto : listaContatti) {
			modelloContatti.addRow(new Object[] {
					contatto.getID(),
					contatto.getPrefissoNome(),
					contatto.getNome(),
					contatto.getCognome()
			});
		}
		
		tableContatti.removeColumn(tableContatti.getColumnModel().getColumn(0));		
		scrollPane.setViewportView(tableContatti);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnIndietro.setBounds(319, 228, 89, 23);
		contentPane.add(btnIndietro);
	}
}
