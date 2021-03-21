package medicaments.DAO.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import medicaments.classes.InfosImportante;

/**
 * DAO class : infos importantes permit to retrieve infos importantes data and link
 * @author Basile
 *
 */
public class InfosImportanteDAO extends MotherClassDAO {

	public InfosImportanteDAO() {
		super();
	}

	/**
	 * Returns infosImportante object list that corresponds to this code CIS
	 *
	 * @param code cis researched
	 *
	 * @return the list of composition object
	 */
	public ArrayList<InfosImportante> getInfosImportante(int cisCode) 
	{
		ArrayList<InfosImportante> list = new ArrayList<InfosImportante>();
		InfosImportante info;
		
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM infos_importantes WHERE `code_CIS` = ?");) {
			
			ps.setInt(1, cisCode);
			try (ResultSet rset = ps.executeQuery();) 
			{
				// Case we have results with the given parameters
				if (rset.next())
				do {

					info = new InfosImportante(cisCode, rset.getString(3), rset.getString(4), rset.getString(5));
					list.add(info);

				} while (rset.next());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Finally we return the result
		return list;
	}
}

