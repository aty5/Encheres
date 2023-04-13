package fr.eni.enchere.servlets;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    /*Utilisateur utilisateur = (Utilisateur) */
		
			request.getSession().getAttribute("utilisateur");
		    
		    /*request.setAttribute("pseudo", utilisateur.getPseudo());
		    request.setAttribute("nom", utilisateur.getNom());
		    request.setAttribute("prenom", utilisateur.getPrenom());
		    request.setAttribute("email", utilisateur.getEmail());
		    request.setAttribute("telephone", utilisateur.getTelephone());
		    request.setAttribute("rue", utilisateur.getRue());
		    request.setAttribute("codePostal", utilisateur.getcodePostal());
		    request.setAttribute("ville", utilisateur.getVille());
		    request.setAttribute("motDePasse", utilisateur.getMdp());*/

		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModifierProfil.jsp");
		rd.forward(request, response);
	
	}

	

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		

		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		
		utilisateur.getIdUtilisateur();
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
			
	
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setcodePostal(codePostal);
		utilisateur.setVille(ville);
		
		try 
		{
			utilisateurManager.modifierUtilisateur(utilisateur); 
		} 
		catch (BLLException | SQLException e) 
		{
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		request.getSession().setAttribute("utilisateur", utilisateur);
			
		response.sendRedirect("/Accueil");
	}
}
	


