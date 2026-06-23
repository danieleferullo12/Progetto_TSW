<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*,java.util.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profilo-Utente</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css">
</head>
<body>
<jsp:include page="../Header.jsp"/>


<%  
   @SuppressWarnings("unchecked")
   List<OrdineBean> ordini=(List<OrdineBean>) request.getAttribute("ordini");

     if(ordini==null || ordini.isEmpty()){
%>

<div id="ordine-vuoto">

<p>Non hai effettuato nessun ordine</p>

</div>

<%}else{%>

<table id="tabella-ord">
<tr>
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