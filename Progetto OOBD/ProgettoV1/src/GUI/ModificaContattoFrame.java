package GUI;

import java.awt.EventQueue;

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
import Model.Rubrica;

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
public class ModificaContattoFrame extends JFrame {
	private JPanel contentPane;
	private JTable tableIndirizzi;
	private JTable tableAccounts;
	private JTable tableNumeri;
	/** Il Controller*/
	 static Controller c;
	/**L'id del contatto visualizzatp*/
	/**Il Chiamante	 */
	 static JFrame frameChiamante;
	 static JFrame frame;
	private JPanel panelFoto;
	private JLabel labelFoto;
	private JScrollPane scrollPaneNumeri;
	private JPanel panelNumeri;
	private JPanel panelIndirizzi;
	private JPanel panelAccount;
	private JScrollPane scrollPane;
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
	private JComboBox<String> comboBoxMail;
	private int contattoID;
	private JButton btnProvaBegin;



	/**
	 * Create the frame.
	 */
	
	public ModificaContattoFrame(Controller controller, JFrame Chiamante, int id) {
		//inizio della transazione di modfica
		c=controller;
		frame=this;
		c.transactionBegin();
		
		Rubrica rubrica = new Rubrica("test");
		frame = this;
		c=controller;
		contattoID = id;
		frameChiamante=Chiamante;
		c.dumpContatto(contattoID);
		c.transactionBegin();
		setTitle("Modifica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//posizione x posizione y, larghezza,altezza
		setBounds(100, 50, 1110, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelFoto = new JPanel();
		panelFoto.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		labelCognome = new JLabel("Cognome");
		
		labelNome = new JLabel("Nome");
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelNome, 72, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelNome, 255, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelNome, 370, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelCognome, 102, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelCognome, 255, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelCognome, 370, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, panelFoto, 40, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panelFoto, 40, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panelFoto, 190, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panelFoto, 190, SpringLayout.WEST, contentPane);
		contentPane.setLayout(sl_contentPane);
		
		
		
		textPanePrefisso = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPanePrefisso, 40, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPanePrefisso, 380, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPanePrefisso, -200, SpringLayout.EAST, contentPane);
		textPanePrefisso.setText(c.getInfoContattoPrefisso(id));
		contentPane.add(textPanePrefisso);
		
		textPaneNome = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPaneNome, 70, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPaneNome, 380, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPaneNome, 0, SpringLayout.EAST, textPanePrefisso);
		textPaneNome.setText(c.getInfoContattoNome(id));
		contentPane.add(textPaneNome);
		
