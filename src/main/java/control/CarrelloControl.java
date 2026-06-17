package control;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.CarrelloBean;
import dao.ProdottoDao;
import dao.ProdottoDaoImpl;


@WebServlet("/carrello")
public class CarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private ProdottoDao prodottoDao;
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) {
			throw new ServletException("DataSource non disponibile nel contesto");
		}
		prodottoDao = new ProdottoDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		CarrelloBean cart=(CarrelloBean) request.getSession().getAttribute("cart");
		
		if(cart==null) {
			
			cart=new CarrelloBean();
		request.getSession().setAttribute("cart", cart);
		}
		
		actions(request,cart);
		request.getSession().setAttribute("cart", cart);
		
	}
	
	private void actions(HttpServletRequest request,CarrelloBean cart) {
		
		String action=request.getParameter("action");
	try {
		if(action!=null) {
			
			if(action.equalsIgnoreCase("add")) {
				
				addProdtoCart(request,cart);
			}
			
			else if(action.equalsIgnoreCase("remove")) {
				
			    removeProdfromCart(request,cart);
			}
			
		}
	 } catch(SQLException e) {
		 
		 System.err.println("Error:" + e.getMessage());
	 }
	}
	
	private void addProdtoCart(HttpServletRequest request,CarrelloBean cart)throws SQLException {
		
		int code=Integer.parseInt(request.getParameter("code"));
		cart.addProd(prodottoDao.doRetrieveByKey(code));
	}
	
	private void removeProdfromCart(HttpServletRequest request,CarrelloBean cart)throws SQLException {
		
		int code=Integer.parseInt(request.getParameter("code"));
		
		cart.deleteProd(prodottoDao.doRetrieveByKey(code));
	}
	
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
