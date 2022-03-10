package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Contatto;
import Model.Gruppo;
import Model.Rubrica;

@SuppressWarnings("serial")
public class VisualizzaGruppiFrame extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("unused")
	private JList<Gruppo> listGruppi;
	private JScrollPane scrollPaneGruppi;
	private JButton buttonCreaGruppo;
	private JTable tableGruppi;
	private DefaultTableModel modelGruppi;
	static JFrame frameChiamante;
	static JFrame frame;
	static Controller c;
	static Rubrica r;
	static int rigaDaModificare = -1;
	static String nomeNuovoGruppo = "";
	static ModificaGruppoFrame modificaGruppoFrame;
	

	@SuppressWarnings("static-access")
	public VisualizzaGruppiFrame(Controller controller, JFrame chiamante) {
		
		frame = this;
		frameChiamante = chiamante;
		c = controller;

		
		if (c.getListaGruppi().isEmpty()) {
			try {
				c.dumpListaGruppi();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setResizable(false);
		setTitle("Visualizza gruppi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 280);
		contentPane = new JPanel();
		contentPane.addAncestorListener(new AncestorListener() {
			
			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void ancestorMoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void ancestorAdded(AncestorEvent event) {
				if (contentPane.isShowing()) {
					aggiornaCreaGruppo();
					aggiornaModificaGruppo(rigaDaModificare);
					rigaDaModificare = -1;
				}
				
			}
		});
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
		
		
		 modelGruppi =  new DefaultTableModel() {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		           //all cells false
		           return false;
		        }};
			
		        modelGruppi.addColumn("Nome");
			
			
		// Inserimento nella tabella dei numeri del contatto
			
		ArrayList<Gruppo> listaGruppi = c.getListaGruppi();
	
		
		for (Gruppo gruppo : listaGruppi) {
			modelGruppi.addRow(new Object[] {
					gruppo.getNomeGruppo()
			});
		}
		
		tableGruppi = new JTable(modelGruppi);
		tableGruppi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					int row = tableGruppi.getSelectedRow();
					String nomeGruppo = modelGruppi.getValueAt(row, 0).toString();
					JDialog visualizzaContattiGruppo = new VisualizzaListaContattiGruppoFrame(controller, frame, nomeGruppo);
					//visualizzaContattiGruppo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					visualizzaContattiGruppo.setVisible(true);
					//visualizzaContattiGruppo.setModalityType(ModalityType.TOOLKIT_MODAL);
					
				}
			}
		});
		ListSelectionModel listenerGruppoSelezionato = tableGruppi.getSelectionModel();
		tableGruppi.setSelectionMode(listenerGruppoSelezionato.SINGLE_SELECTION);
		scrollPaneGruppi.setViewportView(tableGruppi);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameChiamante.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		JButton buttonModificaGruppo = new JButton("Modifica Gruppo");
		buttonModificaGruppo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!listenerGruppoSelezionato.isSelectionEmpty()) {
					int row = tableGruppi.getSelectedRow();
					String nomeGruppo = modelGruppi.getValueAt(row, 0).toString();
					modificaGruppoFrame = new ModificaGruppoFrame(c, frame, nomeGruppo);
					rigaDaModificare = row;
					modificaGruppoFrame.setVisible(true);
					frame.setVisible(false);
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonModificaGruppo, 5, SpringLayout.EAST, scrollPaneGruppi);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonModificaGruppo, -10, SpringLayout.EAST, contentPane);
		contentPane.add(buttonModificaGruppo);
		
		JButton buttonEliminaGruppo = new JButton("Elimina Gruppo");
		buttonEliminaGruppo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!listenerGruppoSelezionato.isSelectionEmpty()) {
					int row = tableGruppi.getSelectedRow();
					String nomeGruppo = modelGruppi.getValueAt(row, 0).toString();
					int ris = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare il gruppo "+nomeGruppo+"?", "Message", JOptionPane.OK_CANCEL_OPTION);
					
					if (ris == JOptionPane.OK_OPTION) {
						try {
							c.eliminaGruppo(nomeGruppo);
							modelGruppi.removeRow(row);
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
		buttonCreaGruppo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unused")
				ArrayList<Contatto> listaContattiArrayList = new ArrayList<>();
				listaContattiArrayList = c.getListaContatti();
				JFrame creaGruppo = new CreaGruppoFrame(controller, frame);
				creaGruppo.setVisible(true);
				frame.setVisible(false);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonModificaGruppo, 5, SpringLayout.SOUTH, buttonCreaGruppo);
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonCreaGruppo, 5, SpringLayout.EAST, scrollPaneGruppi);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonCreaGruppo, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonCreaGruppo, 0, SpringLayout.NORTH, scrollPaneGruppi);
		contentPane.add(buttonCreaGruppo);
		
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnIndietro, 0, SpringLayout.SOUTH, scrollPaneGruppi);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnIndietro, 0, SpringLayout.EAST, buttonModificaGruppo);
		contentPane.add(btnIndietro);
	}
	
	void aggiornaCreaGruppo () {
		int lunghezzaArrayListGruppi = c.getListaGruppi().size();
		int lunghrzzaTabella = modelGruppi.getRowCount();
		if (lunghrzzaTabella < lunghezzaArrayListGruppi) {
			modelGruppi.addRow(new Object[] {
					c.getListaGruppi().get(lunghezzaArrayListGruppi-1).getNomeGruppo()
			});
		}
	}
	
	void aggiornaModificaGruppo (int riga) {
		String nomeGruppo;
		if (riga >= 0) {
			nomeNuovoGruppo = modificaGruppoFrame.getNomeNuovo();
			nomeGruppo = modelGruppi.getValueAt(riga, 0).toString();
			if (nomeGruppo.compareTo(nomeNuovoGruppo)!=0) {
				modelGruppi.setValueAt(nomeNuovoGruppo, riga, 0);
			}
			
		}
	}
}
