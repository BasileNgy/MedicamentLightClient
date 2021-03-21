<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.unbescape.html.HtmlEscape"%>
<!DOCTYPE html>
<html lang="fr">

<%!		String page_title = "Connexion";
%>

	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="style.css" />
				<script src="https://kit.fontawesome.com/14b05e12a0.js" crossorigin="anonymous"></script>
		<title>Connexion </title>
	</head>
<!--
	margin -> marge avec les autres éléments
	padding -> marge intérieur de l'élément
-->
	<body style="background-image: url('https://images.unsplash.com/photo-1471864190281-a93a3070b6de?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9');">
		<jsp:include page="header.jsp" >
			<jsp:param name="title" value="Connexion"/>
		</jsp:include>

		<div class="container mb-5">

			<div class="row">
				<div class="col-md-6 mx-auto">
					<div class="card bg-light">
						<div class="card-header text-white" style="background-color: #474747;"">
							Connexion à votre espace
						</div>
						<div class="card-body">
							<form method="post" action="Login">
								<div class="form-group">
									<label for="LoginInput">E-mail</label>
									<input type="text" class="form-control" id="LoginInput" placeholder="Votre email" name="Login" value="<%=   HtmlEscape.escapeHtml5((String)request.getAttribute("Login")) %>" required>
								</div>

								<div class="form-group">
									<label for="exampleInputPassword1">Mot de passe</label>
									<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Votre mot de passe" name="password" required>
								</div>

								<% if ((Boolean)request.getAttribute("Login_failed")!=null && (Boolean)request.getAttribute("Login_failed") == true) {%>
									<div class="alert alert-danger alert-dismissible fade show mt-1" role="alert">
									Login ou mot de passe incorrect.
									<button type="button" class="close" data-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<% }%>

								<% if ((Boolean)request.getAttribute("not_registred")!=null && (Boolean)request.getAttribute("not_registred") == true) {%>
									<div class="alert alert-danger alert-dismissible fade show mt-1" role="alert">
									Votre compte n'a pas encore été accepté par un administrateur.
									<button type="button" class="close" data-dismiss="alert" aria-label="Close"">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<% }%>

	
								<div class="form-check">
   									<input type="checkbox" class="form-check-input" id="remember_me" name="remember_me">
    								<label class="form-check-label" for="exampleCheck1">Se souvenir de moi</label>
 								</div>
 															<button type="submit" class="float-right btn btn-dark">Valider</button>
 															<p>
 								<div class="form-group">
							    	<label for="inputState">Durée</label>
							    		<select id="lastFor" class="form-control" name="lastFor">
							        	<option selected value="24">1 jour</option>
							    		<option value="48">2 jours</option>
							    		<option value="168">1 semaine</option>
							    		<option value="336">2 semaines</option>
							    		<option value="720">1 mois</option>
							    		<option value="1440">2 mois</option>
							      	</select>
							    </div>
							    
							    <a href="SignIn" class="float-right text-dark pt-2">Pas encore inscrit ?</a>
							</form>
						</div>
					</div>
				</div>
			</div>


		</div>

	</body>

</html>
