<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PuntoFit-login</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css">
</head>
<body>
<jsp:include page="Header.jsp"/>

<% 
List<String> errors = (List<String>) request.getAttribute("errors");
if (errors != null){
	for (String error: errors){ %>
		<%=error %> <br>		
	<%
	}
}
%>



<div id="logCont">
<h2>Accesso cliente</h2>
<form id="login" action="login" method="post">
<div id="email-field">
<label for="email">E-mail</label><br>
<input type="text" id="email" name="email">
<span id="errorEmail"></span> 
</div>

<div id="pass-field">
<label for="pass">Password</label><br>
<input type="password" id="pass" name="password">
<span id="errorPass"></span>
</div>

<div id="button-log">
<button type="submit">Accedi</button>

</div>

</form>


<div id="nonReg">

<h2>Nuovi Clienti</h2>

<p>Se non sei registrato puoi farlo <a href="registrazione">qui</a>

</div>

</div>




<jsp:include page="Footer.jsp"/>
</body>
</html>