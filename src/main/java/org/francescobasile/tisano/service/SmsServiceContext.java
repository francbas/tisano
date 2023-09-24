package org.francescobasile.tisano.service;

import java.util.HashMap;
import java.util.Map;

public class SmsServiceContext implements IFGenericServiceContext<Integer, String> {

    private final Map<Integer, String> context = new HashMap<>();

    private static final Integer prefisso = 0;
    private static final Integer numero = 1;
    private static final Integer msg = 2;
    private static final Integer ack = 3;


    public SmsServiceContext(String prefisso, String numeroTel, String msg, String ack) {
        context.put(SmsServiceContext.prefisso, prefisso);
        context.put(SmsServiceContext.numero, numeroTel);
        context.put(SmsServiceContext.msg, msg);
        context.put(SmsServiceContext.ack, ack);
    }

    public String getPrefisso() {

        return context.get(prefisso);
    }

    public void setPrefisso(String prefisso) {

        context.put(SmsServiceContext.prefisso, prefisso);
    }

    public String getNumero() {

        return context.get(numero);
    }

    public void setNumero(String numero) {
        context.put(SmsServiceContext.numero, numero);
    }

    public String getMsg() {

        return context.get(msg);
    }

    public void setMsg(String msg) {

        context.put(SmsServiceContext.msg, msg);
    }

    public String getAck() {

        return context.get(ack);
    }

    public void setAck(String ack) {

        context.put(SmsServiceContext.ack, ack);
    }

    @Override
    public Map<Integer, String> getContext() {
        return context;
    }
}
