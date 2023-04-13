package fr.eni.enchere.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.ConnectionProvider;

public class ArticleDAOJdbcImpl implements ArticleDAO{

	private static final String INSERT_ARTICLE = "INSERT INTO articles(nom_article, description,"
			+ "date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
			+ "values(?,?,?,?,?,?,?,?);";
	private static final String INSERT_RETRAIT = "INSERT INTO retraits(no_article, rue, code_postal,"
			+ "ville) values(?,?,?,?);";
	private static final String INSERT_ENCHERE = "INSERT INTO encheres(no_utilisateur, no_article, date_enchere,"
			+ "montant_enchere) values(?,?,?,?);";
	
	private static final String SELECT_CATEGORIES = "SELECT libelle FROM categories;";
	private static final String SELECT_CATEGORIE_ID = "SELECT no_categorie FROM categories WHERE libelle = ?;";
	private static final String SELECT_ARTICLE_ID = "SELECT nom_article, description, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, libelle, r.rue, r.code_postal, r.ville, pseudo"
			+ " FROM articles a, categories c, retraits r, utilisateurs u WHERE a.no_article = ? "
			+ "AND a.no_categorie = c.no_categorie AND a.no_article = r.no_article AND u.no_utilisateur = a.no_utilisateur;";
	
	private static final String SELECT_UTILISATEUR_ID = "SELECT no_utilisateur FROM articles_vendus WHERE no_article = ?";
	private static final String SELECT_ENCHERES_ID = "SELECT no_utilisateur, date_enchere, montant_enchere "
			+ "FROM encheres WHERE no_article = ?;";
	private static final String SELECT_CATEGORIE ="SELECT a.nom_article, a.prix_initial, a.fin_encheres, a.no_categorie, u.pseudo"+
			"JOIN utilisateurs u ON a.no_utilisateur = u.no_utilisateur"+
			"JOIN categorie c ON a.no_categorie = c.no_categorie"+
			"WHERE c.libelle = ? AND date_fin_encheres > SYSDATETIME";
	
