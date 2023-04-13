package fr.eni.enchere.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.CodesResultatsDAL;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {

	private static final String AJOUT_UTILISATEUR = "insert into utilisateurs "
			+ "(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,"
			+ "credit, administrateur) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?);";
	
	private static final String SELECT_UTILISATEUR_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
	
	private static final String AFFICHER_UTILISATEUR = "SELECT no_utilisateur, pseudo, nom, prenom, email,"
			+ "telephone, rue, code_postal, ville FROM UTILISATEURS WHERE pseudo = ?";
	
	private static final String MODIFIER_UTILISATEUR = "UPDATE utilisateurs SET pseudo = ?, nom = ?, prenom = ?, email = ?,"
			+ "telephone = ?, rue = ?, code_postal = ?, ville = ? WHERE no_utilisateur = ?;";

	
	
	
	
	/*public int trouverIdUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
            String rue, String codePostal, String ville) throws SQLException {
int idUtilisateur = 0;
String TROUVER_ID_UTILISATEUR = "SELECT idUtilisateur FROM utilisateurs WHERE pseudo = ? AND nom = ? AND prenom = ? " +
                 "AND email = ? AND telephone = ? AND rue = ? AND codePostal = ? AND ville = ?";
try (Connection connection = ConnectionProvider.getConnection();
PreparedStatement statement = connection.prepareStatement(TROUVER_ID_UTILISATEUR)) {
statement.setString(1, pseudo);
statement.setString(2, nom);
statement.setString(3, prenom);
statement.setString(4, email);
statement.setString(5, telephone);
statement.setString(6, rue);
statement.setString(7, codePostal);
statement.setString(8, ville);
ResultSet resultSet = statement.executeQuery();
if (resultSet.next()) {
idUtilisateur = resultSet.getInt("idUtilisateur");
}
}
return idUtilisateur;
}*/

	public void modifierUtilisateur (Utilisateur utilisateur) throws BLLException, SQLException {
		

		try (Connection connection = ConnectionProvider.getConnection();
		     
			
			PreparedStatement statement = connection.prepareStatement(MODIFIER_UTILISATEUR)) {

		    statement.setString(1, utilisateur.getPseudo());
		    statement.setString(2, utilisateur.getNom());
		    statement.setString(3, utilisateur.getPrenom());
		    statement.setString(4, utilisateur.getEmail());
		    statement.setString(5, utilisateur.getTelephone());
		    statement.setString(6, utilisateur.getRue());
		    statement.setString(7, utilisateur.getcodePostal());
		    statement.setString(8, utilisateur.getVille());
		    //statement.setString(9, utilisateur.getMdp());
		    statement.setInt(9, utilisateur.getIdUtilisateur());
		    
		    int rowsUpdated = statement.executeUpdate();
		    if (rowsUpdated > 0) {
		        System.out.println("L'utilisateur a été mis à jour avec succès dans la base de données");
		    } else {
		        System.out.println("Aucune ligne n'a été mise à jour dans la base de données");
		    }}
		 catch (SQLException e) {
		    System.out.println("Erreur lors de la mise à jour de l'utilisateur dans la base de données : " + e.getMessage());
		}
		
	}
		
	
	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) throws BLLException 
	{

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(AJOUT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getcodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMdp());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setInt(11, utilisateur.getAdministrateur());
			
			pstmt.executeUpdate();
			
			
			ResultSet resultSet = pstmt.getGeneratedKeys();
	        if (resultSet.next()) {
	        	 int generatedId = resultSet.getInt(1);
	        	    System.out.println("Generated ID: " + generatedId);
	            utilisateur.setIdUtilisateur(resultSet.getInt(1));
	            System.out.println("User ID: " + utilisateur.getIdUtilisateur());
	        }
	        System.out.println("User ID: " + utilisateur.getIdUtilisateur());
		
		} 
		
		catch (Exception e) 
		{
			BLLException businessException = new BLLException();
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatsDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		System.out.println("User ID: " + utilisateur.getIdUtilisateur());
	}
	
	// implémentation de la méthode pour vérifier que le pseudo est unique
	public boolean verificationPseudoUnique(String pseudo) throws BLLException 
	{
		boolean i = false;
		BLLException bllexception = new BLLException();
		
		try 
		{
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement("select * from UTILISATEURS WHERE pseudo = ?;");
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
			bllexception.printStackTrace();
			bllexception.getListeCodesErreur();
			//System.out.println("Ce pseudo est déjà utilisé.");
			} 
			else 
			{
				i = true;
			}

		} 
		    catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return i;
			}
			
	// implémentation de la méthode pour vérifier que l'email est unique
	public boolean verificationEmailUnique(String email) 
	{
		boolean i = false;

		try 
		{
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement("select * from UTILISATEURS WHERE email = ?;");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) 
			{
				System.out.println("Cet email est déjà utilisé.");
			} 
			else 
			{
				i = true;
			}

		} 
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return i;
	}
	
	
	
public Utilisateur selectUtilisateurByID(int idUtilisateur) throws BLLException {
		
		Utilisateur utilisateur = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_ID);
			pstmt.setInt(1, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				utilisateur = new Utilisateur(rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 
						rs.getString(9), rs.getString(10));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BLLException bllException = new BLLException();
			bllException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ID_ECHEC);
			throw bllException;
		}
		
		return utilisateur;
		
	}

	
	

	public Utilisateur afficherUtilisateurByPseudo(String pseudo) throws BLLException, SQLException
	{
		   Utilisateur utilisateur = null;
		   try (Connection cnx = ConnectionProvider.getConnection()) 
		   {
		      PreparedStatement pstmt = cnx.prepareStatement(AFFICHER_UTILISATEUR);
		      pstmt.setString(1, pseudo);
		      ResultSet rs = pstmt.executeQuery();
		      while (rs.next()) 
		      {
		         int noUtilisateur = rs.getInt("no_utilisateur");
		    	 pseudo = rs.getString("pseudo"); // à mettre ?
		         String nom = rs.getString("nom");
		         String prenom = rs.getString("prenom");
		         String email = rs.getString("email");
		         String telephone = rs.getString("telephone");
		         String rue = rs.getString("rue");
		         String code_postal = rs.getString("code_postal");
		         String ville = rs.getString("ville");
		         utilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville);
		      }
		   }
		   return utilisateur;
	}
	
	@Override
	public Utilisateur selectUtilisateurByEmail(String email) {
		Utilisateur user = null;
		String sqlrequest = "SELECT * FROM utilisateurs WHERE email = ?;";
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlrequest);
			statement.setString(1, email);
			ResultSet rs= statement.executeQuery();
			while(rs.next()) {
				user= new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
						rs.getString(7), rs.getString(8), rs.getString(9));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}


	@Override
	public Utilisateur trouverUtilisateur(String login, String motDePasse) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String selectmotDePasseOfEmail(String email) {
		String mdp = null;
		String sqlrequest="select mot_de_passe FROM utilisateurs WHERE email = ? ";
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(sqlrequest);
			
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				mdp = rs.getString("mot_de_passe");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return mdp;		
	}


	@Override
	public String selectmotDePasseOfPseudo(String pseudo) {
		String mdp = null;
		String sqlrequest="select mot_de_passe FROM utilisateurs WHERE pseudo = ?;";
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(sqlrequest);
			
			pstmt.setString(1, pseudo);
		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				mdp = rs.getString("mot_de_passe");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return mdp;
	}


	


	
	
		


}