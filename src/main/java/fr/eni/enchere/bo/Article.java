package fr.eni.enchere.bo;

import java.time.LocalDate;
import java.util.List;

public class Article {

	private int idArticle;
	private String nom;
	private String description;
	private LocalDate debutEncheres;
	private LocalDate finEncheres;
	private int miseAPrix;
	private int prixVente;
	private boolean vendu;
	private Adresse lieuRetrait;
	private List<Enchere> encheres;
	private Categorie categorie;
	private String pseudo;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(int idArticle, String nom, int miseAPrix, LocalDate finEncheres, String pseudo) {
		this.idArticle = idArticle;
		this.nom = nom;
		this.miseAPrix = miseAPrix;
		this.finEncheres = finEncheres;
		this.setPseudo(pseudo);
	}
	
	public Article(String nom, int miseAPrix, LocalDate finEncheres, String pseudo) {
		this.nom = nom;
		this.miseAPrix = miseAPrix;
		this.finEncheres = finEncheres;
		this.setPseudo(pseudo);
	}

	public Article(String nom, String description, LocalDate debutEncheres, LocalDate finEncheres, int miseAPrix,
			int prixVente, boolean vendu, Categorie categorie, Adresse lieuRetrait) {

		this.nom = nom;
		this.description = description;
		this.categorie = categorie;
		this.debutEncheres = debutEncheres;
		this.finEncheres = finEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.vendu = vendu;
		this.lieuRetrait = lieuRetrait;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDebutEncheres() {
		return debutEncheres;
	}

	public void setDebutEncheres(LocalDate debutEncheres) {
		this.debutEncheres = debutEncheres;
	}

	public LocalDate getFinEncheres() {
		return finEncheres;
	}

	public void setFinEncheres(LocalDate finEncheres) {
		this.finEncheres = finEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public boolean isVendu() {
		return vendu;
	}

	public void setVendu(boolean vendu) {
		this.vendu = vendu;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Adresse getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Adresse lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	@Override
	public String toString() {
		return String.format(
				"Article : %n" + "id = %d%n" + "nom = %s%n" + "description = %s%n" + "categorie = %s%n"
						+ "Dédut des enchères = %s%n" + "Fin des enchères = %s%n" + "Prix initial = %d%n"
						+ "Prix de vente = %d%n" + "Vendu ? %s%n" + "Adresse = %s",
				idArticle, nom, description, categorie, debutEncheres.toString(), finEncheres.toString(), miseAPrix,
				prixVente, vendu,
				lieuRetrait.getRue() + " " + lieuRetrait.getCodePostal() + " " + lieuRetrait.getVille());
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

}