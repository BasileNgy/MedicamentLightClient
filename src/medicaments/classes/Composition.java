package medicaments.classes;

public class Composition {
	/**
	 * La classe Composition, cette classe modélise les compositions stockées dans la BDD
	 * 
 	 * Cette classe est lié à une autre classe
	 * 
	 * @author ansou
	 * @see La composition des médicaments
	 */
	
	private int codeCIS;
	private String designationElement;
	private String codeSubstance;
	private String denominationSubstance;
	private String dosageSubstance;
	private String referenceDosage;
	private String nature;
	private String numeroSAFT;
	
	// Constructeur
	public Composition(int codeCIS, String designationElement, String codeSubstance, String denominationSubstance,
						String dosageSubstance, String referenceDosage, String nature,String numeroSAFT) 
	{
		super();
		
		this.codeCIS = codeCIS;
		this.designationElement = designationElement;
		this.codeSubstance = codeSubstance;
		this.denominationSubstance = denominationSubstance;
		this.dosageSubstance = dosageSubstance;
		this.referenceDosage = referenceDosage;
		this.nature = nature;
		this.numeroSAFT = numeroSAFT;
	}
	
	// Déclaration Getteurs
	public int getCodeCIS() {
		return codeCIS;
	}

	public String getDesignationElement() {
		return designationElement;
	}

	public String getCodeSubstance() {
		return codeSubstance;
	}

	public String getDenominationSubstance() {
		return denominationSubstance;
	}

	public String getDosageSubstance() {
		return dosageSubstance;
	}

	public String getReferenceDosage() {
		return referenceDosage;
	}

	public String getNature() {
		return nature;
	}

	public String getNumeroSAFT() {
		return numeroSAFT;
	}
	
}
