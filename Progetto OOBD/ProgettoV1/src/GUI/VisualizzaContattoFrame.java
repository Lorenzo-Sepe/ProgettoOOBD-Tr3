package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
//import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;

import Model.Contatto;
import Model.Indirizzi;
import Model.NumeriTelefonici;
import Controller.Controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;


@SuppressWarnings({ "unused", "serial" })
public class VisualizzaContattoFrame extends JFrame {
	private BufferedImage imageFoto;
	private JPanel contentPane;
	private JTable tableIndirizzi;
	private JTable tableAccounts;
	private JTable tableNumeri;
	//TODO togliere static quando si collega ad altri frame
	/** Il Controller*/
	static Controller c;
	/**L'id del contatto visualizzatp*/
	private static int contattoID;
	/**Il Chiamante	 */
	static JFrame frameChiamante;
	static JFrame frame;
	Contatto Contatto;
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
	private JComboBox<String> comboBoxMail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaContattoFrame frame = new VisualizzaContattoFrame(c, frameChiamante, contattoID);
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
	
	public VisualizzaContattoFrame(Controller controller, JFrame Chiamante,int id) {
		frame = this;
		c=controller;
		contattoID=id;
		frameChiamante=Chiamante;
		setTitle("Visualizzazione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1110, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelFoto = new JPanel();
		panelFoto.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		labelCognome = new JLabel("Cognome");
		
		labelNome = new JLabel("Nome");
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.WEST, panelFoto, 30, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panelFoto, 180, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelNome, 62, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelCognome, 92, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, panelFoto, 30, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panelFoto, 180, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelNome, 245, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelNome, 360, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelCognome, 245, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelCognome, 360, SpringLayout.WEST, contentPane);
		contentPane.setLayout(sl_contentPane);
		
		//TODO una volta che il controller è completo aggiungere getter per prendere nome cognome e prefisso
		
		textPanePrefisso = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPanePrefisso, 30, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPanePrefisso, 370, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPanePrefisso, -250, SpringLayout.EAST, contentPane);
		textPanePrefisso.setEditable(false);
		textPanePrefisso.setText(c.getContatto(id).getPrefissoNome());
		
		contentPane.add(textPanePrefisso);
		
