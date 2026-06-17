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

<section id="main">

<h2>Il tuo Carrello</h2>

<%  
    CarrelloBean cart= (CarrelloBean) session.getAttribute("cart");
    
     if(cart==null || cart.getProd().isEmpty()){
%>

   <div class="carrello-vuoto">
     
     <p>Il tuo Carrello è vuoto</p>
   
   </div>

 <%} else { %>
 
   <table class="tabella-prod">
     <tr>
       <th>Prodotto</th>
       <th>Prezzo</th>
       <th>Quantità</th>
       <th>Totale</th>
       <th>Operazioni</th>
     </tr> 
   </table>
<% } %>
</section>
  <jsp:include page="Footer.jsp"/>
</body>
</html>