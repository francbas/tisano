package org.francescobasile.tisano.socket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint("/echoendpoint")
public class EchoEndpoint {

    public static int contatore;

    @OnOpen
    public void onOpen(final Session session, EndpointConfig config) {
        contatore = 0;
        System.out.println(session.getId());
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @OnMessage
            public void onMessage(String msg) {
                System.out.println(msg);
                try {
                    session.getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

