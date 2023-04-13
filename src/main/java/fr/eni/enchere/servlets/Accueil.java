package fr.eni.enchere.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDAO;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	
	private static final long serialVersionUID = 1L;  
	
	private Utilisateur utilisateur;
	private List<Article> articles;
	
	@Override
	public void init() throws ServletException {
//		utilisateur = new Utilisateur();
		articles = new ArrayList<>();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleManager articleManager = new ArticleManager();
		List<Article> listeArticle = new ArrayList<>();
		List<String> categories = null;
		
		HttpSession session = request.getSession(); // Vérifie si une session est ouverte, sinon en crée une
		session.setAttribute("utilisateur",utilisateur);
		
		try {
			categories = articleManager.getCategories();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		session.setAttribute("categories", categories);
	
		try {
			//if(request.getAttribute("categorie_select")== null)
			{
			listeArticle = articleManager.selectAll();
			session.setAttribute("articlesEnCours", listeArticle);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/accueil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ArticleManager articleManager = new ArticleManager();
	List<Article> listeArticle = new ArrayList<>();
	List<String> categories = null;
	try {
		categories = articleManager.getCategories();
	} catch (BLLException e1) {
		e1.printStackTrace();
	}
	
	Categorie categorie = new Categorie(request.getParameter("categorie_select"));
	System.out.println(request.getParameter("categorie_select"));
	//request.setAttribute("categories", categories);
		try {
			
				listeArticle = articleManager.selectByCategorie(categorie);
				request.setAttribute("articlesEnCours", listeArticle);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}
}