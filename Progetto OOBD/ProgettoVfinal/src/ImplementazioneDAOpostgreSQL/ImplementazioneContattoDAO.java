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
	
	public Contatto readContattoDB (int id) {
		PreparedStatement readContattoPS;
		Contatto contatto = null;
		try {
			readContattoPS = connection.prepareStatement("SELECT * FROM Contatto WHERE contatto_id = ? AND \"Password_Cassaforte\" IS NULL;");
			readContattoPS.setInt(1, id);
			ResultSet rs = readContattoPS.executeQuery();
			rs.next();
				contatto = new Contatto (rs.getInt(0), rs.getString("prefisso_nome"), rs.getString("nome"), rs.getString("cognome"),rs.getString("path_foto"));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return contatto;
	}
	
	public int addNumeriDB (int idContatto, String prefisso, String numero, String tag, String tipo) {
		PreparedStatement addNumeriPS ;
		int idNum = 0;
		try {
			String query = "INSERT INTO";
			if (tipo == "Fisso") {
				query = query.concat(" numeri_telefonici_fissi ");
			}
			else {
				query = query.concat(" numeri_telefonici_mobili ");
			}
			query = query .concat("(contatto_associato,prefisso_nazionale,numero,identificatore,reindirizzamento) VALUES (?,?,?,?,null) RETURNING numero_id AS idret;") ;
			addNumeriPS = connection.prepareStatement(query);
			addNumeriPS.setInt(1, idContatto);
			addNumeriPS.setString(2, prefisso);
			addNumeriPS.setString(3, numero);
			addNumeriPS.setString(4, tag);
			ResultSet rs = addNumeriPS.executeQuery();
			rs.next();
			idNum = rs.getInt("idret");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return idNum;
	}
	
	public void removeNumeriDB (int idNumero, String tipo) throws SQLException {
		PreparedStatement removeNumeriDB;
		if (tipo.compareTo("Fisso")==0) {
			removeNumeriDB = connection.prepareStatement("DELETE FROM numeri_telefonici_fissi WHERE numero_id = ?;");
		}
		else {
			removeNumeriDB = connection.prepareStatement("DELETE FROM numeri_telefonici_mobili WHERE numero_id = ?;");
		}
		removeNumeriDB.setInt(1, idNumero);
		removeNumeriDB.execute();
	}
	
	public ArrayList<NumeriTelefonici> getListaNumeri (int id) {
		PreparedStatement getListaNumeriFissiPS;
		PreparedStatement getListaNumeriMobiliPS;
		ArrayList<NumeriTelefonici> listaNumeri = new ArrayList<>();
		try {
			getListaNumeriFissiPS = connection.prepareStatement("SELECT * FROM numeri_telefonici_fissi WHERE contatto_associato = ? ORDER BY numero_id;" );
			getListaNumeriFissiPS.setInt(1,id);
			ResultSet rsF = getListaNumeriFissiPS.executeQuery();
			while (rsF.next()) {
				NumeriTelefonici i = new NumeriTelefonici(rsF.getString("prefisso_nazionale"), rsF.getString("numero"), rsF.getString("identificatore"),"Fisso" );
				listaNumeri.add(i);
			}
			getListaNumeriMobiliPS = connection.prepareStatement("SELECT * FROM numeri_telefonici_mobili WHERE contatto_associato = ? ORDER BY numero_id;");
			getListaNumeriMobiliPS.setInt(1, id);
			ResultSet rsM = getListaNumeriMobiliPS.executeQuery();
			while (rsM.next()) {
				NumeriTelefonici i = new NumeriTelefonici(rsM.getString("prefisso_nazionale"), rsM.getString("numero"), rsM.getString("identificatore"),"Mobile" );
				listaNumeri.add(i);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaNumeri;
	}
	
	
	public int addIndirizziDB (int idContatto, String via, String citta, String codicePostale, String nazione, String tag, boolean principale ) {
		PreparedStatement addIndirizziDB;
		PreparedStatement addAbitaDB;
		int idIndirizzo = 0;
		try {
			addIndirizziDB = connection.prepareStatement("INSERT INTO indirizzi (via,città,codice_postale,nazione) VALUES (?,?,?,?) RETURNING indirizzi_id AS id ;");
			addIndirizziDB.setString(1, via);
			addIndirizziDB.setString(2, citta);
			addIndirizziDB.setString(3, codicePostale);
			addIndirizziDB.setString(4, nazione);
			ResultSet rs = addIndirizziDB.executeQuery();
			rs.next();
			idIndirizzo = rs.getInt("id");
			addAbitaDB = connection.prepareStatement("INSERT INTO abita VALUES (?,?,?,?);");
			addAbitaDB.setInt(1, idIndirizzo);
			addAbitaDB.setInt(2, idContatto);
			addAbitaDB.setBoolean(3, principale);
			addAbitaDB.setString(4, tag);
			addAbitaDB.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return idIndirizzo;
	}
	
	public void deleteIndirizzoDB(int indirizzoID) throws SQLException {
		PreparedStatement deleteIndirizzoPS;
		deleteIndirizzoPS = connection.prepareStatement("DELETE FROM indirizzi WHERE indirizzi_id = ?");
		deleteIndirizzoPS.setInt(1, indirizzoID);
		deleteIndirizzoPS.execute();
	}
	
	
	public ArrayList<Indirizzi> getListaIndirizzi (int contattoID){
		PreparedStatement getListaIndirizziPS;
		ArrayList<Indirizzi> listaIndirizzi = new ArrayList<>();
		try {
			getListaIndirizziPS = connection.prepareStatement("SELECT i.*,ab.abitazione_principale, ab.identificatore FROM indirizzi i,abita ab WHERE ab.contatto_associato = ? AND ab.indirizzo_associato = i.indirizzi_id ORDER BY abitazione_principale desc;\r\n"
					+ "");
			getListaIndirizziPS.setInt(1, contattoID);
			ResultSet rs = getListaIndirizziPS.executeQuery();
			while (rs.next()) {
				Indirizzi i = new Indirizzi(rs.getInt("indirizzi_id"), rs.getBoolean("abitazione_principale"), rs.getString("via"),rs.getString("citt�"),rs.getString("codice_postale"),rs.getString("nazione"),rs.getString("identificatore"));
			
				listaIndirizzi.add(i);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaIndirizzi;
	}
	
	public int addAccountDB (int idContatto ,String nickname, String fornitore, String benvenuto, String email) {
		PreparedStatement addAccountPS;
		int idAccount = 0;
		try {
			addAccountPS = connection.prepareStatement("INSERT INTO account (contatto_associato,fornitore,nickname,mail,frase_di_benvenuto) VALUES (?,?,?,?,?) RETURNING account_id;");
			addAccountPS.setInt(1, idContatto);
			addAccountPS.setString(2, fornitore);
			addAccountPS.setString(3, nickname);
			addAccountPS.setString(4, email);
			addAccountPS.setString(5, benvenuto);
			
			ResultSet rs = addAccountPS.executeQuery();
			rs.next();
			idAccount = rs.getInt("account_id");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return idAccount;
	}
	
	public void deleteAccountDB(int idOld) {
        // TODO Auto-generated method stub
        PreparedStatement EliminaAccountPS;
        try {
            EliminaAccountPS=connection.prepareStatement("DELETE FROM account WHERE account_id = ?;");
            EliminaAccountPS.setInt(1, idOld);
            EliminaAccountPS.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
	
	public ArrayList<Account> getListaAccount (int id) {
		PreparedStatement getListaAccountPS;
		ArrayList<Account> listaAccount = new ArrayList<>();
		try {
			getListaAccountPS = connection.prepareStatement("SELECT * FROM account WHERE contatto_associato = ? ORDER BY account_id;");
			getListaAccountPS.setInt(1, id);
			ResultSet rs = getListaAccountPS.executeQuery();
			while (rs.next()) {
				Account i = new Account(rs.getInt("account_id"),rs.getString("fornitore"), rs.getString("nickname"), rs.getString("frase_di_benvenuto"), rs.getString("mail"));
				listaAccount.add(i);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAccount;
	}
	
	public void addEmailDB (int idContatto, String email) {
		PreparedStatement addEmailPS;
		try {
		addEmailPS = connection.prepareStatement("INSERT INTO mail VALUES (?,?);");
		addEmailPS.setString(1, email);
		addEmailPS.setInt(2, idContatto);
		addEmailPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateMail(int idContatto, String oldEmail, String newEmail) throws SQLException {
        PreparedStatement updateEmailPS;
        updateEmailPS = connection.prepareStatement("UPDATE mail SET indirizzo_email = ? WHERE indirizzo_email = ? and contatto_associato = ? ;");
        updateEmailPS.setString(1, newEmail);
        updateEmailPS.setString(2, oldEmail);
        updateEmailPS.setInt(3, idContatto);

        updateEmailPS.execute();
    }
	
	public void deleteMail(int idContatto, String email) throws SQLException {
        PreparedStatement deleteEmailPS;
        deleteEmailPS = connection.prepareStatement("DELETE FROM mail WHERE indirizzo_email = ? and contatto_associato = ? ;");
        deleteEmailPS.setString(1, email);
        deleteEmailPS.setInt(2, idContatto);
        deleteEmailPS.execute();

  }
	
	public ArrayList<String> getListaEmail (int id) {
		PreparedStatement getListaEmailPS;
		ArrayList<String> listaEmail = new ArrayList<>();
		try {
			getListaEmailPS = connection.prepareStatement("SELECT Indirizzo_email  FROM Mail WHERE  Contatto_Associato= ?;");
			getListaEmailPS.setInt(1, id);
			ResultSet rs = getListaEmailPS.executeQuery();
			while (rs.next()) {
				String i = new String(rs.getString("indirizzo_email"));
				listaEmail.add(i);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEmail;
	}
	
	public void setDeputatoMobileSuFIsso(int idContatto,String mobilePrefisso,String mobileNumero,String fissoPrefisso,String fissoNumero) {
        PreparedStatement getDeputato ;
        try {
            getDeputato=connection.prepareStatement("update Numeri_telefonici_mobili set \"reindirizzamento\" = ? where \"numero_id\" =  ?  ;");
        
            
            getDeputato.setInt(1, getIDNumeroFisso(fissoPrefisso, fissoNumero,idContatto));
           

            getDeputato.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        } 

    public void setDeputatoFissoSuMobile(int idContatto,String mobilePrefisso,String mobileNumero, String fissoPrefisso,String fissoNumero) {
        PreparedStatement getDeputato ;
        try {
            getDeputato=connection.prepareStatement("update Numeri_telefonici_fissi set reindirizzamento = ? where numero_id =  ?  ;");
            
        
            
            getDeputato.setInt(2, getIDNumeroFisso(fissoPrefisso, fissoNumero,idContatto));
            
    
            
            getDeputato.setInt(1, getIDNumeroMobile(mobilePrefisso, mobileNumero,idContatto));
         
           getDeputato.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

        } 

	
    public int getIDNumeroMobile(String prefissoMobile,String numeroMobile, int idContatto) throws Exception {
        PreparedStatement getIDNumero;
        int id = 0;
        try {
            getIDNumero=connection.prepareStatement("Select *  from NUMERI_TELEFONICI_MOBILI Where PREFISSO_NAZIONALE = ? AND NUMERO = ? AND contatto_associato=?;");
            getIDNumero.setString(1, prefissoMobile);
            getIDNumero.setString(2, numeroMobile);
            getIDNumero.setInt(3,idContatto);
            ResultSet rs= getIDNumero.executeQuery();
            rs.next();
            id =rs.getInt(1);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        if(id==0) throw new Exception("Id Numero Mobile non trovato ");
        return id;
    }

     public int getIDNumeroFisso(String prefissoFisso,String numeroFisso,int idContatto) throws Exception {
        PreparedStatement getIDNumero;
        int id = 0;
        try {
            getIDNumero=connection.prepareStatement("Select *  from NUMERI_TELEFONICI_FISSI Where PREFISSO_NAZIONALE = ? AND NUMERO = ? AND contatto_associato=?;");
            getIDNumero.setString(1, prefissoFisso);
            getIDNumero.setString(2, numeroFisso);
            getIDNumero.setInt(3,idContatto);
            ResultSet rs= getIDNumero.executeQuery();
            if(rs.next()==false ) throw new Exception("RSEULSETT VUOTOOO Id Numero Fisso non trovato ");
            id =rs.getInt(1);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        if(id==0) throw new Exception("Id Numero Fisso non trovato ");
        return id;
    }

     public ArrayList<Integer> SearchAnagrafica(String prefisso, String nome, String cognome) {
 		PreparedStatement searchAnagraficaPS;
 		ArrayList<Integer> listaRisultato = new ArrayList<>();
 		try {
 		
 			searchAnagraficaPS = connection.prepareStatement("SELECT Contatto_ID FROM Contatto WHERE Prefisso_Nome LIKE ? AND Nome LIKE ? AND Cognome LIKE ?;");
 			searchAnagraficaPS.setString(1,prefisso);
 			searchAnagraficaPS.setString(2,nome);
 			searchAnagraficaPS.setString(3,cognome);
 			
 			ResultSet rs = searchAnagraficaPS.executeQuery();
 			while (rs.next()) {
 				listaRisultato.add(rs.getInt("Contatto_ID"));
 			}	
 		}
 		catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return listaRisultato;
 	}

 	public  ArrayList<Integer> SearchMail(String mail) {
 		PreparedStatement searchMailPS;
 		ArrayList<Integer> listaRisultato = new ArrayList<>();
 		try {
 			searchMailPS = connection.prepareStatement("SELECT  \"contatto_associato\" FROM Mail WHERE mail.\"indirizzo_email\" LIKE ?;");
 			searchMailPS.setString(1,mail);
 			ResultSet rs = searchMailPS.executeQuery();
 			while (rs.next()) {
 				listaRisultato.add(rs.getInt("contatto_associato"));
 				}	
 		}
 		catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return listaRisultato;
 	}

 	public  ArrayList<Integer> SearchAccount(String nickname, String fornitore)  {
 		PreparedStatement searchAccountPS;
 		ArrayList<Integer> listaRisultato = new ArrayList<>();
 		try {
 			searchAccountPS = connection.prepareStatement("SELECT \"contatto_associato\" FROM Account WHERE fornitore LIKE ? AND nickname LIKE ?;");
 			searchAccountPS.setString(1,fornitore);
 			searchAccountPS.setString(2, nickname);
 			ResultSet rs = searchAccountPS.executeQuery();
 			while (rs.next()) {
 				listaRisultato.add(rs.getInt("contatto_associato"));
 				}
 		}
 		catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return listaRisultato;
 	}

 	public ArrayList<Integer> SearchNumeri(String prefissoNumero, String numero, String tipoNumero) {
		PreparedStatement searchNumeriPS;
		ArrayList<Integer> listaRisultato = new ArrayList<>();
		try {
			if (tipoNumero=="Fisso") {
				searchNumeriPS = connection.prepareStatement("SELECT \"contatto_associato\" FROM \"numeri_telefonici_fissi\" WHERE prefisso_nazionale LIKE ? AND numero LIKE ?;");
				searchNumeriPS.setString(1,prefissoNumero);
				searchNumeriPS.setString(2, numero);
			}
			else
			{
			searchNumeriPS = connection.prepareStatement("SELECT \"contatto_associato\" FROM \"numeri_telefonici_mobili\" WHERE prefisso_nazionale LIKE ? AND numero LIKE ?;");
			searchNumeriPS.setString(1,prefissoNumero);
			searchNumeriPS.setString(2, numero);
			}
			
			ResultSet rs = searchNumeriPS.executeQuery();
			while (rs.next()) {
				listaRisultato.add(rs.getInt("contatto_associato"));
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaRisultato;
	}
 	
	public void setFoto(int id, String pathDestiCompleto) {
		PreparedStatement setFotoPS;
		try {
			setFotoPS = connection.prepareStatement("UPDATE Contatto SET  Path_Foto = ? WHERE Contatto_ID = ?;");
			setFotoPS.setString(1,pathDestiCompleto);
			setFotoPS.setInt(2,id);
			setFotoPS.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


	public void updateContattoDB (int idContatto, String prefisso, String nome, String cognome, String pathFoto) {
        PreparedStatement updateContattoPS;
        try {
            updateContattoPS = connection.prepareStatement("UPDATE Contatto SET prefisso_nome = ?, nome = ?, cognome = ?, path_foto = ? WHERE contatto_id = ? ;");
            updateContattoPS.setString(1, prefisso);
            updateContattoPS.setString(2, nome);
            updateContattoPS.setString(3, cognome);
            updateContattoPS.setString(4, pathFoto);
            updateContattoPS.setInt(5, idContatto);
            updateContattoPS.execute();
//            System.out.print("Sono DaoContatto.UpdateContattoDB:- query:-"+updateContattoPS.toString());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


	
	
	
}