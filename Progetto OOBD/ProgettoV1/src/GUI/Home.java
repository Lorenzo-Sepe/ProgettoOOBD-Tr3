package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Contatto;
import Model.*;
import GUI.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
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

public class Home extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelloRubrica;
	private JTable RubricaTable;
	Rubrica rubrica;
	Controller c;
	JFrame frame;
	private JButton btnModificaContatto;
	private JMenuItem mntmContattiDuplicati;
	private JMenuItem mntmAccountDuplicati;
	private String passwordCassaforte = "";
	private boolean flagCreaCassaforte = false;
	private boolean flagModificaCassaforte = false;
	private boolean isPossibiliModifiche;
    private String frameCheHaModificatoQualcosa;





	public Home(Controller controller) {
		c = controller;
		frame=this;
		setTitle("Rubrica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 400);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGestrioneGruppi = new JMenu("Gestione Gruppi");
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
		
		JMenuItem mntmModificaGruppo = new JMenuItem("Visualizza Gruppi");
		mntmModificaGruppo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame visualizzaGruppo = new VisualizzaGruppiFrame(c, frame);
				frame.setVisible(false);
				visualizzaGruppo.setVisible(true);
			}
		});
		mnGestrioneGruppi.add(mntmModificaGruppo);
		
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
							flagCreaCassaforte = true;
							JFrame creaCassaforteFrame = new CreaCassaforteFrame(c, frame, passwordCassaforte, modelloRubrica);
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
					e1.printStackTrace();
				}
			}
		});
		mnGestioneCassaforti.add(mntmVisualizzaCassaforte);
		
		JMenuItem mntmModificaContattiCassaforte = new JMenuItem("Modifica Contatti Cassaforte");
		mntmModificaContattiCassaforte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c.getCassaforte()!=null) {
					String provaPassword = JOptionPane.showInputDialog("Inserire la password");
					if (provaPassword != null) {
						if (provaPassword.compareTo(c.getCassaforte().getPassword())==0) {
							flagModificaCassaforte = true;
							JFrame modificaCassaforte = new ModificaCassaforteFrame(c, frame, provaPassword, modelloRubrica);
							modificaCassaforte.setVisible(true);
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
				
			}
		});
		mnGestioneCassaforti.add(mntmModificaContattiCassaforte);
		
		JMenuItem mntmModificaPassword = new JMenuItem("Modifica Password");
		mntmModificaPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c.getCassaforte()!=null) {
					String provaPassword = JOptionPane.showInputDialog("Inserire la password");
					if (provaPassword != null) {
						if (provaPassword.compareTo(c.getCassaforte().getPassword())==0) {
							String nuovaPassword = JOptionPane.showInputDialog("Inserire la nuova password");
							if (nuovaPassword != null) {
								if (nuovaPassword.compareTo(provaPassword) == 0) {
									JOptionPane.showMessageDialog(null, "La nuova password è uguale alla vecchia", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
								else if (nuovaPassword.compareTo("") == 0) {
									JOptionPane.showMessageDialog(null, "Nuova password non inserita", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
								else {
									try {
//										c.transactionBegin();
										try {
											c.cambiaPasswordCassaforte(provaPassword, nuovaPassword);
										} catch (Exception ex) {
											JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
										}
										c.cambiaPasswordCassaforteDB(nuovaPassword);
//										c.transactionCommit();
										JOptionPane.showMessageDialog(null, "Password modificata con successo");
									} catch (SQLException ex) {
										JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
									}
								}
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
		mnGestioneCassaforti.add(mntmModificaPassword);
		
		JMenu mnGestioneDuplicati = new JMenu("Gestione Duplicati");
		menuBar.add(mnGestioneDuplicati);
		
		mntmContattiDuplicati = new JMenuItem("Cerca Contatti Duplicati");
		mntmContattiDuplicati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ArrayList<String>listaRisultati = c.verificaMailDuplicate();				
					JFrame GestioneDuplicatiFrame = new GestioneDuplicatiContattiFrame(c,frame,listaRisultati);
					frame.setVisible(false);
					GestioneDuplicatiFrame.setVisible(true);
			}
		});
		
		mnGestioneDuplicati.add(mntmContattiDuplicati);
		
		mntmAccountDuplicati = new JMenuItem("Cerca Account Duplicati");
		mntmAccountDuplicati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Contatto>listaRisultati = c.verificaDuplicatiAccount();
				JFrame GestioneDuplicatiFrame = new GestioneDuplicatiAccountFrame(c,frame,listaRisultati);
				frame.setVisible(false);
				GestioneDuplicatiFrame.setVisible(true);
			}
		});
		mnGestioneDuplicati.add(mntmAccountDuplicati);
		
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
		
		//Rubrica = new JTable();
				modelloRubrica = new DefaultTableModel () {
					@Override
			        public boolean isCellEditable(int row, int column) {
			           //all cells false
			           return false;
				}};
		
		JToolBar toolBar = new JToolBar();
		sl_contentPane.putConstraint(SpringLayout.NORTH, toolBar, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, toolBar, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, toolBar, 35, SpringLayout.NORTH, contentPane);
		contentPane.add(toolBar);
		
		JButton btnAggiungiContatto = new JButton("Aggiungi contatto");
		btnAggiungiContatto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame aggiungiContatto = new AggiungiContattoFrame (c,frame);
				frame.setVisible(false);
				aggiungiContatto.setVisible(true);
				isPossibiliModifiche=true;
                frameCheHaModificatoQualcosa="AggiungiContatto";
			}
		});
		btnAggiungiContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/aggiungi.jpg")));
		toolBar.add(btnAggiungiContatto);
		
		btnModificaContatto = new JButton("Modifica Contatto");
		btnModificaContatto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println(RubricaTable.getSelectedRow());
            	int id = (int) RubricaTable.getModel().getValueAt(RubricaTable.getSelectedRow(),0);
            	ModificaContattoFrame newFrame = new ModificaContattoFrame(c, frame, id);
            	isPossibiliModifiche=true;
                frameCheHaModificatoQualcosa="ModificaContattoFrame";
                newFrame.setVisible(true);
                frame.setVisible(false);
			}
		});
		btnModificaContatto.setIcon(new ImageIcon(Home.class.getResource("/immagini/edit.png")));
		toolBar.add(btnModificaContatto);
		
		JButton btnEliminaContatto = new JButton("Elimina Contatto");
		btnEliminaContatto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = RubricaTable.getSelectedRow();
				if (row>0) {
					if (JOptionPane.showConfirmDialog(null, "Sicuro di voler cancellare il contatto?", "WARNING", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						int id = (int) modelloRubrica.getValueAt(row, 0);
						try {
							c.eliminaContattoDB(id);
							modelloRubrica.removeRow(row);
							JOptionPane.showMessageDialog(null, "Contatto eliminato con successo");
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace(); //TODO toglierlo
						}
					}
					
				}
			}
		});
		btnEliminaContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/elimina.jpg")));
		toolBar.add(btnEliminaContatto);
		
		JButton btnCercaContatto = new JButton("Cerca Contatto");
		btnCercaContatto.setIcon(new ImageIcon(Home.class.getResource("/Immagini/ricerca.png")));
		toolBar.add(btnCercaContatto);
		
		JScrollPane scrollPaneRubrica = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.EAST, toolBar, 0, SpringLayout.EAST, scrollPaneRubrica);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneRubrica, 45, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneRubrica, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneRubrica, -15, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneRubrica, -15, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPaneRubrica);
		
		
		
		RubricaTable = new JTable(modelloRubrica);
		RubricaTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		            if(e.getClickCount()==2) {
		               // System.out.println(RubricaTable.getSelectedRow());
		            	int id = (int) RubricaTable.getModel().getValueAt(RubricaTable.getSelectedRow(),0);
		            	VisualizzaContattoFrame newFrame = new VisualizzaContattoFrame(c, frame, id);
		            	newFrame.setVisible(true);
		            	frame.setVisible(false);
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
		
		for(int i=0;i<arrayListContatti.size();i++) {
			modelloRubrica.addRow(new Object[]{
					arrayListContatti.get(i).getID(),
					arrayListContatti.get(i).getPrefissoNome(),
					arrayListContatti.get(i).getNome(),
					arrayListContatti.get(i).getCognome()
			});
		}
		RubricaTable.removeColumn(RubricaTable.getColumnModel().getColumn(0));
		scrollPaneRubrica.setViewportView(RubricaTable);
	}
	
