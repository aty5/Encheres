package fr.eni.enchere.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.ConnectionProvider;

public class CategorieDAOJDBCImpl implements CategorieDAO{
private static final String SELECT_CATEGORIE_ID = "SELECT libelle FROM categories WHERE no_categorie = ?";
	
	
	public String selectCategorieByID(int no_categorie) throws BLLException {
		String libelle = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE_ID);
			ResultSet rs = pstmt.executeQuery();
			pstmt.setInt(1, no_categorie);
			
			while(rs.next())
			{
				libelle = rs.getString("libelle");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BLLException bllException = new BLLException();
			bllException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIE_ID_ECHEC);
			throw bllException;
		}
		
		return libelle;
	}


}