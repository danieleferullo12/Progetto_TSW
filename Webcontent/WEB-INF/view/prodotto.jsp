<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PuntoFit</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="Header.jsp"/>

<%
    ProdottoBean prod = (ProdottoBean) request.getAttribute("prodottoCercato");
%>

<div class="container-prodotto">
    <% if (prod != null) { %>
        
        <div class="prodotto-immagine-wrapper">
            <img src="images/prodotti/<%=prod.getImmagineUrl()%>" alt="<%= prod.getNome() %>" />
        </div>
        
        <div class="prodotto-dettagli">
            <h1><%= prod.getNome() %></h1>
            <p class="prezzo"><%= prod.getPrezzo() %> €</p>
            <p class="descrizione"><%= prod.getDescrizione() %></p>
            
            <button onclick="aggiungiAlCarrello(<%= prod.getIdProdotto() %>, this)">
                Aggiungi al Carrello
            </button>
        </div>
        
    <% } else { %>
        <p class="prodotto-non-disponibile">Prodotto non disponibile.</p>
    <% } %>
</div>

<jsp:include page="Footer.jsp"/>
</body>
</html>