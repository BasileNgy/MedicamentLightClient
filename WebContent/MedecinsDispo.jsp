<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="medicaments.classes.*"%>
<%@ page import="org.unbescape.html.HtmlEscape"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Médecins Disponibles</title>
	<link rel="stylesheet" type="text/css" href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css">
		
		<link rel="shortcut icon" href="./donnees/Icone/croix.ico" type="image/x-icon">
        <link rel="icon" href="./donnees/Icone/croix.ico" type="image/x-icon">        
        		<script src="https://kit.fontawesome.com/14b05e12a0.js" crossorigin="anonymous"></script>
</head>
<body>	

 	<jsp:include page="header.jsp" >
		<jsp:param name="title" value="Médecins Disponibles"/>
	</jsp:include>

	 <% 
    	 ArrayList<Utilisateur> ListeMedecinDispo = null;
	     if(request.getAttribute("ListMedecinDispo")!=null){
	    	 ListeMedecinDispo =(ArrayList<Utilisateur>)request.getAttribute("ListMedecinDispo");
	     }
	    %>
	    
	    <div class="container"> 
	    	<h2>Medecins Disponibles</h2>
			    <%
				if(ListeMedecinDispo.size() != 0){
					for(Utilisateur u :ListeMedecinDispo){
				%>
				<form method="post" action="Chat">	
					<div class="row">
			    		<button type="submit" name="login" value="<%=HtmlEscape.escapeHtml5(u.getLogin())%>" class="col btn-sm btn-outline-dark border border-dark m-1 text-center"><%= HtmlEscape.escapeHtml5(u.getNom())%></button>	
			    	</div>
			    </form>
			    <%
			    	}
				}else{
				%>
					<h3>Aucun médecin disponible actuellement</h3>
			<%	} %>
		</div>
</body>
</html>