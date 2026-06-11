package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarrelloBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	List<ProdottoBean> prodotti;
	
	public CarrelloBean() {
		
		prodotti=new ArrayList<ProdottoBean>();
	}
	
	public void addProd(ProdottoBean prod) {
		
		prodotti.add(prod);
	}
   
	public void deleteProd(ProdottoBean prod) {
		
		for(ProdottoBean prodotto: prodotti) {
			
			if(prodotto.getIdProdotto()==prod.getIdProdotto()) {
				
				prodotti.remove(prodotto);
			}
			
		}
		
		
	}
	
	public List<ProdottoBean> getProd(){
		
		return prodotti;
	}
	
}
