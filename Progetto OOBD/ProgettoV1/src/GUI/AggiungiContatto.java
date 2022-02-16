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
import javax.swing.JDialog;
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
import Controller.Controller;
import Model.NumeriTelefonici;

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
public class AggiungiContatto extends JFrame {
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
	private JScrollPane scrollPane;
	private JLabel labelMail;
	private JList listMail;
	private JLabel labelPrefisso;
	private JLabel labelNome;
	private JLabel labelCognome;
	private JButton modFotoButton;
	private JTextPane textPaneCognome;
	private JTextPane textPaneNome;
	private JTextPane textPanePrefisso;
	private JButton buttonIndietro;
	private JPanel panel;
	private JButton btnAggiungiNumero;
	private JButton btnAggiungiIndirizzo;
	private JButton btnNewButton_1;
	private File fileFoto;
	private ArrayList<NumeriTelefonici> listaNumeri;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AggiungiContatto frame = new AggiungiContatto(c, frameChiamante);
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
		panelFoto.setBounds(35, 35, 150, 150);
		panelFoto.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		labelCognome = new JLabel("Cognome");
		labelCognome.setBounds(250, 97, 115, 14);
		
		labelNome = new JLabel("Nome");
		labelNome.setBounds(250, 67, 115, 14);
		contentPane.setLayout(null);
		
		//TODO una volta che il controller è completo aggiungere getter per prendere nome cognome e prefisso
		
		textPanePrefisso = new JTextPane();
		textPanePrefisso.setBounds(375, 35, 464, 20);
		
		contentPane.add(textPanePrefisso);
		
		textPaneNome = new JTextPane(/*Controller.getterNome()*/);
		textPaneNome.setBounds(375, 65, 464, 20);
		contentPane.add(textPaneNome);
		
		textPaneCognome = new JTextPane(/*Controller.getterCognome()*/);
		textPaneCognome.setBounds(375, 95, 464, 20);
		contentPane.add(textPaneCognome);
		
		ArrayList<String> arrayListContatti = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		
		
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
		
		
		
		
		modFotoButton = new JButton("Modifica Foto");
		modFotoButton.setBounds(35, 195, 150, 23);
		
		contentPane.add(modFotoButton);
		contentPane.add(panelFoto);
	
		panelFoto.setLayout(null);
		
		//TODO aggiungi funzionalit� dinamica
		labelFoto = new JLabel("");
		labelFoto.setIcon(new ImageIcon(AggiungiContatto.class.getResource("/Immagini/NoImage.jpg")));
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
		
	
		modelIndirizzi.addColumn("Via"); 
		modelIndirizzi.addColumn("Citt�"); 
		modelIndirizzi.addColumn("Codice Postale");
		modelIndirizzi.addColumn("Nazione");
		
		// Inserimento degli indirizzi del contatto
	
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
		
		
		labelPrefisso = new JLabel("Prefisso Nome");
		labelPrefisso.setBounds(250, 37, 115, 14);
		labelPrefisso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(labelPrefisso);
		
		labelMail = new JLabel("E-Mails");
		labelMail.setBounds(250, 127, 115, 14);
		contentPane.add(labelMail);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(375, 125, 464, 20);
		contentPane.add(scrollPane);
		
		listMail = new JList<Object>();
		listMail.setModel(new AbstractListModel() {
			String[] values = new String[] {"Mail", "Mail", "Mail"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listMail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listMail);
		
		panelNumeri = new JPanel();
		panelNumeri.setBounds(235, 234, 839, 115);
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
				panelIndirizzi.setBounds(235, 354, 839, 105);
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
				panelAccount.setBounds(235, 464, 839, 125);
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
						buttonIndietro.setBounds(752, 600, 150, 23);
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
						panel.setBounds(5, 344, 10, 10);
						contentPane.add(panel);
						
						JButton btnNewButton = new JButton("Conferma");
						btnNewButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								String prefisso = new String(textPanePrefisso.getText());
								String nome = new String(textPaneNome.getText());
								String cognome = new String(textPaneCognome.getText());
								int id = c.aggiungiContatto(prefisso, nome, cognome, "null");
								c.setFotoContatto(fileFoto, id);
								frameChiamante.setVisible(true);
								frame.setVisible(false);
								frame.dispose();
								
							}
						});
						btnNewButton.setBounds(927, 600, 150, 23);
						contentPane.add(btnNewButton);
						
						btnAggiungiNumero = new JButton("Aggiungi Numero");
						btnAggiungiNumero.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								JDialog aggiungiNumero = new AggiungiNumeroFrame(c,frame);
								aggiungiNumero.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								aggiungiNumero.setModal(true);
								aggiungiNumero.setVisible(true);
							}
						});
						btnAggiungiNumero.setBounds(65, 281, 120, 23);
						contentPane.add(btnAggiungiNumero);
						
						btnAggiungiIndirizzo = new JButton("Aggiungi Indirizzo");
						btnAggiungiIndirizzo.setBounds(65, 385, 120, 23);
						contentPane.add(btnAggiungiIndirizzo);
						
						btnNewButton_1 = new JButton("Aggiungi Accout");
						btnNewButton_1.setBounds(65, 508, 120, 23);
						contentPane.add(btnNewButton_1);
		
		//action listeners foto
		
		
		modFotoButton.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * 
			 * @param e
			 */
			public void mouseClicked(MouseEvent e) {
					 //TODO elimina vecchia foto o salva (da vagliare)
					
					JFileChooser fileChooser = new JFileChooser();
					 	
		            fileChooser.addChoosableFileFilter(new ImageFilter());
		            fileChooser.setAcceptAllFileFilterUsed(false);

		            int option = fileChooser.showOpenDialog(new JPanel());
		            if(option == JFileChooser.APPROVE_OPTION){
		                fileFoto = fileChooser.getSelectedFile();
		                    
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