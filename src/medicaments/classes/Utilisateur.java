package medicaments.classes;
/**
 * 
 * @author Raphael VIGNON
 *
 * La classe Utilisateur est le modele utilise pour instancier un utilisateur etant inscrit sur le site
 */
public class Utilisateur {

	private int id;
	private String login;
	private String mot_de_passe; 
	private String statut;
	private String nom;
	private String prenom;
	private String profession;

	
	/**
	 * 
	 * @param id L'ID de l'utlisateur dans la BDD
	 * @param login L'email de l'utilisateur	
	 * @param mot_de_passe Le MDP de l'utilisateur ( encodé en SHA256 )
	 * @param statut Le statut : enAttente, professionnel ou admin
	 * @param nom Le nom de l'utilisateur
	 * @param prenom Le prenom de l'utilisateur
	 * @param profession La profession : medecin, pharmacien, laboratoire pharmaceutique ou etudiant en medecine/pharmacie
	 */
	public Utilisateur(int id, String login, String mot_de_passe, String statut, String nom, String prenom,
			String profession) {
		super();
		this.id = id;
		this.login = login;
		this.mot_de_passe = mot_de_passe;
		this.statut = statut;
		this.nom = nom;
		this.prenom = prenom;
		this.profession = profession;
	}




	public String getNom() {
		return nom;
		
		
		
	}




	public void setNom(String nom) {
		this.nom = nom;
	}




	public String getPrenom() {
		return prenom;
	}




	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}




	public String getProfession() {
		return profession;
	}




	public void setProfession(String profession) {
		this.profession = profession;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getLogin() {
		return login;
	}




	public void setLogin(String login) {
		this.login = login;
	}




	public String getMot_de_passe() {
		return mot_de_passe;
	}




	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}




	public String getStatut() {
		return statut;
	}




	public void setStatut(String statut) {
		this.statut = statut;
	}




	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", mot_de_passe=" + mot_de_passe + ", statut=" + statut
				+ "]";
	}
	
	
	
	
	
	
	
	
}
