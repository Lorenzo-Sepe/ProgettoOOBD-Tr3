package ProvaMain;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import GUI.AggiungiIndririzzoModificaPanel;
import Model.Indirizzi;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ProvaGui extends JFrame {
	private JTable tabella;
	private static Controller c;
	
	public ProvaGui (ArrayList<Indirizzi> listaIndirizzi, Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		c = controller;
		try {
			c.dumpDati();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DefaultTableModel modelloTabella = new DefaultTableModel() {
			@Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
	        
	        modelloTabella.addColumn("Id");
	        modelloTabella.addColumn("Via");
	        modelloTabella.addColumn("Città");
	        modelloTabella.addColumn("Codice Postale");
	        modelloTabella.addColumn("Nazione");
	        modelloTabella.addColumn("Tag");
	        modelloTabella.addColumn("Principale");
	        
	        for (Indirizzi indirizzo : listaIndirizzi) {
	        	modelloTabella.addRow(new Object[] {
	        			indirizzo.getID(),
	        			indirizzo.getVia(),
	        			indirizzo.getCittà(),
	        			indirizzo.getCodicePostale(),
	        			indirizzo.getNazione(),
	        			indirizzo.getIdentificatore(),
	        			indirizzo.getPrincipale()
	        	});
	        }
	        
	        JScrollPane scrIndirizzi = new JScrollPane();
	        getContentPane().add(scrIndirizzi);
	        
	        tabella = new JTable(modelloTabella);
	        scrIndirizzi.setViewportView(tabella);
	        
	        JButton btnAggiungi = new JButton("Aggiungi");
	        btnAggiungi.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		int id = 0;
	        		String via = "";
	        		String città = "";
	        		String codicePostale = "";
	        		String nazione = "";
	        		String tag = "";
	        		boolean principale = false;
	        		AggiungiIndririzzoModificaPanel aggiungiIndirizzoPanel = new AggiungiIndririzzoModificaPanel();
	        		int ris = JOptionPane.showConfirmDialog(null, aggiungiIndirizzoPanel, "Confirm", JOptionPane.OK_CANCEL_OPTION);
	        		if (ris == JOptionPane.OK_OPTION) {
	        			id = listaIndirizzi.size()+1;
	        			via = aggiungiIndirizzoPanel.getVia();
	        			città = aggiungiIndirizzoPanel.getCittà();
	        			codicePostale = aggiungiIndirizzoPanel.getCodicePostale();
	        			nazione = aggiungiIndirizzoPanel.getNazione();
	        			tag = aggiungiIndirizzoPanel.getTag();
	        			principale = aggiungiIndirizzoPanel.getPrincipale();
	        			listaIndirizzi.add(new Indirizzi(id, principale, via, città, codicePostale, nazione, tag));
	        			c.aggiungiIndirizzo(id, tag, via, città, codicePostale, nazione, principale);
	        			
	        			if (principale) {
	        				modelloTabella.setValueAt(false, 0, 6);
	        				modelloTabella.insertRow(0, new Object[] {id,via,città,codicePostale,nazione,tag,principale});
	        			}
	        			else {
	        				modelloTabella.addRow(new Object[] {id,via,città,codicePostale,nazione,tag,principale});
	        			}
	        		}
	        	}
	        });
	        getContentPane().add(btnAggiungi, BorderLayout.SOUTH);
	}
	
	
}
