package dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import model.OrdineBean;
import model.CarrelloBean;
import model.ElementoCarBean;
import model.ProdottoBean;


import java.util.LinkedList;

public class OrdineDaoImpl implements OrdineDao{
	
	
	private DataSource ds=null;
	
	public OrdineDaoImpl(DataSource ds) {
		
		this.ds=ds;
	}
	
	@Override
	public void doSave(OrdineBean bean) throws SQLException{
		
		String insertSQL = "INSERT INTO Ordine (data_ordine,stato_ordine,totale,id_utente)VALUES (?,?,?,?)";
		
		
		
		try(Connection connection = ds.getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setDate(1, bean.getDataOrdine());
            preparedStatement.setString(2, bean.getStato());
            preparedStatement.setDouble(3, bean.getTotale());
            preparedStatement.setInt(4, bean.getIdUtente());
            
            preparedStatement.executeUpdate();
               
        }
    }
	
public void doSaveCart(OrdineBean bean,CarrelloBean cart) throws SQLException{
		
		String insertSQL = "INSERT INTO Ordine (data_ordine,stato_ordine,totale,id_utente)VALUES (?,?,?,?)";
		String dettaglioSQL="INSERT INTO Dettaglio_Ordine (id_ordine,id_prodotto,quantita,prezzo_unitario)VALUES (?,?,?,?)";
		
		
		try(Connection connection = ds.getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, bean.getDataOrdine());
            preparedStatement.setString(2, bean.getStato());
            preparedStatement.setDouble(3, bean.getTotale());
            preparedStatement.setInt(4, bean.getIdUtente());
            
            preparedStatement.executeUpdate();
            
            int idOrdineGenerato = -1;
            ResultSet rs=preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                idOrdineGenerato = rs.getInt(1); 
            }
            
            PreparedStatement psDettaglio=connection.prepareStatement(dettaglioSQL);
            List<ElementoCarBean> prodotti = cart.getProd();
               
            for (ElementoCarBean elem : prodotti) {
                ProdottoBean p = elem.getProdotto();
                
                psDettaglio.setInt(1, idOrdineGenerato);     
                psDettaglio.setInt(2, p.getIdProdotto());
                psDettaglio.setInt(3, elem.getQuant()); 
                psDettaglio.setDouble(4, p.getPrezzo());      
                      
                
                psDettaglio.executeUpdate();
            }
        }
    }
	
	 @Override
	    public  boolean doDelete(int code) throws SQLException {
	        String deleteSQL = "DELETE FROM Ordine WHERE id_ordine = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
	            preparedStatement.setInt(1, code);
	            int result = preparedStatement.executeUpdate();
	            return result != 0;
	        }
	    }
	 
	 
	 @Override
	public OrdineBean doRetrieveByKey(int code) throws SQLException{
		 OrdineBean bean = new OrdineBean();
	        String selectSQL = "SELECT * FROM Ordine WHERE id_ordine = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
	            preparedStatement.setInt(1, code);
	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                while (rs.next()) {
	                    bean.setIdOrdine(rs.getInt("id_ordine"));
	                    bean.setDataOrdine(rs.getDate("data_ordine"));
	                    bean.setStato(rs.getString("stato_ordine"));
	                    bean.setTotale(rs.getDouble("totale"));
	                    bean.setIdUtente(rs.getInt("id_utente"));
	                   
	                }
	            }
	        }
	        return bean;
	 }
	 
	  @Override
	    public List<OrdineBean> doRetrieveAll(String order) throws SQLException {
	        List<OrdineBean> ordini = new LinkedList<>();
	        String selectSQL = "SELECT * FROM Ordine";
	        if (order != null && !order.isEmpty()) {
	            selectSQL += " ORDER BY " + order;
	        }
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
	        		ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                OrdineBean bean = new OrdineBean();
	                bean.setIdOrdine(rs.getInt("id_ordine"));
                    bean.setDataOrdine(rs.getDate("data_ordine"));
                    bean.setStato(rs.getString("stato_ordine"));
                    bean.setTotale(rs.getDouble("totale"));
                    bean.setIdUtente(rs.getInt("id_utente"));
	                ordini.add(bean);
	            }
	        }
	        return ordini;
	    }
	  
	  public void doUpdate(OrdineBean bean) throws SQLException {
		    String sql = "UPDATE Ordine SET data_ordine = ?, stato_ordine = ?, totale = ?   WHERE id_ordine = ?";
		    
		    try (Connection con = ds.getConnection(); 
		         PreparedStatement ps = con.prepareStatement(sql)) {
		        
		        ps.setDate(1, bean.getDataOrdine());
		        ps.setString(2, bean.getStato());
		        ps.setDouble(3, bean.getTotale());
		        ps.setInt(4, bean.getIdOrdine());
		        
		        
		        ps.executeUpdate();
		    }
		}
	  
	  public List<OrdineBean> doRetreveByIdUtente(int idUtente) throws SQLException{
		  
		  List<OrdineBean> ordini = new LinkedList<>();
		  String selectSQL="SELECT * FROM Ordine WHERE id_utente=?";
		  
		  try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
	            preparedStatement.setInt(1, idUtente);
	         
	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                while (rs.next()) {
	                	
	                OrdineBean	bean= new OrdineBean();
	                	
	                	 bean.setIdOrdine(rs.getInt("id_ordine"));
		                 bean.setDataOrdine(rs.getDate("data_ordine"));
		                 bean.setStato(rs.getString("stato_ordine"));
		                 bean.setTotale(rs.getDouble("totale"));
		                 bean.setIdUtente(rs.getInt("id_utente"));
		                 ordini.add(bean);
	                }
	            }
	        }
	        return ordini;  
		  
	  }

}
