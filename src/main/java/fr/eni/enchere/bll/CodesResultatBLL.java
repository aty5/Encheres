package fr.eni.enchere.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec quand le nom de l'article n'est pas défini
	 */
	public static final int REGLE_NOM_OBLIGATOIRE = 20000;
	
	/**
	 * Echec quand la catégorie de l'article n'est pas définie
	 */
	public static final int REGLE_CATEGORIE_OBLIGATOIRE = 20001;
		
	/**
	 * Echec quand le prix initial de l'article n'est pas défini
	 */
	public static final int REGLE_PRIX_NOMBRE_POSITIF = 20002;
	
	/**
	 * Echec quand la date de début des enchères n'est pas définie
	 */
	public static final int REGLE_DATE_DEBUT_OBLIGATOIRE = 20003;
	
	/**
	 * Echec quand la date de fin des enchères n'est pas définie
	 */
	public static final int REGLE_DATE_FIN_OBLIGATOIRE = 20004;
	
	/**
	 * Echec quand l'adresse du lieu de retrait n'est pas complètement définie
	 */
	public static final int REGLE_LIEU_RETRAIT_ERREUR = 20005;
	
	/**
	 * Echec quand la catégorie n'est pas dans la liste des catégories
	 */
	public static final int REGLE_CATEGORIE_ERREUR = 20006;
	
	/**
	 * Echec quand la date de début est avant la date du jour ou la date de fin est avant le début
	 */
	public static final int REGLE_DATES_ERREUR = 20007;
	
	/**
	 * Echec quand l'utilisateur n'est pas reconnu
	 */
	public static final int REGLE_NO_UTILISATEUR_ERREUR = 20008;
	
}