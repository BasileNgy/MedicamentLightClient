package medicaments.DAO.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import medicaments.classes.Utilisateur;

/** 
 * 
 * La classe UtilisateurDAO permet de faire la liaison entre la BDD et le client leger
 *  
 * @author Raoph
 *
 */
public class UtilisateurDAO extends MotherClassDAO{

	public UtilisateurDAO () {
		super();
	}


	/**
	 * Methode permettant de recuperer toutes les informations d'un utilisateur a l'aide de son email
	 * 
	 * 
	 * @param login Email de l'utilisateur
	 * @return
	 */
	public Utilisateur getUtilisateur(String login) {

		Utilisateur retour = null;

		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM utilisateur u WHERE u.login = ?");){

			ps.setString(1, login);

			// Ex�cution de la requ�te
			try(ResultSet rs = ps.executeQuery();){
			if (rs.next()) {
				retour = new Utilisateur(
						rs.getInt("id"),
						rs.getString("login"),
						rs.getString("mot_de_passe"),
						rs.getString("statut"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("profession"));
			}

		}
	} catch (Exception e) {
			e.printStackTrace();
		}
		return retour;
	}

	/**
	 * Methode permettant de modifier la disponibilite d'un utilisateur a l'aide de son email
	 * 
	 * @param login Email de l'utilisateur
	 * @param disponibilite Disponibilite a lui assigner
	 * @return
	 */
	public Boolean setDisponibiliteUtilisateur(String login, String disponibilite) {

		Boolean retour = false;

		try {
			Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
			String query = "UPDATE utilisateur SET disponibilite = ? WHERE login = ?";
			PreparedStatement ps = conn.prepareStatement(query);
				
			ps.setString(1, disponibilite);
			ps.setString(2, login);

			// Ex�cution de la requ�te
			try{
				ps.execute();
				retour = true;
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retour;
	}
	

	/**
	 * Ajout d'un utilisateur dans la BDD
	 * @param utilisateur Utilisateur suivant le modele de la classe Utilisateur
	 */
	public void addUtilisateur(Utilisateur utilisateur) {

		// connexion � la base de donn�es
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
		PreparedStatement ps = conn.prepareStatement("INSERT INTO utilisateur "
				+ " (login, mot_de_passe, statut, nom, prenom, profession, disponibilite)"
				+ "VALUES (?,?,?,?,?,?,?)");){
			ps.setString(1,utilisateur.getLogin());
			ps.setString(2,utilisateur.getMot_de_passe());
			ps.setString(3,utilisateur.getStatut());
			ps.setString(4,utilisateur.getNom());
			ps.setString(5,utilisateur.getPrenom());
			ps.setString(6,utilisateur.getProfession());
			ps.setString(7,"offline");

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Recuperation de la liste de tous les utilisateurs etant en ligne
	 * @return La liste des utilisateurs
	 * 
	 */
	public ArrayList<Utilisateur> getListUtilisateurOnline() {

		ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();

		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASS);
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM utilisateur WHERE disponibilite = ?");){

			ps.setString(1, "online");

			// Ex�cution de la requ�te
			try(ResultSet rs = ps.executeQuery();){
			if (rs.next()) {
				do {
					list.add( new Utilisateur(
							rs.getInt("id"),
							rs.getString("login"),
							rs.getString("mot_de_passe"),
							rs.getString("statut"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("profession")));
			
				}while (rs.next());
			}
		}
	} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
