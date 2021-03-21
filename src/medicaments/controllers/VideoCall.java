package medicaments.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import io.vidyo.util.GenerateToken;
import javax.websocket.Session;

/**
 * Servlet implementation class VideoCall
 * 
 * @author Raphael VIGNON
 */
@WebServlet("/VideoCall")
public class VideoCall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoCall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * Si les parametres sont bien rentres dans l'URL, generation du token permettant l'appel video et sauvegarde de celui-ci dans la requete, puis affichage de la vue
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("name")!=null && request.getParameter("room")!=null) {

			 String token = GenerateToken.generateProvisionToken("6736ee3dac3a4ae4adfbf49c8f266fde", request.getParameter("name") + "@" + "4e0f76.vidyo.io", "10000", "");
			 request.setAttribute("token", token);
			 
			//Affichage de la vue
			request.getRequestDispatcher("/VideoCall.jsp").forward(request, response);
		
		}else {

			//Affichage de la vue
			request.getRequestDispatcher("/Index").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
