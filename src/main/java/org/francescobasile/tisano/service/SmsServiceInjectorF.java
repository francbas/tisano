package org.francescobasile.tisano.service;

public class SmsServiceInjectorF implements IFGenericServiceDependencyInjectionFactory {
    @Override
    public IFGenericServiceConsumer getConsumer() {
        return new MessageServiceConsumer(new SmsService());
    }
}
