package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
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
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


@SuppressWarnings("unused")
public class VisualizzaContattoFrame extends JFrame {
	private BufferedImage imageFoto;
	private JPanel contentPane;
	private JTable tableIndirizzi;
	private JTable tableAccounts;
	private JTable tableNumeri;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaContattoFrame frame = new VisualizzaContattoFrame();
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
	public VisualizzaContattoFrame() {
		setResizable(false);
		setTitle("Visualizzazione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1060, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelFoto = new JPanel();
		
		JLabel labelCognome = new JLabel("Cognome");
		
		JLabel labelNumeriTelefono = new JLabel("Numeri di Telefono");
		
		JLabel labelNome = new JLabel("Nome");
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.WEST, panelFoto, 50, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panelFoto, 205, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panelFoto, 203, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelNumeriTelefono, 196, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelNumeriTelefono, 224, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelNumeriTelefono, 342, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelNome, 85, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelNome, 224, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelCognome, 115, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelCognome, 224, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, panelFoto, 55, SpringLayout.NORTH, contentPane);
		contentPane.setLayout(sl_contentPane);
		
		//TODO una volta che il controller è completo aggiungere getter per prendere nome cognome e prefisso
		
		JTextPane textPanePrefisso = new JTextPane(/*Controller.getterPrefisso()*/);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPanePrefisso, 55, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPanePrefisso, 348, SpringLayout.WEST, contentPane);
		textPanePrefisso.setEditable(false);
		contentPane.add(textPanePrefisso);
		
		JButton bottonModificaContatto = new JButton("Modifica Contatto");
		sl_contentPane.putConstraint(SpringLayout.EAST, textPanePrefisso, -100, SpringLayout.WEST, bottonModificaContatto);
		sl_contentPane.putConstraint(SpringLayout.NORTH, bottonModificaContatto, 55, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, bottonModificaContatto, 898, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, bottonModificaContatto, 75, SpringLayout.NORTH, contentPane);
		
		contentPane.add(bottonModificaContatto);
		
		JTextPane textPaneNome = new JTextPane(/*Controller.getterNome()*/);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPaneNome, 85, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPaneNome, 348, SpringLayout.WEST, contentPane);
		textPaneNome.setEditable(false);
		contentPane.add(textPaneNome);
		
		JButton bottonModificaNumeri = new JButton(" Modifica Numeri  ");
		sl_contentPane.putConstraint(SpringLayout.EAST, textPaneNome, -100, SpringLayout.WEST, bottonModificaNumeri);
		sl_contentPane.putConstraint(SpringLayout.NORTH, bottonModificaNumeri, 85, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, bottonModificaNumeri, 898, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, bottonModificaNumeri, 105, SpringLayout.NORTH, contentPane);
		
		contentPane.add(bottonModificaNumeri);
		
		JTextPane textPaneCognome = new JTextPane(/*Controller.getterCognome()*/);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPaneCognome, 115, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPaneCognome, 348, SpringLayout.WEST, contentPane);
		textPaneCognome.setEditable(false);
		contentPane.add(textPaneCognome);
		
		JButton bottonModificaIndirizzi = new JButton(" Modifica Indirizzi ");
		sl_contentPane.putConstraint(SpringLayout.EAST, textPaneCognome, -100, SpringLayout.WEST, bottonModificaIndirizzi);
		sl_contentPane.putConstraint(SpringLayout.NORTH, bottonModificaIndirizzi, 115, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, bottonModificaIndirizzi, 898, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, bottonModificaIndirizzi, 135, SpringLayout.NORTH, contentPane);
		
		contentPane.add(bottonModificaIndirizzi);
		
		JButton bottonModificaAccount = new JButton("Modifica Account ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, bottonModificaAccount, 145, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, bottonModificaAccount, 898, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, bottonModificaAccount, 165, SpringLayout.NORTH, contentPane);
		
		contentPane.add(bottonModificaAccount);
		
		JScrollPane scrollPaneNumeri = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneNumeri, 196, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneNumeri, 348, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneNumeri, 1015, SpringLayout.WEST, contentPane);
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
		
	
		modelNumeri.addColumn("Numero"); 
		modelNumeri.addColumn("Tipo Numero"); 
		modelNumeri.addColumn("Principale");
		modelNumeri.addColumn("Identificatore");
		
		// Append a row 
		for(int i=0;i<arrayListContatti.size();i++) {
			modelNumeri.addRow(new Object[]{arrayListContatti.get(i), arrayListContattiCognome.get(i), arrayListContattiCognome.get(i),arrayListContattiCognome.get(i)});	
		}
		 
		
		scrollPaneNumeri.setViewportView(tableNumeri);
		
		
		
		JButton modFotoButton = new JButton("Modifica Foto");
		sl_contentPane.putConstraint(SpringLayout.NORTH, modFotoButton, 217, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, modFotoButton, 50, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, modFotoButton, 200, SpringLayout.WEST, contentPane);
		
		contentPane.add(modFotoButton);
		contentPane.add(panelFoto);
	
		panelFoto.setLayout(null);
		
		//TODO aggiungi funzionalità dinamica
		JLabel labelFoto = new JLabel("");
		labelFoto.setIcon(new ImageIcon(VisualizzaContattoFrame.class.getResource("/imagini/User1.png")));
		labelFoto.setBounds(0, 0, 150, 150);
		panelFoto.add(labelFoto);
		
		
		contentPane.add(labelCognome);
		contentPane.add(labelNome);
		contentPane.add(labelNumeriTelefono);
		
		JScrollPane scrollPaneIndirizzi = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneNumeri, -10, SpringLayout.NORTH, scrollPaneIndirizzi);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneIndirizzi, 281, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneIndirizzi, 348, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneIndirizzi, 1015, SpringLayout.WEST, contentPane);
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
		
		// Append a row TODO aggiungere metodo in Controller
		for(int i=0;i<arrayListContatti.size();i++) {
			modelIndirizzi.addRow(new Object[]{arrayListContatti.get(i), arrayListContattiCognome.get(i), arrayListContattiCognome.get(i),arrayListContattiCognome.get(i)});	
		}
		scrollPaneIndirizzi.setViewportView(tableIndirizzi);
		
		
		JScrollPane scrollPaneAccounts = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneIndirizzi, -10, SpringLayout.NORTH, scrollPaneAccounts);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneAccounts, 366, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneAccounts, 348, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneAccounts, 441, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneAccounts, 1015, SpringLayout.WEST, contentPane);
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
		
		// Append a row TODO aggiungere metodo in Controller
		for(int i=0;i<arrayListContatti.size();i++) {
			modelAccounts.addRow(new Object[]{arrayListContatti.get(i), arrayListContattiCognome.get(i), arrayListContattiCognome.get(i),arrayListContattiCognome.get(i)});	
		}

		scrollPaneAccounts.setViewportView(tableAccounts);
		
		
		JLabel labelAccounts = new JLabel("Accounts");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelAccounts, 366, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelAccounts, 224, SpringLayout.WEST, contentPane);
		contentPane.add(labelAccounts);
		
		JLabel labelIndirizzi = new JLabel("Indirizzi");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelIndirizzi, 281, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelIndirizzi, 224, SpringLayout.WEST, contentPane);
		contentPane.add(labelIndirizzi);
		
		JLabel labelPrefisso = new JLabel("Prefisso Nome");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelPrefisso, 55, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelPrefisso, 224, SpringLayout.WEST, contentPane);
		contentPane.add(labelPrefisso);
		//action listeners
		
		modFotoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 if(e.getClickCount()==2) {
					 //TODO modifica della foto
					 //Controller.modificaFoto();
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
}
