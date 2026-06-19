package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Logout")
public class LogoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutControl() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String role=(String) request.getSession().getAttribute("role");
		
		if(role!=null) {
			
			request.getSession().invalidate();
		}
		
		response.sendRedirect(request.getContextPath() + "prodotto");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
