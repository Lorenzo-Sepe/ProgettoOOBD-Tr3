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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AggiungiAccountFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	static private Controller c;
	static private  JFrame chiamante;
	static private  JDialog frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Controller c = new Controller();
		JFrame frame = new JFrame();
		try {
			AggiungiAccountFrame dialog = new AggiungiAccountFrame(c,frame);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AggiungiAccountFrame(Controller controller, JFrame frameChiamante) {
		frame = this;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Inserire il nickname");
			lblNewLabel.setBounds(42, 22, 111, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(197, 22, 200, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Inserire la mail");
			lblNewLabel_1.setBounds(42, 57, 111, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(197, 57, 200, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Inserire il fornitore");
			lblNewLabel_2.setBounds(42, 92, 111, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(197, 92, 200, 20);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Inserire la frase di benvenuto");
			lblNewLabel_3.setBounds(42, 127, 152, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(42, 152, 355, 20);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
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
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
