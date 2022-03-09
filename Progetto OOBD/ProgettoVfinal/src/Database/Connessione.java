package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {

	// ATTRIBUTI
	private static Connessione istanza;
	private Connection connection = null;
	
	/*Per il testing in locale
	  private String nome = "AccessoProgetto";
	  private String password = "BarraTramontana";
	 private String url = "jdbc:postgresql://localhost:5432/Progetto3";
	 */
	
	private String url = "jdbc:postgresql://progetto.c95ssxjq5g6b.us-east-1.rds.amazonaws.com:5432/ProgettoOOBD";
	private String nome = "LAR";
	private String password = "UninaLAR";
	private String driver = "org.postgresql.Driver";

	// COSTRUTTORE
	public Connessione() throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, nome, password);

		} catch (ClassNotFoundException ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	public Connection getConnection() {
			return connection;
	}
	
	public static Connessione getInstance() throws SQLException {
		if (istanza == null) {
			istanza = new Connessione();
		} else if (istanza.getConnection().isClosed()) {
			istanza = new Connessione();
		}
		return istanza;
	}

	
}