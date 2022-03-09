package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Contatto;
import Controller.Controller;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class VisualizzaCassaforteFrame extends JFrame {

	private JPanel contentPane;
	private JTable tableCassaforte;
	
	static JFrame frameChiamante;
	static JFrame frame;
	static Controller c;
	
	
	
	public VisualizzaCassaforteFrame(Controller controller, JFrame chiamante) {
		c = controller;
		frameChiamante = chiamante;
		frame = this;
		setTitle("Cassaforte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneCassaforte = new JScrollPane();
		scrollPaneCassaforte.setBounds(48, 40, 322, 178);
		contentPane.add(scrollPaneCassaforte);
		
		//tableCassaforte = new JTable();
		DefaultTableModel modelCassaforte = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
		           //all cells false
		           return false;
		        }};
		        
		modelCassaforte.addColumn("Id");
		modelCassaforte.addColumn("Prefisso");
		modelCassaforte.addColumn("Nome");
		modelCassaforte.addColumn("Cognome");
		
		ArrayList<Contatto> contattiProtetti = c.getContattiCassaforte();
		
		for (Contatto contatto : contattiProtetti) {
			System.out.println("sono dentro la visualizza cassadrote frame, id del contatto e-"+contatto.getID());
			modelCassaforte.addRow(new Object[] {
					contatto.getID(),
					contatto.getPrefissoNome(),
					contatto.getNome(),
					contatto.getCognome()
			});
		}
		
		tableCassaforte = new JTable(modelCassaforte);
		tableCassaforte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					int row = tableCassaforte.getSelectedRow();
					int contattoSelezionatoID = (int)modelCassaforte.getValueAt(row, 0);
					System.out.println("id conta selez:-"+contattoSelezionatoID);
					JFrame visualizzaContatto = new VisualizzaContattoCassaforteFrame(controller, frame, contattoSelezionatoID);
					visualizzaContatto.setVisible(true);
					frame.setVisible(false);
				}
			}
		});
		ListSelectionModel listenerContattoSelezionato = tableCassaforte.getSelectionModel();
		tableCassaforte.setSelectionMode(listenerContattoSelezionato.SINGLE_SELECTION);
		
		tableCassaforte.removeColumn(tableCassaforte.getColumnModel().getColumn(0));
		
		
		scrollPaneCassaforte.setViewportView(tableCassaforte);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameChiamante.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnIndietro.setBounds(168, 227, 89, 23);
		contentPane.add(btnIndietro);
		
		JLabel lblNewLabel = new JLabel("Cassaforte");
		lblNewLabel.setBounds(133, 11, 160, 14);
		contentPane.add(lblNewLabel);
	}
}
