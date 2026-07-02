<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>    

<meta charset="UTF-8">

<header>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ajax.js"></script>

 <div id="logo">
  <a href="<%=request.getContextPath()%>/prodotto"><img src="<%=request.getContextPath()%>/images/Logo.png" alt="Logo"></a>
 </div> 

 <div id="search-bar">
  <form action="<%=request.getContextPath()%>/ricerca" method="Get">
    <input type="text" name="search" placeholder="Cerca nel negozio..." required>
    <button type="submit">Cerca</button>
  </form>
 </div>

 <div id="actions">
   <ul>
    <% 
      String role=(String) session.getAttribute("role");
      if(role==null){
     %>
       <li><a href="<%=request.getContextPath()%>/login"><span>Log</span>in</a></li>
    <%
      } else if(role.equals("admin")){
    %>
       <li><a href="<%=request.getContextPath()%>/Logout"><span>Log</span>out</a></li>
       <li><a href="<%=request.getContextPath()%>/common/profilo"><span>Pro</span>filo</a></li>
       <li><a href="<%=request.getContextPath()%>/admin/areaRiservata">Area Riservata</a></li>
    <%
       } else if(role.equals("client")){
    %>
       <li><a href="<%=request.getContextPath()%>/Logout"><span>Log</span>out</a></li>
       <li><a href="<%=request.getContextPath()%>/common/profilo"><span>Pro</span>filo</a></li>
     <% } %>  
    
    <li>
      <a href="<%=request.getContextPath()%>/carrello"><span>Carr</span>ello<span id="contatoreCar"><%
         CarrelloBean carrello=(CarrelloBean) session.getAttribute("cart");
          if(carrello!=null){
        	  out.print(carrello.getQuantTotale());
          }else{
        	  out.print(0);
          }
      %></span></a>
    </li>
   </ul>
 </div>

 <nav>
  <ul>
   <li><a href="<%=request.getContextPath()%>/categoria?categoria=1">PROTEINE</a></li>
   <li><a href="<%=request.getContextPath()%>/categoria?categoria=2">ABBIGLIAMENTO</a></li>
   <li><a href="<%=request.getContextPath()%>/categoria?categoria=3">ACCESSORI</a></li>
   <li><a href="<%=request.getContextPath()%>/categoria?categoria=4">ALIMENTAZIONE</a></li>
  </ul>
 </nav>
</header>