package dao;

import it.unisa.storage.model.DettaglioOrdineBean;

import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.LinkedList;

public class DettaglioOrdineImpl implements DettaglioOrdineDao{
	
	
	private DataSource ds=null;
	
	public DettaglioOrdineImpl(DataSource ds) {
		
		this.ds=ds;
	}
   
	
	@Override
	public void doSave(DettaglioOrdineBean bean) throws SQLException{
		
		String insertSQL = "INSERT INTO Dettaglio_Ordine (id_ordine,id_prodotto,quantita,prezzo_unitario)VALUES (?,?,?,?)";
		
		try(Connection connection = ds.getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, bean.getIdOrdine());
            preparedStatement.setInt(2, bean.getIdProdotto());
            preparedStatement.setInt(3, bean.getQuantita());
            preparedStatement.setDouble(4, bean.getPrezzoUnitario());
            
            preparedStatement.executeUpdate();
        }
    }
	
	 @Override
	    public  boolean doDelete(int code) throws SQLException {
	        String deleteSQL = "DELETE FROM Dettaglio_Ordine WHERE id_dettaglio = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
	            preparedStatement.setInt(1, code);
	            int result = preparedStatement.executeUpdate();
	            return result != 0;
	        }
	    }
	 
	 
	 @Override
	public DettaglioOrdineBean doRetrieveByKey(int code) throws SQLException{
		 DettaglioOrdineBean bean = new DettaglioOrdineBean();
	        String selectSQL = "SELECT * FROM Dettaglio_Ordine WHERE id_dettaglio = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
	            preparedStatement.setInt(1, code);
	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                while (rs.next()) {
	                    bean.setIdDettaglio(rs.getInt("id_dettaglio"));
	                    bean.setIdOrdine(rs.getInt("id_ordine"));
	                    bean.setIdProdotto(rs.getInt("id_prodotto"));
	                    bean.setQuantita(rs.getInt("quantita"));
	                    bean.setPrezzoUnitario(rs.getDouble("prezzo_unitario"));
	                    
	                }
	            }
	        }
	        return bean;
	 }
	 
	  @Override
	    public List<DettaglioOrdineBean> doRetrieveAll(String order) throws SQLException {
	        List<DettaglioOrdineBean> dettagli = new LinkedList<>();
	        String selectSQL = "SELECT * FROM Dettaglio_Ordine";
	        if (order != null && !order.isEmpty()) {
	            selectSQL += " ORDER BY " + order;
	        }
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
	        		ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                DettaglioOrdineBean bean = new DettaglioOrdineBean();
	                bean.setIdDettaglio(rs.getInt("id_dettaglio"));
                    bean.setIdOrdine(rs.getInt("id_ordine"));
                    bean.setIdProdotto(rs.getInt("id_prodotto"));
                    bean.setQuantita(rs.getInt("quantita"));
                    bean.setPrezzoUnitario(rs.getDouble("prezzo_unitario"));
	                dettagli.add(bean);
	            }
	        }
	        return dettagli;
	    }
	  
	  public void doUpdate(DettaglioOrdineBean bean) throws SQLException {
		    String sql = "UPDATE Utente SET id_ordine = ?, id_prodotto = ?, quantita = ?, prezzo_unitario=? WHERE id_dettaglio = ?";
		    
		    try (Connection con = ds.getConnection(); 
		         PreparedStatement ps = con.prepareStatement(sql)) {
		        
		        ps.setInt(1, bean.getIdOrdine());
		        ps.setInt(2, bean.getIdProdotto());
		        ps.setInt(3, bean.getQuantita());
		        ps.setDouble(4, bean.getPrezzoUnitario());
		        ps.setInt(5, bean.getIdDettaglio()); 
		        
		        
		        ps.executeUpdate();
		    }
		}
	  
}
