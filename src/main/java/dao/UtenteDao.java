package dao;

import java.sql.SQLException;

import model.UtenteBean;

public interface UtenteDao extends Dao<UtenteBean>{
	
	public UtenteBean doRetreveByEmailPass(String email, String password) throws SQLException;
   
}
