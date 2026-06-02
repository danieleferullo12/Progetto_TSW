package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import it.unisa.storage.model.*;
import dao.*;
import javax.sql.DataSource;
import java.util.List;


/**
 * Servlet implementation class TestDAO
 */
@WebServlet("/TestDAO")
public class TestDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cambiamo il tipo di risposta in testo semplice così leggiamo i risultati a schermo
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("--- INIZIO TEST DAO ---");
        
        try {
            // 1. Recuperiamo il DataSource dal tuo MainContext (Listener)
            DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
            
            if (ds == null) {
                out.println("ERRORE: Il DataSource è NULL! Controlla il Listener.");
                return;
            }
            out.println("1. Connessione al pool (DataSource) RIUSCITA!\n");
            
            // 2. Istanziamo i tuoi DAO passandogli il DataSource
            UtenteDao utenteDao = new UtenteDaoImpl(ds);
            ProdottoDao prodottoDao = new ProdottoDaoImpl(ds);
            
            // ==========================================
            // TEST 1: LOGIN UTENTE
            // ==========================================
            out.println("=== TEST 1: Login Utente ===");
            // Usa una mail e password che hai realmente inserito nel DB tramite MySQL Workbench
            String emailTest = "danieleferullo10@gmail.com"; 
            String passTest = "123";
            
            UtenteBean utente = utenteDao.doRetreveByEmailPass(emailTest, passTest);
            
            if (utente != null) {
                out.println("LOGIN OK! Trovato utente: " + utente.getNome() + " " + utente.getCognome() + " (Ruolo: " + utente.getRuolo() + ")");
            } else {
                out.println("LOGIN FALLITO: Credenziali errate o utente non trovato.");
            }
            out.println();
            
            // ==========================================
            // TEST 2: PRODOTTI PER CATEGORIA
            // ==========================================
            out.println("=== TEST 2: Recupero Prodotti per Categoria ===");
            int idCategoriaTest = 1; // Cambia con l'ID di una categoria che ha prodotti nel DB
            
            List<ProdottoBean> prodotti = (List<ProdottoBean>) prodottoDao.doRetreveByCategoria(idCategoriaTest);
            
            if (prodotti != null && !prodotti.isEmpty()) {
                out.println("PRODOTTI TROVATI: " + prodotti.size());
                for (ProdottoBean p : prodotti) {
                    out.println("- [" + p.getIdProdotto() + "] " + p.getNome() + " | Prezzo: " + p.getPrezzo() + "€ | Disp: " + p.getQuantitaDisp());
                }
            } else {
                out.println("Nessun prodotto trovato per la categoria ID: " + idCategoriaTest);
            }
            
        } catch (Exception e) {
            out.println("❌ CRASH DURANTE IL TEST! Ecco l'errore:");
            e.printStackTrace(out); // Stampa l'errore direttamente sulla pagina web
        }
        
        out.println("\n--- FINE TEST DAO ---");
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
