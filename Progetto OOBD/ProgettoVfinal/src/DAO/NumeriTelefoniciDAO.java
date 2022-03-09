package DAO;

import Model.NumeriTelefonici;
import java.sql.SQLException;

public interface NumeriTelefoniciDAO {


    public void updateNumeroDB (int idContatto,int idNumero,String tag, String prefisso,String numero,String TipoNew,String TipoOLD) throws SQLException ;
    
    public NumeriTelefonici readNumeroDB (int idNumero,String tipo) throws Exception;
}