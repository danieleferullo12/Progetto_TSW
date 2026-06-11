package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.ProdottoBean;

public interface ProdottoDao extends Dao<ProdottoBean>{
  
	public Collection<ProdottoBean> doRetreveByCategoria(int idCategoria) throws SQLException;
	
	
}
