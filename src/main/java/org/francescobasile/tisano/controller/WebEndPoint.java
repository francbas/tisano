package org.francescobasile.tisano.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

//@WebServlet(
//        name = "WebSocketEndPointServer",
//        urlPatterns = {
//                "/chatbox",
//                "/chatbox/*"
//        },
//        initParams = {
//                @WebInitParam(name = "", value = "")
//        }
//)
public class WebEndPoint{//} extends HttpServlet {
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//    }
//
//    /**
//     * Servlet endpoint per l'accesso alla chat box. Per funzionare correttamente necessita di:
//     * 1) una classe Endpoint configurata opportunamente con un endpoint in ascolto su porta 8080 su protocollo ws.
//     * 2) un handler con encoders e decoders dei messaggi e indirizzamento delle risposte.
//     * 3) una pagina html con gli elementi di ingresso (destinatari e out coming msg) e uscita (incoming msg).
//     * 4) script javascript per la connessione websocket a endpoint e la funzione di callback per gestire i messaggi in ingresso
//     *
//     * @param req
//     * @param resp
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.doGet(req, resp);
//        final Writer writer = resp.getWriter();
//        resp.setContentType("text/html");
//
//        writer.write("<html><head>ChatBox Controller</head>");
//        writer.write("<body><h1>chatbox controller</h1></body>");
//        writer.write("</html>");
//        writer.close();
//        System.out.println("write cloed");
//
//    }
}
