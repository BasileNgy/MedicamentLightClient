package medicaments.DAO.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import medicaments.classes.Presentation;


/**
 * DAO class : presentation permit to retrieve presentation data from database
 * @author Basile Loic
 *
 */
public class PresentationDAO extends MotherClassDAO {

	/**
	 * Builder of the class, creates the connection
	 */
	public PresentationDAO() {
		super();
	}

	/**
	 * Returns the list of medicaments that contains the reasearch parameter in its
	 * name
	 *
	 * @param research the parameter that we want to check the disponibility in the
	 *                 db
	 * @return the matching list
	 */
	public ArrayList<Presentation> getPresentationList(int cisCode) {

		// The list that is going to be returned
		ArrayList<Presentation> result = new ArrayList<Presentation>();
		// A temporary variable to save every medicament in the list
		Presentation pres;
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM presentation WHERE `code_CIS` = ?;");) {

			ps.setInt(1, cisCode);
			;
			// We execute the query and get the result back
			try (ResultSet rset = ps.executeQuery();) {
				// Case we have no result with the given parameters
				if (rset.next())
					// Case we have results with the given parameters
					do {

						pres = new Presentation();

						pres.setId(rset.getInt(1));
						pres.setCodeCIS(rset.getInt(2));
						pres.setCodeCIP7(rset.getString(3));
						pres.setLibelle(rset.getString(4));
						pres.setStatutAdministratif(rset.getString(5));
						pres.setEtatCommercialisation(rset.getString(6));
						pres.setDateDeclarationCommercialisation(rset.getString(7));
						pres.setCodeCIP13(rset.getString(8));
						pres.setAgrementCollectivites(rset.getString(9));
						pres.setTauxRemboursement(rset.getString(10));
						pres.setPrix(rset.getString(11));
						pres.setIndicationRemboursement(rset.getString(12));

						result.add(pres);

					} while (rset.next());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Finally we return the result
		return result;
	}

}
