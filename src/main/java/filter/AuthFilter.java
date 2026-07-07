package filter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AuthFilter extends HttpFilter {
       
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
			
		HttpSession session=request.getSession(false);
		
		String role=null;
		
		if(session!=null) {
			
			role=(String) session.getAttribute("role");
		}
		
		boolean autorizzato=false;
		
		if(role!=null) {
			
			
				autorizzato=role.equals("admin");
				
		 }
		 if(autorizzato) {
			   
			   chain.doFilter(request, response);
		   }
		   else {
			   
			   response.sendRedirect(request.getContextPath() + "/prodotto");
		   }

	}
}