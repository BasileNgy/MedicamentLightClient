<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Statistiques</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/14b05e12a0.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="style.css" />
		
	</head>
	<body
	 onresize="resizeWidth()"
	style="background-image: url('https://images.unsplash.com/photo-1554188248-986adbb73be4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1900&q=1280');">
		<jsp:include page="header.jsp" >
			<jsp:param name="title" value="Statistique"/>
		</jsp:include>
		
		<div class="container">
			<div class="row">
				<ul class="list-group col-lg-12">
				  <li class="list-group-item">
				  	<div class="d-flex w-100 justify-content-between" id="label" name="label">
				  		<h4 class="mb-1">Répartition des laboratoires selon le nombre de médicaments produits</h4>
				  		</div>
				  	<iframe id="chart" height="541.25" src="https://app.powerbi.com/reportEmbed?reportId=f4e6c5fb-9e0f-4144-95b8-cc0cb273f3be&autoAuth=
				  	true&ctid=371cb156-9558-4286-a3cd-3059699b890c&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly93YWJpLWV1cm9wZS1ub3J0aC1iLXJlZGlyZWN0LmFuYWx5c2lzLndpbmRvd3MubmV0LyJ9" 
				  	frameborder="0" allowFullScreen="true"></iframe>

				  </li>
				  <li class="list-group-item">
				  	<div class="d-flex w-100 justify-content-between">
				  		<h4 class="mb-1">Prix moyen des médicaments par année</h4>
				  	</div>
				  	<iframe id="chart2"  height="541.25"  src="https://app.powerbi.com/reportEmbed?reportId=a2b2ba2b-28dc-4af3-b6f7-9a219d8a4619&autoAuth=
				  	true&ctid=371cb156-9558-4286-a3cd-3059699b890c&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly93YWJpLWV1cm9wZS1ub3J0aC1iLXJlZGlyZWN0LmFuYWx5c2lzLndpbmRvd3MubmV0LyJ9" 
				  	frameborder="0" allowFullScreen="true"></iframe>
				  </li>
				</ul>
			</div>
		</div>
		
		<script type="text/javascript">  
			function resizeWidth(){
				document.getElementById("chart").width = document.getElementById("label").offsetWidth;
				document.getElementById("chart").height = document.getElementById("chart").offsetWidth*0.75;
				document.getElementById("chart2").width = document.getElementById("label").offsetWidth;
				document.getElementById("chart2").height = document.getElementById("chart").offsetWidth*0.75;
			}
			resizeWidth();
		</script>
		
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
