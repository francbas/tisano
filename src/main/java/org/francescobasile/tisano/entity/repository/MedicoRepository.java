package org.francescobasile.tisano.entity.repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.Query;
import org.francescobasile.tisano.AbstractEntityRepository;
import org.francescobasile.tisano.entity.Medico;

import java.util.List;

@Stateless
public class MedicoRepository extends AbstractEntityRepository {

    public MedicoRepository() {
        super();
    }

    public Medico findId(int id) {
        return entityManager.find(Medico.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Medico> findAll() {
        Query query = entityManager.createQuery("select m from Medico m");
        return (List<Medico>) query.getResultList();
    }
}
