package DAO;

import Model.NumeriTelefonici;
import java.sql.SQLException;

public interface NumeriTelefoniciDAO {


	/**
	 * esegue una query sulla tabella numeri_telefonici_fissi o numeri_telefonici_mobili a seconda del nuovo e del vecchio tipo
	 * del numero
	 * @param idContatto id del contatto associato al numero da modificare
	 * @param idNumero id del numero da modificare
	 * @param tag Stringa rappresentante il tag da impostare nel numero
	 * @param prefisso Stringa rappresentante il prefisso nazionale da impostare al numero
	 * @param numero Stringa rappresentante il numero da impostare
	 * @param TipoNew Stringa rappresentante il nuovo tipo da impostare al nuovo numero
	 * @param TipoOLD Stringa rappresentante il vecchio tipo del numero prima della modifica
	 * @throws SQLException
	 */
    public void updateNumeroDB (int idContatto,int idNumero,String tag, String prefisso,String numero,String TipoNew,String TipoOLD) throws SQLException ;
    
    /**
     * esegue una query sulla tabella numeri_telefonici_fissi o numeri_telefonici_mobili a seconda del tipo che restituisce un numero
     * @param idNumero identificatore del numero da leggere
     * @param tipo Stringa rappresentante il tipo del numero da restituire
     * @return oggetto della classe NumeriTelefonici dato dal ResultSet
     * @throws Exception
     */
    public NumeriTelefonici readNumeroDB (int idNumero,String tipo) throws Exception;
}