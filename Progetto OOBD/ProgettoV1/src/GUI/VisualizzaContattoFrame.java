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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import GUI.ProvaScegliFile.ImageFilter;
import Controller.Controller;
import model.Contatto;

import com.jgoodies.forms.layout.FormSpecs;
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


@SuppressWarnings("unused")
public class VisualizzaContattoFrame extends JFrame {
	private BufferedImage imageFoto;
	private JPanel contentPane;
	private JTable tableIndirizzi;
	private JTable tableAccounts;
	private JTable tableNumeri;
	//TODO togliere static quando si collega ad altri frame
	static Controller c;
	private static int contattoID;
	static Frame frameChiamante;
	Contatto Contatto;
	private JPanel panelFoto;
	private JLabel labelFoto;

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
	
	public VisualizzaContattoFrame(Controller controller, Frame Chiamante,int id) {
		c=controller;
		contattoID=id;
		frameChiamante=Chiamante;
		setResizable(false);
		setTitle("Visualizzazione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelFoto = new JPanel();
		panelFoto.setBounds(60, 65, 150, 150);
		
		JLabel labelCognome = new JLabel("Cognome");
		labelCognome.setBounds(235, 125, 115, 14);
		
		JLabel labelNumeriTelefono = new JLabel("Numeri di Telefono");
		labelNumeriTelefono.setBounds(235, 225, 120, 14);
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setBounds(235, 95, 115, 14);
		contentPane.setLayout(null);
		
		//TODO una volta che il controller è completo aggiungere getter per prendere nome cognome e prefisso
		
		JTextPane textPanePrefisso = new JTextPane();
		textPanePrefisso.setBounds(360, 65, 450, 20);
		textPanePrefisso.setEditable(false);
		textPanePrefisso.setText(c.getContatto(id).getPrefissoNome());
		
		contentPane.add(textPanePrefisso);
		
		JButton bottonModificaContatto = new JButton("Modifica Contatto");
		bottonModificaContatto.setBounds(910, 65, 150, 20);
		
		contentPane.add(bottonModificaContatto);
		
		JTextPane textPaneNome = new JTextPane(/*Controller.getterNome()*/);
		textPaneNome.setBounds(360, 95, 450, 20);
		textPaneNome.setEditable(false);
		textPaneNome.setText(c.getContatto(id).getNome());
		contentPane.add(textPaneNome);
		
		JButton bottonModificaNumeri = new JButton(" Modifica Numeri  ");
		bottonModificaNumeri.setBounds(910, 95, 150, 20);
		
		contentPane.add(bottonModificaNumeri);
		
		JTextPane textPaneCognome = new JTextPane(/*Controller.getterCognome()*/);
		textPaneCognome.setBounds(360, 125, 450, 20);
		textPaneCognome.setEditable(false);
		textPaneCognome.setText(c.getContatto(id).getCognome());
		contentPane.add(textPaneCognome);
		
		JButton bottonModificaIndirizzi = new JButton(" Modifica Indirizzi ");
		bottonModificaIndirizzi.setBounds(910, 125, 150, 20);
		
		contentPane.add(bottonModificaIndirizzi);
		
		JButton bottonModificaAccount = new JButton("Modifica Account ");
		bottonModificaAccount.setBounds(910, 155, 150, 20);
		
		contentPane.add(bottonModificaAccount);
		
		JScrollPane scrollPaneNumeri = new JScrollPane();
		scrollPaneNumeri.setBounds(360, 225, 700, 75);
		contentPane.add(scrollPaneNumeri);
		
		ArrayList<String> arrayListContatti = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		
		
		  DefaultTableModel modelNumeri =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		tableNumeri = new JTable(modelNumeri);
		ListSelectionModel listenerNumeri=tableNumeri.getSelectionModel();
		tableNumeri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelNumeri.addColumn("Identificatore");
		modelNumeri.addColumn("Prefisso");
		modelNumeri.addColumn("Numero"); 
		modelNumeri.addColumn("Tipo Numero"); 
		
		
		
		// Inserimento nella tabella dei numeri del contatto
		
		for(int i=0;i<c.getContatto(id).getListaNumeri().size();i++) {
			modelNumeri.addRow(new Object[]{
					c.getContatto(id).getNumero(i).gettag(),
					c.getContatto(id).getNumero(i).getPrefisso(),
					c.getContatto(id).getNumero(i).getNumero(),
					c.getContatto(id).getNumero(i).getTipoNumero()
					
			});	
		}
		 
		
		scrollPaneNumeri.setViewportView(tableNumeri);
		
		
		
		JButton modFotoButton = new JButton("Modifica Foto");
		modFotoButton.setBounds(60, 230, 150, 23);
		
		contentPane.add(modFotoButton);
		contentPane.add(panelFoto);
	
		panelFoto.setLayout(null);
		
		//TODO aggiungi funzionalità dinamica
		labelFoto = new JLabel("");
		labelFoto.setIcon(new ImageIcon(c.getPathContatto(id)));
		labelFoto.setBounds(0, 0, 150, 150);
		panelFoto.add(labelFoto);
		
		
		contentPane.add(labelCognome);
		contentPane.add(labelNome);
		contentPane.add(labelNumeriTelefono);
		
		JScrollPane scrollPaneIndirizzi = new JScrollPane();
		scrollPaneIndirizzi.setBounds(360, 310, 700, 75);
		contentPane.add(scrollPaneIndirizzi);
		
		
		DefaultTableModel modelIndirizzi =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		tableIndirizzi = new JTable(modelIndirizzi);
		ListSelectionModel listenerIndirizzi=tableIndirizzi.getSelectionModel();
		tableIndirizzi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		modelIndirizzi.addColumn("Via"); 
		modelIndirizzi.addColumn("Città"); 
		modelIndirizzi.addColumn("Codice Postale");
		modelIndirizzi.addColumn("Nazione");
		
		// Inserimento degli indirizzi del contatto
		
		for(int i=0;i<c.getContatto(id).getListaIndirizzi().size();i++) {
			modelIndirizzi.addRow(new Object[]{
					c.getContatto(id).getIndirizzo(i).getVia(),
					c.getContatto(id).getIndirizzo(i).getCittà(),
					c.getContatto(id).getIndirizzo(i).getCodicePostale(),
					c.getContatto(id).getIndirizzo(i).getNazione()
			});	
		}
		scrollPaneIndirizzi.setViewportView(tableIndirizzi);
		
		
		JScrollPane scrollPaneAccounts = new JScrollPane();
		scrollPaneAccounts.setBounds(360, 395, 700, 76);
		contentPane.add(scrollPaneAccounts);
		//tableAccounts
		DefaultTableModel modelAccounts =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		tableAccounts = new JTable(modelAccounts);
		ListSelectionModel listenerAccount = tableAccounts.getSelectionModel();
		tableAccounts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		modelAccounts.addColumn("Fornitore"); 
		modelAccounts.addColumn("Nickname"); 
		modelAccounts.addColumn("E-Mail");
		modelAccounts.addColumn("Frase di Benvenuto");
		
		// Inserimento account del contatto
		
		for(int i=0;i<c.getContatto(id).getListaAccount().size();i++) {
			modelAccounts.addRow(new Object[]{
					c.getContatto(id).getAccount(i).getFornitore(),
					c.getContatto(id).getAccount(i).getNickname(),
					c.getContatto(id).getAccount(i).getMail(),
					c.getContatto(id).getAccount(i).getBenvenuto()
			});	
		}

		scrollPaneAccounts.setViewportView(tableAccounts);
		
		
		JLabel labelAccounts = new JLabel("Accounts");
		labelAccounts.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelAccounts.setBounds(235, 395, 115, 14);
		contentPane.add(labelAccounts);
		
		JLabel labelIndirizzi = new JLabel("Indirizzi");
		labelIndirizzi.setBounds(235, 310, 115, 14);
		contentPane.add(labelIndirizzi);
		
		JLabel labelPrefisso = new JLabel("Prefisso Nome");
		labelPrefisso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelPrefisso.setBounds(235, 65, 115, 14);
		contentPane.add(labelPrefisso);
		
		JLabel labelMail = new JLabel("E-Mails");
		labelMail.setBounds(235, 158, 115, 14);
		contentPane.add(labelMail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(360, 156, 450, 20);
		contentPane.add(scrollPane);
		
		JList listMail = new JList<Object>();
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
		
		JButton buttonModificaMail = new JButton("Modifica E-Mail");
		buttonModificaMail.setBounds(910, 186, 150, 20);
		contentPane.add(buttonModificaMail);
		
		//action listeners foto
		
		
		modFotoButton.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * 
			 * @param e
			 */
			public void mouseClicked(MouseEvent e) {
				 if(e.getClickCount()==2) {
					 //TODO modifica della foto
					
					 JFileChooser fileChooser = new JFileChooser();
					 	
		                fileChooser.addChoosableFileFilter(new ImageFilter());
		                fileChooser.setAcceptAllFileFilterUsed(false);

		                int option = fileChooser.showOpenDialog(new JPanel());
		                if(option == JFileChooser.APPROVE_OPTION){
		                   File file = fileChooser.getSelectedFile();
		                    c.setFotoContatto(file.getAbsolutePath(), contattoID);
		                    
		          
		                    ImageIcon iconFoto = new ImageIcon(c.getContatto(contattoID).getPathFoto());
		                    labelFoto.setIcon(iconFoto);
		            		labelFoto.setBounds(0, 0, 150, 150);
		            		panelFoto.add(labelFoto);
		                }else{
		                    //TODO 
		                   //testoFile.setText("Open command canceled");
		                }
				 }
			}
		});
		bottonModificaContatto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					 //TODO modifica del contatto
					 //Controller.modificaContatto();
					
				 }
			}
		});
		
		bottonModificaNumeri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					 //TODO modifica dei Numeri
					 //Controller.modificaNumeri();
				 }
			}
		});
	
		bottonModificaIndirizzi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					 //TODO modifica dei Indirizzi
					 //Controller.modificaIndirizzi();
				 }
			}
		});
	
		bottonModificaAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					 //TODO modifica dei Account
					 //Controller.modificaAccount();
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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import GUI.ProvaScegliFile.ImageFilter;
import Controller.Controller;
import model.Contatto;

