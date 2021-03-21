/**
 * 
 */
window.addEventListener( "load", function( event ) {
	
	let room = nom.NomMedecin;
	let pseudo = data.pseudo;
	let chemin = info.chem;
	
	if (pseudo.localeCompare("Patient") == 0){
		pseudo = prompt( "Veuillez saisir votre Nom :" );
	}

    //let ws = new WebSocket( "ws://localhost:8085/G2MedicamentLightClient/"+room+"/" + pseudo );
    let ws = new WebSocket( "ws://"+chemin+"/"+room+"/"+pseudo );

    
    let txtHistory = document.getElementById( "history" );
    let txtMessage = document.getElementById( "txtMessage" );
    txtMessage.focus();
    
    ws.addEventListener( "open", function( evt ) {
        console.log( "Connection established" );
    });

    ws.addEventListener( "message", function( evt ) {
        let message = evt.data;
        console.log( "Receive new message: " + message );
        txtHistory.value += message + "\n";
    });
    
    ws.addEventListener( "close", function( evt ) {
        console.log( "Connection closed" );
    });
    
    
    let btnSend = document.getElementById( "btnSend" );
    btnSend.addEventListener( "click", function( clickEvent ) {
        ws.send( txtMessage.value );
        txtMessage.value = "";
        txtMessage.focus();
    });
 
    let btnClose = document.getElementById( "btnClose" );
    btnClose.addEventListener( "click", function( clickEvent ) {
        ws.close();
    });
    
});