package medicaments.classes;

/**
 * Presentation class linked to the medicament one
 * The CIS code field is in fact the FK of a medicament
 * 
 * @author Loic
 * @see Medicament
 */
public class Presentation {
	
	/** ID in the DB **/
	private int id;
	/** CIS Code of the medicament linked to the presentation **/
	private int codeCIS;
	/** CIP code **/
	private String codeCIP7;
	/** Libelle of the presentation **/
	private String libelle;
	/** Statut administratif**/
	private String statutAdministratif;
	/** Etat de commercialisation du m�dicament **/
	private String etatCommercialisation;
	/** Date since it has been commercialized **/
	private String dateDeclarationCommercialisation;
	/** CIP 13 code **/
	private String codeCIP13;
	/** Agr�ment de collectivit�s **/
	private String agrementCollectivites;
	/** Reimbursement rate **/
	private String tauxRemboursement;
	/** Price of the medicament **/
	private String prix;
	/** Ways to get reimbursed **/
	private String indicationRemboursement;
	
	/**
	 * Constructor of the class, must use the setters to complete the fields
	 */
	public Presentation() {}

	/**
	 * Constructor with attributes
	 */
	public Presentation(int codeCIS, String codeCIP7, String libelle, String statutAdministratif, 
			String etatCommercialisation, String codeCIP13,String dateDeclarationCommercialisation,
			String agrementCollectivites, String tauxRemboursement, String prix,String indicationRemboursement) {
		
		this.codeCIS = codeCIS;
		this.codeCIP7 = codeCIP7;
		this.libelle = libelle;
		this.statutAdministratif = statutAdministratif;
		this.etatCommercialisation = etatCommercialisation;
		this.codeCIP13 = codeCIP13;
		this.dateDeclarationCommercialisation = dateDeclarationCommercialisation;
		this.agrementCollectivites = agrementCollectivites;
		this.tauxRemboursement = tauxRemboursement;
		this.prix = prix;
		this.indicationRemboursement = indicationRemboursement;
	}
	
	
	/** BEGINING OF AUTOGENERATION **/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodeCIS() {
		return codeCIS;
	}

	public void setCodeCIS(int codeCIS) {
		this.codeCIS = codeCIS;
	}

	public String getCodeCIP7() {
		return codeCIP7;
	}

	public void setCodeCIP7(String codeCIP7) {
		this.codeCIP7 = codeCIP7;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getStatutAdministratif() {
		return statutAdministratif;
	}

	public void setStatutAdministratif(String statutAdministratif) {
		this.statutAdministratif = statutAdministratif;
	}

	public String getEtatCommercialisation() {
		return etatCommercialisation;
	}

	public void setEtatCommercialisation(String etatCommercialisation) {
		this.etatCommercialisation = etatCommercialisation;
	}

	public String getDateDeclarationCommercialisation() {
		return dateDeclarationCommercialisation;
	}

	public void setDateDeclarationCommercialisation(String dateDeclarationCommercialisation) {
		this.dateDeclarationCommercialisation = dateDeclarationCommercialisation;
	}

	public String getCodeCIP13() {
		return codeCIP13;
	}

	public void setCodeCIP13(String codeCIP13) {
		this.codeCIP13 = codeCIP13;
	}

	public String getAgrementCollectivites() {
		return agrementCollectivites;
	}

	public void setAgrementCollectivites(String agrementCollectivites) {
		this.agrementCollectivites = agrementCollectivites;
	}

	public String getTauxRemboursement() {
		return tauxRemboursement;
	}

	public void setTauxRemboursement(String tauxRemboursement) {
		this.tauxRemboursement = tauxRemboursement;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getIndicationRemboursement() {
		return indicationRemboursement;
	}

	public void setIndicationRemboursement(String indicationRemboursement) {
		this.indicationRemboursement = indicationRemboursement;
	}

	/** END OF AUTOGENERATION **/
	
	@Override
	public String toString() {
		return "Presentation [id=" + id + ", codeCIS=" + codeCIS + ", codeCIP7=" + codeCIP7 + ", libelle=" + libelle
				+ ", statutAdministratif=" + statutAdministratif + ", etatCommercialisation=" + etatCommercialisation
				+ ", dateDeclarationCommercialisation=" + dateDeclarationCommercialisation + ", codeCIP13=" + codeCIP13
				+ ", agrementCollectivites=" + agrementCollectivites + ", tauxRemboursement=" + tauxRemboursement
				+ ", prix=" + prix + ", indicationRemboursement=" + indicationRemboursement + "]";
	}
}
