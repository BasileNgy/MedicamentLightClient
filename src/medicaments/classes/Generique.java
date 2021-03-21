package medicaments.classes;
import java.util.ArrayList;

/**
 * Generique class, this is used to modelized a generic stored in the DB
 * 
 * @author Basile
 * @see Liste g�nerique
 */

public class Generique {

	/** Identifiant du groupe générique **/
	private int idGroupe;
	/** Libellé du groupe générique **/
	private String libelleGroupe;
	/** Code CIS **/
	private int codeCIS;
	/**Type de générique, avec les valeurs suivantes :
	* 0 :  princeps 
	* 1 :  générique 
	* 2 :  génériques par complémentarité posologique 
	* 4 :  générique substituable  **/
	private int type;
	/** Numéro permettant de trier les éléments d'un groupe **/
	private int numTri;
	
	private ArrayList<Medicament> list;
	
	public Generique(int idGroupe, String libelleGroupe, int codeCIS, int type, int numTri) {
		this.list = new ArrayList<Medicament>();
		this.idGroupe = idGroupe;
		this.libelleGroupe = libelleGroupe;
		this.codeCIS = codeCIS;
		this.type = type;
		this.numTri = numTri;
	}
	
	
	public Generique() {
	}


	public void addMedicament(Medicament m) {
		list.add(m);
	}
	
	public ArrayList<Medicament> getListMedicament(){
		return list;
	}
	
	public int getIDGroupe() {
		return idGroupe;
	}
	
	public String getLibelleGroupe() {
		return libelleGroupe;
	}
	
	public int getCodeCIS() {
		return codeCIS;
	}
	
	public int getType() {
		return type;
	}
	
	
	
	public int getIdGroupe() {
		return idGroupe;
	}


	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}


	public int getNumTri() {
		return numTri;
	}


	public void setNumTri(int numTri) {
		this.numTri = numTri;
	}


	public ArrayList<Medicament> getList() {
		return list;
	}


	public void setList(ArrayList<Medicament> list) {
		this.list = list;
	}


	public void setLibelleGroupe(String libelleGroupe) {
		this.libelleGroupe = libelleGroupe;
	}


	public void setCodeCIS(int codeCIS) {
		this.codeCIS = codeCIS;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int numTri() {
		return numTri;
	}
	
	@Override
	public String toString() {
		return "IDGroupe : "+idGroupe +", LibelleGroupe : " + libelleGroupe+", Code CIS : "
	+ codeCIS+", Type : " + type+", numTri :" + numTri;
	}
}
