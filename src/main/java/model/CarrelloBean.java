package model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
public class CarrelloBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<ElementoCarBean> prodotti;
		
	public CarrelloBean() {
		
		prodotti=new ArrayList<ElementoCarBean>();
	}
	
	public void addProd(ProdottoBean prod) {
		
		for(ElementoCarBean elem: prodotti) {
			
			if(elem.getProdotto().getIdProdotto()==prod.getIdProdotto()) {
				
				elem.setQuant(elem.getQuant()+1);
				return;
			}
			
		}
		
		prodotti.add(new ElementoCarBean(prod,1));
	}
   
	public void deleteSingleProd(ProdottoBean prod) {
		
		for(ElementoCarBean elem:prodotti) {
			
			if(elem.getProdotto().getIdProdotto()==prod.getIdProdotto()) {
				
				if(elem.getQuant()>1) {
				elem.setQuant(elem.getQuant()-1);
				return;
				}
				
				else {
					
					prodotti.remove(elem);
					return;
				}
			}
			
		}
		
		
	}
	
	
	public void deleteProd(ProdottoBean prod) {
		
		for(ElementoCarBean elem: prodotti) {
			
			if(elem.getProdotto().getIdProdotto()==prod.getIdProdotto()) {
				
				prodotti.remove(elem);
				return;
			}
			
		}
		
		
	}
	
	public List<ElementoCarBean> getProd(){
		
		return prodotti;
	}
	
	
	public int getQuantTotale() {
		
		int totale=0;
		
		for(ElementoCarBean elem:prodotti) {
			
			totale+=elem.getQuant();
		}
		
		return totale;
	}
}
