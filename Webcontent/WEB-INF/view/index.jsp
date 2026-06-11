<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Punto Fit</title>
<link type="text/css" rel="stylesheet" href="style/style.css">
</head>
<body>

<jsp:include page="Header.jsp"/>

<section id="main">

  <section class="hero-banner">
  <img src="images/banner.png" alt="banner">
  </section>
  
  <section class="prodotti">
  <div class="order">
  <label for="orderSelect">Ordina Per</label>
  <select id="orderSelect">
  <option value="prodotto">Seleziona</option>
  
  <option value="prodotto?order=prezzo">Prezzo:Crescente</option>
  <option value="prodotto?order=prezzo DESC">Prezzo:Decrescente</option>
  <option value="prodotto?order=nome">Nome</option>
  </select>
  </div>
  <h2>Prodotti in Evidenza</h2>
  
     
     <% 
        List<ProdottoBean> lista=(List<ProdottoBean>) request.getAttribute("prodotti");  
        
        if(lista!=null){
        
        for(ProdottoBean p: lista){	
     %>
        
        <div class="product-card">
          <img src="images/prodotti/<%=p.getImmagineUrl()%>" alt="<%=p.getNome()%>" width="215px" height="215px">
          <h3><%=p.getNome()%></h3>
          <p id="prezzo"><%=p.getPrezzo()%>€<p>
          <a href="#">Aggiungi al Carrello</a>   
        </div>
      <% 
        }
      } 
      %>
  </section>
  
</section>

<jsp:include page="Footer.jsp"/>
</body>
</html>