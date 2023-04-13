

package fr.eni.enchere.dal;

import fr.eni.enchere.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.enchere.dal.jdbc.UtilisateurDAOJDBCImpl;

public abstract class DAOFactory {
	
	public static ArticleDAO getArticleDAO()
	{
		return new ArticleDAOJdbcImpl();
	}
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJDBCImpl();
	}
}
	