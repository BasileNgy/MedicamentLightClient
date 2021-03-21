package medicaments.DAO.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import medicaments.classes.Medicament;
import medicaments.classes.Titulaire;

/**
 * Medicament DAO class
 *
 * Allows you to access the data stored in the DB for the table Medicament and
 * everything linked to it
 *
 * @author Loic Basile
 *
 */
public class MedicamentDAO extends MotherClassDAO {

	/**
	 * Builder of the class, creates the connection
	 */
	public MedicamentDAO() {
		super();
	}

	/**
	 * Returns the list of medicaments that contains the research parameter in its
	 * name
	 *
	 * @param research the parameter that we want to check the disponibility in the
	 *                 db
	 *
	 * @return the matching list
	 */
	public ArrayList<Medicament> getMedicamentListBySearch(String search) {

		// The list that is going to be returned
		ArrayList<Medicament> result = new ArrayList<Medicament>();
		// A temporary variable to save every medicament in the list
		Medicament med;
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM medicament WHERE `denomination` LIKE ?");) {
			ps.setString(1, "%" + search + "%");
			// We execute the query and get the result back
			try (ResultSet rset = ps.executeQuery();) {

				if (rset.next())
					// Case we have results with the given parameters
					do {
						med = new Medicament(rset.getInt(2), rset.getString(3), rset.getString(4), rset.getString(5),
								rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
								rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13));
						// on test si un �l�ment du grp g�n�rique est d�j� pr�sent dans la liste
						// si c'est le cas, on ne l'ajoute pas � la liste
						result.add(med);

					} while (rset.next());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Finally we return the result
		return result;
	}
	
	

	public ArrayList<Medicament> getMedicamentListBySearch2(String search) {

		// The list that is going to be returned
		ArrayList<Medicament> result = new ArrayList<Medicament>();
		// A temporary variable to save every medicament in the list
		Medicament med;
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
		PreparedStatement ps = conn.prepareStatement(" SELECT * FROM medicament WHERE etat_commercialisation='Commercialisée' AND denomination LIKE ? ");) {
		ps.setString(1, "%" + search + "%");
		// We execute the query and get the result back
		try (ResultSet rset = ps.executeQuery();) {

		if (rset.next())
		// Case we have results with the given parameters
		do {
		med = new Medicament(rset.getInt(2), rset.getString(3), rset.getString(4), rset.getString(5),
		rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
		rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13));
		// on test si un l ment du grp g n rique est d j pr sent dans la liste
		// si c'est le cas, on ne l'ajoute pas la liste
		result.add(med);
		} while (rset.next());

		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		// Finally we return the result
		return result;
		}
	

	/**
	 * Returns the object medicmament that corresponds to this code CIS
	 *
	 * @param research the parameter that we want to check the disponibility in the
	 *                 db
	 *
	 * @return the name of the medicament
	 */
	public Medicament getMedicament(int cisCode) {
		Medicament med = new Medicament();
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM medicament WHERE `code_CIS` = ?");) {

			ps.setInt(1, cisCode);
			// We execute the query and get the result back
			try (ResultSet rset = ps.executeQuery();) {
				// Case we have results with the given parameters
				if (rset.next())

					do {

						med = new Medicament(cisCode, rset.getString(3), rset.getString(4), rset.getString(5),
								rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
								rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13));
					} while (rset.next());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Finally we return the result
		return med;
	}

	/**
	 * Returns the list of integer cis code in db
	 *
	 * @return whole list of code CIS
	 */
	public ArrayList<Integer> getListCodeCIS() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT code_CIS FROM medicament");) {

			if (rset.next())

				while (rset.next())
					list.add(rset.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Finally we return the result
		return list;
	}
		
	

	/**
	 * Returns the list of Titulaire object that contains name and number of medicaments they own
	 *
	 * @return  list of titulaire object
	 */
	public ArrayList<Titulaire> getListTitulaire() {
		ArrayList<Titulaire> list = new ArrayList<Titulaire>();

		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT Titulaire, COUNT(*) FROM medicament GROUP BY Titulaire");) {

			if (rset.next())

				while (rset.next())
					list.add(new Titulaire (rset.getString(1), rset.getInt(2)));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Finally we return the result
		return list;
	}

	
	/**
	 * Returns the list of price by year
	 *
	 * @param year searched
	 *
	 * @return all prices of this year
	 */
	public ArrayList<Double> getListPrixParAnnee(int annee) {

		// The list that is going to be returned
		ArrayList<Double> result = new ArrayList<Double>();
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				PreparedStatement ps = conn
						.prepareStatement("SELECT medicament.code_CIS,`denomination`,date_amm, presentation.prix FROM medicament\n" + 
								"	INNER JOIN presentation ON medicament.code_CIS = presentation.code_CIS\n" + 
								"	WHERE `date_amm` LIKE ?");) {

			ps.setString(1, "%"+annee);
			// We execute the query and get the result back
			try (ResultSet rset = ps.executeQuery();) {
				// Case we have no result with the given parameters
				if (rset.next())

					// Case we have results with the given parameters
					do {
						String prix = rset.getString(4);
						try {
							if (prix.contains(",")) {
								if (prix.length() > 6) {
									prix = prix.replaceFirst(",", "");
									prix = prix.replaceFirst(",", ".");
								}else {
									prix = prix.replace(",", ".");
								}
									result.add(Double.parseDouble(prix));
							}
								
						}catch(Exception e) {
							e.printStackTrace();
						}
					} while (rset.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Finally we return the result
		return result;
	}
	

	
}
