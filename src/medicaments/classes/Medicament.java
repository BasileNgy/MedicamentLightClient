package medicaments.classes;

import java.util.ArrayList;

/**
 * Medicament class, this is used to modelized a medicament stored in the DB
 * 
 * <p>
 * This class use other classes, refer to the other links to understand their
 * purpose
 * 
 * @author Loic Basile
 * @see Avis
 * @see Presentation
 */
public class Medicament {

	/** ID In the DB **/
	private int id;
	/** CIS Code, used to identify the medicament **/
	private int codeCis;
	/** Name of the medicament **/
	private String denomination;
	/** Pharmaceutic form **/
	private String formePharmaceutique;
	/** How to take the medicament **/
	private String voieAdministrative;
	/** Official statut of the medicament **/
	private String statutAdministratifAMM;
	/** Procedure of the AMM **/
	private String typeProcedureAMM;
	/** How it is sold **/
	private String etatCommercialisation;
	/** Date it has been approoved **/
	private String dateAMM;
	/** BDM Statut **/
	private String statutBdm;
	/** Autorisation in europe **/
	private String numeroAutorisationEuro;
	/** Brand that sells it **/
	private String titulaire;
	/** The things to care about when you take it **/
	private String surveillanceRenforcee;
	/** Presentation linked to the medicament **/
	private ArrayList<Presentation> lesPresentations;
	/** Avis linked to the medicament **/
	private Avis avisSMR;
	/** Avis ASMR linked to the medicament **/
	private Avis avisASMR;

	/**
	 * Constructor to create the object, complete the fields with the getters and
	 * setters
	 */
	public Medicament(int codeCis, String denomination, String formePharmaceutique, String voieAdministrative,
			String statutAdministratifAMM, String typeProcedureAMM, String etatCommercialisation, String dateAMM,
			String statutBdm, String numeroAutorisationEuro, String titulaire, String surveillanceRenforcee) 
	{ 
		this.codeCis = codeCis;
		this.denomination = denomination;
		this.formePharmaceutique = formePharmaceutique;
		this.voieAdministrative = voieAdministrative;
		this.statutAdministratifAMM = statutAdministratifAMM;
		this.typeProcedureAMM = typeProcedureAMM;
		this.etatCommercialisation = etatCommercialisation;
		this.dateAMM = dateAMM;
		this.statutBdm = statutBdm;
		this.numeroAutorisationEuro = numeroAutorisationEuro;
		this.titulaire = titulaire;
		this.surveillanceRenforcee = surveillanceRenforcee;
		lesPresentations = new ArrayList<Presentation>();
	}

	public Medicament() {
		
	}
	/** AUTO GENERATED GETTERS AND SETTERS **/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodeCis() {
		return codeCis;
	}

	public void setCodeCis(int codeCis) {
		this.codeCis = codeCis;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getFormePharmaceutique() {
		return formePharmaceutique;
	}

	public void setFormePharmaceutique(String formePharmaceutique) {
		this.formePharmaceutique = formePharmaceutique;
	}

	public String getVoieAdministrative() {
		return voieAdministrative;
	}

	public void setVoieAdministrative(String voieAdministrative) {
		this.voieAdministrative = voieAdministrative;
	}

	public String getStatutAdministratifAMM() {
		return statutAdministratifAMM;
	}

	public void setStatutAdministratifAMM(String statutAdministratifAMM) {
		this.statutAdministratifAMM = statutAdministratifAMM;
	}

	public String getTypeProcedureAMM() {
		return typeProcedureAMM;
	}

	public void setTypeProcedureAMM(String typeProcedureAMM) {
		this.typeProcedureAMM = typeProcedureAMM;
	}

	public String getEtatCommercialisation() {
		return etatCommercialisation;
	}

	public void setEtatCommercialisation(String etatCommercialisation) {
		this.etatCommercialisation = etatCommercialisation;
	}

	public String getDateAMM() {
		return dateAMM;
	}

	public void setDateAMM(String dateAMM) {
		this.dateAMM = dateAMM;
	}

	public String getStatutBdm() {
		return statutBdm;
	}

	public void setStatutBdm(String statutBdm) {
		this.statutBdm = statutBdm;
	}

	public String getNumeroAutorisationEuro() {
		return numeroAutorisationEuro;
	}

	public void setNumeroAutorisationEuro(String numeroAutorisationEuro) {
		this.numeroAutorisationEuro = numeroAutorisationEuro;
	}

	public String getTitulaire() {
		return titulaire;
	}

	public void setTitulaire(String titulaire) {
		this.titulaire = titulaire;
	}

	public String getSurveillanceRenforcee() {
		return surveillanceRenforcee;
	}

	public void setSurveillanceRenforcee(String surveillanceRenforcee) {
		this.surveillanceRenforcee = surveillanceRenforcee;
	}
	

	public ArrayList<Presentation> getLesPresentations() {
		return lesPresentations;
	}

	public void setLesPresentations(ArrayList<Presentation> lesPresentations) {
		this.lesPresentations = lesPresentations;
	}

	public Avis getAvisSMR() {
		return avisSMR;
	}

	public void setAvisSMR(Avis avisSMR) {
		this.avisSMR = avisSMR;
	}

	public Avis getAvisASMR() {
		return avisASMR;
	}

	public void setAvisASMR(Avis avisASMR) {
		this.avisASMR = avisASMR;
	}

	@Override
	public String toString() {
		return "Medicament [id=" + id + ", codeCis=" + codeCis + ", denomination=" + denomination
				+ ", formePharmaceutique=" + formePharmaceutique + ", voieAdministrative=" + voieAdministrative
				+ ", statutAdministratifAMM=" + statutAdministratifAMM + ", typeProcedureAMM=" + typeProcedureAMM
				+ ", etatCommercialisation=" + etatCommercialisation + ", dateAMM=" + dateAMM + ", statutBdm="
				+ statutBdm + ", numeroAutorisationEuro=" + numeroAutorisationEuro + ", titulaire=" + titulaire
				+ ", surveillanceRenforcee=" + surveillanceRenforcee + ", lesPresentations=" + lesPresentations
				+ ", avisSMR=" + avisSMR + ", avisASMR=" + avisASMR + "]";
	}

	/** END OF AUTOGENERATION **/
	
	public void addPresentation(Presentation presentation) {
		this.lesPresentations.add(presentation);
	}
	
	
		
}
