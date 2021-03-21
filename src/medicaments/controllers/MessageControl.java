package medicaments.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicaments.DAO.client.MessagerieDAO;
import medicaments.classes.Messagerie;


/**
 * Servlet implementation class MessageControl
 */
@WebServlet("/MessageControl")
public class MessageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MessagerieDAO MSG;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageControl() {
        super();
        MSG = new MessagerieDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Mail_value = request.getParameter("mail");
		String Objet_value = request.getParameter("objet");
		String Message_value = request.getParameter("message");
		doMail(request, response, Mail_value, Objet_value, Message_value);
	}
	
	protected void doMail(HttpServletRequest request, HttpServletResponse response, String Mail_value, String Objet_value,String Message_value) throws ServletException, IOException
	{
		try 
		{
			//Création du message en java
			//System.out.println(nom);
			Messagerie msg = new Messagerie(0,Mail_value,Objet_value,Message_value, null);

			//Ajout du message dans la base de données
			MSG.addMessagerie(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("Index");
		
		doGet(request, response);
	}

}
