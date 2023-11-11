package org.francescobasile.tisano.socket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

//@ServerEndpoint("/chatbot")
public class ChatBotEndpoint {
//    private static final Logger logger = Logger.getLogger("ChatBotEndpoint");
//    /**
//     * Coda delle connessioni aperte all'endpoint
//     */
//    static Queue<Session> sessionQueue = new ConcurrentLinkedQueue<>();
//
//    public void send(String msg) {
//        try {
//            for (Session session :
//                    sessionQueue) {
//                session.getBasicRemote().sendText(msg);
//                logger.log(Level.INFO, "Messaggio inviato: session id {0}, testo \"{1}\"", new String[]{session.getId(), msg});
//            }
//        } catch (IOException e) {
//            logger.log(Level.INFO, e.toString());
//        }
//    }
//
//    @OnOpen
//    public void onOpen(Session session, EndpointConfig config) {
//        sessionQueue.add(session);
//        logger.log(Level.INFO, "Connessione aperta: session id {0}", session.getId());
//    }
//
//    @OnClose
//    public void close(Session session) {
//        sessionQueue.remove(session);
//        logger.log(Level.INFO, "Connessione chiusa: session id {0}", session.getId());
//    }
//
//    @OnError
//    public void error(Session session, Throwable throwable) {
//        sessionQueue.remove(session);
//        logger.log(Level.INFO, throwable.toString());
//        logger.log(Level.INFO, "Errore connessione: session id {0}", session.getId());
//    }

}
