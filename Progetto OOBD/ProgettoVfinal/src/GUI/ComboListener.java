package GUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ComboListener extends KeyAdapter{
	JComboBox<String> comboBoxDelListener;
	ArrayList<String> arrayList;
	
	public ComboListener(JComboBox<String> comboBoxParametro, ArrayList<String> arrayListParametro) {
		comboBoxDelListener=comboBoxParametro;
		arrayList=arrayListParametro;
	}
	
	public void keyPressed (KeyEvent key)
	{
				// TODO Auto-generated method stub
				String text = ((JTextField)key.getSource()).getText();
				//System.out.println("il testo appena insertito è: "+text);
				
				comboBoxDelListener.setModel(new DefaultComboBoxModel<String>(getFilteredList(text)));
				comboBoxDelListener.setSelectedIndex(-1);
				((JTextField)comboBoxDelListener.getEditor().getEditorComponent()).setText(text);
				comboBoxDelListener.showPopup();
	}
	
	
	public Vector<String> getFilteredList(String text)
	{
		Vector<String> v = new Vector<String>();
		for(int a = 0;a<arrayList.size();a++)
		{
			if(arrayList.get(a).toString().startsWith(text))
			{
				v.add(arrayList.get(a).toString());
			}
		}
		return v;
	}
}