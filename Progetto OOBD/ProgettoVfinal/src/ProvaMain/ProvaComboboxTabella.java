package ProvaMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class ProvaComboboxTabella extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProvaComboboxTabella frame = new ProvaComboboxTabella();
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
	public ProvaComboboxTabella() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JComboBox<String> comboProva = new JComboBox<String>();
		comboProva.addItem("ciao");
		comboProva.addItem("mondo");
		
		DefaultTableModel modelTabella = new DefaultTableModel() {
			@Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
		}};
		
		modelTabella.addColumn("nome");
		modelTabella.addColumn("combo");
		
		modelTabella.addRow(new Object[] {
				"ciao mondo"});
		modelTabella.setValueAt(comboProva, 0, 1);
		
		table = new JTable(modelTabella);
		scrollPane.setViewportView(table);
	}

}
