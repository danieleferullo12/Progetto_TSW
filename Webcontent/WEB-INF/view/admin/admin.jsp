<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.*,java.util.*" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Area Riservata</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css">
</head>
<body>
 <jsp:include page="../Header.jsp"/>
 
<h2>Prodotti</h2>
<div id="admin-prod">
<table  border="1">
<tr>
<th>ID<a href="<%=request.getContextPath()%>/admin/areaRiservata?order=id_prodotto">Ordina</a></th>
<th>NOME<a href="<%=request.getContextPath()%>/admin/areaRiservata?order=nome">Ordina</a></th>
<th>IMMAGINE</th>
<th>OPERAZIONI</th>
</tr>

<%
@SuppressWarnings("unchecked")
List<ProdottoBean> lista=(List<ProdottoBean>) request.getAttribute("prodottiA");  

if(lista!=null){

for(ProdottoBean p: lista){	
%>


<tr>
<td><%=p.getIdProdotto()%></td>
<td><%=p.getNome()%></td>
<td><img src="<%=request.getContextPath()%>/images/prodotti/<%=p.getImmagineUrl()%>" alt="<%=p.getNome()%>" width="60" height="60"></td>
<td><a href="<%=request.getContextPath()%>/admin/areaRiservata?action=delete&id=<%=p.getIdProdotto()%>">Elimina</a><br>
    <a href="<%=request.getContextPath()%>/admin/areaRiservata?action=read&id=<%=p.getIdProdotto()%>">Dettagli</a>
</td>
</tr>


<%
    }

 }else{

%>
<tr>
  <td colspan="6">Non ci sono prodotti</td>
</tr>
<%
 }
%>

</table>
</div>
<h2>Dettagli</h2>
<%
   ProdottoBean prodotto=(ProdottoBean) request.getAttribute("prodotto");
    if(prodotto!=null){
%>
<div id="dettagli">
<table  border="1">
<tr>
<th>CODICE</th>
<th>NOME</th>
<th>QUANTITA</th>
<th>PREZZO</th>
</tr>

<tr>
<td><%=prodotto.getIdProdotto()%></td>
<td><%=prodotto.getNome()%></td>
<td><%=prodotto.getQuantitaDisp()%>
<td><%=prodotto.getPrezzo()%></td>
</tr>
</table>
</div>
<%}%>

<h2>Aggiungi</h2>
<div id="aggiungi">
<form action="<%=request.getContextPath()%>/admin/areaRiservata" method="post" enctype="multipart/form-data">
<input type="hidden" name="action" value="insert"> 

<label for="nome">Nome:</label><br>
<input name="nome" id="nome" type="text" maxlength="20" required><br>
 
<label for="prezzo">Prezzo:</label><br>
<input name="prezzo" id="prezzo" type="number" step="0.01" required><br>

<label for="quantita">Quantita:</label><br>
<input name="quant_disp" id="quantita" type="number" min="1" value="1" required><br>

<label for="categoria">ID Categoria:</label><br>
<input name="id_categ" id="categoria" type="number" required><br>

<label for="immagine">Immagine Prodotto:</label><br>
<input name="img" id="immagine" type="file" accept="image/*" required><br><br>
<input type="submit" value="Aggiungi"><input type="reset" value="Reset">

</form>
</div>

<h2>Modifica</h2>
<div id="modifica">
<form action="<%=request.getContextPath()%>/admin/areaRiservata" method="post">
<input type="hidden" name="action" value="mod"> 

<label for="idprod">ID:</label><br>
<input name="id_prodotto" id="idprod" type="number" required><br>

<label for="nome">Nome:</label><br>
<input name="nome" id="nome" type="text" maxlength="20" required><br> 

<label for="prezzo">Prezzo:</label><br>
<input name="prezzo" id="prezzo" type="number" step="0.01" required><br>

<label for="quantita">Quantita:</label>
<br><input name="quant_disp" id="quantita" type="number" min="1" value="1" required><br>

<label for="categoria">ID Categoria:</label><br>
<input name="id_categ" id="categoria" type="number" required><br>

<label for="immgaine">Immagine:</label><br><input name="img" id="immagine" type="text" required><br>
<input type="submit" value="Modifica"><input type="reset" value="Reset">

</form>
</div>

<h2>Ordini effettuati</h2>
<p>Visualizza la pagina dedicata agli <a href="<%=request.getContextPath()%>/admin/OrdiniAdmin">ordini</a></p>

<jsp:include page="../Footer.jsp"/>
</body>
</html>