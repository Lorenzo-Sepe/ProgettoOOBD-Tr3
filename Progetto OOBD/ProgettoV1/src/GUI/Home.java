package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import controller.Controller;
import model.Contatto;

import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

public class Home extends JFrame {

	private JPanel contentPane;
	static Controller controller;
	private JScrollPane scrollPaneRubrica;
	private JTable tblTaskList;
	private Contatto contatto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home(controller);
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
	public Home(Controller controller ) {
		setResizable(false);
		setTitle("Rubrica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuGruppi = new JMenu("Gruppi");
		menuBar.add(menuGruppi);
		
		JMenuItem menuItemCreaGruppo = new JMenuItem("Crea Gruppo");
		menuGruppi.add(menuItemCreaGruppo);
		
		JMenuItem menuItemVisualizza = new JMenuItem("Visualizza Gruppi");
		menuGruppi.add(menuItemVisualizza);
		
		JMenu menuCassaforte = new JMenu("Cassaforti");
		menuBar.add(menuCassaforte);
		
		JMenuItem menuItemCreaCassaforte = new JMenuItem("Crea Cassaforte");
		menuCassaforte.add(menuItemCreaCassaforte);
		
		JMenuItem menuItemVisualizzaCassaforte = new JMenuItem("Visualizza Cassaforti");
		menuCassaforte.add(menuItemVisualizzaCassaforte);
		
		JMenu menuQuit = new JMenu("Esci");
		menuBar.add(menuQuit);
		
		JMenuItem menuItemChiudiProgramma = new JMenuItem("Chiudi Programma");
		menuQuit.add(menuItemChiudiProgramma);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		ArrayList<String> arrayListContatti = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		
		
			scrollPaneRubrica = new JScrollPane();
			sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneRubrica, 0, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneRubrica, 0, SpringLayout.WEST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneRubrica, 0, SpringLayout.SOUTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneRubrica, 0, SpringLayout.EAST, contentPane);
			contentPane.add(scrollPaneRubrica);
			
			DefaultTableModel model =  new DefaultTableModel() {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		           //all cells false
		           return false;
		        }};
		    tblTaskList = new JTable(model);
		   	ListSelectionModel listener=tblTaskList.getSelectionModel();
			tblTaskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			model.addColumn("nome"); 
			model.addColumn("cognome"); 
			
			// Append a row 
			for(int i=0;i<arrayListContatti.size();i++) {
				model.addRow(new Object[]{arrayListContatti.get(i), arrayListContattiCognome.get(i)});
				
			}
			
			tblTaskList.addMouseListener(new MouseAdapter() {
				  public void mouseClicked(MouseEvent e) {
					  if(e.getClickCount()==2) {
						 //TODO manda al frame visualizza contatto
						  JTable target =(JTable)e.getSource();
						  int row=target.getSelectedRow();
						  JOptionPane.showMessageDialog(null, "Selected Row "+row);
						  //ContattoID = c.getListaContatti.get(row).getID;
						  //VisualizzaContattoFrame frameVisualizza = new VisualizzaContattoFrame(c, frameChiamante, ContattoID);
							//frame.setVisible(true);
					  }
				  }
			  });
			scrollPaneRubrica.setViewportView( tblTaskList);
			
}
}