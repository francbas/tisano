package org.francescobasile.tisano.service;

public class EmailService implements IFGenericService {
    @Override
    public <K, V> void executeService(IFGenericServiceContext<K, V> context) {
        EmailServiceContext ctx = (EmailServiceContext) context;
        System.out.println("Email Service executing...");
        System.out.println("emailAt: " + ctx.getEmailAt());
        System.out.println("emailCc: " + ctx.getEmailCc());
        System.out.println("oggetto: " + ctx.getOggetto());
        System.out.println("msg: " + ctx.getMsg());
        System.out.println("Email Service done!");
    }
}
