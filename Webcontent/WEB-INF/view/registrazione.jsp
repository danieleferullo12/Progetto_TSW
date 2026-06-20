<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
<link rel="stylesheet" type="text/css" href="styleForm.css" />
<script src="scripts/validate.js"></script>
</head>
<body>
<% 
     String errorReg=(String) request.getAttribute("errorReg");
     
     if(errorReg!=null){
%> 
    <div id="errorReg">
     <%= errorReg %>
    
    </div>
  
 <% } %>



<form id="reg" action="registrazione" method="post">

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

<div>
<label for="email">E-mail</label>
<input type="text" id="email" name="email" onchange="validateForm(this, emailPattern, document.getElementById('errorEmail'), emailErr)">
<span id="errorEmail"></span>
</div>

<div>
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

<div>
<input type="submit" value="Registrati" onclick="return validate()">
</div>


</form>

</body>
</html>