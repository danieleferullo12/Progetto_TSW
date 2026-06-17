<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>

 <div id="logo">
  <a href="prodotto"><img  src="images/Logo.png" alt="Logo" width=250px height=200px></a>
 </div> 

 <div id="search-bar">
 
  <form action="RicercaServlet" method="Get">
  <input type="text" name="search" placeholder="Cerca nel negozio...">
  <button type="submit">Cerca</button>
  </form>
  
 </div>

   <div id="actions">
   <a href="login.jsp">Login in</a>
   <a href="carrello">Carrello</a>

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