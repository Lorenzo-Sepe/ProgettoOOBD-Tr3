package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
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

public class Home1 extends JFrame {

	private JPanel contentPane;
	private JTable tblTaskList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home1 frame = new Home1();
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
	public Home1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Vis Gruppo");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("******");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Quit");
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, BorderLayout.CENTER);
		
		ArrayList<String> arrayListContatti = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		ArrayList<String> arrayListContattiCognome = new ArrayList<>(Arrays.asList("Mor", "Sep", "Tri","Cal"));
		
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
	

		/*listener.addListSelectionListener(new ListSelectionListener() {

		      @Override
		      public void valueChanged(ListSelectionEvent e) {

		        // JUST IGNORE WHEN USER HAS ATLEAST ONE SELECTION
		        if(e.getValueIsAdjusting())
		        {
		          return;
		        }
		        ListSelectionModel lsm=(ListSelectionModel) e.getSource();

		        if(lsm.isSelectionEmpty())
		        {
		          JOptionPane.showMessageDialog(null, "No selection");
		        }else
		        {
		          int selectedRow=lsm.getMinSelectionIndex();
		          JOptionPane.showMessageDialog(null, "Selected Row "+selectedRow);
		        }
		      }
		    });
*/
		  tblTaskList.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				  if(e.getClickCount()==2) {
					  JTable target =(JTable)e.getSource();
					  int row=target.getSelectedRow();
					  row = row+1;
					  JOptionPane.showMessageDialog(null, "Selected Row "+row);
				  }
			  }
		  });

		
		
	   
		contentPane.add(tblTaskList, BorderLayout.SOUTH);
		
	}
	public boolean isCellEditable(int row, int column){
        return false;
    }
	
}
