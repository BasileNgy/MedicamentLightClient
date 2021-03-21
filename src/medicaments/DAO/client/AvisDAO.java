package medicaments.DAO.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import medicaments.classes.Avis;


/**
 * DAO class : avis permit to retrieve avis data
 * @author Basile
 *
 */
public class AvisDAO extends MotherClassDAO {

	
	public AvisDAO() {
		super();
	}

	/**
	 * Returns the list of avis SMR object that corresponds to the cis code input
	 * name
	 *
	 * @param codeCIS research cis code corresponding
	 * @param typeAvis asmr / smr
	 *
	 * @return the liste of avis object
	 */
	private ArrayList<Avis> getListAvis(int codeCIS, String typeAvis) {

		// The list that is going to be returned
		ArrayList<Avis> result = new ArrayList<Avis>();
		// A temporary variable to save every medicament in the list
		Avis av;
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM avis_"+typeAvis+" WHERE `code_CIS` = ?");){

			ps.setInt(1, codeCIS );
			//We execute the query and get the result back
			try(ResultSet rset = ps.executeQuery();){

			if (rset.next())
			//Case we have results with the given parameters
			do {
				av = new Avis(rset.getInt(2), rset.getString(3), rset.getString(4), rset.getString(5),
						rset.getString(6), rset.getString(7));
				result.add(av);
			} while (rset.next());

		} } catch (SQLException e) {
			e.printStackTrace();
		}
		//Finally we return the result
		return result;
	}

	/**
	 * Ajoute les liens des avis CT aux avis
	 *
	 * @param research cis code corresponding
	 *
	 * @return the liste of avis object
	 */
	private Avis getLiensAvisComplet(Avis av) {


		// A temporary variable to save every medicament in the list
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
		PreparedStatement ps = conn.prepareStatement("SELECT lien FROM avis_ct WHERE `code_dossier_HAS` = ?");){

			ps.setString(1, av.getCodeDossierHAS() );
			//We execute the query and get the result back
			try(ResultSet rset = ps.executeQuery();){
			//Case we have no result with the given parameters
			if (!rset.next()) {
				av.addLienCT("Aucun lien d'avis de la comission de transparence renseigné");
				return av;
			}
			//Case we have results with the given parameters
			do {
				av.addLienCT(rset.getString(1));

			} while (rset.next());

		} } catch (SQLException e) {
			e.printStackTrace();
		}
		//Finally we return the result
		return av;
	}

	/**
	 * Synth�se des m�thodes pr�c�dentes, permet de r�cup�rer une liste d'avis ASMR grace � un code CIS
	 * et chaque avis poss�de une liste de lien CT
	 *
	 * @param research cis code corresponding
	 *
	 * @return the liste of avis object with link
	 */
	public ArrayList<Avis> getListAvisASMRCT(int codeCIS){
		ArrayList<Avis> list = new ArrayList<Avis>();

		list = getListAvis(codeCIS, "asmr");

		for (Avis av : list) {
			getLiensAvisComplet(av);
		}

		return list;
	}


	/**
	 * Synthèse des méthodes précédentes, permet de récupérer une liste d'avis SMR grace à un code CIS
	 * et chaque avis possède une liste de lien CT
	 *
	 * @param research cis code corresponding
	 *
	 * @return the liste of avis object with link
	 */
	public ArrayList<Avis> getListAvisSMRCT(int codeCIS){
		ArrayList<Avis> list = new ArrayList<Avis>();

		list = getListAvis(codeCIS,"smr");

		for (Avis av : list) {
			getLiensAvisComplet(av);
		}

		return list;
	}
}
