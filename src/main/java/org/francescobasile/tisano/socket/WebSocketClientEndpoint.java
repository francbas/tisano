package org.francescobasile.tisano.socket;

import jakarta.websocket.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

//@ClientEndpoint
public class WebSocketClientEndpoint {
//    private static final Logger logger = Logger.getLogger("WebSocketClientEndpoint");
//    private WebSocketContainer websocket = ContainerProvider.getWebSocketContainer();
//    private Session session;
//    private String msg;
//
//    public void connect(String uri) {
//        try {
//            this.session = this.websocket.connectToServer(WebSocketClientEndpoint.class, new URI(uri));
//            logger.log(Level.INFO, "Connessione stabilita: session id {0}", this.session.getId());
//        } catch (DeploymentException | IOException | URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void sendText(String msg) {
//        try {
//            this.session.getBasicRemote().sendText(msg);
//            logger.log(Level.INFO, "Messaggio inviato: msg {0}", msg);
//        } catch (IOException e) {
//            logger.log(Level.INFO, "Exception rilevata: err {0}", e.toString());
//            throw new RuntimeException(e);
//        }
//    }
//
//    @OnOpen
//    public void onOpen(Session session) {
//        logger.log(Level.INFO, "Connessione onOpen: session id {0}", session.getId());
//    }
//
//    @OnMessage
//    public void onMessage(Session session, String msg) {
//        this.msg = msg;
//        logger.log(Level.INFO, "Evento onMessage ricevuto: messaggio  {0}", msg);
//    }
//
//    @OnClose
//    public void close(Session session, CloseReason reason) {
//        this.session = null;
//        this.msg = null;
//        logger.log(Level.INFO, "Connessione chiusa: reason  {0}", reason.getReasonPhrase());
//    }
//
//    @OnError
//    public void error(Session session, Throwable throwable) {
//        this.close(session, new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, throwable.toString()));
//        logger.log(Level.INFO, "Errore socket: errore  {0}", throwable.toString());
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public Session getSession() {
//        return session;
//    }
}
