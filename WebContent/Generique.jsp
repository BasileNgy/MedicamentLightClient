<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="medicaments.classes.*"%>
<%@ page import="org.unbescape.html.HtmlEscape"%>


<!-- ------------------------------------------------------------------------------------------------------------------- -->
<!-- Cette condition est est dans le .jsp pour obligé l'utilisateur à se connecter pour accéder au contenu de cette page -->
<!-- ------------------------------------------------------------------------------------------------------------------- -->
<% if (session == null || session.getAttribute("ID") == null) {response.sendRedirect("./Index.jsp"); return;}%>

<!DOCTYPE html>
<html>
	<head>
		<title>Liste des groupes Génériques</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>


        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <link rel="shortcut icon" href="./donnees/Icone/bowrain.ico" type="image/x-icon">
        <link rel="icon" href="./donnees/Icone/bowrain.ico" type="image/x-icon">
        		<script src="https://kit.fontawesome.com/14b05e12a0.js" crossorigin="anonymous"></script>
	</head>
	
	<body style="background-image: url('https://images.unsplash.com/photo-1554188248-986adbb73be4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1900&q=1280');">
		<jsp:include page="header.jsp" >
		<jsp:param name="title" value="Présentation des mécicaments"/>
		</jsp:include>
		
		
		<div class="container">
            <br/>
            
            
            <!-- -------------------------------------------------------- -->
            <!-- Récuperation de l'objet "listeGenerique" dans le servlet -->
            <!-- -------------------------------------------------------- -->
            <% 
		        ArrayList<Generique> ListeGenerique = null;
		        if(request.getAttribute("listeGenerique")!= null)
		        {
		        	ListeGenerique = (ArrayList<Generique>)request.getAttribute("listeGenerique");
		        }
	        %>
	        
	        <!-- ----------------------------------------- -->
            <!-- Barre de recherche des groupes génériques -->
            <!-- ----------------------------------------- -->
            
            <form class="form-inline justify-content-center" action="GeneriqueListe" method="post">

                <div class="form-group mx-sm-3 mb-2">
                  <input type="text" style="width: 100%;" class="form-control" placeholder="Saisir un générique ..."  minlength=3 name="search" required>
                </div>

                <button type="submit" class="btn btn-primary mb-2">Rechercher</button>
              </form>

          
            
            <br/>
			
			
			<!-- -------------------------------------------- -->
            <!-- Table d'affichage de la liste des génériques -->
            <!-- -------------------------------------------- -->
			
            <table id="table-id" class="table" style="cursor: pointer;">
                <thead class="thead-dark">
                    <th scope="col">N°</th>
                    <th scope="col" style="width:90%">Médicament</th>
                    
                </thead>

                <tbody>
                <%
                    if(ListeGenerique != null) // On vérifie que la liste des génériques est non nullle
                    {
                        int count=0;
                        for(Generique gen : ListeGenerique) // On affiche le nom de chaque groupe générique
                        {
                            %>
                            <tr class="clickable" style="background-color: <%=(count%2==0?"#f0f0f5":"#d7d5f2") %>" data-toggle="collapse" data-target="#generique<%=HtmlEscape.escapeHtml5(String.valueOf(gen.getIDGroupe())) %>" class="accordion-toggle collapsed" aria-expanded="false" aria-controls="group-of-rows-1">
                                <th scope="row"><%=HtmlEscape.escapeHtml5(String.valueOf(gen.getIDGroupe())) %></th>
                                <td><%=HtmlEscape.escapeHtml5(gen.getLibelleGroupe()) %></td>
                            </tr>
                            
                            <%
                                for(Medicament m: gen.getListMedicament()) // On affiche les informations de chaque médicament de chaque groupe générique
                                {
                                    %>
                                    <tr id="generique<%=HtmlEscape.escapeHtml5(String.valueOf(gen.getIDGroupe())) %>" class="collapse">
                                        <td style="background-color: rgba(50, 115, 220,0.8)"></td>
                                        <td style="background-color: rgba(50, 115, 220,0.8)">
                                            <div>
                                                <%=HtmlEscape.escapeHtml5(m.getDenomination()) %>
                                            </div>
                                        </td>
                                    </tr>
                                    <%
                                }
                            count++;
                        }
                    }
                    
                %>  
                    
                </tbody>
            </table>
        </div>
        
        <button style="display: none; position: fixed; bottom: 50px; right: 30px; z-index: 99; font-size: 18px;
		border: none; outline: none; background-color: red; color: white; cursor: pointer;padding: 15px;
		border-radius: 4px;" onclick="topFunction()" id="myBtn" title="Go to top">
			Haut
		</button>
	</body>
	
	<!-- ------------------- -->
	<!--     Back to top     -->
	<!-- ------------------- -->
	
	<script>
		//Get the button
		var mybutton = document.getElementById("myBtn");
		
		// When the user scrolls down 20px from the top of the document, show the button
		window.onscroll = function() {scrollFunction()};
		function scrollFunction() 
		{
			if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) 
			{
				mybutton.style.display = "block";
				} else {
				mybutton.style.display = "none";
			}
		}
		
		// When the user clicks on the button, scroll to the top of the document
		
		function topFunction() 
		{
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	</script>
</html>


