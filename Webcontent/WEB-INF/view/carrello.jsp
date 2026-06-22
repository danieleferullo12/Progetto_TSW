<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/style.css"> 
<title>Il tuo Carrello</title>
</head>
<body>
<jsp:include page="Header.jsp"/>



<h2 id="titolo">Il tuo Carrello</h2>

<%  
    CarrelloBean cart= (CarrelloBean) session.getAttribute("cart");
    
     if(cart==null || cart.getProd().isEmpty()){
%>

   <div class="carrello-vuoto">
     
     <p>Il tuo Carrello è vuoto, torna allo <a href="prodotto">shopping</a></p>
   
   </div>

 <%} else { %>
 
 
 
   <table class="tabella-prod">
     <tr>
       <th></th>
       <th>Prodotto</th>
       <th>Prezzo</th>
       <th>Quantità</th>
       <th>Totale</th>
       <th>Operazioni</th>
     </tr> 
     
     <%   
       double totale=0;
     
       if(cart!=null){
    	   
    	   List<ElementoCarBean> prodotti=cart.getProd();
    	   
    	   if(prodotti!=null && !prodotti.isEmpty()){
    		   
    		   for(ElementoCarBean elem:prodotti){
    		    
    			 ProdottoBean p=elem.getProdotto();
    			 int q=elem.getQuant();	 
    			 
    			 double totaleProdotto=p.getPrezzo()*q;
    			 totale+= totaleProdotto;
     %>
     <tr>
       <td><img src="images/prodotti/<%=p.getImmagineUrl()%>" width="85" height="85" alt="<%=p.getNome()%>"></td>
       <td><%=p.getNome()%></td>
       <td><%=String.format("%.2f €", p.getPrezzo()) %></td>
       <td><%=q %></td> 
       <td><%=String.format("%.2f €", totaleProdotto)%></td>
       <td><a href="carrello?action=add&code=<%=p.getIdProdotto()%>">+</a>
           <a href="carrello?action=removesing&code=<%=p.getIdProdotto()%>">-</a>
           <a href="carrello?action=remove&code=<%=p.getIdProdotto()%>">x</a>
     </tr>
    <% } %>
   <%}%> 
  <%}%>
  </table>
  
  <div id="repilogo">
  <h3>Repilogo Ordine</h3>
  <div id="numero">
   <span>Numero di prodotti:</span>
   <span><%=cart.getProd().size()%></span><br>
  </div>
  
  <div id="totale">
   <span>Totale:</span>
   <span><%=String.format("%.2f €",totale)%></span>
  </div>
  
  <div id="bottone">
    <a href="common/checkout">Vai al Checkout</a>
  </div>
 
  </div>
  
  
 <%}%> 
  

  <jsp:include page="Footer.jsp"/>
</body>
</html>