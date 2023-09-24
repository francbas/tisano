package org.francescobasile.tisano.service;

public interface IFGenericService {
    <K, V> void executeService(IFGenericServiceContext<K, V> context);
}
