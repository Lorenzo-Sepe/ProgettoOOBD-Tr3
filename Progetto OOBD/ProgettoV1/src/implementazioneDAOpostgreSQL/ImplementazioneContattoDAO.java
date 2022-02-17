package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ContattoDAO;
import Database.Connessione;
import Model.Account;
import Model.Contatto;
import Model.Indirizzi;
import Model.NumeriTelefonici;

public class ImplementazioneContattoDAO implements ContattoDAO {
	
	private Connection connection;
	
	public ImplementazioneContattoDAO () {
		try {
			connection = Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void  chiudiConnessione() {
		 try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		
	
	
	public void addContattoDB (Contatto c) {
		PreparedStatement addContattoPS;
		try {
			addContattoPS = connection.prepareStatement("INSERT INTO Contatto (prefisso_nome,nome,cognome,path_foto) VALUES (?,?,?,?)");
			addContattoPS.setString(1, c.getPrefissoNome());
			addContattoPS.setString(2, c.getNome());
			addContattoPS.setString(3, c.getCognome());
			addContattoPS.setString(4, c.getPathFoto());
			addContattoPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteContattoDB (Contatto c) {
		PreparedStatement deleteContattoPS;
		try {
			deleteContattoPS = connection.prepareStatement("DELETE FROM Contatto WHERE contatto_ID = ?");
			deleteContattoPS.setInt(1, c.getID());
			deleteContattoPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateContattoDB (Contatto c, String prefisso, String nome, String cognome, String pathFoto) {
		PreparedStatement updateContattoPS;
		try {
			updateContattoPS = connection.prepareStatement("UPDATE Contatto SET prefisso_nome = ?, nome = ?, cognome = ?, path_foto = ? WHERE contatto_id = ?");
			updateContattoPS.setString(1, prefisso);
			updateContattoPS.setString(2, nome);
			updateContattoPS.setString(3, cognome);
			updateContattoPS.setString(4, pathFoto);
			updateContattoPS.setInt(5, c.getID());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public ArrayList<NumeriTelefonici> getListaNumeri (int id) {
		PreparedStatement getListaNumeriFissiPS;
		PreparedStatement getListaNumeriMobiliPS;
		ArrayList<NumeriTelefonici> listaNumeri = new ArrayList<>();
		try {
			getListaNumeriFissiPS = connection.prepareStatement("SELECT * FROM numeri_telefonici_fissi WHERE contatto_associato = ? ORDER BY numero_id" );
			getListaNumeriFissiPS.setInt(1,id);
			ResultSet rsF = getListaNumeriFissiPS.executeQuery();
			while (rsF.next()) {
				NumeriTelefonici i = new NumeriTelefonici(rsF.getString("identificatore"), rsF.getString("prefisso_nazionale")  , rsF.getString("numero"),"Fisso" );
				listaNumeri.add(i);
			}
			getListaNumeriMobiliPS = connection.prepareStatement("SELECT * FROM numeri_telefonici_mobili WHERE contatto_associato = ? ORDER BY numero_id");
			getListaNumeriMobiliPS.setInt(1, id);
			ResultSet rsM = getListaNumeriMobiliPS.executeQuery();
			while (rsM.next()) {
				NumeriTelefonici i = new NumeriTelefonici(rsM.getString("identificatore"), rsM.getString("prefisso_nazionale")  , rsM.getString("numero"),"Mobile" );
				listaNumeri.add(i);
				
			}
			//connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listaNumeri;
	}
	
	public ArrayList<Indirizzi> getListaIndirizzi (int contattoID){
		PreparedStatement getListaIndirizziPS;
		ArrayList<Indirizzi> listaIndirizzi = new ArrayList<>();
		try {
			getListaIndirizziPS = connection.prepareStatement("SELECT i.*,ab.abitazione_principale, ab.identificatore FROM indirizzi i,abita ab WHERE ab.contatto_associato = ? AND ab.indirizzo_associato = i.indirizzi_id ORDER BY indirizzi_id\r\n"
					+ "");
			getListaIndirizziPS.setInt(1, contattoID);
			ResultSet rs = getListaIndirizziPS.executeQuery();
			while (rs.next()) {
				Indirizzi i = new Indirizzi(rs.getInt("indirizzi_id"), rs.getBoolean("abitazione_principale"), rs.getString("via"),rs.getString("città"),rs.getInt("codice_postale"),rs.getString("nazione"),rs.getString("identificatore"));
			
				listaIndirizzi.add(i);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return listaIndirizzi;
	}
	
	public ArrayList<Account> getListaAccount (int id) {
		PreparedStatement getListaAccountPS;
		ArrayList<Account> listaAccount = new ArrayList<>();
		try {
			getListaAccountPS = connection.prepareStatement("SELECT * FROM account WHERE contatto_associato = ? ORDER BY account_id");
			getListaAccountPS.setInt(1, id);
			ResultSet rs = getListaAccountPS.executeQuery();
			while (rs.next()) {
				Account i = new Account(rs.getString("fornitore"), rs.getString("nickname"), rs.getString("frase_di_benvenuto"), rs.getString("mail"));
				listaAccount.add(i);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAccount;
	}
	
	public void setDeputatoMobileSuFIsso(String mobilePrefisso,String mobileNumero,String fissoPrefisso,String fissoNumero) {
		PreparedStatement getDeputato ;
		String nomeProcedura=" SetDeputatoMobileSuFisso ";
		try {
			getDeputato=connection.prepareStatement("CALL "+nomeProcedura+"( ?, ?)");
			getDeputato.setInt(1, getIDNumeroMobile(mobilePrefisso, mobileNumero));
			getDeputato.setInt(2, getIDNumeroFisso(fissoPrefisso, fissoNumero));
			ResultSet rs= getDeputato.executeQuery();
			//TODO SI deve fare altro ?
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		} 
	public void setDeputatoFissoSuMobile(String mobilePrefisso,String mobileNumero,String fissoPrefisso,String fissoNumero) {
		PreparedStatement getDeputato ;
		String nomeProcedura=" SetDeputatoMobileSuFisso ";
		try {
			getDeputato=connection.prepareStatement("CALL "+nomeProcedura+"( ?, ?)");
			getDeputato.setInt(1, getIDNumeroMobile(mobilePrefisso, mobileNumero));
			getDeputato.setInt(2, getIDNumeroFisso(fissoPrefisso, fissoNumero));
			ResultSet rs= getDeputato.executeQuery();
			//TODO SI deve fare altro ?
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		} 
	private int getIDNumeroMobile(String prefissoMobile,String numeroMobile) throws Exception {
		PreparedStatement getIDNumero;
		int id = 0;
		try {
			getIDNumero=connection.prepareStatement("Select ID Numero_ID from NUMERI_TELEFONICI_MOBILI Where PREFISSO_NAZIONALE = ? AND NUMERO = ? ");
			getIDNumero.setString(1, prefissoMobile);
			getIDNumero.setString(2, numeroMobile);
			ResultSet rs= getIDNumero.executeQuery();
			rs.next();
			id =rs.getInt("Numero_ID");
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(id==0) throw new Exception("Id Numero Mobile non trovato ");
		return id;
	}
	private int getIDNumeroFisso(String prefissoFisso,String numeroFisso) throws Exception {
		PreparedStatement getIDNumero;
		int id = 0;
		try {
			getIDNumero=connection.prepareStatement("Select ID Numero_ID from NUMERI_TELEFONICI_FISSI Where PREFISSO_NAZIONALE = ? AND NUMERO = ? ");
			getIDNumero.setString(1, prefissoFisso);
			getIDNumero.setString(2, numeroFisso);
			ResultSet rs= getIDNumero.executeQuery();
			rs.next();
			id =rs.getInt("Numero_ID");
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(id==0) throw new Exception("Id Numero Fisso non trovato ");
		return id;
	}	
	
	
	
}//Fine classe
