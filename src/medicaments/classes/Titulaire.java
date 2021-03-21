package medicaments.classes;
/**
 * Titulaire class, this is used to modelized a Titulaire stored in the DB
 * @author Basile
 *
 */
public class Titulaire{

	/** Name of the Titulaire **/
	private String nom;
	/** Number of medicament owned**/
	private int nombre;
	
	public Titulaire(String nom, int nombre) {
		super();
		this.nom = nom;
		this.nombre = nombre;
	}

	public String getNom() {
		return nom;
	}

	public int getNombre() {
		return nombre;
	}
	

}
