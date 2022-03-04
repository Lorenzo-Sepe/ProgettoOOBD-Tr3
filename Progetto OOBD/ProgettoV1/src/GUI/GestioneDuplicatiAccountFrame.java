package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Contatto;

public class GestioneDuplicatiAccountFrame extends JFrame {

	private JPanel contentPane;
	private GestioneDuplicatiAccountFrame frame;
	private Object localList;
	private JPanel panelLista;
	private JLabel lblmail;
	private JScrollPane scrollPane;
	private JTable tableAccount;
	private JLabel lblContatti;
	private JToolBar toolBar;
	private JButton btnBack;
	private JButton btnModifica;
	private JButton btnElimina;
	private ArrayList <Contatto> contattilList;
	private JScrollPane scrollPaneContatti;
	private JTable tableRisultati;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					gestioneDuplicatiAccountFrame frame = new gestioneDuplicatiAccountFrame();
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
	public GestioneDuplicatiAccountFrame(Controller c, JFrame frameChiamante, ArrayList<Contatto> listaRisultati) {
		frame = this;
		localList = listaRisultati;
				
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
		
		
		
		lblmail = new JLabel("Contatti con duplicati");
		sl_panelLista.putConstraint(SpringLayout.NORTH, lblmail, 0, SpringLayout.NORTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.WEST, lblmail, 100, SpringLayout.WEST, panelLista);
		sl_panelLista.putConstraint(SpringLayout.SOUTH, lblmail, 25, SpringLayout.NORTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.EAST, lblmail, -10, SpringLayout.EAST, panelLista);
		panelLista.add(lblmail);
		
		scrollPaneContatti = new JScrollPane();
		
		sl_panelLista.putConstraint(SpringLayout.NORTH, scrollPaneContatti, 5, SpringLayout.SOUTH, lblmail);
		sl_panelLista.putConstraint(SpringLayout.WEST, scrollPaneContatti, 5, SpringLayout.WEST, panelLista);
		sl_panelLista.putConstraint(SpringLayout.SOUTH, scrollPaneContatti, -40, SpringLayout.SOUTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.EAST, scrollPaneContatti, -5, SpringLayout.EAST, panelLista);
		panelLista.add(scrollPaneContatti);
		
		
		
		JPanel panelTabella = new JPanel();
		contentPane.add(panelTabella);
		SpringLayout sl_panelTabella = new SpringLayout();
		panelTabella.setLayout(sl_panelTabella);
		
		scrollPane = new JScrollPane();
		sl_panelTabella.putConstraint(SpringLayout.NORTH, scrollPane, 30, SpringLayout.NORTH, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.SOUTH, scrollPane, -40, SpringLayout.SOUTH, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.EAST, scrollPane, -5, SpringLayout.EAST, panelTabella);
		panelTabella.add(scrollPane);
		
		DefaultTableModel modelloAccount = new DefaultTableModel () {
			
	        public boolean isCellEditable(int row, int column) {
	           return false;
	        }

		};

		
		DefaultTableModel modelloContatto = new DefaultTableModel () {
			
	        public boolean isCellEditable(int row, int column) {
	           return false;
	        }     
	        
		};

		modelloContatto.addColumn("Id");
		modelloContatto.addColumn("prefisso");
		modelloContatto.addColumn("nome"); 
		modelloContatto.addColumn("cognome"); 
		
		tableRisultati = new JTable(modelloContatto);
		tableRisultati.removeColumn(tableRisultati.getColumnModel().getColumn(0));
		tableRisultati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Sono in listner");
				int cont = modelloAccount.getRowCount();
				while (cont>0) {
					int i=0;
					modelloAccount.removeRow(i);
					cont--;
				}
					
				
				int id = (int) tableRisultati.getModel().getValueAt(tableRisultati.getSelectedRow(),0);
				c.dumpContatto(id);

				for (int i = 0; i < c.getInfoContattoAccountQuantità(id); i++) {
					System.out.println("Sono in for");
					modelloAccount.addRow(new Object[] {
						c.getInfoContattoAccountFornitore(i, id)	,
						c.getInfoContattoAccountNickname(i, id),
						c.getInfoContattoAccountMail(i, id)	,
						c.getInfoContattoAccountBenvenuto(i, id)	
					});
				}
			}
		});
		final ListSelectionModel listenerContatto = tableRisultati.getSelectionModel();
		tableRisultati.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneContatti.setViewportView(tableRisultati);
		
		for (int i = 0; i < listaRisultati.size(); i++) {
			modelloContatto.addRow(new Object[]{
					listaRisultati.get(i).getID(),
					listaRisultati.get(i).getPrefissoNome(),
					listaRisultati.get(i).getNome(),
					listaRisultati.get(i).getCognome()
			});
		}
		
		
		modelloAccount.addColumn("Fornitore");
		modelloAccount.addColumn("Nickname"); 
		modelloAccount.addColumn("Indirizzo e-mail");
		modelloAccount.addColumn("Frase di benvenuto"); 
		
		
		tableAccount = new JTable(modelloAccount);
		final ListSelectionModel listenerAccount = tableAccount.getSelectionModel();
		tableAccount.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableAccount);
		
		lblContatti = new JLabel("Account del contatto");
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
		btnBack.setIcon(new ImageIcon(GestioneDuplicatiAccountFrame.class.getResource("/immagini/back.jpg")));
		toolBar.add(btnBack);
		
		btnModifica = new JButton("Modifica Account");
		btnModifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if (!listenerAccount.isSelectionEmpty()) {
//					int row = tableAccount.getSelectedRow();
//					int column = 0;
//					AggiungiAccountPanel panelModificaAccount = new AggiungiAccountPanel(listaMail);
//					panelModificaAccount.setAll(modelloAccount.getValueAt(row, column++).toString(), modelloAccount.getValueAt(row, column++).toString(),modelloAccount.getValueAt(row, column++).toString(), modelloAccount.getValueAt(row, column++).toString());
//					int results = JOptionPane.showConfirmDialog(null, panelModificaAccount,"Modifica Account",JOptionPane.OK_CANCEL_OPTION);
//					
//					if (results == JOptionPane.OK_OPTION) {
//						column = 0;
//						modelloAccount.setValueAt(panelModificaAccount.getFornitore(), row, column++);
//						modelloAccount.setValueAt(panelModificaAccount.getNickname(), row, column++);
//						modelloAccount.setValueAt(panelModificaAccount.getMail(), row, column++);
//						modelloAccount.setValueAt(panelModificaAccount.getFraseDiBenvenuto(), row, column++);
//					}
//				}
			}
		});
		btnModifica.setIcon(new ImageIcon(GestioneDuplicatiContattiFrame.class.getResource("/immagini/edit.png")));
		toolBar.add(btnModifica);
		
		btnElimina = new JButton("Elimina Account");
		btnElimina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO
			}
		});
		btnElimina.setIcon(new ImageIcon(GestioneDuplicatiContattiFrame.class.getResource("/immagini/elimina.jpg")));
		toolBar.add(btnElimina);
        
		
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
