package medicaments.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import medicaments.DAO.client.GeneriqueDAO;
import medicaments.classes.Generique;

/**
 * Servlet implementation class GeneriqueListe
 * 
 * @author Ansou
 * @see Groupe Generique
 */
@WebServlet("/GeneriqueListe")
public class GeneriqueListe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneriqueListe() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession session = request.getSession();
		
		if (session == null || session.getAttribute("ID") == null) 
		{
			response.sendRedirect("./Index.jsp"); 
			return;
		}
		response.sendRedirect("./Generique.jsp");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		GeneriqueDAO geneDAO = new GeneriqueDAO();
		ArrayList<Generique> geneList = geneDAO.getListGeneriqueWithMedicament(request.getParameter("search"));
		
		/*  D�finition de l'attribut de l'object requ�ter qui sera ensuite r�cup�r� par la page "Generique.jsp"   */
		request.setAttribute("listeGenerique", geneList); 
		
		request.getRequestDispatcher("./Generique.jsp").forward(request, response); // Passer la requ�te et la r�ponse au .JSP
	}
	

}
