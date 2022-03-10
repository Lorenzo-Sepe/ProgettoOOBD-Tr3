package GUI;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Account;


@SuppressWarnings("serial")
public class GestioneDuplicatiAccountFrame extends JFrame {

	private JPanel contentPane;
	private GestioneDuplicatiAccountFrame frame;
	private ArrayList<String> localList=new ArrayList<>();
	private JPanel panelLista;
	private JLabel lblmail;
	private JScrollPane scrollPane;
	private JTable tableAccount;
	private JLabel lblContatti;
	private JToolBar toolBar;
	private JButton btnBack;
	private JButton btnModifica;
	private JButton btnElimina;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					gestioneDuplicatiAccountFrame frame = new gestioneDuplicatiAccountFrame();
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
	public GestioneDuplicatiAccountFrame(Controller c, JFrame frameChiamante, ArrayList<String> listaRisultati) {
		frame = this;
		localList = listaRisultati;
				
		setTitle("Controllo duplicati");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		panelLista = new JPanel();
		contentPane.add(panelLista);
		SpringLayout sl_panelLista = new SpringLayout();
		panelLista.setLayout(sl_panelLista);
		
		
		
		lblmail = new JLabel("Mail duplicate");
		sl_panelLista.putConstraint(SpringLayout.NORTH, lblmail, 0, SpringLayout.NORTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.WEST, lblmail, 100, SpringLayout.WEST, panelLista);
		sl_panelLista.putConstraint(SpringLayout.SOUTH, lblmail, 25, SpringLayout.NORTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.EAST, lblmail, -10, SpringLayout.EAST, panelLista);
		panelLista.add(lblmail);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_panelLista.putConstraint(SpringLayout.NORTH, scrollPane_1, 30, SpringLayout.NORTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.WEST, scrollPane_1, 5, SpringLayout.WEST, panelLista);
		sl_panelLista.putConstraint(SpringLayout.SOUTH, scrollPane_1, -40, SpringLayout.SOUTH, panelLista);
		sl_panelLista.putConstraint(SpringLayout.EAST, scrollPane_1, -5, SpringLayout.EAST, panelLista);
		panelLista.add(scrollPane_1);
		
DefaultTableModel modelloAccount = new DefaultTableModel () {
			
	        public boolean isCellEditable(int row, int column) {
	           return false;
	        }

		};		
		
DefaultTableModel modelloRisultati = new DefaultTableModel () {
			
	        public boolean isCellEditable(int row, int column) {
	           return false;
	        }     
	        
		};

		modelloRisultati.addColumn("E-Mail");
		for (int i = 0; i < listaRisultati.size(); i++) {
			modelloRisultati.addRow(new Object[]{

			listaRisultati.get(i)
			});
		}
		
		
		
		table = new JTable(modelloRisultati);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				String mailSelezionata = (String) table.getValueAt(row, column);
				int cont = modelloAccount.getRowCount();
				while (cont>0) {
					int i=0;
					modelloAccount.removeRow(i);
					cont--;
				}
								
				ArrayList <Account> listaRisultati = c.verificaDuplicatiAccount(mailSelezionata);
				for (int i = 0; i < listaRisultati.size(); i++) {
					modelloAccount.addRow(new Object[]{
							
							listaRisultati.get(i).getID(),
							listaRisultati.get(i).getAssociato(),
							listaRisultati.get(i).getFornitore(),
							listaRisultati.get(i).getNickname(),
							listaRisultati.get(i).getMail(),
							listaRisultati.get(i).getBenvenuto()
					});
				}
			}
		});
		scrollPane_1.setViewportView(table);
		
		
		
		JPanel panelTabella = new JPanel();
		contentPane.add(panelTabella);
		SpringLayout sl_panelTabella = new SpringLayout();
		panelTabella.setLayout(sl_panelTabella);
		
		scrollPane = new JScrollPane();
		sl_panelTabella.putConstraint(SpringLayout.NORTH, scrollPane, 30, SpringLayout.NORTH, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.SOUTH, scrollPane, -40, SpringLayout.SOUTH, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.EAST, scrollPane, -5, SpringLayout.EAST, panelTabella);
		panelTabella.add(scrollPane);
		
		
		modelloAccount.addColumn("IDaccount");
		modelloAccount.addColumn("ID Contatto Associato");
		modelloAccount.addColumn("Fornitore");
		modelloAccount.addColumn("Nickname"); 
		modelloAccount.addColumn("Indirizzo e-mail");
		modelloAccount.addColumn("Frase di benvenuto"); 
		
		
		tableAccount = new JTable(modelloAccount);
		tableAccount.removeColumn(tableAccount.getColumnModel().getColumn(1));
		tableAccount.removeColumn(tableAccount.getColumnModel().getColumn(0));
		final ListSelectionModel listenerAccount = tableAccount.getSelectionModel();
		tableAccount.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableAccount);
		
		lblContatti = new JLabel("Account della mail");
		sl_panelTabella.putConstraint(SpringLayout.NORTH, lblContatti, 0, SpringLayout.NORTH, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.WEST, lblContatti, 100, SpringLayout.WEST, scrollPane);
		sl_panelTabella.putConstraint(SpringLayout.SOUTH, lblContatti, 25, SpringLayout.NORTH, panelTabella);
		sl_panelTabella.putConstraint(SpringLayout.EAST, lblContatti, 0, SpringLayout.EAST, panelTabella);
		panelTabella.add(lblContatti);
		
		toolBar = new JToolBar();
		sl_panelTabella.putConstraint(SpringLayout.NORTH, toolBar, 0, SpringLayout.SOUTH, scrollPane);
		sl_panelTabella.putConstraint(SpringLayout.WEST, toolBar, 0, SpringLayout.WEST, scrollPane);
		sl_panelTabella.putConstraint(SpringLayout.SOUTH, toolBar, 0, SpringLayout.SOUTH, panelTabella);
		panelTabella.add(toolBar);
		
		btnBack = new JButton("Indietro");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameChiamante.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnBack.setIcon(new ImageIcon(GestioneDuplicatiAccountFrame.class.getResource("/Immagini/back.jpg")));
		toolBar.add(btnBack);
		
		btnModifica = new JButton("Modifica Account");
		btnModifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!listenerAccount.isSelectionEmpty()) {
					c.transactionBegin();
					int row = tableAccount.getSelectedRow();
					int column = 0;
					int columnID =(int) modelloAccount.getValueAt(row, 1) ;
					c.dumpContatto(columnID);
					ArrayList<String> listaMail=c.getInfoContattoMailList((int) modelloAccount.getValueAt(row, 1) );
					AggiungiAccountPanel panelModificaAccount = new AggiungiAccountPanel(listaMail);
					panelModificaAccount.setAll(modelloAccount.getValueAt(row, 3).toString(), modelloAccount.getValueAt(row, 4).toString(),modelloAccount.getValueAt(row, 2).toString(), modelloAccount.getValueAt(row, 5).toString());
					int results = JOptionPane.showConfirmDialog(null, panelModificaAccount,"Modifica Account",JOptionPane.OK_CANCEL_OPTION);
					
					if (results == JOptionPane.OK_OPTION) {
						column = 2;
						modelloAccount.setValueAt(panelModificaAccount.getFornitore(), row, column++);
						modelloAccount.setValueAt(panelModificaAccount.getNickname(), row, column++);
						modelloAccount.setValueAt(panelModificaAccount.getMail(), row, column++);
						modelloAccount.setValueAt(panelModificaAccount.getFraseDiBenvenuto(), row, column++);
					}
					try {
						Integer oldID = (int) modelloAccount.getValueAt(row, 0);
						String	fornitore=modelloAccount.getValueAt(row, 2).toString();
						String nickname=  modelloAccount.getValueAt(row, 3).toString();
						String mail=modelloAccount.getValueAt(row, 4).toString();//vero
						String benvenuto = modelloAccount.getValueAt(row, 5).toString();
						c.modificaAccount(columnID, oldID, fornitore, nickname,mail,benvenuto);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					localList=aggiornaTabella(c, localList);
					c.transactionCommit();
				}
			}
		});
		btnModifica.setIcon(new ImageIcon(GestioneDuplicatiContattiFrame.class.getResource("/immagini/edit.png")));
		toolBar.add(btnModifica);
		
		btnElimina = new JButton("Elimina Account");
		btnElimina.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idOld = (int) tableAccount.getModel().getValueAt(tableAccount.getSelectedRow(),0);
                try {
                	c.transactionBegin();
                    c.eliminaAccount(idOld);
                    int row;
                    if (!listenerAccount.isSelectionEmpty()) {
                        row = tableAccount.getSelectedRow();
                        modelloAccount.removeRow(row);
                    }
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
                c.transactionCommit();
            }
        });
		btnElimina.setIcon(new ImageIcon(GestioneDuplicatiContattiFrame.class.getResource("/immagini/elimina.jpg")));
		toolBar.add(btnElimina);
        
		
	}

	
	
	protected ArrayList<String> aggiornaTabella(Controller c, ArrayList<String> mailList) {
		ArrayList<String> newListaMail = c.verificaMailDuplicate();
		if( newListaMail.equals(mailList)==true ) {
			return mailList;
		}else {
				return newListaMail;
				
			}
	}
}