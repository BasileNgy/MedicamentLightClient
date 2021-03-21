package medicaments.BDDFillers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import medicaments.DAO.client.MotherClassDAO;


/**
 * Classe DAO permettant de remplir la base de données avec les données fourni en fichier csv
 * @author Basile
 *
 */
public class DAOFillBdd extends MotherClassDAO {

	private Connection conn;
	/** Prepared statement used in the DAO **/
	private PreparedStatement ps;

	public DAOFillBdd() {

	}

	public ArrayList<String> getCodeCIS() {
		ArrayList<String> liste = new ArrayList<String>();
		try {
			connect();
			Statement statement = conn.createStatement();
			String query = "SELECT code_CIS FROM medicament";
			java.sql.ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				liste.add(resultSet.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return liste;
	}

	/**
	 * Lit le fichier des donn�es de CIS_bdpm CSV et retourne un tableau de donn�es
	 * � ins�rer dans la bdd
	 * 
	 */
	public void lectureCodeCIS() {
		// Initialisation
		BufferedReader bufferedreader = null;
		FileReader filereader = null;
		String name = "./WebContent/donnees/CIS_bdpm.csv";
		try {
			filereader = new FileReader(name);
			bufferedreader = new BufferedReader(filereader);
			int cpt = 0;
			String strCurrentLine;

			// Lecture du fichier csv jusqu'� qu'il n'y ait plus de ligne
			while ((strCurrentLine = bufferedreader.readLine()) != null) {

				// Parse la ligne avec comme s�parateur ";" pour mettre chaque colonne dans des
				// String
				String data[] = strCurrentLine.split(";");
				for (int i = 0; i < data.length; i++) {
					if (data[i].isEmpty())
						data[i] = "non renseign�";
				}
				insertTableMedicament(data);
				cpt++;
				System.out.println(cpt + " / 15 112");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de lire le fichier");
		} finally {
			try {
				if (bufferedreader != null)
					bufferedreader.close();
				if (filereader != null)
					filereader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lit le fichier des donn�es de CIS_CIP_bdpm CSV et envoie une liste de donn�es
	 * � ins�rer dans la bdd
	 */
	public void lecturePresentation() {
		// Initialisation

		BufferedReader bufferedreader = null;
		FileReader filereader = null;
		String name = "./WebContent/donnees/CIS_CIP_bdpm.csv";
		try {
			filereader = new FileReader(name);
			bufferedreader = new BufferedReader(filereader);
			int cpt = 0;
			String strCurrentLine;

			// Lecture du fichier csv jusqu'� qu'il n'y ait plus de ligne
			while ((strCurrentLine = bufferedreader.readLine()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				// Parse la ligne avec comme s�parateur ";" pour mettre chaque colonne dans des
				// String
				for (int i = 0; i < 13; i++) {
					list.add("non renseign�");
				}
				String data[] = strCurrentLine.split(";");

				for (int j = 0; j < data.length; j++) {
					list.set(j, data[j]);
				}

				insertTablePresentation(list);

				cpt++;
				System.out.println(cpt + " / 20 567");

			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de lire le fichier");
		} finally {
			try {
				if (bufferedreader != null)
					bufferedreader.close();
				if (filereader != null)
					filereader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lit le fichier des donn�es de CIS_HAS_SMR_bdpm CSV et envoie un tableau de
	 * donn�es � ins�rer dans la bdd
	 */
	public void lectureAvisSMR() {
		// Initialisation
		BufferedReader bufferedreader = null;
		FileReader filereader = null;
		String name = "./WebContent/donnees/CIS_HAS_SMR_bdpm.csv";
		try {
			filereader = new FileReader(name);
			bufferedreader = new BufferedReader(filereader);
			int cpt = 0;
			String strCurrentLine;

			// Lecture du fichier csv jusqu'� qu'il n'y ait plus de ligne
			while ((strCurrentLine = bufferedreader.readLine()) != null) {

				// Parse la ligne avec comme s�parateur ";" pour mettre chaque colonne dans des
				// String
				String data[] = strCurrentLine.split(";");
				data[3] = formatDate(data[3]);
				insertTableAvisSMR(data);
				cpt++;
				System.out.println(cpt + " / 11 405");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de lire le fichier");
		} finally {
			try {
				if (bufferedreader != null)
					bufferedreader.close();
				if (filereader != null)
					filereader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lit le fichier des donn�es de CIS_HAS_ASMR_bdpm CSV et envoie un tableaud de
	 * donn�es � ins�rer dans la bdd
	 */
	public void lectureAvisASMR() {
		// Initialisation
		BufferedReader bufferedreader = null;
		FileReader filereader = null;
		String name = "./WebContent/donnees/CIS_HAS_ASMR_bdpm.csv";
		try {
			filereader = new FileReader(name);
			bufferedreader = new BufferedReader(filereader);
			int cpt = 0;
			String strCurrentLine;

			// Lecture du fichier csv jusqu'� qu'il n'y ait plus de ligne
			while ((strCurrentLine = bufferedreader.readLine()) != null) {

				// Parse la ligne avec comme s�parateur ";" pour mettre chaque colonne dans des
				// String
				String data[] = strCurrentLine.split(";");
				data[3] = formatDate(data[3]);
				insertTableAvisASMR(data);
				cpt++;
				System.out.println(cpt + " / 7 164");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de lire le fichier");
		} finally {
			try {
				if (bufferedreader != null)
					bufferedreader.close();
				if (filereader != null)
					filereader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lit le fichier des donn�es de CIS_GENER_bdpm CSV et envoie un tableau de
	 * donn�es � ins�rer dans la bdd
	 */
	public void lectureGenerique() {
		// Initialisation
		BufferedReader bufferedreader = null;
		FileReader filereader = null;
		String name = "./WebContent/donnees/CIS_GENER_bdpm.csv";
		try {
			filereader = new FileReader(name);
			bufferedreader = new BufferedReader(filereader);
			int cpt = 0;
			String strCurrentLine;

			// Lecture du fichier csv jusqu'� qu'il n'y ait plus de ligne
			while ((strCurrentLine = bufferedreader.readLine()) != null) {

				// Parse la ligne avec comme s�parateur ";" pour mettre chaque colonne dans des
				// String
				String data[] = strCurrentLine.split(";");
				insertTableGenerique(data);
				cpt++;
				System.out.println(cpt + " / 9 604");
				// if (cpt >15)break;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de lire le fichier");
		} finally {
			try {
				if (bufferedreader != null)
					bufferedreader.close();
				if (filereader != null)
					filereader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lit le fichier des donn�es de CIS_CPD_bdpm CSV et envoie un tableau de
	 * donn�es � ins�rer dans la bdd
	 */
	public void lecturePrescription() {
		// Initialisation
		BufferedReader bufferedreader = null;
		FileReader filereader = null;
		String name = "./WebContent/donnees/CIS_CPD_bdpm.csv";
		try {
			filereader = new FileReader(name);
			bufferedreader = new BufferedReader(filereader);
			int cpt = 0;
			String strCurrentLine;

			// Lecture du fichier csv jusqu'� qu'il n'y ait plus de ligne
			while ((strCurrentLine = bufferedreader.readLine()) != null) {

				// Parse la ligne avec comme s�parateur ";" pour mettre chaque colonne dans des
				// String
				String data[] = strCurrentLine.split(";");
				;
				insertTablePrescription(data);
				cpt++;
				System.out.println(cpt + " / 22 220");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de lire le fichier");
		} finally {
			try {
				if (bufferedreader != null)
					bufferedreader.close();
				if (filereader != null)
					filereader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lit le fichier des donn�es de CIS_COMPO_bdpm CSV et envoie un tableau de
	 * donn�es � ins�rer dans la bdd
	 */
	public void lectureComposition() {
		// Initialisation
		BufferedReader bufferedreader = null;
		FileReader filereader = null;
		String name = "./WebContent/donnees/CIS_COMPO_bdpm.csv";
		try {
			filereader = new FileReader(name);
			bufferedreader = new BufferedReader(filereader);
			int cpt = 0;
			String strCurrentLine;

			// Lecture du fichier csv jusqu'� qu'il n'y ait plus de ligne
			while ((strCurrentLine = bufferedreader.readLine()) != null) {

				// Parse la ligne avec comme s�parateur ";" pour mettre chaque colonne dans des
				// String
				String data[] = strCurrentLine.split(";");
				insertTableComposition(data);
				cpt++;
				System.out.println(cpt + " / 29 717");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de lire le fichier");
		} finally {
			try {
				if (bufferedreader != null)
					bufferedreader.close();
				if (filereader != null)
					filereader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lit le fichier des donn�es de CIS_InfoImportantes_20200427115849_bdpm CSV et
	 * envoie un tableau de donn�es � ins�rer dans la bdd
	 */
	public void lectureInfosImportante() {
		// Initialisation
		BufferedReader bufferedreader = null;
		FileReader filereader = null;
		String name = "./WebContent/donnees/CIS_InfoImportantes_20200427115849_bdpm.csv";
		try {
			filereader = new FileReader(name);
			bufferedreader = new BufferedReader(filereader);
			int cpt = 0;
			String strCurrentLine;

			// Lecture du fichier csv jusqu'� qu'il n'y ait plus de ligne
			while ((strCurrentLine = bufferedreader.readLine()) != null) {

				// Parse la ligne avec comme s�parateur ";" pour mettre chaque colonne dans des
				// String
				String data[] = strCurrentLine.split(";");
				insertTableInfosImportante(data);
				cpt++;
				System.out.println(cpt + " / 24 611");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de lire le fichier");
		} finally {
			try {
				if (bufferedreader != null)
					bufferedreader.close();
				if (filereader != null)
					filereader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lit le fichier des donn�es de CIS_CPD_bdpm CSV et envoie un tableau de
	 * donn�es � ins�rer dans la bdd
	 */
	public void lectureAvisCT() {
		// Initialisation
		BufferedReader bufferedreader = null;
		FileReader filereader = null;
		String name = "./WebContent/donnees/HAS_LiensPageCT_bdpm.csv";
		try {
			filereader = new FileReader(name);
			bufferedreader = new BufferedReader(filereader);
			int cpt = 0;
			String strCurrentLine;

			// Lecture du fichier csv jusqu'� qu'il n'y ait plus de ligne
			while ((strCurrentLine = bufferedreader.readLine()) != null) {

				// Parse la ligne avec comme s�parateur ";" pour mettre chaque colonne dans des
				// String
				String data[] = strCurrentLine.split(";");

				insertTableAvisCT(data);
				cpt++;
				System.out.println(cpt + " / 8 352");
				// if (cpt>15)break;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de lire le fichier");
		} finally {
			try {
				if (bufferedreader != null)
					bufferedreader.close();
				if (filereader != null)
					filereader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Ins�re un medicament dans la base de donn�es
	 * 
	 * @param tableau - valeurs venant du fichier csv
	 */
	public void insertTableMedicament(String[] tab) {

		try {
			// Ins�re les valeurs de l'object Maire pass� en param�tre dans la base de
			// donn�es
			connect();
			String query = "INSERT INTO medicament (code_CIS, denomination, forme_pharmaceutique, voies_administration, "
					+ "statut_administratif_AMM, type_procedure_AMM, etat_commercialisation, date_amm, statutBdm, "
					+ "Numero_autorisation_euro, Titulaire, Surveillance_renforcee) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(tab[0]));

			for (int i = 1; i < 12; i++)
				preparedStmt.setString(i + 1, tab[i]);

			preparedStmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection database error");
		} finally {
			close();
		}
	}

	/**
	 * Ins�re une presentation dans la base de donn�es
	 * 
	 * @param tableau - valeurs venant du fichier csv
	 */
	public void insertTablePresentation(ArrayList<String> list) {

		try {
			// Ins�re les valeurs de l'object Maire pass� en param�tre dans la base de
			// donn�es
			connect();
			String query = "INSERT INTO presentation (code_CIS, code_CIP7, libelle, statut_administratif, "
					+ "etat_commercialisation, date_declaration_commercialisation, code_CIP13, agrement_collectivites, "
					+ "taux_remboursement, prix, indication_remboursement) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(list.get(0)));
			for (int i = 1; i < 10; i++) {
				preparedStmt.setString(i + 1, list.get(i));
			}
			preparedStmt.setString(11, list.get(12));

			preparedStmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection database error");
		} finally {
			close();
		}
	}

	/**
	 * Ins�re un avis SMR dans la base de donn�es
	 * 
	 * @param tableau - valeurs venant du fichier csv
	 */
	public void insertTableAvisSMR(String[] tab) {

		try {
			// Ins�re les valeurs de l'object Maire pass� en param�tre dans la base de
			// donn�es
			connect();
			String query = "INSERT INTO avis_smr (code_CIS, code_dossier_HAS, motif_evaluation,"
					+ " date, valeur_SMR, libelle_SMR) " + "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(tab[0]));
			for (int i = 1; i < 6; i++)
				preparedStmt.setString(i + 1, tab[i]);

			preparedStmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection database error");
		} finally {
			close();
		}
	}

	/**
	 * Ins�re un avis ASMR dans la base de donn�es
	 * 
	 * @param tableau - valeurs venant du fichier csv
	 */
	public void insertTableAvisASMR(String[] tab) {

		try {
			// Ins�re les valeurs de l'object Maire pass� en param�tre dans la base de
			// donn�es
			connect();
			String query = "INSERT INTO avis_asmr (code_CIS, code_dossier_HAS, motif_evaluation,"
					+ " date, valeur_ASMR, libelle_ASMR) " + "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(tab[0]));
			for (int i = 1; i < 6; i++)
				preparedStmt.setString(i + 1, tab[i]);

			preparedStmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection database error");
		} finally {
			close();
		}
	}

	/**
	 * Ins�re un groupe g�n�rique dans la base de donn�es
	 * 
	 * @param tableau - valeurs venant du fichier csv
	 */
	public void insertTablePrescription(String[] tab) {

		try {
			// Ins�re les valeurs de l'object Maire pass� en param�tre dans la base de
			// donn�es
			connect();
			String query = "INSERT INTO prescription (code_CIS, conditions) " + "VALUES (?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(tab[0]));
			preparedStmt.setString(2, tab[1]);

			preparedStmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection database error");
		} finally {
			close();
		}
	}

	/**
	 * Ins�re une prescription dans la base de donn�es
	 * 
	 * @param tableau - valeurs venant du fichier csv
	 */
	public void insertTableGenerique(String[] tab) {

		try {
			// Ins�re les valeurs de l'object Maire pass� en param�tre dans la base de
			// donn�es
			connect();
			String query = "INSERT INTO generique (id_groupe, libelle_groupe, code_CIS," + " type, numero_tri) "
					+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(tab[0]));
			preparedStmt.setString(2, tab[1]);
			preparedStmt.setInt(3, Integer.parseInt(tab[2]));
			preparedStmt.setInt(4, Integer.parseInt(tab[3]));
			preparedStmt.setInt(5, Integer.parseInt(tab[4]));

			preparedStmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection database error");
		} finally {
			close();
		}
	}

	/**
	 * Ins�re un avis de la comission de transparence dans la base de donn�es
	 * 
	 * @param tableau - valeurs venant du fichier csv
	 */
	public void insertTableAvisCT(String[] tab) {

		try {
			// Ins�re les valeurs de l'object Maire pass� en param�tre dans la base de
			// donn�es
			connect();
			String query = "INSERT INTO avis_ct (code_dossier_HAS, lien) " + "VALUES (?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, tab[0]);
			preparedStmt.setString(2, tab[1]);

			preparedStmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection database error");
		} finally {
			close();
		}
	}

	/**
	 * Ins�re un avis SMR dans la base de donn�es
	 * 
	 * @param tableau - valeurs venant du fichier csv
	 */
	public void insertTableComposition(String[] tab) {

		try {
			// Ins�re les valeurs de l'object Maire pass� en param�tre dans la base de
			// donn�es
			connect();
			String query = "INSERT INTO composition (code_CIS, designation_element, code_substance,"
					+ " denomination_substance, dosage_substance, reference_dosage, nature, numero_SAFT) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(tab[0]));
			for (int i = 1; i < 8; i++) {
				if (tab[i].isEmpty())
					tab[i] = "non renseign�e";
				preparedStmt.setString(i + 1, tab[i]);
			}

			preparedStmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection database error");
		} finally {
			close();
		}
	}

	/**
	 * Ins�re un avis de la comission de transparence dans la base de donn�es
	 * 
	 * @param tableau - valeurs venant du fichier csv
	 */
	public void insertTableInfosImportante(String[] tab) {

		try {
			// Ins�re les valeurs de l'object Maire pass� en param�tre dans la base de
			// donn�es
			connect();
			String query = "INSERT INTO infos_importantes (code_CIS, date_debut, date_fin, lien) "
					+ "VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(tab[0]));
			for (int i = 1; i < 4; i++) {
				if (tab[i].isEmpty())
					tab[i] = "non renseign�e";
				preparedStmt.setString(i + 1, tab[i]);
			}

			preparedStmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection database error");
		} finally {
			close();
		}
	}

	public void insertTableTest() {

		try {
			// Ins�re les valeurs de l'object Maire pass� en param�tre dans la base de
			// donn�es
			connect();
			String query = "INSERT INTO medicament (code_CIS, denomination, forme_pharmaceutique, voies_administration, statut_administratif_AMM) "
					+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, 12345678);
			preparedStmt.setString(2, "test");
			preparedStmt.setString(3, "test");
			preparedStmt.setString(4, "test");
			preparedStmt.setString(5, "test");

			preparedStmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection database error");
		} finally {
			close();
		}
	}

	public String formatDate(String date) {
		String annee = date.substring(0, 4);
		String mois = date.substring(4, 6);
		String jour = date.substring(6);

		return jour + "/" + mois + "/" + annee;
	}

	/**
	 * Ouvre la connection � la base de donn�es
	 */
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, LOGIN, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ferme la connection � la base de donn�es
	 */
	public void close() {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
}
