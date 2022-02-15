package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class AggiungiAccountFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textNickname;
	private JTextField textMail;
	private JTextField textFornitore;
	private JLabel lblNewLabel_3;
	private JTable table;

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
					AggiungiAccountFrame frame = new AggiungiAccountFrame();
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
	public AggiungiAccountFrame() {
		frame=this;
		setResizable(false);
		setTitle("Creazione Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserire il nickname");
		lblNewLabel.setBounds(25, 10, 120, 20);
		contentPane.add(lblNewLabel);
		
		textNickname = new JTextField();
		textNickname.setBounds(155, 10, 200, 20);
		contentPane.add(textNickname);
		textNickname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Inserire la mail");
		lblNewLabel_1.setBounds(25, 35, 120, 20);
		contentPane.add(lblNewLabel_1);
		
		textMail = new JTextField();
		textMail.setBounds(155, 35, 200, 20);
		contentPane.add(textMail);
		textMail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Inserire il fornitore");
		lblNewLabel_2.setBounds(25, 60, 120, 20);
		contentPane.add(lblNewLabel_2);
		
		textFornitore = new JTextField();
		textFornitore.setBounds(155, 60, 200, 20);
		contentPane.add(textFornitore);
		textFornitore.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Inserire la frase\r\n di benvenuto");
		lblNewLabel_3.setBounds(25, 90, 170, 50);
		contentPane.add(lblNewLabel_3);
		
		table = new JTable();
		table.setBounds(25, 145, 400, 65);
		contentPane.add(table);
		
		JButton btnNewButton = new JButton("Annulla");
		btnNewButton.setBounds(25, 220, 90, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Conferma");
		btnNewButton_1.setBounds(320, 220, 105, 25);
		contentPane.add(btnNewButton_1);
	}
}
