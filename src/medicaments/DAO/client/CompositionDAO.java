package medicaments.DAO.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import medicaments.classes.Composition;

/**
 * Cette classe donne l'accès au stockage de la table "Composition" de la BDD
 * 
 * @author ansou
 * @see La composition des médicaments @
 */

public class CompositionDAO extends MotherClassDAO {

	public CompositionDAO() {
		super();
	}

	/**
	 * Renvoie la composition des médicaments qui correspond au CodeCIS commun à eux
	 *
	 * @param recherche CodeCIS Correspondant au médicament
	 *
	 * @return Les éléments de la classe Composition
	 */

	public ArrayList<Composition> getListComposition(int codeCIS) {
		// La liste à retournée
		ArrayList<Composition> result = new ArrayList<Composition>();

		// Une variable temporaire pour enregistrer chaque médicament de la liste
		Composition comp;

		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM composition WHERE `code_CIS` = ?");) {
			// Initialisation de la conn

			// Prepare the statement

			ps.setInt(1, codeCIS);
			// Exécution de la requête et récupération du résultat
			try (ResultSet rset = ps.executeQuery();) {
				// Dans le cas où nous n'avons aucun résultat avec les paramètres donnés
				if (rset.next())
					// Dans le cas où nous avons des résultats avec les paramètres donnés
					do {
						comp = new Composition(rset.getInt(2), rset.getString(3), rset.getString(4), rset.getString(5),
								rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9));

						result.add(comp);

					} while (rset.next());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Nous retournons le résultat
		return result;
	}

}
