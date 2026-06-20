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

import dao.UtenteDao;
import dao.UtenteDaoImpl;
import model.UtenteBean;

@WebServlet("/registrazione")
public class RegControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtenteDao utenteDao;
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) {
			throw new ServletException("DataSource non disponibile nel contesto");
		}
		utenteDao = new UtenteDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp");
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		UtenteBean utente=new UtenteBean();
		
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		String tel=request.getParameter("telefono");
		String ind=request.getParameter("indirizzo");
		
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setEmail(email);
		utente.setPasswordHash(pass);
		utente.setTelefono(tel);
		utente.setIndirizzoSpedizione(ind);
		
		try {
			
			regUtente(utente);
			
			response.sendRedirect(request.getContextPath() + "/login");
			
		}catch(SQLException e) {
			
			System.err.println("Err:" + e.getMessage());
			
			request.setAttribute("errorReg","E-mail inserita già in uso");
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}
	
	
	private void regUtente(UtenteBean utente)throws SQLException{
		
		utenteDao.doSave(utente);
		
	}

}
