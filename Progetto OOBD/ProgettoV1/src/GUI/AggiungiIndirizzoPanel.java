package GUI;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class AggiungiIndirizzoPanel extends JPanel {

	private JTextField textFieldVia;
	private JTextField textFieldCittà;
	private JTextField textFieldCodicePostale;
	private JTextField textFieldNazione;
	private JTextField textFieldIdentificatore;
	
	public AggiungiIndirizzoPanel() {
		setLayout(new GridLayout(6,3));
		
		JLabel lbVia = new JLabel("via");
		add(lbVia);
		
		textFieldVia = new JTextField();
		add(textFieldVia);
		textFieldVia.setColumns(15);
		
		JLabel lbCittà = new JLabel("città");
		add(lbCittà);
		
		textFieldCittà = new JTextField();
		add(textFieldCittà);
		textFieldCittà.setColumns(15);
		
		JLabel lbCodicePostale = new JLabel("codice postale");
		add(lbCodicePostale);
		
		textFieldCodicePostale = new JTextField();
		add(textFieldCodicePostale);
		textFieldCodicePostale.setColumns(15);
		
		JLabel lbNazione = new JLabel("nazione");
		add(lbNazione);
		
		textFieldNazione = new JTextField();
		add(textFieldNazione);
		textFieldNazione.setColumns(15);
		
		JLabel lbIdentificatore = new JLabel("identificatore");
		add(lbIdentificatore);
		
		textFieldIdentificatore = new JTextField();
		add(textFieldIdentificatore);
		textFieldIdentificatore.setColumns(15);
		
	}
	
	public String getVia () {
		return textFieldVia.getText();
	}
	
	public String getCittà ( ) {
		return textFieldCittà.getSelectedText();
	}
	
	public String getCodicePostale () {
		return textFieldCodicePostale.getText();
	}
	
	public String getNazione () {
		return textFieldNazione.getText();
	}
	
	public String getIdentificatore () {
		return textFieldIdentificatore.getText();
	}
	
}
