package medicaments.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import medicaments.DAO.client.UtilisateurDAO;
import medicaments.classes.Utilisateur;
import tools.secure.SHA256Encrypter;

/**
 * Servlet implementation class SignIn
 * 
 * @author Raphael VIGNON
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilisateurDAO UDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        UDAO = new UtilisateurDAO();
    }

	/**
	 * 
	 * Affichage de la vue de l'inscription
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String Login_value = null;
		
		HttpSession session = request.getSession(true);
		
		//R�cup�ration des anciennes valeurs
		if(session.getAttribute("Login")!=null) {
			Login_value = (String) session.getAttribute("Login");
		}else
		{
			Login_value= "";
			session.setAttribute("Login", Login_value);
		}
		
		if(session.getAttribute("Login_failed")==null) 
			session.setAttribute("Login_failed", false);
		
		
		
		//Affichage de la vue
		request.getRequestDispatcher("/Inscription.jsp").forward(request, response);
	}

	/**
	 * Recuperation des valeurs du formulaire pour tenter une inscription
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String Login_value = request.getParameter("Login");
		String password_value = request.getParameter("password");
		String password_verify_value = request.getParameter("password_verify");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String profession = request.getParameter("profession");
		
		doSignIn(request,response,Login_value,password_value,password_verify_value,nom,prenom,profession);
	}
	
	
	/**   
	 * 
	 * Methode permettant d'effectuer une tentative d'inscription. Celle-ci echoue si l'utilisateur existe deja, ou si les 2 mdp rentres ne correspondent pas
	 *   
	 * @param request Requette HTTP
	 * @param response Reponse HTTP
	 * @param Login_value Email de l'utilisateur souhaitant s'inscrire
	 * @param password_value MDP de l'utilisateur souhaitant s'inscrire
	 * @param password_verify_value Repetition du MDP
	 * @param nom Nom de l'utilisateur
	 * @param prenom Prenom de l'utilisateur
	 * @param profession Profession de l'utilisateur : medecin, pharmacien, laboratoire pharmaceutique ou etudiant en medecine/pharmacie
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doSignIn(HttpServletRequest request, HttpServletResponse response,String Login_value,String password_value,String password_verify_value,String nom,String prenom,String profession) throws ServletException, IOException {
		if(checkPasswords(password_value,password_verify_value)) {
			if(!checkIfUserExist(Login_value)) {
				//Hashage des mots de passe pour les stocker
				SHA256Encrypter shaHash = new SHA256Encrypter();
				try {
					byte[] password_hash_bytes = shaHash.getSHA(password_value);
					String password_hash=new String();
					password_hash = shaHash.toHexString(password_hash_bytes);
					
					//Cr�ation de l'utilisateur en java
					System.out.println(nom);
					Utilisateur user = new Utilisateur(0,Login_value,password_hash,"enAttente",nom,prenom,profession);

					//Ajout de l'utilisateur dans la base de donn�es
					UDAO.addUtilisateur(user);
					
				} catch (NoSuchAlgorithmException e) {
					request.setAttribute("hash_error", true);
					e.printStackTrace();
				}
				
				
				//Redirection vers l'index
				response.sendRedirect("Index");
			}else {
				request.setAttribute("login_exist", true);
				doGet(request,response);
			}
			
		}else {
			request.setAttribute("password_dissmatch", true);
			doGet(request,response);
		}
	
	}
	
	
	/**
	 * Methode permettant de verifier si les deux mots de passent correspondent
	 * 
	 * @param password
	 * @param passwordVerify
	 * @return
	 */
	private boolean checkPasswords(String password, String passwordVerify) {
		if(password!=null & passwordVerify!=null) {
			if(password.equals(passwordVerify)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 *    
	 *  Methode permettant de verifier si l'utilisateur existe deja dans la BDD
	 *    
	 * @param Login Email de l'utilisateur
	 * @return Vrai si il existe, faux sinon
	 */
	private boolean checkIfUserExist(String Login) {
		if(Login!=null) {
			if(UDAO.getUtilisateur(Login)!=null) {
				return true;
			}
		}
		return false;
	}
}
