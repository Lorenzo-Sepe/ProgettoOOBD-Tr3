package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.JScrollPane;
//import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.io.File;
import java.util.ArrayList;
import javax.swing.SpringLayout;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;

import Controller.Controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;


@SuppressWarnings({  "serial" })
public class AggiungiContatto extends JFrame {
	private JPanel contentPane;
	private JTable tableIndirizzi;
	private JTable tableAccounts;
	private JTable tableNumeri;
	/** Il Controller*/
	 Controller c;
	/**L'id del contatto visualizzatp*/
	/**Il Chiamante	 */
	 JFrame frameChiamante;
	 JFrame frame;
	private JPanel panelFoto;
	private JLabel labelFoto;
	private JScrollPane scrollPaneNumeri;
	private JPanel panelNumeri;
	private JPanel panelIndirizzi;
	private JPanel panelAccount;
	private JLabel labelMail;
	private JLabel labelPrefisso;
	private JLabel labelNome;
	private JLabel labelCognome;
	private JButton modFotoButton;
	private JTextPane textPaneCognome;
	private JTextPane textPaneNome;
	private JTextPane textPanePrefisso;
	private JButton buttonIndietro;
	private JPanel panel;
	private File fileFoto;
	private JButton btnAggiungiNumero;
	private JButton btnEliminaNumero;
	private JButton btnModificaNumero;
	private JButton btnAggiungiIndirizzo;
	private JButton btnEliminaIndirizzo;
	private JButton btnModificaIndirizzo;
	private JButton btnAggiungiContatto;
	private JButton btnEliminaAccount;
	private JButton btnModificaAccount;
	
	private ArrayList<String> listaNumeri = new ArrayList<String>(); 
	private ArrayList<String> listaMail = new ArrayList<String>(); 
	private String pathFoto;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AggiungiContatto frame = new AggiungiContatto(c, frameChiamante);
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
	
	public AggiungiContatto(Controller controller, JFrame Chiamante) {
		frame = this;
		c=controller;
		frameChiamante=Chiamante;
		setTitle("Visualizzazione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelFoto = new JPanel();
		panelFoto.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		labelCognome = new JLabel("Cognome");
		
		labelNome = new JLabel("Nome");
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelNome, 67, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelNome, 250, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelNome, 365, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelCognome, 97, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelCognome, 250, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelCognome, 365, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, panelFoto, 35, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panelFoto, 35, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panelFoto, 185, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panelFoto, 185, SpringLayout.WEST, contentPane);
		contentPane.setLayout(sl_contentPane);
		
		//TODO una volta che il controller è completo aggiungere getter per prendere nome cognome e prefisso
		
		textPanePrefisso = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPanePrefisso, 35, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPanePrefisso, 375, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPanePrefisso, -200, SpringLayout.EAST, contentPane);
		
		contentPane.add(textPanePrefisso);
		
