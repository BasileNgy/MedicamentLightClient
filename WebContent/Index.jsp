<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<!DOCTYPE html>
<html lang="fr">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://kit.fontawesome.com/14b05e12a0.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="style.css" />

<link rel="shortcut icon" href="./donnees/Icone/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="./donnees/Icone/favicon.ico" type="image/x-icon">

<title>Accueil</title>
</head>
<!-- 
	margin -> marge avec les autres éléments
	padding -> marge intérieur de l'élément
-->
<body
	style="background-image: url('https://images.unsplash.com/photo-1554188248-986adbb73be4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1900&q=1280');">

	<jsp:include page="header.jsp">
		<jsp:param name="title" value="Menu" />
	</jsp:include>

	<div class="container">

		<div class="row">
			<div class="col-md-9 mx-auto">
				<div class="card bg-light">
					<div class="card-header text-white"
						style="background-color: #474747;">Propos général</div>
					<div class="card-body">
						<h1>Bienvenue !</h1>
						<p>
						<p>Ce site a été réalisé dans le but de délivrer toutes les
							informations nécessaires concernant un grand nombre de
							médicaments !</p>
						<p>Professionnel ? Particulier ? Vous trouverez ici tout ce
							qu'il vous faut !</p>
						<p>
						<h2 class="text-danger">Alerte coronavirus :</h2>
						<p>Dans le cadre de la pandémie de coronavirus, nous vous
							recommendons de suivre les consignes suivantes :
						<p>
							<i class="fas fa-people-arrows"></i> Respectez une distance d'un
							mètre avec les personnes avec lesquelles vous entrez en contact
						<p>
							<i class="fas fa-hands-wash"></i> Lavez-vous les mains
							régulièrement
						<p>
							<i class="fas fa-head-side-mask"></i> Portez un masque lorsque
							nécessaire
						<h4 class="text-center">Ensemble, combattons la maladie</h4>

					</div>
				</div>
			</div>
			</br>
			<div class="col-md-3 mx-auto">
				<div class="card bg-light">
					<div class="card-header text-white"
						style="background-color: #474747;">A propos</div>
					<div class="card-body">
						<h1>Contexte</h1>
						<p>
						<p>Ce site est l'objet d'un projet scolaire de fin de semestre de 4e année d'ingénieur à l'ESIGELEC de Rouen.</p>
						<h2 class="text-info">Auteurs</h2>
						<p>Dandjinou Cyriaque</p>
						<p>Keita Ansoumane</p>
						<p>Labeye Loïc</p>
						<p>Nguyen Basile</p>
						<p>Vignon Raphael</p>

					</div>
				</div>
			</div>
		</div>
	</div>
	<br/>
	<div class="card-group">
		<%
			if (session.getAttribute("Statut") != null) {
		%>
		<div class="col-md-3 mx-auto">
			<a href="./GeneriqueListe" class="bg-info ">
				<div class="card bg-info text-white text-center p-3">
					<blockquote class="blockquote mb-0">
						<p>Consulter les groupes génériques disponibles </p>
					</blockquote>

				</div>
			</a>
		</div>
		<%
			}
		%>
<br/>
		<div class="col-md-3 mx-auto">
			<a href="./ListeMedicament" class="bg-info ">
				<div class="card text-white text-center p-3"
					style="background-color: #77a832">
					<blockquote class="blockquote mb-0">
						<p>Voir la liste des médicaments disponibles</p>
					</blockquote>
				</div>
			</a>
		</div>
		<br/>
		<%
			if (session.getAttribute("Statut") != null) {
		%>
		<div class="col-md-2 mx-auto">
			<a href="./Chat" class="bg-info ">
				<div class="card text-white text-center p-3"
					style="background-color: #77a832">
					<blockquote class="blockquote mb-0">
						<p>Recevoir un patient en ligne</p>
					</blockquote>
				</div>
			</a>
		</div>
		<%
			} else{
		%>
		<div class="col-md-2 mx-auto">
			<a href="./MedecinsDispo" class="bg-info ">
				<div class="card text-white text-center p-3"
					style="background-color: #77a832">
					<blockquote class="blockquote mb-0">
						<p>Discuter avec un professionel en ligne</p>
					</blockquote>
				</div>
			</a>
		</div>
		<%
			}
		%>
		<%
			if (session.getAttribute("Statut") != null) {
		%>
		</br>
		<div class="col-md-3 mx-auto">
			<a href="./Statistique" class="bg-info ">
				<div class="card  text-white text-center p-3"
					style="background-color: #bd2dac">
					<blockquote class="blockquote mb-0">
						<p>Consulter les statistiques disponibles</p>
					</blockquote>
				</div>
			</a>
		</div>
		<%
			}
		%>
	</div>
	
	
	<br/>
	
	<jsp:include page="Footer.jsp" >
		<jsp:param name="title" value="Detail"/>
	</jsp:include>
	
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