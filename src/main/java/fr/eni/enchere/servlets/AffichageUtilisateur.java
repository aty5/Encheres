package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class AfficherProfil
 */
@WebServlet("/AffichageUtilisateur")
public class AffichageUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String pseudo = request.getParameter("pseudo");

	      UtilisateurManager utilisateurManager = new UtilisateurManager();
	      Utilisateur utilisateur = null;
	      try {
	         utilisateur = utilisateurManager.afficherUtilisateurByPseudo(pseudo);
	      } catch (BLLException e) {
	         e.printStackTrace();
	      }

	      request.setAttribute("utilisateur", utilisateur);

	      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/AffichageUtilisateur.jsp");
	      dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}