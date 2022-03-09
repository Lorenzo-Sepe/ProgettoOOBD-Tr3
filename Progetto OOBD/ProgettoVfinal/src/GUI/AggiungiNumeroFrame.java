package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import Controller.Controller;
import Model.Account;
import Model.EnumPrefissoNumero;
import Model.Indirizzi;
import Model.NumeriTelefonici;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;





public class AggiungiNumeroFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNumero;
	private JTextField textTag;
	static private Controller c;
	static private JFrame frameChiamante;
	static private JDialog frame;
	private final ButtonGroup tipoNumero = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ArrayList<NumeriTelefonici> lista = new ArrayList<>();
		try {
			AggiungiNumeroFrame dialog = new AggiungiNumeroFrame(c,frameChiamante,lista);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AggiungiNumeroFrame(Controller controller, JFrame chiamante, ArrayList<NumeriTelefonici> listaNumeri) {
		frame = this;
		c= controller;
		frameChiamante = chiamante;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserire il numero");
		lblNewLabel.setBounds(43, 89, 145, 14);
		contentPanel.add(lblNewLabel);
		
		textNumero = new JTextField();
		textNumero.setBounds(288, 89, 110, 20);
		contentPanel.add(textNumero);
		textNumero.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Inserire il tag");
		lblNewLabel_2.setBounds(43, 54, 145, 14);
		contentPanel.add(lblNewLabel_2);
		
		textTag = new JTextField();
		textTag.setBounds(198, 54, 200, 20);
		contentPanel.add(textTag);
		textTag.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(198, 135, 200, 49);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnFisso = new JRadioButton("Fisso");
		tipoNumero.add(rdbtnFisso);
		rdbtnFisso.setBounds(26, 7, 109, 23);
		panel.add(rdbtnFisso);
		
		JRadioButton rdbtnMobile = new JRadioButton("Mobile");
		tipoNumero.add(rdbtnMobile);
		rdbtnMobile.setBounds(132, 7, 76, 23);
		panel.add(rdbtnMobile);
		
		JLabel lblNewLabel_1 = new JLabel("Scegli il tipo");
		lblNewLabel_1.setBounds(43, 151, 145, 14);
		contentPanel.add(lblNewLabel_1);
		
		JComboBox comboBoxPrefisso = new JComboBox();
		comboBoxPrefisso.setBounds(198, 89, 56, 20);
		EnumPrefissoNumero[] arrayEnumPrefissi = EnumPrefissoNumero.class.getEnumConstants();
	       for (EnumPrefissoNumero enumPrefissoNumero : arrayEnumPrefissi) {
	    	   String prefisso = new String (enumPrefissoNumero.getPrefisso().toString());
	        	comboBoxPrefisso.addItem(prefisso.substring(prefisso.indexOf('+')+1));
	        }
		contentPanel.add(comboBoxPrefisso);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Conferma");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String tipo = new String();
						if (rdbtnFisso.isSelected()) {
							tipo = "Fisso";
						}
						else if (rdbtnMobile.isSelected()) {
							tipo = "Mobile";
						}
						NumeriTelefonici numero = new NumeriTelefonici(comboBoxPrefisso.getActionCommand(), textNumero.getText() , textTag.getText(),tipo );
						frame.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Indietro");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						frame.setVisible(false);
						frameChiamante.setVisible(true);
						frame.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
