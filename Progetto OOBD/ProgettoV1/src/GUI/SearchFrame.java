package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Controller.Controller;
import Model.*;
import ImplementazioneDAOpostgreSQL.*;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SearchFrame extends JFrame {
	
	private JScrollPane scrollPaneMail;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelRicercaNome;
	private JPanel panelRicercaMail;
	private JPanel panelRicercaAccount;
	private JPanel panelRicercaNumero;
	private JLabel labelPrefissoNome;
	private JTextField textFieldPrefissoNome;
	private JLabel labelNome;
	private JTextField textFieldNome;
	private JLabel labelCognome;
	private JTextField textFieldCognome;
	private JTable tableContatti;
	private JScrollPane scrollPaneContatti;
	private JTable tableMail;
	private JLabel labelMail;
	private JTextField textFieldMail;
	private JButton buttonRicercaMail;
	private JButton buttonClose;
	private JScrollPane scrollPane_1;
	private JTable tableAccount;
	private JScrollPane scrollPaneNumero;
	private JTable tableNumeri;
	private JLabel labelInserireNickname;
	private JLabel labelIserireFornitore;
	private JTextField textFieldNickname;
	private JTextField textFieldFornitore;
	private JButton buttonRicercaAccount;
	private JLabel labelPrefissoNumero;
	private JLabel labelNumero;
	private JPanel panelRadioButtons;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldNumero;
	private JTextField textFieldPrefissoNumero;
	Controller Controller;
	JFrame FrameChiamante;
	JFrame Frame;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SearchFrame frame = new SearchFrame(Controller, FrameChiamante);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public SearchFrame(Controller Controller,	JFrame FrameChiamante ) {
		Frame= this;
		setTitle("Ricerca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tabbedPane, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tabbedPane, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tabbedPane, -30, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tabbedPane, 0, SpringLayout.EAST, contentPane);
		contentPane.add(tabbedPane);
		
		//Ricerca per dati anagrafici
		
		DefaultTableModel modelContatti =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }
	        };
		
	    modelContatti.addColumn("Prefisso Nome");
	    modelContatti.addColumn("Nome");
	    modelContatti.addColumn("Cognome");    
		
		panelRicercaNome = new JPanel();
		tabbedPane.addTab("Ricerca per Nome", null, panelRicercaNome, null);
		SpringLayout sl_panelRicercaNome = new SpringLayout();
		panelRicercaNome.setLayout(sl_panelRicercaNome);
		
		labelPrefissoNome = new JLabel("Inserire Prefisso Nome");
		sl_panelRicercaNome.putConstraint(SpringLayout.NORTH, labelPrefissoNome, 22, SpringLayout.NORTH, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.WEST, labelPrefissoNome, 30, SpringLayout.WEST, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.EAST, labelPrefissoNome, 222, SpringLayout.WEST, panelRicercaNome);
		panelRicercaNome.add(labelPrefissoNome);
		
		textFieldPrefissoNome = new JTextField();
		sl_panelRicercaNome.putConstraint(SpringLayout.NORTH, textFieldPrefissoNome, 20, SpringLayout.NORTH, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.WEST, textFieldPrefissoNome, 200, SpringLayout.WEST, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.EAST, textFieldPrefissoNome, -250, SpringLayout.EAST, panelRicercaNome);
		panelRicercaNome.add(textFieldPrefissoNome);
		textFieldPrefissoNome.setColumns(10);
		
		labelNome = new JLabel("Inserire Nome");
		sl_panelRicercaNome.putConstraint(SpringLayout.NORTH, labelNome, 52, SpringLayout.NORTH, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.WEST, labelNome, 30, SpringLayout.WEST, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.EAST, labelNome, 222, SpringLayout.WEST, panelRicercaNome);
		panelRicercaNome.add(labelNome);
		
		textFieldNome = new JTextField();
		sl_panelRicercaNome.putConstraint(SpringLayout.NORTH, textFieldNome, 50, SpringLayout.NORTH, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.WEST, textFieldNome, 200, SpringLayout.WEST, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.EAST, textFieldNome, -250, SpringLayout.EAST, panelRicercaNome);
		panelRicercaNome.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		labelCognome = new JLabel("Inserire Cognome");
		sl_panelRicercaNome.putConstraint(SpringLayout.NORTH, labelCognome, 82, SpringLayout.NORTH, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.WEST, labelCognome, 30, SpringLayout.WEST, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.EAST, labelCognome, 222, SpringLayout.WEST, panelRicercaNome);
		panelRicercaNome.add(labelCognome);
		
		textFieldCognome = new JTextField();
		sl_panelRicercaNome.putConstraint(SpringLayout.NORTH, textFieldCognome, 80, SpringLayout.NORTH, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.WEST, textFieldCognome, 200, SpringLayout.WEST, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.EAST, textFieldCognome, -250, SpringLayout.EAST, panelRicercaNome);
		panelRicercaNome.add(textFieldCognome);
		textFieldCognome.setColumns(10);
		
		scrollPaneContatti = new JScrollPane();
		sl_panelRicercaNome.putConstraint(SpringLayout.NORTH, scrollPaneContatti, 20, SpringLayout.SOUTH, textFieldCognome);
		sl_panelRicercaNome.putConstraint(SpringLayout.WEST, scrollPaneContatti, 0, SpringLayout.WEST, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.SOUTH, scrollPaneContatti, 333, SpringLayout.NORTH, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.EAST, scrollPaneContatti, 0, SpringLayout.EAST, panelRicercaNome);
		panelRicercaNome.add(scrollPaneContatti);
		
		tableContatti = new JTable(modelContatti );
		scrollPaneContatti.setViewportView(tableContatti);
		
		JButton buttonSearchNome = new JButton("Ricerca ");
		sl_panelRicercaNome.putConstraint(SpringLayout.NORTH, buttonSearchNome, 0, SpringLayout.NORTH, textFieldPrefissoNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.WEST, buttonSearchNome, -130, SpringLayout.EAST, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.SOUTH, buttonSearchNome, 0, SpringLayout.SOUTH, textFieldPrefissoNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.EAST, buttonSearchNome, -30, SpringLayout.EAST, panelRicercaNome);
		buttonSearchNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Prefisso = textFieldPrefissoNome.getText();
				String Nome = textFieldNome.getText();
				String Cognome = textFieldCognome.getText();
				
				ArrayList<Contatto> RisultatiRicercaAnagrafica =  Controller.SearchAnagrafica(Prefisso,Nome,Cognome);
				//TODO Aggiornare la tabella
			}
		});
		panelRicercaNome.add(buttonSearchNome);
		
		//Ricerca per Mail
		
		scrollPaneMail = new JScrollPane();
		
		panelRicercaMail = new JPanel();
		tabbedPane.addTab("Ricerca per E-Mail", null, panelRicercaMail, null);
		SpringLayout sl_panelRicercaMail = new SpringLayout();
		panelRicercaMail.setLayout(sl_panelRicercaMail);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panelRicercaMail.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panelRicercaMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.SOUTH, scrollPane, 333, SpringLayout.NORTH, panelRicercaMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, panelRicercaMail);
		panelRicercaMail.add(scrollPane);
		
		tableMail = new JTable(modelContatti);
		scrollPane.setViewportView(tableMail);
		
		labelMail = new JLabel("Inserire E-Mail");
		sl_panelRicercaMail.putConstraint(SpringLayout.NORTH, labelMail, 22, SpringLayout.NORTH, panelRicercaMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.WEST, labelMail, 30, SpringLayout.WEST, panelRicercaMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.EAST, labelMail, 150, SpringLayout.WEST, panelRicercaMail);
		panelRicercaMail.add(labelMail);
		
		textFieldMail = new JTextField();
		sl_panelRicercaMail.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.SOUTH, textFieldMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.NORTH, textFieldMail, 20, SpringLayout.NORTH, panelRicercaMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.WEST, textFieldMail, 200, SpringLayout.WEST, panelRicercaMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.EAST, textFieldMail, -250, SpringLayout.EAST, panelRicercaMail);
		panelRicercaMail.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		buttonRicercaMail = new JButton("Ricerca");
		buttonRicercaMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mail = textFieldMail.getText();
				ArrayList<Contatto> RisultatiRicercaMail = Controller.searchMail(mail);
				//TODO Aggiornare la tabella 
			}
		});
		sl_panelRicercaMail.putConstraint(SpringLayout.NORTH, buttonRicercaMail, 20, SpringLayout.NORTH, panelRicercaMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.WEST, buttonRicercaMail, -130, SpringLayout.EAST, panelRicercaMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.SOUTH, buttonRicercaMail, 0, SpringLayout.SOUTH, textFieldMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.EAST, buttonRicercaMail, -30, SpringLayout.EAST, panelRicercaMail);
		panelRicercaMail.add(buttonRicercaMail);
		
		//Ricerca per Account
		
		panelRicercaAccount = new JPanel();
		tabbedPane.addTab("Ricerca per Account", null, panelRicercaAccount, null);
		SpringLayout sl_panelRicercaAccount = new SpringLayout();
		panelRicercaAccount.setLayout(sl_panelRicercaAccount);
		
		scrollPane_1 = new JScrollPane();
		sl_panelRicercaAccount.putConstraint(SpringLayout.WEST, scrollPane_1, 0, SpringLayout.WEST, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.SOUTH, scrollPane_1, 0, SpringLayout.SOUTH, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.EAST, scrollPane_1, 0, SpringLayout.EAST, panelRicercaAccount);
		panelRicercaAccount.add(scrollPane_1);
		
		tableAccount = new JTable(modelContatti);
		scrollPane_1.setViewportView(tableAccount);
		
		labelInserireNickname = new JLabel("Inserire Nickname");
		sl_panelRicercaAccount.putConstraint(SpringLayout.NORTH, labelInserireNickname, 22, SpringLayout.NORTH, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.WEST, labelInserireNickname, 30, SpringLayout.WEST, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.EAST, labelInserireNickname, 200, SpringLayout.WEST, panelRicercaAccount);
		panelRicercaAccount.add(labelInserireNickname);
		
		labelIserireFornitore = new JLabel("Inserire Fornitore");
		sl_panelRicercaAccount.putConstraint(SpringLayout.NORTH, labelIserireFornitore, 53, SpringLayout.NORTH, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.WEST, labelIserireFornitore, 30, SpringLayout.WEST, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.EAST, labelIserireFornitore, 200, SpringLayout.WEST, panelRicercaAccount);
		panelRicercaAccount.add(labelIserireFornitore);
		
		textFieldNickname = new JTextField();
		sl_panelRicercaAccount.putConstraint(SpringLayout.NORTH, textFieldNickname, 20, SpringLayout.NORTH, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.WEST, textFieldNickname, 200, SpringLayout.WEST, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.EAST, textFieldNickname, -250, SpringLayout.EAST, panelRicercaAccount);
		panelRicercaAccount.add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		textFieldFornitore = new JTextField();
		sl_panelRicercaAccount.putConstraint(SpringLayout.NORTH, scrollPane_1, 20, SpringLayout.SOUTH, textFieldFornitore);
		sl_panelRicercaAccount.putConstraint(SpringLayout.NORTH, textFieldFornitore, 50, SpringLayout.NORTH, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.WEST, textFieldFornitore, 200, SpringLayout.WEST, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.EAST, textFieldFornitore, -250, SpringLayout.EAST, panelRicercaAccount);
		panelRicercaAccount.add(textFieldFornitore);
		textFieldFornitore.setColumns(10);
		
		buttonRicercaAccount = new JButton("Ricerca");
		sl_panelRicercaAccount.putConstraint(SpringLayout.NORTH, buttonRicercaAccount, 20, SpringLayout.NORTH, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.WEST, buttonRicercaAccount, -130, SpringLayout.EAST, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.SOUTH, buttonRicercaAccount, 0, SpringLayout.SOUTH, textFieldNickname);
		sl_panelRicercaAccount.putConstraint(SpringLayout.EAST, buttonRicercaAccount, -30, SpringLayout.EAST, panelRicercaAccount);
		panelRicercaAccount.add(buttonRicercaAccount);
		
		panelRicercaNumero = new JPanel();
		tabbedPane.addTab("Ricerca per Numero", null, panelRicercaNumero, null);
		SpringLayout sl_panelRicercaNumero = new SpringLayout();
		panelRicercaNumero.setLayout(sl_panelRicercaNumero);
		
		scrollPaneNumero = new JScrollPane();
		sl_panelRicercaNumero.putConstraint(SpringLayout.WEST, scrollPaneNumero, 0, SpringLayout.WEST, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.SOUTH, scrollPaneNumero, 0, SpringLayout.SOUTH, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.EAST, scrollPaneNumero, 0, SpringLayout.EAST, panelRicercaNumero);
		panelRicercaNumero.add(scrollPaneNumero);
		
		tableNumeri = new JTable(modelContatti);
		scrollPaneNumero.setViewportView(tableNumeri);
		
		labelPrefissoNumero = new JLabel("Inserire Prefisso Numero");
		sl_panelRicercaNumero.putConstraint(SpringLayout.NORTH, labelPrefissoNumero, 22, SpringLayout.NORTH, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.WEST, labelPrefissoNumero, 30, SpringLayout.WEST, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.EAST, labelPrefissoNumero, 200, SpringLayout.WEST, panelRicercaNumero);
		panelRicercaNumero.add(labelPrefissoNumero);
		
		labelNumero = new JLabel("Inserire Numero Telefonico");
		sl_panelRicercaNumero.putConstraint(SpringLayout.NORTH, labelNumero, 52, SpringLayout.NORTH, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.WEST, labelNumero, 30, SpringLayout.WEST, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.EAST, labelNumero, 200, SpringLayout.WEST, panelRicercaNumero);
		panelRicercaNumero.add(labelNumero);
		
		panelRadioButtons = new JPanel();
		sl_panelRicercaNumero.putConstraint(SpringLayout.NORTH, scrollPaneNumero, 20, SpringLayout.SOUTH, panelRadioButtons);
		sl_panelRicercaNumero.putConstraint(SpringLayout.NORTH, panelRadioButtons, 80, SpringLayout.NORTH, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.WEST, panelRadioButtons, 30, SpringLayout.WEST, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.SOUTH, panelRadioButtons, 110, SpringLayout.NORTH, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.EAST, panelRadioButtons, 347, SpringLayout.WEST, panelRicercaNumero);
		panelRicercaNumero.add(panelRadioButtons);
		
		JRadioButton radioButtonFisso = new JRadioButton("Numero Fisso");
		radioButtonFisso.setSelected(true);
		buttonGroup.add(radioButtonFisso);
		panelRadioButtons.add(radioButtonFisso);
		
		JRadioButton radioButtonMobile = new JRadioButton("Numero Mobile");
		buttonGroup.add(radioButtonMobile);
		panelRadioButtons.add(radioButtonMobile);
		
		textFieldNumero = new JTextField();
		sl_panelRicercaNumero.putConstraint(SpringLayout.NORTH, textFieldNumero, 50, SpringLayout.NORTH, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.WEST, textFieldNumero, 200, SpringLayout.WEST, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.EAST, textFieldNumero, -250, SpringLayout.EAST, panelRicercaNumero);
		panelRicercaNumero.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		textFieldPrefissoNumero = new JTextField();
		sl_panelRicercaNumero.putConstraint(SpringLayout.NORTH, textFieldPrefissoNumero, 20, SpringLayout.NORTH, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.WEST, textFieldPrefissoNumero, 200, SpringLayout.WEST, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.EAST, textFieldPrefissoNumero, -250, SpringLayout.EAST, panelRicercaNumero);
		panelRicercaNumero.add(textFieldPrefissoNumero);
		textFieldPrefissoNumero.setColumns(10);
		
		JButton buttonRicercaNumeri = new JButton("Ricerca");
		sl_panelRicercaNumero.putConstraint(SpringLayout.NORTH, buttonRicercaNumeri, 0, SpringLayout.NORTH, textFieldPrefissoNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.WEST, buttonRicercaNumeri, -130, SpringLayout.EAST, panelRicercaNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.SOUTH, buttonRicercaNumeri, 0, SpringLayout.SOUTH, textFieldPrefissoNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.EAST, buttonRicercaNumeri, -30, SpringLayout.EAST, panelRicercaNumero);
		panelRicercaNumero.add(buttonRicercaNumeri);
		
		buttonClose = new JButton("Indietro");
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameChiamante.setVisible(true);
				Frame.setVisible(false);
				Frame.dispose();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonClose, 10, SpringLayout.SOUTH, tabbedPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonClose, -90, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonClose, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonClose, -5, SpringLayout.EAST, contentPane);
		contentPane.add(buttonClose);
	}
}
