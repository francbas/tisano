package org.francescobasile.tisano.service;

public class SmsService implements IFGenericService {

    @Override
    public <K, V> void executeService(IFGenericServiceContext<K, V> context) {
        SmsServiceContext ctx = (SmsServiceContext) context;
        System.out.println("SMS Service executing...");
        System.out.println("prefisso: " + ctx.getPrefisso());
        System.out.println("numero: " + ctx.getNumero());
        System.out.println("msg: " + ctx.getMsg());
        System.out.println("ack: " + ctx.getAck());
        System.out.println("SMS Service done!");
    }
}
