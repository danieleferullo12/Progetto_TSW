package dao;

import it.unisa.storage.model.OrdineBean;
import java.sql.SQLException;

public interface OrdineDao extends Dao<OrdineBean>{
	
	public OrdineDao doRetreveByIdUtente(int idUtente) throws SQLException;

}
