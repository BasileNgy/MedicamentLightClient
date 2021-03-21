package medicaments.classes;

import java.sql.Timestamp;


/**
 * 
 * @author Raphael VIGNON
 * 
 *  La classe UserConnection est le modele utilise pour instancier une tentative de connection d'un utilisateur a son compte sur le client leger
 *
 */
public class UserConnection {

	private int id;
	private int idUtilisateur;
	private String remoteIp;
	private Timestamp timestamp;
	
	
	
	/**
	 * 
	 * @param id L'ID de la connexion dans la BDD
	 * @param idUtilisateur	L'ID du compte utilisateur sur lequel la connection a ete effectuee
	 * @param remoteIp L'adresse IPV4 ayant effectue la connection
	 * @param timestamp Un timestamp representant la date et l'heure de la connexion
	 */
	public UserConnection(int id, int idUtilisateur, String remoteIp, Timestamp timestamp) {
		super();
		this.id = id;
		this.idUtilisateur = idUtilisateur;
		this.remoteIp = remoteIp;
		this.timestamp = timestamp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
}
