package ImplementazioneDAOpostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.*;
import DAO.GruppiDAO;
import Model.Account;
import Model.Contatto;
import Model.Gruppo;

public class ImplementazioneGruppiDAO implements GruppiDAO {

	private Connection connection;

	public void aggiungiMembroGruppoDB(Gruppo nomeGruppo, Contatto c) {
		PreparedStatement addMembroPS;
		try {
			addMembroPS = connection.prepareStatement("INSERT INTO Appartenenza (gruppo_nome,Contatto_ID) VALUES (?,?)");
			addMembroPS.setString(1, nomeGruppo.getNomeGruppo());
			addMembroPS.setInt(2, c.getID());
			addMembroPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void eliminaMembroGruppoDB(Gruppo nomeGruppo, Contatto c) {
		// TODO Auto-generated method stub
		PreparedStatement deleteMembroPS;
		try {
			deleteMembroPS = connection.prepareStatement("DELETE FROM Appartenenza WHERE gruppo_nome=? AND Contatto_ID=?");
			deleteMembroPS.setString(1, nomeGruppo.getNomeGruppo());
			deleteMembroPS.setInt(2, c.getID());
			deleteMembroPS.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public ArrayList<String> leggiGruppiDB() {
		PreparedStatement leggiGruppiPS;
		ArrayList<String> listaGruppi = new ArrayList<>();
		try {
			leggiGruppiPS = connection.prepareStatement("SELECT * FROM gruppi");
			ResultSet rs = leggiGruppiPS.executeQuery();
			while (rs.next()) {
				String i = rs.getString("nome");
				listaGruppi.add(i);
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listaGruppi;
	}

}
