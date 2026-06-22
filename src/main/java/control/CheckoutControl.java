package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import dao.OrdineDao;
import dao.OrdineDaoImpl;
import model.CarrelloBean;
import model.ElementoCarBean;
import model.UtenteBean;
import model.ProdottoBean;
import model.OrdineBean;


@WebServlet("/common/checkout")
public class CheckoutControl extends HttpServlet {
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
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/common/checkout.jsp");
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		CarrelloBean cart=(CarrelloBean) session.getAttribute("cart");
		UtenteBean utente=(UtenteBean) session.getAttribute("utente");
		
		OrdineBean ordine=new OrdineBean();
		
		
		if(cart==null || cart.getProd().isEmpty()) {
			
			
			response.sendRedirect(request.getContextPath() + "/prodotto");
		}
		
		double totale=0;
		List<ElementoCarBean> prodotti=cart.getProd();
		
		for(ElementoCarBean elem:prodotti) {
			
			ProdottoBean p=elem.getProdotto();
			int q=elem.getQuant();
			
			totale += p.getPrezzo() *q;
		}
		
		int id_utente=utente.getIdUtente();
		java.sql.Date data = new java.sql.Date(System.currentTimeMillis());
		String stato="in eleborazione";
		
		
		ordine.setDataOrdine(data);
		ordine.setStato(stato);
		ordine.setTotale(totale);
		ordine.setIdUtente(id_utente);
		
		try {
			
			saveOrd(ordine,cart);
			session.removeAttribute("cart");
			response.sendRedirect(request.getContextPath() + "/prodotto");
			
		}catch(SQLException e){
			
			System.err.println("Err:" + e.getMessage());
		}
		
	}
	
	private void saveOrd(OrdineBean ordine,CarrelloBean cart)throws SQLException {
		
		ordineDao.doSaveCart(ordine,cart);
		
		
	}

}
