package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class ModificaMailFrame extends JFrame {

	/** The controller. */
	private static Controller controller;
	
	/** The frame. */
	JFrame frame;
	
	private JPanel contentPane;
	private JTextField textFieldNuovaMail;
	private ArrayList<String> listaMail = new ArrayList<>();
	

	private static JFrame frameChiamante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					ModificaMailFrame frame = new ModificaMailFrame(controller, frameChiamante);
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
	public ModificaMailFrame(Controller c, JFrame frameChiamante) {
		//listaMail = Controller.RiempiListaMail(listaMail);
		setTitle("ModificaMail");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 90, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 115, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 110, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, 300, SpringLayout.WEST, contentPane);
		contentPane.add(scrollPane);
		
		//TODO
		DefaultListModel<String> listMailModel = new DefaultListModel<String>();		
		JList<String> listMail = new JList<String>(listMailModel);
		listMail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMail.setVisibleRowCount(1);
		ArrayList<String> arrayListContatti = new ArrayList<>(Arrays.asList("Rai", "Lore", "Ale","Jesico"));
		for(String s :  arrayListContatti ) {
			 listMailModel.addElement(s);
		}
		/**listMail.setModel(new AbstractListModel() {
			String[] values = new String[] {"test ", "test", "test"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});*/
		scrollPane.setViewportView(listMail);
		
		textFieldNuovaMail = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldNuovaMail, 35, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldNuovaMail, 115, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textFieldNuovaMail, -35, SpringLayout.NORTH, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldNuovaMail, 300, SpringLayout.WEST, contentPane);
		contentPane.add(textFieldNuovaMail);
		textFieldNuovaMail.setColumns(10);
		
		JLabel labelMail = new JLabel("Inserisci E-Mail");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelMail, 37, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelMail, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelMail, 107, SpringLayout.WEST, contentPane);
		contentPane.add(labelMail);
		
		JButton buttonNuovaMail = new JButton("Aggiungi E-Mail");
		buttonNuovaMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NuovaMail = textFieldNuovaMail.getText();
				listMailModel.addElement(NuovaMail);
				//TODO mandare le nuove info nel database e model
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonNuovaMail, 33, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonNuovaMail, 350, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonNuovaMail, -15, SpringLayout.EAST, contentPane);
		contentPane.add(buttonNuovaMail);
		
		JButton buttonSovrascrivi = new JButton("Sovrascrivi E-Mail");
		buttonSovrascrivi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isSelected = listMail.getSelectedIndex();
				if(isSelected == -1) {
					return;
				}  
				String NuovaMail = textFieldNuovaMail.getText();
				//TODO mandare le nuove info nel database e model
				listMailModel.setElementAt(NuovaMail,isSelected);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonSovrascrivi, 83, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonSovrascrivi, 350, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonSovrascrivi, -15, SpringLayout.EAST, contentPane);
		contentPane.add(buttonSovrascrivi);
		
		JLabel labelMailContatto = new JLabel("E-Mail del contatto");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelMailContatto, 94, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelMailContatto, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelMailContatto, 107, SpringLayout.WEST, contentPane);
		contentPane.add(labelMailContatto);
		
		JButton buttonIndietro = new JButton("Indietro");
		buttonIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					frameChiamante.setVisible(true);
					frame.setVisible(false);
					frame.dispose();
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonIndietro, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonIndietro, 0, SpringLayout.EAST, buttonNuovaMail);
		contentPane.add(buttonIndietro);
	}
}
