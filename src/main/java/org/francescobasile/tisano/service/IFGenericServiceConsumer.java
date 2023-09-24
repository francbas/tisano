package org.francescobasile.tisano.service;

public interface IFGenericServiceConsumer {
    <K, V> void processService(IFGenericServiceContext<K, V> ctx);
}
