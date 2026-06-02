package dao;

import java.sql.SQLException;
import java.util.Collection;
import it.unisa.storage.model.ProdottoBean;

public interface ProdottoDao extends Dao<ProdottoBean>{
  
	public Collection<ProdottoBean> doRetreveByCategoria(int idCategoria) throws SQLException;
	
	public boolean doUpdateImage(String url)throws SQLException;
	
}
