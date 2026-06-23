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
   List<OrdineBean> ordini=(List<OrdineBean>) session.getAttribute("ordini");

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
</tr>


</table>
 <%}%>

<jsp:include page="../Footer.jsp"/>
</body>
</html>