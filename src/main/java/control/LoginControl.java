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
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.UtenteBean;
import dao.UtenteDao;
import dao.UtenteDaoImpl;


@WebServlet("/login")
public class LoginControl extends HttpServlet {
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
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
	    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		List<String> errors=new ArrayList<String>();
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		
		email=validateField(email,"E-mail",errors);
		pass=validateField(pass,"Password",errors);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		
		if(!errors.isEmpty()) {
			
			request.setAttribute("errors",errors);
			dispatcher.forward(request, response);
			return;
		}
		 
		else {
			try {
			loginCheck(request,response,email,pass,errors);
			}catch(SQLException e ) {
				
				 System.err.println("Error:" + e.getMessage());
			}
		}
		
	}
	
	private void loginCheck(HttpServletRequest request,HttpServletResponse response,String email,String pass,List<String> errors)throws SQLException,IOException,ServletException{
		
		UtenteBean utente=utenteDao.doRetreveByEmailPass(email, pass);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		
		if(utente!=null) {
			
			if(utente.getEmail().equals("danieleferullo10@gmail.com")&&utente.getPasswordHash().equals("123")) {
				
				request.getSession().setAttribute("role","admin");
				response.sendRedirect(request.getContextPath() + "/admin/areaRiservata");
				
			}
			
			else {
				
				request.getSession().setAttribute("role","client");
				request.getSession().setAttribute("utente", utente);
				response.sendRedirect(request.getContextPath() + "/prodotto");
				
			}
			
			
		}else {
			
			errors.add("Username o Password non validi!");
			request.setAttribute("errors",errors);
			dispatcher.forward(request, response);
		}
		
		
	}
	
	
	private String validateField(String val,String field,List<String> errors) {
		
		if(val==null|| val.trim().isEmpty()) {
			
			errors.add(field +" "+ "non inserita");
			return "";
		}
		return val.trim();
	}
	
}
