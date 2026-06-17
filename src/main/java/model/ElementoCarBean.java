package model;



public class ElementoCarBean {
	
   private ProdottoBean prodotto;	
   private int quantita;	
	
   public ElementoCarBean(ProdottoBean prodotto,int quantita) {
	   
	   this.prodotto=prodotto;
	   this.quantita=quantita;
   }
  
   public ProdottoBean getProdotto() {
	   
	   return prodotto;
   }
   
   public int getQuant() {
	   
	   return quantita;
   }
   
   public void setQuant(int quantita) {
	   
	   this.quantita=quantita;
   }
   
}
