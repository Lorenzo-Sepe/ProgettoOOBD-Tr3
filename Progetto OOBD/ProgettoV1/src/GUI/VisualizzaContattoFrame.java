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


public class VisualizzaContattoFrame extends JFrame {

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelFoto = new JPanel();
		
		JLabel labelFoto = new JLabel();
		
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
				RowSpec.decode("50dlu"),
				RowSpec.decode("10px"),
				RowSpec.decode("50px"),
				RowSpec.decode("10px"),
				RowSpec.decode("50px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JTextPane textPanePrefisso = new JTextPane();
		textPanePrefisso.setEditable(false);
		contentPane.add(textPanePrefisso, "6, 2, 2, 1, default, fill");
		
		JTextPane textPaneNome = new JTextPane();
		textPaneNome.setEditable(false);
		contentPane.add(textPaneNome, "6, 4, 2, 1, default, fill");
		
		JTextPane textPaneCognome = new JTextPane();
		textPaneCognome.setEditable(false);
		contentPane.add(textPaneCognome, "6, 6, 2, 1, default, fill");
		
		JScrollPane scrollPaneNumeri = new JScrollPane();
		contentPane.add(scrollPaneNumeri, "6, 8, 3, 1, fill, fill");
		
		tableNumeri = new JTable();
		tableNumeri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableNumeri.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Numero", "Tipo Numero", "Principale"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableNumeri.getColumnModel().getColumn(0).setResizable(false);
		tableNumeri.getColumnModel().getColumn(1).setResizable(false);
		tableNumeri.getColumnModel().getColumn(2).setResizable(false);
		scrollPaneNumeri.setViewportView(tableNumeri);
		
		JButton modFotoButton = new JButton("Modifica Foto");
		contentPane.add(modFotoButton, "2, 10, left, top");
		contentPane.add(panelFoto, "2, 2, 1, 7, fill, fill");
	
		panelFoto.setLayout(null);
		ImageIcon  iconFoto = new ImageIcon(VisualizzaContattoFrame.class.getResource("/imagini/User2.png"));
		Image image = (iconFoto).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		iconFoto = new ImageIcon(image);
		panelFoto.add(labelFoto);
		
				//panelFoto.add(iconFoto);
		contentPane.add(labelCognome, "4, 6, left, top");
		contentPane.add(labelNome, "4, 4, left, top");
		contentPane.add(labelNumeriTelefono, "4, 8, fill, top");
		
		JScrollPane scrollPaneIndirizzi = new JScrollPane();
		contentPane.add(scrollPaneIndirizzi, "6, 10, 5, 1, fill, fill");
		
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
		
		JLabel labelAccounts = new JLabel("Accounts");
		contentPane.add(labelAccounts, "4, 12, left, top");
		
		JLabel labelIndirizzi = new JLabel("Indirizzi");
		contentPane.add(labelIndirizzi, "4, 10, left, top");
		
		JLabel labelPrefisso = new JLabel("Prefisso Nome");
		contentPane.add(labelPrefisso, "4, 2, left, top");
		
		JScrollPane scrollPaneAccounts = new JScrollPane();
		contentPane.add(scrollPaneAccounts, "6, 12, 5, 1, fill, fill");
		
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
	}
}
