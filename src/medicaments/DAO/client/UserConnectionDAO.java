package medicaments.DAO.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 * La classe UserConnectionDAO permet de faire le lien entre la BDD et le client leger
 * @author Raoph
 *
 */
public class UserConnectionDAO extends MotherClassDAO{

	/**
	 * Methode permettant d'ajouter une connection dans la BDD en la sauvegardant avec l'heure et la date actuelle
	 * @param login Email du compte s'etant connecte
	 * @param remote_ip Adresse IP effectuant la connection
	 */
	public void addUserConnexion(String login,String remote_ip) {


		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `user_connection`(`id_utilisateur`, `remote_ip`, `timestamp`) VALUES ((SELECT u.id FROM utilisateur u WHERE login=?),?,CURRENT_TIMESTAMP())");){

			ps.setString(1, login);
			ps.setString(2, remote_ip);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
