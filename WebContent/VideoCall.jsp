<!DOCTYPE html>
<html>
<head>
	<title>VidyoConnector</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	
	<link href="/G2MedicamentLightClient/CSS/VidyoConnector.css" rel="stylesheet" type="text/css" >
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="/G2MedicamentLightClient/scripts/VidyoConnector.js"></script>
	
	<script type="text/javascript">
	var data={
		token: "<%= request.getAttribute("token")%>",
		room: "<%= request.getParameter("room")%>",
		name: "<%= request.getParameter("name")%>"
	};
	
	
	// Runs when the page loads
	$(function() {
		joinViaBrowser();
        $("#joinLeaveButton").one("click", joinLeave);
        
	});
	</script>
</head>

<!-- We execute the VidyoConnectorApp library on page load
to hook up all of the events to elements. -->
<body id="vidyoConnector">
	<div id="renderer">
	</div>
	<button id="joinLeaveButton" title="Lancer l'appel" type="button" class="btn btn-success toolbarButton callStart">
		<svg class="bi bi-play-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
		  <path d="M11.596 8.697l-6.363 3.692c-.54.313-1.233-.066-1.233-.697V4.308c0-.63.692-1.01 1.233-.696l6.363 3.692a.802.802 0 0 1 0 1.393z"/>
		</svg>
	</button>
		
	<button id="changeCamButton" onclick="changeCam()" title="Changer de camera" class="btn btn-light">
		<svg class="bi bi-camera-video" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
		  <path fill-rule="evenodd" d="M2.667 3.5c-.645 0-1.167.522-1.167 1.167v6.666c0 .645.522 1.167 1.167 1.167h6.666c.645 0 1.167-.522 1.167-1.167V4.667c0-.645-.522-1.167-1.167-1.167H2.667zM.5 4.667C.5 3.47 1.47 2.5 2.667 2.5h6.666c1.197 0 2.167.97 2.167 2.167v6.666c0 1.197-.97 2.167-2.167 2.167H2.667A2.167 2.167 0 0 1 .5 11.333V4.667z"/>
		  <path fill-rule="evenodd" d="M11.25 5.65l2.768-1.605a.318.318 0 0 1 .482.263v7.384c0 .228-.26.393-.482.264l-2.767-1.605-.502.865 2.767 1.605c.859.498 1.984-.095 1.984-1.129V4.308c0-1.033-1.125-1.626-1.984-1.128L10.75 4.785l.502.865z"/>
		</svg>
	</button>
	
	
	<button id="microphoneButton" title="Couper le micro" class="btn btn-light">
		<svg class="bi bi-mic" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
		  <path fill-rule="evenodd" d="M3.5 6.5A.5.5 0 0 1 4 7v1a4 4 0 0 0 8 0V7a.5.5 0 0 1 1 0v1a5 5 0 0 1-4.5 4.975V15h3a.5.5 0 0 1 0 1h-7a.5.5 0 0 1 0-1h3v-2.025A5 5 0 0 1 3 8V7a.5.5 0 0 1 .5-.5z"/>
		  <path fill-rule="evenodd" d="M10 8V3a2 2 0 1 0-4 0v5a2 2 0 1 0 4 0zM8 0a3 3 0 0 0-3 3v5a3 3 0 0 0 6 0V3a3 3 0 0 0-3-3z"/>
		</svg>
	</button>
	
</body>
</html>
