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
		setTitle("Visualizzazione");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelFoto = new JPanel();
		
		JLabel labelCognome = new JLabel("Cognome");
		
		JLabel labelNumeriTelefono = new JLabel("Numeri di Telefono");
		
		JLabel labelNome = new JLabel("Nome");
		
		
		
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("48px"),
				ColumnSpec.decode("100px:grow"),
				ColumnSpec.decode("67px"),
				ColumnSpec.decode("118px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(145dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(145dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(145dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("50px"),
				RowSpec.decode("20px"),
				RowSpec.decode("10px"),
				RowSpec.decode("20px"),
				RowSpec.decode("10px"),
				RowSpec.decode("20px"),
				RowSpec.decode("10px"),
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("75px"),
				RowSpec.decode("10px"),
				RowSpec.decode("75px"),
				RowSpec.decode("10px"),
				RowSpec.decode("75px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JTextPane textPanePrefisso = new JTextPane();
		textPanePrefisso.setEditable(false);
		contentPane.add(textPanePrefisso, "6, 2, 3, 1, default, fill");
		
		JButton bottonModificaContatto = new JButton("Modifica Contatto");
		
		contentPane.add(bottonModificaContatto, "10, 2, right, default");
		
		JTextPane textPaneNome = new JTextPane();
		textPaneNome.setEditable(false);
		contentPane.add(textPaneNome, "6, 4, 3, 1, default, fill");
		
		JButton bottonModificaNumeri = new JButton(" Modifica Numeri  ");
		
		contentPane.add(bottonModificaNumeri, "10, 4, right, default");
		
		JTextPane textPaneCognome = new JTextPane();
		textPaneCognome.setEditable(false);
		contentPane.add(textPaneCognome, "6, 6, 3, 1, default, fill");
		
		JButton bottonModificaIndirizzi = new JButton(" Modifica Indirizzi ");
		
		contentPane.add(bottonModificaIndirizzi, "10, 6, right, default");
		
		JButton bottonModificaAccount = new JButton("Modifica Account ");
		
		contentPane.add(bottonModificaAccount, "10, 8, right, default");
		
		JScrollPane scrollPaneNumeri = new JScrollPane();
		contentPane.add(scrollPaneNumeri, "6, 10, 5, 1, fill, fill");
		
		ArrayList<String> arrayListContatti = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		
		
		  DefaultTableModel modelNumeri =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		tableNumeri = new JTable(modelNumeri);
		ListSelectionModel listener=tableNumeri.getSelectionModel();
		tableNumeri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		modelNumeri.addColumn("Numero"); 
		modelNumeri.addColumn("Tipo Numero"); 
		modelNumeri.addColumn("Principale");
		modelNumeri.addColumn("Identificatore");
		
		// Append a row 
		for(int i=0;i<arrayListContatti.size();i++) {
			modelNumeri.addRow(new Object[]{arrayListContatti.get(i), arrayListContattiCognome.get(i), arrayListContattiCognome.get(i),arrayListContattiCognome.get(i)});
			
		}
		 
		/*
		tableNumeri = new JTable();
		tableNumeri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableNumeri.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Numero", "Tipo Numero", "Principale", "Identificatore"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		tableNumeri.getColumnModel().getColumn(0).setResizable(false);
		tableNumeri.getColumnModel().getColumn(1).setResizable(false);
		tableNumeri.getColumnModel().getColumn(2).setResizable(false);
		tableNumeri.getColumnModel().getColumn(3).setResizable(false);
		*/
		scrollPaneNumeri.setViewportView(tableNumeri);
		
		
		
		JButton modFotoButton = new JButton("Modifica Foto");
		
		contentPane.add(modFotoButton, "2, 12, fill, top");
		contentPane.add(panelFoto, "2, 2, 1, 9, fill, fill");
	
		panelFoto.setLayout(null);
		
		//TODO aggiungi funzionalità dinamica
		JLabel labelFoto = new JLabel("");
		labelFoto.setIcon(new ImageIcon(VisualizzaContattoFrame.class.getResource("/imagini/User1.png")));
		labelFoto.setBounds(0, 0, 100, 100);
		panelFoto.add(labelFoto);
		
		
		contentPane.add(labelCognome, "4, 6, left, top");
		contentPane.add(labelNome, "4, 4, left, top");
		contentPane.add(labelNumeriTelefono, "4, 10, fill, top");
		
		JScrollPane scrollPaneIndirizzi = new JScrollPane();
		contentPane.add(scrollPaneIndirizzi, "6, 12, 5, 1, fill, fill");
		
		tableIndirizzi = new JTable();
		tableIndirizzi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableIndirizzi.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableIndirizzi.getColumnModel().getColumn(0).setResizable(false);
		tableIndirizzi.getColumnModel().getColumn(1).setResizable(false);
		tableIndirizzi.getColumnModel().getColumn(2).setResizable(false);
		tableIndirizzi.getColumnModel().getColumn(3).setResizable(false);
		tableIndirizzi.getColumnModel().getColumn(4).setResizable(false);
		tableIndirizzi.getColumnModel().getColumn(5).setResizable(false);
		
		scrollPaneIndirizzi.setViewportView(tableIndirizzi);
		
		JScrollPane scrollPaneAccounts = new JScrollPane();
		contentPane.add(scrollPaneAccounts, "6, 14, 5, 1, fill, fill");
		
		tableAccounts = new JTable();
		tableAccounts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//da modificare per dare accesso ai DAO
		tableAccounts.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "Messaggio di Benvenuto"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableAccounts.getColumnModel().getColumn(3).setPreferredWidth(200);
		scrollPaneAccounts.setViewportView(tableAccounts);
		
		JLabel labelAccounts = new JLabel("Accounts");
		contentPane.add(labelAccounts, "4, 14, left, top");
		
		JLabel labelIndirizzi = new JLabel("Indirizzi");
		contentPane.add(labelIndirizzi, "4, 12, left, top");
		
		JLabel labelPrefisso = new JLabel("Prefisso Nome");
		contentPane.add(labelPrefisso, "4, 2, left, top");
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
