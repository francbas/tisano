package org.francescobasile.tisano.service;

import java.util.Map;

public interface IFGenericServiceContext<K, V> {
    Map<K, V> getContext();
}
