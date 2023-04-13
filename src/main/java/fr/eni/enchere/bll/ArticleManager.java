package fr.eni.enchere.bll;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DAOFactory;

public class ArticleManager {
		
	private ArticleDAO articleDAO;
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	public List<Article> selectByCategorie(Categorie categorie) throws SQLException{
		return this.articleDAO.selectByCategorie(categorie.getLibelle());
	}
	
	public List<Article> selectAll() throws SQLException{
		return this.articleDAO.getArticlesEnCours();
	}
	
	public List<String> getCategories() throws BLLException{
		return this.articleDAO.selectCategories();
	}
	
	public int getCategorieByID(String libelle) throws BLLException {
		return this.articleDAO.selectCategorieByID(libelle);
	}
	
	public Article getArticleByID(int id) throws BLLException {
		return this.articleDAO.selectArticleByID(id);
	}
	
	public void ajouterArticle(Article article, int noUtilisateur) throws BLLException{
				
		int noCategorie = getCategorieByID(article.getCategorie().getLibelle());
		BLLException bllException = new BLLException();
		
		if(validerArticle(article, noCategorie, noUtilisateur, bllException)) {
			this.articleDAO.insert(article, noCategorie, noUtilisateur);
			System.out.println(article.toString());
		} else {
			
			throw bllException;
		}
	}
	
	public void ajouterEnchere(Enchere enchere, int noArticle) throws BLLException {
		if(validerEnchere(enchere,getArticleByID(noArticle))) {
			articleDAO.insertEnchere(enchere,noArticle);
		}
	}
	
	public boolean validerArticle(Article article, int noCategorie, int noUtilisateur, BLLException bllE) throws BLLException {
		boolean valide = true;
		
		if(article.getNom().isBlank()) {
			valide = false;
			bllE.ajouterErreur(CodesResultatBLL.REGLE_NOM_OBLIGATOIRE);		
		}
		
		if(noCategorie == -1) {
			valide = false;
			bllE.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIE_OBLIGATOIRE);		
		} 
		
		if(article.getMiseAPrix() <= 0) {
			valide = false;
			bllE.ajouterErreur(CodesResultatBLL.REGLE_PRIX_NOMBRE_POSITIF);			
		}
		
		if(article.getDebutEncheres() == null) {
			valide = false;
			bllE.ajouterErreur(CodesResultatBLL.REGLE_DATE_DEBUT_OBLIGATOIRE);			
		}
		
		if(article.getFinEncheres() == null) {
			valide = false;
			bllE.ajouterErreur(CodesResultatBLL.REGLE_DATE_FIN_OBLIGATOIRE);			
		} 
		
		if(article.getLieuRetrait().getRue().isBlank() ||
				article.getLieuRetrait().getCodePostal().isBlank() ||
				article.getLieuRetrait().getVille().isBlank()) {
			valide = false;
			bllE.ajouterErreur(CodesResultatBLL.REGLE_LIEU_RETRAIT_ERREUR);	
		}
		
		if(!this.getCategories().contains(article.getCategorie().getLibelle())) {
			valide = false;
			bllE.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIE_ERREUR);			
		} 
		
		if(article.getDebutEncheres().isBefore(LocalDate.now()) ||
				article.getFinEncheres().isBefore(article.getDebutEncheres())) {
			valide = false;
			bllE.ajouterErreur(CodesResultatBLL.REGLE_DATES_ERREUR);
			
		} 
		
		if(noUtilisateur < 0) {
			valide = false;
			bllE.ajouterErreur(CodesResultatBLL.REGLE_NO_UTILISATEUR_ERREUR);
		}
			
		return valide;
	}
	
	public boolean validerEnchere(Enchere enchere, Article article) {
		return enchere.getMontantEnchere() > article.getPrixVente();
	}
	
	public int getNumeroVendeur(int numeroArticle) throws BLLException {
		return articleDAO.selectUtilisateurID(numeroArticle);
	}

	public List<Enchere> getEncheres(int numeroArticle) throws BLLException {
		return articleDAO.selectEncheresByIDArticle(numeroArticle);
	}
	
	public int getIdMeilleurEncherisseur(int numeroArticle) throws BLLException {
		
		int bestEncherisseur = -1;
		int bestMontant = 0;
		List<Enchere> encheres = getEncheres(numeroArticle);
		
		if(encheres.size() > 0) {
			for(Enchere e : encheres) {
				if(e.getMontantEnchere() >= bestMontant) {
					bestEncherisseur = e.getIdACheteur();
					bestMontant = e.getMontantEnchere();
				}
			}
		}
		
		return bestEncherisseur;
	}
	
	public boolean encheresEncours(Article a) {
		return (a.getDebutEncheres().isBefore(LocalDate.now())
				&& a.getFinEncheres().isAfter(LocalDate.now()));
	}
	
	public boolean encheresTerminees(Article a) {
		return a.getFinEncheres().isBefore(LocalDate.now());
	}
	
	public void enregistrerArticle(Article article) throws BLLException {
		articleDAO.updateArticle(article);
	}

}