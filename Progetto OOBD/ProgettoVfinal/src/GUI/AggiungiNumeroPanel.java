package GUI;

import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import Model.EnumPrefissoNumero;

@SuppressWarnings("serial")
public class AggiungiNumeroPanel extends JPanel{

	private JTextField textFieldTagNumero;
	JTextField textFieldNumero;
	JTextField textFieldPrefissoNnmero;
	JFormattedTextField txtNumero;
	JRadioButton radioFisso ;
	JRadioButton radioMobile;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AggiungiNumeroPanel() {
		this.setLayout(new GridLayout(4,3));
	JLabel lbTagNumero = new JLabel("tag");
	
	
		//lbTagNumero.setBounds(122, 79, 45, 13);
		this.add(lbTagNumero);
		
		/**
	 * 
	 */

		 textFieldTagNumero = new JTextField();
		
		this.add(textFieldTagNumero);
		textFieldTagNumero.setColumns(10);
		//this.add(Box.createHorizontalStrut(10));
		
		
		JLabel lbPrefissoNumero = new JLabel("Prefisso");
		this.add(lbPrefissoNumero);
		
		
		JComboBox comboBoxPrefisso = new JComboBox();
		ArrayList<String > arrayListStringEnumPrefissi= new ArrayList<>();
		EnumPrefissoNumero[] arrayEnumPrefissi = EnumPrefissoNumero.class.getEnumConstants();
		
		for (EnumPrefissoNumero enumPrefissoNumero : arrayEnumPrefissi) {
			arrayListStringEnumPrefissi.add(enumPrefissoNumero.getPrefisso().toString());
		}
		comboBoxPrefisso.setModel(new DefaultComboBoxModel(arrayListStringEnumPrefissi.toArray(new String[0])) {
			
		});
		comboBoxPrefisso.setSelectedIndex(-1);
		comboBoxPrefisso.setEditable(true);
		 textFieldPrefissoNnmero = (JTextField) comboBoxPrefisso.getEditor().getEditorComponent();
		textFieldPrefissoNnmero.addKeyListener(new ComboListener(comboBoxPrefisso, arrayListStringEnumPrefissi));
		this.add(comboBoxPrefisso);
//		texfield di prefisso
//		JTextField textFieldPrefissoNnmero = new JTextField();
//		
//		this.add(textFieldPrefissoNnmero);
		textFieldPrefissoNnmero.setColumns(15);
//		this.add(Box.createHorizontalStrut(10));
		
		JLabel lbNumero = new JLabel("Numero");
		
		this.add(lbNumero);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setToolTipText("Inserisci Solo dei Numeri");
		this.add(textFieldNumero);
		textFieldNumero.setColumns(10);

		
		txtNumero = new JFormattedTextField();
		
			
		txtNumero.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.###", new DecimalFormatSymbols(Locale.US)))));
		
		
		this.add(textFieldNumero);
		
//		this.add(Box.createHorizontalStrut(10));
		JLabel lbTipoNumero = new JLabel("Tipo");
		
		this.add(lbTipoNumero);
		ButtonGroup gruppoBottoniTipoNumero = new ButtonGroup();
		 radioFisso = new JRadioButton("Fisso");
		 radioMobile = new JRadioButton("Mobile");
		gruppoBottoniTipoNumero.add(radioMobile);
		gruppoBottoniTipoNumero.add(radioFisso);
		JPanel panelloBottoniRadio = new JPanel();
		panelloBottoniRadio.add(radioFisso);
		panelloBottoniRadio.add(radioMobile);
		this.add(panelloBottoniRadio);
		
	
}
	public String getTag () {
	return textFieldTagNumero.getText();
	}
	public String getPrefisso() {
		return textFieldPrefissoNnmero.getText();
	}
	public String getNumero() {
		return textFieldNumero.getText();
	}
	public String getTipo() {
		return radioFisso.isSelected() ? radioFisso.getText() : radioMobile.getText();
	}
	
	public void setAll(String tag,String prefisso,String numero,String tipo ) {
        textFieldTagNumero.setText(tag);
        textFieldPrefissoNnmero.setText(prefisso);
        textFieldNumero.setText(numero);
        if(tipo.compareTo("Fisso")==0) {
            radioFisso.setSelected(true);
        }else {
            radioMobile.setSelected(true);
        }
	}
}