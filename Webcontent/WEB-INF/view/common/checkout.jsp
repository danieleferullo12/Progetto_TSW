<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="<%=request.getContextPath()%>/scripts/validate.js"></script>
</head>
<body>
<jsp:include page="../Header.jsp"/>


<%  
   UtenteBean utente=(UtenteBean) session.getAttribute("utente");
%>

<form id="reg" action="checkout" method="post">
<div id="checkCont">

<h2>Completa l'ordine</h2>

<div id="email-field">
<label for="email">E-mail</label>
<input type="text" id="email" name="email" value="<%=utente.getEmail()%>" onchange="validateForm(this, emailPattern, document.getElementById('errorEmail'), emailErr)">
<span id="errorEmail"></span>
</div>

<div id="tel-field">
<label for="tel">Telefono</label>
<input type="text" id="tel" name="telefono" value="<%=utente.getTelefono()%>" onchange="validateForm(this, telefonoPattern, document.getElementById('errorTel'), telErr)">
<span id="errorTel"></span>
</div>

<div id="ind-field">
<label for="indirizzo">Indirizzo</label>
<input type="text" id="indirizzo" name="indirizzo" value="<%=utente.getIndirizzoSpedizione()%>"  onchange="validateForm(this, indirizzoPattern, document.getElementById('errorInd'), indErr)">
<span id="errorInd"></span>
</div>

<div id="pag-field">
<label for="pagamento">Numero Carta Di Credito</label>
<input type="text" id="pagamento" name="carta"  onchange="validateForm(this, cartaPattern, document.getElementById('errorCart'), cartErr)">
<span id="errorCart"></span>
</div>

<div id="button-check">
<button type="submit" onclick="return validateCheck()">Completa</button>
</div>


</div>
</form>
<jsp:include page="../Footer.jsp"/>
</body>
</html>