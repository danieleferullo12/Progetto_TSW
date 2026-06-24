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


@WebServlet("/admin/areaRiservata")
public class AdminArea extends HttpServlet {
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
		
		
		actions(request);
		loadProdList(request);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/admin/admin.jsp");
		dispatcher.forward(request, response);
	}

	
	private void actions(HttpServletRequest request) {
		
		String action=request.getParameter("action");
		
		try {
			
			if(action!=null) {
				
				if(action.equalsIgnoreCase("insert")){
					
					insertProduct(request);
				}
				
				else if(action.equalsIgnoreCase("delete")) {
					
					deleteProduct(request);
				}
				else if(action.equalsIgnoreCase("read")) {
					
					readProduct(request);
				}
				
				else if(action.equalsIgnoreCase("mod")) {
					
					modProd(request);
				}
			}
			
		}catch(SQLException e) {
			
			System.err.println("Error:" + e.getMessage());
		}
			
	}
	
	
	private void insertProduct(HttpServletRequest request)throws SQLException {
		
		String nome=request.getParameter("nome");
		double prezzo=Double.parseDouble(request.getParameter("prezzo"));
		int quantita=Integer.parseInt(request.getParameter("quant_disp"));
		String urlImg=request.getParameter("img");
		int categoria=Integer.parseInt(request.getParameter("id_categ"));
		
		ProdottoBean prodotto=new ProdottoBean();
		
		prodotto.setNome(nome);
		prodotto.setPrezzo(prezzo);
		prodotto.setQuantitaDisp(quantita);
		prodotto.setImmagineUrl(urlImg);
		prodotto.setIdCategoria(categoria);
		
		prodottoDao.doSave(prodotto);
			
	}
	
	private void deleteProduct(HttpServletRequest request)throws SQLException{
		
		int id=Integer.parseInt(request.getParameter("id"));
		prodottoDao.doDelete(id);
		
	}
	
	private void readProduct(HttpServletRequest request)throws SQLException{
		
		int id=Integer.parseInt(request.getParameter("id"));
		request.setAttribute("prodotto",prodottoDao.doRetrieveByKey(id));
		
	}
	
	private void loadProdList(HttpServletRequest request) {
		
		String order=request.getParameter("order");
		
		try {
			
			request.setAttribute("prodottiA",prodottoDao.doRetrieveAll(order));
			
		}catch(SQLException e) {
			
			System.err.println("Error:" + e.getMessage());
		}
	}
	
	private void modProd(HttpServletRequest request)throws SQLException{
		
		
		
		int idProd=Integer.parseInt(request.getParameter("id_prodotto"));
		String nome=request.getParameter("nome");
		double prezzo=Double.parseDouble(request.getParameter("prezzo"));
		int quantita=Integer.parseInt(request.getParameter("quant_disp"));
		String urlImg=request.getParameter("img");
		int categoria=Integer.parseInt(request.getParameter("id_categ"));
		
        ProdottoBean prodotto=new ProdottoBean();
		
        prodotto.setIdProdotto(idProd);
		prodotto.setNome(nome);
		prodotto.setPrezzo(prezzo);
		prodotto.setQuantitaDisp(quantita);
		prodotto.setImmagineUrl(urlImg);
		prodotto.setIdCategoria(categoria);
		
		prodottoDao.doUpdate(prodotto);
	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
