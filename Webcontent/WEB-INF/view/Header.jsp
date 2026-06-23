<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>

 <div id="logo">
  <a href="<%=request.getContextPath()%>/prodotto"><img  src="<%=request.getContextPath()%>/images/Logo.png" alt="Logo" width=250px height=200px></a>
 </div> 

 <div id="search-bar">
 
  <form action="RicercaServlet" method="Get">
  <input type="text" name="search" placeholder="Cerca nel negozio...">
  <button type="submit">Cerca</button>
  </form>
  
 </div>

   <div id="actions">
   <ul>
    <% 
      String role=(String) session.getAttribute("role");
      if(role==null){
   %>
    <li><a href="<%=request.getContextPath()%>/login"><span>Log</span>in</a>
    <%
        } else{
    %>
       <li><a href="<%=request.getContextPath()%>/Logout"><span>Log</span>out</a>
       <li><a href="<%=request.getContextPath()%>/common/profilo"><span>Pro</span>filo</a>
    
    <%
       }
    %>
    
    <li><a href="<%=request.getContextPath()%>/carrello"><span>Carr</span>ello</a>
   </ul>
   </div>

  <nav>
  <ul>
   <li><a href="">PROTEINE</a>
   <li><a href="">ABBIGLIAMENTO</a>
   <li><a href="">ACCESSORI</a>
   <li><a href="">ALIMENTAZIONE</a>
  
  </ul>
  </nav>


 </header>