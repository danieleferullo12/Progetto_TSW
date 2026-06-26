<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PuntoFit</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css">
</head>
<body>
<jsp:include page="Header.jsp"/>

<section id="main">

  <section class="hero-banner">
  <img src="images/banner.png" alt="banner">
  </section>
  
  
  <h2>Scopri le novita</h2>
  
  <section class="prodotti">
 
     
     <% 
        @SuppressWarnings("unchecked")
        List<ProdottoBean> lista=(List<ProdottoBean>) request.getAttribute("Listaprodotti");  
        
        if(lista!=null){
        
        for(ProdottoBean p: lista){	
     %>
        
        <div class="product-card">
          <img src="images/prodotti/<%=p.getImmagineUrl()%>" alt="<%=p.getNome()%>" width="215px" height="215px">
          <h3><%=p.getNome()%></h3>
          <p class="prezzo">Da <%=p.getPrezzo()%> €<p>
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