import com.jgoodies.forms.layout.FormSpecs;
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


@SuppressWarnings("unused")
public class VisualizzaContattoFrame extends JFrame {
	private BufferedImage imageFoto;
	private JPanel contentPane;
	private JTable tableIndirizzi;
	private JTable tableAccounts;
	private JTable tableNumeri;
	//TODO togliere static quando si collega ad altri frame
	static Controller c;
	private static int contattoID;
	static Frame frameChiamante;
	Contatto Contatto;
	private JPanel panelFoto;
	private JLabel labelFoto;

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
	
	public VisualizzaContattoFrame(Controller controller, Frame Chiamante,int id) {
		c=controller;
		contattoID=id;
		frameChiamante=Chiamante;
		setResizable(false);
		setTitle("Visualizzazione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelFoto = new JPanel();
		panelFoto.setBounds(60, 65, 150, 150);
		
		JLabel labelCognome = new JLabel("Cognome");
		labelCognome.setBounds(235, 125, 115, 14);
		
		JLabel labelNumeriTelefono = new JLabel("Numeri di Telefono");
		labelNumeriTelefono.setBounds(235, 225, 120, 14);
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setBounds(235, 95, 115, 14);
		contentPane.setLayout(null);
		
		//TODO una volta che il controller è completo aggiungere getter per prendere nome cognome e prefisso
		
		JTextPane textPanePrefisso = new JTextPane();
		textPanePrefisso.setBounds(360, 65, 450, 20);
		textPanePrefisso.setEditable(false);
		textPanePrefisso.setText(c.getContatto(id).getPrefissoNome());
		
		contentPane.add(textPanePrefisso);
		
		JButton bottonModificaContatto = new JButton("Modifica Contatto");
		bottonModificaContatto.setBounds(910, 65, 150, 20);
		
		contentPane.add(bottonModificaContatto);
		
		JTextPane textPaneNome = new JTextPane(/*Controller.getterNome()*/);
		textPaneNome.setBounds(360, 95, 450, 20);
		textPaneNome.setEditable(false);
		textPaneNome.setText(c.getContatto(id).getNome());
		contentPane.add(textPaneNome);
		
		JButton bottonModificaNumeri = new JButton(" Modifica Numeri  ");
		bottonModificaNumeri.setBounds(910, 95, 150, 20);
		
		contentPane.add(bottonModificaNumeri);
		
		JTextPane textPaneCognome = new JTextPane(/*Controller.getterCognome()*/);
		textPaneCognome.setBounds(360, 125, 450, 20);
		textPaneCognome.setEditable(false);
		textPaneCognome.setText(c.getContatto(id).getCognome());
		contentPane.add(textPaneCognome);
		
		JButton bottonModificaIndirizzi = new JButton(" Modifica Indirizzi ");
		bottonModificaIndirizzi.setBounds(910, 125, 150, 20);
		
		contentPane.add(bottonModificaIndirizzi);
		
		JButton bottonModificaAccount = new JButton("Modifica Account ");
		bottonModificaAccount.setBounds(910, 155, 150, 20);
		
		contentPane.add(bottonModificaAccount);
		
		JScrollPane scrollPaneNumeri = new JScrollPane();
		scrollPaneNumeri.setBounds(360, 225, 700, 75);
		contentPane.add(scrollPaneNumeri);
		
		ArrayList<String> arrayListContatti = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		
		
		  DefaultTableModel modelNumeri =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		tableNumeri = new JTable(modelNumeri);
		ListSelectionModel listenerNumeri=tableNumeri.getSelectionModel();
		tableNumeri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelNumeri.addColumn("Identificatore");
		modelNumeri.addColumn("Prefisso");
		modelNumeri.addColumn("Numero"); 
		modelNumeri.addColumn("Tipo Numero"); 
		
		
		
		// Inserimento nella tabella dei numeri del contatto
		
		for(int i=0;i<c.getContatto(id).getListaNumeri().size();i++) {
			modelNumeri.addRow(new Object[]{
					c.getContatto(id).getNumero(i).gettag(),
					c.getContatto(id).getNumero(i).getPrefisso(),
					c.getContatto(id).getNumero(i).getNumero(),
					c.getContatto(id).getNumero(i).getTipoNumero()
					
			});	
		}
		 
		
		scrollPaneNumeri.setViewportView(tableNumeri);
		
		
		
		JButton modFotoButton = new JButton("Modifica Foto");
		modFotoButton.setBounds(60, 230, 150, 23);
		
		contentPane.add(modFotoButton);
		contentPane.add(panelFoto);
	
		panelFoto.setLayout(null);
		
		//TODO aggiungi funzionalità dinamica
		labelFoto = new JLabel("");
		labelFoto.setIcon(new ImageIcon(c.getPathContatto(id)));
		labelFoto.setBounds(0, 0, 150, 150);
		panelFoto.add(labelFoto);
		
		
		contentPane.add(labelCognome);
		contentPane.add(labelNome);
		contentPane.add(labelNumeriTelefono);
		
		JScrollPane scrollPaneIndirizzi = new JScrollPane();
		scrollPaneIndirizzi.setBounds(360, 310, 700, 75);
		contentPane.add(scrollPaneIndirizzi);
		
		
		DefaultTableModel modelIndirizzi =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		tableIndirizzi = new JTable(modelIndirizzi);
		ListSelectionModel listenerIndirizzi=tableIndirizzi.getSelectionModel();
		tableIndirizzi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		modelIndirizzi.addColumn("Via"); 
		modelIndirizzi.addColumn("Città"); 
		modelIndirizzi.addColumn("Codice Postale");
		modelIndirizzi.addColumn("Nazione");
		
		// Inserimento degli indirizzi del contatto
		
		for(int i=0;i<c.getContatto(id).getListaIndirizzi().size();i++) {
			modelIndirizzi.addRow(new Object[]{
					c.getContatto(id).getIndirizzo(i).getVia(),
					c.getContatto(id).getIndirizzo(i).getCittà(),
					c.getContatto(id).getIndirizzo(i).getCodicePostale(),
					c.getContatto(id).getIndirizzo(i).getNazione()
			});	
		}
		scrollPaneIndirizzi.setViewportView(tableIndirizzi);
		
		
		JScrollPane scrollPaneAccounts = new JScrollPane();
		scrollPaneAccounts.setBounds(360, 395, 700, 76);
		contentPane.add(scrollPaneAccounts);
		//tableAccounts
		DefaultTableModel modelAccounts =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		tableAccounts = new JTable(modelAccounts);
		ListSelectionModel listenerAccount = tableAccounts.getSelectionModel();
		tableAccounts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		modelAccounts.addColumn("Fornitore"); 
		modelAccounts.addColumn("Nickname"); 
		modelAccounts.addColumn("E-Mail");
		modelAccounts.addColumn("Frase di Benvenuto");
		
		// Inserimento account del contatto
		
		for(int i=0;i<c.getContatto(id).getListaAccount().size();i++) {
			modelAccounts.addRow(new Object[]{
					c.getContatto(id).getAccount(i).getFornitore(),
					c.getContatto(id).getAccount(i).getNickname(),
					c.getContatto(id).getAccount(i).getMail(),
					c.getContatto(id).getAccount(i).getBenvenuto()
			});	
		}

		scrollPaneAccounts.setViewportView(tableAccounts);
		
		
		JLabel labelAccounts = new JLabel("Accounts");
		labelAccounts.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelAccounts.setBounds(235, 395, 115, 14);
		contentPane.add(labelAccounts);
		
		JLabel labelIndirizzi = new JLabel("Indirizzi");
		labelIndirizzi.setBounds(235, 310, 115, 14);
		contentPane.add(labelIndirizzi);
		
		JLabel labelPrefisso = new JLabel("Prefisso Nome");
		labelPrefisso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelPrefisso.setBounds(235, 65, 115, 14);
		contentPane.add(labelPrefisso);
		
		JLabel labelMail = new JLabel("E-Mails");
		labelMail.setBounds(235, 158, 115, 14);
		contentPane.add(labelMail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(360, 156, 450, 20);
		contentPane.add(scrollPane);
		
		JList listMail = new JList<Object>();
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
		
		JButton buttonModificaMail = new JButton("Modifica E-Mail");
		buttonModificaMail.setBounds(910, 186, 150, 20);
		contentPane.add(buttonModificaMail);
		
		//action listeners foto
		
		
		modFotoButton.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * 
			 * @param e
			 */
			public void mouseClicked(MouseEvent e) {
				 if(e.getClickCount()==2) {
					 //TODO modifica della foto
					
					 JFileChooser fileChooser = new JFileChooser();
					 	
		                fileChooser.addChoosableFileFilter(new ImageFilter());
		                fileChooser.setAcceptAllFileFilterUsed(false);

		                int option = fileChooser.showOpenDialog(new JPanel());
		                if(option == JFileChooser.APPROVE_OPTION){
		                   File file = fileChooser.getSelectedFile();
		                    c.setFotoContatto(file.getAbsolutePath(), contattoID);
		                    
		          
		                    ImageIcon iconFoto = new ImageIcon(c.getContatto(contattoID).getPathFoto());
		                    labelFoto.setIcon(iconFoto);
		            		labelFoto.setBounds(0, 0, 150, 150);
		            		panelFoto.add(labelFoto);
		                }else{
		                    //TODO 
		                   //testoFile.setText("Open command canceled");
		                }
				 }
			}
		});
		bottonModificaContatto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					 //TODO modifica del contatto
					 //Controller.modificaContatto();
					
				 }
			}
		});
		
		bottonModificaNumeri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					 //TODO modifica dei Numeri
					 //Controller.modificaNumeri();
				 }
			}
		});
	
		bottonModificaIndirizzi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					 //TODO modifica dei Indirizzi
					 //Controller.modificaIndirizzi();
				 }
			}
		});
	
		bottonModificaAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					 //TODO modifica dei Account
					 //Controller.modificaAccount();
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
