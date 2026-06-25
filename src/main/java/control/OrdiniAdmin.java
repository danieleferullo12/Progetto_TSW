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

import model.OrdineBean;
import dao.OrdineDao;
import dao.OrdineDaoImpl;

@WebServlet("/admin/OrdiniAdmin")
public class OrdiniAdmin extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private OrdineDao ordineDao;
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) {
			throw new ServletException("DataSource non disponibile nel contesto");
		}
		ordineDao = new OrdineDaoImpl(ds);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			List<OrdineBean> tuttiGliOrdini=ordineDao.doRetrieveAllWithDetails();
			request.setAttribute("tuttiGliOrdini", tuttiGliOrdini);
		}catch(SQLException e) {
			
			
			System.err.println("Error:" + e.getMessage());
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/admin/gestioneOrdini.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
