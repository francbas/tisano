package org.francescobasile.tisano.service;

public class EmailServiceInjectorF implements IFGenericServiceDependencyInjectionFactory {
    @Override
    public IFGenericServiceConsumer getConsumer() {
        return new MessageServiceConsumer(new EmailService());
    }
}
