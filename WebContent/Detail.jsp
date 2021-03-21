<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ page import="medicaments.classes.Presentation" %>
	<%@ page import="medicaments.classes.Avis" %>
	<%@ page import="medicaments.classes.Generique" %>
	<%@ page import="medicaments.classes.Medicament" %>
	<%@ page import="medicaments.classes.Prescription" %>
	<%@ page import="medicaments.classes.Composition" %>
	<%@ page import="medicaments.classes.InfosImportante" %>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="org.unbescape.html.HtmlEscape"%>

<%
		Medicament med = (Medicament) request.getAttribute("medic");
		ArrayList<Presentation> listPres = (ArrayList<Presentation>) request.getAttribute("listPres");
		ArrayList<Prescription> listPresc = (ArrayList<Prescription>) request.getAttribute("listPresc");
		ArrayList<Avis> listAvisSMR = (ArrayList<Avis>) request.getAttribute("listAvisSMR");
		ArrayList<Avis> listAvisASMR = (ArrayList<Avis>) request.getAttribute("listAvisASMR");
		ArrayList<Composition> listCompo = (ArrayList<Composition>) request.getAttribute("listCompo");
		ArrayList<InfosImportante> listInfo = (ArrayList<InfosImportante>) request.getAttribute("listInfo");
		Boolean erreurCIS = (Boolean) request.getAttribute("erreurCode");
	%>


<!DOCTYPE html>
<html lang="fr">

	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/14b05e12a0.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="style.css" />

		<link rel="shortcut icon" href="./donnees/Icone/rainbow.ico" type="image/x-icon">
        <link rel="icon" href="./donnees/Icone/rainbow.ico" type="image/x-icon">

		<title>Détails</title>
	</head>
<!--
	margin -> marge avec les autres éléments
	padding -> marge intérieur de l'élément
