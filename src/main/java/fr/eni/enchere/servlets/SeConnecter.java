package fr.eni.enchere.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class SeConnecter
 */
@WebServlet("/SeConnecter")
public class SeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/SeConnecter.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clicConnect= request.getParameter("connecter");
		String creaCompte= request.getParameter("creerCompte");
		String motpass= request.getParameter("mot_de_passe");
		String idpageSeConnecter= request.getParameter("identifiant");
		utilisateurManager= new UtilisateurManager();
		Utilisateur utilisateur= null;
		
		try {
			utilisateur = utilisateurManager.verifUtilisateur(idpageSeConnecter, motpass);
		} catch (BLLException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(clicConnect != null) {
			
			if(utilisateur== null) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/SeConnecter.jsp");
				rd.forward(request, response);			
			}
			else {
				
				HttpSession session = request.getSession();				
				session.setAttribute("utilisateur", utilisateur);
				request.setAttribute("utilisateur", utilisateur);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/accueil.jsp");
				rd.forward(request, response);
				//response.sendRedirect("Accueil");				
		
			}
		}
					
		if(creaCompte != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Sinscrire.jsp");
			rd.forward(request, response);
		}
		 
	}

}