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

import dao.OrdineDao;
import dao.OrdineDaoImpl;
import model.UtenteBean;
import model.OrdineBean;

@WebServlet("/common/profilo")
public class ProfiloControl extends HttpServlet {
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
		
		UtenteBean utente=(UtenteBean) request.getSession().getAttribute("utente");
		int idUtente=utente.getIdUtente();
		
	
		
		try {
			
			getOrder(request,idUtente);
		}catch(SQLException e) {
			
			
			 System.err.println("Error:" + e.getMessage());	
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB_INF/view/common/profilo.jsp");
		dispatcher.forward(request, response);
	}

	
	private void getOrder(HttpServletRequest request,int idUtente)throws SQLException {
		
		List<OrdineBean> ordini=ordineDao.doRetreveByIdUtente(idUtente);
		
		request.getSession().setAttribute("ordini", ordini);
		
	}
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
