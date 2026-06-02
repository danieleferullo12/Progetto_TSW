package dao;

import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.LinkedList;

import it.unisa.storage.model.UtenteBean;

public class UtenteDaoImpl implements UtenteDao{

	private DataSource ds=null;
	
	public UtenteDaoImpl(DataSource ds) {
		
		this.ds=ds;
		
	}
	@Override
	public void doSave(UtenteBean bean) throws SQLException{
		
		String insertSQL = "INSERT INTO Utente (nome,cognome,email,password_hash,ruolo,indirizzo_spedizione,telefono)VALUES (?,?,?,?,?,?,?,?,?)";
		
		try(Connection connection = ds.getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, bean.getNome());
            preparedStatement.setString(2, bean.getCognome());
            preparedStatement.setString(3, bean.getEmail());
            preparedStatement.setString(4, bean.getPasswordHash());
            preparedStatement.setString(5, bean.getRuolo());
            preparedStatement.setString(6, bean.getIndirizzoSpedizione());
            preparedStatement.setString(7, bean.getTelefono());
            
            preparedStatement.executeUpdate();
        }
    }
	
	 @Override
	    public  boolean doDelete(int code) throws SQLException {
	        String deleteSQL = "DELETE FROM Utente WHERE code = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
	            preparedStatement.setInt(1, code);
	            int result = preparedStatement.executeUpdate();
	            return result != 0;
	        }
	    }
	 
	 
	 @Override
	public UtenteBean doRetrieveByKey(int code) throws SQLException{
		 UtenteBean bean = new UtenteBean();
	        String selectSQL = "SELECT * FROM Utente WHERE code = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
	            preparedStatement.setInt(1, code);
	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                while (rs.next()) {
	                    bean.setIdUtente(rs.getInt("id_utente"));
	                    bean.setNome(rs.getString("nome"));
	                    bean.setCognome(rs.getString("cognome"));
	                    bean.setEmail(rs.getString("email"));
	                    bean.setPasswordHash(rs.getString("password_hash"));
	                    bean.setRuolo(rs.getString("ruolo"));
	                    bean.setIndirizzoSpedizione(rs.getString("indirizzo_spedizione"));
	                    bean.setTelefono(rs.getString("telefono"));
	                }
	            }
	        }
	        return bean;
	 }
	 
	  @Override
	    public List<UtenteBean> doRetrieveAll(String order) throws SQLException {
	        List<UtenteBean> utenti = new LinkedList<>();
	        String selectSQL = "SELECT * FROM Utente";
	        if (order != null && !order.isEmpty()) {
	            selectSQL += " ORDER BY " + order;
	        }
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
	        		ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                UtenteBean bean = new UtenteBean();
	                bean.setIdUtente(rs.getInt("id_utente"));
                    bean.setNome(rs.getString("nome"));
                    bean.setCognome(rs.getString("cognome"));
                    bean.setEmail(rs.getString("email"));
                    bean.setPasswordHash(rs.getString("password_hash"));
                    bean.setRuolo(rs.getString("ruolo"));
                    bean.setIndirizzoSpedizione(rs.getString("indirizzo_spedizione"));
                    bean.setTelefono(rs.getString("telefono"));
	                utenti.add(bean);
	            }
	        }
	        return utenti;
	    }
	  @Override
	  public UtenteBean doRetreveByEmailPass(String email, String password) throws SQLException{
		  
		  UtenteBean bean=null;
		  String selectSQL="SELECT * FROM Utente WHERE email=? AND password_hash=?";
		  
		  try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
	            preparedStatement.setString(1, email);
	            preparedStatement.setString(2,password);
	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                if (rs.next()) {
	                	
	                	bean= new UtenteBean();
	                	
	                    bean.setIdUtente(rs.getInt("id_utente"));
	                    bean.setNome(rs.getString("nome"));
	                    bean.setCognome(rs.getString("cognome"));
	                    bean.setEmail(rs.getString("email"));
	                    bean.setPasswordHash(rs.getString("password_hash"));
	                    bean.setRuolo(rs.getString("ruolo"));
	                    bean.setIndirizzoSpedizione(rs.getString("indirizzo_spedizione"));
	                    bean.setTelefono(rs.getString("telefono"));
	                }
	            }
	        }
	        return bean;  
	  }
	
	  public void doUpdate(UtenteBean bean) throws SQLException {
		    String sql = "UPDATE Utente SET nome = ?, cognome = ?, email = ?, "
		               + "password_hash = ?, ruolo = ?, indirizzo_spedizione = ?, telefono=? WHERE id_utente = ?";
		    
		    try (Connection con = ds.getConnection(); 
		         PreparedStatement ps = con.prepareStatement(sql)) {
		        
		        ps.setString(1, bean.getNome());
		        ps.setString(2, bean.getCognome());
		        ps.setString(3, bean.getEmail());
		        ps.setString(4, bean.getPasswordHash());
		        ps.setString(5, bean.getRuolo()); 
		        ps.setString(6, bean.getIndirizzoSpedizione());
		        ps.setString(7, bean.getTelefono());
		        ps.setInt(8,bean.getIdUtente());
		        
		        ps.executeUpdate();
		    }
		}
}
