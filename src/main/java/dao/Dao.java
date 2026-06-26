package dao;

import java.sql.SQLException;

import java.util.List;

public interface Dao <T>{
	
	public void doSave(T bean) throws SQLException;
	
	public void doUpdate(T bean) throws SQLException;
	
	public boolean doDelete(int code) throws SQLException;
	
    public T doRetrieveByKey(int code) throws SQLException;
	
	public List<T> doRetrieveAll(String order) throws SQLException;


}
