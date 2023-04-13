package fr.eni.enchere.bo;

public class Categorie {
	
	private String libelle;
	private int no_categorie;
	
	public Categorie (String libelle, int no_categorie) {
		this.libelle = libelle;
		this.no_categorie = no_categorie;
	}
	
	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	public Categorie() {
	}

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getNo_categorie() {
		return no_categorie;
	}
	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}
		
}