package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import DAO.RubricaDAO;
import Model.Gruppo;
import Model.Rubrica;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualizzaGruppiFrame extends JFrame {

	private JPanel contentPane;
	private JList listGruppi;
	private JScrollPane scrollPaneGruppi;
	private JButton buttonCreaGruppo;
	private JTable tableGruppi;
	
	static JFrame frameChiamante;
	static JFrame frame;
	static Controller c;
	static Rubrica r;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Controller controller = new Controller();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaGruppiFrame frame = new  VisualizzaGruppiFrame (controller, frameChiamante);
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
	public VisualizzaGruppiFrame(Controller controller, JFrame chiamante) {
		
		frame = this;
		frameChiamante = chiamante;
		c = controller;
		

		
		try {
			c.dumpListaGruppi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setTitle("Visualizza gruppi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		scrollPaneGruppi = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneGruppi, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneGruppi, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneGruppi, -5, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneGruppi, -150, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPaneGruppi);
		
		
		 DefaultTableModel modelGruppi =  new DefaultTableModel() {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		           //all cells false
		           return false;
		        }};
			
		        modelGruppi.addColumn("Id");
		        modelGruppi.addColumn("Nome");
			
			
		// Inserimento nella tabella dei numeri del contatto
			
		ArrayList<Gruppo> listaGruppi = c.getListaGruppi();
		int i = 1;
		
		for (Gruppo gruppo : listaGruppi) {
			modelGruppi.addRow(new Object[] {
					i++,
					gruppo.getNomeGruppo()
			});
		}
		
		tableGruppi = new JTable(modelGruppi);
		ListSelectionModel listenerGruppoSelezionato = tableGruppi.getSelectionModel();
		tableGruppi.setSelectionMode(listenerGruppoSelezionato.SINGLE_SELECTION);
		tableGruppi.removeColumn(tableGruppi.getColumnModel().getColumn(0));
		scrollPaneGruppi.setViewportView(tableGruppi);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameChiamante.setVisible(true);
				frame.setVisible(false);
				try {
					c.dumpDati();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
			}
		});
		
		JButton buttonModificaGruppo = new JButton("Modifica Gruppo");
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonModificaGruppo, 5, SpringLayout.EAST, scrollPaneGruppi);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonModificaGruppo, -10, SpringLayout.EAST, contentPane);
		contentPane.add(buttonModificaGruppo);
		
		JButton buttonEliminaGruppo = new JButton("Elimina Gruppo");
		buttonEliminaGruppo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!listenerGruppoSelezionato.isSelectionEmpty()) {
					int row = tableGruppi.getSelectedRow();
					String nomeGruppo = modelGruppi.getValueAt(row, 1).toString();
					int ris = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare il gruppo "+nomeGruppo+"?", "Message", JOptionPane.OK_CANCEL_OPTION);
					
					if (ris == JOptionPane.OK_OPTION) {
						try {
							c.eliminaGruppo(nomeGruppo);
							modelGruppi.removeRow(row);
							c.dumpListaContatti();
							JOptionPane.showMessageDialog(null, "Gruppo eliminato con successo");
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
						
					}
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonEliminaGruppo, 5, SpringLayout.SOUTH, buttonModificaGruppo);
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonEliminaGruppo, 5, SpringLayout.EAST, scrollPaneGruppi);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonEliminaGruppo, -10, SpringLayout.EAST, contentPane);
		contentPane.add(buttonEliminaGruppo);
		
		buttonCreaGruppo = new JButton("Crea Gruppo");
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonModificaGruppo, 5, SpringLayout.SOUTH, buttonCreaGruppo);
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonCreaGruppo, 5, SpringLayout.EAST, scrollPaneGruppi);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonCreaGruppo, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonCreaGruppo, 0, SpringLayout.NORTH, scrollPaneGruppi);
		contentPane.add(buttonCreaGruppo);
		
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnIndietro, 0, SpringLayout.SOUTH, scrollPaneGruppi);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnIndietro, 0, SpringLayout.EAST, buttonModificaGruppo);
		contentPane.add(btnIndietro);
	}
}
