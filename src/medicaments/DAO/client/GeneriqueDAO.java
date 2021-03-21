package medicaments.DAO.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import medicaments.classes.Generique;
/**
 * DAO class : generique permit to retrieve generique data and medicament involved
 * @author Basile
 *
 */
public class GeneriqueDAO extends MotherClassDAO {

	public GeneriqueDAO() {
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
	private ArrayList<Integer> getListCodeCISOfGroupe(int id_groupe) {

		// The list that is going to be returned
		ArrayList<Integer> result = new ArrayList<Integer>();
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				PreparedStatement ps = conn
						.prepareStatement("SELECT Code_CIS FROM generique WHERE `id_groupe` = ?;");) {

			ps.setInt(1, id_groupe);
			// We execute the query and get the result back
			try (ResultSet rset = ps.executeQuery();) {
				// Case we have no result with the given parameters
				if (rset.next())

					// Case we have results with the given parameters
					do {
						result.add(rset.getInt(1));

					} while (rset.next());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Finally we return the result
		return result;
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
	private ArrayList<Generique> getGeneriqueListBySearch(String search) {

		// The list that is going to be returned
		ArrayList<Generique> result = new ArrayList<Generique>();
		// A temporary variable to save every medicament in the list
		Generique gen;

		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				PreparedStatement ps = conn
						.prepareStatement("SELECT g.* FROM generique g, medicament m WHERE g.libelle_groupe LIKE ? AND g.code_CIS = m.code_CIS ");) {

			ps.setString(1, "%" + search + "%");
			// We execute the query and get the result back
			try (ResultSet rset = ps.executeQuery();) {
				// Case we have no result with the given parameters
				if (rset.next())

					// Case we have results with the given parameters
					do {
						gen = new Generique(rset.getInt(2), rset.getString(3), rset.getInt(4), rset.getInt(5),
								rset.getInt(6));
						// on test si un �l�ment du grp g�n�rique est d�j� pr�sent dans la liste
						// si c'est le cas, on ne l'ajoute pas � la liste
						if (uniciteListBySearch(gen, result))
							result.add(gen);

					} while (rset.next());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Finally we return the result
		return result;
	}

	/**
	 * Prevent to put 2 element of the same generic group (idGroup) is in the list
	 * compare idGroupe of the new Generic element and the whole list id groupe
	 *
	 * @param newGen
	 * @param list
	 * @return true if idGen is unique and can be added, false if not
	 */
	public Boolean uniciteListBySearch(Generique newGen, ArrayList<Generique> list) {
		for (Generique gen : list) {
			if (gen.getIDGroupe() == newGen.getIDGroupe())
				return false;
		}
		return true;
	}

	/**
	 * permit to retrieve an arraylist of generique object that contains an
	 * arraylist of medicament
	 *
	 * @param search string to search in db
	 * @return arraylist of generique object
	 */
	public ArrayList<Generique> getListGeneriqueWithMedicament(String search) {
		ArrayList<Generique> listGenerique = new ArrayList<Generique>();
		ArrayList<Integer> listCodeCIS = new ArrayList<Integer>();
		MedicamentDAO medDAO = new MedicamentDAO();
		// recup�re une liste d'objets generique dont le nom correspond � la recherche
		listGenerique = getGeneriqueListBySearch(search);

		for (Generique gen : listGenerique) {
			// on r�cup�re la liste des codeCIS de chaque groupe
			listCodeCIS = getListCodeCISOfGroupe(gen.getIDGroupe());
			for (Integer i : listCodeCIS) {
				// on ajout chaque medicament � l'objet g�nerique grace � son code cis
				if(medDAO.getMedicament(i).getDenomination()!=null) {
					gen.addMedicament(medDAO.getMedicament(i));
				}
			}
		}
		return listGenerique;
	}

	/**
	 * Returns the list of generique for a code cis
	 * 
	 * @author Loic
	 * 
	 * @param codeCIS the code cis of the list of generique
	 * @return the list of generique
	 */
	public ArrayList<Generique> getListGeneriqueFromCIS(int codeCIS) {

		ArrayList<Generique> listGenerique = new ArrayList<Generique>();
		Generique tmpGen;

		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM generique WHERE `code_CIS` LIKE ?");) {

			ps.setInt(1, codeCIS);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					do {
						tmpGen = new Generique();
						tmpGen.setCodeCIS(codeCIS);
						tmpGen.setIdGroupe(rs.getInt("id_groupe"));
						tmpGen.setLibelleGroupe(rs.getString("libelle_groupe"));
						tmpGen.setNumTri(rs.getInt("numero_tri"));
						tmpGen.setType(rs.getInt("type"));
						listGenerique.add(tmpGen);
					} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listGenerique;
	}

}
