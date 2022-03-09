package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.NumeriTelefonici;
import DAO.NumeriTelefoniciDAO;
import Database.Connessione;
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
     
    public void updateNumeroDB (int idContatto,int idNumero,String tag, String prefisso,String numero,String tipoNew,String tipoOLD) throws SQLException {
        //SET numero_id=?, contatto_associato=?, prefisso_nazionale=?, numero=?, identificatore=?, reindirizzamento=?
        String nomeProcedura= "\"Update_Tabelle_Numeri\"";
        
        PreparedStatement proceduraPS;
        
        proceduraPS = connection.prepareStatement("CALL "+nomeProcedura+"(?, ?, ?, ?, ?, ?, ? );");// IDcontatto, IDNumero, PrefissoNazionale, Numero, Tag, TipoOLD, TipoNEW
        int row=1;
        proceduraPS.setInt(row++, idContatto);
        proceduraPS.setInt(row++, idNumero);
        proceduraPS.setString(row++, prefisso);
        proceduraPS.setString(row++, numero);
        proceduraPS.setString(row++, tag);
        proceduraPS.setString(row++, tipoOLD);
        proceduraPS.setString(row++, tipoNew);
        
        proceduraPS.execute();
        
  
        
        	
       
    }

	public NumeriTelefonici readNumeroDB(int idNumero, String tipo) throws Exception {
		NumeriTelefonici numero=null;
		PreparedStatement readNumeroPS;
		PreparedStatement readNumeroDeputatoPS;
		int idDeputato= 0;
        String tabellaTipo= tipo.compareToIgnoreCase("fisso") ==0 ? "fissi" : "mobili";
        String tabellatipoDeputato= tipo.compareToIgnoreCase("fisso") !=0 ? "fissi" : "mobili";
        String tipoDeputato= tipo.compareToIgnoreCase("fisso") !=0 ? "Fisso" : "Mobile";
            readNumeroPS = connection.prepareStatement("SELECT * FROM numeri_telefonici_"+tabellaTipo+" WHERE numero_id = ?;");
            readNumeroPS.setInt(1, idNumero);
            ResultSet rs = readNumeroPS.executeQuery();
            if(rs.next()) {
               numero= new NumeriTelefonici ( rs.getString("identificatore"),rs.getString("prefisso_nazionale"), rs.getString("numero"), tipo);
               idDeputato=rs.getInt("reindirizzamento");
               if(idDeputato>0) {
            	   readNumeroDeputatoPS = connection.prepareStatement("SELECT * FROM numeri_telefonici_"+tabellatipoDeputato+" WHERE numero_id = ?;");
            	   readNumeroDeputatoPS.setInt(1, idDeputato);
            	   ResultSet rsDeputato =  readNumeroDeputatoPS.executeQuery();
            	   if(rsDeputato.next()) {
            		   //String tag,String string, String Numero, String tipoNumero)
            		   numero.setDeputato(new NumeriTelefonici(rs.getString("identificatore"),rs.getString("prefisso_nazionale"), rs.getString("numero"), tipoDeputato) );
            	   }
               }
               
               
            }else {
            	throw new Exception("Errore nella ricerca del numero ");
            }
     
        return numero;
	
	}
    
	public NumeriTelefonici readDeputatoDiNumeroDB(int idNumero, String tipo)  {
     		NumeriTelefonici numero=null;
     		PreparedStatement readNumeroPS;
     		PreparedStatement readNumeroDeputatoPS;
     		
     		int idDeputato= 0;
             String tabellaTipo= tipo.compareToIgnoreCase("fisso") ==0 ? "fissi" : "mobili";
             String tabellatipoDeputato= tipo.compareToIgnoreCase("fisso") !=0 ? "fissi" : "mobili";
             String tipoDeputato= tipo.compareToIgnoreCase("fisso") !=0 ? "Fisso" : "Mobile";
            try {
             readNumeroPS = connection.prepareStatement("SELECT * FROM numeri_telefonici_"+tabellaTipo+" WHERE numero_id = ?;");
                 readNumeroPS.setInt(1, idNumero);
                 System.out.println("Sono IMPLNumeriDao nerlla readDep query:-"+readNumeroPS.toString());

                 ResultSet rs = readNumeroPS.executeQuery();
                 if(rs.next()) {
                    idDeputato=rs.getInt("reindirizzamento");
                    if(idDeputato>0) {
                 	   readNumeroDeputatoPS = connection.prepareStatement("SELECT * FROM numeri_telefonici_"+tabellatipoDeputato+" WHERE numero_id = ?;");
                 	   readNumeroDeputatoPS.setInt(1, idDeputato);
                 	  System.out.println("Sono IMPLNumeriDao nerlla readDep query2:-"+readNumeroDeputatoPS.toString());
                 	   ResultSet rsDeputato =  readNumeroDeputatoPS.executeQuery();
                 	   
                 	   if(rsDeputato.next()) {
                 		  
                 		  numero= new NumeriTelefonici(rsDeputato.getString("identificatore"),rsDeputato.getString("prefisso_nazionale"), rsDeputato.getString("numero"), tipoDeputato) ;
                 	   }
                    }
         
         
      }else {
      	throw new Exception("Errore nella ricerca del numero ");
      }
            }catch(Exception e) {
            		numero=null;
            }
            
  return numero;

}
   
    
  
	
}
