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

@WebServlet("/Sinscrire")
public class Sinscrire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Sinscrire.jsp");
		rd.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
	
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");			
		String motDePasse1 = request.getParameter("mot_de_passe1");
		String motDePasse2 = request.getParameter("mot_de_passe2");
		
		
		
		request.setAttribute("pseudo", pseudo);
		request.setAttribute("nom", nom);
		request.setAttribute("prenom", prenom);
		request.setAttribute("email", email);
		request.setAttribute("telephone", telephone);
		request.setAttribute("rue", rue);
		request.setAttribute("code_postal", codePostal);
		request.setAttribute("ville", ville);
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();				
		
	try 
	{
				
		utilisateurManager.ajouterUtilisateur(pseudo, nom, prenom, email, 
											  telephone, rue, codePostal, ville,
											  motDePasse1, motDePasse2);
		
		
		
	} 
				
	catch (BLLException e) 
	{
					
		request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Sinscrire.jsp");
		rd.forward(request, response);
	} 
		
	catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, 
        rue, codePostal, ville, motDePasse1);
		HttpSession session = request.getSession();
		
		session.setAttribute("utilisateur", utilisateur);
		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
		rd.forward(request, response);
		
		
	}
}


			

