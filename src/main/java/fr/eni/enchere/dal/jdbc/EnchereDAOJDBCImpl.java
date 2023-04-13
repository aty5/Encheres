package fr.eni.enchere.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bll.BusinessException;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.EnchereDAO;

public class EnchereDAOJDBCImpl implements EnchereDAO{
	
	private static final String SELECT_ENCHERE = "SELECT "+
												" u.no_utilisateur as no_utilisateur,"
												+"a.no_article as no_utilisateur,"
												+"date_enchere, montant_enchere, id_enchere"
												+"FROM ENCHERES e"
												+"INNER JOIN ENCHERE e ON e.no_article=a.no_article,"
												+"INNER JOIN ENCHERE e ON e.no_utilisateur=u.no_utilisateur";
	

	@Override
	public List<Enchere> select() throws BusinessException{
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE);
			ResultSet rs = pstmt.executeQuery();
			Enchere enchere = new Enchere();
			while(rs.next())
			{
				if(rs.getInt("id_enchere")!= enchere.getIdEnchere())
				{
					enchere = enchereBuilder(rs);
					listeEnchere.add(enchere);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			//BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResulstatsDAL.);
		}
		return listeEnchere;
	}


	private Enchere enchereBuilder(ResultSet rs) {
		Enchere enchere = new Enchere ();
		try {
			enchere.setIdEnchere(rs.getInt(PreparedStatement.RETURN_GENERATED_KEYS));
			enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
			enchere.setIdArticle(rs.getInt("no_article"));
			enchere.setIdACheteur(rs.getInt("no_utilisateur"));
			enchere.setMontantEnchere(rs.getInt("montant_enchere"));
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return enchere;
	}

}