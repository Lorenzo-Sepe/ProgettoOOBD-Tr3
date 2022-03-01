package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SetDeputatoPanel extends JPanel {
private 		ArrayList<JComboBox<Object>> arrayListnComboBox= new ArrayList<JComboBox<Object>>();

	public SetDeputatoPanel(DefaultTableModel modelloNumeriDiTelefono) {

		
		JPanel panelBloccoListaNumeri = new JPanel();
		ArrayList<String> arrayListnumeroDeputatoFisso = new ArrayList<String>();
		ArrayList<String> arrayListnumeroDeputatoMobile = new ArrayList<String>();
		String numeroDeputatoTmp;
		int colonna = 0;

		for (int i = 0; i < modelloNumeriDiTelefono.getRowCount(); i++) {

			numeroDeputatoTmp = "";
			for (colonna = 1; colonna < modelloNumeriDiTelefono.getColumnCount()-1; colonna++) {

				numeroDeputatoTmp += " " + modelloNumeriDiTelefono.getValueAt(i, colonna).toString();
			}
			if (modelloNumeriDiTelefono.getValueAt(i, 3).toString().compareToIgnoreCase("fisso") == 0)
				arrayListnumeroDeputatoFisso.add(numeroDeputatoTmp);
			else
				arrayListnumeroDeputatoMobile.add(numeroDeputatoTmp);
		}

		panelBloccoListaNumeri.setLayout(
				new GridLayout(modelloNumeriDiTelefono.getRowCount(), modelloNumeriDiTelefono.getColumnCount()));
		for (int i = 0; i < modelloNumeriDiTelefono.getRowCount(); i++) {

			JPanel panelLabelNumero = new JPanel(new GridLayout(1, modelloNumeriDiTelefono.getColumnCount()));
			for (colonna = 0; colonna < modelloNumeriDiTelefono.getColumnCount(); colonna++) {
				panelLabelNumero.add(new JLabel(modelloNumeriDiTelefono.getValueAt(i, colonna).toString()));
			}

			panelBloccoListaNumeri.add(panelLabelNumero);
			JComboBox<Object> comboBOxTmp;
			if (modelloNumeriDiTelefono.getValueAt(i, 3).toString().compareToIgnoreCase("mobile") == 0) {
				comboBOxTmp =new JComboBox<Object>(arrayListnumeroDeputatoFisso.toArray());
				arrayListnComboBox.add(comboBOxTmp);
				panelBloccoListaNumeri.add(comboBOxTmp);
				
			} else {
				panelBloccoListaNumeri.add(new JComboBox<Object>(arrayListnumeroDeputatoMobile.toArray()));
				comboBOxTmp =new JComboBox<Object>(arrayListnumeroDeputatoMobile.toArray());
				arrayListnComboBox.add(comboBOxTmp);
			}

		}
		this.add(panelBloccoListaNumeri);


	}
	

	public String getPrefissoDeputato(int index){
		String NumeroCompleto=  arrayListnComboBox.get(index).getSelectedItem().toString();
		if(NumeroCompleto.startsWith(" " ))NumeroCompleto= NumeroCompleto.replaceFirst(" ","");
		
		return NumeroCompleto.substring(0,NumeroCompleto.lastIndexOf(" "));
		
	}

	public String getNumeroDeputato(int index){
		String NumeroCompleto=  arrayListnComboBox.get(index).getSelectedItem().toString();
		return NumeroCompleto.substring(NumeroCompleto.lastIndexOf(" ")+1);
		
	}


	
}

