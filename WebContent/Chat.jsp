<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String pseudo = "Patient";
	String nomMedecin = "dr";
	String chemin;
%>
<%
if (session.getAttribute("Statut") != null) {
	pseudo = (String) session.getAttribute("Name");
}
chemin = request.getServerName()+":"+"8085"+request.getContextPath();
//System.out.println(path);
nomMedecin = (String) request.getAttribute("NomMedecin");
%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Chat en ligne</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/14b05e12a0.js" crossorigin="anonymous"></script>
		<script type="text/javascript" src="Client.js"></script>
		<script type="text/javascript">
			var nom={
			        NomMedecin: "<%=nomMedecin%>"
			    };
			var data={
			        pseudo: "<%=pseudo%>"
			    };
			var info={
					chem: "<%=chemin%>"
				};
			
			
			function startCall(){
				window.open("./VideoCall?name="+data.pseudo+"&room="+nom.NomMedecin, "Appel Video",
					    "height=500,width=800,modal=yes,alwaysRaised=yes");
			}
			
		</script>
		
		<link rel="stylesheet" href="style.css" />
		
	</head>
	<body
	 onresize="resizeWidth()"
	style="background-image: url('https://images.unsplash.com/photo-1554188248-986adbb73be4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1900&q=1280');">
		<jsp:include page="header.jsp" >
			<jsp:param name="title" value="Discussion"/>
		</jsp:include>
		
		<div class="container">
		
			<ul class="list-group col-lg-12">
			  <li class="list-group-item">
			  
			  	<div class="d-flex w-100 justify-content-between">
				  	<div class="input-group mb-3">
					  <textarea class="form-control" rows="10" aria-label="With textarea" id="history" readonly></textarea>
					</div>
				</div>
				
				<div class="d-flex w-100 justify-content-between">
					<div class="input-group mb-3">
					  <input type="text" class="form-control" placeholder="Ecrivez votre message..." aria-label="Default" id="txtMessage" aria-describedby="inputGroup-sizing-default">
					</div>
			     </div>
			    
			    <div class="d-flex w-100 justify-content-between">
			    	<button type="button" id="btnSend" class="btn btn-dark">Envoyer</button>
			    	<button type="button" id="btnVideoCall" class="btn" onClick="startCall()">
			    	
			    	<svg class="bi bi-camera-video" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					  <path fill-rule="evenodd" d="M2.667 3.5c-.645 0-1.167.522-1.167 1.167v6.666c0 .645.522 1.167 1.167 1.167h6.666c.645 0 1.167-.522 1.167-1.167V4.667c0-.645-.522-1.167-1.167-1.167H2.667zM.5 4.667C.5 3.47 1.47 2.5 2.667 2.5h6.666c1.197 0 2.167.97 2.167 2.167v6.666c0 1.197-.97 2.167-2.167 2.167H2.667A2.167 2.167 0 0 1 .5 11.333V4.667z"/>
					  <path fill-rule="evenodd" d="M11.25 5.65l2.768-1.605a.318.318 0 0 1 .482.263v7.384c0 .228-.26.393-.482.264l-2.767-1.605-.502.865 2.767 1.605c.859.498 1.984-.095 1.984-1.129V4.308c0-1.033-1.125-1.626-1.984-1.128L10.75 4.785l.502.865z"/>
					</svg>
			    	
			    	</button>
			    	
			    	<a class="btn btn-dark" id="btnClose" href="Index" role="button">Fermer la conversation</a>
			    </div>
			</ul>
		</div>
</body>
</html>