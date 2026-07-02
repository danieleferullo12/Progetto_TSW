<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,model.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestione-ordini</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="../Header.jsp"/>

<h2>Ordini effettuati sul sito</h2>

<%  
   @SuppressWarnings("unchecked")
   List<OrdineBean> ordini=(List<OrdineBean>) request.getAttribute("tuttiGliOrdini");

     if(ordini==null || ordini.isEmpty()){
%>

<div class="vuoto">

<p>Non sono ancora stati effettuati ordini</p>

</div>

<%}else{%>

<table class="tabella-prod">
<tr>
<th>Id Ordine</th>
<th>Id Utente</th>
<th>Data</th>
<th>Stato</th>
<th>Totale</th>
<th>Prodotto</th>
<th>Quantità</th>
<th>Prezzo Unitario</th>
</tr>

<%
    for(OrdineBean ordine:ordini){
    
   List<DettaglioOrdineBean> dettagli=ordine.getDettagli();
   int numeroProdotti=dettagli.size();


%>

<tr>
<td rowspan="<%= numeroProdotti%>"><%= ordine.getIdOrdine()%></td>
<td rowspan="<%= numeroProdotti %>"><%= ordine.getIdUtente() %></td>
<td rowspan="<%= numeroProdotti %>"><%= ordine.getDataOrdine() %></td>
<td rowspan="<%= numeroProdotti %>"><%= ordine.getStato() %></td>
<td rowspan="<%= numeroProdotti %>"><%= ordine.getTotale() %> &euro;</td>

<%
     if (dettagli != null && !dettagli.isEmpty()) {
   
      DettaglioOrdineBean primoDettaglio = dettagli.get(0);

%>
<td><%= primoDettaglio.getNomeProdotto() %></td>
<td><%= primoDettaglio.getQuantita() %></td>
<td><%= primoDettaglio.getPrezzoUnitario() %> &euro;</td>

</tr><%
        for (int i = 1; i < dettagli.size(); i++) {
          DettaglioOrdineBean altroDettaglio = dettagli.get(i); 
    
    %>

<tr>
<td><%= altroDettaglio.getNomeProdotto() %></td>
<td><%= altroDettaglio.getQuantita() %></td>
<td><%= altroDettaglio.getPrezzoUnitario() %> &euro;</td>
</tr>

<%
        }
  }
%>
  <%}%>
  </table> 
<%}%>

<jsp:include page="../Footer.jsp"/>
</body>
</html>