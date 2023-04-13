package fr.eni.enchere.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.Adresse;

/**
 * Servlet implementation class VendreUnArticle
 */
@WebServlet("/VendreUnArticle")
public class ServletVendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Article article;
	private Utilisateur utilisateur;
	private int noUtilisateur;
	private static List<String> categories;
	private ArticleManager articleManager;
	private UtilisateurManager utilisateurManager;

	@Override
	public void init() throws ServletException {
	
		articleManager = new ArticleManager();
		utilisateurManager = new UtilisateurManager();
		
		try {
			categories = articleManager.getCategories();
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		// lecture du numéro de l'utilisateur connecté et récupération de l'utilisateur correspondant
		noUtilisateur = utilisateur.getIdUtilisateur(); //4; Integer.parseInt(request.getParameter("idUtilisateur"));
		
//		try {
//			utilisateur = utilisateurManager.selectUtilisateurByID(noUtilisateur);
//		} catch (BLLException | SQLException e) {
//			e.printStackTrace();
//		}
				
		request.setAttribute("categories", categories);
		request.setAttribute("adresseRetraitParDefaut",new Adresse(utilisateur.getRue(),
							utilisateur.getcodePostal(),utilisateur.getVille()));
		
		LocalDate dateEnchereParDefaut = LocalDate.now();
		request.setAttribute("debutencheres", dateEnchereParDefaut);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/VendreArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		article = OutilsDeServlets.lectureArticle(request);
		
		try {
			articleManager.ajouterArticle(article, noUtilisateur);
		} catch (BLLException e) {
			e.printStackTrace();
		}
				
		request.setAttribute("categories", categories);
		request.setAttribute("adresseRetraitParDefaut",new Adresse(utilisateur.getRue(),
				utilisateur.getcodePostal(),utilisateur.getVille()));

		LocalDate dateEnchereParDefaut = LocalDate.now();
		request.setAttribute("debutencheres", dateEnchereParDefaut);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/VendreArticle.jsp");
		rd.forward(request, response);		
		
	}
	
}