-->
	<body style="background-image: url('https://images.unsplash.com/photo-1554188248-986adbb73be4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1900&q=1280');">

		<jsp:include page="header.jsp" >
			<jsp:param name="title" value="Detail"/>
		</jsp:include>
	<%if(erreurCIS == false){ %>
			<div class="container">

				<div class="row">
					<ul class="list-group col-lg-12">
					  <li class="list-group-item">
					  	<div class="d-flex w-100 justify-content-between">
					  		<h4 class="mb-1"><%= HtmlEscape.escapeHtml5(med.getDenomination()) %></h4>
					  	</div>
					  </li>
					  <li class="list-group-item  mt-3">
					  	<div class="d-flex w-100 justify-content-between">
					  		<h4 class="mb-1" style="color: blue">Présentation</h4>
					  	</div>
					  	<%
					  		if (null != listPres){
					  			for(Presentation pres : listPres){
						  			%>
						  				<table class="table-sm">
										  <tbody>
										    <tr>
										      <th id="col1" name="col1" scope="row">Code CIS</th>
										      <td><%= HtmlEscape.escapeHtml5(String.valueOf(pres.getCodeCIS()))%></td>

										    </tr>
										    <tr>
										      <th scope="row">Code CIP7</th>
										      <td><%=HtmlEscape.escapeHtml5(pres.getCodeCIP7())%></td>
										    </tr>
										    <tr>
										      <th scope="row">Code CIP13</th>
										      <td><%=HtmlEscape.escapeHtml5(pres.getCodeCIP13())%></td>
										    </tr>
										    <tr>
										      <th scope="row">Libelle</th>
										      <td><%=HtmlEscape.escapeHtml5(pres.getLibelle())%></td>
										    </tr>
										    <tr>
										      <th scope="row">Statut Administratif</th>
										      <td><%=HtmlEscape.escapeHtml5(pres.getStatutAdministratif())%></td>
										    </tr>
										    <tr>
										      <th scope="row">Etat de commercialisation</th>
										      <td><%=HtmlEscape.escapeHtml5(pres.getEtatCommercialisation())%></td>
										    </tr>
										    <tr>
										      <th scope="row">Date de commercialisation</th>
										      <td><%=HtmlEscape.escapeHtml5(pres.getDateDeclarationCommercialisation())%></td>
										    </tr>
										    <tr>
										      <th scope="row">Agréments aux Collectivités</th>
										      <td><%=HtmlEscape.escapeHtml5(pres.getAgrementCollectivites())%></td>
										    </tr>
										    <tr>
										      <th scope="row">Taux de Remboursement</th>
										      <td><%=HtmlEscape.escapeHtml5(pres.getTauxRemboursement())%></td>
										    </tr>
										    <tr>
										      <th scope="row">Prix</th>
										      <td><%=HtmlEscape.escapeHtml5(pres.getPrix())%></td>
										    </tr>
										    <tr>
										      <th scope="row">Indication de Remboursement</th>
										      <td><%=HtmlEscape.escapeHtml5((pres.getIndicationRemboursement()==null || pres.getIndicationRemboursement().isEmpty()?"Non renseigné":pres.getIndicationRemboursement()))%></td>
										      </tr>
										  </tbody>
										</table>
										<br/>
						  			<%
					  			}
					  		}else {
								%>
								<p class="mb-1">Aucune Présentation Renseignée</p>
							<%
							}
					  	%>
					  </li>
					  
					  
					  <li class="list-group-item  mt-3">
					  	<div class="d-flex w-100 justify-content-between">
					  		<h4 class="mb-1" style="color: blue">Composition</h4>
					  	</div>
					  	
					  	<%
					  		if (listCompo != null)
					  		{
					  			for(Composition comp : listCompo)
					  			{
					  				%>
					  					<table class="table-sm">
										  <tbody>
										  	<tr>
                                              <th name="col2" scope="row" >Désignation élément</th>
                                              <td><%= HtmlEscape.escapeHtml5(comp.getDesignationElement())%></td>
                                            </tr>
                                            <tr>
                                              <th scope="row" >Code substance</th>
                                              <td><%= HtmlEscape.escapeHtml5(comp.getCodeSubstance())%></td>
                                            </tr>
                                            <tr>
                                              <th scope="row" >Denomination substance</th>
                                              <td><%= HtmlEscape.escapeHtml5(comp.getDenominationSubstance())%></td>
                                            </tr>
                                            <tr>
                                              <th scope="row">Dosage substance</th>
                                              <td><%= HtmlEscape.escapeHtml5(comp.getDosageSubstance())%></td>
                                            </tr>
                                            <tr>
                                              <th scope="row">Reference dosage</th>
                                              <td><%= HtmlEscape.escapeHtml5(comp.getReferenceDosage())%></td>
                                            </tr>
                                            <tr>
                                              <th scope="row">Nature</th>
                                              <td><%= HtmlEscape.escapeHtml5(comp.getNature())%></td>
                                            </tr>
                                            <tr>
                                              <th scope="row">Numero SAFT</th>
                                              <td><%= HtmlEscape.escapeHtml5(comp.getNumeroSAFT())%></td>
                                            </tr>
										  </tbody>
										</table>
					  				<%
					  			}
					  		}
					  		else {
					  			%>
					  				<p class="mb-1">Aucune Composition Renseignée</p>
					  			<%
					  		}
					  	%>
					  </li>
					  
					  
					  
					  <% if (session.getAttribute("Statut") != null)
					  { %>
					  
					    <!-- ------------------------------------------------------------------------------ -->
					  	<!-- ------------------- Affichage des informations importantes ------------------- -->
					  	<!-- ------------------------------------------------------------------------------ -->
					  	
					  	<li class="list-group-item list-group-item-danger mt-3">
						  	<div class="d-flex w-100 justify-content-between">
						  		<h4 class="mb-1" style="color: blue"><span>&#9888;</span> Informations importantes </h4>
						  	</div>
						  	<%
						  		if (listInfo != null)
						  		{
						  			for(InfosImportante info : listInfo)
						  			{
						  				%>
						  				
							  				<table class="table-sm">
											  <tbody>
											  	<tr>
		                                            <th scope="row">Date</th>
		                                            <td><%= HtmlEscape.escapeHtml5(info.getDateDebut())%></td>
	                                          	</tr>
	                                          	
	                                          	<tr>
		                                            <th scope="row">Date de fin</th>
		                                            <td><%= HtmlEscape.escapeHtml5(info.getDateDebut())%>  -  <%= HtmlEscape.escapeHtml5(info.getDateFin())%></td>
	                                          	</tr>
	                                          	
	                                          	<tr>
		                                            <th scope="row">Lien</th>
		                                            <td>
		                                            	<a href="<%= HtmlEscape.escapeHtml5(info.getLien())%>" >
		                                            		<%= info.getLien() %>
		                                            	</a>
		                                            </td>
	                                          	</tr>
	                                          </tbody>
	                                        </table>
							  			
							  			<%
						  			}
						  		}else {
									%>
									<p class="mb-1">Aucune Information Importante</p>
								<%
								}
						  	%>
						</li>
					  
					  
						<li class="list-group-item mt-3">
						  	<div class="d-flex w-100 justify-content-between">
						  		<h4 class="mb-1" style="color: blue">Prescription</h4>
						  	</div>
						  	<%
						  		if (listPresc.size()!=0){
						  			for(Prescription presc : listPresc){
							  			%>
										<p class="mb-1"><%=HtmlEscape.escapeHtml5(presc.getConditions())%></p>
							  			<%
						  			}
						  		}else {
									%>
									<p class="mb-1">Aucune Prescription Renseignée</p>
								<%
								}
						  	%>
						</li>
					 
					 
					  	<li class="list-group-item  my-3">
						  	<div class="d-flex w-100 justify-content-between">
						  		<h4 class="mb-1" style="color: blue">Avis du Service Médical Rendu</h4>
						  	</div>
							<%
								if (listAvisSMR.size() != 0){
									for(Avis av : listAvisSMR){
							  			%>
							  				<table class="table-sm">
											  <tbody>
											    <tr>
											      <th name="col3" scope="row" >Code Dossier HAS</th>
											      <td><%=HtmlEscape.escapeHtml5(av.getCodeDossierHAS())%></td>
		
											    </tr>
											    <tr>
											      <th scope="row"  >Motif Evaluation</th>
											      <td><%=HtmlEscape.escapeHtml5(av.getMotifEval())%></td>
											    </tr>
											    <tr>
											      <th scope="row">Date</th>
											      <td><%=HtmlEscape.escapeHtml5(av.getDate())%></td>
											    </tr>
											    <tr>
											      <th scope="row">Valeur</th>
											      <td><%=HtmlEscape.escapeHtml5(av.getValeur())%></td>
											    </tr>
											    <tr>
											      <th scope="row">Libelle</th>
											      <td><%=av.getLibelle()%></td>
											    </tr>
											    <tr>
											      <th scope="row">Avis Complet</th>
											      <td>
											      	<a href="<%= HtmlEscape.escapeHtml5(av.getListLienCT().get(0))%>" class="text-decoration-none"><%= HtmlEscape.escapeHtml5(av.getListLienCT().get(0))%></a>
											      </td>
											    </tr>
											  </tbody>
											</table>
											<br />
							  			<%
							  		}
								}else {
									%>
										<p>Aucun Avis Renseigné</p>
									<%
								}
						  	%>
						  	<br />
						  	<div class="d-flex w-100 justify-content-between">
						  		<h4 class="mb-1" style="color: blue">Avis de l'Amélioration du Service Médical Rendu</h4>
						  	</div>
							<%
								if (listAvisASMR.size() != 0){
									for(Avis av : listAvisASMR){
							  			%>
										<!-- <br /> -->
											<table class="table-sm table-responsive-sm">
		
											  <tbody>
											    <tr>
											      <th name="col4" scope="row">Code Dossier HAS</th>
											      <td><%=HtmlEscape.escapeHtml5(av.getCodeDossierHAS())%></td>
		
											    </tr>
											    <tr>
											      <th scope="row">Motif Evaluation</th>
											      <td><%=HtmlEscape.escapeHtml5(av.getMotifEval())%></td>
											    </tr>
											    <tr>
											      <th scope="row">Date</th>
											      <td><%=HtmlEscape.escapeHtml5(av.getDate())%></td>
											    </tr>
											    <tr>
											      <th scope="row">Valeur</th>
											      <td><%=HtmlEscape.escapeHtml5(av.getValeur())%></td>
											    </tr>
											    <tr>
											      <th scope="row">Libelle</th>
											      <td><%=av.getLibelle()%></td>
											    </tr>
											    <tr>
											      <th scope="row">Avis Complet</th>
											      <td >
											      	<a href="<%= HtmlEscape.escapeHtml5(av.getListLienCT().get(0))%>" class="text-decoration-none"><%= HtmlEscape.escapeHtml5(av.getListLienCT().get(0))%></a>
											      </td>
											    </tr>
											  </tbody>
											</table>
											<br />
							  			<%
							  		}
								}else {
									%>
									<p class="mb-1">Aucun Avis Renseigné</p>
								<%
							}
						  	%>
					    </li>
					  <%} %>
					</ul>
				</div>
			</div>
	
		<%}else{ %>
			<div class="container">
				<ul class="list-group col-lg-12">
					  <li class="list-group-item">
					  	<div class="d-flex w-100 justify-content-between">
					  		<h4 class="mb-1">Code CIS non répertorié </h4>
					  	</div>
					  </li>
				</ul>
			</div>
		<%}%>
		
		<button style="display: none; position: fixed; bottom: 50px; right: 30px; z-index: 99; font-size: 18px;
		border: none; outline: none; background-color: red; color: white; cursor: pointer;padding: 15px;
		border-radius: 4px;" onclick="topFunction()" id="myBtn" title="Go to top">
			Haut
		</button>
	
	</body>
	
	<script>
	var th = document.getElementsByTagName("th");
	for(var element in th){
		th[element].width=200;}
	</script>
	
	
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