		textPaneCognome = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPaneCognome, 100, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPaneCognome, 380, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPaneCognome, 0, SpringLayout.EAST, textPanePrefisso);
		textPaneCognome.setText(c.getInfoContattoNome(id));
		contentPane.add(textPaneCognome);
		


		
		
		// Inserimento nella tabella dei numeri del contatto
		
		
		
		
		modFotoButton = new JButton("Modifica Foto");
		sl_contentPane.putConstraint(SpringLayout.NORTH, modFotoButton, 200, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, modFotoButton, 40, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, modFotoButton, 190, SpringLayout.WEST, contentPane);
		
		contentPane.add(modFotoButton);
		contentPane.add(panelFoto);
	
		panelFoto.setLayout(null);
		
		//TODO modifica dimensioni della foto
		labelFoto = new JLabel("");
		labelFoto.setIcon(new ImageIcon(c.getImageModificata(150, 150, new File (c.getPathContatto(id)))));
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
		
		for(int i=0;i<c.getInfoContattoIndirizzoQuantità(id);i++) {

			modelIndirizzi.addRow(new Object[]{
					c.getInfoContattoIndirizzoTag(i, id),
					c.getInfoContattoIndirizzoVia(i,id),
					c.getInfoContattoIndirizzoCittà(i, id),
					c.getInfoContattoIndirizzoCodicePostale(i, id),
					c.getInfoContattoIndirizzoNazione(i, id)
			});	
		}
		
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
		
		for(int i=0;i<c.getInfoContattoAccountQuantità(id);i++) {
			
			modelAccounts.addRow(new Object[]{
				c.getInfoContattoAccountFornitore(i, id),
				c.getInfoContattoAccountNickname(i, id),
				c.getInfoContattoAccountMail(i, id),
				c.getInfoContattoAccountBenvenuto(i, id)
		});	
	}
		
		labelPrefisso = new JLabel("Prefisso Nome");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelPrefisso, 42, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelPrefisso, 255, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelPrefisso, 370, SpringLayout.WEST, contentPane);
		labelPrefisso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(labelPrefisso);
		
		labelMail = new JLabel("E-Mails");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelMail, 132, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelMail, 255, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelMail, 370, SpringLayout.WEST, contentPane);
		contentPane.add(labelMail);
		
		comboBoxMail = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxMail, 130, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxMail, 380, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxMail, 0, SpringLayout.EAST, textPanePrefisso);
	
		for (int i = 0; i < c.getInfoContattoMailList(id).size(); i++) {
			comboBoxMail.addItem((String) c.getInfoContattoMailList(id).get(i));
		}
		contentPane.add(comboBoxMail);
				
		panelNumeri = new JPanel();
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
		
		for(int i=0;i<c.getInfoContattoNumeroQuantita(id);i++) {
System.out.println(			c.getInfoContattoTagNumero(i,id));
System.out.println(			c.getInfoContattoPrefissoNumero(i,id));
System.out.println(			c.getInfoContattoNumeroNumero(i,id));
System.out.println(			c.getInfoContattoNumeroTipo(i,id));
			
			modelloNumeri.addRow(new Object[]{
					c.getInfoContattoTagNumero(i,id),
					c.getInfoContattoPrefissoNumero(i,id),
					c.getInfoContattoNumeroNumero(i,id),
					c.getInfoContattoNumeroTipo(i,id)
			});	
		}
		scrollPaneNumeri.setViewportView(tableNumeri);
		
		btnAggiungiNumero = new JButton("Aggiungi");
		
		sl_panelNumeri.putConstraint(SpringLayout.NORTH, btnAggiungiNumero, 5, SpringLayout.SOUTH, labelNumeriTelefono);
		sl_panelNumeri.putConstraint(SpringLayout.WEST, btnAggiungiNumero, 0, SpringLayout.WEST, labelNumeriTelefono);
		btnAggiungiNumero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String numeroTag= null;
				String numeroPrefisso= null;
				String numeroNumero= null;
				String numeroTipo = null;
				String DeputatoTipo = null;
				SetDeputatoPanel pannelSettaDeputati = null;
				AggiungiNumeroPanel panelAggiungiNumero = new AggiungiNumeroPanel();
                int result = JOptionPane.showConfirmDialog(null, panelAggiungiNumero, "Inserisci Numero", JOptionPane.OK_CANCEL_OPTION);
                try {
                    if (result == JOptionPane.OK_OPTION) {
//                    	c.checkFormNumero(panelAggiungiNumero.getPrefisso(), panelAggiungiNumero.getNumero());
                    	numeroTag=panelAggiungiNumero.getTag();
        				 numeroPrefisso =panelAggiungiNumero.getPrefisso();
        				 numeroNumero=panelAggiungiNumero.getNumero();
        				 numeroTipo=panelAggiungiNumero.getTipo();
                    	c.checkFormNumero(numeroPrefisso, numeroNumero, listaNumeri);
                    	
                    	 pannelSettaDeputati = new SetDeputatoPanel(modelloNumeri,
                    			numeroTag,
                    			numeroPrefisso,
                    			numeroNumero,
                    			numeroTipo
                    			);
                    	int tastoCliccatoNeiDialog= JOptionPane.showConfirmDialog(null,
								pannelSettaDeputati, "Seleziona i Deputati",
								JOptionPane.OK_OPTION);
						
						if(tastoCliccatoNeiDialog!=JOptionPane.OK_OPTION) {
							throw new Exception("Per aggiungere un numero deve avere un Deputato ");
						}
                    	modelloNumeri.addRow(new Object[]{
                    			numeroTag,
                    			numeroPrefisso,
                    			numeroNumero,
                    			numeroTipo
                        });
                    	listaNumeri.add(panelAggiungiNumero.getPrefisso()+panelAggiungiNumero.getNumero());
                    }
                  c.aggiungiNumero(contattoID, numeroTag, numeroPrefisso, numeroNumero, numeroTipo);
                  if(numeroTipo.compareToIgnoreCase("fisso")==0) DeputatoTipo = "Mobile";
                  else DeputatoTipo= "Fisso";
                  System.out.println("Sono Prima di chiamare la set Deputato prefADD: "+numeroPrefisso);
                  System.out.println("Sono Prima di chiamare la set Deputato NumeroADD: "+numeroNumero);
                  System.out.println("Sono Prima di chiamare la set Deputato TipoADD: "+numeroTipo);
                  System.out.println("Sono Prima di chiamare la set Deputato prefDEP: "+pannelSettaDeputati.getPrefissoDeputato());
                  System.out.println("Sono Prima di chiamare la set Deputato NumDEP: "+pannelSettaDeputati.getNumeroDeputato());
                  System.out.println("Sono Prima di chiamare la set Deputato TipoDep: "+DeputatoTipo);


                  c.setDeputato(contattoID, numeroPrefisso, numeroNumero, numeroTipo, pannelSettaDeputati.getPrefissoDeputato() , pannelSettaDeputati.getNumeroDeputato(), DeputatoTipo);
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
				//TODO c.eliminaNumero
				//TODO c.checkEliminaNumero
				//TODO c.eliminaNumero (DAO)
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
				int row =tableNumeri.getSelectedRow();
				if(row>=0) {
				int colonna=0;
				String numeroTag= null;
				String numeroPrefisso= null;
				String numeroNumero= null;
				String numeroTipo = null;
				String DeputatoTipo = null;
				SetDeputatoPanel pannelSettaDeputati = null;
				AggiungiNumeroPanel panelAggiungiNumero = new AggiungiNumeroPanel();
				
				panelAggiungiNumero.setAll(
						modelloNumeri.getValueAt(row, colonna++).toString(),
						modelloNumeri.getValueAt(row, colonna++).toString(),
						modelloNumeri.getValueAt(row, colonna++).toString(),
						modelloNumeri.getValueAt(row, colonna++).toString()
						);
                int result = JOptionPane.showConfirmDialog(null, panelAggiungiNumero, "Inserisci Numero", JOptionPane.OK_CANCEL_OPTION);
                try {
                    if (result == JOptionPane.OK_OPTION) {
//                    	c.checkFormNumero(panelAggiungiNumero.getPrefisso(), panelAggiungiNumero.getNumero());
                    	numeroTag=panelAggiungiNumero.getTag();
        				 numeroPrefisso =panelAggiungiNumero.getPrefisso();
        				 numeroNumero=panelAggiungiNumero.getNumero();
        				 numeroTipo=panelAggiungiNumero.getTipo();
                    	c.checkFormNumeroModifica(numeroPrefisso, numeroTipo, listaNumeri);
                    	
                    	 pannelSettaDeputati = new SetDeputatoPanel(modelloNumeri,
                    			numeroTag,
                    			numeroPrefisso,
                    			numeroNumero,
                    			numeroTipo
                    			);
                    	int tastoCliccatoNeiDialog= JOptionPane.showConfirmDialog(null,
								pannelSettaDeputati, "Seleziona i Deputati",
								JOptionPane.OK_OPTION);
						
						if(tastoCliccatoNeiDialog!=JOptionPane.OK_OPTION) {
							throw new Exception("Per aggiungere un numero deve avere un Deputato ");
						}
						String tipoOLD="";//TODO Prendere il tipo prima della modifica
						c.modificaNumero(contattoID, numeroTag, numeroPrefisso, numeroNumero, numeroTipo, tipoOLD);
                    	//TODO fare la set non add row
						modelloNumeri.addRow(new Object[]{
                    			numeroTag,
                    			numeroPrefisso,
                    			numeroNumero,
                    			numeroTipo
                        });
                    	listaNumeri.set(listaNumeri.indexOf(modelloNumeri.getValueAt(row, 1).toString()+modelloNumeri.getValueAt(row, 2).toString()), numeroPrefisso+numeroNumero);
                    }
                 
                  if(numeroTipo.compareToIgnoreCase("fisso")==0) DeputatoTipo = "Mobile";
                  else DeputatoTipo= "Fisso"; 
//                  System.out.println("Sono Prima di chiamare la set Deputato prefADD: "+numeroPrefisso);
//                  System.out.println("Sono Prima di chiamare la set Deputato NumeroADD: "+numeroNumero);
//                  System.out.println("Sono Prima di chiamare la set Deputato TipoADD: "+numeroTipo);
//                  System.out.println("Sono Prima di chiamare la set Deputato prefDEP: "+pannelSettaDeputati.getPrefissoDeputato());
//                  System.out.println("Sono Prima di chiamare la set Deputato NumDEP: "+pannelSettaDeputati.getNumeroDeputato());
//                  System.out.println("Sono Prima di chiamare la set Deputato TipoDep: "+DeputatoTipo);

                  c.setDeputato(contattoID, numeroPrefisso, numeroNumero, numeroTipo, pannelSettaDeputati.getPrefissoDeputato() , pannelSettaDeputati.getNumeroDeputato(), DeputatoTipo);
                }
                catch (Exception ex) {
                	ex.printStackTrace();
                	JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);
				}

				}	}
		});
		panelNumeri.add(btnModificaNumero);
				
				panelIndirizzi = new JPanel();
				sl_contentPane.putConstraint(SpringLayout.NORTH, panelNumeri, -135, SpringLayout.NORTH, panelIndirizzi);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, panelNumeri, -5, SpringLayout.NORTH, panelIndirizzi);
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
						//TODO c.eliminaIndirizzo
						//TODO c.checkEliminaIndirizzo
						//TODO c.eliminaIndirizzo (DAO)
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
								 modelIndirizzi.setValueAt(panelModificaIndirizzo.getVia(), row, column++);
								 modelIndirizzi.setValueAt(panelModificaIndirizzo.getCittà(), row, column++);
								 modelIndirizzi.setValueAt(panelModificaIndirizzo.getCodicePostale(), row, column++);
								 modelIndirizzi.setValueAt(panelModificaIndirizzo.getNazione(), row, column++);
								 modelIndirizzi.setValueAt(panelModificaIndirizzo.getTag(), row, column++);
							}
						}
					}
				});
				panelIndirizzi.add(btnModificaIndirizzo);
				
				panelAccount = new JPanel();
				sl_contentPane.putConstraint(SpringLayout.NORTH, panelAccount, -165, SpringLayout.SOUTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, panelAccount, -35, SpringLayout.SOUTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.EAST, panelNumeri, 0, SpringLayout.EAST, panelAccount);
				sl_contentPane.putConstraint(SpringLayout.EAST, panelIndirizzi, 0, SpringLayout.EAST, panelAccount);
				sl_contentPane.putConstraint(SpringLayout.EAST, panelAccount, -5, SpringLayout.EAST, contentPane);
				sl_contentPane.putConstraint(SpringLayout.WEST, panelNumeri, 0, SpringLayout.WEST, panelAccount);
				sl_contentPane.putConstraint(SpringLayout.NORTH, panelIndirizzi, -135, SpringLayout.NORTH, panelAccount);
				sl_contentPane.putConstraint(SpringLayout.WEST, panelIndirizzi, 0, SpringLayout.WEST, panelAccount);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, panelIndirizzi, -5, SpringLayout.NORTH, panelAccount);
				sl_contentPane.putConstraint(SpringLayout.WEST, panelAccount, 240, SpringLayout.WEST, contentPane);
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
								//TODO c.eliminaAccount
								//TODO c.checkEliminaAccount
								//TODO c.eliminaAccount (DAO)
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
						sl_contentPane.putConstraint(SpringLayout.NORTH, buttonIndietro, -30, SpringLayout.SOUTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.WEST, buttonIndietro, 770, SpringLayout.WEST, contentPane);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonIndietro, -5, SpringLayout.SOUTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.EAST, buttonIndietro, 920, SpringLayout.WEST, contentPane);
						buttonIndietro.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								//annullamento operazioni
								c.transactionRollBack();
								frameChiamante.setVisible(true);
								frame.setVisible(false);
								frame.dispose();
							}
						});
						contentPane.add(buttonIndietro);
						
						panel = new JPanel();
						sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 349, SpringLayout.NORTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, contentPane);
						contentPane.add(panel);
						
						JButton btnConfirm = new JButton("Conferma");
						sl_contentPane.putConstraint(SpringLayout.NORTH, btnConfirm, -30, SpringLayout.SOUTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.WEST, btnConfirm, 930, SpringLayout.WEST, contentPane);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, btnConfirm, -5, SpringLayout.SOUTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.EAST, btnConfirm, 1080, SpringLayout.WEST, contentPane);
						btnConfirm.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								
								SetIndirizzoPrincipale pannelSettaIndizzoPrincipale;
								ArrayList<String> listaTipi = new ArrayList<String>();
								int row=0;//variabile contattore per gestire tutti i vari for
								int tastoCliccatoNeiDialog=0; // tasto cllicato nei JDialougue
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
									
									
									// Load anagrafie di contatto
									int idContatto = c.aggiungiContatto(textPanePrefisso.getText(), textPaneNome.getText(),
											textPaneCognome.getText(), pathFoto);

									
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
									for (int i = 0; i < modelIndirizzi.getRowCount(); i++) {
										if (indexDelIndirizzoPrincipale == i)
											c.aggiungiIndirizzo(idContatto, modelIndirizzi.getValueAt(i, 1).toString(),
													modelIndirizzi.getValueAt(i, 2).toString(),
													modelIndirizzi.getValueAt(i, 3).toString(),
													modelIndirizzi.getValueAt(i, 4).toString(),
													modelIndirizzi.getValueAt(i, 0).toString(), true);
										else
											c.aggiungiIndirizzo(idContatto, modelIndirizzi.getValueAt(i, 1).toString(), 
													modelIndirizzi.getValueAt(i, 2).toString(),
													modelIndirizzi.getValueAt(i, 3).toString(),
													modelIndirizzi.getValueAt(i, 4).toString(),
													modelIndirizzi.getValueAt(i, 0).toString(), false);
									}
									// Load degli account
									for (int i = 0; i < modelAccounts.getRowCount(); i++) {
										c.aggiungiAccount(idContatto, modelAccounts.getValueAt(i, 0).toString(),
												modelAccounts.getValueAt(i, 1).toString(),
												modelAccounts.getValueAt(i, 2).toString(),
												modelAccounts.getValueAt(i, 3).toString());

									}	
									
									//uscita dall frame e commit
									c.transactionCommit();
									frame.setVisible(false);
									frameChiamante.setVisible(true);
									frame.dispose();
								}
								
								catch (Exception ex) {
									JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);
								}
								
							}
						});
						contentPane.add(btnConfirm);
						
						JButton btnAggiungiEmail = new JButton("Aggiungi Email");
						sl_contentPane.putConstraint(SpringLayout.NORTH, btnAggiungiEmail, 155, SpringLayout.NORTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.WEST, btnAggiungiEmail, 380, SpringLayout.WEST, contentPane);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAggiungiEmail, 180, SpringLayout.NORTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.EAST, btnAggiungiEmail, 530, SpringLayout.WEST, contentPane);
						btnAggiungiEmail.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								String email = JOptionPane.showInputDialog("Inserire l'email");
								try {
									c.checkFormMail(email,listaMail);
								
//									if (c.searchMail(email).size()>1)
//									{
//										listaMail.add(email);
//										int row = comboBoxMail.getSelectedIndex();
//										int old = listaMail.indexOf(comboBoxMail.getItemAt(row));
//										String newEmail = JOptionPane.showInputDialog("Inserire l'email");
//										try {
//											c.checkFormMail(newEmail,listaMail);
//											listaMail.set(old, newEmail);
//											comboBoxMail.remove(row);
//											comboBoxMail.addItem(newEmail);
//										}
//										catch (Exception ex) {
//											JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);	
//										}
//									
//									else {
									comboBoxMail.addItem(email);
									comboBoxMail.setSelectedItem(email);
									listaMail.add(email);
//									}
								}
								catch (Exception ex) {
									JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);
									
								}
							}
						});
						contentPane.add(btnAggiungiEmail);
						
						JButton btnEliminaEmail = new JButton("Elimina Email");
						sl_contentPane.putConstraint(SpringLayout.NORTH, btnEliminaEmail, 155, SpringLayout.NORTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.WEST, btnEliminaEmail, 535, SpringLayout.WEST, contentPane);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, btnEliminaEmail, 180, SpringLayout.NORTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.EAST, btnEliminaEmail, 685, SpringLayout.WEST, contentPane);
						btnEliminaEmail.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								//TODO c.eliminaEmail
								//TODO c.checkEliminaEmail
								//TODO c.eliminaEmail (DAO)
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
						sl_contentPane.putConstraint(SpringLayout.NORTH, btnModificaEmail, 155, SpringLayout.NORTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.WEST, btnModificaEmail, 690, SpringLayout.WEST, contentPane);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, btnModificaEmail, 180, SpringLayout.NORTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.EAST, btnModificaEmail, 840, SpringLayout.WEST, contentPane);
						btnModificaEmail.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								String mailSelected = (String) comboBoxMail.getSelectedItem();
								int row = comboBoxMail.getSelectedIndex();
								String newEmail = JOptionPane.showInputDialog("Inserire l'email");
								try {
									c.checkFormMail(newEmail,listaMail);
									//if(c.getInfoContattoMailList(id).size()==1) {
									comboBoxMail.removeItem(mailSelected);
									int old = listaMail.indexOf(mailSelected);
									listaMail.set(old, newEmail);
									comboBoxMail.addItem(newEmail);}
								//}
								catch (Exception ex) {
									JOptionPane.showMessageDialog(null, ex.getMessage(),"ERRORE",JOptionPane.ERROR_MESSAGE);	
								}
							}
						});
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