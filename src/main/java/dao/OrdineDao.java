package dao;

import java.sql.SQLException;
import model.CarrelloBean;

import model.OrdineBean;

public interface OrdineDao extends Dao<OrdineBean>{
	
	public OrdineBean doRetreveByIdUtente(int idUtente) throws SQLException;
	public void doSaveCart(OrdineBean bean, CarrelloBean cart) throws SQLException;

}
