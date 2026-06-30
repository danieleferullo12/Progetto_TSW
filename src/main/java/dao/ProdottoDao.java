package dao;

import java.sql.SQLException;

import java.util.List;

import model.ProdottoBean;

public interface ProdottoDao extends Dao<ProdottoBean>{
  
	public List<ProdottoBean> doRetreveByCategoria(int idCategoria) throws SQLException;
	public List<ProdottoBean> doRetrieveLatest(int limit)throws SQLException;
	public ProdottoBean doRetrieveByName(String name)throws SQLException;
	
	
}
