package medicaments.classes;
/**
 * Prescription class, this is used to modelized a prescription stored in the DB
 * @author Basile
 *
 */
public class Prescription {
	/** CIS Code, used to identify the medicament **/
	private int codeCIS;
	/**Condition of prescription or permit to supply**/
	private String conditions;
	
	public Prescription(int codeCIS, String conditions) {
		this.codeCIS = codeCIS;
		this.conditions = conditions;
	}
	
	public int getCodeCIS() {
		return codeCIS;
	}
	
	public String getConditions() {
		return conditions;
	}
	
	@Override
	public String toString() {
		return "Code CIS : "+codeCIS+", Conditions : "+conditions;
	}
}
