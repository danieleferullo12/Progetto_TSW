package dao;

import java.sql.SQLException;
import java.util.List;

import model.DettaglioOrdineBean;


public interface DettaglioOrdineDao extends Dao<DettaglioOrdineBean>{
	
	
	public List<DettaglioOrdineBean> doRetrieveByIdOrdine(int idOrdine)throws SQLException;
	
}
