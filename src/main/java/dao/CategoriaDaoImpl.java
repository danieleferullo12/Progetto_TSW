package dao;

import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import model.CategoriaBean;

import java.util.LinkedList;


public class CategoriaDaoImpl implements CategoriaDao{
	
	
	private DataSource ds=null;
	
	public CategoriaDaoImpl(DataSource ds) {
		
		this.ds=ds;
	}
	
	
	@Override
	public void doSave(CategoriaBean bean) throws SQLException{
		
		String insertSQL = "INSERT INTO Categoria (nome,descrizione)VALUES (?,?)";
		
		try(Connection connection = ds.getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, bean.getNome());
            preparedStatement.setString(2, bean.getDescrizione());
           
            
            preparedStatement.executeUpdate();
        }
    }
	
	 @Override
	    public  boolean doDelete(int code) throws SQLException {
	        String deleteSQL = "DELETE FROM Categoria WHERE id_categoria = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
	            preparedStatement.setInt(1, code);
	            int result = preparedStatement.executeUpdate();
	            return result != 0;
	        }
	    }
	 
	 
	 @Override
	public CategoriaBean doRetrieveByKey(int code) throws SQLException{
		 CategoriaBean bean = new CategoriaBean();
	        String selectSQL = "SELECT * FROM Categoria WHERE id_categoria = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
	            preparedStatement.setInt(1, code);
	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                while (rs.next()) {
	                    bean.setIdCategoria(rs.getInt("id_categoria"));
	                    bean.setNome(rs.getString("nome"));
	                    bean.setDescrizione(rs.getString("descrizione"));
	                }     
	            }
	        }
	        return bean;
	 }
	 
	  @Override
	    public List<CategoriaBean> doRetrieveAll(String order) throws SQLException {
	        List<CategoriaBean> categorie = new LinkedList<>();
	        String selectSQL = "SELECT * FROM Categoria";
	        if (order != null && !order.isEmpty()) {
	            selectSQL += " ORDER BY " + order;
	        }
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
	        		ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                CategoriaBean bean = new CategoriaBean();
	                bean.setIdCategoria(rs.getInt("id_categoria"));
                    bean.setNome(rs.getString("nome"));
                    bean.setDescrizione(rs.getString("descrizione"));
	                categorie.add(bean);
	            }
	        }
	        return categorie;
	    }
	  
	  public void doUpdate(CategoriaBean bean) throws SQLException {
		    String sql = "UPDATE Categoria SET nome = ?, descrizione = ? WHERE id_categoria = ?";
		    
		    try (Connection con = ds.getConnection(); 
		         PreparedStatement ps = con.prepareStatement(sql)) {
		        
		        ps.setString(1, bean.getNome());
		        ps.setString(2, bean.getDescrizione());
		        ps.setInt(3, bean.getIdCategoria());
		       
		        
		        ps.executeUpdate();
		    }
		}

}
