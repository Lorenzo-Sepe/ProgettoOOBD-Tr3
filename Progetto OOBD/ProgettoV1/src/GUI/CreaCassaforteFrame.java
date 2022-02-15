package GUI;



import Model.*;
import Controller.Controller;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 
 * @author AlessandroTrincone
 *
 */
public class CreaCassaforteFrame extends JFrame {

	private JPanel contentPane;
	private JTable ContattiTable;
	private JTable CassaforteTable;
	private JTextField textFieldPassword;
	private JTextField textFieldCassaforte;
	private JButton buttonAnnulla;
	private JButton btnSalva;
	private JButton buttonAggiungi;
	private JButton buttonElimina;
	
	/** The controller. */
	private static Controller controller;
	
	/**Il FrameChiamante 	 */
	static JFrame frameChiamante;
	
	/** The frame. */
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreaCassaforteFrame frame = new CreaCassaforteFrame(controller, frameChiamante);
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
	public CreaCassaforteFrame(Controller controller, JFrame frameChiamante) {
		frame=this;
		setResizable(false);
		setTitle("Crea Cassaforte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNomeGruppo = new JLabel("Nome Gruppo");
		labelNomeGruppo.setBounds(50, 20, 65, 25);
		contentPane.add(labelNomeGruppo);
		
		JScrollPane scrollPaneRubrica = new JScrollPane();
		scrollPaneRubrica.setBounds(15, 85, 390, 190);
		contentPane.add(scrollPaneRubrica);
		
		//Contatti = new JTable();
		DefaultTableModel modelloContatti =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
		ContattiTable = new JTable(modelloContatti);
		ListSelectionModel listenerContattoSelezionato=ContattiTable.getSelectionModel();
		ContattiTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelloContatti.addColumn("prefisso");
		modelloContatti.addColumn("nome"); 
		modelloContatti.addColumn("cognome"); 
		
		ArrayList<String> arrayListContattiNome = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		ArrayList<String> arrayListContattiPrefisso = new ArrayList<>(Arrays.asList("Isabel","Cavaliere","Trincalex","Amante"));
		
		// Append a row 
		for(int i=0;i<arrayListContattiNome.size();i++) {
			modelloContatti.addRow(new Object[]{arrayListContattiPrefisso.get(i), arrayListContattiNome.get(i), arrayListContattiCognome.get(i)});
			
		}
		scrollPaneRubrica.setViewportView(ContattiTable);
		
		JScrollPane scrollPaneCassaforte = new JScrollPane();
		scrollPaneCassaforte.setBounds(530, 85, 390, 190);
		contentPane.add(scrollPaneCassaforte);
		
		//Gruppo = new JTable();
		DefaultTableModel modelloCassaforte =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }};
		
	    CassaforteTable = new JTable(modelloCassaforte);
		ListSelectionModel listenerGruppoSelezionato=CassaforteTable.getSelectionModel();
		CassaforteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		modelloCassaforte.addColumn("prefisso");
		modelloCassaforte.addColumn("nome"); 
		modelloCassaforte.addColumn("cognome"); 
		

		scrollPaneCassaforte.setViewportView(CassaforteTable);
		
		ArrayList <Contatto> MembriCassaforte = new ArrayList<>();
		
		buttonAggiungi = new JButton("Aggiungi ->");
		buttonAggiungi.setBounds(410, 125, 115, 25);
		buttonAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				if (!listenerContattoSelezionato.isSelectionEmpty()) {
					row = ContattiTable.getSelectedRow();
					String prefisso = (String) ContattiTable.getValueAt(row, 0);
					String nome = (String) ContattiTable.getValueAt(row, 1);
					String cognome = (String) ContattiTable.getValueAt(row, 2);
					DefaultTableModel contattiModel = (DefaultTableModel) ContattiTable.getModel();
					contattiModel.removeRow(row);
					modelloCassaforte.addRow(new Object[]{prefisso, nome, cognome});
					//TODO MembriCassaforte.add();
					}
			}
		});
		contentPane.add(buttonAggiungi);
		
		buttonElimina = new JButton("<- Elimina");
		buttonElimina.setBounds(410, 155, 115, 25);
		buttonElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonElimina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				if (!listenerGruppoSelezionato.isSelectionEmpty()) {
					row = CassaforteTable.getSelectedRow();
					String prefisso = (String) CassaforteTable.getValueAt(row, 0);
					String nome = (String) CassaforteTable.getValueAt(row, 1);
					String cognome = (String) CassaforteTable.getValueAt(row, 2);
					DefaultTableModel gruppoModel = (DefaultTableModel) CassaforteTable.getModel();
					gruppoModel.removeRow(row);
					modelloContatti.addRow(new Object[]{prefisso, nome, cognome});
					//TODO MembriCassaforte.remove(object);
				}
			}
		});
		contentPane.add(buttonElimina);
		
		JButton buttonSalva = new JButton("Salva");
		buttonSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO salvataggio della password
				String password = textFieldPassword.getText();
				
			}
		});
		buttonSalva.setBounds(690, 325, 90, -45);
		contentPane.add(buttonSalva);
		
		buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		buttonAnnulla.setBounds(735, 305, 90, 23);
		contentPane.add(buttonAnnulla);
		
		JLabel labelPassword = new JLabel("Inserisci Password");
		labelPassword.setBounds(50, 50, 90, 15);
		contentPane.add(labelPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(175, 50, 230, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		btnSalva = new JButton("Salva");
		btnSalva.setBounds(835, 305, 90, 23);
		contentPane.add(btnSalva);
		
		textFieldCassaforte = new JTextField();
		textFieldCassaforte.setText("");
		textFieldCassaforte.setBounds(175, 25, 230, 20);
		contentPane.add(textFieldCassaforte);
		textFieldCassaforte.setColumns(10);
	}
}