package fr.eni.enchere.dal;

public abstract class CodesResultatDAL {

	/**
	 * Echec général quand tentative d'ajouter un article null
	 */
	public static final int INSERT_ARTICLE_NULL=10000;
	
	/**
	 * Echec général quand tentative d'ajouter un lieu de retrait null
	 */
	public static final int INSERT_LIEU_RETRAIT_NULL=10000;
	
	/**
	 * Echec général quand tentative d'ajouter une enchere null
	 */
	public static final int INSERT_ENCHERE_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion de l'article
	 */
	public static final int INSERT_ARTICLE_ECHEC=10001;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion de l'article
	 */
	public static final int INSERT_ENCHERE_ECHEC=10001;
	
	/**
	 * Echec de la lecture des articles
	 */
	public static final int LECTURE_ARTICLE_ECHEC=10002;
	
	/**
	 * Echec de la lecture de l'identifiant de la catégorie
	 */
	public static final int LECTURE_CATEGORIE_ID_ECHEC=10003;
	
	/**
	 * Echec quand tentative de lecture d'un article avec un nom vide 
	 */
	public static final int LECTURE_ARTICLE_ID_INCORRECT=10004;
	
	/**
	 * Echec de la lecture de l'article à partir de son nom
	 */
	public static final int LECTURE_ARTICLE_NAME_ECHEC=10005;
	
	/**
	 * Echec de la lecture de l'utilisateur à partir de son identifiant
	 */
	public static final int LECTURE_UTILISATEUR_ID_ECHEC=10002;
	
	/**
	 * Echec de la lecture des encheres sur un article
	 */
	public static final int LECTURE_ENCHERES_ECHEC=10002;
	
}