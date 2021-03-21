package medicaments.controllers;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicaments.DAO.client.*;
import medicaments.classes.*;

/**
 * Servlet implementation class Presmedoc
 */
@WebServlet("/ListeMedicament")
public class ListeMedicament extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MedicamentDAO MDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeMedicament() {
        super();
       // PDAO=new PresentationDAO();
        MDAO=new MedicamentDAO();
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("/ListeMedicament.jsp").forward(request, response);
		
		
		
		
		ArrayList<Medicament> ListeDeMedicamments=(ArrayList<Medicament>)request.getAttribute("listeTousMedicaments");// prendre la liste des medoc
		if ((ListeDeMedicamments)==null) {
			
		
				ListeDeMedicamments = MDAO.getMedicamentListBySearch("");// avoir tous les medicaments sans exception
			
			
			request.setAttribute("listeTousMedicaments",ListeDeMedicamments); // envoyer la liste à la page listMedicament.jsp
			
			
			
		}
		
		
		request.getRequestDispatcher("/ListeMedicament.jsp").forward(request, response);// retourner sur la page ListeMedicament.jsp
		
        
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Medicament> ListeDeMedicamments=null;
		
		
		
		if(request.getParameter("Commercialise")!=null) {
			ListeDeMedicamments = MDAO.getMedicamentListBySearch2(request.getParameter("search"));
		
		}else {
			ListeDeMedicamments = MDAO.getMedicamentListBySearch(request.getParameter("search"));
		}
		
		
		request.setAttribute("listemedicaments", ListeDeMedicamments);
		request.setAttribute("keyword",request.getParameter("search"));
		//Renvois vers le fichier prestlistemedoc.jsp
		request.getRequestDispatcher("/ListeMedicament.jsp").forward(request, response);
		
	}

}
