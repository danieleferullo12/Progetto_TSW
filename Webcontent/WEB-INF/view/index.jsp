<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Punto Fit</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css">
</head>
<body>

<jsp:include page="Header.jsp"/>

<section id="main">

  <section class="hero-banner">
  <img src="images/banner.png" alt="banner">
  </section>
  
  
  <h2>Scopri i prodotti in Evidenza</h2>
  
  <section class="prodotti">
  <div class="order">
  <form action="prodotto" method="get">
  <label for="orderSelect">Ordina Per</label>
  
  <select id="orderSelect">
  <option value="">Seleziona</option>
  <option value="prezzo">Prezzo:Crescente</option>
  <option value="prezzo DESC">Prezzo:Decrescente</option>
  <option value="nome">Nome</option>
  </select>
  <button type="submit">Ordina</button>
  </form>
  </div>
  
     
     <% 
        List<ProdottoBean> lista=(List<ProdottoBean>) request.getAttribute("prodotti");  
        
        if(lista!=null){
        
        for(ProdottoBean p: lista){	
     %>
        
        <div class="product-card">
          <img src="images/prodotti/<%=p.getImmagineUrl()%>" alt="<%=p.getNome()%>" width="215px" height="215px">
          <h3><%=p.getNome()%></h3>
          <p id="prezzo"><%=p.getPrezzo()%>€<p>
          <a href="carrello?action=add&code=<%=p.getIdProdotto()%>">Aggiungi al Carrello</a>   
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