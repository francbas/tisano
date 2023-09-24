package org.francescobasile.tisano.service;

public class MessageServiceConsumer implements IFGenericServiceConsumer {
    private final IFGenericService service;

    public MessageServiceConsumer(IFGenericService service) {
        this.service = service;
    }

    @Override
    public <K, V> void processService(IFGenericServiceContext<K, V> ctx) {
        service.executeService(ctx);
    }
}
