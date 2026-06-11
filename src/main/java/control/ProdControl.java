package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import model.CarrelloBean;


@WebServlet("/prodotto")
public class ProdControl extends HttpServlet {
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
		 	loadProdList(request);
			
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/WEB-INF/view/index.jsp");
			dispatcher.forward(request,response);
		
		
	}
	
	
	private void loadProdList(HttpServletRequest request) {
		
		String order=request.getParameter("order");
		
		try {
			
			request.setAttribute("prodotti",prodottoDao.doRetrieveAll(order));
			
		}catch(SQLException e) {
			
			System.err.println("Error:" + e.getMessage());
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
