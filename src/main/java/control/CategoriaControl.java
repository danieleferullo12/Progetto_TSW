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
import java.util.List;

import javax.sql.DataSource;

import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import model.ProdottoBean;

@WebServlet("/categoria")
public class CategoriaControl extends HttpServlet {
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
		
		String categoria=request.getParameter("categoria");
		
		try {
			
			List<ProdottoBean> prodotti;
			
			if(categoria!=null) {
				
				int idCategoria=Integer.parseInt(categoria);
				
				prodotti=prodottoDao.doRetreveByCategoria(idCategoria);
				
			}else {
				
				prodotti = prodottoDao.doRetrieveAll(null);
			}
			
			 request.setAttribute("Listaprodotti", prodotti);
			 RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/categoria.jsp");
			 dispatcher.forward(request, response);
			
		}catch(SQLException e) {
			
			System.err.println("Error:" + e.getMessage());
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