		textPaneNome = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPaneNome, 65, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPaneNome, 375, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPaneNome, -200, SpringLayout.EAST, contentPane);
		contentPane.add(textPaneNome);
		
		textPaneCognome = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPaneCognome, 95, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPaneCognome, 375, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPaneCognome, -200, SpringLayout.EAST, contentPane);
		contentPane.add(textPaneCognome);
		


		
		
		// Inserimento nella tabella dei numeri del contatto
		
		
		
		
		modFotoButton = new JButton("Modifica Foto");
		sl_contentPane.putConstraint(SpringLayout.NORTH, modFotoButton, 195, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, modFotoButton, 35, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, modFotoButton, 185, SpringLayout.WEST, contentPane);
		
		contentPane.add(modFotoButton);
		contentPane.add(panelFoto);
	
		panelFoto.setLayout(null);
		
		//TODO modifica dimensioni della foto
		labelFoto = new JLabel("");
		 //c.getImageModificata(150, 150, new File ( AggiungiContatto.class.getResource("/Immagini/NoImage.jpg")) )
		labelFoto.setIcon(new ImageIcon(AggiungiContatto.class.getResource("/Immagini/NoImage.jpg")));
		labelFoto.setBounds(0, 0, 150, 150);
		panelFoto.add(labelFoto);
		
		
		contentPane.add(labelCognome);
		contentPane.add(labelNome);
		
		
		final DefaultTableModel modelIndirizzi =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
	        
	    modelIndirizzi.addColumn("Tag");
		modelIndirizzi.addColumn("Via"); 
		modelIndirizzi.addColumn("Città"); 
		modelIndirizzi.addColumn("Codice Postale");
		modelIndirizzi.addColumn("Nazione");
		
		// Inserimento degli indirizzi del contatto
	
		//tableAccounts
		final DefaultTableModel modelAccounts =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
	
		modelAccounts.addColumn("Fornitore"); 
		modelAccounts.addColumn("Nickname"); 
		modelAccounts.addColumn("E-Mail");
		modelAccounts.addColumn("Frase di Benvenuto");
		
		// Inserimento account del contatto
		
		
		labelPrefisso = new JLabel("Prefisso Nome");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelPrefisso, 37, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelPrefisso, 250, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelPrefisso, 365, SpringLayout.WEST, contentPane);
		labelPrefisso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(labelPrefisso);
		
		labelMail = new JLabel("E-Mails");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelMail, 127, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelMail, 250, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelMail, 365, SpringLayout.WEST, contentPane);
		contentPane.add(labelMail);
		
		final JComboBox<String> comboBoxMail = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxMail, -4, SpringLayout.NORTH, labelMail);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxMail, 0, SpringLayout.WEST, textPanePrefisso);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxMail, 0, SpringLayout.EAST, textPanePrefisso);
		contentPane.add(comboBoxMail);
				
		panelNumeri = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.WEST, panelNumeri, 235, SpringLayout.WEST, contentPane);
		contentPane.add(panelNumeri);
		SpringLayout sl_panelNumeri = new SpringLayout();
		panelNumeri.setLayout(sl_panelNumeri);
		
		JLabel labelNumeriTelefono = new JLabel("Numeri di Telefono");
		sl_panelNumeri.putConstraint(SpringLayout.NORTH, labelNumeriTelefono, 0, SpringLayout.NORTH, panelNumeri);
		sl_panelNumeri.putConstraint(SpringLayout.WEST, labelNumeriTelefono, 10, SpringLayout.WEST, panelNumeri);
		sl_panelNumeri.putConstraint(SpringLayout.EAST, labelNumeriTelefono, 125, SpringLayout.WEST, panelNumeri);
		panelNumeri.add(labelNumeriTelefono);
		
		scrollPaneNumeri = new JScrollPane();
		sl_panelNumeri.putConstraint(SpringLayout.NORTH, scrollPaneNumeri, 0, SpringLayout.NORTH, panelNumeri);
		sl_panelNumeri.putConstraint(SpringLayout.WEST, scrollPaneNumeri, 140, SpringLayout.WEST, panelNumeri);
		sl_panelNumeri.putConstraint(SpringLayout.SOUTH, scrollPaneNumeri, 0, SpringLayout.SOUTH, panelNumeri);
		sl_panelNumeri.putConstraint(SpringLayout.EAST, scrollPaneNumeri, 0, SpringLayout.EAST, panelNumeri);
		panelNumeri.add(scrollPaneNumeri);
		
		//tableNumeri = new JTable(modelNumeri);
		final DefaultTableModel modelloNumeri = new DefaultTableModel() {
			@Override
			 public boolean isCellEditable(int row, int column) {
		           //all cells false
		           return false;
			 }
		};
		
		tableNumeri = new JTable(modelloNumeri);
		
		modelloNumeri.addColumn("tag");
		modelloNumeri.addColumn("prefisso");
		modelloNumeri.addColumn("numero");
		modelloNumeri.addColumn("tipo");
		
		
		final ListSelectionModel listenerNumeri=tableNumeri.getSelectionModel();
		tableNumeri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		scrollPaneNumeri.setViewportView(tableNumeri);
		
		btnAggiungiNumero = new JButton("Aggiungi");
		
		sl_panelNumeri.putConstraint(SpringLayout.NORTH, btnAggiungiNumero, 5, SpringLayout.SOUTH, labelNumeriTelefono);
		sl_panelNumeri.putConstraint(SpringLayout.WEST, btnAggiungiNumero, 0, SpringLayout.WEST, labelNumeriTelefono);
		btnAggiungiNumero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AggiungiNumeroPanel panelAggiungiNumero = new AggiungiNumeroPanel();
                int result = JOptionPane.showConfirmDialog(null, panelAggiungiNumero, "Inserisci Numero", JOptionPane.OK_CANCEL_OPTION);
                try {
                    if (result == JOptionPane.OK_OPTION) {
//                    	c.checkFormNumero(panelAggiungiNumero.getPrefisso(), panelAggiungiNumero.getNumero());
                    	c.checkFormNumero(panelAggiungiNumero.getPrefisso(), panelAggiungiNumero.getNumero(), listaNumeri);
                    	modelloNumeri.addRow(new Object[]{
                    			panelAggiungiNumero.getTag(),
                    			panelAggiungiNumero.getPrefisso(),
                    			panelAggiungiNumero.getNumero(),
                    			panelAggiungiNumero.getTipo()
                        });
                    	listaNumeri.add(panelAggiungiNumero.getPrefisso()+panelAggiungiNumero.getNumero());
                    }
                }
                catch (Exception ex) {
                	ex.printStackTrace();
                	JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		sl_panelNumeri.putConstraint(SpringLayout.EAST, btnAggiungiNumero, -10, SpringLayout.EAST, labelNumeriTelefono);
		panelNumeri.add(btnAggiungiNumero);
		
		btnEliminaNumero = new JButton("Elimina");
		sl_panelNumeri.putConstraint(SpringLayout.NORTH, btnEliminaNumero, 5, SpringLayout.SOUTH, btnAggiungiNumero);
		sl_panelNumeri.putConstraint(SpringLayout.WEST, btnEliminaNumero, 0, SpringLayout.WEST, labelNumeriTelefono);
		btnEliminaNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_panelNumeri.putConstraint(SpringLayout.EAST, btnEliminaNumero, -25, SpringLayout.WEST, scrollPaneNumeri);
		btnEliminaNumero.addMouseListener(new MouseAdapter() { 
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				if (!listenerNumeri.isSelectionEmpty()) {
					row = tableNumeri.getSelectedRow();
					String numeroDaEliminare = modelloNumeri.getValueAt(row, 1).toString()+modelloNumeri.getValueAt(row, 2).toString();
					listaNumeri.remove(numeroDaEliminare);
					modelloNumeri.removeRow(row);
				}
			}
		});
		panelNumeri.add(btnEliminaNumero);
		
		btnModificaNumero = new JButton("Modifica");
		sl_panelNumeri.putConstraint(SpringLayout.NORTH, btnModificaNumero, 5, SpringLayout.SOUTH, btnEliminaNumero);
		sl_panelNumeri.putConstraint(SpringLayout.WEST, btnModificaNumero, 0, SpringLayout.WEST, labelNumeriTelefono);
		sl_panelNumeri.putConstraint(SpringLayout.EAST, btnModificaNumero, -25, SpringLayout.WEST, scrollPaneNumeri);
		btnModificaNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listenerNumeri.isSelectionEmpty()) {
					int row =tableNumeri.getSelectedRow();
	                int column=0;
	                AggiungiNumeroPanel panelModificaNumero = new AggiungiNumeroPanel();
	                panelModificaNumero.setAll(modelloNumeri.getValueAt(row, column++).toString(), modelloNumeri.getValueAt(row, column++).toString(), modelloNumeri.getValueAt(row, column++).toString(), modelloNumeri.getValueAt(row, column++).toString());

	                int result = JOptionPane.showConfirmDialog(null, panelModificaNumero, "Modifica Numero",  JOptionPane.OK_CANCEL_OPTION);

	                if (result == JOptionPane.OK_OPTION) {
	                	try {
	                		
	                		c.checkFormNumeroModifica(panelModificaNumero.getPrefisso(), panelModificaNumero.getName(), listaNumeri);
	                		
	                		String numeroDaAggiungere = panelModificaNumero.getPrefisso()+panelModificaNumero.getNumero(); 
	                		String numeroDaSostituire = modelloNumeri.getValueAt(row, 1).toString()+modelloNumeri.getValueAt(row, 2).toString();
	                		listaNumeri.set(listaNumeri.indexOf(numeroDaSostituire), numeroDaAggiungere);
		                    column=0;
		                    modelloNumeri.setValueAt(panelModificaNumero.getTag(), row, column++);
		                    modelloNumeri.setValueAt(panelModificaNumero.getPrefisso(), row, column++);
		                    modelloNumeri.setValueAt(panelModificaNumero.getNumero(), row, column++);
		                    modelloNumeri.setValueAt(panelModificaNumero.getTipo(), row, column++);
	                	}
	                	catch (Exception ex) {
	                		JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);
						}
	                }
				}
			}
		});
		panelNumeri.add(btnModificaNumero);
				
				panelIndirizzi = new JPanel();
				sl_contentPane.putConstraint(SpringLayout.NORTH, panelNumeri, -135, SpringLayout.NORTH, panelIndirizzi);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, panelNumeri, -5, SpringLayout.NORTH, panelIndirizzi);
				sl_contentPane.putConstraint(SpringLayout.EAST, panelNumeri, 0, SpringLayout.EAST, panelIndirizzi);
				sl_contentPane.putConstraint(SpringLayout.WEST, panelIndirizzi, 235, SpringLayout.WEST, contentPane);
				contentPane.add(panelIndirizzi);
				SpringLayout sl_panelIndirizzi = new SpringLayout(); 
				panelIndirizzi.setLayout(sl_panelIndirizzi);
				
				JLabel labelIndirizzi = new JLabel("Indirizzi");
				sl_panelIndirizzi.putConstraint(SpringLayout.NORTH, labelIndirizzi, 0, SpringLayout.NORTH, panelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.WEST, labelIndirizzi, 10, SpringLayout.WEST, panelIndirizzi);
				panelIndirizzi.add(labelIndirizzi);
				
				JScrollPane scrollPaneIndirizzi = new JScrollPane();
				sl_panelIndirizzi.putConstraint(SpringLayout.NORTH, scrollPaneIndirizzi, 0, SpringLayout.NORTH, panelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.WEST, scrollPaneIndirizzi, 140, SpringLayout.WEST, panelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.SOUTH, scrollPaneIndirizzi, 0, SpringLayout.SOUTH, panelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.EAST, scrollPaneIndirizzi, 0, SpringLayout.EAST, panelIndirizzi);
				panelIndirizzi.add(scrollPaneIndirizzi);
				
				tableIndirizzi = new JTable(modelIndirizzi);
				final ListSelectionModel listenerIndirizzi=tableIndirizzi.getSelectionModel();
				tableIndirizzi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPaneIndirizzi.setViewportView(tableIndirizzi);
				
				btnAggiungiIndirizzo = new JButton("Aggiungi");
				sl_panelIndirizzi.putConstraint(SpringLayout.NORTH, btnAggiungiIndirizzo, 5, SpringLayout.SOUTH, labelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.WEST, btnAggiungiIndirizzo, 0, SpringLayout.WEST, labelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.SOUTH, btnAggiungiIndirizzo, 25, SpringLayout.SOUTH, labelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.EAST, btnAggiungiIndirizzo, -20, SpringLayout.WEST, scrollPaneIndirizzi);
				btnAggiungiIndirizzo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						AggiungiIndirizzoPanel panelAggiungiIndirizzo = new AggiungiIndirizzoPanel();
						int result = JOptionPane.showConfirmDialog(null, panelAggiungiIndirizzo, "Inserisci Indirizzo", JOptionPane.OK_CANCEL_OPTION);
						
						if (result == JOptionPane.OK_OPTION) {
							try {
								c.checkFormIndirizzo(panelAggiungiIndirizzo.getCodicePostale());
								modelIndirizzi.addRow(new Object[] {
										panelAggiungiIndirizzo.getTag(),
										panelAggiungiIndirizzo.getVia(),
										panelAggiungiIndirizzo.getCittà(),
										panelAggiungiIndirizzo.getCodicePostale(),
										panelAggiungiIndirizzo.getNazione(),
								});
							}
							catch (Exception ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				panelIndirizzi.add(btnAggiungiIndirizzo);
				
				btnEliminaIndirizzo = new JButton("Elimina");
				btnEliminaIndirizzo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				sl_panelIndirizzi.putConstraint(SpringLayout.NORTH, btnEliminaIndirizzo, 5, SpringLayout.SOUTH, btnAggiungiIndirizzo);
				sl_panelIndirizzi.putConstraint(SpringLayout.WEST, btnEliminaIndirizzo, 0, SpringLayout.WEST, labelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.EAST, btnEliminaIndirizzo, -20, SpringLayout.WEST, scrollPaneIndirizzi);
				btnEliminaIndirizzo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row;
						if (!listenerIndirizzi.isSelectionEmpty()) {
							row = tableIndirizzi.getSelectedRow();
							modelIndirizzi.removeRow(row);
						}
					}
				});
				panelIndirizzi.add(btnEliminaIndirizzo);
				
				btnModificaIndirizzo = new JButton("Modifica");
				sl_panelIndirizzi.putConstraint(SpringLayout.NORTH, btnModificaIndirizzo, 5, SpringLayout.SOUTH, btnEliminaIndirizzo);
				sl_panelIndirizzi.putConstraint(SpringLayout.WEST, btnModificaIndirizzo, 0, SpringLayout.WEST, labelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.EAST, btnModificaIndirizzo, -20, SpringLayout.WEST, scrollPaneIndirizzi);
				btnModificaIndirizzo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (!listenerIndirizzi.isSelectionEmpty()) {
							int row = tableIndirizzi.getSelectedRow();
							int column = 0;
							AggiungiIndirizzoPanel panelModificaIndirizzo = new AggiungiIndirizzoPanel();
							panelModificaIndirizzo.setAll(modelIndirizzi.getValueAt(row, column++).toString(), modelIndirizzi.getValueAt(row, column++).toString(), modelIndirizzi.getValueAt(row, column++).toString(), modelIndirizzi.getValueAt(row, column++).toString(), modelIndirizzi.getValueAt(row, column++).toString());
							int result = JOptionPane.showConfirmDialog(null, panelModificaIndirizzo, "Modifica Indirizzo", JOptionPane.OK_CANCEL_OPTION);
							
							if (result == JOptionPane.OK_OPTION) {
								column = 0;
								 modelIndirizzi.setValueAt(panelModificaIndirizzo.getTag(), row, column++);
								 modelIndirizzi.setValueAt(panelModificaIndirizzo.getVia(), row, column++);
								 modelIndirizzi.setValueAt(panelModificaIndirizzo.getCittà(), row, column++);
								 modelIndirizzi.setValueAt(panelModificaIndirizzo.getCodicePostale(), row, column++);
								 modelIndirizzi.setValueAt(panelModificaIndirizzo.getNazione(), row, column++);
							}
						}
					}
				});
				panelIndirizzi.add(btnModificaIndirizzo);
				
				panelAccount = new JPanel();
				sl_contentPane.putConstraint(SpringLayout.NORTH, panelIndirizzi, -135, SpringLayout.NORTH, panelAccount);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, panelIndirizzi, -5, SpringLayout.NORTH, panelAccount);
				sl_contentPane.putConstraint(SpringLayout.EAST, panelIndirizzi, 0, SpringLayout.EAST, panelAccount);
				sl_contentPane.putConstraint(SpringLayout.NORTH, panelAccount, -160, SpringLayout.SOUTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.WEST, panelAccount, 235, SpringLayout.WEST, contentPane);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, panelAccount, -30, SpringLayout.SOUTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.EAST, panelAccount, -10, SpringLayout.EAST, contentPane);
				contentPane.add(panelAccount);
				SpringLayout sl_panelAccount = new SpringLayout();
				panelAccount.setLayout(sl_panelAccount);
				
				
				JLabel labelAccounts = new JLabel("Accounts");
				sl_panelAccount.putConstraint(SpringLayout.NORTH, labelAccounts, 0, SpringLayout.NORTH, panelAccount);
				sl_panelAccount.putConstraint(SpringLayout.WEST, labelAccounts, 10, SpringLayout.WEST, panelAccount);
				panelAccount.add(labelAccounts);
				labelAccounts.setFont(new Font("Tahoma", Font.PLAIN, 11));
				
				
				JScrollPane scrollPaneAccounts = new JScrollPane();
				sl_panelAccount.putConstraint(SpringLayout.EAST, labelAccounts, -20, SpringLayout.WEST, scrollPaneAccounts);
				sl_panelAccount.putConstraint(SpringLayout.NORTH, scrollPaneAccounts, 0, SpringLayout.NORTH, panelAccount);
				sl_panelAccount.putConstraint(SpringLayout.WEST, scrollPaneAccounts, 140, SpringLayout.WEST, panelAccount);
				sl_panelAccount.putConstraint(SpringLayout.SOUTH, scrollPaneAccounts, 0, SpringLayout.SOUTH, panelAccount);
				sl_panelAccount.putConstraint(SpringLayout.EAST, scrollPaneAccounts, 0, SpringLayout.EAST, panelAccount);
				panelAccount.add(scrollPaneAccounts);
				
				tableAccounts = new JTable(modelAccounts);
				final ListSelectionModel listenerAccount = tableAccounts.getSelectionModel();
				tableAccounts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
						scrollPaneAccounts.setViewportView(tableAccounts);
						
						btnAggiungiContatto = new JButton("Aggiungi");
						sl_panelAccount.putConstraint(SpringLayout.WEST, btnAggiungiContatto, 0, SpringLayout.WEST, labelAccounts);
						btnAggiungiContatto.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								AggiungiAccountPanel panelAggiungiAccount = new AggiungiAccountPanel(listaMail);
								int result = JOptionPane.showConfirmDialog(null, panelAggiungiAccount, "Aggiungi Account", JOptionPane.OK_CANCEL_OPTION);
								
								if (result == JOptionPane.OK_OPTION) {
									modelAccounts.addRow(new Object[] {
											panelAggiungiAccount.getFornitore(),
											panelAggiungiAccount.getNickname(),
											panelAggiungiAccount.getMail(),
											panelAggiungiAccount.getFraseDiBenvenuto()
									});
								}
							}
						});
						sl_panelAccount.putConstraint(SpringLayout.NORTH, btnAggiungiContatto, 6, SpringLayout.SOUTH, labelAccounts);
						sl_panelAccount.putConstraint(SpringLayout.EAST, btnAggiungiContatto, 0, SpringLayout.EAST, labelAccounts);
						panelAccount.add(btnAggiungiContatto);
						
						btnEliminaAccount = new JButton("Elimina");
						sl_panelAccount.putConstraint(SpringLayout.NORTH, btnEliminaAccount, 5, SpringLayout.SOUTH, btnAggiungiContatto);
						sl_panelAccount.putConstraint(SpringLayout.WEST, btnEliminaAccount, 0, SpringLayout.WEST, labelAccounts);
						sl_panelAccount.putConstraint(SpringLayout.EAST, btnEliminaAccount, -20, SpringLayout.WEST, scrollPaneAccounts);
						btnEliminaAccount.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								int row;
								if (!listenerAccount.isSelectionEmpty()) {
									row = tableAccounts.getSelectedRow();
									modelAccounts.removeRow(row);
								}
							}
						});
						panelAccount.add(btnEliminaAccount);
						
						btnModificaAccount = new JButton("Modifica");
						sl_panelAccount.putConstraint(SpringLayout.NORTH, btnModificaAccount, 5, SpringLayout.SOUTH, btnEliminaAccount);
						sl_panelAccount.putConstraint(SpringLayout.WEST, btnModificaAccount, 0, SpringLayout.WEST, labelAccounts);
						sl_panelAccount.putConstraint(SpringLayout.EAST, btnModificaAccount, -20, SpringLayout.WEST, scrollPaneAccounts);
						btnModificaAccount.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (!listenerAccount.isSelectionEmpty()) {
									int row = tableAccounts.getSelectedRow();
									int column = 0;
									AggiungiAccountPanel panelModificaAccount = new AggiungiAccountPanel(listaMail);
									panelModificaAccount.setAll(modelAccounts.getValueAt(row, column++).toString(), modelAccounts.getValueAt(row, column++).toString(),modelAccounts.getValueAt(row, column++).toString(), modelAccounts.getValueAt(row, column++).toString());
									int results = JOptionPane.showConfirmDialog(null, panelModificaAccount,"Modifica Account",JOptionPane.OK_CANCEL_OPTION);
									
									if (results == JOptionPane.OK_OPTION) {
										column = 0;
										modelAccounts.setValueAt(panelModificaAccount.getFornitore(), row, column++);
										modelAccounts.setValueAt(panelModificaAccount.getNickname(), row, column++);
										modelAccounts.setValueAt(panelModificaAccount.getMail(), row, column++);
										modelAccounts.setValueAt(panelModificaAccount.getFraseDiBenvenuto(), row, column++);
									}
								}
							}
						});
						panelAccount.add(btnModificaAccount);
						
						buttonIndietro = new JButton("Indietro");
						sl_contentPane.putConstraint(SpringLayout.NORTH, buttonIndietro, -25, SpringLayout.SOUTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonIndietro, 0, SpringLayout.SOUTH, contentPane);
						buttonIndietro.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								//TODO serve per cambiare da un frame ad un'altro
								frameChiamante.setVisible(true);
								frame.setVisible(false);
								frame.dispose();
							}
						});
						contentPane.add(buttonIndietro);
						
						panel = new JPanel();
						sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 344, SpringLayout.NORTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.WEST, panel, 5, SpringLayout.WEST, contentPane);
						contentPane.add(panel);
						
						JButton btnConfirm = new JButton("Conferma");
						sl_contentPane.putConstraint(SpringLayout.NORTH, btnConfirm, -25, SpringLayout.SOUTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, btnConfirm, 0, SpringLayout.SOUTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.WEST, buttonIndietro, -160, SpringLayout.WEST, btnConfirm);
						sl_contentPane.putConstraint(SpringLayout.EAST, buttonIndietro, -10, SpringLayout.WEST, btnConfirm);
						sl_contentPane.putConstraint(SpringLayout.WEST, btnConfirm, -150, SpringLayout.EAST, panelAccount);
						sl_contentPane.putConstraint(SpringLayout.EAST, btnConfirm, 0, SpringLayout.EAST, panelNumeri);
						btnConfirm.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								
								SetIndirizzoPrincipale pannelSettaIndizzoPrincipale;
								ArrayList<String> listaTipi = new ArrayList<String>();
								int row=0;//variabile contattore per gestire tutti i vari for
								int tastoCliccatoNeiDialog=0; // tasto cllicato nei JDialo
								int indexDelIndirizzoPrincipale=-1;
								//check Almeno Due Numeri Con Tipo Diverso
								for ( row= 0; row < modelloNumeri.getRowCount(); row++) {
									listaTipi.add(modelloNumeri.getValueAt(row, 3).toString());
								}
								
								try {
									c.checkAlmenoDueNumeriConTipoDiverso(listaTipi);
									
									SetDeputatoPanel pannelSettaDeputati = new SetDeputatoPanel(modelloNumeri);
									
									//Controllo se ci sono righe nella tabella indirizzi
									if(modelIndirizzi.getRowCount() >1) {
										//Richiesta di selezione indirizzo principale
										 pannelSettaIndizzoPrincipale = new SetIndirizzoPrincipale(modelIndirizzi); 
										tastoCliccatoNeiDialog= JOptionPane.showConfirmDialog(null,
												pannelSettaIndizzoPrincipale, "Seleziona Indirizzo principale",
												JOptionPane.OK_CANCEL_OPTION);
										if(tastoCliccatoNeiDialog!=JOptionPane.OK_OPTION) {
											throw new Exception( "Per aggiungere un contatto serve che scegliate un indirizzo principale");
										}
											indexDelIndirizzoPrincipale=pannelSettaIndizzoPrincipale.getIndexIndirizzoPrincipale();
										
									}
										//richiesta di selezionamento deputati
									
									tastoCliccatoNeiDialog= JOptionPane.showConfirmDialog(null,
											pannelSettaDeputati, "Seleziona i Deputati",
											JOptionPane.OK_OPTION);
										
									if(tastoCliccatoNeiDialog!=JOptionPane.OK_OPTION) {
										throw new Exception("Per aggiungere un numero deve avere un Deputato ");
									}
									// Load anagrafie di contatto
									int idContatto = c.aggiungiContatto(textPanePrefisso.getText(), textPaneNome.getText(),
											textPaneCognome.getText(), pathFoto);

									// Load Numeri nel Database
									for (int i = 0; i < modelloNumeri.getRowCount(); i++) {
										c.aggiungiNumero(idContatto, modelloNumeri.getValueAt(i, 0).toString(),
												modelloNumeri.getValueAt(i, 1).toString(),
												modelloNumeri.getValueAt(i, 2).toString(),
												modelloNumeri.getValueAt(i, 3).toString());
									}
									// Load Dei deputati per ogni Numero
									String tipoDelDeputato ;
									
									for (int i = 0; i < modelloNumeri.getRowCount(); i++) {
										System.out.println("ceck if  "+modelloNumeri.getValueAt(i , 3).toString().compareToIgnoreCase("Fisso") );
										if (modelloNumeri.getValueAt(i , 3).toString().compareToIgnoreCase("Fisso") == 0)
											tipoDelDeputato = "Mobile";
										else tipoDelDeputato = "Fisso";
										
										System.out.println("pref 1:-"+modelloNumeri.getValueAt(i, 1).toString()
												+"\nnum 1:-"+modelloNumeri.getValueAt(i, 2).toString()
												+"\ntipo 1:-"+modelloNumeri.getValueAt(i, 3).toString()
												+"\npref 2:-"+pannelSettaDeputati.getPrefissoDeputato(i)
												+"\nnum 2:-"+pannelSettaDeputati.getNumeroDeputato(i)
												+"\ntipo2:-"+tipoDelDeputato); 
										c.setDeputato(idContatto, modelloNumeri.getValueAt(i, 1).toString(),
												modelloNumeri.getValueAt(i, 2).toString(),
												modelloNumeri.getValueAt(i, 3).toString(),
												pannelSettaDeputati.getPrefissoDeputato(i),
												pannelSettaDeputati.getNumeroDeputato(i), tipoDelDeputato);
									}
									// Load delle e-mail nel Database
									for (int i = 0; i < comboBoxMail.getItemCount(); i++) {
										c.aggiungiMail(idContatto, comboBoxMail.getItemAt(i));
									}
									// Load degli indirizzi e setta indirizzo principale
									String tagIndirizzo=null;
									String viaIndirizzo=null;
									String cittaIndirizzo=null;
									String codicePostaleIndirizzo=null;
									String nazioneIndirizzo = null;
									for (int i = 0; i < modelIndirizzi.getRowCount(); i++) {
										tagIndirizzo= modelIndirizzi.getValueAt(i, 0).toString();
										viaIndirizzo= modelIndirizzi.getValueAt(i, 1).toString();
										cittaIndirizzo= modelIndirizzi.getValueAt(i, 2).toString();
										codicePostaleIndirizzo= modelIndirizzi.getValueAt(i, 3).toString();
										nazioneIndirizzo= modelIndirizzi.getValueAt(i, 4).toString();
										
										if (indexDelIndirizzoPrincipale == i)
											c.aggiungiIndirizzo(idContatto, tagIndirizzo, viaIndirizzo, cittaIndirizzo, codicePostaleIndirizzo, nazioneIndirizzo, true);
										else
											c.aggiungiIndirizzo(idContatto, tagIndirizzo, viaIndirizzo, cittaIndirizzo, codicePostaleIndirizzo, nazioneIndirizzo, false);

									}
									// Load degli account
									
									for (int i = 0; i < modelAccounts.getRowCount(); i++) {
										//	public int  aggiungiAccount(int id,String nickname,String fornitore,String benvenuto, String email) {
										String nicknameAccount= modelAccounts.getValueAt(i, 0).toString();
										String fornitoreAccount =  modelAccounts.getValueAt(i, 1).toString();
										String email=  modelAccounts.getValueAt(i, 2).toString();
										String benvenutoAccount =  modelAccounts.getValueAt(i, 3).toString();
										
										c.aggiungiAccount(idContatto, nicknameAccount, fornitoreAccount, benvenutoAccount, email);

									}	
									
									//uscita dall frame
									frame.setVisible(false);
									frameChiamante.setVisible(true);
									frame.dispose();
								}
								
								catch (Exception ex) {
									JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);
									ex.printStackTrace();
								}
								
							}
						});
						contentPane.add(btnConfirm);
						
						JButton btnAggiungiEmail = new JButton("Aggiungi Email");
						sl_contentPane.putConstraint(SpringLayout.NORTH, btnAggiungiEmail, 6, SpringLayout.SOUTH, comboBoxMail);
						sl_contentPane.putConstraint(SpringLayout.WEST, btnAggiungiEmail, 0, SpringLayout.WEST, textPanePrefisso);
						sl_contentPane.putConstraint(SpringLayout.EAST, btnAggiungiEmail, 150, SpringLayout.WEST, textPanePrefisso);
						btnAggiungiEmail.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								String email= JOptionPane.showInputDialog("Inserire l'email");
								if(email!=null)
									try {
									c.checkFormMail(email,listaMail);
									
									comboBoxMail.addItem(email);
									comboBoxMail.setSelectedIndex(comboBoxMail.getItemCount()-1);
									listaMail.add(email);
								}
								catch (Exception ex) {
									JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);
									ex.printStackTrace();
									
								}
							}
						});
						contentPane.add(btnAggiungiEmail);
						
						JButton btnEliminaEmail = new JButton("Elimina Email");
						sl_contentPane.putConstraint(SpringLayout.NORTH, btnEliminaEmail, 6, SpringLayout.SOUTH, comboBoxMail);
						sl_contentPane.putConstraint(SpringLayout.WEST, btnEliminaEmail, 5, SpringLayout.EAST, btnAggiungiEmail);
						sl_contentPane.putConstraint(SpringLayout.EAST, btnEliminaEmail, 155, SpringLayout.EAST, btnAggiungiEmail);
						btnEliminaEmail.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (comboBoxMail.getItemCount() <1) {
									JOptionPane.showInternalMessageDialog(null, "Nessuna Email presente", "Error",JOptionPane.ERROR_MESSAGE);
								}
								else {
									int result = JOptionPane.showConfirmDialog(null, "Sei sicuro di eliminare la mail?",  null, JOptionPane.OK_CANCEL_OPTION);
									int result2 = JOptionPane.OK_OPTION;
									if (result == JOptionPane.OK_OPTION) {
										String mailSelected = (String) comboBoxMail.getSelectedItem();
										for (int i = 0;i <tableAccounts.getRowCount();i++) {
											if (mailSelected.equals(tableAccounts.getValueAt(i,2))) {
												result2 = JOptionPane.showConfirmDialog(null,"Questa Email è presente nell'account "+tableAccounts.getValueAt(i, 1)+ ", procedere ugualmente?","Warning",JOptionPane.OK_CANCEL_OPTION);
												if (result2 == JOptionPane.OK_OPTION) {
													modelAccounts.setValueAt("Nessuna Mail", i, 2);
													JOptionPane.showMessageDialog(null,"Eliminata mail dall'account "+tableAccounts.getValueAt(i,1));
												}
												break;
											}
										}
										if (result2 == JOptionPane.OK_OPTION ) {
											listaMail.remove(mailSelected);
											comboBoxMail.removeItem(mailSelected);
										}
									}
								}
							}
						});
						contentPane.add(btnEliminaEmail);
						
						JButton btnModificaEmail = new JButton("Modifica Email");
						btnModificaEmail.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								
								if(comboBoxMail.getSelectedItem()!=null) {
									try {
								String mailSElezionata = comboBoxMail.getSelectedItem().toString() ;
								String mailModificata = mailSElezionata;
								 Object mailObj =JOptionPane.showInputDialog(null,
						                "Modifica l'email", "Modifica Email",
						                JOptionPane.QUESTION_MESSAGE,null,null,mailSElezionata) ;
								 if(mailObj!=null)
									  mailModificata=mailObj.toString();
								 
								if(mailModificata.compareToIgnoreCase(mailSElezionata)!=0) {
									
										c.checkFormMail(mailModificata, listaMail);
										
										comboBoxMail.insertItemAt(mailModificata, comboBoxMail.getSelectedIndex());
										comboBoxMail.removeItem(mailSElezionata);
										listaMail.remove(mailSElezionata);
										listaMail.add(mailModificata);
										
									}
								} catch (Exception e1) {
										e1.printStackTrace();
					                	JOptionPane.showMessageDialog(null, e1.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);
									}
									
								}
							}
							
						});
						sl_contentPane.putConstraint(SpringLayout.NORTH, btnModificaEmail, 6, SpringLayout.SOUTH, comboBoxMail);
						sl_contentPane.putConstraint(SpringLayout.WEST, btnModificaEmail, 5, SpringLayout.EAST, btnEliminaEmail);
						sl_contentPane.putConstraint(SpringLayout.EAST, btnModificaEmail, 155, SpringLayout.EAST, btnEliminaEmail);
						contentPane.add(btnModificaEmail);
						
						
		
		//action listeners foto
		
		
		modFotoButton.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * 
			 * @param e
			 */
			public void mouseClicked(MouseEvent e) {					
					JFileChooser fileChooser = new JFileChooser();
					 	
		            fileChooser.addChoosableFileFilter(new ImageFilter());
		            fileChooser.setAcceptAllFileFilterUsed(false);

		            int option = fileChooser.showOpenDialog(new JPanel());
		            if(option == JFileChooser.APPROVE_OPTION){
		                fileFoto = fileChooser.getSelectedFile();
		                pathFoto = fileFoto.getAbsolutePath();
		                    
		                ImageIcon iconFoto = new ImageIcon(c.getImageModificata(150, 150, fileFoto));
		                labelFoto.setIcon(iconFoto);
		            	labelFoto.setBounds(0, 0, 150, 150);
		            		
		                }else{
		                    //TODO 
		                   //testoFile.setText("Open command canceled");
		                }
			}
		});
	}
	
	class ImageFilter extends FileFilter {
        public final static String JPEG = "jpeg";
        public final static String JPG = "jpg";
        public final static String GIF = "gif";
        public final static String TIFF = "tiff";
        public final static String TIF = "tif";
        public final static String PNG = "png";


        public boolean accept(File f) {
           if (f.isDirectory()) {
              return true;
           }

           String extension = getExtension(f);
           if (extension != null) {
              if (extension.equals(TIFF)  || extension.equals(TIF)  ||  extension.equals(GIF) || extension.equals(JPEG) || extension.equals(JPG) || extension.equals(PNG)) {
                 return true;
              } else {
                 return false;
              }
           }
           return false;
        }        
  

        public String getDescription() {
           return "Image Only";
        }

        String getExtension(File f) {
           String ext = null;
           String s = f.getName();
           int i = s.lastIndexOf('.');

           if (i > 0 &&  i < s.length() - 1) {
              ext = s.substring(i+1).toLowerCase();
           }
           return ext;
        } 


}
}