package DAO;

import Model.Contatto;
import Model.NumeriTelefonici;
import java.util.ArrayList;

public interface NumeriTelefoniciDAO {
	
	public void addNumeroDB (NumeriTelefonici num, Contatto c);
	
	public void removeNumeroDB (NumeriTelefonici num);
	
	public void updateNumeroDB (NumeriTelefonici num, String prefisso, String numero, String tipoNumero, String tag);
	
	public ArrayList<NumeriTelefonici> readNumeroDB (NumeriTelefonici num);
}
