package fr.eni.enchere.servlets;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;

public class OutilsDeServlets {
	
	protected static Article lectureArticle(HttpServletRequest request) {
		
		Article articleLu = null;
				
//		Lecture des informations saisies
		String nom = request.getParameter("nom");		
		String description = request.getParameter("description");	
		String categorie = request.getParameter("categorie");
		int miseAPrix = Integer.parseInt(request.getParameter("prix"));	
		LocalDate debutEncheres = LocalDate.parse(request.getParameter("debut"));		
		LocalDate finEncheres = LocalDate.parse(request.getParameter("fin"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		
		articleLu = new Article(nom, description, debutEncheres, finEncheres, miseAPrix,
					miseAPrix, false, new Categorie(categorie), new Adresse(rue,codePostal,ville));
		
		return articleLu;
		
	}

}