	@Override
	public void insert(Article article, int noCategorie, int noUtilisateur) throws BLLException {
		
		BLLException bllException = new BLLException();
		
		if(article==null){
			bllException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_NULL);
			throw bllException;
		} else if(article.getLieuRetrait()==null) {
			bllException.ajouterErreur(CodesResultatDAL.INSERT_LIEU_RETRAIT_NULL);
			throw bllException;		
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getNom());
				pstmt.setString(2, article.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(article.getDebutEncheres()));
				pstmt.setDate(4, java.sql.Date.valueOf(article.getFinEncheres()));
				pstmt.setInt(5, article.getMiseAPrix());
				pstmt.setInt(6, article.getMiseAPrix());
				pstmt.setInt(7, noUtilisateur);
				pstmt.setInt(8, noCategorie);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					article.setIdArticle(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				
				// Insertion de l'adresse de retrait dans la table retraits
				pstmt = cnx.prepareStatement(INSERT_RETRAIT);
				pstmt.setInt(1, article.getIdArticle());
				pstmt.setString(2, article.getLieuRetrait().getRue());
				pstmt.setString(3, article.getLieuRetrait().getCodePostal());
				pstmt.setString(4, article.getLieuRetrait().getVille());
				pstmt.executeUpdate();
				
				pstmt.close();
				
				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			bllException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
			throw bllException;
		}
		
	}

	@Override
	public List<String> selectCategories() throws BLLException {

		List<String> categories = new ArrayList<String>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIES);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				categories.add(rs.getString(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BLLException bllException = new BLLException();
			bllException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw bllException;
		}
		
		return categories;
	}


	@Override
	public int selectCategorieByID(String libelle) throws BLLException {
		int noCategorie = -1;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE_ID);
			pstmt.setString(1, libelle);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				noCategorie = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BLLException bllException = new BLLException();
			bllException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIE_ID_ECHEC);
			throw bllException;
		}
		
		return noCategorie;
	}

	@Override
	public Article selectArticleByID(int id) throws BLLException {
		
		Article article = null;
		String nom = null;
		String description = null;
		Categorie categorie = null;
		LocalDate debutEncheres = null;
		LocalDate finEncheres = null;
		int miseAPrix = 0;
		int prixVente = 0;
		Adresse lieuRetrait = null;
		String pseudo = null;
		
		if(id <= 0)
		{
			BLLException bllException = new BLLException();
			bllException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ID_INCORRECT);
			throw bllException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_ID);
			pstmt.setInt(1,id);		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				nom = rs.getString(1);
				description = rs.getString(2);
				debutEncheres = rs.getDate(3).toLocalDate();
				finEncheres = rs.getDate(4).toLocalDate();
				miseAPrix = rs.getInt(5);
				prixVente = rs.getInt(6);
				categorie = new Categorie(rs.getString(7));
				lieuRetrait = new Adresse(rs.getString(8), rs.getString(9), rs.getString(10));
				pseudo = rs.getString(11);
				
				System.out.println("Pseudo : " + pseudo);
							
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BLLException bllException = new BLLException();
			bllException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_NAME_ECHEC);
			throw bllException;
		}	
		System.out.println("Nom = " + nom);
		
		article = new Article(nom, description, debutEncheres, finEncheres, miseAPrix, prixVente,
				false, categorie, lieuRetrait);
		article.setPseudo(pseudo);
		article.setIdArticle(id);
		
		return article;
	}

	@Override
	public int selectUtilisateurID(int id) throws BLLException {
		int numeroUtilisateur = -1;
		
		if(id <= 0)
		{
			BLLException bllException = new BLLException();
			bllException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ID_INCORRECT);
			throw bllException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_ID);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				numeroUtilisateur = rs.getInt(1);		
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BLLException bllException = new BLLException();
			bllException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ID_ECHEC);
			throw bllException;
		}
		
		return numeroUtilisateur;
	}

	@Override
	public List<Enchere> selectEncheresByIDArticle(int id) throws BLLException {
		List<Enchere> encheres = null;
		
		if(id <= 0)
		{
			BLLException bllException = new BLLException();
			bllException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ID_INCORRECT);
			throw bllException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERES_ID);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			
			encheres = new ArrayList<>();
			
			while(rs.next())
			{
				encheres.add(new Enchere(rs.getInt(1),rs.getInt(3),rs.getDate(2).toLocalDate()));		
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BLLException bllException = new BLLException();
			bllException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERES_ECHEC);
			throw bllException;
		}
		
		return encheres;
	}

	@Override
	public void insertEnchere(Enchere enchere, int noArticle) throws BLLException {
		
		BLLException bllException = new BLLException();
		
		if(enchere==null){
			bllException.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_NULL);
			throw bllException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE);
				pstmt.setInt(1, enchere.getIdACheteur());
				pstmt.setInt(2, noArticle);
				pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDateEnchere()));
				pstmt.setInt(4, enchere.getMontantEnchere());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();

				rs.close();
				pstmt.close();
				
				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			bllException.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_ECHEC);
			throw bllException;
		}
	}

	@Override
	public void updateArticle(Article article) throws BLLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Article> selectByCategorie(String libelle) throws SQLException {
		List<Article> listeArticle = new ArrayList<Article>();
		
		Connection cnx = ConnectionProvider.getConnection();
		
		PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE);
		pstmt.setString(1, libelle);
		ResultSet rs = pstmt.executeQuery();
		Article articleCategorie = new Article();
		
		while (rs.next())
		{
			try {
				if (rs.getString("libelle").equals(articleCategorie.getCategorie().getLibelle()))
				{
					String nom = rs.getString("nom_article");
					int miseAPrix = rs.getInt("prix_initial");
					LocalDate finEncheres = rs.getDate("date_fin_encheres").toLocalDate();
					String pseudo = rs.getString("pseudo");
					
					articleCategorie = new Article (nom, miseAPrix, finEncheres, pseudo);
					listeArticle.add(articleCategorie);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listeArticle;
	}

	
	
	@Override
	public List<Article> getArticlesEnCours() throws SQLException 
	{
        List<Article> articlesEnCours = new ArrayList<>();
        int noArticle;
        String nomArticle ;
        int prixInitial ;
        LocalDate dateFinEnchere ;
        String pseudo ;

        try (Connection cnx = ConnectionProvider.getConnection()) 
        {
        	Statement statement = cnx.createStatement();
        	
        	ResultSet resultat = statement.executeQuery
        			("SELECT a.no_article, a.nom_article, a.prix_initial, a.date_fin_encheres, u.pseudo "
                     + "FROM articles a "
                     + "JOIN utilisateurs u "
                     + "ON a.no_utilisateur = u.no_utilisateur "
                     + "WHERE a.date_fin_encheres > SYSDATETIME()");
        	
        	while (resultat.next()) 
        	{
        		noArticle = resultat.getInt("no_article");
        		nomArticle = resultat.getString("nom_article");
                prixInitial = resultat.getInt("prix_initial");
                dateFinEnchere = resultat.getDate("date_fin_encheres").toLocalDate();
                pseudo = resultat.getString("pseudo");

               // Article article = new Article(nomArticle, prixInitial, dateFinEnchere, pseudo);
                articlesEnCours.add(new Article(noArticle, nomArticle, prixInitial, dateFinEnchere, pseudo));
            }
        	
        }
        catch (SQLException e) {
			e.printStackTrace();
        }
		return articlesEnCours;
     
	
	}
}