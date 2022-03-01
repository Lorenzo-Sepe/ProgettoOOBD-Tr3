package GUI;


import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Contatto;
import Model.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;













@SuppressWarnings("serial")
public class Home extends JFrame {

	private JPanel contentPane;
	private JTable RubricaTable;
	Rubrica rubrica;
	Controller c;
	JFrame frame;
	private DefaultTableModel modelloRubrica;
	private String passwordCassaforte = "";
	private boolean isPossibiliModifiche;
	private String frameCheHaModificatoQualcosa;

	/**
	 * Create the frame.
	 */
	public Home(final Controller c) {
		frame=this;
		try {
			c.dumpDati();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("Rubrica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu  mnGestrioneGruppi = new JMenu("Gestione Gruppi");
		menuBar.add(mnGestrioneGruppi);
		
		JMenuItem mntmCreaGruppo = new JMenuItem("Crea Gruppo");
		mntmCreaGruppo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame creaGruppo = new CreaGruppoFrame(c, frame);
				frame.setVisible(false);
				creaGruppo.setVisible(true);
			}
		});
		mnGestrioneGruppi.add(mntmCreaGruppo);
		
		JMenuItem mntmVisualizzaGruppo = new JMenuItem("Visualizza Gruppi");
		mntmVisualizzaGruppo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame visualizzaGruppo = new VisualizzaGruppiFrame(c, frame);
				frame.setVisible(false);
				visualizzaGruppo.setVisible(true);
			}
		});
		mnGestrioneGruppi.add(mntmVisualizzaGruppo);
		
		JMenuItem mntmEliminaGruppo = new JMenuItem("Elimina Gruppo");
		mnGestrioneGruppi.add(mntmEliminaGruppo);
		
		JMenu mnGestioneCassaforti = new JMenu("Gestione Cassaforti");
		menuBar.add(mnGestioneCassaforti);
		
		JMenuItem mntmCreaCassaforte = new JMenuItem("Crea Cassaforte");
		mntmCreaCassaforte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c.getCassaforte()==null) {
					passwordCassaforte = JOptionPane.showInputDialog("Inserire la password");
					if (passwordCassaforte != null) {
						if (passwordCassaforte.compareTo("")!=0) {
							JFrame creaCassaforteFrame = new CreaCassaforteFrame(c, frame, passwordCassaforte);
							creaCassaforteFrame.setVisible(true);
							frame.setVisible(false);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "La cassaforte è già stata creata", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnGestioneCassaforti.add(mntmCreaCassaforte);
		
		JMenuItem mntmVisualizzaCassaforte = new JMenuItem("Visualizza Cassaforti");
		mntmVisualizzaCassaforte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (c.getCassaforte()!=null) {
						String provaPassword = JOptionPane.showInputDialog("Inserire la password");
						if (provaPassword != null) {
							if (provaPassword.compareTo(c.getCassaforte().getPassword())==0) {
								JFrame visualizzaCassaforte = new VisualizzaCassaforteFrame(c, frame);
								visualizzaCassaforte.setVisible(true);
								frame.setVisible(false);
							}
							else if (provaPassword.compareTo("")==0) {
								JOptionPane.showMessageDialog(null, "Password non inserita", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "Password Errata", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "La cassaforte non esiste", "ERROR", JOptionPane.ERROR_MESSAGE);
						// TODO rendilo interattivo
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnGestioneCassaforti.add(mntmVisualizzaCassaforte);
		
		JMenuItem mntmEliminaCassaforte = new JMenuItem("Elimina Cassaforte");
		mntmEliminaCassaforte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c.getCassaforte()!=null) {
					String provaPassword = JOptionPane.showInputDialog("Inserire la password");
					if (provaPassword != null) {
						if (provaPassword.compareTo(c.getCassaforte().getPassword())==0) {
							int result = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare la cassaforte?", "Warning", JOptionPane.OK_CANCEL_OPTION);
							if (result == JOptionPane.OK_OPTION) {
								c.eliminaCassaforte(provaPassword);
								JOptionPane.showMessageDialog(null, "Cassaforte eliminata con successo");
							}
						}
						else if (provaPassword.compareTo("")==0) {
							JOptionPane.showMessageDialog(null, "Password non inserita", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Password Errata", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "La cassaforte non esiste", "ERROR", JOptionPane.ERROR_MESSAGE);
					// TODO rendilo interattivo
				}
			}
		});
		
		JMenuItem mntmModificaContattiCassaforte = new JMenuItem("Modifica Contatti Cassaforte");
		mntmModificaContattiCassaforte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO IMplementare la chiamata al frame per modificare i contatti che ci sono nella cassaforte
				
				isPossibiliModifiche=true;
				frameCheHaModificatoQualcosa="ModificaContattiCassaforteFrame";
				
				
			}
		});
		mnGestioneCassaforti.add(mntmModificaContattiCassaforte);
		mnGestioneCassaforti.add(mntmEliminaCassaforte);
		
		
		
		contentPane = new JPanel();
		contentPane.addAncestorListener(new AncestorListener() {
			
			

			

			@Override
			public void ancestorRemoved(AncestorEvent event) {		}
			
			@Override
			public void ancestorMoved(AncestorEvent event) {			}
			
			@Override
			public void ancestorAdded(AncestorEvent event) {
				System.out.println("Sono nel Acestor salcazzo HOME");
				System.out.println("contentpane:isshowing:  "+contentPane.isShowing());
				if (contentPane.isShowing()) {
					if(isPossibiliModifiche) {
						if(frameCheHaModificatoQualcosa.compareTo("AggiungiContatto")==0&&modelloRubrica.getRowCount()<c.getListaContatti().size()) {
							//aggiunta contatto nella tab di rubrica
							modelloRubrica.addRow(new Object[]{
									c.getListaContatti().get(c.getListaContatti().size()-1).getID(),
									c.getListaContatti().get(c.getListaContatti().size()-1).getPrefissoNome(),
									c.getListaContatti().get(c.getListaContatti().size()-1).getNome(),
									c.getListaContatti().get(c.getListaContatti().size()-1).getCognome()
							});		
							}else if (frameCheHaModificatoQualcosa.compareTo("ModificaContattoFrame")==0) {
								//TODO implementare aggiorna nella modifica contatto
							}else if(frameCheHaModificatoQualcosa.compareTo("ModificaContattiCassaforteFrame")==0) {
								//TODO implementare aggiorna quando un contatto viene aggiunto nella cassaforte
							}
						
						
						//alla fine ripristina isPossibiliModifiche
						  isPossibiliModifiche=false;
				    }
				}
			}

	
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JToolBar toolBar = new JToolBar();
		sl_contentPane.putConstraint(SpringLayout.NORTH, toolBar, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, toolBar, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, toolBar, 35, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, toolBar, 611, SpringLayout.WEST, contentPane);
		contentPane.add(toolBar);
		
		JButton btnAggiungiContatto = new JButton("Aggiungi contatto");
		btnAggiungiContatto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame aggiungiContatto = new AggiungiContatto (c,frame);
				frame.setVisible(false);
				aggiungiContatto.setVisible(true);
				isPossibiliModifiche=true;
				frameCheHaModificatoQualcosa="AggiungiContatto";
				
			}
		});
		btnAggiungiContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/aggiungi.jpg")));
		toolBar.add(btnAggiungiContatto);
		
		JButton btnModificaContatto = new JButton("Modifica Contatto");
		btnModificaContatto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println(RubricaTable.getSelectedRow());
            	int id = (int) RubricaTable.getModel().getValueAt(RubricaTable.getSelectedRow(),0);
            	ModificaContattoFrame newFrame = new ModificaContattoFrame(c, frame, id);
            	frame.setVisible(false);
            	newFrame.setVisible(true);
            	isPossibiliModifiche=true;
            	frameCheHaModificatoQualcosa="ModificaContattoFrame";
			}
		});
		btnModificaContatto.setIcon(new ImageIcon(Home.class.getResource("/immagini/edit.png")));
		toolBar.add(btnModificaContatto);
		
		JButton btnEliminaContatto = new JButton("Elimina Contatto");
		btnEliminaContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/elimina.jpg")));
		toolBar.add(btnEliminaContatto);
		
		JButton btnCercaContatto = new JButton("Cerca Contatto");
		btnCercaContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/ricerca.png")));
		toolBar.add(btnCercaContatto);
		
		JScrollPane scrollPaneRubrica = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneRubrica, 45, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneRubrica, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneRubrica, -15, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneRubrica, -15, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPaneRubrica);
		
		//Rubrica = new JTable();
		 modelloRubrica = new DefaultTableModel () {
			@Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
		}};
		
		RubricaTable = new JTable(modelloRubrica);
		RubricaTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		            if(e.getClickCount()==2) {
		                System.out.println(RubricaTable.getSelectedRow());
		            	int id = (int) RubricaTable.getModel().getValueAt(RubricaTable.getSelectedRow(),0);
		            	VisualizzaContattoFrame newFrame = new VisualizzaContattoFrame(c, frame, id);
		            	frame.setVisible(false);
		            	newFrame.setVisible(true);
		            }
		    }
		});
		ListSelectionModel listenerContattoSelezionato=RubricaTable.getSelectionModel();
		RubricaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelloRubrica.addColumn("Id");
		modelloRubrica.addColumn("prefisso");
		modelloRubrica.addColumn("nome"); 
		modelloRubrica.addColumn("cognome"); 
		
		ArrayList<Contatto>  arrayListContatti = c.getListaContatti();
		
		for (Contatto contatto : arrayListContatti) {
			modelloRubrica.addRow(new Object[]{
					contatto.getID(),
					contatto.getPrefissoNome(),
					contatto.getNome(),
					contatto.getCognome()
			});
		}
		RubricaTable.removeColumn(RubricaTable.getColumnModel().getColumn(0));
		scrollPaneRubrica.setViewportView(RubricaTable);
	}
}
