package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AggiungiIndirizzoFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	static private Controller c;
	static private JFrame chiamante;
	static private JDialog frame;
	private JTextField textVia;
	private JTextField textCittà;
	private JTextField textCodicePostale;
	private JTextField textNazione;
	private JTextField textIdentificatore;
	private final ButtonGroup buttonGroupPrincipale = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Controller c = new Controller();
		JFrame frame = new JFrame();
		try {
			AggiungiIndirizzoFrame dialog = new AggiungiIndirizzoFrame(c, frame);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AggiungiIndirizzoFrame(Controller controller, JFrame frameChiamante) {
		frame = this;
		c = controller;
		chiamante = frameChiamante;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserire la via");
		lblNewLabel.setBounds(10, 44, 129, 14);
		contentPanel.add(lblNewLabel);
		
		textVia = new JTextField();
		textVia.setBounds(174, 41, 161, 20);
		contentPanel.add(textVia);
		textVia.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Inserire la citt\u00E0");
		lblNewLabel_1.setBounds(10, 69, 129, 14);
		contentPanel.add(lblNewLabel_1);
		
		textCittà = new JTextField();
		textCittà.setBounds(174, 66, 161, 20);
		contentPanel.add(textCittà);
		textCittà.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Inserire il codice postale");
		lblNewLabel_2.setBounds(10, 94, 129, 14);
		contentPanel.add(lblNewLabel_2);
		
		textCodicePostale = new JTextField();
		textCodicePostale.setBounds(174, 91, 161, 20);
		contentPanel.add(textCodicePostale);
		textCodicePostale.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Inserire la nazione");
		lblNewLabel_3.setBounds(10, 119, 129, 14);
		contentPanel.add(lblNewLabel_3);
		
		textNazione = new JTextField();
		textNazione.setBounds(174, 116, 161, 20);
		contentPanel.add(textNazione);
		textNazione.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Inserire l'identificatore");
		lblNewLabel_4.setBounds(10, 144, 129, 14);
		contentPanel.add(lblNewLabel_4);
		
		textIdentificatore = new JTextField();
		textIdentificatore.setBounds(174, 141, 161, 20);
		contentPanel.add(textIdentificatore);
		textIdentificatore.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Principale");
		lblNewLabel_5.setBounds(10, 169, 129, 14);
		contentPanel.add(lblNewLabel_5);
		
		JPanel panel = new JPanel();
		panel.setBounds(174, 172, 161, 30);
		contentPanel.add(panel);
		
		JRadioButton rdbtnSi = new JRadioButton("Si");
		buttonGroupPrincipale.add(rdbtnSi);
		panel.add(rdbtnSi);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		buttonGroupPrincipale.add(rdbtnNo);
		panel.add(rdbtnNo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Conferma");
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
						frame.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
