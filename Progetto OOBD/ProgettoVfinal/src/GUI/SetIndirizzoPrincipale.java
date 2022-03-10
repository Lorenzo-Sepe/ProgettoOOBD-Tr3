package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SetIndirizzoPrincipale extends JPanel{
private ArrayList<JRadioButton> arrayListRadioBotton;

private ButtonGroup gruppoBottoniPrincipale;
    @SuppressWarnings("unused")
	public SetIndirizzoPrincipale (DefaultTableModel modelIndirizzo) throws Exception {
        JPanel pannelBloccoIndirizzo =  new JPanel(new GridLayout(modelIndirizzo.getRowCount(),modelIndirizzo.getColumnCount()+1));
        
         gruppoBottoniPrincipale= new ButtonGroup();
        arrayListRadioBotton= new ArrayList<JRadioButton>();
        
        Object[] array = modelIndirizzo.getDataVector().toArray();
        
        
//    for(int i=0;i<array.length;i++) {
//        System.out.println(array[i].toString());
//    }
        
        
        for (int row=0;row<modelIndirizzo.getRowCount();row++) {
            JRadioButton radioButtonTmp = new JRadioButton();
            gruppoBottoniPrincipale.add(radioButtonTmp);
            arrayListRadioBotton.add(radioButtonTmp);
            for (int column =0; column<modelIndirizzo.getColumnCount();column++) {
                
//                System.out.println("row del getvalue "+row+"< "+modelIndirizzo.getRowCount());
//                System.out.println("Colonna del getvalue:  "+column+"<"+modelIndirizzo.getColumnCount());
//                //if(modelIndirizzo.getValueAt(row, column)==null) throw new Exception("DIO CANE è VUO con GetVALUE");
                String stringLb= modelIndirizzo.getValueAt(row, column).toString();
//                System.out.println("stringa del getvalue "+stringLb);
                pannelBloccoIndirizzo.add(new JLabel( stringLb));
            }
            arrayListRadioBotton.get(0).setSelected(true);
            pannelBloccoIndirizzo.add(radioButtonTmp);
            
        }
        
        this.add(pannelBloccoIndirizzo);
        
    }
    public int getIndexIndirizzoPrincipale() throws Exception {
        {
            for (int index =0; index<arrayListRadioBotton.size();index++) {
                if(arrayListRadioBotton.get(index).isSelected()) {
                    return index;
                }
            }
            throw new Exception("Non hai selezionato nessun Indirizzo come principale");
        }
    }
    

}