//	void aggiornaDopoCreazioneCassaforte () {
//		ArrayList<Contatto> contattiInCassaforte = new ArrayList<>(c.getContattiCassaforte());
//		for (Contatto contatto : contattiInCassaforte) {
//			for (int i = 0; i < modelloRubrica.getRowCount() ; i++) {
//				if ((int)modelloRubrica.getValueAt(i, 0) == contatto.getID()) {
//					modelloRubrica.removeRow(i);
//				}
//			}
//		}
//		flagCreaCassaforte = false;
//	}
//	
//	void aggiornaDopoModificaCassaforte () {	// TODO vedere con Raimondo
//		ArrayList<Contatto> contattiInCassaforte = new ArrayList<>(c.getContattiCassaforte());
//		for (Contatto contatto : contattiInCassaforte) {
//			for (int i = 0; i < modelloRubrica.getRowCount() ; i++) {
//				if ((int)modelloRubrica.getValueAt(i, 0) == contatto.getID()) {
//					modelloRubrica.removeRow(i);
//				}
//			}
//		}
//		ArrayList<Contatto> listaContatti = new ArrayList<>(c.getListaContatti());
//		for (int i = 0; i < listaContatti.size(); i++) {
//			if (listaContatti.get(i).getID() != (int)modelloRubrica.getValueAt(i, 0)) {
//				modelloRubrica.insertRow(i, new Object[] {
//						listaContatti.get(i).getID(),
//						listaContatti.get(i).getPrefissoNome(),
//						listaContatti.get(i).getNome(),
//						listaContatti.get(i).getCognome()
//				});
//			}
//			System.out.println(listaContatti.get(i).StampaContatto());
//			System.out.println(modelloRubrica.getValueAt(i, 0));
//		}
//		flagModificaCassaforte = false;
//	}
}