package fr.eni.enchere.dal;

import java.sql.SQLException;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	public void ajouterUtilisateur(Utilisateur utilisateur) throws SQLException, BLLException;

	public boolean verificationPseudoUnique(String pseudo) throws  BLLException;
	
	public boolean verificationEmailUnique(String email);
	
	public Utilisateur afficherUtilisateurByPseudo(String Pseudo) throws BLLException, SQLException;

	public void modifierUtilisateur (Utilisateur utilisateur) throws SQLException, BLLException;;

	public Utilisateur selectUtilisateurByID(int idUtilisateur) throws BLLException;

	
	/*public int trouverIdUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
            String rue, String codePostal, String ville) throws SQLException;*/
	
	public Utilisateur trouverUtilisateur(String login, String motDePasse);
	
	public String selectmotDePasseOfEmail(String email);
	
	public String selectmotDePasseOfPseudo(String pseudo);
	
	public Utilisateur selectUtilisateurByEmail(String email);
}