package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Contatto;
import Model.NumeriTelefonici;
import DAO.NumeriTelefoniciDAO;
import Database.Connessione;

public class ImplementazioneNumeriTelefoniciDAO implements NumeriTelefoniciDAO {
	private Connection connection;
	
	public ImplementazioneNumeriTelefoniciDAO () {
		try {
			connection = Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//
	public void addNumeroFissoDB (NumeriTelefonici num, Contatto c) {
		PreparedStatement addNumeroPS;
		try {
			addNumeroPS = connection.prepareStatement("INSERT INTO numeri_telefonici_Fissi (contatto_associato,prefisso_nazionale,numero,identificatore) VALUES (?,?,?,?)");
			addNumeroPS.setInt(1, c.getID());
			addNumeroPS.setString(2, num.getPrefisso());
			addNumeroPS.setString(3, num.getNumero());
			addNumeroPS.setString(4, num.getTag());
			addNumeroPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeNumeroFissoDB (NumeriTelefonici num) {
		PreparedStatement removeNumeroPS;
		try {
			removeNumeroPS = connection.prepareStatement("DELETE FROM Numeri_telefonici_Fissi WHERE numero = ? AND prefisso_nazionale = ?");
			removeNumeroPS.setString(1, num.getNumero());
			removeNumeroPS.setString(2, num.getPrefisso());
			removeNumeroPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateNumeroFissoDB (NumeriTelefonici num, String prefisso, String numero, String tag) {
		PreparedStatement updateNumeroPS;
		try {
			updateNumeroPS = connection.prepareStatement("UPDATE Numeri_telefonici_Fissi SET prefisso_nazionale = ?, numero = ?, tipo_numero = ?, identificatore = ? WHERE numero = ? AND prefisso_Nazionale = ?");
			updateNumeroPS.setString(1, prefisso);
			updateNumeroPS.setString(2, numero);
			updateNumeroPS.setString(3, tag);
			updateNumeroPS.setString(4, num.getNumero());
			updateNumeroPS.setString(5,num.getPrefisso());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public ArrayList<NumeriTelefonici> readNumeroFissoDB (NumeriTelefonici num) {
//		ArrayList<NumeriTelefonici> listaNumeri = new ArrayList<>();
//		PreparedStatement readNumeroPS;
//		try {
//			readNumeroPS = connection.prepareStatement("SELECT * FROM numeri_telefonici_Fissi WHERE numero = ? and prefisso_Nazionale = ? ");
//			readNumeroPS.setString(1, num.getNumero());
//			readNumeroPS.setString(2,num.getPrefisso());
//			ResultSet rs = readNumeroPS.executeQuery();
//			while (rs.next()) {
//				NumeriTelefonici i= new NumeriTelefonici ( rs.getString("identificatore"),rs.getString("prefisso_nazionale"), rs.getString("numero"),"fisso" );
//				listaNumeri.add(i);
//			}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return listaNumeri;
//	}
	
	public ArrayList<NumeriTelefonici> selectAllDB () {
		ArrayList<NumeriTelefonici> listaNumeri = new ArrayList<>();
		PreparedStatement selectAllPS1;
		PreparedStatement selectAllPS2;
		try {
			selectAllPS1 = connection.prepareStatement("SELECT * FROM numeri_telefonici_Fissi ORDER BY numero_id");
			ResultSet rs1 = selectAllPS1.executeQuery();
			while (rs1.next()) {																							
				NumeriTelefonici i= new NumeriTelefonici (rs1.getString("identificatore"), rs1.getString("prefisso_nazionale"), rs1.getString("numero"), "fisso");
				listaNumeri.add(i);
			}
			selectAllPS2 = connection.prepareStatement("SELECT * FROM numeri_telefonici_Mobili ORDER BY numero_id");
			ResultSet rs2 = selectAllPS2.executeQuery();
			while (rs2.next()) {																
				NumeriTelefonici j= new NumeriTelefonici (rs2.getString("identificatore"), rs2.getString("prefisso_nazionale"), rs2.getString("numero"), "mobile");
				listaNumeri.add(j);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaNumeri;
	}


	@Override
	public void addNumeroMobileDB(NumeriTelefonici num, Contatto c) {

		PreparedStatement addNumeroPS;
		try {
			addNumeroPS = connection.prepareStatement("INSERT INTO numeri_telefonici_Mobile (contatto_associato,prefisso_nazionale,numero,identificatore) VALUES (?,?,?,?)");
			addNumeroPS.setInt(1, c.getID());
			addNumeroPS.setString(2, num.getPrefisso());
			addNumeroPS.setString(3, num.getNumero());
			addNumeroPS.setString(4, num.getTag());
			addNumeroPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	


	@Override
	public void removeNumeroMobileDB(NumeriTelefonici num) {
		PreparedStatement removeNumeroPS;
		try {
			removeNumeroPS = connection.prepareStatement("DELETE FROM Numeri_telefonici_Mobile WHERE numero = ? AND Prefisso_Nazionale = ? ");
			removeNumeroPS.setString(1, num.getNumero());
			removeNumeroPS.setString(2, num.getPrefisso());
			removeNumeroPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	


	@Override
	public void updateNumeroMobileDB(NumeriTelefonici num, String prefisso, String numero, String tag) {
		PreparedStatement updateNumeroPS;
		try {
			updateNumeroPS = connection.prepareStatement("UPDATE Numeri_telefonici_Mobile SET prefisso_nazionale = ?, numero = ?, tipo_numero = ?, identificatore = ? WHERE numero= ? AND Prefisso_Nazionale = ?");
			updateNumeroPS.setString(1, prefisso);
			updateNumeroPS.setString(2, numero);
			updateNumeroPS.setString(3, tag);
			updateNumeroPS.setString(4, num.getNumero());
			updateNumeroPS.setString(5, num.getPrefisso());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public ArrayList<NumeriTelefonici> readNumeroDB(NumeriTelefonici num) {
		// TODO Auto-generated method stub
		return null;
	}

}
