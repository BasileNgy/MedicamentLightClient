package medicaments.DAO.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import medicaments.classes.Prescription;

/**
 * DAO class : prescription permit to retrieve prescription data
 * @author Basile
 *
 */
public class PrescriptionDAO extends MotherClassDAO{

	/** Parameter less constructor **/
	public PrescriptionDAO() {
		super();
	}


	/**
	 * Returns the list of medicaments that contains the research parameter in its
	 * name
	 *
	 * @param research the parameter that we want to check the disponibility in the db
	 *
	 * @return the matching list
	 */
	public ArrayList<Prescription> getPrescriptionList(int cisCode) {

		// The list that is going to be returned
		ArrayList<Prescription> result = new ArrayList<Prescription>();

		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM prescription WHERE `code_CIS` = ?;");){

			ps.setInt(1, cisCode);
			//We execute the query and get the result back
			try(ResultSet rset = ps.executeQuery();){
			//Case we have no result with the given parameters
			if (rset.next())
			//Case we have results with the given parameters
			do {
				result.add(new Prescription(rset.getInt(2), rset.getString(3)));

			} while (rset.next());

		} } catch (SQLException e) {
			e.printStackTrace();
		}
		//Finally we return the result
		return result;
	}
}