		textPaneNome = new JTextPane(/*Controller.getterNome()*/);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPaneNome, 60, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPaneNome, 370, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPaneNome, -250, SpringLayout.EAST, contentPane);
		textPaneNome.setEditable(false);
		textPaneNome.setText(c.getContatto(id).getNome());
		contentPane.add(textPaneNome);
		
		textPaneCognome = new JTextPane(/*Controller.getterCognome()*/);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPaneCognome, 90, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPaneCognome, 370, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPaneCognome, -250, SpringLayout.EAST, contentPane);
		textPaneCognome.setEditable(false);
		textPaneCognome.setText(c.getContatto(id).getCognome());
		contentPane.add(textPaneCognome);
		
		ArrayList<String> arrayListContatti = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		
		comboBoxMail = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxMail, 0, SpringLayout.WEST, textPanePrefisso);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxMail, 0, SpringLayout.EAST, textPanePrefisso);
	
		for (int i = 0; i < c.getInfoContattoMailList(id).size(); i++) {
			comboBoxMail.addItem((String) c.getInfoContattoMailList(id).get(i));
		}
		contentPane.add(comboBoxMail);
		
		  DefaultTableModel modelNumeri =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		modelNumeri.addColumn("Identificatore");
		modelNumeri.addColumn("Prefisso");
		modelNumeri.addColumn("Numero"); 
		modelNumeri.addColumn("Tipo Numero"); 
		
		
		// Inserimento nella tabella dei numeri del contatto
		
		for(int i=0;i<c.getInfoContattoNumeroQuantita(id);i++) {

			modelNumeri.addRow(new Object[]{
					c.getInfoContattoTagNumero(i, id),
					c.getInfoContattoPrefissoNumero(i,id),
					c.getInfoContattoNumeroNumero(i, id),
					c.getInfoContattoNumeroTipo(i, id)
			});	
		}
		contentPane.add(panelFoto);
	
		panelFoto.setLayout(null);
		
		//TODO aggiungi funzionalità dinamica
		labelFoto = new JLabel("");
		labelFoto.setIcon(new ImageIcon(c.getPathContatto(id)));
		labelFoto.setBounds(0, 0, 150, 150);
		panelFoto.add(labelFoto);
		
		
		contentPane.add(labelCognome);
		contentPane.add(labelNome);
		
		
		DefaultTableModel modelIndirizzi =  new DefaultTableModel() {
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
		
		for(int i=0;i<c.getInfoContattoIndirizzoQuantità(id);i++) {

			modelNumeri.addRow(new Object[]{
					c.getInfoContattoIndirizzoTag(i, id),
					c.getInfoContattoIndirizzoVia(i,id),
					c.getInfoContattoIndirizzoCittà(i, id),
					c.getInfoContattoIndirizzoCodicePostale(i, id),
					c.getInfoContattoIndirizzoNazione(i, id)
			});	
		}
		//tableAccounts
		DefaultTableModel modelAccounts =  new DefaultTableModel() {
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

			modelNumeri.addRow(new Object[]{
					
					c.getInfoContattoAccountFornitore(i,id),
					c.getInfoContattoAccountNickname(i, id),
					c.getInfoContattoAccountMail(i, id),
					c.getInfoContattoAccountBenvenuto(i, id)
			});	
		}
		
		labelPrefisso = new JLabel("Prefisso Nome");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelPrefisso, 32, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelPrefisso, 245, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelPrefisso, 360, SpringLayout.WEST, contentPane);
		labelPrefisso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(labelPrefisso);
		
		labelMail = new JLabel("E-Mails");
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxMail, -4, SpringLayout.NORTH, labelMail);
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelMail, 122, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelMail, 245, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelMail, 360, SpringLayout.WEST, contentPane);
		contentPane.add(labelMail);
		
		panelNumeri = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panelNumeri, -400, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panelNumeri, 230, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panelNumeri, -15, SpringLayout.EAST, contentPane);
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
		
		tableNumeri = new JTable(modelNumeri);
		ListSelectionModel listenerNumeri=tableNumeri.getSelectionModel();
		tableNumeri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		scrollPaneNumeri.setViewportView(tableNumeri);
				
				panelIndirizzi = new JPanel();
				sl_contentPane.putConstraint(SpringLayout.SOUTH, panelNumeri, -5, SpringLayout.NORTH, panelIndirizzi);
				sl_contentPane.putConstraint(SpringLayout.WEST, panelIndirizzi, 230, SpringLayout.WEST, contentPane);
				sl_contentPane.putConstraint(SpringLayout.EAST, panelIndirizzi, -15, SpringLayout.EAST, contentPane);
				contentPane.add(panelIndirizzi);
				SpringLayout sl_panelIndirizzi = new SpringLayout();
				panelIndirizzi.setLayout(sl_panelIndirizzi);
				
				JLabel labelIndirizzi = new JLabel("Indirizzi");
				sl_panelIndirizzi.putConstraint(SpringLayout.NORTH, labelIndirizzi, 0, SpringLayout.NORTH, panelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.WEST, labelIndirizzi, 5, SpringLayout.WEST, panelIndirizzi);
				panelIndirizzi.add(labelIndirizzi);
				
				JScrollPane scrollPaneIndirizzi = new JScrollPane();
				sl_panelIndirizzi.putConstraint(SpringLayout.NORTH, scrollPaneIndirizzi, 0, SpringLayout.NORTH, panelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.WEST, scrollPaneIndirizzi, 140, SpringLayout.WEST, panelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.SOUTH, scrollPaneIndirizzi, 0, SpringLayout.SOUTH, panelIndirizzi);
				sl_panelIndirizzi.putConstraint(SpringLayout.EAST, scrollPaneIndirizzi, 0, SpringLayout.EAST, panelIndirizzi);
				panelIndirizzi.add(scrollPaneIndirizzi);
				
				tableIndirizzi = new JTable(modelIndirizzi);
				ListSelectionModel listenerIndirizzi=tableIndirizzi.getSelectionModel();
				tableIndirizzi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPaneIndirizzi.setViewportView(tableIndirizzi);
				
				panelAccount = new JPanel();
				sl_contentPane.putConstraint(SpringLayout.NORTH, panelAccount, -170, SpringLayout.SOUTH, contentPane);
				
				sl_contentPane.putConstraint(SpringLayout.SOUTH, panelAccount, -45, SpringLayout.SOUTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, panelIndirizzi, -5, SpringLayout.NORTH, panelAccount);
				sl_contentPane.putConstraint(SpringLayout.WEST, panelAccount, 230, SpringLayout.WEST, contentPane);
				sl_contentPane.putConstraint(SpringLayout.EAST, panelAccount, -15, SpringLayout.EAST, contentPane);
				contentPane.add(panelAccount);
				SpringLayout sl_panelAccount = new SpringLayout();
				panelAccount.setLayout(sl_panelAccount);
				
				
				JLabel labelAccounts = new JLabel("Accounts");
				sl_panelAccount.putConstraint(SpringLayout.NORTH, labelAccounts, 0, SpringLayout.NORTH, panelAccount);
				sl_panelAccount.putConstraint(SpringLayout.WEST, labelAccounts, 10, SpringLayout.WEST, panelAccount);
				sl_panelAccount.putConstraint(SpringLayout.EAST, labelAccounts, 125, SpringLayout.WEST, panelAccount);
				panelAccount.add(labelAccounts);
				labelAccounts.setFont(new Font("Tahoma", Font.PLAIN, 11));
				
				
				JScrollPane scrollPaneAccounts = new JScrollPane();
				sl_panelAccount.putConstraint(SpringLayout.NORTH, scrollPaneAccounts, 0, SpringLayout.NORTH, panelAccount);
				sl_panelAccount.putConstraint(SpringLayout.WEST, scrollPaneAccounts, 140, SpringLayout.WEST, panelAccount);
				sl_panelAccount.putConstraint(SpringLayout.SOUTH, scrollPaneAccounts, 0, SpringLayout.SOUTH, panelAccount);
				sl_panelAccount.putConstraint(SpringLayout.EAST, scrollPaneAccounts, 0, SpringLayout.EAST, panelAccount);
				panelAccount.add(scrollPaneAccounts);
				
				tableAccounts = new JTable(modelAccounts);
				ListSelectionModel listenerAccount = tableAccounts.getSelectionModel();
				tableAccounts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
						scrollPaneAccounts.setViewportView(tableAccounts);
						
						buttonIndietro = new JButton("Indietro");
						sl_contentPane.putConstraint(SpringLayout.WEST, buttonIndietro, 919, SpringLayout.WEST, contentPane);
						sl_contentPane.putConstraint(SpringLayout.EAST, buttonIndietro, -15, SpringLayout.EAST, contentPane);
						buttonIndietro.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								//TODO serve per cambiare da un frame ad un'altro
								frameChiamante.setVisible(true);
								frame.setVisible(false);
								frame.dispose();
							}
						});
						sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonIndietro, -10, SpringLayout.SOUTH, contentPane);
						contentPane.add(buttonIndietro);
						
						panel = new JPanel();
						sl_contentPane.putConstraint(SpringLayout.NORTH, panelIndirizzi, 10, SpringLayout.NORTH, panel);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, -280, SpringLayout.SOUTH, contentPane);
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
