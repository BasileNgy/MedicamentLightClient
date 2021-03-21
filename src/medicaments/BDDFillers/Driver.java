package medicaments.BDDFillers;
import java.util.ArrayList;

import medicaments.DAO.client.MedicamentDAO;
import medicaments.DAO.client.UtilisateurDAO;
import medicaments.classes.Utilisateur;

public class Driver {

	public static void main(String[] args) {
		


		UtilisateurDAO userdao = new UtilisateurDAO();
		ArrayList<Utilisateur> list_user = new ArrayList<Utilisateur>();
		
		//userdao.setDisponibiliteUtilisateur("rty@rty", "online");

		list_user = userdao.getListUtilisateurOnline();
		for(Utilisateur u : list_user)
			System.out.println(u.getNom());
	}

}