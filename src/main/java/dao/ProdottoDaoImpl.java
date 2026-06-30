package dao;

import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import model.ProdottoBean;

import java.util.LinkedList;

public class ProdottoDaoImpl implements ProdottoDao{
	
	private DataSource ds=null;
	
	public ProdottoDaoImpl(DataSource ds) {
		
		this.ds=ds;
	}
   
	@Override
	public void doSave(ProdottoBean bean) throws SQLException{
		
		String insertSQL = "INSERT INTO Prodotto (nome,descrizione,prezzo,quantita_disponibile,immagine_url,id_categoria)VALUES (?,?,?,?,?,?)";
		
		try(Connection connection = ds.getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, bean.getNome());
            preparedStatement.setString(2, bean.getDescrizione());
            preparedStatement.setDouble(3, bean.getPrezzo());
            preparedStatement.setInt(4, bean.getQuantitaDisp());
            preparedStatement.setString(5, bean.getImmagineUrl());
            preparedStatement.setInt(6, bean.getIdCategoria());
            
            
            preparedStatement.executeUpdate();
        }
    }
	
	 @Override
	    public  boolean doDelete(int code) throws SQLException {
	        String deleteSQL = "DELETE FROM Prodotto WHERE id_prodotto = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
	            preparedStatement.setInt(1, code);
	            int result = preparedStatement.executeUpdate();
	            return result != 0;
	        }
	    }
	 
	 
	 @Override
	public ProdottoBean doRetrieveByKey(int code) throws SQLException{
		 ProdottoBean bean = new ProdottoBean();
	        String selectSQL = "SELECT * FROM Prodotto WHERE id_prodotto = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
	            preparedStatement.setInt(1, code);
	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                while (rs.next()) {
	                    bean.setIdProdotto(rs.getInt("id_prodotto"));
	                    bean.setNome(rs.getString("nome"));
	                    bean.setDescrizione(rs.getString("descrizione"));
	                    bean.setPrezzo(rs.getDouble("prezzo"));
	                    bean.setQuantitaDisp(rs.getInt("quantita_disponibile"));
	                    bean.setImmagineUrl(rs.getString("immagine_url"));
	                    bean.setIdCategoria(rs.getInt("id_categoria"));
	                    
	                }
	            }
	        }
	        return bean;
	 }
	 
	 public List<ProdottoBean> doRetrieveLatest(int limit)throws SQLException{
		 
		 List<ProdottoBean> prodotti = new LinkedList<>();
	        String selectSQL ="SELECT * FROM Prodotto ORDER BY id_prodotto DESC LIMIT ?";
	        
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
	        		preparedStatement.setInt(1,limit);	
	        			
	        	try(ResultSet rs = preparedStatement.executeQuery()){ 
	            while (rs.next()) {
	                ProdottoBean bean = new ProdottoBean();
	                bean.setIdProdotto(rs.getInt("id_prodotto"));
               bean.setNome(rs.getString("nome"));
               bean.setDescrizione(rs.getString("descrizione"));
               bean.setPrezzo(rs.getDouble("prezzo"));
               bean.setQuantitaDisp(rs.getInt("quantita_disponibile"));
               bean.setImmagineUrl(rs.getString("immagine_url"));
               bean.setIdCategoria(rs.getInt("id_categoria"));
	            prodotti.add(bean);
	            }
	        }
	     } 	
	        return prodotti;    
		
	 }
	 
	 public ProdottoBean doRetrieveByName(String nome)throws SQLException{
		 
		 String sql = "SELECT * FROM prodotto WHERE nome LIKE ? LIMIT 1";
		  
		 ProdottoBean bean=null;
		 
		    try (Connection con = ds.getConnection(); 
		         PreparedStatement ps = con.prepareStatement(sql)) {
		        
		        ps.setString(1, "%" + nome + "%"); 
		        
		        try (ResultSet rs = ps.executeQuery()) {
		            if (rs.next()) {
		            	bean=new ProdottoBean();
		                bean.setIdProdotto(rs.getInt("id_prodotto"));
	                    bean.setNome(rs.getString("nome"));
	                    bean.setDescrizione(rs.getString("descrizione"));
	                    bean.setPrezzo(rs.getDouble("prezzo"));
	                    bean.setQuantitaDisp(rs.getInt("quantita_disponibile"));
	                    bean.setImmagineUrl(rs.getString("immagine_url"));
	                    bean.setIdCategoria(rs.getInt("id_categoria"));
		    
		            }
		        }
		    }
		    return bean;
	 }
	 
	 
	  @Override
	    public List<ProdottoBean> doRetrieveAll(String order) throws SQLException {
	        List<ProdottoBean> prodotti = new LinkedList<>();
	        String selectSQL = "SELECT * FROM Prodotto";
	        if (order != null && !order.isEmpty()) {
	            selectSQL += " ORDER BY " + order;
	        }
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
	        		ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                ProdottoBean bean = new ProdottoBean();
	                bean.setIdProdotto(rs.getInt("id_prodotto"));
                    bean.setNome(rs.getString("nome"));
                    bean.setDescrizione(rs.getString("descrizione"));
                    bean.setPrezzo(rs.getDouble("prezzo"));
                    bean.setQuantitaDisp(rs.getInt("quantita_disponibile"));
                    bean.setImmagineUrl(rs.getString("immagine_url"));
                    bean.setIdCategoria(rs.getInt("id_categoria"));
	                prodotti.add(bean);
	            }
	        }
	        return prodotti;
	    }
	
	  public List<ProdottoBean> doRetreveByCategoria(int idCategoria) throws SQLException{
		  
		  List<ProdottoBean> prodotti = new LinkedList<>();
	        String selectSQL = "SELECT * FROM Prodotto WHERE id_categoria=?";
	        
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
	        		preparedStatement.setInt(1, idCategoria);	
	        			
	        	try(ResultSet rs = preparedStatement.executeQuery()){ 
	            while (rs.next()) {
	                ProdottoBean bean = new ProdottoBean();
	                bean.setIdProdotto(rs.getInt("id_prodotto"));
                  bean.setNome(rs.getString("nome"));
                  bean.setDescrizione(rs.getString("descrizione"));
                  bean.setPrezzo(rs.getDouble("prezzo"));
                  bean.setQuantitaDisp(rs.getInt("quantita_disponibile"));
                  bean.setImmagineUrl(rs.getString("immagine_url"));
                  bean.setIdCategoria(rs.getInt("id_categoria"));
	                prodotti.add(bean);
	            }
	        }
	     } 	
	        return prodotti;
		    
	  }
	  
	  public void doUpdate(ProdottoBean bean) throws SQLException {
		    String sql = "UPDATE Prodotto SET nome = ?,  prezzo = ?, "
		               + "quantita_disponibile = ?, immagine_url = ?, id_categoria = ? WHERE id_prodotto = ?";
		    
		    try (Connection con = ds.getConnection(); 
		         PreparedStatement ps = con.prepareStatement(sql)) {
		        
		        ps.setString(1, bean.getNome());
		        ps.setDouble(2, bean.getPrezzo());
		        ps.setInt(3, bean.getQuantitaDisp());
		        ps.setString(4, bean.getImmagineUrl()); 
		        ps.setInt(5, bean.getIdCategoria());
		        ps.setInt(6, bean.getIdProdotto()); 
		        
		        ps.executeUpdate();
		    }
		}
	
}
