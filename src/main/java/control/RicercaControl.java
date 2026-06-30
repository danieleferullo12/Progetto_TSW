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
import model.ProdottoBean;

@WebServlet("/ricerca")
public class RicercaControl extends HttpServlet {
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
		
		String nome=request.getParameter("search");
		
		if(nome==null || nome.trim().isEmpty()) {
			
			response.sendRedirect(request.getContextPath() + "/prodotto");
			return;
		}
		
		try {
			
			ProdottoBean prodotto=prodottoDao.doRetrieveByName(nome);
			
			if(prodotto!=null) {
				
				request.setAttribute("prodottoCercato", prodotto);
				RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/prodotto.jsp");
				dispatcher.forward(request, response);
				
			}else {
				
				response.sendRedirect(request.getContextPath() + "/prodotto");
			}
			
			
		}catch(SQLException e) {
			 
			 System.err.println("Error:" + e.getMessage());
		 }
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
