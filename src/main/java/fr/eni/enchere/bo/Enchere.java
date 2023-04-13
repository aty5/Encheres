package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Enchere {

	private int idACheteur;
	private int montantEnchere;
	private LocalDate dateEnchere;
	private int idArticle;
	private int idEnchere;
	
	
	

	public Enchere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enchere(int idACheteur, int montantEnchere, LocalDate dateEnchere) {
		this.idACheteur = idACheteur;
		this.montantEnchere = montantEnchere;
		this.dateEnchere = dateEnchere;
	}
	
	public Enchere (int idACheteur, int idArticle, int idEnchere, int montantEnchere, LocalDate dateEnchere) {
		this.idACheteur = idACheteur;
		this.idArticle = idArticle;
		this.idEnchere = idEnchere;
		this.montantEnchere= montantEnchere;
		this.dateEnchere=dateEnchere;
	}
	
	public int getIdACheteur() {
		return idACheteur;
	}

	public void setIdACheteur(int idACheteur) {
		this.idACheteur = idACheteur;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public int getIdEnchere() {
		return idEnchere;
	}

	public void setIdEnchere(int idEnchere) {
		this.idEnchere = idEnchere;
	}
	
}