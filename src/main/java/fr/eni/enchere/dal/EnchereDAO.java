package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bll.BusinessException;
import fr.eni.enchere.bo.Enchere;

public interface EnchereDAO {


	public List<Enchere> select() throws BusinessException;
}