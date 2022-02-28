package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ModificaContattoPanel extends JPanel {

		private JTextField textFieldPrefisso;
	    private JTextField textFieldNome;
	    private JTextField textFieldCogome;


	/**
	 * Create the panel.
	 */
	    
	    public ModificaContattoPanel() {
		 setLayout(new GridLayout(6,3));
        
        JLabel lbPrefisso = new JLabel("Prefisso Nome");
        add(lbPrefisso);
        
        textFieldPrefisso = new JTextField();
        add(textFieldPrefisso);
        textFieldPrefisso.setColumns(15);
        
        JLabel lbNome = new JLabel("Nome");
        add(lbNome);
        
        textFieldNome = new JTextField();
        add(textFieldNome);
        textFieldNome.setColumns(15);
        
        JLabel lbCognome = new JLabel("Cognome");
        add(lbCognome);
        
        textFieldCogome = new JTextField();
        add(textFieldCogome);
        textFieldCogome.setColumns(15);
    }
    
    public void setAll(String prefisso,String nome, String cognome) {
    	textFieldPrefisso.setText(prefisso);
        textFieldNome.setText(nome);
        textFieldCogome.setText(cognome);
        
    }
    
    public String getPrefisso () {
        return textFieldPrefisso.getText();
    }
    
    public String getNome ( ) {
        return textFieldNome.getSelectedText();
    }
    
    public String getCognome () {
        return textFieldCogome.getText();
    }
    


    
    


}
