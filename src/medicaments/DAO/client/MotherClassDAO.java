package medicaments.DAO.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Mother class of the other DAO classes, used to connect to the remote DB
 * 
 * @author Loic
 *
 */
public abstract class MotherClassDAO {

	/** Log informations to the db **/
	protected String URL;
	protected String LOGIN;
	protected String PASS;
	/** Config file containing the DB access infos **/
	private Properties config = new Properties();

	/**
	 * Instantiate the connection
	 */
	public MotherClassDAO() {

		try (InputStream fis = getClass().getClassLoader().getResourceAsStream("config.properties");) {

			config.load(fis);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			URL = config.getProperty("URL");
			LOGIN = config.getProperty("User");
			PASS = config.getProperty("Password");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(
					"Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
}
