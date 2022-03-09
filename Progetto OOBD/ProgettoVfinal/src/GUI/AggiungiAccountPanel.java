package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;



import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AggiungiAccountPanel extends JPanel {
    private JTextField textFieldNickname;
    @SuppressWarnings("rawtypes")
    private JComboBox ComboBoxMail;
    private JTextField textFieldForinitore;
    private JTextArea textAreaFraseDiBenvenuto;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public AggiungiAccountPanel(ArrayList<String> arrayListEmail) {
        this.setLayout(new GridLayout(3, 1));

        JPanel tuttoTraneFraseDIbenvenuto = new JPanel();

        tuttoTraneFraseDIbenvenuto.setLayout(new GridLayout(3, 5));

        int numeroColonnetextField = 10;
        JLabel lbNickname = new JLabel("Nickname");
        tuttoTraneFraseDIbenvenuto.add(lbNickname);
        textFieldNickname = new JTextField();
        textFieldNickname.setColumns(numeroColonnetextField);
        tuttoTraneFraseDIbenvenuto.add(textFieldNickname);

        JLabel lbMail = new JLabel("Mail");
        tuttoTraneFraseDIbenvenuto.add(lbMail);
         ComboBoxMail = new JComboBox();
        ComboBoxMail.addItem("Nessuna Mail");
    	  for (String string : arrayListEmail) {
    		  ComboBoxMail.addItem(string);
        }
        tuttoTraneFraseDIbenvenuto.add(ComboBoxMail);


        JLabel lbFornitore = new JLabel("Fornitore");
        tuttoTraneFraseDIbenvenuto.add(lbFornitore);
        textFieldForinitore = new JTextField();
        textFieldForinitore.setColumns(numeroColonnetextField);
        tuttoTraneFraseDIbenvenuto.add(textFieldForinitore);

        this.add(tuttoTraneFraseDIbenvenuto);
//        JPanel panelFraseDiBenevenuto = new JPanel();

        JLabel lbFraseDiBenvenuto = new JLabel("Frase di benvenuto");
        this.add(lbFraseDiBenvenuto);
        textAreaFraseDiBenvenuto = new JTextArea("", 1, 1);
        textAreaFraseDiBenvenuto.setLineWrap(true);
        textAreaFraseDiBenvenuto.setWrapStyleWord(true);
//        textAreaFraseDiBenvenuto.setCaretPosition("frase di benvenuto".length());
        this.add(new JScrollPane(textAreaFraseDiBenvenuto));

    }
public String getNickname() {
    return textFieldNickname.getText();
}
public String getMail() {
    return ComboBoxMail.getSelectedItem().toString();
}
public String getFornitore() {
    return textFieldForinitore.getText();
}
public String getFraseDiBenvenuto() {
    return textAreaFraseDiBenvenuto.getText();
}

public void setAll(String nickname,String mail, String fornitore,String fraseDiBenvenuto) {
    textFieldNickname.setText(nickname);
    ComboBoxMail.setSelectedItem(mail) ;
    textFieldForinitore.setText(fornitore);
    textAreaFraseDiBenvenuto.setText(fraseDiBenvenuto);
}

}
