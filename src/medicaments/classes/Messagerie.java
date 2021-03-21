package medicaments.classes;

import java.sql.Timestamp;

/**
 * The Messagerie class, this class models the messageries stored in the BDD 
 * this class is linked to another class
 * 
 * @author ansou
 *
 */
public class Messagerie 
{
	private int id;
	private String mail;
	private String objet;
	private String message;
	private Timestamp timestamp;
	
	/**
	 * Class Constructor
	 * 
	 * @param id
	 * @param mail
	 * @param objet
	 * @param message
	 * @param timestamp
	 */
	public Messagerie(int id, String mail, String objet, String message, Timestamp timestamp)
	{
		super();
		this.id = id;
		this.mail = mail;
		this.objet = objet;
		this.message = message;
		this.timestamp = timestamp;
	}

	/**
	 * Getters and Setters
	 */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getObjet() {
		return objet;
	}


	public void setObjet(String objet) {
		this.objet = objet;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
}

