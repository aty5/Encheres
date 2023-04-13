package fr.eni.enchere.dal;

import fr.eni.enchere.bll.BLLException;

public interface CategorieDAO {
	
	public String selectCategorieByID(int no_categorie) throws BLLException;
}