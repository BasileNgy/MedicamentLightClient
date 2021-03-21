<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<%@ page import="org.unbescape.html.HtmlEscape"%>

<!DOCTYPE html>
<html lang="fr">

<%!		String page_title = "Inscription";
		
%>

	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="style.css" />
				<script src="https://kit.fontawesome.com/14b05e12a0.js" crossorigin="anonymous"></script>

		<title>Inscription </title>
	</head>
<!-- 
	margin -> marge avec les autres éléments
	padding -> marge intérieur de l'élément
-->
	<body style="background-image: url('https://images.unsplash.com/photo-1471864190281-a93a3070b6de?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9');">
		<jsp:include page="header.jsp" >
			<jsp:param name="title" value="Inscription"/>
		</jsp:include>
		
		<div class="container mb-5">

			<div class="row">
				<div class="col-md-6 mx-auto">
					<div class="card bg-light">
						<div class="card-header text-white" style="background-color: #474747;"> 
							Inscription au site
						</div>
						<div class="card-body">
							<form method="post" action="SignIn">
							
								<% if (request.getAttribute("login_exist")!=null && (Boolean)request.getAttribute("login_exist") == true) {%>
									<div class="alert alert-danger alert-dismissible fade show mt-1" role="alert">
									Email déjà utilisé.
									<button type="button" class="close" data-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>			
									</div>
								<% }%>
								
								<% if (request.getAttribute("password_dissmatch")!=null &&(Boolean)request.getAttribute("password_dissmatch") == true) {%>
									<div class="alert alert-danger alert-dismissible fade show mt-1" role="alert">
									Les mots de passe ne correspondent pas.
									<button type="button" class="close" data-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>			
								</div>
								<% }%>
								
								<div class="form-group">
									<label for="LoginInput">E-mail</label>
									<input type="email" class="form-control" id="LoginInput" placeholder="Enter Login" name="Login" value="<%= HtmlEscape.escapeHtml5((String)session.getAttribute("Login")) %>" required>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Nom</label>
									<input type="text" class="form-control" id="exampleInputPassword1" placeholder="Nom" name="nom" required>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Prenom</label>
									<input type="text" class="form-control" id="exampleInputPassword1" placeholder="Prénom" name="prenom" required>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Mot de passe</label>
									<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password" required>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Répetez le mot de passe</label>
									<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password_verify" required>
								</div>
								<div class="form-group">
							    	<label for="inputState">Profession</label>
							    		<select id="profession" class="form-control" name="profession">
							        	<option selected value="medecin">Médecin</option>
							    		<option value="pharmacien">Pharmacien</option>
							    		<option value="labo">Laboratoire pharmaceutique</option>
							    		<option value="eem">Étudiant en médecine</option>
							    		<option value="eep">Étudiant en pharmacie</option>
							      	</select>
							    </div>
								<button type="submit" class="btn btn-dark">Valider</button>
							    <a href="Login" class="float-right text-dark pt-2">Déjà inscrit ?</a>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</body>

</html>