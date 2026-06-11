package dao;

import java.sql.SQLException;

import model.OrdineBean;

public interface OrdineDao extends Dao<OrdineBean>{
	
	public OrdineBean doRetreveByIdUtente(int idUtente) throws SQLException;

}
