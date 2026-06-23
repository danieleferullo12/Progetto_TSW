package dao;

import java.sql.SQLException;
import java.util.List;

import model.CarrelloBean;

import model.OrdineBean;

public interface OrdineDao extends Dao<OrdineBean>{
	
	public List<OrdineBean> doRetreveByIdUtente(int idUtente) throws SQLException;
	public void doSaveCart(OrdineBean bean, CarrelloBean cart) throws SQLException;

}
