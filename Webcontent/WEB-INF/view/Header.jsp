<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>

 <div id="logo">
  <a href="index.jsp"><img  src="images/Logo.png" alt="Logo"></a>
 </div> 

 <div id="serach-bar">
 
  <form action="RicercaServlet" method="Get">
  <input type="text" name="search" placeholder="Cerca nel negozio...">
  <button type="submit">Cerca</button>
  </form>
  
 </div>

   <div id="actions">
   <a href="login.jsp">Log In</a>
   <a href="carrello.jsp">Carrello</a>

   </div>

  <nav>
  <ul>
   <li><a href="">PROTEINE</a>
   <li><a href="">ABBIGLIAMENTO</a>
   <li><a href="">ACCESSORI</a>
   <li><a href="">ALIMENTAZIIONE</a>
  
  </ul>
  </nav>


 </header>