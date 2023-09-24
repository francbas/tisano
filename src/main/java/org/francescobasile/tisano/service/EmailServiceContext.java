package org.francescobasile.tisano.service;

import java.util.HashMap;
import java.util.Map;

public class EmailServiceContext implements IFGenericServiceContext<Integer, String> {
    private final Map<Integer, String> context = new HashMap<>();

    private static final Integer emailAt = 0;
    private static final Integer emailCc = 1;
    private static final Integer msg = 2;
    private static final Integer oggetto = 3;

    public EmailServiceContext(String emailAt, String emailCc, String msg, String oggetto) {
//        context = Arrays.asList(emailAt, emailCc, msg, oggetto);
        context.put(EmailServiceContext.emailAt, emailAt);
        context.put(EmailServiceContext.emailCc, emailCc);
        context.put(EmailServiceContext.msg, msg);
        context.put(EmailServiceContext.oggetto, oggetto);
    }

    public String getEmailAt() {
        return context.get(0);
    }

    public void setEmailAt(String emailAt) {
        context.put(EmailServiceContext.emailAt, emailAt);
    }

    public String getEmailCc() {
        return context.get(emailCc);
    }

    public void setEmailCc(String emailCc) {
        context.put(EmailServiceContext.emailCc, emailCc);
    }

    public String getMsg() {
        return context.get(msg);
    }

    public void setMsg(String msg) {
        context.put(EmailServiceContext.msg, msg);
    }

    public String getOggetto() {
        return context.get(oggetto);
    }

    public void setOggetto(String oggetto) {
        context.put(EmailServiceContext.oggetto, oggetto);
    }

    @Override
    public Map<Integer, String> getContext() {
        return context;
    }
}
