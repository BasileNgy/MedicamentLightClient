package medicaments.classes;

public class InfosImportante {

/**
 * InfosImportante class, this is used to modelized an InfosImportantes stored in the DB
 * 
 * 
 * This class use other classes, refer to the other links to understand their
 * purpose
 * 
 * @author Ansou
 */
	
	private int codeCis;
	private String dateDebut;
	private String dateFin;
	private String lien;
	
	public InfosImportante(int codeCis, String dateDebut, String dateFin, String lien) {
		super();
		this.codeCis = codeCis;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.lien = lien;
	}

	public int getCodeCis() {
		return codeCis;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public String getLien() {
		return lien;
	}
	
}
