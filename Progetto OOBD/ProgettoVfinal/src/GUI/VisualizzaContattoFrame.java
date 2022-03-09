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
import Model.NumeriTelefonici;
import Model.Rubrica;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTML.Tag;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;


@SuppressWarnings({  "serial" })
public class VisualizzaContattoFrame extends JFrame {
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
	private JLabel labelMail;
	private JLabel labelPrefisso;
	private JLabel labelNome;
	private JLabel labelCognome;
	private JTextPane textPaneCognome;
	private JTextPane textPaneNome;
	private JTextPane textPanePrefisso;
	private JButton buttonIndietro;
	private JPanel panel;
	private File fileFoto;
	
	private ArrayList<String> listaNumeri = new ArrayList<String>();
	private ArrayList<String> listaMail = new ArrayList<String>();
	
	private String pathFoto;
	private JComboBox<String> comboBoxMail;
	private int contattoID;



	/**
	 * Create the frame.
	 */
	
	public VisualizzaContattoFrame(Controller controller, JFrame Chiamante, int id) {
		
		
		frame = this;
		c=controller;
		contattoID = id;
		frameChiamante=Chiamante;
		c.dumpContatto(contattoID);
//		c.transactionBegin();
		setTitle("Visualizza");
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
	        
		modelIndirizzi.addColumn("Id");
	    modelIndirizzi.addColumn("Tag");
		modelIndirizzi.addColumn("Via"); 
		modelIndirizzi.addColumn("Città"); 
		modelIndirizzi.addColumn("Codice Postale");
		modelIndirizzi.addColumn("Nazione");
		
		for(int i=0;i<c.getInfoContattoIndirizzoQuantità(id);i++) {
			modelIndirizzi.addRow(new Object[]{
					c.getInfoContattoIndirizzoId(i,id),
					c.getInfoContattoIndirizzoVia(i,id),
					c.getInfoContattoIndirizzoCittà(i, id),
					c.getInfoContattoIndirizzoCodicePostale(i, id),
					c.getInfoContattoIndirizzoNazione(i, id)
			});	
		}
		
		tableIndirizzi = new JTable(modelIndirizzi);
		final ListSelectionModel listenerIndirizzi=tableIndirizzi.getSelectionModel();
		tableIndirizzi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tableIndirizzi.removeColumn(tableIndirizzi.getColumnModel().getColumn(0));
		
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
		tableNumeri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					int row = tableNumeri.getSelectedRow();
					String tag = tableNumeri.getValueAt(row, 1).toString();
					String prefN = tableNumeri.getValueAt(row, 2).toString();
					String numero = tableNumeri.getValueAt(row, 3).toString();
					NumeriTelefonici deputato = c.getDeputatoDiNumero(contattoID, prefN,numero);
					System.out.println("Test deputato "+deputato);
//					JPanel visualizzaDeputato = new VisualizzaDeputatoPanel(tag, prefN, numero, deputato.getTag(), deputato.getPrefisso(), deputato.getNumero());
//					JOptionPane.showMessageDialog(null, visualizzaDeputato, "Visualizza deputato",JOptionPane.INFORMATION_MESSAGE);
					//TODO implementate il visualizzaDeputato
				}
			}
		});
		
		modelloNumeri.addColumn("tag");
		modelloNumeri.addColumn("prefisso");
		modelloNumeri.addColumn("numero");
		modelloNumeri.addColumn("tipo");
		
		
		final ListSelectionModel listenerNumeri=tableNumeri.getSelectionModel();
		tableNumeri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		for(int i=0;i<c.getNumeroQuantita(id);i++) {
			modelloNumeri.addRow(new Object[]{
					c.getInfoContattoTagNumero(i,id),
					c.getInfoContattoPrefissoNumero(i,id),
					c.getInfoContattoNumeroNumero(i,id),
					c.getInfoContattoNumeroTipo(i,id)
			});	
		}
		scrollPaneNumeri.setViewportView(tableNumeri);
				
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
				
				
				scrollPaneIndirizzi.setViewportView(tableIndirizzi);
				
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
						
						buttonIndietro = new JButton("Indietro");
						sl_contentPane.putConstraint(SpringLayout.NORTH, buttonIndietro, 6, SpringLayout.SOUTH, panelAccount);
						sl_contentPane.putConstraint(SpringLayout.WEST, buttonIndietro, 931, SpringLayout.WEST, contentPane);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonIndietro, -4, SpringLayout.SOUTH, contentPane);
						sl_contentPane.putConstraint(SpringLayout.EAST, buttonIndietro, 0, SpringLayout.EAST, panelNumeri);
						buttonIndietro.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
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