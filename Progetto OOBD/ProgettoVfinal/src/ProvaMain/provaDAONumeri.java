package ProvaMain;

import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Controller;
import DAO.CassaforteDAO;
import DAO.ContattoDAO;
import DAO.GruppoDAO;
import DAO.RubricaDAO;
import ImplementazioneDAOpostgreSQL.ImplementazioneCassaforteDAO;
import ImplementazioneDAOpostgreSQL.ImplementazioneContattoDAO;
import ImplementazioneDAOpostgreSQL.ImplementazioneGruppoDAO;
import ImplementazioneDAOpostgreSQL.ImplementazioneRubricaDAO;
import Model.Account;
import Model.Contatto;
import Model.Gruppo;
import Model.Indirizzi;
import Model.NumeriTelefonici;
import Model.Rubrica;

public class provaDAONumeri {
	public static void main (String args[]) {
		Controller c = new Controller();
		ContattoDAO contattoDao = new ImplementazioneContattoDAO();
		ArrayList<Contatto> schifo = c.SearchAnagrafica("prefisso", "anome", "cognome");
		for (Contatto i : schifo) {
			System.out.println(i.StampaContatto());
		}
	}

}
