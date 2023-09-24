package org.francescobasile.tisano.service;

public class MyClientApp {

    public static void main(String[] args) {
        String msg = "Tuttapppost!!";
        String oggetto = "argomento";
        String emailAt = "ciccio@asd.iy";
        String emailCc = "pippo@asd.iy";
        IFGenericServiceContext<Integer, String> emailctx = new EmailServiceContext(emailAt, emailCc, msg, oggetto);
        String num = "0123456";
        String ack = "noAck";
        IFGenericServiceContext<Integer, String> smsctx = new SmsServiceContext("+39", num, msg, ack);
        IFGenericServiceConsumer consumer;

        EmailServiceInjectorF injectorF = new EmailServiceInjectorF();
        consumer = injectorF.getConsumer();
        consumer.processService(emailctx);

        SmsServiceInjectorF injectorF1 = new SmsServiceInjectorF();
        consumer = injectorF1.getConsumer();
        consumer.processService(smsctx);
    }
}
