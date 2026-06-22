<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css">
<script src="scripts/validate.js"></script>
</head>
<body>
<jsp:include page="../Header.jsp"/>

<form id="checkout" action="checkout" method="post">
<div id="checkCont">

<h2>Completa l'ordine</h2>

<div id="email-field">
<label for="email">E-mail</label>
<input type="text" id="email" name="email" onchange="validateForm(this, emailPattern, document.getElementById('errorEmail'), emailErr)">
<span id="errorEmail"></span>
</div>

<div id="tel-field">
<label for="tel">Telefono</label>
<input type="text" id="tel" name="telefono" onchange="validateForm(this, telefonoPattern, document.getElementById('errorTel'), telErr)">
<span id="errorTel"></span>
</div>

<div id="ind-field">
<label for="indirizzo">Indirizzo</label>
<input type="text" id="indirizzo" name="indirizzo" onchange="validateForm(this, indirizzoPattern, document.getElementById('errorInd'), indErr)">
<span id="errorInd"></span>
</div>

<div id="button-check">
<button type="submit" onclick="return validate()">Completa</button>
</div>


</div>
</form>
<jsp:include page="../Footer.jsp"/>
</body>
</html>