package implementazioneDAOpostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Contatto;
import Model.NumeriTelefonici;
import DAO.NumeriTelefoniciDAO;
import DAO.Connessione;

public class ImplementazioneNumeriTelefoniciDAO implements NumeriTelefoniciDAO {
	private Connection connection;
	
	public ImplementazioneNumeriTelefoniciDAO () {
		try {
			connection = Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addNumeroDB (NumeriTelefonici num, Contatto c) {
		PreparedStatement addNumeroPS;
		try {
			addNumeroPS = connection.prepareStatement("INSERT INTO numeri_telefonici (contatto_associato,prefisso_nazionale,numero,tipo_numero,identificatore) VALUES (?,?,?,?,?)");
			addNumeroPS.setInt(1, c.getID());
			addNumeroPS.setString(2, num.getPrefisso());
			addNumeroPS.setString(3, num.getNumero());
			addNumeroPS.setString(4, num.getTipoNumero());
			addNumeroPS.setString(5, num.getTag());
			addNumeroPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeNumeroDB (NumeriTelefonici num) {
		PreparedStatement removeNumeroPS;
		try {
			removeNumeroPS = connection.prepareStatement("DELETE FROM Numeri_telefonici WHERE numero_id = ?");
			removeNumeroPS.setInt(1, num.getID());
			removeNumeroPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateNumeroDB (NumeriTelefonici num, String prefisso, String numero, String tipoNumero, String tag) {
		PreparedStatement updateNumeroPS;
		try {
			updateNumeroPS = connection.prepareStatement("UPDATE Numeri_telefonici SET prefisso_nazionale = ?, numero = ?, tipo_numero = ?, identificatore = ? WHERE numero_id = ?");
			updateNumeroPS.setString(1, prefisso);
			updateNumeroPS.setString(2, numero);
			updateNumeroPS.setString(3, tipoNumero);
			updateNumeroPS.setString(4, tag);
			updateNumeroPS.setInt(5, num.getID());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<NumeriTelefonici> readNumeroDB (NumeriTelefonici num) {
		ArrayList<NumeriTelefonici> listaNumeri = new ArrayList<>();
		PreparedStatement readNumeroPS;
		try {
			readNumeroPS = connection.prepareStatement("SELECT * FROM numeri_telefonici WHERE numero_id = ?");
			readNumeroPS.setInt(1, num.getID());
			ResultSet rs = readNumeroPS.executeQuery();
			while (rs.next()) {
				NumeriTelefonici i= new NumeriTelefonici (rs.getString("prefisso_nazionale"), rs.getString("numero"), rs.getString("tipo_numero"), rs.getString("identificatore"));
				listaNumeri.add(i);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaNumeri;
	}
	
	public ArrayList<NumeriTelefonici> selectAllDB () {
		ArrayList<NumeriTelefonici> listaNumeri = new ArrayList<>();
		PreparedStatement selectAllPS;
		try {
			selectAllPS = connection.prepareStatement("SELECT * FROM numeri_telefonici ORDER BY numero_id");
			ResultSet rs = selectAllPS.executeQuery();
			while (rs.next()) {
				NumeriTelefonici i= new NumeriTelefonici (rs.getString("prefisso_nazionale"), rs.getString("numero"), rs.getString("tipo_numero"), rs.getString("identificatore"));
				listaNumeri.add(i);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaNumeri;
	}
}
