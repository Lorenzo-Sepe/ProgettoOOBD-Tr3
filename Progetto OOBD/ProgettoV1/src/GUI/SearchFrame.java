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
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private final ButtonGroup buttonGroup =  new ButtonGroup();
	private JTextField textFieldNumero;
	private JTextField textFieldPrefissoNumero;
	 Controller Controller;
	 JFrame FrameChiamante;
	 JFrame frame;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					
//					Controller Controller = new Controller();
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
		frame= this;
		setTitle("Ricerca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tabbedPane, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tabbedPane, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tabbedPane, -35, SpringLayout.SOUTH, contentPane);
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
		
	    modelContatti.addColumn("Id");
	    modelContatti.addColumn("Prefisso Nome");
	    modelContatti.addColumn("Nome");
	    modelContatti.addColumn("Cognome");    
		
		DefaultTableModel modelContatti2 =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }
	        };
		
	    modelContatti2.addColumn("Id");
	    modelContatti2.addColumn("Prefisso Nome");
	    modelContatti2.addColumn("Nome");
	    modelContatti2.addColumn("Cognome");  
	    
	    DefaultTableModel modelContatti3 =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }
	        };
		
	    modelContatti3.addColumn("Id");
	    modelContatti3.addColumn("Prefisso Nome");
	    modelContatti3.addColumn("Nome");
	    modelContatti3.addColumn("Cognome");  
	    
	    DefaultTableModel modelContatti4 =  new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }
	        };
		
	    modelContatti4.addColumn("Id");
	    modelContatti4.addColumn("Prefisso Nome");
	    modelContatti4.addColumn("Nome");
	    modelContatti4.addColumn("Cognome");  
	    
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
		
		tableContatti = new JTable(modelContatti);
		tableContatti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					int row = tableContatti.getSelectedRow();
					if (row>=0) {
						int id = (int)modelContatti.getValueAt(row,0);
						JFrame visualizzaContatto = new VisualizzaContattoFrame(Controller, frame, id);
						visualizzaContatto.setVisible(true);
						frame.setVisible(false);
					}
				}
			}
		});
		tableContatti.removeColumn(tableContatti.getColumnModel().getColumn(0));
		scrollPaneContatti.setViewportView(tableContatti);
		
		JButton buttonSearchNome = new JButton("Ricerca ");
		sl_panelRicercaNome.putConstraint(SpringLayout.WEST, buttonSearchNome, -130, SpringLayout.EAST, panelRicercaNome);
		sl_panelRicercaNome.putConstraint(SpringLayout.EAST, buttonSearchNome, 0, SpringLayout.EAST, scrollPaneContatti);
		buttonSearchNome.setHorizontalAlignment(SwingConstants.LEFT);
		sl_panelRicercaNome.putConstraint(SpringLayout.SOUTH, buttonSearchNome, 30, SpringLayout.NORTH, textFieldPrefissoNome);
		buttonSearchNome.setIcon(new ImageIcon(SearchFrame.class.getResource("/immagini/confirm.png")));
		sl_panelRicercaNome.putConstraint(SpringLayout.NORTH, buttonSearchNome, 0, SpringLayout.NORTH, textFieldPrefissoNome);
		buttonSearchNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	    for (int i = 0; i < modelContatti.getRowCount(); i++) {
		    	    	modelContatti.removeRow(i);
		    	    }
		    	
				
				String Prefisso = textFieldPrefissoNome.getText();
				String Nome = textFieldNome.getText();
				String Cognome = textFieldCognome.getText();
				
				ArrayList<Contatto> RisultatiRicercaAnagrafica =  Controller.SearchAnagrafica(Prefisso,Nome,Cognome);
				//TODO Aggiornare la tabella
				for(int i=0;i<RisultatiRicercaAnagrafica.size();i++) {
					modelContatti.addRow(new Object[]{
							RisultatiRicercaAnagrafica.get(i).getID(),
							RisultatiRicercaAnagrafica.get(i).getPrefissoNome(),
							RisultatiRicercaAnagrafica.get(i).getNome(),
							RisultatiRicercaAnagrafica.get(i).getCognome()
					});
				}
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
		
		tableMail = new JTable(modelContatti2);
		tableMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					int row = tableMail.getSelectedRow();
					if (row >= 0) {
						int id = (int)modelContatti2.getValueAt(row, 0);
						JFrame visualizzaContatto = new VisualizzaContattoFrame(Controller, frame, id);
						visualizzaContatto.setVisible(true);
						frame.setVisible(false);
					}
				}
			}
		});
		
		tableMail.removeColumn(tableMail.getColumnModel().getColumn(0));
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
		buttonRicercaMail.setIcon(new ImageIcon(SearchFrame.class.getResource("/immagini/confirm.png")));
		buttonRicercaMail.setHorizontalAlignment(SwingConstants.LEFT);
		sl_panelRicercaMail.putConstraint(SpringLayout.SOUTH, buttonRicercaMail, 50, SpringLayout.NORTH, panelRicercaMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.EAST, buttonRicercaMail, 0, SpringLayout.EAST, panelRicercaMail);
		buttonRicercaMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 for (int i = 0; i < modelContatti2.getRowCount(); i++) {
		    	    	modelContatti2.removeRow(i);
		    	    }
				
				String mail = textFieldMail.getText();
				ArrayList<Contatto> RisultatiRicercaMail = Controller.searchMail(mail);
				//TODO Aggiornare la tabella 
				
				for(int i=0;i<RisultatiRicercaMail.size();i++) {
					modelContatti2.addRow(new Object[]{
							RisultatiRicercaMail.get(i).getID(),
							RisultatiRicercaMail.get(i).getPrefissoNome(),
							RisultatiRicercaMail.get(i).getNome(),
							RisultatiRicercaMail.get(i).getCognome()
					});
				}
			}
		});
		sl_panelRicercaMail.putConstraint(SpringLayout.NORTH, buttonRicercaMail, 20, SpringLayout.NORTH, panelRicercaMail);
		sl_panelRicercaMail.putConstraint(SpringLayout.WEST, buttonRicercaMail, -130, SpringLayout.EAST, panelRicercaMail);
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
		
		tableAccount = new JTable(modelContatti3);
		tableAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					int row = tableAccount.getSelectedRow();
					if (row >= 0) {
						int id = (int)modelContatti3.getValueAt(row, 0);
						JFrame visualizzaContatto = new VisualizzaContattoFrame(Controller, frame, id);
						visualizzaContatto.setVisible(true);
						frame.setVisible(false);
					}
				}
			}
		});
		tableAccount.removeColumn(tableAccount.getColumnModel().getColumn(0));
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
		buttonRicercaAccount.setIcon(new ImageIcon(SearchFrame.class.getResource("/immagini/confirm.png")));
		buttonRicercaAccount.setHorizontalAlignment(SwingConstants.LEFT);
		sl_panelRicercaAccount.putConstraint(SpringLayout.NORTH, buttonRicercaAccount, 20, SpringLayout.NORTH, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.SOUTH, buttonRicercaAccount, 50, SpringLayout.NORTH, panelRicercaAccount);
		sl_panelRicercaAccount.putConstraint(SpringLayout.EAST, buttonRicercaAccount, 0, SpringLayout.EAST, scrollPane_1);
		buttonRicercaAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 for (int i = 0; i < modelContatti3.getRowCount(); i++) {
		    	    	modelContatti3.removeRow(i);
		    	    }
				
				String nickname = textFieldNickname.getText();
				String fornitore = textFieldFornitore.getText();
				ArrayList<Contatto> RisultatiRicercaAccount = Controller.searchAccount(nickname,fornitore);
				//TODO Aggiornare la tabella 
				
				for(int i=0;i<RisultatiRicercaAccount.size();i++) {
					modelContatti3.addRow(new Object[]{
							RisultatiRicercaAccount.get(i).getID(),
							RisultatiRicercaAccount.get(i).getPrefissoNome(),
							RisultatiRicercaAccount.get(i).getNome(),
							RisultatiRicercaAccount.get(i).getCognome()
					});
				}
			}
		});
		sl_panelRicercaAccount.putConstraint(SpringLayout.WEST, buttonRicercaAccount, -130, SpringLayout.EAST, panelRicercaAccount);
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
		
		tableNumeri = new JTable(modelContatti4);
		tableNumeri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					int row = tableNumeri.getSelectedRow();
					if (row >= 0) {
						int id = (int)modelContatti4.getValueAt(row, 0);
						JFrame visualizzaContatto = new VisualizzaContattoFrame(Controller, frame, id);
						visualizzaContatto.setVisible(true);
						frame.setVisible(false);
					}
				}
			}
		});
		tableNumeri.removeColumn(tableNumeri.getColumnModel().getColumn(0));
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
		sl_panelRicercaNumero.putConstraint(SpringLayout.SOUTH, panelRadioButtons, 110, SpringLayout.NORTH, panelRicercaNumero);
		panelRicercaNumero.add(panelRadioButtons);
		
		JRadioButton radioButtonFisso = new JRadioButton("Numero Fisso");
		radioButtonFisso.setSelected(true);
		buttonGroup.add(radioButtonFisso);
		panelRadioButtons.add(radioButtonFisso);
		
		JRadioButton radioButtonMobile = new JRadioButton("Numero Mobile");
		buttonGroup.add(radioButtonMobile);
		panelRadioButtons.add(radioButtonMobile);
		
		textFieldNumero = new JTextField();
		sl_panelRicercaNumero.putConstraint(SpringLayout.NORTH, panelRadioButtons, 10, SpringLayout.SOUTH, textFieldNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.WEST, panelRadioButtons, 0, SpringLayout.WEST, textFieldNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.EAST, panelRadioButtons, 300, SpringLayout.WEST, textFieldNumero);
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
		buttonRicercaNumeri.setIcon(new ImageIcon(SearchFrame.class.getResource("/immagini/confirm.png")));
		buttonRicercaNumeri.setHorizontalAlignment(SwingConstants.LEFT);
		sl_panelRicercaNumero.putConstraint(SpringLayout.SOUTH, buttonRicercaNumeri, 10, SpringLayout.SOUTH, textFieldPrefissoNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.EAST, buttonRicercaNumeri, 0, SpringLayout.EAST, scrollPaneNumero);
		buttonRicercaNumeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 for (int i = 0; i < modelContatti4.getRowCount(); i++) {
		    	    	modelContatti4.removeRow(i);
		    	    }
				
				String prefissoNumero = textFieldPrefissoNumero.getText();
				String numero = textFieldNumero.getText();
				String tipoNumero;
				if (radioButtonFisso.isSelected()) {
					tipoNumero = "Fisso";
				}else {
					tipoNumero = "Mobile";
				}
				ArrayList<Contatto> RisultatiRicercaNumeri = Controller.searchNumeri(prefissoNumero, numero, tipoNumero);
				//TODO Aggiornare la tabella 
				for(int i=0;i<RisultatiRicercaNumeri.size();i++) {
					modelContatti4.addRow(new Object[]{
							RisultatiRicercaNumeri.get(i).getID(),
							RisultatiRicercaNumeri.get(i).getPrefissoNome(),
							RisultatiRicercaNumeri.get(i).getNome(),
							RisultatiRicercaNumeri.get(i).getCognome()
					});
				}
			}
		});
		sl_panelRicercaNumero.putConstraint(SpringLayout.NORTH, buttonRicercaNumeri, 0, SpringLayout.NORTH, textFieldPrefissoNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.WEST, buttonRicercaNumeri, -130, SpringLayout.EAST, panelRicercaNumero);
		panelRicercaNumero.add(buttonRicercaNumeri);
		
		JLabel lblNewLabel = new JLabel("Inserire tipo numero");
		sl_panelRicercaNumero.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, panelRadioButtons);
		sl_panelRicercaNumero.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, labelPrefissoNumero);
		sl_panelRicercaNumero.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, labelPrefissoNumero);
		panelRicercaNumero.add(lblNewLabel);
		
		buttonClose = new JButton("Indietro");
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonClose, -130, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonClose, 0, SpringLayout.EAST, contentPane);
		buttonClose.setIcon(new ImageIcon(SearchFrame.class.getResource("/immagini/back.jpg")));
		buttonClose.setHorizontalAlignment(SwingConstants.LEFT);
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonClose, -30, SpringLayout.SOUTH, contentPane);
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameChiamante.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonClose, 0, SpringLayout.SOUTH, contentPane);
		contentPane.add(buttonClose);
    	}
	
}