package medicaments.classes;
import java.util.Hashtable;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Class send and receive data from websocket
 * @author Basile
 *
 */
@ServerEndpoint(value="/{room}/{pseudo}", 
                configurator=ChatRoom.EndpointConfigurator.class)
public class ChatRoom {
    
    private static ChatRoom singleton = new ChatRoom();

    private ChatRoom() {
    }

    /**
     * Acquisition de notre unique instance ChatRoom 
     */
    public static ChatRoom getInstance() {
        return ChatRoom.singleton;
    }

    /**
     * On maintient toutes les sessions utilisateurs dans une collection.
     */
    private Hashtable<String, Session> sessions = new Hashtable<>();
    
    /**
     * Cette méthode est déclenchée à chaque connexion d'un utilisateur.
     */
    @OnOpen
    public void open(Session session, @PathParam("pseudo") String pseudo, @PathParam("room") String room ) {
        sendMessage( pseudo + " vient de se connecter", room);
        session.getUserProperties().put( "room", room );
        session.getUserProperties().put( "pseudo", pseudo );
        sessions.put( session.getId(), session );
    }

    /**
     * Cette méthode est d�clench�e � chaque d�connexion d'un utilisateur.
     */
    @OnClose
    public void close(Session session) {
        String pseudo = (String) session.getUserProperties().get( "pseudo" );
        String room = (String) session.getUserProperties().get( "room" );
        sessions.remove( session.getId() );
        sendMessage( pseudo + " vient de se déconnecter", room);
    }

    /**
     * Cette méthode est d�clench�e en cas d'erreur de communication.
     */
    @OnError
    public void onError(Throwable error) {
        System.out.println( "Error: " + error.getMessage() );
    }

    /**
     * Cette méthode est déclenchée à chaque réception d'un message utilisateur.
     */
    @OnMessage
    public void handleMessage(String message, Session session) {
        String pseudo = (String) session.getUserProperties().get( "pseudo" );
        String room = (String) session.getUserProperties().get( "room" );
        String fullMessage = pseudo + " : " + message; 
        
        sendMessage( fullMessage, room );
    }

    /**
     * Une m�thode privée, spécifique à notre exemple.
     * Elle permet l'envoie d'un message aux participants de la discussion.
     */
    private void sendMessage( String fullMessage, String room ) {
        // Affichage sur la console du server Web.
        System.out.println( fullMessage );      
        
        // On envoie le message � tout le monde.
        for( Session session : sessions.values() ) {
        	if (session.getUserProperties().get( "room" ).equals(room)) {
        		try {
	                session.getBasicRemote().sendText( fullMessage );
	            } catch( Exception exception ) {
	                System.out.println( "ERROR: cannot send message to " + session.getId() );
	            }
        	}
        }       
    }
    
    /**
     * Permet de ne pas avoir une instance différente par client.
     * ChatRoom est donc gérer en "singleton" et le configurateur utilise ce singleton. 
     */
    public static class EndpointConfigurator extends ServerEndpointConfig.Configurator {
        @Override 
        @SuppressWarnings("unchecked")
        public <T> T getEndpointInstance(Class<T> endpointClass) {
            return (T) ChatRoom.getInstance();
        }
    }
}
