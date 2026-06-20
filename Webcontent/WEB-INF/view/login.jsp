<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PuntoFit-login</title>
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




<form id="login" action="login" method="post">
<div>
<label for="email">E-mail</label>
<input type="text" id="email" name="email">
<span id="errorEmail"></span> 
</div>

<div>
<label for="pass">Password</label>
<input type="password" id="pass" name="password">
<span id="errorPass"></span>
</div>

<div>
<input type="submit" value="Accedi">

</div>

</form>

<div id="nonReg">

<p>Se non sei registrato puoi farlo <a href="registrazione">qui</a>

</div>


<jsp:include page="Footer.jsp"/>
</body>
</html>