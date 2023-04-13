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
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifierArticleVendu
 */
@WebServlet("/VoirArticle")
public class ServletVoirArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int idArticle;
	private int idUtilisateur;
	private Article article;
	private Utilisateur utilisateur;
	private Utilisateur vendeur;
	private Utilisateur acheteur;
	private Enchere enchere;
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
		
		String url = request.getContextPath();
		
		idUtilisateur = utilisateur.getIdUtilisateur(); //4; Integer.parseInt(request.getParameter("numeroUtilisateur"));
		idArticle = Integer.parseInt(request.getParameter("numeroArticle"));
		String pseudoVendeur = request.getParameter("pseudoVendeur");
		
		try {
			article = articleManager.getArticleByID(idArticle);
			vendeur = utilisateurManager.afficherUtilisateurByPseudo(pseudoVendeur);
			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("categories", categories);
		request.setAttribute("article", article);		
		request.setAttribute("utilisateur", utilisateur);
		request.setAttribute("vendeur", vendeur);
		request.setAttribute("acheteur", acheteur);
				
		if(idUtilisateur != vendeur.getIdUtilisateur()) {
			if(articleManager.encheresEncours(article)) {
				url = "/WEB-INF/JSP/Encherir.jsp";
				
			} else if(articleManager.encheresTerminees(article)) {
				url = "/WEB-INF/JSP/InfoVenteTerminee.jsp";
			}
			
		} else {
			if(articleManager.encheresEncours(article)) {
				request.setAttribute("acces", "disabled");
				url = "/WEB-INF/JSP/ModifierVente.jsp";
			} else if(articleManager.encheresTerminees(article)) {
				url = "/WEB-INF/JSP/DetailMaVenteFinEncheres.jsp";
			} else {
				request.setAttribute("acces", "");
				url = "/WEB-INF/JSP/ModifierVente.jsp";
			}
		}
			
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		article = OutilsDeServlets.lectureArticle(request);
			
		String btnEnregistrer = request.getParameter("btnEnregistrer");
		String btnAnnuler = request.getParameter("btnAnnuler");
		String btnEncherir = request.getParameter("Btn_Encherir");
		
		if(btnEncherir != null) {
			enchere = new Enchere(idUtilisateur,Integer.parseInt(request.getParameter("proposition")),LocalDate.now());
			try {
				articleManager.ajouterEnchere(enchere,idArticle);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		
		if(btnEnregistrer != null) {
			try {
				articleManager.enregistrerArticle(article);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}