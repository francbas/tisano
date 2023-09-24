package org.francescobasile.tisano;

import java.util.List;

public interface EntityService<E> {
    E findById(int id);

    List<E> findAll();

    Boolean create(E entity);

    <P> Boolean update(E entity);

    Boolean delete(E entity);
}
