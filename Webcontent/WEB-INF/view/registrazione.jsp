<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css">
<script src="scripts/validate.js"></script>
</head>
<body>
<jsp:include page="Header.jsp"/>
<% 
     String errorReg=(String) request.getAttribute("errorReg");
     
     if(errorReg!=null){
%> 
    <div id="errorReg">
     <%= errorReg %>
    
    </div>
  
 <% } %>



<form id="reg" action="registrazione" method="post">

<div id="regCont">

<div>
<label for="nome">Nome</label>
<input type="text" id="nome" name="nome" onchange="validateForm(this, nomeoCognPattern, document.getElementById('errorNome'), nomeErr)">
<span id="errorNome"></span>
</div>

<div>
<label for="cognome">Cognome</label>
<input type="text" id="cognome" name="cognome" onchange="validateForm(this, nomeoCognPattern, document.getElementById('errorCognome'), cognErr)">
<span id="errorCognome"></span>
</div>

<div id="email-field">
<label for="email">E-mail</label>
<input type="text" id="email" name="email" onchange="validateForm(this, emailPattern, document.getElementById('errorEmail'), emailErr)">
<span id="errorEmail"></span>
</div>

<div id="pass-field">
<label for="pass">Password</label>
<input type="password" id="pass" name="password" onchange="validateForm(this,passPattern,document.getElementById('errorPass'),passErr)">
<span id="errorPass"></span>
</div>

<div>
<label for="tel">Telefono</label>
<input type="text" id="tel" name="telefono" onchange="validateForm(this, telefonoPattern, document.getElementById('errorTel'), telErr)">
<span id="errorTel"></span>
</div>

<div>
<label for="indirizzo">Indirizzo</label>
<input type="text" id="indirizzo" name="indirizzo" onchange="validateForm(this, indirizzoPattern, document.getElementById('errorInd'), indErr)">
<span id="errorInd"></span>
</div>

<div id="button-log">
<button type="submit" onclick="return validate()">Registrati</button>
</div>

</div>

</form>

<jsp:include page="Footer.jsp"/>
</body>
</html>