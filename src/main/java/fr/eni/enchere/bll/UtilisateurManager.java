package fr.eni.enchere.bll;

import java.sql.SQLException; 

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() 
	{
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	
	/*public int trouverIdUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
            String rue, String codePostal, String ville) throws SQLException {
				
	int idUtilisateur = 0;
	this.utilisateurDAO.trouverIdUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville);
		
	
	return idUtilisateur;
		
		
	}*/
	public void ajouterUtilisateur(String pseudo, String nom, String prenom, String email,
		       String telephone, String rue, String codePostal, String ville,
		       String motDePasse1, String motDePasse2) throws SQLException, BLLException
	{
		BLLException bllException = new BLLException();
		this.validerPseudo(pseudo, bllException);
		this.validerEmail(email, bllException);
		this.validerIdentite(nom, prenom, bllException);
		this.validerAdresse(rue, codePostal, ville, bllException);
		this.validerTelephone(telephone, bllException);
		this.validerMDP(motDePasse1, motDePasse2, bllException);
		
		
		if(!bllException.hasErreurs())
		{
			Utilisateur utilisateur = new Utilisateur ( pseudo,  nom,  prenom,  email,
				        telephone,  rue,  codePostal,  ville, motDePasse1);
			this.utilisateurDAO.ajouterUtilisateur(utilisateur);
			
		}
		else
		{
			throw bllException;
		}
	}
	
	public Utilisateur selectUtilisateurByID(int idUtilisateur) throws BLLException, SQLException {
		Utilisateur utilisateur = null;
		
		utilisateur = utilisateurDAO.selectUtilisateurByID(idUtilisateur);
		
		return utilisateur;
	}
	
	
	public Utilisateur afficherUtilisateurByPseudo(String pseudo) throws BLLException 
	{
		Utilisateur utilisateur = null;
		try 
		{
			utilisateur = utilisateurDAO.afficherUtilisateurByPseudo(pseudo);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateur;		
	}
	
	
	public void modifierUtilisateur(Utilisateur utilisateur) throws BLLException, SQLException
	{
		/*BusinessException bllException = new BusinessException();
		this.validerPseudo(pseudo, bllException);
		this.validerEmail(email, bllException);
		this.validerIdentite(nom, prenom, bllException);
		this.validerAdresse(rue, codePostal, ville, bllException);
		this.validerTelephone(telephone, bllException);
		
		if (!bllException.hasErreurs()) 
		{*/
		System.out.println(utilisateur.getIdUtilisateur());
		utilisateurDAO.modifierUtilisateur(utilisateur);
		
		/*}else 
		{
			throw bllException;
		}*/
	}



	




		/*BusinessException bllException = new BusinessException();
		this.validerPseudo(utilisateur.getPseudo(), bllException);
		this.validerEmail(utilisateur.getEmail(), bllException);
		this.validerIdentite(utilisateur.getNom(), utilisateur.getPrenom(), bllException);
		this.validerAdresse(utilisateur.getRue(), utilisateur.getcodePostal(), utilisateur.getVille(), bllException);
		this.validerTelephone(utilisateur.getTelephone(), bllException);
		
		
		if (!bllException.hasErreurs()) 
		{
			utilisateurDAO.modifierUtilisateur(utilisateur);
		} 
		else 
		{
			throw bllException;*/
		
		
	
	
	
	
	
	//Les méthodes pour valider les champs remplis par les utilisateurs lors de l'inscription utilisés dans les lignes 24 à 29
	
	
		private void validerPseudo(String pseudo, BLLException bllException) throws BLLException 
		{
			if(pseudo.equals("") 
			   || pseudo.trim().length()>30
			   || !this.utilisateurDAO.verificationPseudoUnique(pseudo))
			{
				bllException.ajouterErreur(CodesResultatsBLL.ERREUR_PSEUDO_UTILISATEUR);
			}
		}
		
		private void validerEmail(String email, BLLException bllException) throws BLLException 
		{
			if(email.equals("") 
			   || email.trim().length()>30
			   || !this.utilisateurDAO.verificationEmailUnique(email))
			{
				bllException.ajouterErreur(CodesResultatsBLL.ERREUR_EMAIL_UTILISATEUR);
			}
		}
		
		private void validerIdentite(String nom, String prenom, BLLException bllException) throws BLLException 
		{
			if(nom.equals("") || nom.trim().length()>30 || prenom.equals("") || prenom.trim().length()>30)
			{
				bllException.ajouterErreur(CodesResultatsBLL.ERREUR_IDENTITE_UTILISATEUR);
			}
		}
		
		private void validerAdresse(String rue, String code_postal, String ville, BLLException bllException) throws BLLException 
		{
			if(rue.equals("") || rue.trim().length()>30
			    || code_postal.equals("") || code_postal.trim().length()>30 || !code_postal.matches("\\d+")
			    || ville.equals("") || ville.trim().length()>30
			   )
			{
				bllException.ajouterErreur(CodesResultatsBLL.ERREUR_ADRESSE_UTILISATEUR);
			}
		}
		
		
		private void validerTelephone(String telephone, BLLException bllException) throws BLLException 
		{
			if(telephone.equals("") || telephone.trim().length()>30 || !telephone.matches("\\d+"))
			{
				bllException.ajouterErreur(CodesResultatsBLL.ERREUR_TELEPHONE_UTILISATEUR);
			}
		}
		
		private void validerMDP(String mot_de_passe1, String mot_de_passe2, BLLException bllException) throws BLLException 
		{
			if(mot_de_passe1.equals("") || mot_de_passe2.equals("")
		       || (!(mot_de_passe1.equals(mot_de_passe2))))
			{
				bllException.ajouterErreur(CodesResultatsBLL.ERREUR_MDP_UTILISATEUR);
			}
		}
		
		public int getUtilisateurID(String pseudo) {
			return 0;
		}
		
		public Utilisateur verifUtilisateur(String identifiant, String motDePasse) throws BLLException, SQLException {
			String mdp;
			Utilisateur user = null; 
			
			if(identifiant.indexOf('@')>0) {
				mdp= this.utilisateurDAO.selectmotDePasseOfEmail(identifiant);
				
				if (mdp != null) {
					if(mdp.equals(motDePasse)) {
						user= this.utilisateurDAO.selectUtilisateurByEmail(identifiant);
						
						
					}
					
				
				
			
				}
				
			}	
			
			 else {
				mdp= this.utilisateurDAO.selectmotDePasseOfPseudo(identifiant);
				System.out.println(mdp);
				if (mdp != null) {
					if(mdp.equals(motDePasse)) {
						user= this.utilisateurDAO.afficherUtilisateurByPseudo(identifiant);
						
					}
					
			
				}
				
			 }
				
			
			return user;
			
			
			
		}
	}