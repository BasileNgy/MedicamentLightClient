package medicaments.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicaments.DAO.client.AvisDAO;
import medicaments.DAO.client.CompositionDAO;
import medicaments.DAO.client.MedicamentDAO;
import medicaments.DAO.client.PrescriptionDAO;
import medicaments.DAO.client.PresentationDAO;
import medicaments.DAO.client.InfosImportanteDAO;
import medicaments.classes.Medicament;

/**
 * Servlet implementation class Detail
 * @author Basile
 */
@WebServlet("/Detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MedicamentDAO medDAO = new MedicamentDAO();
	PresentationDAO presDAO = new PresentationDAO();
	PrescriptionDAO prescDAO = new PrescriptionDAO();
	AvisDAO avDAO = new AvisDAO();
	CompositionDAO compDAO = new CompositionDAO();
	InfosImportanteDAO infosDAO = new InfosImportanteDAO();
	Medicament medic;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Detail() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * retrieve and send data of code cis selected but if this code CIS doesn't exist, erreurCIS is triggered
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int code = 0;
		Boolean erreurCIS = true;
		try {
			code = Integer.parseInt(request.getParameter("cis"));
			if (medDAO.getListCodeCIS().contains(code)) {
				erreurCIS = false;
				request.setAttribute("medic", medDAO.getMedicament(code));
				request.setAttribute("listPres", presDAO.getPresentationList(code));
				request.setAttribute("listPresc", prescDAO.getPrescriptionList(code));
				request.setAttribute("listAvisSMR", avDAO.getListAvisSMRCT(code));
				request.setAttribute("listAvisASMR", avDAO.getListAvisASMRCT(code));
				request.setAttribute("listCompo", compDAO.getListComposition(code));
				request.setAttribute("listInfo", infosDAO.getInfosImportante(code));
			}
		}catch (Exception e){
			e.printStackTrace();
		}	
		request.setAttribute("erreurCode", erreurCIS);
		request.getRequestDispatcher("/Detail.jsp").forward(request,response);	
		//System.out.println(avDAO.getListAvisASMRCT(code).get(0).getLibelle());
	}



}
