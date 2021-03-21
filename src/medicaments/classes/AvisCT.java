package medicaments.classes;
/**
 * AvisCT class, this is used to modelized a avisCT stored in the DB
 * @author Basile
 *
 */
public class AvisCT {

	/** Code du dossier HAS**/
	private String codeDossier;
	/** lien vers avis complet**/
	private String lien;
	
	/**
	 * Create avisCT with attirbutes
	 * @param codeDossier
	 * @param lien
	 */
	public AvisCT(String codeDossier, String lien) {
		this.codeDossier = codeDossier;
		this.lien = lien;
	}
	
	public String getCodeDossier() {
		return codeDossier;
	}
	
	public String getLien() {
		return lien;
	}
}
