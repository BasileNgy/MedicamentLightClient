
	function ShowRenderer(vidyoConnector) {
	    var rndr = document.getElementById('renderer');
	    vidyoConnector.ShowViewAt({viewId: "renderer", x: rndr.offsetLeft, y: rndr.offsetTop, width: rndr.offsetWidth, height: rndr.offsetHeight});
	}

	// Run StartVidyoConnector when the VidyoClient is successfully loaded
	function StartVidyoConnector(VC) {

	    var microphonePrivacy = false;
		
		
		
	    var vidyoConnector;
	    var cameras = {};
	    var microphones = {};
	    var speakers = {};
	    var cameraPrivacy = false;
	    var microphonePrivacy = false;
	    var speakerPrivacy = false;

	    window.onresize = function() {
	        if (vidyoConnector) {
	            ShowRenderer(vidyoConnector);
	        }
	    };
	   
	    VC.CreateVidyoConnector({
	        viewId: "renderer",                            // Div ID where the composited video will be rendered, see VidyoConnector.html
	        viewStyle: "VIDYO_CONNECTORVIEWSTYLE_Default", // Visual style of the composited renderer
	        remoteParticipants: 2,                         // Maximum number of participants to render
	        logFileFilter: "warning",
	        logFileName:"",
	        userData:""
	    }).then(function(vc) {
	        vidyoConnector = vc;
	        ShowRenderer(vidyoConnector);
	        registerDeviceListeners(vidyoConnector, cameras, microphones, speakers);
	    }).catch(function(err) {
	        console.error("CreateVidyoConnector Failed " + err);
	    });
	    
        $("#microphoneButton").click(function() {
            microphonePrivacy = !microphonePrivacy;
            vidyoConnector.SetMicrophonePrivacy({
                privacy: microphonePrivacy
            }).then(function() {
                if (microphonePrivacy) {
                    $("#microphoneButton").addClass("microphoneOff").removeClass("microphoneOn");
                } else {
                    $("#microphoneButton").addClass("microphoneOn").removeClass("microphoneOff");
                }
                console.log("SetMicrophonePrivacy Success");
            }).catch(function() {
                console.error("SetMicrophonePrivacy Failed");
            });
        });
        
	    
	    
	}
	

    
    function joinLeave() {
        if ($("#joinLeaveButton").hasClass("callStart")) {
            $("#joinLeaveButton").removeClass("callStart").addClass("callEnd");
            $('#joinLeaveButton').prop('title', 'Quitter');
            connectToConference(vidyoConnector);
        } else {
            vidyoConnector.Disconnect().then(function() {
                console.log("Disconnect Success");
                $("#joinLeaveButton").removeClass("callEnd").addClass("callStart");
                $('#joinLeaveButton').prop('title', 'Rejoindre l appel');
                window.close();
            }).catch(function() {
                console.error("Disconnect Failure");
            });
        }
        $("#joinLeaveButton").one("click", joinLeave);
    }
	
	function registerDeviceListeners(vidyoConnector, cameras, microphones, speakers) {
	    vidyoConnector.RegisterLocalCameraEventListener({
	        onAdded: function(localCamera) {
	        },
	        onRemoved: function(localCamera) {
	        },
	        onSelected: function(localCamera) {
	        },
	        onStateUpdated: function(localCamera, state) {
	        }
	    });
	    vidyoConnector.RegisterLocalMicrophoneEventListener({
	        onAdded: function(localMicrophone) {
	        },
	        onRemoved: function(localMicrophone) {
	        },
	        onSelected: function(localMicrophone) {
	        },
	        onStateUpdated: function(localMicrophone, state) {
	        }
	    });
	    vidyoConnector.RegisterLocalSpeakerEventListener({
	        onAdded: function(localSpeaker) {
	        },
	        onRemoved: function(localSpeaker) {
	        },
	        onSelected: function(localSpeaker) {
	        },
	        onStateUpdated: function(localSpeaker, state) {
	        }
	    });
	}



	function connectToConference(vidyoConnector, returnURL) {

	    vidyoConnector.Connect({
	        host: "prod.vidyo.io",
	        token: data.token,
	        displayName: data.name,
	        resourceId: data.room.split('@')[0]+data.room.split('@')[1],

	        onSuccess: function() {
	            console.log("vidyoConnector.Connect : onSuccess callback received");
	            ShowRenderer(vidyoConnector);
	        },
	        onFailure: function(reason) {
	            // Failed
	            console.error("vidyoConnector.Connect : onFailure callback received");
	        },
	        onDisconnected: function(reason) {
	            // Disconnected
	            console.log("vidyoConnector.Connect : onDisconnected callback received");
	            ShowRenderer(vidyoConnector);
	        }
	    }).then(function(status) {
	        if (status) {
	            console.log("Connect Success");
	        } else {
	            console.error("Connect Failed");
	        }
	    }).catch(function() {
	        console.error("Connect Failed");
	    });
	}

	function onVidyoClientLoaded(status) {
		switch (status.state) {
			case "READY":    // The library is operating normally
				$("#renderer").removeClass("hidden");
				StartVidyoConnector(VC);
				break;
			case "FAILED":
					ShowFailed(status);
				break;
		}
		return true; // Return true to reload the plugins if not available
	}

	function loadVidyoClientLibrary() {
		//We need to ensure we're loading the VidyoClient library and listening for the callback.
		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.src = 'https://static.vidyo.io/latest/javascript/VidyoClient/VidyoClient.js?onload=onVidyoClientLoaded';
		document.getElementsByTagName('head')[0].appendChild(script);
	}
	
	function joinViaBrowser() {
		loadVidyoClientLibrary();
	}
	
	function changeCam(){
		vidyoConnector.CycleCamera();
	}
	