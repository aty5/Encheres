package fr.eni.enchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;

public interface ArticleDAO {
	
	public void insert(Article article, int noCategorie, int noUtilisateur) throws BLLException;
	public void insertEnchere(Enchere enchere,int noArticle) throws BLLException;
	public List<String> selectCategories() throws BLLException;
	public int selectCategorieByID(String libelle) throws BLLException;
	public Article selectArticleByID(int id) throws BLLException;
	public int selectUtilisateurID(int id) throws BLLException;
	public List<Enchere> selectEncheresByIDArticle(int id) throws BLLException;
	public void updateArticle(Article article) throws BLLException;
	List<Article> getArticlesEnCours() throws SQLException;
	List<Article> selectByCategorie(String libelle) throws SQLException;
}
