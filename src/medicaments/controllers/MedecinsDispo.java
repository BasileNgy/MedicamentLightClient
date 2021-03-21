package medicaments.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicaments.DAO.client.UtilisateurDAO;
import medicaments.classes.Utilisateur;

/**
 * Servlet implementation class MedecinsDispo
 * @author Basile
 */
@WebServlet("/MedecinsDispo")
public class MedecinsDispo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedecinsDispo() {
        super();
        userDAO = new UtilisateurDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * retrieve and send list of medecins available actually
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Utilisateur> listMedecin = new ArrayList<Utilisateur>();
		listMedecin = userDAO.getListUtilisateurOnline();
		request.setAttribute("ListMedecinDispo", listMedecin);
		request.getRequestDispatcher("/MedecinsDispo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
