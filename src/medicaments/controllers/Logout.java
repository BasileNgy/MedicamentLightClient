package medicaments.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import medicaments.DAO.client.UtilisateurDAO;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurDAO udao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        udao = new UtilisateurDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//session.removeAttribute("Login");
		for(Cookie c:request.getCookies()) {
			if(c.getName().equals("remember_Login")||c.getName().equals("remember_password"))
				c.setMaxAge(0);
			response.addCookie(c);
		}
		udao.setDisponibiliteUtilisateur((String) session.getAttribute("Login"), "offline");
		session.invalidate();
		response.sendRedirect("Login");
		return;
	}

}
