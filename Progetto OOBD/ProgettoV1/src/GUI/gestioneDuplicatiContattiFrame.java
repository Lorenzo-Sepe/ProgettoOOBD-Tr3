package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import Controller.Controller;
import Model.Contatto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class gestioneDuplicatiContattiFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panelLista;
	private JTable tableRisultati;
	private JList listaMail;
	private JLabel lblmail;
	private JScrollPane scrollPane;
	private JLabel lblContatti;
	private JToolBar toolBar;
	private JButton btnBack;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JFrame frame;
	private ArrayList<String>localList = new ArrayList<>();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MailDuplicateFrame frame = new MailDuplicateFrame(c,frameC,list);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public gestioneDuplicatiContattiFrame(Controller c, JFrame frameChiamante, ArrayList mailList) {
		frame = this;
		localList = mailList;
		setTitle("Controllo duplicati");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		panelLista = new JPanel();
		contentPane.add(panelLista);
		SpringLayout sl_panelLista = new SpringLayout();
		panelLista.setLayout(sl_panelLista);
		
		
		
		lblmail = new JLabel("Email Duplicate");
		sl_panelLista.putConstraint(SpringLayout.NORTH, lblmail, 0, SpringLayout.NORTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.WEST, lblmail, 100, SpringLayout.WEST, panelLista);
		sl_panelLista.putConstraint(SpringLayout.SOUTH, lblmail, 25, SpringLayout.NORTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.EAST, lblmail, -10, SpringLayout.EAST, panelLista);
		panelLista.add(lblmail);
		
		JPanel panelTabella = new JPanel();
		contentPane.add(panelTabella);
		SpringLayout sl_panelTabella = new SpringLayout();
		panelTabella.setLayout(sl_panelTabella);
		
		scrollPane = new JScrollPane();
		sl_panelTabella.putConstraint(SpringLayout.NORTH, scrollPane, 30, SpringLayout.NORTH, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.SOUTH, scrollPane, -35, SpringLayout.SOUTH, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.EAST, scrollPane, -5, SpringLayout.EAST, panelTabella);
		panelTabella.add(scrollPane);
		
		
		DefaultTableModel modelloMail = new DefaultTableModel () {
			
	        public boolean isCellEditable(int row, int column) {
	           return false;
		}
	        
	        
		};

		modelloMail.addColumn("Id");
		modelloMail.addColumn("prefisso");
		modelloMail.addColumn("nome"); 
		modelloMail.addColumn("cognome"); 
		
//		modelloMail.addRow(new Object[]{
//				"",
//				""
//		});
		tableRisultati = new JTable(modelloMail);
		tableRisultati.removeColumn(tableRisultati.getColumnModel().getColumn(0));
		scrollPane.setViewportView(tableRisultati);
		
		lblContatti = new JLabel("Contatti in cui appare");
		sl_panelTabella.putConstraint(SpringLayout.NORTH, lblContatti, 0, SpringLayout.NORTH, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.WEST, lblContatti, 100, SpringLayout.WEST, scrollPane);
		sl_panelTabella.putConstraint(SpringLayout.SOUTH, lblContatti, 25, SpringLayout.NORTH, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.EAST, lblContatti, 0, SpringLayout.EAST, panelTabella);
		panelTabella.add(lblContatti);
		
		toolBar = new JToolBar();
		sl_panelTabella.putConstraint(SpringLayout.NORTH, toolBar, 0, SpringLayout.SOUTH, scrollPane);
		sl_panelTabella.putConstraint(SpringLayout.WEST, toolBar, 0, SpringLayout.WEST, scrollPane);
		sl_panelTabella.putConstraint(SpringLayout.SOUTH, toolBar, 0, SpringLayout.SOUTH, panelTabella);
		panelTabella.add(toolBar);
		
		btnBack = new JButton("Indietro");
		btnBack.setIcon(new ImageIcon(gestioneDuplicatiContattiFrame.class.getResource("/immagini/back.jpg")));
		toolBar.add(btnBack);
		
		btnNewButton_2 = new JButton("Modifica Contatto");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = (int) tableRisultati.getModel().getValueAt(tableRisultati.getSelectedRow(),0);
            	ModificaContattoFrame newFrame = new ModificaContattoFrame(c, frame, id);
            	localList = aggiornaTabella(c, localList);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(gestioneDuplicatiContattiFrame.class.getResource("/immagini/edit.png")));
		toolBar.add(btnNewButton_2);
		
		btnNewButton_1 = new JButton("Elimina Contatto");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableRisultati.getSelectedRow();
				if (row>0) {
					if (JOptionPane.showConfirmDialog(null, "Sicuro di voler cancellare il contatto?", "WARNING", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						int id = (int) modelloMail.getValueAt(row, 0);
						try {
							c.eliminaContattoDB(id);
							modelloMail.removeRow(row);
							JOptionPane.showMessageDialog(null, "Contatto eliminato con successo");
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace(); 
						}
					}
					
				}
				localList = aggiornaTabella(c, localList);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(gestioneDuplicatiContattiFrame.class.getResource("/immagini/elimina.jpg")));
		toolBar.add(btnNewButton_1);
        
		DefaultListModel modelloLista = new DefaultListModel();
		modelloLista.addAll(mailList);
		
		listaMail = new JList(modelloLista);
		listaMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mailSelezionata = listaMail.getSelectedValue().toString();
				int cont = modelloMail.getRowCount();
				while (cont>0) {
					int i=0;
					modelloMail.removeRow(i);
					cont--;
				}
								
				ArrayList <Contatto> listaRisultati = c.verificaDuplicatiContatto(mailSelezionata);
				for (int i = 0; i < listaRisultati.size(); i++) {
					modelloMail.addRow(new Object[]{
							listaRisultati.get(i).getID(),
							listaRisultati.get(i).getPrefissoNome(),
							listaRisultati.get(i).getNome(),
							listaRisultati.get(i).getCognome()
					});
				}
			}
		});
		sl_panelLista.putConstraint(SpringLayout.NORTH, listaMail, 30, SpringLayout.NORTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.WEST, listaMail, 5, SpringLayout.WEST, panelLista);
		sl_panelLista.putConstraint(SpringLayout.SOUTH, listaMail, -5, SpringLayout.SOUTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.EAST, listaMail, -5, SpringLayout.EAST, panelLista);
		panelLista.add(listaMail);
	}

	protected ArrayList aggiornaTabella(Controller c, ArrayList mailList) {
		ArrayList<String> newListaMail = c.verificaMailDuplicate();
		if( newListaMail.equals(mailList)==true ) {
			return mailList;
		}else {
				return newListaMail;
			}
	}
	
	
}
