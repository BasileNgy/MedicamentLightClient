package medicaments.DAO.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import medicaments.DAO.client.MotherClassDAO;
import medicaments.classes.Messagerie;


/**
 * This class give access to table of "messagerie" in database
 * 
 * @author Ansoumane
 * @see The elements of the Composition class
 */

public class MessagerieDAO extends MotherClassDAO
{
	/**
	 * Builder of the class, creates the connection
	 */
	public MessagerieDAO()
	{
		super();
	}
	
	/**
	 * Allows to save in the database each new message send by a user
	 * 
	 * @param messagerie
	 * 
	 * @return News messages send
	 */
	public void addMessagerie(Messagerie messagerie) 
	{

		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `messagerie`(`mail`, `objet`, `message`, `timestamp`)"
				+ "VALUES (?,?,?,CURRENT_TIMESTAMP())");)
		{
			ps.setString(1, messagerie.getMail());
			ps.setString(2, messagerie.getObjet());
			ps.setString(3, messagerie.getMessage());
			
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
