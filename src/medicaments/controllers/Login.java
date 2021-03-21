package medicaments.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import medicaments.DAO.client.UserConnectionDAO;
import medicaments.DAO.client.UtilisateurDAO;
import medicaments.classes.Utilisateur;
import tools.secure.SHA256Encrypter;

/**
 * Servlet implementation class Login
 * 
 * @author Raphael VIGNON
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Variables internes
	private UtilisateurDAO udao;
	private UserConnectionDAO ucdao;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     * Creation de servlet ainsi que des connection DAO nescessaires
     */
    public Login() {
        super();
        udao = new UtilisateurDAO();
        ucdao = new UserConnectionDAO();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Affichage de la vue de connexion. Si des cookies de connection sont enregistres, la connection se fera directement
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	

		String Login_value = null;
		String password_value = null;
		
		//Si les cookies pour se souvenir sont la,et que l'on a pas d�j� tent� de se connecter avec tenter de se connecter avec
		boolean remember_me_cookies = false;
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("remember_Login") ) {
						Login_value=c.getValue();
						remember_me_cookies = true;
					}
					if(c.getName().equals("remember_password")) {
						password_value=c.getValue();
						remember_me_cookies = true;
					}
				}
			}
			
			if(request.getAttribute("cookies_failed")!=null && !(boolean)request.getAttribute("cookies_failed")) {
				remember_me_cookies = false;
			}

		if(remember_me_cookies) {
			doLogin(request, response,Login_value,password_value,true);
		}else {
			
			//R�cup�ration des anciennes valeurs
			if(request.getAttribute("Login")!=null) {
				Login_value = (String) request.getAttribute("Login");
			}else
			{
				Login_value= "";
				request.setAttribute("Login", Login_value);
			}
			
			//Valeur permettant d'indiquer si l'utilisateur a �chou� sa connexion pr�c�dente
			if(request.getAttribute("Login_failed")==null) 
				request.setAttribute("Login_failed", false);
			
			//Affichage de la vue
			request.getRequestDispatcher("/Connexion.jsp").forward(request, response);
		}
	}

	/**
	 * Tentative de connexion avec les parametre passes dans le formulaire
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Login_value = request.getParameter("Login");
		String password_value = request.getParameter("password");

		//Tentative de connexion
		doLogin(request,response,Login_value,password_value,false);
}	
	
	/**
	 * 
	 * Methode permettant d'effectuer la connection, en ajoutant les paramettres de l'utilisateur dans des variables de session, ainsi que dans des cookies si l'option "se souvenir de moi" est cochee.
	 * Elle sauvegarde aussi la connexion dans l'historique
	 * 
	 * @param request Requette HTTP
	 * @param response Reponse HTTP
	 * @param Login_value Email de l'utilisateur tentant de se connecter
	 * @param password_value MDP rentre pour la tentative de connection
	 * @param fromCookies Permet de savoir si l'on tente cette connection avec des cookies ou non
	 * @throws ServletException 
	 * @throws IOException
	 */
	protected void doLogin(HttpServletRequest request, HttpServletResponse response,String Login_value,String password_value,boolean fromCookies) throws ServletException, IOException{
		//R�cup�ration de l'utilisateur dans la BDD 
		Utilisateur user = udao.getUtilisateur(Login_value);


		HttpSession session = request.getSession(true);
		if(checkLogin(request,response,password_value, user,fromCookies)) {
			
			//Authentification r�ussie
			if( request.getParameter("remember_me")!=null && request.getParameter("remember_me").equals("on")) {

				//Cr�ation d'un cookie pour se souvenir de l'utilisateur pendant 2 mois
				Cookie remember_Login_cookie = new Cookie("remember_Login",user.getLogin());
				remember_Login_cookie.setMaxAge(3600*Integer.parseInt(request.getParameter("lastFor").toString()));
				Cookie remember_password_cookie = new Cookie("remember_password",user.getMot_de_passe());
				remember_password_cookie.setMaxAge(3600*Integer.parseInt(request.getParameter("lastFor").toString()));
				
				response.addCookie(remember_password_cookie);
				response.addCookie(remember_Login_cookie);
			
			}

			//Sauvegarde des donn�es de l'utilisateur dans la session
			session.setAttribute("Login", user.getLogin());
			session.setAttribute("ID", user.getId());
			session.setAttribute("Statut", user.getStatut());
			session.setAttribute("Name", user.getNom());
			request.setAttribute("Login_failed", false);
			//rend l'utilisateur comme disponible sur le site
			udao.setDisponibiliteUtilisateur(user.getLogin(), "online");
			
			//Sauvegarde de la connection avec l'adresse IP du client dans la BDD
			ucdao.addUserConnexion(user.getLogin(), request.getRemoteAddr());
			
			response.sendRedirect("Index");
			
			
			
			//Authentification �chou�e
		}else {			
			//Sauvegarde des valeurs pour les garder lors du rechargement de la page
		
			request.setAttribute("Login", Login_value);
			request.setAttribute("cookies_failed", true);
			request.setAttribute("Login_failed", true);
		
			doGet(request, response);
		}
	}
	
	/**
	 * 
	 * Methode permettant de verifier si le mail et le mdp envoyes correspondent a un compte utilisateur dans la BDD ayant la possibilite de se connecter ( soit de statut admin, soit professionnel )
	 * 
	 * @param request Requette HTTP
	 * @param response Reponse HTTP
	 * @param password_value 
	 * @param user
	 * @param fromCookies
	 * @return
	 */
	private boolean checkLogin(HttpServletRequest request, HttpServletResponse response,String password_value, Utilisateur user, boolean fromCookies) {
		
		if (user==null) {
			return false;
		}
		
		SHA256Encrypter shaHash = new SHA256Encrypter();
		try {
			byte[]password_hash_bytes = shaHash.getSHA(password_value);
			String password_hash = shaHash.toHexString(password_hash_bytes);
			

			//Verification que le mot de passe correspond si l'on vient des cookies
			if(fromCookies && user.getMot_de_passe().equals(password_value)) {
				if(!user.getStatut().equals("admin") && !user.getStatut().equals("professionnel")) {
					request.setAttribute("not_registred", true);
					return false;
				}
				return true;

			//Verification que le mot de passe correspond si l'on ne vient pas des cookies
			}else if(!fromCookies && user.getMot_de_passe().equals(password_hash)) {
				if(!user.getStatut().equals("admin") && !user.getStatut().equals("professionnel")) {
					request.setAttribute("not_registred", true);
					return false;
				}
				return true;		
			//Sinon, renvoyer sur la page de base	
			}else {
				return false;
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return false;
		
		
	}
	

}